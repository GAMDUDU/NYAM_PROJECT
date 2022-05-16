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
		<section class="right_container">
			<h1 class="title">내가 쓴 댓글</h1>

			<div class="panel panel-primary">
				<h1>작성한 댓글들</h1>
				<br>
				<table class="table">
					<tr class="user-info-header">
						<th>글번호</th>
						<th>댓글내용</th>
						<th>작성일</th>
					</tr>
					
					<c:set var="list" value="${List }"/>
					<c:if test="${!empty list }">
						<c:forEach items="${list }" var="dto">
						<input type="hidden" name="reply_num" value="${dto.getReply_num() }">
						<input type="hidden" name="userId" value="${userId}">
							<tr>
								<td>${dto.getReply_num() }</td>
								<td>	<!-- 댓글내용 클릭시 댓글 단페이지로 이동 -->
									<a href="#">
										${dto.getReply_cont() }
									</a>
								</td>
								<td>${dto.getReply_date().substring(0, 10) }</td>
						</c:forEach>
					</c:if>
					
					<c:if test="${empty list }">
						<tr>
							<td colspan="5" align="center">
								<h3>작성한 댓글이 없습니다.</h3>
							</td>
						</tr>
					</c:if>
				</table>
				<br>
				
				<c:if test="${page > block}">
						<a href="owner_reply.do?num=${num }&page=1">◀◀</a>
						<a href="owner_reply.do?num=${num }&page=${startBlock - 1 }">◀</a>
					</c:if>
				
					<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
						<c:if test="${(i == page)}">
							<b><a href="owner_reply.do?num=${num }&page=${i }">[${i }]</a></b>
						</c:if>
						
						<c:if test="${i != page }">
							<a href="owner_reply.do?num=${num }&page=${i }">[${i }]</a>
						</c:if>
					</c:forEach>
				
					<c:if test="${endBlock < allPage }">
						<a href="owner_reply.do?num=${num }&page=${endBlock + 1 }">▶</a>
						<a href="owner_reply.do?num=${num }&page=${allPage }">▶▶</a>
					</c:if>
			</div>
		</section>

		<jsp:include page="../../navi/footer.jsp"/>
	
		</div>
	</div>
</body>
</html>