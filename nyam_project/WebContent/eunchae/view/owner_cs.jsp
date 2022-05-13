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
	<c:set var="num" value="${num }"/>
		<section class="right_container">
			<div class="service">
				<h1 class="title">고객센터</h1>
				<h2>Q&A</h2>
				<a href="<%=request.getContextPath() %>/owner_cs_write.do">문의하러 가기></a>
				<br><br>
				<table width="800">
					<tr>
						<th>글번호</th>
						<th>글제목</th>
						<th>처리상황</th>
					</tr>
					
					<c:set var="list" value="${List }"/>
					<c:if test="${!empty list }">
						<c:forEach items="${list }" var="dto">
						<input type="hidden" name="Service_num" value="${dto.getService_num() }">
						<input type="hidden" name="userId" value="${userId}">
							<tr>
								<td>${dto.getService_num() }</td>
								<td>	<!-- 제목 클릭시 리뷰페이지로 이동 -->
									<c:if test="${dto.getService_indent() != 0 }">
										<c:forEach begin="1" end="${dto.getService_indent() }">
											└
										</c:forEach>
									</c:if>
									<a href="<%=request.getContextPath() %>/review_content.do?num=${dto.getService_num() }">
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
							<td colspan="5" align="center">
								<h3>작성된 문의글이 없습니다.</h3>
							</td>
						</tr>
					</c:if>
				</table>
				<br><br>
				
				<div class="paging">
					<c:if test="${page > block}">
						<a href="owner_cs.do?num=${num }&page=1">◀◀</a>
						<a href="owner_cs.do?num=${num }&page=${startBlock - 1 }">◀</a>
					</c:if>
				
					<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
						<c:if test="${(i == page)}">
							<b><a href="owner_cs.do?num=${num }&page=${i }">[${i }]</a></b>
						</c:if>
						
						<c:if test="${i != page }">
							<a href="owner_cs.do?num=${num }&page=${i }">[${i }]</a>
						</c:if>
					</c:forEach>
				
					<c:if test="${endBlock < allPage }">
						<a href="owner_cs.do?num=${num }&page=${endBlock + 1 }">▶</a>
						<a href="owner_cs.do?num=${num }&page=${allPage }">▶▶</a>
					</c:if>
					<br><br>
				</div>
						
				<div class="searchForm">
					<form method="post" action="<%=request.getContextPath() %>/service_cs_search.do">
						<select name="search_field" class="searchSelect">
							<option value="title">제목</option>
							<option value="content">내용</option>
							<option value="title_content">제목 + 내용</option>
						</select>
						
						<input type="text" name="search_keyword" class="searchInput">
						<input type="submit" value="검색" class="submitBtn">
					</form>
				</div>
			</div>
		</section>
	</div>
	</div>
</body>
</html>