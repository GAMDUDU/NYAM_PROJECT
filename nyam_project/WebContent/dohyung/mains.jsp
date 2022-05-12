<%@page import="com.kdh.review.model.ReviewDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.kdh.review.model.ReviewDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%

	ReviewDAO dao = ReviewDAO.getInstance();

	List<ReviewDTO> list = dao.getReviewList(1, 15);
	
	request.setAttribute("List", list);


%>
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
	font-size:20px;
	
	
	}
	
	
	.cont {
	margin-top:30px;
	padding-left:200px;
	}

</style>


</head>
<body>

<jsp:include page="include/navi_user.jsp"/>

  
     
     <div class="title">
     <table class="titls" border="0" cellspacing="0">
      	<tr>
      	
      		<td>맛집 리스트</td>
		</tr>
		
      
  
		 </table>
		 
		 
		 
	</div>	 
	

	
	
	
	
	
	
	<div class="cont">
		
		
	
	
		<form method="post" action="<%=request.getContextPath() %>/review_search.do">
		
		
		<select name="search_field">
			<option value="title_content" select> 내용 </option>
			<option value="adress"> 위치</option>
			<option value="rate"> 별점</option>
			
			
		
		</select>
		<input name="search_keyword">
		<input type="submit" value="검색">
		
		</form>
		</div>
	
	
	<br>
	<br>
	<br>
	
	
	<c:set var="list" value="${List}"/>
	
	<c:if test="${empty list}">
		<span>리뷰 목록이 없습니다.</span><br>
	</c:if>
	
	
	<c:if test="${!empty list}">
	<table border="1" cellspacing="0" >
		
		<tr>
		<c:forEach items="${list }" var="dto">
			<c:set var="count" value="${count+1 }"/>
			<td>
				<a href="<%=request.getContextPath()%>/owner_content.do?no=${dto.getReview_ceo_num()}">
				
				<img src="<%=request.getContextPath()%>/dohyung/upload/${dto.getReview_image()}"
						width="150" height="150"> 
						
						</a>
						</td>
		<c:if test="${count%5==0 }">				
		
		</tr>
		<tr>
	</c:if>
	
	</c:forEach>				
	
	
	
		
			
	
	
	
	
	
	</table>
	<!-- 'review_write.do?page=${page}'"> -->
	</c:if>
	

	
		
	
	<br>
	
	
		<br><br>
	
	
	

		 
		 
	</div>
	
	</div>	






</div>

</body>
</html>