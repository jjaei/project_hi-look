package com.hilook.beans.vo;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

@Component
@Data
public class Criteria { // 게시글 검색의 기
	private int pageNum; // 현재 페이지
	private int amount; // 현재 페이지에 보여질 게시글의 
	private int limit;
	private int offset;
	
	public Criteria() {
		this(1,10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
		this.limit = pageNum * amount;
		this.offset = (pageNum - 1) * amount;
	}
	
	public void setParam() {
		this.limit = amount;
		this.offset = (pageNum - 1) * amount;
	}
	
	public String getListLink() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
			.queryParam("pageNum", this.pageNum)
			.queryParam("amount", this.getAmount());
				
			return builder.toUriString();
	}
}