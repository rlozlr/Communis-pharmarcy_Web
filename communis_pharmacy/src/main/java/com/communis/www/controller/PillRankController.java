package com.communis.www.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.communis.www.domain.MenuDTO;
import com.communis.www.domain.PagingVO;
import com.communis.www.domain.PillImgVO;
import com.communis.www.domain.PillVO;
import com.communis.www.handler.PagingHandler;
import com.communis.www.service.PillRankService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/pillRank/*")
public class PillRankController {
	
	private final PillRankService prsv;
	
	@GetMapping("/list")
	public void register() {}
	
	@GetMapping("/digestionList")
	public void digestionList(Model model, PagingVO pgvo) {
		pgvo.setQty(9);
		List<PillVO> digestionList = prsv.getDigestionList(pgvo);
		int totalCount = prsv.totalCount(pgvo);
		PagingHandler ph = new PagingHandler(pgvo, totalCount, 9);
		log.info(">>>>> ph >>>>> {}", ph);
		model.addAttribute("ph", ph);
		
	    // 각 PillVO와 해당 이미지 리스트를 모델에 추가
	    List<MenuDTO> mdtoList = new ArrayList<>();
	    for (PillVO pvo : digestionList) {
	        MenuDTO mdto = prsv.getPillImg(pvo.getPillId());
	        mdtoList.add(mdto);
	    }
	    model.addAttribute("mdtoList", mdtoList);
	}
	
	
}
