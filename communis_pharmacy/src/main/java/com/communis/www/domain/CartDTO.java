package com.communis.www.domain;

import com.communis.www.security.MemberVO;

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
public class CartDTO {

	private CartVO cvo;
	private PillVO pvo;
	private MemberVO mvo;
}
