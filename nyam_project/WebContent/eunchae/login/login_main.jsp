<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">
$(function(){
	var user2 = "<c:out value='${user}'/>";
	if(user2 == "member"){
		var informationurl = "<%=request.getContextPath() %>/user_information.do";
	}else if (user2 == "ceo"){
		var informationurl = "<%=request.getContextPath() %>/ceo_information.do";
	}
	
	$("#information").click(function(){
		location.href = informationurl; 

	});
	
	$("#inquiry").click(function(){
		location.href = "<%=request.getContextPath() %>/inquiry.do";
	});
	
	
});

</script>
</head>
<body>




	<jsp:include page="../include/navi_ceo.jsp"/>

	<button type="button" class="btn btn-primary" data-bs-toggle="modal"
		data-bs-target="#staticBackdrop">로그인</button>
	<jsp:include page="login_main2.jsp"></jsp:include>


	<span id="welcom"></span>







	</div>
	</div>






</body>
</html>