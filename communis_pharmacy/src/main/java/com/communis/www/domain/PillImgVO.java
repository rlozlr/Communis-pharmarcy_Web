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
public class PillImgVO {

	private String pillImgId;
	private String pillImgSaveDir;
	private String pillImgName;
	private int pillImgType;
	private long pillImgSize;
	private String pillImgReg;
	private String pillImgMod;
	private String pillImgStatus;
	private long pillId;
	
}
