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
		<h1 class="title">문의글 검색 페이지</h1>
		<a href="owner_cs.do?num=${num }">글 목록으로 ></a>
		<br><br>
		<table width="800">
			<c:set var="list" value="${List }"/>
			<c:set var="num" value="${num }"/>
			<tr>
				<th>글번호</th>
				<th>글제목</th>
				<th>처리상황</th>
			</tr>
			
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
				<input type="hidden" name="service_num" value="${dto.getService_num() }">
				<input type="hidden" name="userId" value="${userId}">
					<tr>
						<td>${dto.getService_num() }</td>
						<td>	<!-- 제목 클릭시 리뷰페이지로 이동 -->
							<a href="#">
								${dto.getService_title() }
							</a>
						</td>
						<td>
							<c:if test="${dto.getService_check().equals('확인중') }">
								<button class="tableBtn">확인중</button>
							</c:if>
							
							<c:if test="${dto.getService_check().equals('답변완료') }">
								<button class="tableBtn">답변완료</button>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			
			<c:if test="${empty list }">
				<tr>
					<td colspan="3" align="center">
						<h3>검색된 문의글이 없습니다.</h3>
					</td>
				</tr>
			</c:if>
		</table>
		<br>
		
		<!-- 페이징처리 -->
		<c:if test="${page > block}">
			<a href="service_search.do?page=1&search_field=${search_field }&search_keyword=${search_keyword}">[맨 앞]</a>
			<a href="service_search.do?page=${startBlock - 1 }&search_field=${search_field }&search_keyword=${search_keyword}">◀</a>
		</c:if>
		
		<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
			<c:if test="${i == page }">
				<b><a href="service_search.do?page=${i }&search_field=${search_field }&search_keyword=${search_keyword}">[${i }]</a></b>
			</c:if>
			
			<c:if test="${i != page }">
				<a href="service_search.do?page=${i }&search_field=${search_field }&search_keyword=${search_keyword}">[${i }]</a>
			</c:if>
		</c:forEach>
		
		<c:if test="${endBlock < allPage }">
			<a href="service_search.do?page=${endBlock + 1 }&search_field=${search_field }&search_keyword=${search_keyword}">▶</a>
			<a href="service_search.do?page=${allPage }&search_field=${search_field }&search_keyword=${search_keyword}">[맨 뒤]</a>
		</c:if>
		<br><br>

		
	</section>
	
	</div>
	</div>
</body>
</html>