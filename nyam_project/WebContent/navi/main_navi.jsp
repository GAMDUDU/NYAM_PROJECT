<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/navi_ceo.css">

<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.6.0.js"></script>

	
<script type="text/javascript">






$(function(){
	
	
	
	var usertype = '<%=(String)session.getAttribute("user")%>';
	
	if(usertype == "member"){
		$("#nonuser_navi").css({
			"display":"none"
		});
		$("#ceo_navi").css({
			"display":"none"
		});
		$("#user_navi").css({
			"display":"block"
		});
		$("#admin_navi").css({
			"display":"none"
		});
		$("#main_div").css({
			"display":"block"
		});
	}else if(usertype == "ceo"){
		$("#nonuser_navi").css({
			"display":"none"
		});
		$("#ceo_navi").css({
			"display":"block"
		});
		$("#user_navi").css({
			"display":"none"
		});
		$("#admin_navi").css({
			"display":"none"
		});
		$("#main_div").css({
			"display":"block"
		});
	}else if(usertype == "admin"){
		$("#nonuser_navi").css({
			"display":"none"
		});
		$("#ceo_navi").css({
			"display":"none"
		});
		$("#user_navi").css({
			"display":"none"
		});
		$("#admin_navi").css({
			"display":"block"
		});
		$("#main_div").css({
			"display":"none"
		});
	}else if(usertype == null){
		$("#nonuser_navi").css({
			"display":"block"
		});
		$("#ceo_navi").css({
			"display":"none"
		});
		$("#user_navi").css({
			"display":"none"
		});
		$("#admin_navi").css({
			"display":"none"
		});
		$("#main_div").css({
			"display":"block"
		});
	}
	
	
	
});
	
</script>

	


</head>
<body>



	<div class="wrapper">
		<div class="menubar">
			<div class="naviBox" id="main_div">
				<h2>NyamNyam 🍔</h2>
				<ul>
					
					<li><a href="<%=request.getContextPath()%>/review_main.do">맛집
							스토리</a></li>
							<li><a href="<%=request.getContextPath()%>/owner_list.do">맛집
							리스트</a></li>
				</ul>
			</div>
			<div class="naviBox" id = "nonuser_navi">
				<!-- 방문객일때 (로그인X)  -->
				<h3>마이페이지</h3>
				<ul>
					<li><a href="#" data-bs-toggle="modal"
						data-bs-target="#staticBackdrop">로그인</a></li>
					<jsp:include
						page="../ogj/login/login_main2.jsp"></jsp:include>
				</ul>
			</div>
			<div class="naviBox" id = "user_navi" style="display:none;">
				<h3>마이페이지</h3>
				<!-- 회원일때 -->
				<ul>
					<li><a href="<%=request.getContextPath()%>/review_mylist.do">나의
							스토리 </a>
					<li><a href="<%=request.getContextPath()%>/reply_mylist.do">나의
							댓글 </a>
					<li><a href="<%=request.getContextPath()%>/reply_mylist.do">정보수정
					</a>
					<li><a href="<%=request.getContextPath()%>/reply_mylist.do">고객센터
					</a>
					<li><a href="<%=request.getContextPath()%>/owner_logout.do">로그아웃</a></li>
				</ul>
			</div>
			<div class="naviBox" id = "ceo_navi" style="display:none;">
				<!-- 사장님일때  -->
				<h3>마이페이지</h3>
				<ul>
					<li><a
						href="<%=request.getContextPath() %>/owner_insert.do?id=${id}">가게
							소개 하기</a></li>
					<li><a
						href="<%=request.getContextPath() %>/owner_reply.do?id=${id}">내
							가게 스토리 / 댓글 보기</a></li>
					<li><a
						href="<%=request.getContextPath() %>/owner_my_reply.do?id=${id}">내가
							쓴 댓글 보기</a></li>
					<li><a
						href="<%=request.getContextPath() %>/owner_cs_review.do?id=${id}">신고한
							리뷰 / 댓글 보기</a></li>
					<li><a href="">정보 관리</a></li>
					<li><a
						href="<%=request.getContextPath() %>/owner_cs.do?id=${id}">고객센터</a></li>
					<li><a href="<%=request.getContextPath()%>/owner_logout.do">로그아웃</a></li>
				</ul>
			</div>
			<div class="naviBox" id = "admin_navi" style="display:none;">
				<!-- 관리자일때  -->
				<h3>NyamNyam 🍔</h3>
				<h3>관리자 페이지</h3>
				<ul>
					<li><a
						href="<%=request.getContextPath()%>/admin_member_Management.do">회원관리</a></li>

					<li><a
						href="<%=request.getContextPath()%>/admin_reply_Management.do">댓글관리</a></li>

					<li><a
						href="<%=request.getContextPath()%>/admin_review_Management.do">리뷰관리</a></li>

					<li><a
						href="<%=request.getContextPath()%>/admin_ceo_Management.do">가게관리</a></li>

					<li><a
						href="<%=request.getContextPath()%>/admin_service_Management.do">고객센터</a></li>
					<li><a href="<%=request.getContextPath()%>/owner_logout.do">로그아웃</a></li>
				</ul>
			</div>
		</div>

		<div class="center">
</body>
</html>



