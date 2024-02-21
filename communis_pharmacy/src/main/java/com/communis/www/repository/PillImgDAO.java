package com.communis.www.repository;

import java.util.List;

import com.communis.www.domain.PillImgVO;

public interface PillImgDAO {

	List<PillImgVO> getImgList(long pillId);

}
