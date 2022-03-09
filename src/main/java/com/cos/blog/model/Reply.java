package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity //USer 클래스를 읽어서 자동으로 MYsql에 테이블을 만들어준다 
public class Reply {

	@Id //Primary Key임을 알려준다
	@GeneratedValue(strategy = GenerationType.IDENTITY) //IDENENTITY: 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다                                   
	private int 		id;								// 즉, 오라클에 연결시 시퀀스전략을 쓴다는 것이고 MYSQL연결시 오토 인크리먼트를 쓴다는것이다
	

	@Column(nullable=false, length = 200)
	private String		content;

	@ManyToOne       // 여러개의 답변은 하나의 개시글에 달릴수 있다 Many Reply to One Board
	@JoinColumn(name="boardId")
	private Board board;
	
	@ManyToOne        // 여러개의 답변은 하나의 유저가 작성할 수 있다  Many Reply to One User
	@JoinColumn(name="userId")
	private User user;
	
	@CreationTimestamp // 시간이 자동으로 입력됨
	private Timestamp 	createDate;
}
