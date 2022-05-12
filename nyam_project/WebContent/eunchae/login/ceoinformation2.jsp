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
	href="<%=request.getContextPath()%>/css/style.css">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">
	$(function() {

		var regid = /^[a-zA-Z0-9]{6,12}$/;
		var regpwd = /^[a-zA-Z0-9]{4,12}$/;
		var regname = /^[가-힣]{2,8}$/;
		var regemail = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
		var regphone = /^\d{3}-\d{3,4}-\d{4}$/;
		var regceonum = /^(\d{3,3})+[-]+(\d{2,2})+[-]+(\d{5,5})/;
		var shopname = /^[가-힣a-zA-Z0-9]{2,15}$/;
		var regnickname = /^[가-힣0-9]{2,8}$/;
		
		var pwdchecknum1 = 0;
		var pwdchecknum2 = 0;
		var phonechecknum = 1;
		var namechecknum = 1;
		var conumchecknum = 1;

		$("#ceo_pwd1_info").keyup(function() { //-------pwd 정규식검사
			var inputvalue = $("#ceo_pwd1_info").val();
			if (!regpwd.test(inputvalue)) {
				$("#ceo_pwd1_info_check").html("4~12자리 영문 대소문자와 숫자로만 입력하십시오.");
				$("#ceo_pwd1_info_check").css({
					"color" : "red"
				});
				pwdchecknum1 = 0;
			} else {
				$("#ceo_pwd1_info_check").html("성공");
				$("#ceo_pwd1_info_check").css({
					"color" : "blue"
				});
				pwdchecknum1 = 1;
			}
		});

		$("#ceo_pwd2_info").keyup(function() { //-------pwd 확인 유효성검사
			var pwd1 = $("#ceo_pwd1_info").val();
			var pwd2 = $("#ceo_pwd2_info").val();
			if (pwd1 !== pwd2) {
				$("#ceo_pwd2_info_check").html("비밀번호가 틀립니다.");
				$("#ceo_pwd2_info_check").css({
					"color" : "red"
				});
				pwdchecknum2 = 0;

			} else {
				$("#ceo_pwd2_info_check").html("성공");
				$("#ceo_pwd2_info_check").css({
					"color" : "blue"
				});
				pwdchecknum2 = 1;

			}
		});



		$(document)
				.on(
						"keyup",
						"#ceo_phone_info",
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
							var inputvalue = $("#ceo_phone_info").val();

							if (!regphone.test(inputvalue)) {
								$("#ceo_phone_info_check")
										.html("잘못된 전화번호 입니다.");
								$("#ceo_phone_info_check").css({
									"color" : "red"
								});

								phonechecknum = 0;
							} else {
								$("#ceo_phone_info_check").html("성공");
								$("#ceo_phone_info_check").css({
									"color" : "blue"
								});
								phonechecknum = 1;
							}

						});

	
		$("#ceo_conum_info").keyup(
				function() { //-------사업자번호 유효성 검사

					$(this).val(
							$(this).val().replace(/[^0-9]/g, "").replace(
									/(\d{3})(\d{2})(\d{5})/g, "$1-$2-$3")
									.replace("--", "-"));

					var inputvalue = $("#ceo_conum_info").val();

					if (!regceonum.test(inputvalue)) {
						$("#ceo_conum_info_check").html("잘못된 형식 입니다.");
						$("#ceo_conum_info_check").css({
							"color" : "red"
						});
						conumchecknum = 0;
					} else {
						$("#ceo_conum_info_check").html("성공");
						$("#ceo_conum_info_check").css({
							"color" : "blue"
						});
						conumchecknum = 1;
					}
				});


		
		
		$("#ceo_name_info").keyup(function() { //-------name 정규식 검사
			var inputvalue = $("#ceo_name_info").val();
			if (!shopname.test(inputvalue)) {
				$("#ceo_name_info_check").html("잘못된 이름 입니다.");
				$("#ceo_name_info_check").css({
					"color" : "red"
				});
				namechecknum = 0;
			} else {
				$("#ceo_name_info_check").html("성공");
				$("#ceo_name_info_check").css({
					"color" : "blue"
				});
				namechecknum = 1;
			}
		});
		
		
		
		
		$("#okbtn").click(
				function() {
					if ($("#ceo_pwd1_info").val() !== ""
							&& $("#ceo_pwd2_info").val() !== ""
							&& $("#ceo_name_info").val() !== ""
							&& $("#ceo_phone_info").val() !== ""
							&& $("#ceo_conum_info").val() !== "") {
						if (pwdchecknum1 == 0) {
							$("#ceo_pwd1_info").focus();
						} else if (pwdchecknum2 == 0) {
							$("#ceo_pwd2_info").focus();
						}  else if (namechecknum == 0) {
							$("#ceo_name_info").focus();
						} else if (conumchecknum == 0) {
							$("#ceo_conum_info").focus();
						} else if (phonechecknum == 0){
							$("#ceo_phone_info").focus();
						}

					}
				});
		
		$(".infoip").keyup(function() {
			if (pwdchecknum1 == 1 && pwdchecknum2 == 1 && phonechecknum == 1 && namechecknum == 1 && conumchecknum == 1) {
				$(".info_form").attr("onsubmit", "return true");
			} else {
				$(".info_form").attr("onsubmit", "return false");
			}
		});

	});
</script>
</head>
<body>




	<jsp:include page="../include/navi_ceo.jsp"></jsp:include>


	<%-- <jsp:include page="login_main2.jsp"></jsp:include> --%>

	<c:set var="dto" value="${ceodto }" />

	<div class="infobody">
		<form class="row g-3 signup_form info_form" onsubmit="return false"
			action="<%=request.getContextPath()%>/ceo_info_ok.do">
			<div class="col-md-4">
				<label for="validationDefault01" class="form-label">아이디</label> <input
					type="text" class="form-control infoip" id="ceo_id_info"
					value="${dto.getCeo_id() }" disabled>
			</div>
			<div class="checkdiv join_check_id">
				<span class="erorcheck"></span>
			</div>


			<div class="col-md-4">
				<label for="validationDefault02" class="form-label">비밀번호</label> <input
					type="password" class="form-control infoip" id="ceo_pwd1_info"
					name="pwd" value="" required>
			</div>
			<div class="checkdiv join_check_id">
				<span class="erorcheck" id="ceo_pwd1_info_check"></span>
			</div>
			<div class="col-md-4">
				<label for="validationDefault02" class="form-label">비밀번호 확인</label>
				<input type="password" class="form-control infoip"
					id="ceo_pwd2_info" value="" required>
			</div>
			<div class="checkdiv join_check_id">
				<span class="erorcheck" id="ceo_pwd2_info_check"></span>
			</div>
			<div class="col-md-4">
				<label for="validationDefault02" class="form-label">상호명</label> <input
					type="text" class="form-control infoip" id="ceo_name_info" name="name"
					value="${dto.getCeo_name() }" required>
			</div>
			<div class="checkdiv join_check_id">
				<span class="erorcheck" id="ceo_name_info_check"></span>
			</div>
			<div class="col-md-4">
				<label for="validationDefault02" class="form-label">사업자 번호</label> <input
					type="text" class="form-control infoip" id="ceo_conum_info" value="${dto.getCeo_conum() }"
					required maxlength="12"  name = "conum" required>
			</div>
			<div class="checkdiv join_check_id">
				<span class="erorcheck" id="ceo_conum_info_check"></span>
			</div>



			<div class="col-md-4">
				<label for="validationDefault02" class="form-label">휴대폰 번호</label> <input
					type="text" class="form-control phoneNumber infoip"
					id="ceo_phone_info" value="${dto.getCeo_phone() }" maxlength="13"
					name="phone" required>
			</div>
			<div class="checkdiv join_check_id">
				<span class="erorcheck" id="ceo_phone_info_check"></span>
			</div>


			<div class="col-md-4">

				<div class="col-md-4">
					<label for="validationDefault02" class="form-label">이메일</label> <input
						type="text" class="form-control" id="ceo_email_info"
						value="${dto.getCeo_mail() }" disabled>
				</div>
			</div>
			<div class="checkdiv join_check_id">
				<span class="erorcheck" id="ceo_name_info_check"></span>
			</div>


			<div class=" signbutton">
				<div class="col-12">
					<button class="btn btn-primary join_ok" id="okbtn">수정 완료</button>
					<button class="btn btn-primary" type="reset" data-bs-toggle="modal">정정</button>

				</div>

			</div>
		</form>
	</div>







	</div>
	</div>






</body>
</html>