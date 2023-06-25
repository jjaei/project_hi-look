package com.hilook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hilook.beans.vo.UserVO;
import com.hilook.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/join")
	public void loginGET() {
		
		log.info("회원가입 페이지 진입");
		
	}
	
//	로그인 페이지 이동
	@GetMapping("/login")
	public void joinGET() {
			
		log.info("로그인 페이지 진입");		
		
	}
	
//	회원가입
	@PostMapping("/join")
	public String createUser(UserVO user) {
		userService.createUser(user);
		return "redirect:/main";
	}
	
//	아이디 중복체크
	@PostMapping("/userIdCheck")
	@ResponseBody
	public String userIdCheck(String user_id) throws Exception{
//		log.info("아이디 중복체크 중");
		
		int result = userService.checkId(user_id);
		
//		log.info("결과 = " + result);
		
		if(result != 0) {
			return "fail";  // 중복 아이디 존재
		} else {
			return "success";  // 중복 아이디 없음
		}
	}

	@PostMapping("/login")
	public String login(HttpServletRequest request, UserVO user, RedirectAttributes rttr) throws Exception {
		
		HttpSession session = request.getSession();
		UserVO login_info = userService.userLogin(user);
//		로그인 요청을 받으면 로그인 쿼리 결과값이 담긴 UserVO 객체를 반환 받아 login_info에 저장 됨.
		
		if(login_info == null) { // 아이디, 비밀번호가 일치하지 않는 경우
			int result = 0;
			rttr.addFlashAttribute("result", result);
			return "redirect:/user/login";
		} else { 		
		session.setAttribute("user", login_info); // 아이디, 비밀번호가 일치할 경우 로그인 성공	
		return "redirect:/main";
		
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) throws Exception {
		session.invalidate();
		
		return "redirect:/main";
	}
	
//	회원 탈퇴
	@PostMapping("/withdraw")
	public String withdraw(HttpSession session, UserVO user, RedirectAttributes rttr) {
		UserVO user_info = (UserVO)session.getAttribute("user");
		String user_pw = user.getUser_pw();
		String withdraw_pw = user_info.getUser_pw();
		
		if(withdraw_pw.equals(user_pw)) {
			userService.deleteUser(user_info);
			rttr.addFlashAttribute("result","success");
			session.invalidate();
			return "redirect:/main";
		} else {
			rttr.addFlashAttribute("result","fail");
			return "redirect:/user/withdraw";
		}
	}
	
	@GetMapping("/withdraw")
	public void withdrawForm() {
	    
	}	
}