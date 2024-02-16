package com.communis.www.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.communis.www.domain.PillNaverImageVO;
import com.communis.www.domain.PillNaverResultVO;
import com.communis.www.domain.PillNaverShopVO;
import com.communis.www.domain.PillVO;
import com.communis.www.service.PillService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/apiPill")
public class PillInfoApiController {
	
	// 네이버 검색 API
	private final String clientId = "GKiuRoD8tw_H5nG_0SA_";
	private final String clientSecret = "vmRxh0_ve3";
    
    public List<PillVO> fetchPillData(String pillName) {

        try {
            int totalCount = getPillTotalCount(pillName);
            List<PillVO> pillInfoList = new ArrayList<>();
            
            for (int i = 0; i < totalCount; i++) {
                ResponseEntity<List<String>> publicResponse = fetchPillDataFromPublicAPI(i, pillName);
                // 응답이 비어 있는 경우 처리
                if (publicResponse == null || publicResponse.getBody() == null || publicResponse.getBody().isEmpty()) {
                    log.error("Empty response received from public API");
                    continue;
                }
                
                List<String> jsonResponseList = publicResponse.getBody();
                
                JSONObject publicJson = new JSONObject(jsonResponseList.get(i));
                String itemName = publicJson.getString("itemName");
                String entpName = publicJson.getString("entpName");
                String efcyQesitm = publicJson.getString("efcyQesitm");
                String thumbnail = fetchThumbnailFromNaverAPI(itemName, entpName);

                // PillVO 객체 생성 및 데이터 채우기
                PillVO pillInfo = new PillVO(itemName, entpName, efcyQesitm, thumbnail);
                pillInfoList.add(pillInfo);
            }

            return pillInfoList;
            
        } catch (Exception e) {
        	log.error("Error while fetching pill data: {}", e.getMessage());
	        return null;
	    }
	}

    private String publicApi(int page, String pillName) {
    	
        try {
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList");
            urlBuilder.append("?serviceKey=V9DGQbhMJjmbWxK4YRrcT8ermlbt7BNHmCwFW8XVWFHjcQJ2BjGutncG1vYC4ZAUGuzz%2BwkFyFkOYRA0m4g5pA%3D%3D"); // 여기에 본인의 실제 서비스키 입력
            urlBuilder.append("&itemName=" + URLEncoder.encode(pillName, "UTF-8"));
            urlBuilder.append("&pageNo="+Integer.toString(page));
            urlBuilder.append("&type=json"); // JSON 형식으로 응답 받기

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();
            conn.disconnect();
            
            return response.toString();
            
        } catch (Exception e) {
            // 오류 발생 시 Internal Server Error 반환
        	return "An error occurred while fetching pill data.";
        	//return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching pill data.");
        }
    }
    
    private ResponseEntity<List<String>> fetchPillDataFromPublicAPI(int count, String pillName) {
    	
        try {
        	List<String> pillInfoList = new ArrayList<>();
        	int totalCount = getPillTotalCount(pillName);
            
            // total count를 기반으로 페이지 수 계산
            int pageCount = (int)Math.ceil((double)totalCount / 10); // numOfRows가 10으로 고정

            for(int i = 1; i <= pageCount; i++) {
                String response = publicApi(i, pillName);

                // 응답이 없는 경우
                if (response.length() == 0) {
                    log.error("Empty response received from public API");
                    continue;
                }
                
                // JSON 형식의 응답 문자열을 JSONObject로 변환
                JSONObject jsonResponse = new JSONObject(response.toString().trim());
                // body 객체에서 items 배열을 가져옴
                JSONArray items = jsonResponse.getJSONObject("body").getJSONArray("items");
                // items 배열 for문 돌려서 이름 일치하는거 가져오기
                for(int j = 0; j < items.length(); j++) {
                    JSONObject item = items.getJSONObject(j);
                    // item에서 itemName 값을 가져옴
                    String itemName = item.getString("itemName");
                    // itemName을 비교하여 포함하면 리스트에 추가
                    if (itemName.contains(pillName)) {
                        pillInfoList.add(item.toString());
                    } 
                }
            }
            
            if (pillInfoList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
            } else {
                return ResponseEntity.ok().body(pillInfoList);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }
    
	private String fetchThumbnailFromNaverAPI(String itemName, String entpName) {
		
		try {
			// 네이버 API 호출을 위한 URI 생성
			URI naverUri = UriComponentsBuilder
	    		.fromUriString("https://openapi.naver.com")
	    		.path("/v1/search/image")
	            .queryParam("query", itemName + " " + entpName + "소개")
	            .encode()
	            .build()
	            .toUri();
			
			
			RequestEntity<Void> req = RequestEntity
                    .get(naverUri)
                    .header("X-Naver-Client-Id", clientId)
                    .header("X-Naver-Client-Secret", clientSecret)
                    .build();
			
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> resp = restTemplate.exchange(req, String.class);
			
	        // JSON 응답을 파싱하여 PillNaverResultVO 객체 반환
			ObjectMapper om = new ObjectMapper();
			PillNaverResultVO resultVO = null;

			try {
			    resultVO = om.readValue(resp.getBody(), PillNaverResultVO.class);

			} catch (JsonMappingException e) {
			    e.printStackTrace();

			} catch (JsonProcessingException e) {
			    e.printStackTrace();
			}

			List<PillNaverImageVO> imgList =resultVO.getItems();

			if (imgList != null && !imgList.isEmpty()) {
			    // 첫 번째 아이템의 썸네일 정보만 추출
			    String thumbnail = imgList.get(0).getThumbnail();
			    return thumbnail;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
    
//	private String fetchThumbnailFromNaverAPI(String itemName, String entpName) {
//		
//		try {
//			// 네이버 API 호출을 위한 URI 생성
//			URI naverUri = UriComponentsBuilder
//	    		.fromUriString("https://openapi.naver.com")
//	    		.path("/v1/search/shop.json")
//	            .queryParam("query", itemName + " " + entpName)
//	            .encode()
//	            .build()
//	            .toUri();
//			
//			
//			RequestEntity<Void> req = RequestEntity
//                    .get(naverUri)
//                    .header("X-Naver-Client-Id", clientId)
//                    .header("X-Naver-Client-Secret", clientSecret)
//                    .build();
//			
//			RestTemplate restTemplate = new RestTemplate();
//			ResponseEntity<String> resp = restTemplate.exchange(req, String.class);
//			
//	        // JSON 응답을 파싱하여 PillNaverResultVO 객체 반환
//	        ObjectMapper om = new ObjectMapper();
//	        PillNaverResultVO resultVO = om.readValue(resp.getBody(), PillNaverResultVO.class);
//	        
//	        String thumbnail = null;
//			List<PillNaverShopVO> shopList =resultVO.getItems();
//	        for (PillNaverShopVO shop : shopList) {
//				thumbnail = shop.getImage();
//	        }
//	        return thumbnail; // 이미지 URL을 담은 리스트 반환
//	        
//	    } catch (Exception e) {
//	        log.error("Error while fetching thumbnails from Naver API: {}", e.getMessage());
//	        return "Error while fetching thumbnails from Naver API"; // 에러 발생 시 빈 리스트 반환
//	    }
//	}
			

	
    private int getPillTotalCount(String pillName) {
        try {
            // 첫 번째 페이지의 데이터를 가져와서 total count 확인
            String firstPageResponse = publicApi(1, pillName);
            if (firstPageResponse.length() == 0) {
                log.error("Empty response received from public API");
                return 0;
            }
            
            // JSON 형식의 응답 문자열을 JSONObject로 변환하여 total count 추출
            JSONObject firstPageJsonResponse = new JSONObject(firstPageResponse.toString().trim());
            int totalCount = firstPageJsonResponse.getJSONObject("body").getInt("totalCount");
            return totalCount;
            
        } catch (Exception e) {
            e.printStackTrace();
            return 0; // 에러 발생 시 0을 반환하거나 적절한 에러 처리를 수행할 수 있습니다.
        }
    }
    
}
