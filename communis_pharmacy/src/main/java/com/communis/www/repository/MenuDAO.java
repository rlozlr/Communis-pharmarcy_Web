package com.communis.www.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.communis.www.domain.PillVO;

@Mapper
public interface MenuDAO {

	void insert(PillVO pvo);

	int findByItem(@Param("itemName") String itemName, @Param("entpName") String entpName);

}
