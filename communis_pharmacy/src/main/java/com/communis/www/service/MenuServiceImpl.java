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
		mdao.insert(pvo);
	}
	
	
}
