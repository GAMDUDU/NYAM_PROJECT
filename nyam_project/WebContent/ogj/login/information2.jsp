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
	
	var regpwd = /^[a-zA-Z0-9]{4,12}$/;
	var regphone = /^\d{3}-\d{3,4}-\d{4}$/;
	var regnickname = /^[가-힣0-9]{2,8}$/;
	var pwd1 = 0;
	var pwd3 = 0;
	var phone = 1;
	var nickname = 0;
	
	
	
	$("#user_pwd1_info").keyup(function() { //-------pwd 정규식검사
		var inputvalue = $("#user_pwd1_info").val();
		if (!regpwd.test(inputvalue)) {
			$("#user_pwd1_info_check").html("4~12자리 영문 대소문자와 숫자로만 입력하십시오.");
			$("#user_pwd1_info_check").css({
				"color" : "red"
			});
			pwd1 = 0;
		} else {
			$("#user_pwd1_info_check").html("✔");
			$("#user_pwd1_info_check").css({
				"color" : "blue"
			});
			pwd1 = 1;
		}
	});
	

	
	$("#user_nickname_info").keyup(function() { //-------닉네임 정규식 검사
		var inputvalue = $("#user_nickname_info").val();
		if (!regnickname.test(inputvalue)) {
			$("#user_nickname_info_check").html("2~8자리 한글,숫자 조합으로 입력하시오..");
			$("#user_nickname_info_check").css({
				"color" : "red"
			});
			nickname = 0;
		} else {
			$("#user_nickname_info_check").html("✔");
			$("#user_nickname_info_check").css({
				"color" : "blue"
			});
			nickname = 1;
		}
	});

	$("#user_pwd2_info").keyup(function() { //-------pwd 확인 유효성검사
		var pwd1 = $("#user_pwd1_info").val();
		var pwd2 = $("#user_pwd2_info").val();
		if (pwd1 !== pwd2) {
			$("#user_pwd2_info_check").html("비밀번호가 틀립니다.");
			$("#user_pwd2_info_check").css({
				"color" : "red"
			});
			pwd3 = 0;
			
		} else {
			$("#user_pwd2_info_check").html("✔");
			$("#user_pwd2_info_check").css({
				"color" : "blue"
			});
			pwd3 = 1;
			
		}
	});
	
	$(".infoip").keyup(function(){
		if(pwd1 == 1 && pwd3 == 1 && phone == 1){
			$(".info_form").attr("onsubmit" , "return true");
		}else {
			$(".info_form").attr("onsubmit" , "return false");
		}
	});
	
	

	
	$(document)
	.on(
			"keyup",
			"#user_phone_info",
			function() {
				$(this)
						.val(
								$(this)
										.val()
										.replace(/[^0-9]/g, "")
										.replace(
												/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/,
												"$1-$2-$3")
										.replace("--", "-"));
				var inputvalue = $("#user_phone_info").val();

				if (!regphone.test(inputvalue)) {
					$("#user_phone_info_check").html("잘못된 전화번호 입니다.");
					$("#user_phone_info_check").css({
						"color" : "red"
					});

					phone = 0;
				} else {
					$("#user_phone_info_check").html("✔");
					$("#user_phone_info_check").css({
						"color" : "blue"
					});
					phone = 1;
				}
				
				

			});
	
	
	$("#okbtn").click(function(){
		if ($("#user_pwd1_info").val() !== "" && $("#user_pwd2_info").val() !== ""){
			if(pwd1 == 0){
				$("#user_pwd1_info").focus();
			}else if (pwd3 == 0){
				$("#user_pwd2_info").focus();
			}else if (phone == 0){
				$("#user_phone_info").focus();
			}else if (nickname == 0){
				$("#user_nickname_info").focus();
			}else {
				var pwd1 = $("#user_pwd1_info").val();
				var nickname = $("#user_nickname_info").val();
				var phone = $("#user_phone_info").val();
				
				
				
				
			}
			
			
			
			}
	});
	 
});

</script>
</head>
<body>




	<jsp:include page="../../navi/main_navi.jsp"/>
	
	<h1>고객 정보 수정</h1>


	<%-- <jsp:include page="login_main2.jsp"></jsp:include> --%>
	
	<c:set var="dto" value="${userdto }"/>

	<div class = "infobody">
	<form class="row g-3 signup_form info_form" onsubmit="return false" action="<%=request.getContextPath()%>/user_info_ok.do">
						<div class="col-md-4">
							<label for="validationDefault01" class="form-label">아이디</label> <input
								type="text" class="form-control infoip" id="user_id_info" value="${dto.getMember_id() }"
								 disabled>
						</div>
												<div class="checkdiv join_check_id">
							<span class="erorcheck"></span>
						</div>
												<div class="col-md-4">
							<label for="validationDefault01" class="form-label">닉네임</label> <input
								type="text" class="form-control infoip" id="user_nickname_info" value="${dto.getMember_nickname() }"
								 required>
						</div>
												<div class="checkdiv join_check_nickname">
							<span class="erorcheck" id="user_nickname_info_check"></span>
						</div>


						<div class="col-md-4">
							<label for="validationDefault02" class="form-label">비밀번호</label>
							<input type="password" class="form-control infoip" id="user_pwd1_info" name = "pwd"
								value="" required>
						</div>
						<div class="checkdiv join_check_id">
							<span class="erorcheck" id="user_pwd1_info_check"></span>
						</div>
						<div class="col-md-4">
							<label for="validationDefault02" class="form-label">비밀번호
								확인</label> <input type="password" class="form-control infoip"
								id="user_pwd2_info" value="" required>
						</div>
						<div class="checkdiv join_check_id">
							<span class="erorcheck" id="user_pwd2_info_check"></span>
						</div>
						<div class="col-md-4">
							<label for="validationDefault02" class="form-label">이름</label> <input
								type="text" class="form-control" id="user_name_info" value="${dto.getMember_name() }"
								 disabled>
						</div>
												<div class="checkdiv join_check_id">
							<span class="erorcheck" ></span>
						</div>

						<div class="col-md-4">
							<label for="validationDefault02" class="form-label">휴대폰
								번호</label> <input type="text" class="form-control phoneNumber  infoip"
								id="user_phone_info" value="${dto.getMember_phone() }" maxlength="13" name = "phone" required>
						</div>
						<div class="checkdiv join_check_id">
							<span class="erorcheck" id="user_phone_info_check"></span>
						</div>


						<div class="col-md-4">

						<div class="col-md-4">
							<label for="validationDefault02" class="form-label">이메일</label> <input
								type="text" class="form-control" id="user_email_info" value="${dto.getMember_mail() }"
								 disabled>
						</div>
						</div>
												<div class="checkdiv join_check_id">
							<span class="erorcheck" ></span>
						</div>


						<div class=" signbutton">
							<div class="col-12">
								<button class="btn btn-primary join_ok" id = "okbtn">수정 완료</button>
								<button class="btn btn-primary" type="reset"
									 data-bs-toggle="modal">정정</button>

							</div>

						</div>
					</form>
	</div>







	</div>
	</div>



<jsp:include page="../../navi/footer.jsp" />


</body>
</html>