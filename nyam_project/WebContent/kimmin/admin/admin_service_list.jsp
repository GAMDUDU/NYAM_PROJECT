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


</head>
<body>
	<jsp:include page="../../navi/main_navi.jsp"/>

	<div class="main_title">

	</div>

	
		<div class="panel panel-primary">
		 	<div class="panel-heading">고객센터</div>
 		<table class="table">

			<tr class="user-info-header">
				<th>글번호</th>
				<th>문의글제목</th>
				<th>작성자</th>
				<th>문의종류</th>
			</tr>

			<c:set var="list" value="${servicelist }" />
			<c:if test="${!empty servicelist }">
				<c:forEach items="${servicelist }" var="dto">
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
			
			
			<c:if test="${empty servicelist }">
				<tr class="user-info-items">
					<td colspan="4" class="cont">
						<h3 align="center">작성된 문의내역이 없습니다.</h3>
					</td>
				</tr>
			</c:if>
		</table>
	</div>
	
	<%--페이징 --%>
	<nav class="tac">
  <ul class="pagination">
    <li>
     <c:if test="${page == 1 }">
     	<a href="admin_service_Management.do?page=${startBlock }" aria-label="Previous">
     </c:if>
     
     <c:if test="${page != 1 }">
      	<a href="admin_service_Management.do?page=${page -1 }" aria-label="Previous">
     </c:if>
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
 
     <li>

    	<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
			<c:if test="${i == page }">
				<a href="admin_service_Management.do?page=${i }">${i }</a>
			</c:if>
			
			<c:if test="${i != page }">
				<a href="admin_service_Management.do?page=${i }">${i }</a>
			</c:if>
			
		</c:forEach>
	</li>
 

  <li>
     <c:if test="${endBlock == allPage }">
     	<a href="admin_service_Management.do?page=${endBlock }" aria-label="Next">
     </c:if>
     
     <c:if test="${endBlock != allPage }">
      	<a href="admin_service_Management.do?page=${page + 1 }" aria-label="Next">
     </c:if>
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
   
  </ul>
  
</nav>

	<%--페이징 --%>
			
		<form method="post" action="<%=request.getContextPath() %>/admin_service_search.do" class="tac">
			<select class="sel" name="search_field">
				<option value="name">작성자</option>
				<option value="member">사용자문의</option>
				<option value="ceo">사장님문의</option>
				<option value="review">리뷰문의</option>
				<option value="etc">기타문의</option>
			</select>

			<input class="searchInput" name="search_keyword">
			<input class="btn btn-primary" type="submit" value="검색">		
		</form>

	</div>
	
	</div> <!-- include -->
	</div> <!-- include -->
	</div> <!-- include end -->
	
</body>
</html>