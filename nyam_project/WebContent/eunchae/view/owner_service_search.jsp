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
	
	<section class="right_container">
		<c:set var="list" value="${List }"/>
		<c:set var="num" value="${num }"/>
		<h1 class="title">문의글 검색 페이지</h1>
		<br>
		<a class="ownerSearch" href="owner_cs.do?num=${num }">글 목록으로 ></a>

		<div class="panel panel-primary">
			<div class="panel-heading">Q&A</div>
				<table class="table">
					<tr class="user-info-items">
						<th>글번호</th>
						<th>글제목</th>
						<th>처리상황</th>
					</tr>
			
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
				<input type="hidden" name="service_num" value="${dto.getService_num() }">
				<input type="hidden" name="userId" value="${userId}">
					<tr class="user-info-items">
						<td>${dto.getService_num() }</td>
						<td>	<!-- 제목 클릭시 리뷰페이지로 이동 -->
							<a href="<%=request.getContextPath() %>/owner_cs_content.do?num=${dto.getService_num()}">
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
					<td colspan="3" align="center">
						<h3>검색된 문의글이 없습니다.</h3>
					</td>
				</tr>
			</c:if>
		</table>
		</div>		
		
		<!-- 페이징처리 -->
		<nav class="tac">
	    <ul class="pagination">
	    <li>
	    <c:if test="${page == 1 }">
			<a href="service_cs_search.do?page=${startBlock }&search_field=${search_field }&search_keyword=${search_keyword}" aria-label="Previous">
		</c:if>
		
		<c:if test="${page != 1 }">
			<a href="service_cs_search.do?page=${page - 1 }&search_field=${search_field }&search_keyword=${search_keyword}" aria-label="Previous">
		</c:if>
		<span aria-hidden="true">&laquo;</span>
	    </a>
	    </li>
		
		<li>
		<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
			<c:if test="${i == page }">
				<a href="service_cs_search.do?page=${i }&search_field=${search_field }&search_keyword=${search_keyword}">${i }</a>
			</c:if>
			
			<c:if test="${i != page }">
				<a href="service_cs_search.do?page=${i }&search_field=${search_field }&search_keyword=${search_keyword}">${i }</a>
			</c:if>
		</c:forEach>
		</li>
		
		<li>
		<c:if test="${endBlock == allPage }">
			<a href="service_cs_search.do?page=${endBlock }&search_field=${search_field }&search_keyword=${search_keyword}" aria-label="Next">
		</c:if>
		
		<c:if test="${endBlock != allPage }">
			<a href="service_cs_search.do?page=${page + 1 }&search_field=${search_field }&search_keyword=${search_keyword}" aria-label="Next">
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