<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css">
<style type="text/css">



	.title{

	
	width:400px;
	padding-left:200px;
	margin-top:30px;
	margin-left:50px;
	}
	
	.titls{
	
	width:300px;
	}
	
	.titls td{
	font-size:32px;
	
	
	}
	
	
	.cont {
	margin-top:30px;
	padding-left:200px;
	}


.yeab{
	
	text-align: center;
}


</style>

</head>
<body>

<jsp:include page="../../navi/main_navi.jsp"/>

		<c:set var="list" value="${List}"/>
	
		<c:if test="${empty list}">
		<span>리뷰 목록이 없습니다.</span><br>
	</c:if>
	

 <div class="title">
     <table class="titls" border="0" cellspacing="0">
      	<tr>
      	
      		<td>맛집 둘러보기</td>
		</tr>
		
      
  
		 </table>
		 <br>
		 
		 
		 
	</div>	 
	
	<br>
	<br>
	<br>

		
<c:set var="list" value="${List }"/>
<c:if test="${!empty list}">

			
			<c:forEach items="${list}" var="dto">
			
			<table border="0" cellspacing="0">
			<tr>
			<td>
			<a href="<%=request.getContextPath()%>/owner_contents.do?no=${dto.getCeo_num()}&page=${page}">
				<img src="<%=request.getContextPath()%>/image/ceoimage/${dto.getCeo_image()}"
				
						width="150" height="150"> 
						</a>
						</td>
			<td>&nbsp;식당이름 :${dto.getCeo_name()}
			<br>&nbsp;평점 :${dto.getCeo_avgrate() }
			<br>&nbsp;위치 :${dto.getCeo_addr() }
			<br>&nbsp;내용 :${dto.getCeo_cont() }
			</td>
			</c:forEach>
			</c:if>
			
			
			
			
			
	
		</tr>
			
	
	
	</table>
	
		<c:if test="${page >block }">
			<a href="owner_list.do?page=1">[맨처음]	</a>
			<a href="owner_list.do?page=${startBlock -1}">◀</a>
		</c:if>
		<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
			<c:if test="${i==page }">
				<b><a href="owner_list.do?page=${i }">[${i }]</a></b>
			
			</c:if>
			<c:if test="${i!=page }">
				<a href="owner_list.do?page=${i }&no=">[${i }]</a>
			
			</c:if>
		
		</c:forEach>
		<c:if test="${endBlock < allPage }">
			<a href="owner_list.do?page=${endBlock +1 }">▶</a>
			<a href="owner_list.do?page=${allPage }">[마지막]</a>
		
		</c:if>
		
		<br><br>
		
		<div class="cont">
		
		
	
	
		<form method="post" action="<%=request.getContextPath() %>/owner_search.do">
		
		
		<select name="search_field">
			<option value="name" select> 식당이름</option>
			<option value="adress"> 위치</option>
			<option value="rate"> 별점</option>
			<option value="content"> 내용</option>
			
			
			
		
		</select>
		<input name="search_keyword">
		<input type="submit" value="검색">
		
		</form>
		</div>
		
		</div>
	
	</div>	
		

</body>
</html>