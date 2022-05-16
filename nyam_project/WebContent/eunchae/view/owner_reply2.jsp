<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/eunchae/cssCeo/title.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/eunchae/cssCeo/ownerBtn.css">
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic:400,700,800&amp;subset=korean" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/eunchae/cssCeo/table.css">

<link rel="stylesheet" href="/css/bootstrap.css">
<script type="text/javascript" src="/js/bootstrap.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> <!-- 부가적인 테마 --> <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="../../navi/main_navi.jsp"/>
		<section class="right_container">
			<h1 class="title">내 가게 리뷰 / 댓글</h1>

			<div class="panel panel-primary">
				<div class="panel-heading">리뷰목록</div>
				<br>
				<table class="table">
					<tr class="user-info-header">
						<th>글번호</th>
						<th>리뷰내용</th>
						<th>작성일</th>
					</tr>
					
					<c:set var="list" value="${List }"/>
					<c:if test="${!empty list }">
						<c:forEach items="${list }" var="dto">
						<input type="hidden" name="review_no" value="${dto.getReview_num() }">
						<input type="hidden" name="userId" value="${userId}">
							<tr>
								<td>${dto.getReview_num() }</td>
								<td>	<!-- 제목 클릭시 리뷰페이지로 이동 -->
									<a href="<%=request.getContextPath()%>/owner_contents.do?no=${dto.getReview_ceo_num() }&rno=${dto.getReview_num()}" class="reviewcont">
										${dto.getReview_cont() }
									</a>
								</td>
								<td>${dto.getReview_date().substring(0, 10) }</td>
							</tr>
						</c:forEach>
					</c:if>
					
					<c:if test="${empty list }">
						<tr class="user-info-items">
							<td colspan="5" align="center">
								<h3>작성된 리뷰가 없습니다.</h3>
							</td>
						</tr>
					</c:if>
				</table>
			</div>
				
			<nav class="tac">
				<ul class="pagination">
				<li>
					<c:if test="${page == 1 }">
						<a href="owner_reply2.do?id=${user_id }&page=${startBlock }" aria-label="Previous">
					</c:if>
					
					 <c:if test="${page != 1 }">
				      	<a href="owner_reply2.do?id=${user_id }&page=${page -1 }" aria-label="Previous">
				     </c:if>
				        <span aria-hidden="true">&laquo;</span>
				      </a>
				<%-- <c:if test="${page > block}">
						<a href="owner_reply.do?num=${num }&page=1">◀◀</a>
						<a href="owner_reply.do?num=${num }&page=${startBlock - 1 }">◀</a>
					</c:if> --%>
				</li>
				
				<li>
					<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
						<c:if test="${(i == page)}">
							<a href="owner_reply2.do?id=${user_id }&page=${i }">${i }</a>
						</c:if>
						
						<c:if test="${i != page }">
							<a href="owner_reply2.do?id=${user_id }&page=${i }">${i }</a>
						</c:if>
					</c:forEach>
				</li>
				
		<%-- 		<li>
					<c:if test="${endBlock < allPage }">
						<a href="owner_reply.do?num=${num }&page=${endBlock + 1 }">▶</a>
						<a href="owner_reply.do?num=${num }&page=${allPage }">▶▶</a>
					</c:if>
				</li> --%>
				
				<li>
					<c:if test="${endBlock == allPage }">
						<a href="owner_reply2.do?id=${user_id }&page=${endBlock}" aria-label="Next">
					</c:if>
					
					<c:if test="${endBlock != allPage }">	
						<a href="owner_reply2.do?id=${user_id }&page=${page + 1 }"  aria-label="Next"></a>
					</c:if>
					<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
			</ul>
		</nav>
		
			<div class="panel panel-primary">
				<div class="panel-heading">댓글목록</div>
				<br>
				<table class="table">
					<tr class="user-info-header">
						<th>댓글</th>
						<th>댓글내용</th>
						<th>작성일</th>
					</tr>
					
					<c:set var="Plist" value="${PList }"/>
					<c:if test="${!empty Plist }">

						<c:forEach items="${Plist }" var="dto">
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
										<a href="<%=request.getContextPath()%>/owner_contents.do?no=${dto.getReply_ceo_num() }&rno=${dto.getReply_review_num()}">
											${dto.getReply_cont() }
										</a>
									</td>
									<td>${dto.getReply_date().substring(0, 10) }</td>
								</tr>
						</c:forEach>
					</c:if>
					
					<c:if test="${empty Plist }">
						<tr class="user-info-items">
							<td colspan="5" align="center">
								<h3>작성된 댓글이 없습니다.</h3>
							</td>
						</tr>
					</c:if>
				</table>
			</div>
				
			<nav class="tac">
			<ul class="pagination">
				<li>
					<c:if test="${Ppage == 1}">
						<a href="owner_reply2.do?id=${user_id }&Ppage=${PstartBlock }" aria-label="Previous">
					</c:if>
					
					<c:if test="${page != 1 }">
				      	<a href="owner_reply2.do?id=${user_id }&Ppage=${Ppage -1 }" aria-label="Previous">
				    </c:if>
				    <span aria-hidden="true">&laquo;</span>
      					</a>
				</li>
				
				<li>
					<c:forEach begin="${PstartBlock }" end="${PendBlock }" var="j">
						<c:if test="${(j == Ppage) }">
							<a href="owner_reply2.do?id=${user_id }&Ppage=${j }">${j }</a>
						</c:if>
						
						<c:if test="${j != Ppage }">
							<a href="owner_reply2.do?id=${user_id }&Ppage=${j }">${j }</a>
						</c:if>
					</c:forEach>
				</li>
				
				<li>
					<c:if test="${PendBlock == PallPage }">
						<a href="owner_reply2.do?id=${user_id }&Ppage=${PendBlock}" aria-label="Next"> 
					</c:if>
					
					<c:if test="${PendBlock != PallPage }">
						<a href="owner_reply2.do?id=${user_id }&Ppage=${Ppage + 1 }" aria-label="Next">
					</c:if>
						<span aria-hidden="true">&raquo;</span> 
					</a>
				</li>
			</ul>
		</nav>
		</section>
		
		<jsp:include page="../../navi/footer.jsp"/>
		
		</div>
	</div>
</body>
</html>