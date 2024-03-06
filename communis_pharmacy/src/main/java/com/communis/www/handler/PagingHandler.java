package com.communis.www.handler;

import com.communis.www.domain.PagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class PagingHandler {
	
	private int startPage;
	private int endPage;
	private int totalCount;
	private int qty;
	
	private PagingVO pgvo;
	private Boolean prev, next;
	
	public PagingHandler(PagingVO pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		this.endPage = (int) Math.ceil(pgvo.getPageNo() / (double) pgvo.getQty()) * pgvo.getQty();
		this.startPage = endPage - 9;
		
		int realEndPage = (int)(Math.ceil(totalCount / (double) pgvo.getQty()));
		if(realEndPage < endPage) {
			this.endPage = realEndPage;
		}
		
		this.prev = startPage > 1;
		this.next = this.endPage < realEndPage;
	}

	public PagingHandler(PagingVO pgvo, int totalCount, int qty) {
	    this.pgvo = pgvo;
	    this.totalCount = totalCount;
	    this.qty = qty; // qty 값을 설정합니다.
	    this.endPage = (int) Math.ceil(pgvo.getPageNo() / (double) this.qty) * this.qty; // this.qty를 사용하여 계산합니다.
	    this.startPage = endPage - (this.qty - 1); // qty 값에 따라서 동적으로 설정합니다.
	    
	    int realEndPage = (int)(Math.ceil(totalCount / (double) this.qty));
	    if(realEndPage < endPage) {
	        this.endPage = realEndPage;
	    }
	    
	    this.prev = startPage > 1;
	    this.next = this.endPage < realEndPage;
	}
}
