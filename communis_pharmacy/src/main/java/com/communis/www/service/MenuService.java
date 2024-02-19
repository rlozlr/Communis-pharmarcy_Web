package com.communis.www.service;

import com.communis.www.domain.PillVO;

public interface MenuService {

	void insert(PillVO pvo);

	int findByItem(String itemName, String entpName);

}
