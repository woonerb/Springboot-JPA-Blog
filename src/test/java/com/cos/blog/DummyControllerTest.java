package com.cos.blog;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {

	//{id} 주소로 파라미터를 전달받을 수 있음
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("dummy/user/{id}")
	public User detail(@PathVariable int id) {
		
		// user/4를 찾을 때 db에서 못 찾아오면 user가 null이 될수도
		// 있는데, 그러면 null이 리턴되서 프로그램에 문제가 생길수 있을걸???
		// 그래서 Optional로 너의 User객체로 감싸서 가져올테니
		// null인지 아닌지 판단해서 get해야한다!!!
		User user =	userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다.id:"+id);
			}
		});
		return user;
	}
	
	//DummyControllerTest가 메모리에 뜰때, 
	//AutoWired 어노테이션이 붙어있는 변수들도 함께 메모리에 띄어준다
	@Autowired 
	private UserRepository userRepository;
	
	@PostMapping("/dummy/join")
	public String join(User user) {
		
	user.setRole(RoleType.User); //default는 user이다
		
	userRepository.save(user);	
	return "회원가입이 완료되었습니다.";
		
	}
}
