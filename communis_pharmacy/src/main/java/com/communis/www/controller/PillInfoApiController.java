package com.communis.www.controller;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.communis.www.domain.PagingVO;
import com.communis.www.domain.PillVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PillInfoApiController {
	
	// 네이버 검색 API
	private final String clientId = "GKiuRoD8tw_H5nG_0SA_";
	private final String clientSecret = "vmRxh0_ve3";
	
//            // 공공데이터 API 호출 및 데이터 가져오기
//            RestTemplate restTemplate = new RestTemplate();
//            ResponseEntity<String> publicResponse = restTemplate.getForEntity(publicUri, String.class);
//
//            // JSON 데이터 파싱 후 totalCount만 추출
//            int totalCount = getPillTotalCount(publicResponse.getBody());
//            
//            // JSON 데이터 파싱 후 PillVO 객체로 변환
//            List<PillVO> pillList = parseJsonToPillList(publicResponse.getBody());
//
//            // 각 제품에 대해 네이버 API를 호출하여 썸네일 정보 가져오기
//            for (PillVO pill : pillList) {
//                String thumbnail = fetchThumbnail(pill.getItemName(), pill.getEntpName());
//                pill.setThumbnail(thumbnail);
//            }
//
//            log.info(">>> pillList {}>>>>" ,pillList);
//            return ResponseEntity.ok().body(pillList);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//	}
	@RequestMapping(value="/pillRank/getPillInfo", method= {RequestMethod.GET, RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> callPillData() {

		try {
			URI publicUri = UriComponentsBuilder
					.fromUriString("http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList")
					.queryParam("ServiceKey=V9DGQbhMJjmbWxK4YRrcT8ermlbt7BNHmCwFW8XVWFHjcQJ2BjGutncG1vYC4ZAUGuzz%2BwkFyFkOYRA0m4g5pA%3D%3D")
			        .queryParam("type", "json")
			        .queryParam("pageNo", 1)
			        .queryParam("numOfRows", 10)
			        .queryParam("flag", "Y")
			        .encode()
			        .build()
			        .toUri();
			
			RestTemplate restTemplate = new RestTemplate();
			URL url = new URL(publicUri.toString());
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");

			BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
			StringBuilder result = new StringBuilder();
			String returnLine;

			while((returnLine = br.readLine()) != null) {
				result.append(returnLine);
				log.info(returnLine);
			}
			urlConnection.disconnect();
			log.info(">>>> Public result >>>> {}" + result);

			return ResponseEntity.ok().body(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 에러 발생 시 500 에러 반환
		}
	}
	
	@RequestMapping(value="/pillRank/getPillImg", method= {RequestMethod.GET, RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> callPillImg(@PathVariable("itemName") String itemName, @PathVariable("entpName") String entpName) {
		
		try {
			// 네이버 API 호출을 위한 URI 생성
			URI naverUri = UriComponentsBuilder
	    		.fromUriString("https://openapi.naver.com")
	    		.path("v1/search/encyc.json")
	            .queryParam("query", itemName + " " + entpName + " 이미지")
	            .queryParam("display", 1)
	            .encode()
	            .build()
	            .toUri();
			
			// 네이버 API 호출
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Naver-Client-Id", clientId);
            headers.set("X-Naver-Client-Secret", clientSecret);
            
			URL url = new URL(naverUri.toString());
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");

			BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
			StringBuilder result = new StringBuilder();
			String returnLine;

			while((returnLine = br.readLine()) != null) {
				result.append(returnLine);
				log.info(returnLine);
			}
			urlConnection.disconnect();
			log.info(">>>> Naver result >>>> {}" + result);

            
            return ResponseEntity.ok().body(result.toString());

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 에러 발생 시 500 에러 반환
		}
	}

//	private String fetchThumbnail(String itemName, String entpName) {
//        try {
//            // 네이버 API 호출을 위한 URI 생성
//            URI naverUri = UriComponentsBuilder
//            		.fromUriString("https://openapi.naver.com")
//            		.path("v1/search/encyc.json")
//                    .queryParam("query", itemName + " " + entpName + " 이미지")
//                    .queryParam("display", 1)
//                    .encode()
//                    .build()
//                    .toUri();
//
//            // 네이버 API 호출 및 썸네일 URL 가져오기
//            RestTemplate restTemplate = new RestTemplate();
//            HttpHeaders headers = new HttpHeaders();
//            headers.set("X-Naver-Client-Id", clientId);
//            headers.set("X-Naver-Client-Secret", clientSecret);
//            HttpEntity<String> entity = new HttpEntity<>("body", headers);
//            ResponseEntity<String> naverResponse = restTemplate.exchange(naverUri, HttpMethod.GET, entity, String.class);
//
//            // 썸네일 URL 추출
//            String thumbnailUrl = extractThumbnailUrl(naverResponse.getBody());
//            return thumbnailUrl;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//	}
//
//	private String extractThumbnailUrl(String responseBody) {
//	    try {
//	        // 1. 문자열 형태의 JSON을 파싱하기 위한 JSONParser 객체 생성.
//	        JSONParser parser = new JSONParser();
//	        // 2. 문자열을 JSON 형태로 JSONObject 객체에 저장.
//	        JSONObject responseJson = (JSONObject) parser.parse(responseBody);
//	        // 3. 필요한 리스트 데이터 부분만 가져와 JSONArray로 저장.
//	        JSONArray itemsArray = (JSONArray) responseJson.get("items");
//	        // 4. 첫 번째 아이템의 link 속성값을 썸네일 URL로 반환
//	        JSONObject firstItem = (JSONObject) itemsArray.get(0);
//	        String imageUrl = (String) firstItem.get("link");
//	        return imageUrl;
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        return null;
//	    }
//	}
//	
//	//JSON 데이터 파싱 후 PillVO 객체로 변환하는 로직 작성
//    private List<PillVO> parseJsonToPillList(String responseBody) {
//        try {
//            JSONParser parser = new JSONParser();
//            JSONObject responseJson = (JSONObject) parser.parse(responseBody);
//            JSONArray itemsArray = (JSONArray) responseJson.get("items");
//            // 각 아이템을 PillVO 객체로 변환하여 리스트에 추가
//            List<PillVO> pillList = new ArrayList<>();
//            for (Object item : itemsArray) {
//                JSONObject itemJson = (JSONObject) item;
//                PillVO pill = new PillVO();
//                // 필요한 데이터를 JSON에서 추출하여 PillVO 객체에 설정
//                pill.setItemName((String) itemJson.get("itemName"));
//                pill.setEntpName((String) itemJson.get("entpName"));
//                pill.setEfcyQesitm((String) itemJson.get("efcyQesitm"));
//                pill.setThumbnail(null); // 초기값은 null로 설정
//                pillList.add(pill);
//            }
//            return pillList;
//        } catch (ParseException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//    
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
