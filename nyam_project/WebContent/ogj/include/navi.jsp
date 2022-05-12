<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	*{
		margin:0;
		padding:0;
	}
	body{
		height:100%;
	}
	
	.wrapper{
	height:100%;
	display: grid;
	grid-template-columns: 25% 75% ;
	width:100%;
	
		}
	.menubar{
	
	background-color:#e0e0e0;
	width:80%;
	margin-left:50px;
	height:700px;
	grid-column: 1;
	
	
	
	}
	.menubar_left{
	padding-left:0px;
	
	width:100%;
	height:700px;
	list-style:none;
	
	
	}
	
	
	
	.menubar_left li{
	line-height:30px;
	list-style:none;
	}
	
	
	
	
	.center{
	margin-left:50px;
	grid-column: 2;
	}
	

	

</style>
</head>
<body>
<div class="wrapper"> 



	<div class="menubar">
		<ul class="menubar_left">
		<li class="bold">&nbsp;&nbsp;<a href="<%=request.getContextPath() %>/review_main.do">HOME</a>
			</li>
			<li class="bold">&nbsp;&nbsp;<a href="<%=request.getContextPath() %>/review_list.do?no=1">인기글</a>
			</li>
			<li class="bold">&nbsp;&nbsp;<a href="<%=request.getContextPath() %>/review_list.do?no=2">맛 보장된 집</a>
				<ul>
					
					<br>
				</ul>
			</li>
			<hr>
			<li class="bold">&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/owner_list.do">맛집둘러보기</a>
			
			
				<ul>
					<c:set var="num" value="${num}"/>
    		<a href="<%=request.getContextPath() %>/owner_insert.do?num=${num}">맛집으로 등록하기</a>
					
					
					<li>&nbsp;&nbsp;<a href="<%=request.getContextPath() %>/owner_insert.do">가게등록</a>
					</li>
					
					<br>
				</ul>
			</li>
			<hr>
			<li class="bold">&nbsp;&nbsp;마이페이지
				<ul>
					<li>&nbsp;&nbsp;내가쓴글
					</li>
					<li>&nbsp;&nbsp;내가쓴 댓글
					</li>
					<li>&nbsp;&nbsp;비밀번호 변경
					</li>
				</ul>	
			</li>
			<li class="bold">&nbsp;&nbsp;로그아웃
			</li>
		</ul>
		
		
		
		</div>
		<div class="center">
		
	



</body>
</html>