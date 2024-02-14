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
public class PillNaverEncycVO {

	private String title;
	private String link;
	private String description;
	private String thumbnail;
}
