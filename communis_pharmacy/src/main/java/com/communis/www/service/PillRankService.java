package com.communis.www.service;


import java.util.List;

import com.communis.www.domain.MenuDTO;
import com.communis.www.domain.PagingVO;
import com.communis.www.domain.PillImgVO;
import com.communis.www.domain.PillVO;

public interface PillRankService {

	List<PillVO> getDigestionList(PagingVO pgvo);

	int totalCount(PagingVO pgvo);

	MenuDTO getPillImg(long pillId);
}
