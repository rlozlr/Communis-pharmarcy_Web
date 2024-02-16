package com.communis.www.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.communis.www.domain.PillVO;
import com.communis.www.service.MenuService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/menu/*")
public class MenuController {

	private final MenuService msv;
	
	@Autowired
    private PillInfoApiController pillInfoApiController;

	@GetMapping("/register")
	public void register () {}
	
    @PostMapping("/register")
    public void searchPill(@RequestParam String pillName, Model model) {
    	// 약품명으로 약품 정보 검색
    	List<PillVO> pillInfoList = pillInfoApiController.fetchPillData(pillName);
        model.addAttribute("pillInfoList", pillInfoList);
    }
    
    @PostMapping("/insert")
    public String insertMenu(@RequestParam String itemName, @RequestParam String entpName,
    		@RequestParam String efcyQesitm, @RequestParam String thumbnail) {
        PillVO pvo = new PillVO(itemName, entpName, efcyQesitm, thumbnail);
        msv.insert(pvo);
        
        return "redirect:/menu/register";
    }
    
    @PostMapping("/insertSelected")
    public String insertSelected(HttpServletRequest request) {
        // 선택된 약품들의 이름을 받아옵니다.
        String[] selectedItems = request.getParameterValues("selectedItems");
        log.info("체크된거 >>>>>{}", selectedItems);
        // 선택된 약품이 존재할 경우에만 처리합니다.
        if (selectedItems != null) {
            for (String itemName : selectedItems) {
                // 해당 약품이 선택되었는지 확인합니다.
                String isSelected = request.getParameter("selectedItems_" + itemName);
                if ("on".equals(isSelected)) {
                    // 선택된 약품에 대한 정보를 추출합니다.
                    String entpName = request.getParameter("entpName_" + itemName);
                    String efcyQesitm = request.getParameter("efcyQesitm_" + itemName);
                    String thumbnail = request.getParameter("thumbnail_" + itemName);
                    
                    PillVO pvo = new PillVO(itemName, entpName, efcyQesitm, thumbnail);
                    msv.insert(pvo);
                }
            }
        }

        return "redirect:/menu/register";
    }
}
