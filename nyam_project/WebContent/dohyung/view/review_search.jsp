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
	
	.dd{
	font-size:25px;
	font-style: bold;
	}
	
</style>
</head>
<body>


<jsp:include page="../../navi/main_navi.jsp"/>

<div class="title">
     <table class="titls" border="0" cellspacing="0">
     <c:set var="field" value="${search_field}"/>
      	<tr>
      	
      	<c:if test="${field=='title_content'}">
      	
      		<td> 내용 검색 리스트</td>
		</tr>
		
      </c:if>
      <c:if test="${field=='rate'}">
      	
      		<td> 별점 검색 리스트</td>
		</tr>
		
      </c:if>
  
		 </table>
		 
		 
		 
		 
	</div>	 
	<br><br><br>
	
	<c:set var="list" value="${List}"/>
	<table border="0" cellspacing="0">
		<c:if test="${!empty list }">
			<c:forEach items="${list }" var="dto">
			<tr>
			<td>
			<a href="<%=request.getContextPath()%>/owner_contents.do?no=${dto.getReview_ceo_num()}&page=${page}">
				<img src="<%=request.getContextPath()%>/dohyung/upload/${dto.getReview_image()}"
						width="150" height="150"> 
						</a>
						</td>
			<td><div class="dd">제목 :${dto.getReview_title()}</div>
			<br>식당이름:${dto.getCeo_name() }
			<br>위치:${dto.getCeo_addr() }
			<br>평점 :&nbsp;${dto.getReview_rate() }(${dto.getCeo_avgrate()})&nbsp;&nbsp;다녀간날: ${dto.getReview_went().substring(0,10) }
			<br>내용 :${dto.getReview_cont() }
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
			<a href="review_searchs.do?page=1&search_field=${search_field }&search_keyword=${search_keyword}">[맨처음]	</a>
			<a href="review_searchs.do?page=${startBlock -1 }&search_field=${search_field }&search_keyword=${search_keyword}">◀</a>
		</c:if>
		<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
			<c:if test="${i==page }">
				<b><a href="review_searchs.do?page=${i }&search_field=${search_field }&search_keyword=${search_keyword}">[${i }]</a></b>
			
			</c:if>
			<c:if test="${i!=page }">
				<a href="review_searchs.do?page=${i }&search_field=${search_field }&search_keyword=${search_keyword}">[${i }]</a>
			
			</c:if>
		
		</c:forEach>
		<c:if test="${endBlock < allPage }">
			<a href="review_searchs.do?page=${endBlock +1 }&search_field=${search_field }&search_keyword=${search_keyword}">▶</a>
			<a href="review_searchs.do?page=${allPage }&search_field=${search_field }&search_keyword=${search_keyword}">[마지막]</a>
		
		</c:if>
		<br><br>
	
	

		 
		 
	</div>
	
	</div>	






</div>

</body>
</html>