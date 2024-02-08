package com.communis.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PillVO {

	private String itemName;	// 제품명 (공공)
	private String entpName;	// 업체명	(공공)
	private String efcyQesitm;	//효능 (공공)
	private String thumbnail;	// 이미지 (네이버)
}
