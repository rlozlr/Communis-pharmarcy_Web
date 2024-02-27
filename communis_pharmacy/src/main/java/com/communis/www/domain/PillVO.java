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

	private long pillId;
	private String itemName;	// 제품명 (공공)
	private String entpName;	// 업체명	(공공)
	private String efcyQesitm;	//효능 (공공)
	private String thumbnail;	// 이미지 (네이버)로 시도했으나, 너무 느려서 파일첨부로 데이터 넣기
	private int pillPrice;
	private long pillSell;	// 판매량
	private long pillDibs;	// 찜
	private long pillStock;	// 재고
	private String pillReg;
	private String pillMod;
	private String pillStatus;
	
	public PillVO(String itemName, String entpName, String efcyQesitm) {
		this.itemName = itemName;
		this.entpName = entpName;
		this.efcyQesitm = efcyQesitm;
	}
	
	public PillVO(String itemName, String entpName, String efcyQesitm, String thumbnail) {
		this.itemName = itemName;
		this.entpName = entpName;
		this.efcyQesitm = efcyQesitm;
		this.thumbnail = thumbnail;
	}
	
}
