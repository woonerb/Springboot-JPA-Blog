package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//html을 리턴해주는 컨트롤러 : Controller
//data를 리턴해주는 컨트롤러 : RestController
@RestController
public class DummyControllerTest {
	@Autowired                              //의존성 주입(DI)  //DummyControllerTest가 메모리에 뜰때, UserRepository 얘도 메모리에 뛰우게 한다
	private UserRepository userRepository;  //user테이블 접근을 위한 클래스(DAO)!!

	
	/*
	 *   insertUser FOR 회원가입
	 *   // http://localhost:8000/blog/dummy/join (INSERT이므로 POST!!)   */                                      
	@PostMapping("/dummy/insertUser") //insert를 위해서 post를 쓴다!
	public String insertUser(User user) {
		// 굳이 key = value가 아닌 객체로도 스프링이 받아 줄 수 있다.
		// 밑에는 key, value로 하나씩 파싱받아서 스프링이 넣어주는 방식은 주석 쳐둠!! 참고만!!
		
			System.out.println("id: "+user.getId());
			System.out.println("username: "+user.getUsername());
			System.out.println("password: "+user.getPassword());
			System.out.println("email: "+user.getEmail());
			System.out.println("role :"+user.getRole());
			System.out.println("createDate: "+user.getCreateDate());
			
			user.setRole(RoleType.USER);  // NULL 이라는 값이 있으므로, default 값인 USER가 아닌 NULL로 INSERT 하는 문제 발생! 
			                              // 이러한 문제 해결하기 위해서 @DynamicInsert 라는 어노테이션을 쓸수도 있지만, 그냥 값을 박자!!  
										  // 사용가능한 값-> RoleType.USER, RoleType.ADMIN
			
			userRepository.save(user);    // DB에 Insert
		return "회원가입이 완료되었습니다.";	
	}
	
	// 이때 http의 body에 username, password, email 데이터를 담아서 요청을 보내본다
	/* key = value로 넘겨주면 스프링이 알아서 파싱해서 함수에 세팅해준다
	 * 	
	// insertUser FOR 회원가입
	// http://localhost:8000/blog/dummy/join (요청)
	public String join(String username, String password, String email) {
		
		System.out.println("username:"+username);
		System.out.println("password:"+password);
		System.out.println("email:"+email);
		
		return "회원가입이 완료되었습니다.";	
	}
	*/	
	
	
	
	/*
	 *  selectUser for 특정회원조회                                                
	 *  //http://localhost:8000/blog/dummy/selectUser/{id}   */
	@GetMapping("/dummy/selectUser/{id}")
	public User selectUser(@PathVariable int id) {
		
		// User user = userRepository.findById(id); // 해당 함수는 Optional로 리턴되므로 에러가 발생한다!!
												    // 왜냐하면 id가 4번인 것을 db에 찾으러 갔는데, 없으면 null이 리턴될 수 있는데
												    // 그러면 문제가 있을 수도 있으므로 Optional로 user객체를 감싸서 리턴받는데, User에 담으려 하기에 에러발생!

		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() 
		{
			//Supplier는 인터페이스이므로 구현을 해줘야 한다...
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id: "+id); 	// 에러 익셉션을 발생시켜준다!!
			}
		});
		
		// 
		// 요청을 웹브라우저에서 했는데, 
		// ResetControoler이므로 html파일이 아닌 데이터인 자바 객체를 리턴하므로
		// 웹브라우저가 이해할 수 있도록 json으로 변환해서 리턴해야한다.
		// 다행히 스프링부트는 MessageConverter가 작동해서 Jackson 라이브러리를 통해 자바오브젝트 ->json해준다!
		
		return user;
	}
	
	
	/*
	 * selectUserList for 전체회원 조회
	 * http://localhost:8000/blog/dummy/selectUserList  
	 */
	@GetMapping("/dummy/selectUserList")
	public List<User> selectUserList() {
		
		return userRepository.findAll();		
	}
	
	/*
	 * selectUserPageList  for 회원 정보를 페이징처리해서  1페이지에 2건의 데이터를 리턴
	 * http://localhost:8000/blog/dummy/selectUserPageList        //default는 1번째페이지  
	 * http://localhost:8000/blog/dummy/selectUserPageList?page=0 //1번째 페이지  
	 * http://localhost:8000/blog/dummy/selectUserPageList?page=2 //3번째 페이지 
	 */
	@GetMapping("/dummy/selectUserPageList")
	public List<User> pageList(@PageableDefault(size=2,sort="id",direction=Sort.Direction.DESC) Pageable pageable) //크기는 2개, id를 기준으로 내림차순 
	{	
		List<User> users = userRepository.findAll(pageable).getContent(); //user 테이블에서 findAll해오면, pageable객체에 이상한 정보가 많은데 content부분만 가져온다!!		
		return users;
	}
	
	
	
	/*
	 * updateUser for 회원정보수정(password,email)
	 * http://localhost:8000/blog/dummy/updateUser/{id}
	 * 
	 */
	@Transactional // 해당 함수가 종료시에 트랜잭션이 끝난것으로 간주하며, 트랜잭션 종료시 자동으로 commit되게 한다.
	@PutMapping("/dummy/updateUser/{id}")
	public String updateUser(@PathVariable int id, @RequestBody User requestUser) // json으로 들어와도 @RequestBody 있으면 MessageConverter가 jackson써서 json->자바 오브젝트로 바꿔줌 
	{
		
		System.out.println("id:"+id);
		System.out.println("password:"+requestUser.getPassword());
		System.out.println("email:"+requestUser.getEmail());
		
		User u = userRepository.findById(id).orElseThrow();     //요청 들어온 id의 user정보를 db에서 가져오며, 찾으려는 id가 없으면 exception발생  
				
		u.setPassword(requestUser.getPassword()); 				//비밀번호를 변경
		u.setEmail(requestUser.getEmail()); 					//이메일을 변경

		
		//userRepository.save(u);									//db update
		//@Transactional이 있으므로 굳이 수동으로 update 해주지 않아도 된다
		//@Transactional이 붙어 있기에 , 함수 종료시 자동으로 commit을 수행하면서
	    // 기존에 영속화 되어있는 것과 다름을 스스로 인지하는데 이를 더티체킹이라 부르며
		// 이는 결국 update효과를 내게 된다 
		
		return "id: " + id + "업데이트 완료!";
	}
	
	/*
	 * deleteUser for 회원정보삭제(password,email)
	 * http://localhost:8000/blog/dummy/deleteUser/{id}
	 * 
	 */
	@DeleteMapping("/dummy/deleteUser/{id}")
	public String deleteUser(@PathVariable int id) // json으로 들어와도 @RequestBody 있으면 MessageConverter가 jackson써서 json->자바 오브젝트로 바꿔줌 
	{
		try 
		{
			userRepository.deleteById(id);
		} 
		catch (Exception e) 
		{
			return "id: "+id +"는 db에 없으므로 삭제에 실패하였습니다";
		}
		
		return "id: " + id + "삭제 완료!";
	}
	
	
}
