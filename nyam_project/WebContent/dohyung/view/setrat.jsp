<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
    
    <% 
		int no=Integer.parseInt(request.getParameter("no").trim());
    %>
    



    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="../style/sty.css" rel="stylesheet" type="text/css" />


    
    


<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.js"></script>

</head>
<body>




<c:set var="no" value="${cont}"/>
		<form method="post" action="<%=request.getContextPath()%>/rate_write_ok.do?cnum=${no}">
	<table border="0" cellspacing="0" width="350">
<tr>

		
		
			
		
		



<tr>

		
		
		
				<th>평점</th>
				<td>
				<td><input type="number" name="rate"min="1" max="5" value="5"></td>
				
				<!-- <form name="myform" id="myform" method="post">
			    <fieldset>
			        <legend>이모지 별점</legend>
			        <input type="radio" name="rating" value="5" id="rate1"><label for="rate1">⭐</label>
			        <input type="radio" name="rating" value="4" id="rate2"><label for="rate2">⭐</label>
			        <input type="radio" name="rating" value="3" id="rate3"><label for="rate3">⭐</label>
			        <input type="radio" name="rating" value="2" id="rate4"><label for="rate4">⭐</label>
			        <input type="radio" name="rating" value="1" id="rate5"><label for="rate5">⭐</label>
			    </fieldset>
			</form>
</td> -->

	</tr>
	<tr>			
	
	<td><input type="submit" value="별점 등록" >
	
	</td>
	</tr>
	
	
	</table>
	</form>
	
	
	

</body>
</html>