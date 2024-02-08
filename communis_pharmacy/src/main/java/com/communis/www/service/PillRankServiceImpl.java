package com.communis.www.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.communis.www.controller.PillInfoApiController;
import com.communis.www.domain.PagingVO;
import com.communis.www.domain.PillVO;
import com.communis.www.repository.PillRankDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PillRankServiceImpl implements PillRankService {

	private final PillRankDAO prdao;



}
