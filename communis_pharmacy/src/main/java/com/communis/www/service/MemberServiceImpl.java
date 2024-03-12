package com.communis.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.communis.www.repository.MemberDAO;
import com.communis.www.security.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
	
	private final MemberDAO mdao;

	@Transactional
	@Override
	public int register(MemberVO mvo) {
		int isOk = mdao.insert(mvo);
		return mdao.insertAuthInit(mvo.getEmail());
	}

	@Override
	public boolean updateLastLogin(String authEmail) {
		// TODO Auto-generated method stub
		log.info(">>> authEmail >>> {}",authEmail);
		return mdao.updateLastLoging(authEmail) > 0 ? true : false;
	}

	@Override
	public MemberVO detail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public List<MemberVO> getList() {
		List<MemberVO> list = mdao.selectAllList();
		for(MemberVO mvo : list) {
			mvo.setAuthList(mdao.selectAuths(mvo.getEmail()));
		}
		return list;
	}

	@Override
	public int modifyPwdEmpty(MemberVO mvo) {
		// TODO Auto-generated method stub
		return mdao.modifyPwdEmpty(mvo);
	}

	@Override
	public int modify(MemberVO mvo) {
		// TODO Auto-generated method stub
		return mdao.modify(mvo);
	}
	
	@Transactional
	@Override
	public int remove(String email) {
		int isOk= mdao.removeAuth(email);
		return mdao.remove(email);
	}

}

