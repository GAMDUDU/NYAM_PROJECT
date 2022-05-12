/**
 * 	customer 테이블을 이용한 Ajax 실습 
 */

$(function(){
	
	// 여러 ajax에서 동일하게 사용되는 속성 설정
	$.ajaxSetup({
		//ajax에서 한글 깨짐 문제 해결
		ContentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type: "post",
		
	});
	// 모든 데이터를 가져오는 함수 - customer 테이블의 전체 데이터를 가져오는 함수
	
	
		/*$("#like").click(function(){
		$.ajax({
		url: "/nyamnyam/like",
		type: "POST",
		dataType: "json",
		data: $('#like_form').serialize(), //아이디가 like_form인 곳의 모든 정보를 가져와 파라미터 전송 형태(표준 쿼리형태)로 만들어줌
		success:
		function(data){ //ajax통신 성공시 넘어오는 데이터 통째 이름 =data
		alert("'좋아요'가 반영되었습니다!") ; // data중 put한 것의 이름 like
		$("#like_result").html(data.like); //id값이 like_result인 html을 찾아서 data.like값으로 바꿔준다.
		},
		error:
		function (request, status, error){
		alert("ajax실패")
		}
		});
		}	
*/

/*	$("#likes").click(function(){
		$.ajax({
		url: "/nyamnyam/like",
		type: "POST",
		dataType: "json",
		data: $('#like_form').serialize(), //아이디가 like_form인 곳의 모든 정보를 가져와 파라미터 전송 형태(표준 쿼리형태)로 만들어줌
		success:
		function(data){ //ajax통신 성공시 넘어오는 데이터 통째 이름 =data
		alert("'좋아요'가 반영되었습니다!") ; // data중 put한 것의 이름 like
		$("#like_result").text(data); //id값이 like_result인 html을 찾아서 data.like값으로 바꿔준다.
		
		},
		error:
		function (request, status, error){
		alert("ajax실패")
		}
		});
		});	
		*/
		
		/*$("#favorite").click(function(){			
			
			$.post(
					"/favorite"
					, {"articleId" : "${article.articleId}"}	
					, function(data){
												
						var jsonData3 = {};
						try {
							jsonData3 = JSON.parse(data);
						}catch(e) {
							jsonData3.result = false;
						}
						console.log(jsonData3);
						
						if ( jsonData3.result ){
							var text = $("#favorite").text();
							if (jsonData3.isFavorite){
								$("#favorite").text("♥");
							}
							else if (text == "♥"){
									$("#favorite").text("♡");
							} 
						}
						else {
							 alert("세션이 만료되었습니다. 다시 로그인해주세요.");
							location.href = "/"; 
						}
					}
			);
		});*/
		
	$("#likes").click(function(){
		var like_form=$('#like_form');
		$.ajax({
		url: "/nyam_project/like",
		type: "POST",
		dataType: "json",
		data: $('#like_form').serialize(), //아이디가 like_form인 곳의 모든 정보를 가져와 파라미터 전송 형태(표준 쿼리형태)로 만들어줌
		success:
		function(data){ //ajax통신 성공시 넘어오는 데이터 통째 이름 =data
		var likecheck=data.likecheck;
		var like= data.like;
		var likes='';
		
		if(likecheck==1){
			alert("추천");
			$("#like_result").text(like);
			likes="/nyam_project/dohyung/upload/like.png"
			
			
		}else{
			alert("추천취소");
			$("#like_result").text(like);
			likes="/nyam_project/dohyung/upload/dislike.png"
		}
		$('#likes', like_form).attr('src', likes);
		
		
		/*if(data[0]==1){
			alert("추천 ") ;
			$("#like_result").text(data[1]);
		} else if (data[0] == 0){
			alert("추천 취소") ;
			$("#like_result").text(data[1]);
		}*/
		 // data중 put한 것의 이름 like
		 //id값이 like_result인 html을 찾아서 data.like값으로 바꿔준다.
		
		},
		error:
		function (request, status, error){
		alert("ajax실패")
		}
		});
		});	


/*
	$("#likes").click(function(){
		$.ajax({
		url: "/nyamnyam/like",
		type: "POST",
		dataType: "json",
		data: $('#like_form').serialize(), //아이디가 like_form인 곳의 모든 정보를 가져와 파라미터 전송 형태(표준 쿼리형태)로 만들어줌
		success:
		function(data){ //ajax통신 성공시 넘어오는 데이터 통째 이름 =data
		
		if(data==0){
			alert("추천 완료") ;
		} else if (likecheck == 1){
			alert("추천 취소") ;
			
		}
		 // data중 put한 것의 이름 like
		
		
		},
		error:
		function (request, status, error){
		alert("ajax실패")
		}
		});
		});	
*/

	
	
	
	function getData(){
		
		var rcnum=$("#r_c_num").val();
		var rnum=$("#r_num").val();
		$.ajax({
		url: "/nyam_project/select2",
		datatype : "xml",		 //결과 데이터 타입
		data: {num : rcnum, rnum: rnum},
		success : function(data){
			
			//테이블 태그의 첫번째 행(타이틀(제목) 태그)을 제외하고 
			//나머지 모든 행을 제거하라는 의미.
			
			$("#reply tr:gt(0)").remove();
			let table ="";
			$(data).find("reply").each(function(){
				
				table +="<tr>";
				
				
				/*table +="<td>"+$(this).find("id").text()+"</td>";
				table +="<td>"+$(this).find("date").text() +"</td>"; */
				table +="<td>"+$(this).find("id").text()+"<br>"+$(this).find("date").text().substring(0,10)+"</td>";
				
				table +="<td>&nbsp;&nbsp;"+$(this).find("cont").text() + "</td>";	
				
				
				table +="<td id='tdDel'> <input type='button' value='삭제'" +
						   " id='del' num='" +$(this).find("no").text() +"'></td>";
				table += "</tr>";
			});
				
				//테이블의 첫번째 행의 아래에 table 추가
				$("#reply tr:eq(0)").after(table);
		},
			error:function(){
				alert('통신 에러 발생');
			}
		});
	}	//getData() 함수 end	
	

	
	//가입하기 버튼을 클릭했을 때 DB에 추가로 저장
	/*$("#btns").on("click", function(){
		var replyceonum=$("#r_c_num").val();
		var reviewnum=$("#r_num").val();
	
		
		var replyid=$("#id").val();
		var replytext=$("#cont").val();
		 if (replytext.trim().length==0){
        alert("댓글을 입력해주세요!")
        $("#replytext").focus();
        return;
    }
		
		var reply = {"replyceonum":replyceonum, "reviewnum":reviewnum, "replyid":replyid,"replytext":replytext};
				
		$.ajax({
			url:"/nyamnyam/insert",
			datatype:"text",
			data:JSON.stringify(reply),
			
			success:function(data){
				if(data>0){
					alert('고객 가입 완료!!!');
					
					// 가입완료 후에 다시 전체 레코드를 화면에 뿌려주면 됨
					getData();
					
					//input 태그에 입력된 내용을 지우는 작업.
					$("input[type=text]").each(function(){
						$(this).val("");
					});
				}else {
					alert('고객 가입이 실패 하였습니다.~~~');
				}
			},
			
			error: function(){
				alert('통신 오류 입니다.');
			}
			
		});
		
	});	// 댓글 등록하기 end*/
	
	$("#btns").click(function(){
					
		$.ajax({
			url:"/nyam_project/insert2",
			datatype:"text",
			data:$("#reply_su").serialize(),
			success:function(data){
				if(data>0){
					alert('댓글 등록 완료!!!');
					
					// 가입완료 후에 다시 전체 레코드를 화면에 뿌려주면 됨
					getData();
					
					//input 태그에 입력된 내용을 지우는 작업.
					$("input[type=text]").each(function(){
						$(this).val("");
					});
				}else {
					alert('고객 가입이 실패 하였습니다.~~~');
				}
			},
			
			error: function(){
				alert('통신 오류 입니다.');
			}
			
		});
		
	});	// 댓글 등록하기 end*/
	
	
	// 삭제 버튼을 클릭했을 때 이벤트 적용 
	// 삭제 버튼은 동적으로 생성된 요소는 제이쿼리에서 on() 함수를 이용해야 함.
	// 형식) on("click 이나 change 같은 이벤트", "이벤트 적용 선택자 또는 태그", 동작함수(무명함수))
	$("table").on("click", "#del", function(){
		$.ajax({
			url: "/nyam_project/delete.do",
			datatype:"text",
			data:"no="+$(this).attr("num"),
			success:function(data){
				if(data>0){
					alert('고객이 삭제 되었습니다!!!');
					getData();
				}else{
					alert('고객 삭제 실패~~~~');
				}
			},
			error:function(){
				alert('통신 오류 발생~~~~');
			}
		});
		
	});
		
	getData();
	
	
});