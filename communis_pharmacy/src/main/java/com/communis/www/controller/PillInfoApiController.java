package com.communis.www.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PillInfoApiController {
	
	@RequestMapping(value="/pillRank/getPillData", method= {RequestMethod.GET, RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> callPillData() {

		try {
			String requestUrl = "http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList?" + 
								"ServiceKey=V9DGQbhMJjmbWxK4YRrcT8ermlbt7BNHmCwFW8XVWFHjcQJ2BjGutncG1vYC4ZAUGuzz%2BwkFyFkOYRA0m4g5pA%3D%3D" + 
								"&type=json" +
								"&pageNo=1" +
								"&numOfRows=10" +
								"&flag=Y";
			URL url = new URL(requestUrl);
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
			log.info(">>>> result >>>> {}"+result);
			
			return ResponseEntity.ok().body(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 에러 발생 시 500 에러 반환
		}
	}
	
}
