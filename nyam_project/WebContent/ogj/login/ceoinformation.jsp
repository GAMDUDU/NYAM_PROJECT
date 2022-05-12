<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
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

$(function(){
	

	
});

</script>
</head>
<body>




	<jsp:include page="../include/navi.jsp"></jsp:include>


	

	<div class = "infobody">
	<form class="row g-3 signup_form" action="<%=request.getContextPath()%>/ceo_pwd_confirm_ok.do">

						<div class="col-md-4">
							<label for="validationDefault02" class="form-label">비밀번호</label>
							<input type="password" class="form-control" id="ceo_pwd_confirm"
								value="" name = "pwd" required>
						</div>
						<div class="checkdiv join_check_id">
							<span class="erorcheck" id="ceo_pwd_confirm_check"></span>
						</div>


						<div class=" signbutton">
							<div class="col-12">
								<button class="btn btn-primary join_ok" type="submit">확인</button>
								<button class="btn btn-primary" type="reset"
									 data-bs-toggle="modal">취소</button>

							</div>
						</div>
					</form>
	</div>







	</div>
	</div>






</body>
</html>