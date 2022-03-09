package com.cos.blog.test;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class tempControllerTest {

	// http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome()");
		
		//파일리턴 기본경로: src/main/resources/static에 있는 아래의 파일을 리턴해라~
		// return "/home.html" 이라고 하면 에러가 난다.
		
		return "/home.html";  //풀경로: src/main/resources/static/home.html
		
	}
}
