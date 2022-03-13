package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) 
	{
		System.out.println("UserApiController: save 호출됨");
	
		user.setRole(RoleType.USER);
		int result = userService.회원가입(user);
		
		//DB에 INSERT 후 (200,1)RETURN
		return new ResponseDto<Integer>(HttpStatus.OK.value(),result);  //성공시 (200,1)
	}
	
}