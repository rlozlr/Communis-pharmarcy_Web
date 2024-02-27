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
	
	@Transactional
	@Override
	public int register(MenuDTO mdto) {
		// TODO Auto-generated method stub
		int isOk = mdao.insert(mdto.getPvo());
		
		if (mdto.getPillImgList() == null || mdto.getPillImgList().isEmpty()) {
			log.info(">>>>>>>>>>>>>here >>>>>>>>>>>");
	        return isOk; // 등록 실패하거나 첨부 파일이 없는 경우 바로 리턴
	    }
		if(isOk > 0 && mdto.getPillImgList().size() > 0) {
			log.info(">>>>>>>>>>>>>hey >>>>>>>>>>>");
			long pillId = mdao.selectOnePillId();
			for(PillImgVO pivo : mdto.getPillImgList()) {
				pivo.setPillId(pillId);
				isOk += pidao.insertFile(pivo);
			}
		}
		return isOk;
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
