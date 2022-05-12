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
	font-size:20px;
	
	
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

<jsp:include page="../include/navi_user.jsp"/>


  
     
     <div class="title">
     <table class="titls" border="0" cellspacing="0">
      	<tr>
      	<c:set var="no" value="${No}"/>
      	
      	<c:if test="${no==1 }">
      		<td>인기 글</td>
      	</c:if>
      	
      	
      	<c:if test="${no==2 }">
      		<td>맛보장된 집</td>
      	</c:if>
		</tr>
		
      
  
		 </table>
		 
		 
		 
		 
		 
	</div>	 
	<br><br><br>
	
	<c:set var="list" value="${List}"/>
	
		<c:if test="${empty list}">
		<span>리뷰 목록이 없습니다.</span><br>
	</c:if>
	
	<c:if test="${!empty list}">
	<table border="0" cellspacing="0">
		
		
		<tr>
			<c:forEach items="${list}" begin="0" end="${list.size()-3 }" var="dto">
			
			
				<td>
					&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/owner_content.do?no=${dto.getReview_ceo_num()}&page=${page}">
						<img src="<%=request.getContextPath()%>/dohyung/upload/${dto.getReview_image()}"
						width="200" height="200"> 
						</a>&nbsp;&nbsp;
				</td>
			</c:forEach>	
			</tr>
			<tr>
				<c:forEach items="${list}" begin="0" end="${list.size()-3 }" var="dto">
				
			
				<td class="yeab">${dto.getReview_title()}</td>
				
				</c:forEach>	
			</tr>	
					
		
		
		
	
			
			</table>
			
			<br><br>
			
			<c:if test="${!empty list}">
			<c:forEach items="${list}" begin="${list.size()-2 }" end="${list.size()-1 }" var="dto">
			
			<table border="0" cellspacing="0">
			<tr>
			<td>
			<a href="<%=request.getContextPath()%>/owner_content.do?no=${dto.getReview_ceo_num()}&page=${page}">
				<img src="<%=request.getContextPath()%>/dohyung/upload/${dto.getReview_image()}"
						width="150" height="150"> 
						</a>
						</td>
			<td>제목 :${dto.getReview_title()}
			<br>평점 :&nbsp;${dto.getReview_rate() }&nbsp;&nbsp;다녀간날 ${dto.getReview_went().substring(0,10) }
			<br>내용 :${dto.getReview_cont() }
			</td>
			</c:forEach>
			</c:if>
			
			
			</c:if>
			
			
			
	
		</tr>
			
	
	
	</table>
	
		<c:if test="${page >block }">
			<a href="review_list.do?page=1&no=${no }">[맨처음]	</a>
			<a href="review_list.do?page=${startBlock -1}&no=${no }">◀</a>
		</c:if>
		<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
			<c:if test="${i==page }">
				<b><a href="review_list.do?page=${i }&no=${no }">[${i }]</a></b>
			
			</c:if>
			<c:if test="${i!=page }">
				<a href="review_list.do?page=${i }&no=${no }">[${i }]</a>
			
			</c:if>
		
		</c:forEach>
		<c:if test="${endBlock < allPage }">
			<a href="review_list.do?page=${endBlock +1 }&no=${no }">▶</a>
			<a href="review_list.do?page=${allPage }&no=${no }">[마지막]</a>
		
		</c:if>
		<br><br>
	
	
	
	
	
	
	
	
	
	
	</div>


	</div>
	
	</div>	




</body>
</html>