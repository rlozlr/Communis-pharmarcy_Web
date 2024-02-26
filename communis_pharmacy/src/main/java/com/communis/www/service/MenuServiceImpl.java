package com.communis.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.communis.www.domain.MenuDTO;
import com.communis.www.domain.PagingVO;
import com.communis.www.domain.PillImgVO;
import com.communis.www.domain.PillVO;
import com.communis.www.repository.MenuDAO;
import com.communis.www.repository.PillImgDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

	private final MenuDAO mdao;
	private final PillImgDAO pidao;
	
	@Override
	public void insert(PillVO pvo) {
		
		// DB에 pill 정보 존재 여부 확인
		int result = mdao.findByItem(pvo.getItemName(), pvo.getEntpName());
		
        // 이미 존재하는 경우 중복 저장을 방지하기 위해 처리합니다.
        if (result > 0) {
        	throw new IllegalStateException("이미 존재하는 약품입니다.");
        }
		
		mdao.insert(pvo);
	}

	@Override
	public int findByItem(String itemName, String entpName) {
		return mdao.findByItem(itemName, entpName);
	}

	@Override
	public List<PillVO> getList(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return mdao.getList(pgvo);
	}

	@Override
	public int totalCount(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return mdao.totalCount(pgvo);
	}

	@Override
	public int update(PillVO pvo) {
		// TODO Auto-generated method stub
		return mdao.update(pvo);
	}

	@Override
	public void delete(PillVO pvo) {
		pidao.deleteAll(pvo);
		mdao.delete(pvo);
	}

	@Transactional
	@Override
	public MenuDTO getDetail(long pillId) {
		// TODO Auto-generated method stub
		PillVO pvo = mdao.getDetail(pillId);
		List<PillImgVO> pillImgList = pidao.getImgList(pillId);
		MenuDTO mdto = new MenuDTO(pvo, pillImgList);
		return mdto;
	}
	
	@Transactional
	@Override
	public int modify(MenuDTO mdto) {
		int isOk = mdao.update(mdto.getPvo());
		if(mdto.getPillImgList() == null) {
			return isOk;
		}
		
		if(isOk > 0 && mdto.getPillImgList().size() > 0) {
			for(PillImgVO pivo : mdto.getPillImgList()) {
				pivo.setPillId(mdto.getPvo().getPillId());
				isOk += pidao.updateFile(pivo);
			}
		}
		return isOk;
	}

	@Override
	public int removeFile(String pill_img_id) {
		// TODO Auto-generated method stub
		return pidao.removeFile(pill_img_id);
	}
	
}
