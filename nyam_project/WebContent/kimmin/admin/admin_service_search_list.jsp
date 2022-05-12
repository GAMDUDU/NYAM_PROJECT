<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>



<style type="text/css">
.title {
	background-color: #e0e0e0;
	width: 400px;
	margin: 0 auto;
	padding: 0 10px;
	margin-top: 30px;
	text-align: center;
}

.titls td {
	font-size: 20px;
}

table {
	width: 100%;
	display: inline-table;
}

.user-info-header {
	background: #eee;
}

.user-info-header th {
	padding: 10px;
}

.user-info-items td {
	padding: 8px;
}

.user-info-items:hover {
	background: #eee;
}

.membercontent {
	display: none;
}
</style>
</head>
<body>
	<jsp:include page="../include/admin_navi.jsp" />

	<div class="title">
		<h2>관리자 페이지</h2>
	</div>

	<div style="text-align: center; margin-top: 20px;">
		<table border="1" cellspacing="0" width="400"
			style="margin: 0 auto; margin-top: 15px;">
			<tr>
				<td colspan="8" class="cont"
					style="padding: 10px; background: #ccc;">
					<h3 align="center">고객센터</h3>
				</td>
			</tr>

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
						<a href="<%=request.getContextPath() %>/service_cont.do?num=${dto.getService_num() }">
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
	
	<div style="text-align: center; margin-top: 15px;">
		<c:if test="${page > block }">
			<a href="service_search.do?page=1&search_field=${search_field }&search_keyword=${search_keyword }">◀◀</a> <!-- 1페이지로 가라 -->
			<a href="service_search.do?page=${startBlock -1 }&search_field=${search_field }&search_keyword=${search_keyword }">◀</a><!-- 이전페이지로 가라  -->
		</c:if>		

		<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
			<c:if test="${i == page }">
				<b><a href="service_search.do?page=${i }&search_field=${search_field }&search_keyword=${search_keyword }">[${i }]</a></b>
			</c:if>
			
			<c:if test="${i != page }">
				<a href="service_search.do?page=${i }&search_field=${search_field }&search_keyword=${search_keyword }">[${i }]</a>
			</c:if>
		</c:forEach>

		<c:if test="${endBlock < allPage }">
			<a href="service_search.do?page=${endBlock + 1 }&search_field=${search_field }&search_keyword=${search_keyword }">▶</a>
			<a href="service_search.do?page=${allPage }&search_field=${search_field }&search_keyword=${search_keyword }">▶▶</a>
		</c:if>
		

			
		<form method="post" action="<%=request.getContextPath() %>/service_search.do" style="margin-top:8px;">
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
			onclick="location.href='service_Management.do?page=1'">		
		</form>

	</div>
	
	</div> <!-- include -->
	</div> <!-- include -->
	</div> <!-- include end -->

</body>
</html>