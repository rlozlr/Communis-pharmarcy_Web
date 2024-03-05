package com.communis.www.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@GetMapping("/digestionDetail")
	public void detail() {}
	
	
	
}
