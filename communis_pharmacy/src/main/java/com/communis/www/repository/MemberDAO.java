package com.communis.www.repository;

import java.util.List;

import com.communis.www.security.AuthVO;
import com.communis.www.security.MemberVO;

public interface MemberDAO {

	int insert(MemberVO mvo);

	int insertAuthInit(String email);

	MemberVO selectEmail(String username);

	List<AuthVO> selectAuths(String username);

	int updateLastLoging(String authEmail);

	List<MemberVO> selectAllList();

	int modifyPwdEmpty(MemberVO mvo);

	int modify(MemberVO mvo);

	int removeAuth(String email);

	int remove(String email);

}
