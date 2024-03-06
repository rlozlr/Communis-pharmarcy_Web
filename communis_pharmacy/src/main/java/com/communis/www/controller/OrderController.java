package com.communis.www.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.communis.www.domain.MenuDTO;
import com.communis.www.domain.OrderRequest;
import com.communis.www.domain.PillVO;
import com.communis.www.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/orderCheck/*")
public class OrderController {
	
	private final OrderService osv;

	public String buyNow(@RequestParam("pillId") String pillId,
            @RequestParam("itemName") String itemName,
            @RequestParam("entpName") String entpName,
            @RequestParam("pillPrice") long pillPrice,
            HttpSession session, Model model) {
		
		// 주문 정보를 생성
		PillVO pillVO = new PillVO();
		pillVO.setPillId(pillId);
		pillVO.setItemName(itemName);
		pillVO.setEntpName(entpName);
		pillVO.setPillPrice(pillPrice);

		// 세션에 주문 정보를 저장
		session.setAttribute("orderRe", pillVO);
		
		// 주문 확인 페이지로 이동
		return "/buy/buyNow";
		}
}
