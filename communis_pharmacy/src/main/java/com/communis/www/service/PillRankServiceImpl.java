package com.communis.www.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.communis.www.controller.PillAPIController;
import com.communis.www.domain.MenuDTO;
import com.communis.www.domain.PagingVO;
import com.communis.www.domain.PillImgVO;
import com.communis.www.domain.PillVO;
import com.communis.www.repository.MenuDAO;
import com.communis.www.repository.PillImgDAO;
import com.communis.www.repository.PillRankDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PillRankServiceImpl implements PillRankService {

	private final PillRankDAO prdao;
	private final MenuDAO mdao;
	private final PillImgDAO pidao;

	@Override
	public List<PillVO> getDigestionList(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return prdao.getDigestionList(pgvo);
	}

	@Override
	public int totalCount(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return prdao.totalCount(pgvo);
	}

	@Override
	public MenuDTO getPillImg(long pillId) {
		PillVO pvo = mdao.getDetail(pillId);
		List<PillImgVO> pillImgList = pidao.getImgList(pillId);
		MenuDTO mdto = new MenuDTO(pvo, pillImgList);
		return mdto;
	}

}
