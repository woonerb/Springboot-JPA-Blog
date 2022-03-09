package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity //Board 클래스를 읽어서 자동으로 MYsql에 테이블을 만들어준다 
public class Board {
		
		@Id //Primary Key임을 알려준다
		@GeneratedValue(strategy = GenerationType.IDENTITY) //IDENENTITY: 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다                                   
		private int 		id;								// 즉, 오라클에 연결시 시퀀스전략을 쓴다는 것이고 MYSQL연결시 오토 인크리먼트를 쓴다는것이다
		
		@Column(nullable = false, length = 100)		 				// null될수 없으며 길이가 30이다
		private String         title ;  	
		
		
		@Lob //대용량의 데이터
		private String		content; // 썸머노트라는 라이브러리 <html> 태그를 써서 디자인 할 것 이다   

			
	    @ColumnDefault("0")	  						//기본값은 0이다
		private int	 		count;	 				//조회수 
	    
	    										// Many = Board, User = One: 한명의 유저는 여러개의 게시물을 쓸 수 있다.
	    @ManyToOne(fetch = FetchType.EAGER)  	// N:1(Board:User의 관계) EAGER:무조건 가져와야함, LAZY: 선택시에만 가져옴
	    @JoinColumn(name="userId")				// db는 오브젝트를 저장하지 못해서 FK를통해 조인하여 사용한다 BUT, 자바는 오브젝트를 저장할 수 있어서 이렇게 쓴다
	    private User 		user;				// ORA가 있기에, 오브젝트로 저장을 하면 알아서 DB를 조인해서 쓰는것 처럼 해준다		//컬럼명은 userId로 사용!!
	    
	    @OneToMany(mappedBy="board", fetch = FetchType.EAGER) //mappedBy 연관관계의 주인이 아니다(난 FK가 아님을 알려줌 즉,DB에 컬럼을 만들지 말라는것)
	    private List<Reply> reply;
	    
		@CreationTimestamp // 시간이 자동으로 입력됨
		private Timestamp 	createDate;
		
}
