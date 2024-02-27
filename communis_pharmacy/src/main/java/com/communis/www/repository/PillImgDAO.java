package com.communis.www.repository;

import java.util.List;

import com.communis.www.domain.PillImgVO;
import com.communis.www.domain.PillVO;

public interface PillImgDAO {

	List<PillImgVO> getImgList(long pillId);

	int insertFile(PillImgVO pivo);

	int updateFile(PillImgVO pivo);

	int removeFile(String pill_img_id);

	void deleteAll(PillVO pvo);


}
