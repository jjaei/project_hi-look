package com.hilook.beans.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class UserVO {
	private String user_id;
	private String user_pw;
	private String user_name;
	private String user_email;
}
