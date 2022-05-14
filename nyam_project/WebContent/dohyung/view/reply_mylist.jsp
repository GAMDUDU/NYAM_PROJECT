<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css">

</head>
<body>
<jsp:include page="../../navi/main_navi.jsp"/>
		 <div class="title">
	
	
	
		<div class="comment">
			
			<br>
			<br><Br>
			
			<h2>댓글리스트</h2>
			<br>
			</div>
			<table width="800">
					<tr>
						<th>리뷰/홍보글</th>
						<th>댓글내용</th>
						<th>작성자</th>
						<th>작성일</th>
						
					</tr>
				
				<c:set var="list" value="${List }"/>
				<c:if test="${!empty list }">
					<c:forEach items="${list }" var="dto">
					<input type="hidden" name="reply_num" value="${dto.getReply_num() }">
						<tr>
							<td>
							<c:if test="${dto.getReply_review_num() == 0 }">
											홍보글
										</c:if>
										
										<c:if test="${dto.getReply_review_num() != 0 }">
											리뷰
										</c:if>
										</td>
							<td>	<!-- 제목 클릭시 리뷰페이지로 이동 -->
								
									${dto.getReply_cont() }
								
							</td>
							<td>${dto.getReply_id() }</td>
							<td>${dto.getReply_date().substring(0, 10) }</td>
							
						</tr>
					</c:forEach>
				</c:if>
				
				<c:if test="${empty list }">
					<tr>
						<td colspan="5" align="center">
							<h3>작성된 댓글이 없습니다.</h3>
						</td>
					</tr>
				</c:if>
			</table>
			<br>
			
			<c:if test="${page > block}">
					<a href="reply_mylist.do?num=${num }&page=1">◀◀</a>
					<a href="reply_mylist.do?num=${num }&page=${startBlock - 1 }">◀</a>
				</c:if>
			
				<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
					<c:if test="${(i == page)}">
						<b><a href="reply_mylist.do?num=${num }&page=${i }">[${i }]</a></b>
					</c:if>
					
					<c:if test="${i != page }">
						<a href="reply_mylist.do?num=${num }&page=${i }">[${i }]</a>
					</c:if>
				</c:forEach>
			
				<c:if test="${endBlock < allPage }">
					<a href="reply_mylist.do?num=${num }&page=${endBlock + 1 }">▶</a>
					<a href="reply_mylist.do?num=${num }&page=${allPage }">▶▶</a>
				</c:if>
		</div>
		<br><br><br>
		</div>

</body>
</html>