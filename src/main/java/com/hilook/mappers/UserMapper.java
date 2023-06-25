package com.hilook.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.hilook.beans.vo.UserVO;

@Mapper
public interface UserMapper {
	
	public void createUser(UserVO user);
	public int checkId(String user_id);
	public UserVO userLogin(UserVO user);
	public void deleteUser(UserVO user);		
}