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

</style>

</head>
<body>

<jsp:include page="../include/admin_navi.jsp" />

<div class="title">
		
	</div>
	
	<div class="panel panel-primary">
	
	<div class="panel-heading">고객문의 내용</div>
  
 	<table class="table">
 		

 		
 		 <c:set var="dto" value="${content }"/>
 		 <form method="post" action="<%=request.getContextPath() %>/admin_service_reply.do?num=${dto.getService_num() }">
 		 <c:if test="${!empty content }">
 		
 			 	<tr class="user-info-items">
 					<th>문의글번호</th>
 					<td class="cont">${dto.getService_num() } </td>
 					
 					<th>작성자</th>
 					<td class="cont">${dto.getService_name() }</td>

 				</tr>
 				
 				<tr class="user-info-items">
 				 <th>문의글종류</th>	 
 				 <td class="cont">${dto.getService_code() } </td>
 				  					
				 <th>비밀번호</th>	
 					<td class="cont" >${dto.getService_pwd() }</td>

 		
 				</tr>
 				
 				<tr class="user-info-items">
 				<th>문의글제목</th>
 				<td class="cont">${dto.getService_title() } </td>
 				
 				<th>답변상태</th>	
 				<td class="cont">${dto.getService_check() } </td>
 				</tr>
 				
 				<tr class="user-info-items">
 				<th>문의내용</th>
 				<td colspan="3">
 				<textarea  colspn="3" rows="20" cols="80">${dto.getService_cont() }</textarea>
 				</td>
 				</tr>
 				
 		
 			</c:if>
 		
 		 		<c:if test="${empty content }">
 			<tr class="user-info-items">
 				<td colspan="6" class="cont">
 					<h3 align="center">등록된 문의사항이 없습니다.</h3>
 				</td>
 			</tr>
 		</c:if>
 		
 		
 		 		<tr class="user-button-items">
	 			<td colspan="4" align="center">
	 			
	 			<input type="submit" class="btn btn-default navbar-btn" value="답변글달기">
	 			
	 			<input type="button" class="btn btn-default navbar-btn" value="문의글삭제"
	 				onclick="if(confirm('문의글을 삭제하시겠습니까?')){
	 				location.href='admin_service_delete.do?num=${dto.getService_num() }'
	 				}else{ return; }">

	 			
	 			</td>
	 		</tr>
 		</form>  
 	</table>
 	
 </div>

</body>
</html>