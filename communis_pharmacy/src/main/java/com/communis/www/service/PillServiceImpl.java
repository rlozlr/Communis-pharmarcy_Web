package com.communis.www.service;

import org.springframework.stereotype.Service;

import com.communis.www.domain.PillVO;
import com.communis.www.repository.PillDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PillServiceImpl implements PillService {
	
	private final PillDAO pdao;
	
	@Override
	public void insertPill(PillVO pillInfo) {
		// TODO Auto-generated method stub
		pdao.insert(pillInfo);
	}

	
}
