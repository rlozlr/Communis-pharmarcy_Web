package com.communis.www.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.communis.www.domain.MenuDTO;
import com.communis.www.domain.PagingVO;
import com.communis.www.domain.PillImgVO;
import com.communis.www.domain.PillVO;
import com.communis.www.handler.PagingHandler;
import com.communis.www.handler.PillImgHandler;
import com.communis.www.service.MenuService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/menu/*")
public class MenuController {

	private final MenuService msv;
	private final PillImgHandler pih;
	
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
    
    @ResponseBody
    @PostMapping(value ="/insert", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String insertMenu(@RequestBody PillVO pvo) {
    	int result = msv.findByItem(pvo.getItemName(), pvo.getEntpName());
    	
    	if(result == 0) {
    		msv.insert(pvo);
    		return "success";
    	} else if (result > 0) {
    		return "duplicate";
    	} else {
    		return "-1";
    	}
    	
    }
    
    @ResponseBody
    @PostMapping("/insertSelected")
    public String insertSelectedPills(@RequestBody List<PillVO> selectedItems) {
        // 이미 등록된 약품들을 저장할 리스트
        List<PillVO> alreadyRegisteredItems = new ArrayList<>();
        List<PillVO> insertedItems = new ArrayList<>();
        
        // 선택된 약품 정보를 받아와서 데이터베이스에 저장
        for (PillVO pvo : selectedItems) {
            int result = msv.findByItem(pvo.getItemName(), pvo.getEntpName());
            if (result > 0) {
            	alreadyRegisteredItems.add(pvo);
            } else {
                msv.insert(pvo);
                insertedItems.add(pvo);
            }
        }

        if (alreadyRegisteredItems.isEmpty()) {
            return "success"; // 모든 약품이 삽입됨
        } else if (insertedItems.isEmpty()) {
            return "duplicate"; // 모든 약품이 이미 존재함
        } else {
            return "isContain"; // 일부 약품은 이미 존재함
        }
    }
    
	@GetMapping("/list")
	public void list (Model model, PagingVO pgvo) {

		log.info(">>> pgvo Type >>> {}", pgvo.getType());
		log.info(">>> pgvo Keyword >>> {}", pgvo.getKeyword());
		List<PillVO> list = msv.getList(pgvo);
		log.info(">>> list >>> {}", list);
		int totalCount = msv.totalCount(pgvo);
		log.info(">>> totalCount >>> {}", totalCount);
		PagingHandler ph = new PagingHandler(pgvo, totalCount);
		model.addAttribute("list", list);
		model.addAttribute("ph", ph);
	}
	
	@GetMapping("/remove")
	public String remove (PillVO pvo) {
		msv.delete(pvo);
		return "redirect:/menu/list";
	}
	
	@GetMapping({"/detail", "/modify"})
	public void detail (Model model, @RequestParam("pillId") long pillId) {
		model.addAttribute("mdto", msv.getDetail(pillId));
	}
	
	@PostMapping("/modify")
	public String modify(PillVO pvo, RedirectAttributes re, @RequestParam(name = "pillImgName", required = false) MultipartFile[] files) {
	    List<PillImgVO> flist = new ArrayList<>();
	    if(files != null && files.length > 0 && files[0].getSize() > 0) {
	        flist = pih.uploadFiles(files);
	    }
	    int isOk = msv.modify(new MenuDTO(pvo, flist));
	    re.addAttribute("pvo", pvo.getPillId());
	    return "redirect:/menu/detail";
	}
    
}
