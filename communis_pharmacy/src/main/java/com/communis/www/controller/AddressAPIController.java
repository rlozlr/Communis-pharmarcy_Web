package com.communis.www.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.communis.www.domain.AddressVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AddressAPIController {

    public AddressVO fetchAddressDataFromPublicAPI(String roadName) {
    	
        try {
            StringBuilder urlBuilder = new StringBuilder("http://openapi.epost.go.kr/postal/retrieveNewAdressAreaCdService/retrieveNewAdressAreaCdService/getNewAddressListAreaCd");
            urlBuilder.append("?serviceKey=V9DGQbhMJjmbWxK4YRrcT8ermlbt7BNHmCwFW8XVWFHjcQJ2BjGutncG1vYC4ZAUGuzz%2BwkFyFkOYRA0m4g5pA%3D%3D");
            urlBuilder.append("&srchwrd=" + URLEncoder.encode(roadName, "UTF-8"));
            
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
            
            // XML 파싱
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(url.openStream());
            
            // 루트 엘리먼트 가져오기
            Element root = document.getDocumentElement();
            
            // 주소 정보 노드 가져오기
            NodeList addressNodes = root.getElementsByTagName("newAddressListAreaCd");
            if (addressNodes.getLength() > 0) {
                Node addressNode = addressNodes.item(0);
                if (addressNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element addressElement = (Element) addressNode;
                    long zipNo = Long.parseLong(addressElement.getElementsByTagName("zipNo").item(0).getTextContent());
                    String rnAdres = addressElement.getElementsByTagName("rnAdres").item(0).getTextContent();
                    String lnmAdres = addressElement.getElementsByTagName("lnmAdres").item(0).getTextContent();
                    return new AddressVO(zipNo, rnAdres, lnmAdres);
                }
            }
            
            // 주소 정보가 없는 경우 null 반환
            return null;
            
        } catch (Exception e) {
        	log.error("An error occurred while fetching address data: {}", e.getMessage());
        	return null;
        }
    }    
    
}