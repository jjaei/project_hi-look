package com.hilook.services;

import org.springframework.stereotype.Service;

import com.hilook.beans.vo.UserVO;

@Service
public interface UserService {

	public void createUser(UserVO user);
	public int checkId(String user_id);
	public UserVO userLogin(UserVO user);
	public void deleteUser(UserVO user);
}
