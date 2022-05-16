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
	<jsp:include page="../../navi/main_navi.jsp"/>
	
	<section>
		<c:set var="dto" value="${cont }"/>
		<h1 class="title">내 정보 수정하기</h1>
		<form method="post" action="<%=request.getContextPath()%>/owner_mypage_insert_ok.do">
			<input type="hidden" name="ceo_num" value="${dto.getCeo_num() }">
			
			<table>
				<tr>
					<th>아이디</th>
					<td><input name="id" value="${dto.getCeo_id() }" readonly></td>
				</tr>
				
				<tr>
					<th>사업자번호</th>
					<td><input name="conum" value="${dto.getCeo_conum() }" readonly></td>
				</tr>
				
				<tr>
					<th>이름</th>
					<td><input name="name" value="${dto.getCeo_name() }" readonly></td>
				</tr>
				
				<tr>
					<th>비밀번호</th>
					<td><input name="pwd" value="${dto.getCeo_pwd() }" type="password"></td>
				</tr>
				
				<tr>
					<th>연락처</th>
					<td><input name="phone" value="${dto.getCeo_phone() }" ></td>
				</tr>
				
				<tr>
					<th>이메일</th>
					<td><input name="mail" value="${dto.getCeo_mail() }" ></td>
				</tr>
				
				<tr>
					<th>가게의 지도상 위도</th>
					<td><input name="lat" value="${dto.getCeo_lat() }" ></td>
				</tr>
				
				<tr>
					<th>가게의 지도상 경도</th>
					<td><input name="lng" value="${dto.getCeo_lng() }" ></td>
				</tr>
				
			</table>
			
			<input class="btn btn-primary" type="submit" value="정보 수정하기">
		</form>
	</section>
	
	<jsp:include page="../../navi/footer.jsp"/>
	
	</div>
	</div>
</body>
</html>