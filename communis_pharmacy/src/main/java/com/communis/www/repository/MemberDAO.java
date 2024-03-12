package com.communis.www.repository;

import java.util.List;

import com.communis.www.security.AuthVO;
import com.communis.www.security.MemberVO;

public interface MemberDAO {

	int insert(MemberVO mvo);

	int insertAuthInit(String email);

	MemberVO selectEmail(String username);

	List<AuthVO> selectAuths(String username);

	int updateLastLogin(String authEmail);

}
