<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/eunchae/cssCeo/title.css">
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic:400,700,800&amp;subset=korean" rel="stylesheet">

<link rel="stylesheet" href="/css/bootstrap.css">
<script type="text/javascript" src="/js/bootstrap.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

</head>
<body>
	<jsp:include page="../../navi/main_navi.jsp"/>
	
	
		<section>
			<h1 class="title">내 정보 수정</h1>
			
			<h4>현재 비밀번호를 입력해주세요.</h4>
			
			<form method="post" action="<%=request.getContextPath() %>/owner_mypage2.do">
				<input type="password" name="pwd">
				<input type="submit" class="btn btn-primary" value="확인">
			</form>
			
		</section>
	
	<jsp:include page="../../navi/footer.jsp"/>
	
	</div>
	</div>
</body>
</html>