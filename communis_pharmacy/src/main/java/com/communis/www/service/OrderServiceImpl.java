package com.communis.www.service;

import org.springframework.stereotype.Service;

import com.communis.www.domain.PillVO;
import com.communis.www.repository.MenuDAO;
import com.communis.www.repository.OrderDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
	
	private final OrderDAO odao;
	private final MenuDAO mdao;
	
	@Override
	public PillVO getPillInfo(long pillId) {
		// TODO Auto-generated method stub
		return mdao.getDetail(pillId);
	}

}
