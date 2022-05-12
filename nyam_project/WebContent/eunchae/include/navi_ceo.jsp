<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../../css/navi_ceo.css">
</head>
<body>
<div class="wrapper"> 
	<div class="menubar">
		<div class="naviBox">
			<h2>NyamNyam 🍔</h2>
			<ul>
				<li><a href="<%=request.getContextPath() %>/reivew_list.do">인기글</a></li>
				<li><a href="<%=request.getContextPath() %>/reivew_list.do">맛집</a></li>
			</ul>
		</div>
		
		<div class="naviBox">
			<h3>마이페이지</h3>
			<ul>
				<li><a href="<%=request.getContextPath() %>/owner_insert.do?num=${num}">가게 소개 하기</a></li>
				<li><a href="<%=request.getContextPath() %>/owner_reply.do?num=${num}">내 가게 리뷰 / 댓글 보기</a></li>
				<li><a href="<%=request.getContextPath() %>/owner_my_reply.do?num=${num}">내가 쓴 댓글 보기</a></li>
				<li><a href="<%=request.getContextPath() %>/owner_cs_review.do?num=${num}">신고된 리뷰 / 댓글 관리</a></li>
				<li><a href="<%=request.getContextPath() %>/owner_cs.do?num=${num}">고객센터</a></li>
			</ul>
		</div>
		
		<%-- <jsp:include page="../login/login_main2.jsp"/> --%>
		
		<div align="right" class="naviLog">
			<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop">로그인</button>
			<%-- <h3><a href="<%=request.getContextPath() %>/owner_logout.do"><button class="btn btn-primary">로그아웃</button></a></h3> --%>
		</div>
	</div>

	<div class="center">
	<!-- navi쓰는 곳에서 div 두번 닫아줘야함 -->

</body>
</html>