package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

	// http://localhost:8000/blog/ 를 입력하면
	// WEB-INF/views/index.jsp 파일을 찾아가게 된다.
	@GetMapping({ "", "/" })
	public String index() {
		return "index";
	}

}
