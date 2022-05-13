<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<script type="text/javascript" src="js/jquery-3.6.0.js"></script>
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

.user-button-items input{
	font-weight: bold;
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

 		<c:set var="dto" value="${content }"/> 
 			<form method="post" enctype="multipart/form-data"
 				action="<%=request.getContextPath() %>/admin_review_update.do?num=${dto.getReview_num() }">
 					<c:if test="${!empty content }">	
 						<tr class="user-info-header">
 							<th>가게번호</th>
 								<td class="cont">${dto.getReview_ceo_num() }</td>
 							<th>리뷰번호</th>	
 								<td class="cont">${dto.getReview_num() }</td>
 							<th>작성자 아이디</th>	
 								<td class="cont">${dto.getReview_id() }</td>
 						</tr>
 			
 			<tr class="user-info-items">
 			<th>작성일</th>
 			<td class="cont">${dto.getReview_date() }</td>

 			<th>다녀간날짜</th>	
 			<td class="cont">${dto.getReview_went() }</td>
 			
 				<th>리뷰이미지</th>
 					<td class="cont">
 					<img src="<%=request.getContextPath() %>/upload/${dto.getReview_image() } "
 									width="190" height="120">
 						<input type="hidden" name="image_new">
          				<input type="hidden" name="image_old"
          				            value="${dto.getReview_image() }">
          				</td>
 			
 			</tr>
 			
 			
 			
 			<tr class="user-info-items">
 					<th>리뷰제목</th>	
 					<td class="cont" colspan="5">${dto.getReview_title() }</td>


 			</tr>
 			
 			<tr class="user-info-items">
 					 <th>리뷰내용</th>	
 					<td class="cont" colspan="5">${dto.getReview_cont() }</td>
 					 					

 					
 			</tr>
 					
 					<tr class="user-info-items">

 					<th>평점</th>					
 					<td class="cont">${dto.getReview_rate() }</td>
 					<th>좋아요</th>	
 					<td class="cont">${dto.getReview_like() }</td>
 					<th>신고</th>	
 					<td class="cont">
 					<input name="bad" value="${dto.getReview_bad() }"></td>
 					
 					



 				</tr>
 			</c:if>
 			
 		<c:if test="${empty content }">
 			<tr class="user-info-items">
 				<td colspan="11" class="cont">
 					<h3 align="center">작성된 리뷰가 없습니다.</h3>
 				</td>
 			</tr>
 		</c:if>
 		
 		 <tr class="user-button-items">
	 		<td colspan="11" align="center">
	 			
	 			<input type="submit" class="btn btn-default navbar-btn" value="리뷰 수정">
	 			
	 			<input type="button" class="btn btn-default navbar-btn" value="리뷰 삭제"
	 				onclick="if(confirm('리뷰를 삭제하시겠습니까?')){
	 				location.href='admin_review_delete.do?no=${dto.getReview_num() }'
	 				}else{ return; }">
	 			 <input type="submit" class="btn btn-default navbar-btn" value="전체리뷰목록"
	 				onclick="location.href='admin_review_Management.do'">
	 			</td>
	 		</tr>
 		</form> 
 			</table>
 </div>

</body>
</html>