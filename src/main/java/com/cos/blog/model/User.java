package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder   //객체 생성시 생성자를 일일이 안만들어도 ok! 
//@DynamicInsert //null인 필드가 있음면 제외하고 인서트해준다
@Entity //USer 클래스를 읽어서 자동으로 MYsql에 테이블을 만들어준다 
public class User {
	
	@Id //Primary Key임을 알려준다                           		//.TABLE     : 테이블의 넘버링 전략을 따라간다                     
	@GeneratedValue(strategy = GenerationType.IDENTITY)  		//.IDENENTITY: 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다 즉, 오라클에 연결시 시퀀스전략을 쓴다는 것이고 MYSQL연결시 오토 인크리먼트를 쓴 
	@Column(nullable = false, length = 30) 						// null될수 없으며 길이가 30이다   // 시퀀스, auto_increment
	private int         id ;  		            				
	
	@Column(nullable = false, length = 30, unique =true) 		//  null될수 없으며, 고유한 값이어야한다 길길이가 30이다
	private String 		username;  // 아이디

	@Column(nullable = false, length = 100) 					//  null될수 없으며길이가 100이다, 나중에 암호화된 HASH값으로 대체
	private String 		password;
	
	@Column(nullable = false, length = 50) 						//  null될수 없으며길이가 50이다
	private String 		email;
	
	@Enumerated(EnumType.STRING)  								//db에는 enum타입이 없으므로 붙인다
	private RoleType 	role;	 								//String이 아닌 Enum으로 //ADMIN, USER, MANAGER 중에서만 선택할 수 있게 한다	
	
	@CreationTimestamp 											// 시간이 자동으로 입력됨
	private Timestamp 	createDate;
	
	
}
