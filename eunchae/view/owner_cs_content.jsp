<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/eunchae/cssCeo/title.css">
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic:400,700,800&amp;subset=korean" rel="stylesheet">
</head>
<body>
	<jsp:include page="../include/navi_ceo.jsp"/>
	
	<section class="right_container">
		<h1 class="title">문의글 보기</h1>
		<a href="owner_cs.do?id=${id }">글 목록으로 ></a>
		<br><br>
		<table>
			<c:set var="dto" value="${content }" />
			<tr>
				<th>글제목</th>
				<td align="left"><h3>${dto.getService_title() }</h3></td>
				
			</tr>
			
			<tr>
				<th>문의내용</th>
				<td><textarea rows="20" cols="85" readonly>${dto.getService_cont() }</textarea></td>
			</tr>
			
			<tr>
				<td colspan="2">
					<a href="owner_cs_update.do?num=${dto.getService_num() }"><button class="submitBtn">글 수정</button></a>
					<a href="owner_cs_delete.do?num=${dto.getService_num() }"><button class="submitBtn">글 삭제</button></a>
				</td>
			</tr>
		</table>
	</section>
	
	</div>
	</div>
</body>
</html>