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


.searchInput{
	width: 250px;
	height: 30px;
	border-radius: 15px;
	border: 1px solid black;
	padding-left: 15px;
}

</style>
</head>
<body>
	<jsp:include page="../include/admin_navi.jsp" />

	<div class="main_title">
		
	</div>


	<div class="panel panel-primary">
	
	<div class="panel-heading">회원정보</div>

	<table class="table">
			<tr class="user-info-header">
				<th>회원번호</th>
				<th>회원이름</th>
				<th>회원아이디</th>
				<th>회원닉네임</th>
				<th>회원연락처</th>
				

			</tr>
			<c:set var="list" value="${memberlist }" />
			<c:if test="${!empty memberlist }">
				<c:forEach items="${memberlist }" var="dto">
					<tr class="user-info-items">
						
						<!-- <td class="cont"> <input name="num" value="${dto.getMember_num() }"></td> -->
						<td class="cont">${dto.getMember_num() }</td>
						
						<td class="cont">${dto.getMember_name() }</td>
						<td class="cont">
							<a href="<%=request.getContextPath() %>/admin_member_cont.do?num=${dto.getMember_num() }">
								${dto.getMember_id() }</a>
						</td>
						
						<td class="cont">${dto.getMember_nickname() }</td>
						<td class="cont">${dto.getMember_phone() }</td>
					</tr>
					
				</c:forEach>
			</c:if>
			<c:if test="${empty memberlist }">
				<tr class="user-info-items">
					<td colspan="8" class="cont">
						<h3 align="center">가입된 회원이 없습니다</h3>
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
     	<a href="admin_member_Management.do?page=${startBlock }" aria-label="Previous">
     </c:if>
     
     <c:if test="${page != 1 }">
      	<a href="admin_member_Management.do?page=${page -1 }" aria-label="Previous">
     </c:if>
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
 
     <li>

    	<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
			<c:if test="${i == page }">
				<a href="admin_member_Management.do?page=${i }">${i }</a>
			</c:if>
			
			<c:if test="${i != page }">
				<a href="admin_member_Management.do?page=${i }">${i }</a>
			</c:if>
			
		</c:forEach>
	</li>
 

  <li>
     <c:if test="${endBlock == allPage }">
     	<a href="admin_member_Management.do?page=${endBlock }" aria-label="Next">
     </c:if>
     
     <c:if test="${endBlock != allPage }">
      	<a href="admin_member_Management.do?page=${page + 1 }" aria-label="Next">
     </c:if>
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
   
  </ul>
  
</nav>

	<%--페이징 --%>
	

			
		<form method="post" action="<%=request.getContextPath() %>/admin_member_search.do">
			<select name="search_field">
				<option value="name">이름</option>
				<option value="id">아이디</option>
				<option value="nickname">닉네임</option>
			</select>

			<input class="searchInput" name="search_keyword">
			<input type="submit" value="검색" class="btn btn-primary">	
		
		</form>

	</div>
	
	</div> <!-- include -->
	</div> <!-- include -->
	</div> <!-- include end -->
	
	

</body>
</html>