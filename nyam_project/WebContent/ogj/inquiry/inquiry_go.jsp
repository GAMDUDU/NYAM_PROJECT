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
<link href="<%=request.getContextPath()%>/ogj/file/summernote.css"
	rel="stylesheet">
	
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.1/js/bootstrap.min.js"></script> 
<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet">


<script type="text/javascript">
	$(function() {
		$('.summernote').summernote({
			height : 400,
			lang : 'ko-KR'
		});
		
		
		$("#resetarea").click(function(){
			$('.summernote').summernote('reset');
		});
	});
</script>
<title>Insert title here</title>
</head>
<body> 

	 <jsp:include page="../include/navi.jsp"></jsp:include> 
	

	 <div class="inquiry_go_body" style="border: 1px solid gray">
		<form class="row g-3 info_form" onsubmit="return false"
			action="<%=request.getContextPath()%>/ogj/inquiry_go_ok.do"
			style = "margin-left : 0; margin-right : 0">
			<div class="col-md-4" style="width: 90%">
				<label for="validationDefault01" class="form-label">제목</label> <input
					type="text" class="form-control" style="width: 90%" required>
			</div>
 





			<div class="checkdiv join_check_id">
				<span class="erorcheck"></span>
			</div>
			<div class="textarea">

             <textarea class="summernote" name="contents"></textarea>
			</div>


			<div class=" signbutton">
				<div class="col-12">
					<button class="btn btn-primary join_ok" id="okbtn">문의하기</button>
					<button class="btn btn-primary" type="reset"  id="resetarea" data-bs-toggle="modal">정정</button>
					
				</div>

			</div>
		</form>
	</div>

	</div>
	</div>




</body>

<script src="<%=request.getContextPath()%>/ogj/file/summernote.min.js"></script>

</html>