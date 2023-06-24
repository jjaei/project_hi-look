package com.hilook.beans.vo;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Component
@Data
@Slf4j
public class PageDTO {
	private int startPage;
	private int endPage;
	private int realEnd;
	private boolean prev, next;
	
	private int total;
	private Criteria cri;
	
	public PageDTO() {}
	public PageDTO(Criteria cri, int total) {
		this.cri = cri;
		this.total = total;
		
		endPage = (int)(Math.ceil(cri.getPageNum() / 10.0)) * 10;
		startPage = this.endPage - 9;
		
		realEnd = (int)(Math.ceil((total * 1.0) / cri.getAmount()));
		
		log.info("endPage" + endPage);
		log.info("realEnd" + realEnd);
	
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}
}
