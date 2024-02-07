package com.communis.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PillRank {

	private long pill_rank_id;
	private String pill_rank_type;
	private String pill_name;
	private long pill_dibs;
	private long pill_sell;
}
