let index = {

	init: function() {    //btn-save버튼이 클릭되면 save를 한다
		$("#btn-save").on("click", () => { //function()=>{} this를 바인딩 해주기 위하여 사용!
			this.save();
		});
	}
,
	save: function() {
		//alert("user의 save함수 호출되었음");
		
		let data ={
			username:$("#username").val(),
			password:$("#password").val(),
			email:$("#email").val()
		};
		
		
		// ajax 호출시 default가 비동기 호출이다
		// ajax 통신을 이용해서 3개의 데이터를 json으로 변경해서 isnert 요청한다
		$.ajax({
			//회원가입 수행을 요청해서
			type:"POST",
			url:"/blog/api/user",
			data:JSON.stringify(data),
			contentType:"application/json; charset=utf-8",  //body데이터의 타입  (MIME)
			dataType:"json"									//요청에 대해 응답받을 데이터의 타입이며 여기여 json을 적어두면 
															//응답받은 byte문자열 ->javascript object로 자동 변환해줌
			
		    //결과가 정상인경우	
		}).done(function(resp){
				alert("회원가입이 완료되었습니다.");
				console.log(resp);
				location.href="/blog";
			
		   //결과가 실패인경우
		}).fail(function(error){
				alert(JSON.stringify(error));		
			
		}); //ajax 통신을 이용해서 3개의 데이터를 
		                     //json으로 변경하여 insert 요청한다                     
	},
	
	login: function() {
		//alert("user의 save함수 호출되었음");
		
		let data ={
			username:$("#username").val(),
			password:$("#password").val()
		};
		
		
		// ajax 호출시 default가 비동기 호출이다
		// ajax 통신을 이용해서 3개의 데이터를 json으로 변경해서 isnert 요청한다
		$.ajax({
			//회원가입 수행을 요청해서
			type:"POST",
			url:"/blog/api/user/login",
			data:JSON.stringify(data),						//http body 데이터
			contentType:"application/json; charset=utf-8",  //body데이터의 타입  (MIME)
			dataType:"json"									//요청에 대해 응답받을 데이터의 타입이며 여기여 json을 적어두면 
															//응답받은 byte문자열 ->javascript object로 자동 변환해줌
			
		    //결과가 정상인경우	
		}).done(function(resp){
				alert("로그인이 완료되었습니다.");
				console.log(resp);
				location.href="/blog";
			
		   //결과가 실패인경우
		}).fail(function(error){
				alert(JSON.stringify(error));		
			
		}); //ajax 통신을 이용해서 2개의 데이터를 
		                     //json으로 변경하여 select 요청한다                     
	}
}