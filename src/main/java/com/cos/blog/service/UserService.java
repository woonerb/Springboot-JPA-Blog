package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service //스프링이 컴포넌트 스캔해서, Bean에 등록해준다. (==IOC를해준다)(==메모리에 띄어준다)
public class UserService 
{

	@Autowired //스프링이 DI 하게 등록하라고!
	private UserRepository userRepository;
	
	@Transactional //아래 메소드 전체가 성공하면 commit, 하나라도 실패하면 rollback
	public int 회원가입(User user) 
	{
		try 
		{
			userRepository.save(user);
			return 1;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("UserService: 회원가입():" + e.getMessage());
		}
		return -1;
	}
}
