package com.communis.www.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.communis.www.domain.PagingVO;
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
	
<<<<<<< HEAD
	@GetMapping("/detail")
	public void detail() {}
=======
	@GetMapping("/digestionDetail")
	public void digestionDetail(Model m, PagingVO pgvo) {
	    ResponseEntity<String> responseEntity = prsv.callPillData(pgvo); // PagingVO 객체를 이용하여 API 호출
	    if (responseEntity.getStatusCode() == HttpStatus.OK) {
	        m.addAttribute("result", responseEntity.getBody()); // View로 데이터 전달
	    } else {
	        // 에러 처리
	    }
		
	}
>>>>>>> academy
}
