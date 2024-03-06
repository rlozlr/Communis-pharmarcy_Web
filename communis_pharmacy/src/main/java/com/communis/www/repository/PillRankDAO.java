package com.communis.www.repository;

import java.util.List;

import com.communis.www.domain.PagingVO;
import com.communis.www.domain.PillVO;

public interface PillRankDAO {

	List<PillVO> getDigestionList(PagingVO pgvo);

	int totalCount(PagingVO pgvo);

}
