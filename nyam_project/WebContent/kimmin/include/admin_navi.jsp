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
		grid-column: 2;
		margin: auto;
	}
	
	.wrapper {
	height: 100%;
	display: grid;
	grid-template-columns: 20% 75%;
	width: 100%;
}
	
	.center {
	grid-column: 2;
}
</style>

</head>
<body>
	<div class="wrapper">
		<div class="menubar">
			<div class="box">
			<ul class="menubar_left">
				<li class="bold"><a href="<%=request.getContextPath()%>/member_Management.do">회원관리</a></li>
			
				<li class="bold">
				<a href="<%=request.getContextPath()%>/reply_Management.do">댓글관리</a></li>
	
				<li class="bold">
				<a href="<%=request.getContextPath()%>/review_Management.do">리뷰관리</a></li>
	
				<li class="bold">
				<a href="<%=request.getContextPath()%>/ceo_Management.do">가게관리</a></li>
	
				<li class="bold">
				<a href="<%=request.getContextPath()%>/service_Management.do">고객센터</a></li>

			</ul>
		</div>
		
		<div align="right">
		<h3><a href="<%=request.getContextPath() %>/admin_logout.do">로그아웃</a></h3>
		<h3><a href="<%=request.getContextPath() %>/main.jsp">메인페이지로</a></h3>
		</div>
		
		
		</div>
		<div class="center">
</body>
</html>



