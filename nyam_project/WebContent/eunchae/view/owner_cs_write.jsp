<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/eunchae/cssCeo/title.css">
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic:400,700,800&amp;subset=korean" rel="stylesheet">
</head>
<body>
	<jsp:include page="../include/navi_ceo.jsp"/>
	
	<section class="right_container">
		<h1 class="title">문의하기</h1>
		<form method="post" action="<%=request.getContextPath() %>/owner_cs_write_ok.do">
			<table>
				<tr>
					<th>글 제목</th>
					<td><input name="title"></td>
				</tr>
				
				<tr>
					<th>문의 내용</th>
					<td><textarea rows="20" cols="85" name="cont"></textarea></td>
				</tr>
				
				<tr>
					<td colspan="2">
						<input type="submit" class="submitBtn" value="작성하기">
					</td>
				</tr>
			</table>
		</form>
	</section>
	
	</div>
	</div>
</body>
</html>