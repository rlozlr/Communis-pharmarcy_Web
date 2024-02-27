package com.communis.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.communis.www.domain.PagingVO;
import com.communis.www.domain.PillVO;

@Mapper
public interface MenuDAO {

	List<PillVO> getList(PagingVO pgvo);

	int insert(PillVO pvo);

	long selectOnePillId();
	
	int updateImg(@Param("thumbnail") String thumbnail, @Param("pillId") long pillId);

	int totalCount(PagingVO pgvo);

	int update(PillVO pvo);

	void delete(PillVO pvo);

	PillVO getDetail(long pillId);


}
