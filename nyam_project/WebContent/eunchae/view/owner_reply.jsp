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
			<h1 class="title">내 가게 리뷰 / 댓글</h1>

			<div class="review">
				<h1>작성된 리뷰</h1>
				<br>
				<table width="800">
					<tr>
						<th>글번호</th>
						<th>리뷰제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>신고하기</th>
					</tr>
					
					<c:set var="list" value="${List }"/>
					<c:if test="${!empty list }">
						<c:forEach items="${list }" var="dto">
						<input type="hidden" name="review_num" value="${dto.getReview_num() }">
						<input type="hidden" name="userId" value="${userId}">
							<tr>
								<td>${dto.getReview_num() }</td>
								<td>	<!-- 제목 클릭시 리뷰페이지로 이동 -->
									<a href="<%=request.getContextPath()%>/review_mycontent.do?no=${dto.getReview_num() }">
										${dto.getReview_title() }
									</a>
								</td>
								<td>${dto.getReview_id() }</td>
								<td>${dto.getReview_date().substring(0, 10) }</td>
								<td>
									<c:if test="${dto.getReview_bad() == 0 }">
										<a href="<%=request.getContextPath() %>/owner_report.do?num=${dto.getReview_ceo_num()}&review_no=${dto.getReview_num()}"><button class="tableBtn">신고하기</button></a>
									</c:if>
									
									<c:if test="${dto.getReview_bad() == 1 }">
										<button class="tableBtn">신고됨</button>
									</c:if>
									
									<c:if test="${dto.getReview_bad() == 2 }">
										<button class="tableBtn">삭제됨</button>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					
					<c:if test="${empty list }">
						<tr>
							<td colspan="5" align="center">
								<h3>작성된 리뷰가 없습니다.</h3>
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
			<br><br><br>
			
			
			
			<div class="comment">
				<h1>작성된 댓글</h1>
				<br>
				<table width="800">
					<tr>
						<th>리뷰/홍보글</th>
						<th>댓글내용</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>신고하기</th>
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
										<a href="#">
											${dto.getReply_cont() }
										</a>
									</td>
									<td>${dto.getReply_id() }</td>
									<td>${dto.getReply_date().substring(0, 10) }</td>
									<td>
										<c:if test="${dto.getReply_bad() == 0 }">
										<a href="<%=request.getContextPath() %>/owner_preport.do?num=${dto.getReply_ceo_num()}&review_no=${dto.getReply_review_num()}&reply_no=${dto.getReply_num()}"><button>신고하기</button></a>
									</c:if>
									
									<c:if test="${dto.getReply_bad() == 1 }">
										<button class="tableBtn">신고됨</button>
									</c:if>
									
									<c:if test="${dto.getReply_bad() == 2 }">
										<button class="tableBtn">삭제됨</button>
									</c:if>
									</td>
								</tr>
						</c:forEach>
					</c:if>
					
					<c:if test="${empty Plist }">
						<tr>
							<td colspan="5" align="center">
								<h3>작성된 댓글이 없습니다.</h3>
							</td>
						</tr>
					</c:if>
				</table>
				<br>
				
				<c:if test="${Ppage > Pblock}">
					<a href="owner_reply.do?num=${num }&Ppage=1">◀◀</a>
					<a href="owner_reply.do?num=${num }&Ppage=${PstartBlock - 1 }">◀</a>
				</c:if>
			
				<c:forEach begin="${PstartBlock }" end="${PendBlock }" var="j">
					<c:if test="${(j == Ppage) }">
						<b><a href="owner_reply.do?num=${num }&Ppage=${j }">[${j }]</a></b>
					</c:if>
					
					<c:if test="${j != Ppage }">
						<a href="owner_reply.do?num=${num }&Ppage=${j }">[${j }]</a>
					</c:if>
				</c:forEach>
			
				<c:if test="${PendBlock < PallPage }">
					<a href="owner_reply.do?num=${num }&Ppage=${PendBlock + 1 }">▶</a>
					<a href="owner_reply.do?num=${num }&Ppage=${PallPage }">▶▶</a>
				</c:if>
			</div>
		</section>
		</div>
	</div>
</body>
</html>