<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="icon" href="favicon.ico">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/main.css">
<link rel="stylesheet" href="/css/bootstrap.css">
<script type="text/javascript" src="js/jquery-3.6.0.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> <script type="text/javascript" src="/js/bootstrap.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS --> <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> <!-- 부가적인 테마 --> <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css"> <!-- 제이쿼리 --> <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> <!-- 합쳐지고 최소화된 최신 자바스크립트 --> <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>



<style type="text/css">
.title {
	background-color: #e0e0e0;
	width: 400px;
	margin: 0 auto;
	padding: 0 10px;
	margin-top: 30px;
	text-align: center;
}

</style>
</head>
<body>
	<jsp:include page="../include/admin_navi.jsp" />

	<div class="title">
		
	</div>

	<div class="panel panel-primary">

		<div class="panel-heading">고객센터 검색내역</div>
		<table class="table">

			<tr class="user-info-header">
				<th>글번호</th>
				<th>문의글제목</th>
				<th>작성자</th>
				<th>문의종류</th>
			</tr>

			<c:set var="list" value="${List }" />
			<c:if test="${!empty List }">
				<c:forEach items="${List }" var="dto">
					<tr class="user-info-items">
						
						<td class="cont">${dto.getService_num() }</td>
						
						<td>
						<%-- 댓글인 경우 --%>
						<c:if test="${dto.getService_indent() !=0 }">
							<c:forEach begin="1" end="${dto.getService_indent() }">
								<img src="kimmin/image/reply.png" width="10" height="10">
							</c:forEach>
						</c:if>
						
						<%-- 원글인 경우 --%>
						<a href="<%=request.getContextPath() %>/admin_service_cont.do?num=${dto.getService_num() }">
									${dto.getService_title() }</a></td>
									
						<td class="cont">${dto.getService_name() }</td>
						<td class="cont">${dto.getService_code() }</td>
					</tr>
					
				</c:forEach>
			</c:if>
			
			
			<c:if test="${empty List }">
				<tr class="user-info-items">
					<td colspan="4" class="cont">
						<h3 align="center">검색된 문의가 없습니다.</h3>
					</td>
				</tr>
			</c:if>
		</table>
	</div>
	
	<%--페이징 --%>
	<nav>
		<ul class="pagination">
			<li>
			
			<c:if test="${page == 1 }">
					<a href="admin_service_search.do?page=${startBlock }&search_field=${search_field }&search_keyword=${search_keyword }"
						aria-label="Previous">
				</c:if> 
				
				<c:if test="${page != 1 }">
					<a href="admin_service_search.do?page=${page -1 }&search_field=${search_field }&search_keyword=${search_keyword }"
						aria-label="Previous">
				</c:if>
				
				<span aria-hidden="true">&laquo;</span> </a>
			</li>





			<li><c:forEach begin="${startBlock }" end="${endBlock }" var="i">
					<c:if test="${i == page }">
						<a href="admin_service_search.do?page=${i }&search_field=${search_field }&search_keyword=${search_keyword }">${i }</a>
					</c:if>

					<c:if test="${i != page }">
						<a href="admin_service_search.do?page=${i }&search_field=${search_field }&search_keyword=${search_keyword }">${i }</a>
					</c:if>
				</c:forEach></li>




			<li>
			<c:if test="${endBlock == allPage }">
				<a href="admin_service_search.do?page=${endBlock }&search_field=${search_field }&search_keyword=${search_keyword }"
					aria-label="Next">
			</c:if> 
				
			<c:if test="${endBlock != allPage }">
				<a href="admin_service_search.do?page=${page +1 }&search_field=${search_field }&search_keyword=${search_keyword }"
					aria-label="Next">
			</c:if> <span aria-hidden="true">&raquo;</span> </a>
			</li>


		</ul>

	</nav>

<%--페이징 --%>

			
		<form method="post" action="<%=request.getContextPath() %>/admin_service_search.do" style="margin-top:8px;">
			<select name="search_field">
				<option value="name">작성자</option>
				<option value="member">사용자문의</option>
				<option value="ceo">사장님문의</option>
				<option value="review">리뷰문의</option>
				<option value="etc">기타문의</option>
			</select>



			<input name="search_keyword">
			<input type="submit" value="검색">
			
			<input type="button" value="전체목록"
			onclick="location.href='admin_service_Management.do?page=1'">		
		</form>

	</div>
	
	</div> <!-- include -->
	</div> <!-- include -->
	</div> <!-- include end -->

</body>
</html>