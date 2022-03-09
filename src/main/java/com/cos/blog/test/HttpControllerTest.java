package com.cos.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpControllerTest {

		private static final String TAG = "HttpControllerTest:";
	
		@GetMapping("http/lombok")
		//http://localhost:8000/http/lombok
		public String lombokTest() {
			Member m = Member.builder().password("1234").username("ssar").build(); 
			
			System.out.println(TAG + "getter:"+m.getId());
			m.setId(5000);
			System.out.println(TAG + "setter:"+m.getId());
			
			return "lombok test 완료";
		}
		
	//인터넷 브라우저 요청은 무조건 get요청밖에 할 수 없다(put,delete등은 할 수 없다)
	//http://localhost:8000/http/get 
	@GetMapping("http/get")
	public String getTest(Member m) {

		return "get요청:" +m.getId() + "," +m.getUsername() +"," + m.getEmail();
	}

	
}
