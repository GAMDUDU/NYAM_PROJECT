<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<jsp:include page="../include/navi_ceo.jsp"/>
	
		<section>
			<h1 class="title">내 정보 수정</h1>
			
			<h4>현재 비밀번호를 입력해주세요.</h4>
			
			<form method="post" action="<%=request.getContextPath() %>/owner_mypage.do">
				<input type="password" name="pwd">
				<input type="submit" class="submitBtn" value="확인">
			</form>
			
		</section>
	
	</div>
	</div>
</body>
</html>