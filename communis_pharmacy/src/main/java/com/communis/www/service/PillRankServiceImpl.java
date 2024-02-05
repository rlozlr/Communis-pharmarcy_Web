package com.communis.www.service;

import org.springframework.stereotype.Service;

import com.communis.www.repository.PillRankDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PillRankServiceImpl implements PillRankService {

	private final PillRankDAO prdao;
}
