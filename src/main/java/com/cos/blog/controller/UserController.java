package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {


	//http://localhost:8000/blog/user/joinForm 가 오면
	//  -->
	// WEB-INF/views/user/joinForm.jsp  로보내준다
	@GetMapping("/user/joinForm")
	public String joinForm() {
		
		return "user/joinForm";
	}
	
	
	//http://localhost:8000/blog/user/loginForm 가 오면
	//  -->
	// WEB-INF/views/user/loginForm.jsp  로보내준다
	@GetMapping("/user/loginForm")
	public String loginForm() {
		
		return "user/loginForm";
	}
	
}
