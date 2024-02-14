package com.communis.www.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
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

import com.communis.www.domain.PillNaverEncycVO;
import com.communis.www.domain.PillNaverResultVO;
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

	//private final PillService psv;
	
	// 네이버 검색 API
	private final String clientId = "GKiuRoD8tw_H5nG_0SA_";
	private final String clientSecret = "vmRxh0_ve3";
    
    public PillVO fetchPillData(String pillName) {
        try {
            // 공공 API 호출
            ResponseEntity<String> publicResponse = fetchPillDataFromPublicAPI(pillName);
            log.info(">>>> 7 >>>>>{}",publicResponse);
            // 응답이 비어 있는 경우 처리
            if (publicResponse == null || publicResponse.getBody() == null || publicResponse.getBody().isEmpty()) {
                log.error("Empty response received from public API");
                return null;
            }

            // JSON 형식으로 파싱
            JSONObject publicJson = new JSONObject(publicResponse.getBody());
            String itemName = publicJson.getString("itemName");

            // 특정 약물 이름이 일치하는지 확인
            if (itemName.contains(pillName)) {
                String entpName = publicJson.getString("entpName");
                String efcyQesitm = publicJson.getString("efcyQesitm");

                // 네이버 API 호출을 통해 썸네일 가져오기
                String thumbnail = fetchThumbnailFromNaverAPI(itemName, entpName);

                // 네이버 응답이 null이거나 비어 있는 경우 처리
                if (thumbnail == null || thumbnail.isEmpty()) {
                    log.error("Empty or null response received from Naver API");
                    return null;
                }

                // PillVO 객체 생성 및 데이터 채우기
                PillVO pillInfo = new PillVO(itemName, entpName, efcyQesitm, thumbnail);
                log.info(">>> pillVO >>> {}", pillInfo);
                return pillInfo;
            } else {
                return null; // 약물 이름이 일치하지 않는 경우
            }
            // DB에 데이터 저장 (추가 작업 필요)
        } catch (Exception e) {
            log.error("Error while fetching pill data: {}", e.getMessage());
            return null;
        }
    }
    private String publicApi(int page) {
        try {
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList");
            urlBuilder.append("?serviceKey=V9DGQbhMJjmbWxK4YRrcT8ermlbt7BNHmCwFW8XVWFHjcQJ2BjGutncG1vYC4ZAUGuzz%2BwkFyFkOYRA0m4g5pA%3D%3D"); // 여기에 본인의 실제 서비스키 입력
            urlBuilder.append("&pageNo="+Integer.toString(page));
            urlBuilder.append("&type=json"); // JSON 형식으로 응답 받기

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder response = new StringBuilder();
            //response.append("{");
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            log.info(">>>> response >>>> {}", response);
            br.close();
            conn.disconnect();
            
            return response.toString();
            
        } catch (Exception e) {
            // 오류 발생 시 Internal Server Error 반환
        	return "An error occurred while fetching pill data.";
        	//return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching pill data.");
        }
    }
    
    public ResponseEntity<String> fetchPillDataFromPublicAPI(String pillName) {
    	
        try {
        	
        	for(int i = 1; i < 470; i ++) {
        		String response = publicApi(i);


		        // 응답이 없는 경우
	            if (response.length() == 0) {
	                log.error("Empty response received from public API");
	                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	            }
	            
	            
	            log.info(">>>> 1");
		        
	            // JSON 형식의 응답 문자열을 JSONObject로 변환
	            JSONObject jsonResponse = new JSONObject(response.toString().trim());
	            log.info(">>>> 2");
	            // body 객체에서 items 배열을 가져옴
	            JSONArray items = jsonResponse.getJSONObject("body").getJSONArray("items");
	            log.info(">>>> 3");
	            log.info("items.length>>>");
	            // items 배열 for문 돌려서 이름 일치하는거 가져오기
	            for(int j = 0; j < items.length(); j++) {
		            JSONObject firstItem = items.getJSONObject(j);
		            log.info(">>>> 4");
		            // firstItem에서 itemName 값을 가져옴
		            String itemName = firstItem.getString("itemName");
		            log.info(">>>> 5");
		            // itemName을 비교
		            if (itemName.contains(pillName)) {
		                // itemName이 pillName을 포함하고 있는 경우에 대한 처리
		            	log.info(">>>> 6 ");
		                return ResponseEntity.ok().body(firstItem.toString());
		            } 
	            }
        	}
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item name does not match the provided pillName.");
        } catch (Exception e) {
            // 오류 발생 시 Internal Server Error 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching pill data.");
        }
    }
    
	private String fetchThumbnailFromNaverAPI(String itemName, String entpName) {
		
		try {
			// 네이버 API 호출을 위한 URI 생성
			URI naverUri = UriComponentsBuilder
	    		.fromUriString("https://openapi.naver.com")
	    		.path("/v1/search/encyc.json")
	            .queryParam("query", itemName + " " + entpName + " 이미지")
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
			
			ObjectMapper om = new ObjectMapper();
			PillNaverResultVO resultVO = null;

			try {
			    resultVO = om.readValue(resp.getBody(), PillNaverResultVO.class);
			    
			} catch (JsonMappingException e) {
			    e.printStackTrace();
			    
			} catch (JsonProcessingException e) {
			    e.printStackTrace();
			}

			List<PillNaverEncycVO> encycList =resultVO.getItems();
			
			if (encycList != null && !encycList.isEmpty()) {
			    // 첫 번째 아이템의 썸네일 정보만 추출
			    String thumbnail = encycList.get(0).getThumbnail();
			    log.info(">>> thumbnail >>> {}", thumbnail);
			    return thumbnail;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
			
	
//    private int getPillTotalCount(String responseBody) {
//        try {
//            // JSON 데이터 파싱
//            JSONObject responseJson = new JSONObject(responseBody);
//            JSONObject bodyJson = responseJson.getJSONObject("body");
//
//            // "totalCount" 필드 추출
//            int totalCount = bodyJson.getInt("totalCount");
//            return totalCount;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0; // 에러 발생 시 0을 반환하거나 적절한 에러 처리를 수행할 수 있습니다.
//        }
//    }
    
}
