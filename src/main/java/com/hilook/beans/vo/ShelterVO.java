package com.hilook.beans.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ShelterVO {
	private String careNm;
	private String careAddr;
	private String careTel;
	private String saveTrgtAnimal;  // 구조대상 동물
}
