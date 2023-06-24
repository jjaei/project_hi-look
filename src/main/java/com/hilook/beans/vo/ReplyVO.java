package com.hilook.beans.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ReplyVO {
	private Long rno;
	private Long bno;
	private String reply;
	private String replier;
	private String replydate;
	private String updatedate;
}
