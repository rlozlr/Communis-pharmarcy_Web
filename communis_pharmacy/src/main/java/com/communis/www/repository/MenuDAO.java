package com.communis.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.communis.www.domain.PagingVO;
import com.communis.www.domain.PillVO;

@Mapper
public interface MenuDAO {

	void insert(PillVO pvo);

	int findByItem(@Param("itemName") String itemName, @Param("entpName") String entpName);

	List<PillVO> getList(PagingVO pgvo);

	int totalCount(PagingVO pgvo);

}
