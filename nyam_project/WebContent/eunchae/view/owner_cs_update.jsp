<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<jsp:include page="../include/navi_ceo.jsp"/>
	
	<section class="right_container">
		<h1 class="title">문의하기</h1>
		<form method="post" action="<%=request.getContextPath() %>/owner_cs_update_ok.do">
			<input type="hidden" value="${num }" name="num">
			<table>
				<c:set var="dto" value="${cont }"/>
				<c:set var="num" value="${num }"/>
				<tr>
					<th>글 제목</th>
					<td align="left"><input class="csUpdateInput" name="title" value="${dto.getService_title() }"></td>
				</tr>
				
				<tr>
					<th>문의 내용</th>
					<td><textarea rows="20" cols="85" name="cont">${dto.getService_cont() }</textarea></td>
				</tr>
				
				<tr>
					<td colspan="2">
						<input type="submit" class="submitBtn" value="수정하기">
						<a href="<%=request.getContextPath() %>/owner_cs_content.do?num=${num}"><button class="submitBtn">돌아가기</button></a>
					</td>
				</tr>
			</table>
		</form>
	</section>
	
	</div>
	</div>
</body>
</html>