package com.communis.www.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	//private final AddressAPIController addressAPI;
	
	@GetMapping("/orderCheck")
	public String buyNow(@RequestParam("pillId") long pillId, Model model) {
		
		PillVO pvo = osv.getPillInfo(pillId);
		model.addAttribute("pvo", pvo);
		
		return "/buy/orderCheck";
	}
	
}
