package com.communis.www.service;

import org.springframework.stereotype.Service;

import com.communis.www.domain.PillVO;
import com.communis.www.repository.MenuDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

	private final MenuDAO mdao;

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
	
	
}
