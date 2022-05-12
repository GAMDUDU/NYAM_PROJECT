<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

table{
	width: 100%;
    display: inline-table;
}
.user-info-header {background:#eee;}
.user-info-header th{padding:10px;}
.user-info-items td{padding:8px;}
.user-info-items:hover{background:#eee;}

.membercontent{
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
 			<td colspan="4" class="cont" 
					style="padding: 10px; background: #ccc;">
 				<h3 align="center">고객문의답변</h3>
 			</td>
 		</tr>

		<c:set var="dto" value="${reply }"/>
		 <form method="post" action="<%=request.getContextPath() %>/service_reply_ok.do">
 				
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
 				
 		
 		

 		
 		 		<tr class="user-info-items">
	 			<td colspan="4" align="center">
	 			
	 			<input type="submit" value="확인">
				<input type="reset" value="다시작성"> 
	 			<input type="button" value="전체문의목록"
	 				onclick="location.href='service_Management.do'">
	 			
	 			</td>
	 		</tr>
 		</form>  
 	</table>
 	
 </div>
</body>
</html>