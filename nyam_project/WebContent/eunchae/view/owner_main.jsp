<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./style.css">
</head>
<body>
	<jsp:include page="../include/navi_ceo.jsp"/>
	<c:set var="num" value="${num}"/>
    		<a href="<%=request.getContextPath() %>/owner_insert.do?num=${num}">맛집으로 등록하기</a>
    		<br>
        	<a href="<%=request.getContextPath() %>/owner_reply.do?num=${num}">리뷰 / 댓글 보기</a>
        	<br>
        	<a href="<%=request.getContextPath() %>/owner_cs.do?num=${num}">고객센터</a>
        	<br>

        	<h3>*사장님 로그인 시 메인 페이지*</h3>
		</div>
	</div>
</body>
</html>