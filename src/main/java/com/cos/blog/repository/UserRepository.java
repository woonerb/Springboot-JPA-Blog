package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cos.blog.model.User;
	
//JpaRepository를 extends하면서 자동으로 bean에 등록되므로
//@Repository 생략 가능			
                                                     //User 테이블이 관리하며, PK는 Integer형이다를 표현!
public interface UserRepository extends JpaRepository<User,Integer>
{
	//마치, DAO로 사용할 수 있게 된다
	//자동으로 bean 등록이 된다 -> @Repository 생략 가능
}
