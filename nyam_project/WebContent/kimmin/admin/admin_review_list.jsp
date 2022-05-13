<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	
	<div class="panel-heading">회원정보</div>
		<table class="table">

			<tr class="user-info-header">
				<th>리뷰번호</th>
				<th>리뷰제목</th>
				<th>리뷰작성자</th>
				<th>리뷰작성일</th>
				<th>리뷰평점</th>
				<th>좋아요</th>
				<th>싫어요</th>

			</tr>

			<c:set var="list" value="${reviewlist }" />
			<c:if test="${!empty reviewlist }">
				<c:forEach items="${reviewlist }" var="dto">
					<tr class="user-info-items">
						<td class="cont">${dto.getReview_num() }</td>
						
						<td class="cont">
							<a href="<%=request.getContextPath() %>/admin_review_cont.do?num=${dto.getReview_num() }">
								${dto.getReview_title() }</a></td>
								
						<td class="cont">${dto.getReview_id() }</td>
						<td class="cont">${dto.getReview_date() }</td>
						<td class="cont">${dto.getReview_rate() }</td>
						<td class="cont">${dto.getReview_like() }</td>
						<td class="cont">${dto.getReview_bad() }</td>
					</tr>
					
				</c:forEach>


			</c:if>

			<c:if test="${empty reviewlist }">
				<tr class="user-info-items">
					<td colspan="8" class="cont">
						<h3 align="center">작성된 리뷰가 없습니다</h3>
					</td>
				</tr>
			</c:if>
		</table>
	</div>



	<nav>
  <ul class="pagination">
    <li>
     <c:if test="${page == 1 }">
     	<a href="admin_review_Management.do?page=${startBlock }" aria-label="Previous">
     </c:if>
     
     <c:if test="${page != 1 }">
      	<a href="admin_review_Management.do?page=${page-1 }" aria-label="Previous">
     </c:if>
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
 
     <li>

    	<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
			<c:if test="${i == page }">
				<a href="admin_review_Management.do?page=${i }">${i }</a>
			</c:if>
			
			<c:if test="${i != page }">
				<a href="admin_review_Management.do?page=${i }">${i }</a>
			</c:if>
			
		</c:forEach>
	</li>
 

  <li>
     <c:if test="${endBlock == allPage }">
     	<a href="admin_review_Management.do?page=${endBlock }" aria-label="Next">
     </c:if>
     
     <c:if test="${endBlock != allPage }">
      	<a href="admin_review_Management.do?page=${page + 1 }" aria-label="Next">
     </c:if>
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
   
  </ul>
  
</nav>


		<form method="post" action="<%=request.getContextPath()%>/admin_review_search.do" style="margin-top:8px;">
			<select name="search_field">
				<option value="title">제목</option>
				<option value="id">아이디</option>
			</select>
			<input name="search_keyword"> <input type="submit" value="검색">
		</form>


	</div><!-- include-->
	</div><!-- include-->
	</div><!-- include end-->
</body>
</html>