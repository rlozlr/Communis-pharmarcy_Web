package com.communis.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.communis.www.domain.PillVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/menu/*")
public class MenuController {

	@Autowired
    private PillInfoApiController pillInfoApiController;

	@GetMapping("/register")
	public void register () {}
	
    @GetMapping("/search")
    public String searchPillByName(@RequestParam String pillName, Model model) {
        log.info(">>> PillName >>> {}",pillName);
    	// 약품명으로 약품 정보 검색
        PillVO pillInfo = pillInfoApiController.fetchPillData(pillName);
        
        if (pillInfo != null) {
            model.addAttribute("pillInfo", pillInfo);
        }
        return "redirect:/menu/register";
    }
    
}
