<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../include/navi_user.jsp"/>
	  <div class="title">
     <table class="titls" border="0" cellspacing="0">
      	<tr>
      	
      		<td>맛집 리스트</td>
		</tr>
		
      
  
		 </table>
		 
		 
		 
	</div>	 
	
	
	
		<div class="review">
			<h1> 리뷰 </h1>
			<br>
			
			<h2>리뷰</h2>
			<br>
			<table width="800">
				<tr>
					<th>글번호</th>
					<th>리뷰제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>처리상태</th>
				</tr>
				
				<c:set var="list" value="${List }"/>
				<c:if test="${!empty list }">
					<c:forEach items="${list }" var="dto">
					
						<tr>
							<td>${dto.getReview_num() }</td>
							<td>	<!-- 제목 클릭시 리뷰페이지로 이동 -->
							<a href="<%=request.getContextPath() %>/review_mycontent.do?no=${dto.getReview_num()}">${dto.getReview_title() }</a>	
							</td>
							<td>${dto.getReview_id() }</td>
							<td>${dto.getReview_date().substring(0, 10) }</td>
							
					</c:forEach>
				</c:if>
				
				<c:if test="${empty list }">
					<tr>
						<td colspan="5" align="center">
							<h3>신고된 리뷰가 없습니다.</h3>
						</td>
					</tr>
				</c:if>
			</table>
			<br>
			
			<c:if test="${page > block}">
					<a href="review_mylist.do?num=${num }&page=1">◀◀</a>
					<a href="review_mylist.do?num=${num }&page=${startBlock - 1 }">◀</a>
				</c:if>
			
				<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
					<c:if test="${(i == page)}">
						<b><a href="review_mylist.do?num=${num }&page=${i }">[${i }]</a></b>
					</c:if>
					
					<c:if test="${i != page }">
						<a href="review_mylist.do?num=${num }&page=${i }">[${i }]</a>
					</c:if>
				</c:forEach>
			
				<c:if test="${endBlock < allPage }">
					<a href="review_mylist.do?num=${num }&page=${endBlock + 1 }">▶</a>
					<a href="review_mylist.do?num=${num }&page=${allPage }">▶▶</a>
				</c:if>
		</div>
		<br><br><br>
		</div>

</body>
</html>