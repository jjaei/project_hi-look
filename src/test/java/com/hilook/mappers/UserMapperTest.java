package com.hilook.mappers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hilook.beans.vo.UserVO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMapperTest {
	
	@Autowired
	private UserMapper mapper;
	
//	@Test
	public void testCreateUser() {
		UserVO user = new UserVO();
		user.setUser_id("테스트3");
		user.setUser_pw("123456");
		user.setUser_name("김테스트");
		user.setUser_email("abc@naver.com");
		
		log.info("-------- 회원가입 test ");
		
		mapper.createUser(user);
	}
	
	
//	@Test
	public void testDeleteUser() {
		String user_id = "테스트2";
		UserVO user = new UserVO();
		mapper.deleteUser(user);		
	}
	
//	@Test 
	public void testCheckId() { // 아이디 중복 검사
		String user_id = "테스트2";
		String user_id2 = "id123";
		mapper.checkId(user_id);
		mapper.checkId(user_id2);
		log.info("아이디 중복검사 테스트");
	}
	
	@Test
	public void userLogin() throws Exception {
		UserVO user = new UserVO();
		
		user.setUser_id("후째");
		user.setUser_pw("1234");
		
		mapper.userLogin(user);
		log.info("------------------로그인 여부" + mapper.userLogin(user));
	}
}
