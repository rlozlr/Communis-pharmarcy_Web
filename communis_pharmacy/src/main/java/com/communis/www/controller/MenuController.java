package com.communis.www.controller;

import java.util.ArrayList;
import java.util.List;

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
    
	@ResponseBody
	@GetMapping(value="/{itemName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PillVO>> searchPill(@PathVariable("itemName") String itemName) {
	    List<PillVO> pillInfoList = pillInfoApiController.fetchPillData(itemName);
	    if (pillInfoList != null) {
	        return ResponseEntity.ok(pillInfoList);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@PostMapping("/register")
	public String register(PillVO pvo, @RequestParam(name="files", required = false) MultipartFile[] files) {
		List<PillImgVO> flist = null;
		if(files[0].getSize() > 0) {
			flist = pih.uploadFiles(files);
		}
	    int isOk = msv.register(new MenuDTO(pvo, flist));
	    return "redirect:/menu/list";
	}
    
	@GetMapping("/list")
	public void list (Model model, PagingVO pgvo) {
		List<PillVO> list = msv.getList(pgvo);
		int totalCount = msv.totalCount(pgvo);
		PagingHandler ph = new PagingHandler(pgvo, totalCount);
		model.addAttribute("list", list);
		model.addAttribute("ph", ph);
	}
	
	@PutMapping(value = "/update", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> edit(@RequestBody PillVO pvo) {
		int isOk = msv.update(pvo);
		return isOk > 0 ?
				new ResponseEntity<String>("1", HttpStatus.OK) :
					new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
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
	
	@DeleteMapping(value="/menu/{pill_img_id}", produces= MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> removeFile(@PathVariable("pill_img_id") String pill_img_id) {
		int isOk = msv.removeFile(pill_img_id);
		return isOk > 0 ?
				new ResponseEntity<String>("1", HttpStatus.OK) :
					new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
				
	}
    
}
