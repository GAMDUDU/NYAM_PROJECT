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
 				<h3 align="center">고객문의내용</h3>
 			</td>
 		</tr>
 		

 		
 		 <c:set var="dto" value="${content }"/>
 		 <form method="post" action="<%=request.getContextPath() %>/service_reply.do?num=${dto.getService_num() }">
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
 		
 		
 		 		<tr class="user-info-items">
	 			<td colspan="4" align="center">
	 			
	 			<input type="submit" value="답변글달기">
	 			
	 			<input type="button" value="문의글삭제"
	 				onclick="if(confirm('문의글을 삭제하시겠습니까?')){
	 				location.href='service_delete.do?num=${dto.getService_num() }'
	 				}else{ return; }">

	 			
	 			</td>
	 		</tr>
 		</form>  
 	</table>
 	
 </div>

</body>
</html>