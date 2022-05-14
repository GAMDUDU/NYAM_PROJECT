<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
.title{

	background-color:#e0e0e0;
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
</style>
</head>
<body>


<jsp:include page="../../navi/main_navi.jsp"/>

<div class="title">
     <table class="titls" border="0" cellspacing="0">
     <c:set var="field" value="${search_field}"/>
      	<tr>
      	
      	<c:if test="${field=='content'}">
      	
      		<td> 내용 검색 리스트</td>
      		</c:if>
		
		
      
      <c:if test="${field=='rate'}">
      	
      		<td> 별점 검색 리스트</td>
		
		
      </c:if>
      <c:if test="${field=='name'}">
      	
      		<td> 상호명검색 리스트</td>
		
		
      </c:if>
      <c:if test="${field=='adress'}">
      	
      		<td> 위치 검색 리스트</td>
		
		
      </c:if>
  	</tr>
		 </table>
		 
		 <br>
		 
		 
	</div>	 
	<br><br><br>
	
	<c:set var="list" value="${List}"/>
	<table border="0" cellspacing="0">
		<c:if test="${!empty list }">
			<c:forEach items="${list }" var="dto">
			<tr>
			<td>
			<a href="<%=request.getContextPath()%>/owner_contents.do?no=${dto.getCeo_num()}&page=${page}">
				<img src="<%=request.getContextPath()%>/dohyung/upload/${dto.getCeo_image()}"
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
			<c:if test="${empty list }">
			<tr>
			<td colspan = "2" align="center">
			<h3>검색된 게시물 리스트가 없습니다..</h3>
			</td>
			</c:if>
	
		</tr>
			
	
	
	</table>
	
		<c:if test="${page >block }">
			<a href="owner_search.do?page=1&search_field=${search_field }&search_keyword=${search_keyword}">[맨처음]	</a>
			<a href="owner_search.do?page=${startBlock -1 }&search_field=${search_field }&search_keyword=${search_keyword}">◀</a>
		</c:if>
		<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
			<c:if test="${i==page }">
				<b><a href="owner_search.do?page=${i }&search_field=${search_field }&search_keyword=${search_keyword}">[${i }]</a></b>
			
			</c:if>
			<c:if test="${i!=page }">
				<a href="owner_search.do?page=${i }&search_field=${search_field }&search_keyword=${search_keyword}">[${i }]</a>
			
			</c:if>
		
		</c:forEach>
		<c:if test="${endBlock < allPage }">
			<a href="owner_search.do?page=${endBlock +1 }&search_field=${search_field }&search_keyword=${search_keyword}">▶</a>
			<a href="owner_search.do?page=${allPage }&search_field=${search_field }&search_keyword=${search_keyword}">[마지막]</a>
		
		</c:if>
		<br><br>
	
	

		 
		 
	</div>
	
	</div>	






</div>

</body>
</html>