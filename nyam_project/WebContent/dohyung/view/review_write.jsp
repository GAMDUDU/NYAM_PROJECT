<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  
 


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

 <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">  
  <script src="https://code.jquery.com/jquery-1.12.4.js">  </script> 
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> 
 



<script> 
	$(function() { 
		
		$("#datepicker").datepicker({ 
			numberOfMonths: 1 , 
			showWeek: true , 
			firstDay: 1 , 
			dateFormat:"yy/mm/dd" , 
			prevText: '이전 달' , 
			nextText: '다음 달' , 
			monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', 
				'8월', '9월', '10월', '11월', '12월'] , 
				monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'] , dayNames: ['일', '월', '화', '수', '목', '금', '토'] , dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'] , dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'] , showMonthAfterYear: true , yearSuffix: '년' }); 
	}); 
</script>

<style type="text/css">
.cont {
	margin-top:100px;
	padding-left:80px;
	}
	
#title{
	padding-left:200px;
}

</style>
</head>
<body>

<jsp:include page="../../navi/main_navi.jsp"/>
	<div class="cont">
	
		<hr width="65%" color="red">
		<div id="title">
			<h3>리뷰등록 폼 페이지</h3>
		</div>
		<hr width="65%" color="red">
		<br>
		<br>
		
		
		<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath() %>/review_inputOk.do">
		
		<c:set var="dto" value="${cnum }"/>
		
			<%-- <input type="hidden" id="cnum" name="cnum" value="${dto.getReview_ceo_num() }"> 
			이것도 이상..
			 --%>
			 
			 <input type="hidden" id="cnum" name="cnum" value="${cnum }"> 
			<table border="0" cellspacing="0" width="800">
			
			
			<tr>
				<th>리뷰제목</th>
				<td><input name="rtitle"></td>
			</tr>
			
			<tr>
				<th>리뷰내용</th>
				<td><textarea rows="7" cols="30" name="rcont"></textarea></td>
			</tr>
			
			
			
			<tr>
				<th>리뷰이미지</th>
				<td><input type="file" name="rimage"></td>
			</tr>
			
			<tr>
				<th>평점</th>
				<td><input type="number" name="rate"min="1" max="5" value="5"></td>
			</tr>
			
			<tr>
				<th>다녀간 날짜</th>
				<td><input type="date" name="went" id="datepicker"></td>
			</tr>

			

			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="리뷰등록">&nbsp;&nbsp;&nbsp;
					<input type="reset" value="다시작성">
				
				</td>
			
			</tr>

			
			</table>
			</form>
			</div>
			</div>
			</div>
			

</body>
</html>