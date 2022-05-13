<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/navi_ceo.css">


</head>
<body>
	<div class="wrapper">
		<div class="menubar">
			<div class="naviBox">
			<h3>NyamNyam 🍔</h3>
			<h4>관리자 페이지</h4>
			
			<ul class="menubar_left">
				<li class="bold"><a href="<%=request.getContextPath()%>/admin_member_Management.do">회원관리</a></li>
			
				<li class="bold">
				<a href="<%=request.getContextPath()%>/admin_reply_Management.do">댓글관리</a></li>
	
				<li class="bold">
				<a href="<%=request.getContextPath()%>/admin_review_Management.do">리뷰관리</a></li>
	
				<li class="bold">
				<a href="<%=request.getContextPath()%>/admin_ceo_Management.do">가게관리</a></li>
	
				<li class="bold">
				<a href="<%=request.getContextPath()%>/admin_service_Management.do">고객센터</a></li>

			</ul>
		</div>
		
		<div class="naviBox">
		<ul>
		<li><a href="<%=request.getContextPath() %>/admin_logout.do">로그아웃</a></li>
		<li><a href="<%=request.getContextPath() %>/dohyung/main.jsp">메인페이지로</a></li>
		</ul>
		</div>
		
		
		</div>
		<div class="center">
</body>
</html>



