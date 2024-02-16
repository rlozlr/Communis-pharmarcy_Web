package com.communis.www.repository;

import org.apache.ibatis.annotations.Mapper;

import com.communis.www.domain.PillVO;

@Mapper
public interface MenuDAO {

	void insert(PillVO pvo);

}
