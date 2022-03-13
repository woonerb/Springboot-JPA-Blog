package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;
	
//JpaRepository를 extends하면서 자동으로 bean에 등록되므로
//@Repository 생략 가능			
                                                     //User 테이블이 관리하며, PK는 Integer형이다를 표현!
public interface UserRepository extends JpaRepository<User,Integer>
{
	//마치, DAO로 사용할 수 있게 된다
	//자동으로 bean 등록이 된다 -> @Repository 생략 가능

	
	
	//JPA Naming 쿼리
	//SELECT * FROM user WHERE =a1 AND password=a2;   가 자동으로 만들어진다.
	User findByUsernameAndPassword(String a1, String a2);      //유저네임과 password로 찾는다

	
	/*
	//위의 것을 똑같이 nativeQuery를 이용해서 표현한것!!
	@Query(value="SELECT * FROM user WHERE username =?1 and password=?2",nativeQuery=true)
	User login(String username, String password);
	*/
}
