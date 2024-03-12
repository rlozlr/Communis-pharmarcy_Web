package com.communis.www.service;

import java.util.List;

import com.communis.www.security.MemberVO;

public interface MemberService {

	int register(MemberVO mvo);

	boolean updateLastLogin(String authEmail);

	MemberVO detail(String email);

	List<MemberVO> getList();

	int modifyPwdEmpty(MemberVO mvo);

	int modify(MemberVO mvo);

	int remove(String email);

}

