package com.communis.www.controller;



import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.communis.www.domain.AddressVO;
import com.communis.www.domain.PillVO;
import com.communis.www.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/orderController/*")
public class OrderController {
	
	private final OrderService osv;
	private final AddressAPIController addressAPI;
	
	@GetMapping("/orderCheck")
	public String buyNow(@RequestParam("pillId") long pillId, Model model) {
		
		PillVO pvo = osv.getPillInfo(pillId);
		model.addAttribute("pvo", pvo);
		
		return "/buy/orderCheck";
	}
	
	@ResponseBody
	@GetMapping(value="/{roadName}", produces = MediaType.TEXT_XML_VALUE)
    public ResponseEntity<AddressVO> getAddress(@PathVariable ("roadName") String roadName) {
		log.info(">>>>> roadName >>>> {}",roadName);
    	AddressVO addrVO = addressAPI.fetchAddressDataFromPublicAPI(roadName);
    	log.info(">>>>> addrVO >>>> {}",addrVO);
        if (addrVO != null) {
            return ResponseEntity.ok(addrVO); // 주소 정보가 있을 경우 200 상태 코드와 함께 주소 정보 반환
        } else {
            return ResponseEntity.notFound().build(); // 주소 정보가 없을 경우 404 상태 코드 반환
        }
    }
}
