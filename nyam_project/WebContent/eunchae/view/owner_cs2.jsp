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
<link rel="stylesheet" href="<%=request.getContextPath()%>/eunchae/cssCeo/ownerBtn.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/eunchae/cssCeo/table.css">

<link rel="stylesheet" href="/css/bootstrap.css">
<script type="text/javascript" src="/js/bootstrap.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> <!-- 부가적인 테마 --> <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

</head>
<body>
	<jsp:include page="../../navi/main_navi.jsp"/>
	<c:set var="num" value="${num }"/>
		<section class="right_container">
			<h1 class="title">고객센터</h1>
			<br>
			<a class="ownerSearch" href="<%=request.getContextPath() %>/owner_cs_write2.do">문의하러 가기></a>
			
			<div class="panel panel-primary">
			<div class="panel-heading">Q&A</div>
			<table class="table">
				<tr class="user-info-items">
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
								<a href="<%=request.getContextPath() %>/owner_cs_content.do?num=${dto.getService_num() }">
									${dto.getService_title() }
								</a>
							</td>
							<td>
								<c:if test="${dto.getService_check().equals('확인중') }">
									<button class="ownerBtn" id="doneBtn">확인중</button>
								</c:if>
								
								<c:if test="${dto.getService_check().equals('답변완료') }">
									<button class="ownerBtn" id="doneBtn">답변완료</button>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				
				<c:if test="${empty list }">
					<tr class="user-info-items">
						<td colspan="5" align="center">
							<h3>작성된 문의글이 없습니다.</h3>
						</td>
					</tr>
				</c:if>
			</table>
		</div>
			
		<nav class="tac">
 				<ul class="pagination">
   			<li>	
				<c:if test="${page == 1 }">
					<a href="owner_cs.do?num=${num }&page=${startBlock }" aria-label="Previous">
				</c:if>
				
				<c:if test="${page != 1 }">	
					<a href="owner_cs.do?num=${num }&page=${page -1 }" aria-label="Previous">
				</c:if>
				<span aria-hidden="true">&laquo;</span>
		      </a>
		    </li>
			
			<li>
				<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
					<c:if test="${(i == page)}">
						<a href="owner_cs.do?num=${num }&page=${i }">${i }</a>
					</c:if>
					
					<c:if test="${i != page }">
						<a href="owner_cs.do?num=${num }&page=${i }">${i }</a>
					</c:if>
				</c:forEach>
			</li>
			
			<li>
				<c:if test="${endBlock == allPage }">
					<a href="owner_cs.do?num=${num }&page=${endBlock }" aria-label="Next">
				</c:if>
				
				<c:if test="${endBlock != allPage }">
					<a href="owner_cs.do?num=${num }&page=${page + 1 }" aria-label="Next">
				</c:if>
		        <span aria-hidden="true">&raquo;</span>
		      </a>
			</li>
		</ul>
		</nav>
					
			<div class="searchForm">
				<form method="post" action="<%=request.getContextPath() %>/service_cs_search.do">
					<select name="search_field" class="searchSelect">
						<option value="title">제목</option>
						<option value="content">내용</option>
						<option value="title_content">제목 + 내용</option>
					</select>
					
					<input type="text" name="search_keyword" class="searchInput">
					<input type="submit" value="검색" class="btn btn-primary">
				</form>
			</div>
	</section>
		
	<jsp:include page="../../navi/footer.jsp"/>
		
	</div>
	</div>
</body>
</html>