package com.communis.www.domain;

import java.util.List;

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
public class PillNaverResultVO {
	
	private String lastBuildDate;
	private int total;
	private int start;
	private List<PillVO> items;
}
