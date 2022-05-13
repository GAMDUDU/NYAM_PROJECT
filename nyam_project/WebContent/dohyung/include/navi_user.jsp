<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/navi_ceo.css">
<style type="text/css">
	*{
		margin:0;
		padding:0;
	}

	.wrapper{
		width:100vw;
		height:100vh;
		display: grid;
		grid-template-columns: 20% 80% ;
	}
	
	.menubar{
		grid-column: 1;
		height: 100%;
	}
	
	.box{
		padding-bottom: 50px;
	}


	.menubar ul{
		padding-top: 5px;
		padding-bottom: 5px;
		border-top: 2px solid #444;
    	border-bottom: 1px solid #ddd;
	}


	.menubar_left li{
		list-style:none;
	}
	
	.menubar a{
		color: black;
	}
	
	.menubar a:hover{
		color: #ddd;
	}

	.center{
		margin-left:100px;
	grid-column: 2;
	}
</style>
</head>
<body>

		
		
	<div class="wrapper"> 
	<div class="menubar">
		<div class="box">
			<h2>&nbsp;&nbsp;NyamNyam</h2>
			
			
		
		
		
		
			<ul>
			<li class="bold">&nbsp;&nbsp;<a href="<%=request.getContextPath() %>/review_main.do">HOME</a></li>
				<li>&nbsp;&nbsp;<a href="<%=request.getContextPath() %>/review_list.do?no=1">인기글</a></li>
				<li>&nbsp;&nbsp;<a href="<%=request.getContextPath() %>/review_list.do?no=2">맛집</a></li>
				<li class="bold">&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/owner_list.do">맛집둘러보기</a></li>
			</ul>
		</div>
		
		<div class="box">
			<h2>&nbsp;&nbsp;마이페이지</h2>
			<ul>
				<li>&nbsp;&nbsp;<a href="<%=request.getContextPath() %>/review_mylist.do">내가쓴 리뷰 </a>
				<li>&nbsp;&nbsp;<a href="<%=request.getContextPath() %>/reply_mylist.do">내가쓴 댓글 </a>
				
				<li>&nbsp;&nbsp;정보수정</li>
				<li>&nbsp;&nbsp;고객센터</li>
			</ul>
		</div>
		
		<div align="right">
		<h3><a href="<%=request.getContextPath() %>/owner_logout.do">로그아웃</a></h3>
				<jsp:include page="../../ogj/login/login_main2.jsp"/>
		
			<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">로그인</button>
		
		</div>
		
	</div>
	

	<div class="center">
	
	
		
	
	


