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
	
	<div class="panel-heading">고객문의 답변</div>
  
 	<table class="table">

		<c:set var="dto" value="${reply }"/>
		 <form method="post" action="<%=request.getContextPath() %>/admin_service_reply_ok.do">
 				
 				<input type="hidden" name="num" value="${dto.getService_num() }">
 				<input type="hidden" name="service_group" value="${dto.getService_group() }">
 				<input type="hidden" name="service_step" value="${dto.getService_step() }">
 				<input type="hidden" name="service_indent" value="${dto.getService_indent() }">
 		
 			 	<tr class="user-info-items">
 					<th>문의글번호</th>
 					<td class="cont">${dto.getService_num() } </td>
 					
					<th>작성자</th>
					<td> <input name="writer"
							value="${dto.getService_name() }" ></td>
 					
				</tr>
 				
 				<tr class="user-info-items">
 				 <th>문의글종류</th>	 
 				 <td class="cont"> 
 				 <select name="code">
 				 	<option value="사용자문의"
 				 	<c:if test="${dto.getService_code() == '사용자문의' }">selected</c:if>>사용자문의</option>
 				 	
 				 	<option value="사장님문의"
 				 	<c:if test="${dto.getService_code() == '사장님문의' }">selected</c:if>>사장님문의</option>
 				 	
 				 	<option value="리뷰문의"
 				 	<c:if test="${dto.getService_code() == '리뷰문의' }">selected</c:if>>리뷰문의</option>
 				 	
 				 	<option value="기타문의"
 				 	<c:if test="${dto.getService_code() == '기타문의' }">selected</c:if>>기타문의</option>
 				 	</select>
 				 	</td>
 				 
 				 <th>비밀번호</th>	
 				<td class="cont"> <input name="pwd"
							value="${dto.getService_pwd() }" ></td>
 				</tr>
 				
 		
 				
 				
 				<tr class="user-info-items">
 				<th>문의글제목</th>
 				<td class="cont"> <input name="title"
							value="${dto.getService_title() }" ></td> 
											
 				 <th>답변상태</th>	
 				 <td class="cont">
 				 <select name="check">
 				 	<option value="문의중"
 				 	<c:if test="${dto.getService_check() == '문의중' }">selected</c:if>>문의중</option>
 				 	
 				 	<option value="답변완료"
 				 	<c:if test="${dto.getService_check() == '답변완료' }">selected</c:if>>답변완료</option>
 				 	</select>
 				 </td>
 				
 				</tr>
 				
 				<tr class="user-info-items">
 				<th>문의내용</th>
 				<td colspan="3">
 				<textarea colspn="3" rows="20" cols="80" name="content">${dto.getService_cont() }</textarea>
 				</td>
 				</tr>
 				
 		
 		

 		
 		 		<tr class="user-button-items">
	 			<td colspan="4" align="center">
	 			
	 			<input type="submit" class="btn btn-default navbar-btn" value="확인">
				<input type="reset" class="btn btn-default navbar-btn" value="다시작성"> 
	 			<input type="button" class="btn btn-default navbar-btn"value="전체문의목록"
	 				onclick="location.href='admin_service_Management.do'">
	 			
	 			</td>
	 		</tr>
 		</form>  
 	</table>
 	
 </div>
</body>
</html>