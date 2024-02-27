package com.communis.www.service;

import java.util.List;

import com.communis.www.domain.MenuDTO;
import com.communis.www.domain.PagingVO;
import com.communis.www.domain.PillVO;

public interface MenuService {

	int register(MenuDTO mdto);

	List<PillVO> getList(PagingVO pgvo);

	int totalCount(PagingVO pgvo);

	int update(PillVO pvo);

	void delete(PillVO pvo);

	MenuDTO getDetail(long pillId);

	int modify(MenuDTO mdto);

	int removeFile(String pill_img_id);


}
