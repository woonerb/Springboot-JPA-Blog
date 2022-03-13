package com.cos.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;


@ControllerAdvice    //com.cos.blog내에서 excepiton이 발생하면 여기로 오게!!
@RestController
public class GlobalExceptionHandler 
{
	@ExceptionHandler(value=IllegalArgumentException.class) // IllegalArgumentException 익셉션이 발생하면 아래의 함수를 실행시킨다
	public ResponseDto<String> handleArgumentException(IllegalArgumentException e) 
	{
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
	}
}


