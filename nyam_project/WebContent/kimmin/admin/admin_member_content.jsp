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
    width: 860px;
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

	<jsp:include page="../../navi/main_navi.jsp"/>

	<div class="title">
		
	</div>
	
	<div class="panel panel-primary">
	
	<div class="panel-heading">회원정보</div>
  
 	<table class="table">
 	
 		<form method="post" 
 			action="<%=request.getContextPath() %>/admin_member_update.do">
 		<c:set var="dto" value="${content }"/>
 			<c:if test="${!empty content }">
 			
 				<tr class="user-info-items">
 				<th>회원번호</th> 
 					<td class="cont"><input name="num" value="${dto.getMember_num() }" ></td>
 				<th>회원이름</th>
 				 	<td class="cont"><input name="name" value="${dto.getMember_name() }" readonly></td>
 				<th>회원아이디</th>	
 					<td class="cont"><input name="id" value="${dto.getMember_id() }" readonly></td>
 				</tr>
 				
 				<tr class="user-info-items">
 				<th>회원비밀번호</th>
 					<td class="cont"><input name="pwd" value="${dto.getMember_pwd() }" readonly></td>
 				<th>회원연락처</th>	
 					<td class="cont"><input name="phone" value="${dto.getMember_phone() }"></td>
 				<th>회원메일</th>
 					<td class="cont"><input name="mail" value="${dto.getMember_mail() }"></td>
 				</tr>
 				
 				<tr class="user-info-items">
 				<th>회원가입일</th>		
 					<td class="cont"><input name="date" value="${dto.getMember_date() }" readonly></td>
 				<th>회원닉네임</th>		
 					<td class="cont"><input name="nickname" value="${dto.getMember_nickname() }"></td>
 				<th>차단</th>		
 					
 					<td class="cont">
 				 	<select name="block">

 				 	<option value="n" <c:if test="${dto.getMember_block() == 'n' }">selected</c:if>>사용중</option>
 				 	<option value="y" <c:if test="${dto.getMember_block() == 'y' }">selected</c:if>>차단</option>

 				 	</select>
 				 </td>

 				</tr>
 		</c:if>
 		
 		
 		
 		
 		
 		
 		
 		<c:if test="${empty content }">
 			<tr class="user-info-items">
 				<td colspan="9" class="cont">
 					<h3 align="center">가입된 회원이 없습니다</h3>
 				</td>
 			</tr>
 		</c:if>
 	 					
	 
	 	<tr class="user-button-items">
	 		<td colspan="9" align="center">

	 			<input type="submit" class="btn btn-default navbar-btn" value="회원정보 수정">
	 			<input type="button" class="btn btn-default navbar-btn" value="회원 정보 삭제"
	 				onclick="if(confirm('회원을 삭제하시겠습니까?')){
	 				location.href='admin_member_delete.do?no=${dto.getMember_num() }'
	 				}else{ return; }">
	 			<input type="submit" class="btn btn-default navbar-btn" value="전체회원목록" 
	 				onclick="location.href='admin_member_Management.do'">
	 			
	 			</td>
	 		</tr>
 		</form>  
 	</table>
 </div>

</body>
</html>