package com.hilook.beans.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AnimalVO {	
	private String desertionNo; // 유기번호
	private String filename; // 썸네일
	private String happenDt; // 접수일
	private String happenPlace; // 발견장소
	private String kindCd; // 품종
	private String colorCd; // 색상
	private String age; // 나이
	private String weight; // 체중
	private String noticeNo; // 공고 번호
	private String noticeSdt; // 공고 시작일
	private String noticeEdt; // 공고 종료일
	private String popfile; // 이미지
	private String processState; // 상태
	private String sexCd; // 성별
	private String neuterYn; // 중성화 여부
	private String specialMark; // 특징
	private String careNm; // 보호소 이름
	private String careTel; // 보호소 전화번호
	private String careAddr; // 보호장소
	private String detailUrl;
}


