<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/ogj/css/style.css">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.6.0.js"></script>
	<script type="text/javascript">
	
	
	</script>
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="../include/navi.jsp"></jsp:include>
	<button type="button" onclick="<%=request.getContextPath()%>/inquiry_go.do">문의하기</button>
	<button type="button" onclick="<%=request.getContextPath()%>/inquiry_view.do">내 문의내역</button>
	
		</div>
	</div>

</body>
</html>