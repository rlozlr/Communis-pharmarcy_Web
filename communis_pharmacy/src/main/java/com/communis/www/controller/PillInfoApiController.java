package com.communis.www.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.communis.www.domain.PillVO;
import com.communis.www.service.PillService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/apiPill")
public class PillInfoApiController {

	private final PillService psv;
	
	// 네이버 검색 API
	private final String clientId = "GKiuRoD8tw_H5nG_0SA_";
	private final String clientSecret = "vmRxh0_ve3";
    
    public PillVO fetchPillData(String pillName) {
        try {
        	
            // 공공 API 호출
            ResponseEntity<String> publicResponse = fetchPillDataFromPublicAPI();
            JSONObject publicJson = new JSONObject(publicResponse.getBody());
            String responseBody = publicResponse.getBody();
            log.info("Public API response: {}", responseBody);
            // 응답이 비어 있는 경우 처리
            if (responseBody == null || responseBody.isEmpty()) {
                log.error("Empty response received from public API");
                return null;
            }
            
          String itemName = publicJson.getString("itemName");
          if(itemName.contains(pillName)) {
	          String entpName = publicJson.getString("entpName");
	          String efcyQesitm = publicJson.getString("efcyQesitm");
	          
	          // 네이버 API 호출
	          ResponseEntity<String> naverResponse = fetchThumbnailFromNaverAPI(itemName, entpName);
	          JSONObject naverJson = new JSONObject(naverResponse.getBody());
	          JSONArray itemsArray = naverJson.getJSONArray("items");
	          JSONObject item = itemsArray.getJSONObject(0); // 첫 번째 아이템 가져오기
	          String thumbnail = item.getString("thumbnail");
	          
	          // PillVO 객체 생성 및 데이터 채우기
	          PillVO pillInfo = new PillVO(itemName, entpName, efcyQesitm, thumbnail);
	          log.info(">>> pillVO >>> {}", pillInfo);
	          return pillInfo;
          } else {
          	return null;
          }
          // DB에 데이터 저장
          //psv.insertPill(pillInfo);
      } catch (Exception e) {
          log.error("Error while fetching pill data: {}", e.getMessage());
          return null;
      }
  }
            
	private ResponseEntity<String> fetchPillDataFromPublicAPI(String pillName) {
		
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
			log.info(">>>> Public result >>>> {}", result);

			return ResponseEntity.ok().body(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 에러 발생 시 500 에러 반환
		}
	}
	
	private ResponseEntity<String> fetchThumbnailFromNaverAPI(String itemName, String entpName) {
		
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
			
			URL url = new URL(naverUri.toString());
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setRequestProperty("X-Naver-Client-Id", clientId);
			urlConnection.setRequestProperty("X-Naver-Client-Secret", clientSecret);

			BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
			StringBuilder result = new StringBuilder();
			String returnLine;

			while((returnLine = br.readLine()) != null) {
				result.append(returnLine);
				log.info(returnLine);
			}
			urlConnection.disconnect();
			log.info(">>>> Naver result >>>> {}", result);

			return ResponseEntity.ok().body(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 에러 발생 시 500 에러 반환
		}
	}
//	        
//	        return resp;
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 에러 발생 시 500 에러 반환
//		}
//	}

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
