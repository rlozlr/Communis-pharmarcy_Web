package com.communis.www.service;

import java.util.List;

import com.communis.www.domain.PagingVO;
import com.communis.www.domain.PillVO;

public interface MenuService {

	void insert(PillVO pvo);

	int findByItem(String itemName, String entpName);

	List<PillVO> getList(PagingVO pgvo);

	int totalCount(PagingVO pgvo);

	int update(PillVO pvo);

	void delete(PillVO pvo);

}
