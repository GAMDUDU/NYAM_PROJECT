<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<div align="center">
		<jsp:include page="include/navi_user.jsp"/>
		<form method="post" action="<%=request.getContextPath() %>/owner_login_ok.do">
			<table>
				<tr>
					<th>업주 아이디</th>
					<td><input name="owner_id"></td>
				</tr>
				
				<tr>
					<th>업주 비밀번호</th>
					<td><input type="password" name="owner_pwd"></td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="로그인">&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>