package com.hilook.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hilook.beans.vo.UserVO;
import com.hilook.mappers.UserMapper;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper mapper;
	
	@Override
	public void createUser(UserVO user) {
		mapper.createUser(user);
	}
	
	@Override
	public int checkId(String user_id) {
		return mapper.checkId(user_id);
	}
	
	@Override
	public UserVO userLogin(UserVO user) {
		return mapper.userLogin(user);
	}
	
	@Override
	public void deleteUser(UserVO user) {
		mapper.deleteUser(user);
	}
	
}