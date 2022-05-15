$(function() {

		var check = $("#check").html();
		$("#checktest").html("");
		var usersearchURL = "<%=request.getContextPath()%>/ogj/data/SearchUserId.jsp";
		var searchUser = 1;

		$("button[name='user']").click(function() {
			$(".text-muted a").attr("data-bs-target", "#staticBackdrop2");
			$(".login_main button").attr("onclick" , "$().login()");
			$().reset();
			$(".user-choice").css({
				"border" : "1px solid #919191",
				"border-bottom" : "0px",
				"background-color" : "white"
			});
			$(".ceo-choice").css({
				"border" : "1px solid #ddd",
				"border-bottom" : "1px solid #919191",
				"border-left" : "1px solid #91919",
				"background-color" : "#ededed"
			});
			usersearchURL = "<%=request.getContextPath()%>/ogj/data/SearchUserId.jsp";

		});
		$("button[name='ceo']").click(function() {
			$(".text-muted a").attr("data-bs-target", "#staticBackdrop3");
			$(".login_main button").attr("onclick" , "$().ceologin()");
			
			$().reset();
			$(".ceo-choice").css({
				"border" : "1px solid #919191",
				"border-bottom" : "0px",
				"background-color" : "white"
			});
			$(".user-choice").css({
				"border" : "1px solid #ddd",
				"border-bottom" : "1px solid #919191",
				"border-right" : "1px solid #91919",
				"background-color" : "#ededed"
			});
			usersearchURL = "<%=request.getContextPath()%>/ogj/data/SearchCeoId.jsp";
		});
		$("button[name='useridSearch']").click(function() {

			$().reset();
			$(".id_search").css({
				"border" : "1px solid #919191",
				"border-bottom" : "0px",
				"background-color" : "white"
			});
			$(".pwd_search").css({
				"border" : "1px solid #ddd",
				"border-bottom" : "1px solid #919191",
				"border-left" : "1px solid #91919",
				"background-color" : "#ededed"
			});
			$(".searchIdDiv").css({
				"display" : "block"
			});
			$(".searchPwdDiv").css({
				"display" : "none"
			});
			$("#searchinput1").attr("required", true);
			$("#searchinput2").attr("required", false);
			if (usersearchURL == "<%=request.getContextPath()%>/ogj/data/SearchUserPwd.jsp") {
				usersearchURL = "<%=request.getContextPath()%>/ogj/data/SearchUserId.jsp";
			} else if (usersearchURL == "<%=request.getContextPath()%>/ogj/data/SearchCeoPwd.jsp") {
				usersearchURL = "<%=request.getContextPath()%>/ogj/data/SearchCeoId.jsp";
			}
			searchUser = 1;

		});
		$("button[name='userpwdSearch']").click(function() {
			$().reset();
			$(".pwd_search").css({
				"border" : "1px solid #919191",
				"border-bottom" : "0px",
				"background-color" : "white"
			});

			$(".id_search").css({
				"border" : "1px solid #ddd",
				"border-bottom" : "1px solid #919191",
				"border-right" : "1px solid #91919",
				"background-color" : "#ededed"
			});
			$(".searchPwdDiv").css({
				"display" : "block"
			});
			$(".searchIdDiv").css({
				"display" : "none"
			});
			$("#searchinput1").attr("required", false);
			$("#searchinput2").attr("required", true);
			if (usersearchURL == "<%=request.getContextPath()%>/ogj/data/SearchUserId.jsp") {
				usersearchURL = "<%=request.getContextPath()%>/ogj/data/SearchUserPwd.jsp";
			} else if (usersearchURL == "<%=request.getContextPath()%>/ogj/data/SearchCeoId.jsp") {
				usersearchURL = "<%=request.getContextPath()%>/ogj/data/SearchCeoPwd.jsp";
			}
			searchUser = 2;
		});

		//-----------------------------회원가입 정규식 유효성 검사--------------------------
		/* var regid = /^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]|.*[0-9]).{8,24}$/; */
		var regid = /^[a-zA-Z0-9]{6,12}$/;
		var regpwd = /^[a-zA-Z0-9]{4,12}$/;
		var regname = /^[가-힣]{2,8}$/;
		var regemail = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
		var regphone = /^\d{3}-\d{3,4}-\d{4}$/;
		var regceonum = /^(\d{3,3})+[-]+(\d{2,2})+[-]+(\d{5,5})/;
		var shopname = /^[가-힣a-zA-Z0-9]{2,15}$/;
		var regnickname = /^[가-힣0-9]{2,8}$/;
		
		var idok = 0;
		var pwok = 0;
		var pw2ok = 0;
		var nameok = 0;
		var emailok = 0;
		var phoneok = 0;
		var nickname = 0;

		$("#user_join_id").keyup(function() { //-----id 정규식 검사
			var inputvalue = $("#user_join_id").val();
			if (!regid.test(inputvalue)) {
				$("#join_id_check").html("6~12자리 영문 대소문자와 숫자로만 입력하십시오.");

				$("#join_id_check").css({
					"color" : "red"
				});
				idok = 0;
			} else {
				$("#join_id_check").html(check);
				$("#join_id_check").css({
					"color" : "blue"

				});
				idok = 1;
			}

		});

		$("#ceo_join_id").keyup(function() { //-----ceo id 정규식 검사
			var inputvalue = $("#ceo_join_id").val();
			if (!regid.test(inputvalue)) {
				$("#join_ceoid_check").html("6~12자리 영문 대소문자와 숫자로만 입력하십시오.");

				$("#join_ceoid_check").css({
					"color" : "red"
				});
				idok = 0;
			} else {
				$("#join_ceoid_check").html(check);
				$("#join_ceoid_check").css({
					"color" : "blue"

				});
				idok = 1;
			}

		});

		$("#user_join_pwd ").keyup(function() { //-------pwd 정규식검사
			var inputvalue = $("#user_join_pwd").val();
			if (!regpwd.test(inputvalue)) {
				$("#join_pwd_check").html("4~12자리 영문 대소문자와 숫자로만 입력하십시오.");
				$("#join_pwd_check").css({
					"color" : "red"
				});
				pwok = 0;
			} else {
				$("#join_pwd_check").html(check);
				$("#join_pwd_check").css({
					"color" : "blue"
				});
				pwok = 1;
			}
		});

		$("#user_join_pwd_check").keyup(function() { //-------pwd 확인 유효성검사
			var pwd1 = $("#user_join_pwd").val();
			var pwd2 = $("#user_join_pwd_check").val();
			if (pwd1 !== pwd2) {
				$("#join_pwd2_check").html("비밀번호가 틀립니다.");
				$("#join_pwd2_check").css({
					"color" : "red"
				});
				pw2ok = 0;
			} else {
				$("#join_pwd2_check").html(check);
				$("#join_pwd2_check").css({
					"color" : "blue"
				});
				pw2ok = 1;
			}
		});
		$("#ceo_join_pwd").keyup(function() { //------- ceo pwd 정규식검사
			var inputvalue = $("#ceo_join_pwd").val();
			if (!regpwd.test(inputvalue)) {
				$("#ceo_pwd_check").html("4~12자리 영문 대소문자와 숫자로만 입력하십시오.");
				$("#ceo_pwd_check").css({
					"color" : "red"
				});
				pwok = 0;
			} else {
				$("#ceo_pwd_check").html(check);
				$("#ceo_pwd_check").css({
					"color" : "blue"
				});
				pwok = 1;
			}
		});

		$("#ceo_join_pwd2").keyup(function() { //------- ceo pwd 확인 유효성검사
			var pwd1 = $("#ceo_join_pwd").val();
			var pwd2 = $("#ceo_join_pwd2").val();
			if (pwd1 !== pwd2) {
				$("#ceo_pwd_check2").html("비밀번호가 틀립니다.");
				$("#ceo_pwd_check2").css({
					"color" : "red"
				});
				pw2ok = 0;
			} else {
				$("#ceo_pwd_check2").html(check);
				$("#ceo_pwd_check2").css({
					"color" : "blue"
				});
				pw2ok = 1;
			}
		});
		$("#user_join_name").keyup(function() { //-------name 정규식 검사
			var inputvalue = $("#user_join_name").val();
			if (!regname.test(inputvalue)) {
				$("#join_name_check").html("잘못된 이름 입니다.");
				$("#join_name_check").css({
					"color" : "red"
				});
				nameok = 0;
			} else {
				$("#join_name_check").html(check);
				$("#join_name_check").css({
					"color" : "blue"
				});
				nameok = 1;
			}
		});
		$("#ceo_join_name").keyup(function() { //-------ceo 상호명 정규식 검사
			var inputvalue = $("#ceo_join_name").val();
			if (!shopname.test(inputvalue)) {
				$("#ceo_name_check").html("2~15자리 한글과 영어 숫자로만 입력해 주세요.");
				$("#ceo_name_check").css({
					"color" : "red"
				});
				nameok = 0;
			} else {
				$("#ceo_name_check").html(check);
				$("#ceo_name_check").css({
					"color" : "blue"
				});
				nameok = 1;
			}
		});

		$(".emailgroup").keyup(

				function() { //-------email 정규식 검사
					var inputvalue = $("#user_join_email-1").val() + "@"
							+ $("#user_join_email-2").val();
					if (!regemail.test(inputvalue)) {
						$("#join_email_check").html("잘못된 형식 입니다.");
						$("#join_email_check").css({
							"color" : "red"
						});
						emailok = 0;
					} else {
						$("#join_email_check").html(check);
						$("#join_email_check").css({
							"color" : "blue"
						});
						emailok = 1;
					}
				});
		$(".ceo_email_group").keyup(

				function() { //-------  ceo email 정규식 검사
					var inputvalue = $("#ceo_join_email1").val() + "@"
							+ $("#ceo_join_email2").val();
					if (!regemail.test(inputvalue)) {
						$("#ceo_email_check").html("잘못된 형식 입니다.");
						$("#ceo_email_check").css({
							"color" : "red"
						});
						emailok = 0;
					} else {
						$("#ceo_email_check").html(check);
						$("#ceo_email_check").css({
							"color" : "blue"
						});
						emailok = 1;
					}
				});
		$("#searchinput1").keyup(function() { //-------name 정규식 검사
			var inputvalue = $("#searchinput1").val();
			if (!regname.test(inputvalue)) {
				$("#search_name_check").html("잘못된 이름 형식 입니다.");
				$("#search_name_check").css({
					"color" : "red"
				});
				idok = 0;
			} else {
				$("#search_name_check").html(check);
				$("#search_name_check").css({
					"color" : "blue"
				});
				idok = 1;
			}
		});
		$("#searchinput2").keyup(function() { //-------name 정규식 검사
			var inputvalue = $("#searchinput2").val();
			if (!regid.test(inputvalue)) {
				$("#search_id_check").html("6~12자리 영문 대소문자와 숫자로만 입력하십시오.");
				$("#search_id_check").css({
					"color" : "red"
				});
				idok = 0;
			} else {
				$("#search_id_check").html(check);
				$("#search_id_check").css({
					"color" : "blue"
				});
				idok = 1;
			}
		});
		$(".search_email_group").keyup(

				function() { //-------  search email 정규식 검사
					var inputvalue = $("#search_email1").val() + "@"
							+ $("#search_email2").val();
					if (!regemail.test(inputvalue)) {
						$("#search_email_check").html("잘못된 형식 입니다.");
						$("#search_email_check").css({
							"color" : "red"
						});
						emailok = 0;
					} else {
						$("#search_email_check").html(check);
						$("#search_email_check").css({
							"color" : "blue"
						});
						emailok = 1;
					}
				});

		$(document)
				.on(
						"keyup",
						"#user_join_phone",
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
							var inputvalue = $("#user_join_phone").val();

							if (!regphone.test(inputvalue)) {
								$("#join_phone_check").html("잘못된 전화번호 입니다.");
								$("#join_phone_check").css({
									"color" : "red"
								});

								phoneok = 0;
							} else {
								$("#join_phone_check").html(check);
								$("#join_phone_check").css({
									"color" : "blue"
								});
								phoneok = 1;
							}

						});

		$(document)
				.on(
						"keyup",
						"#ceo_join_phone",
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
							var inputvalue = $("#ceo_join_phone").val();

							if (!regphone.test(inputvalue)) {
								$("#ceo_phone_check").html("잘못된 전화번호 입니다.");
								$("#ceo_phone_check").css({
									"color" : "red"
								});
								phoneok = 0;
							} else {
								$("#ceo_phone_check").html(check);
								$("#ceo_phone_check").css({
									"color" : "blue"
								});
								phoneok = 1;
							}

						});
		$("#nickname_set").keyup(function() { //-------닉네임 정규식 검사
			var inputvalue = $("#nickname_set").val();
			if (!regnickname.test(inputvalue)) {
				$("#nickname_check").html("2~8자리 한글,숫자 조합으로 입력하시오..");
				$("#nickname_check").css({
					"color" : "red"
				});
				nickname = 0;
			} else {
				$("#nickname_check").html(check);
				$("#nickname_check").css({
					"color" : "blue"
				});
				nickname = 1;
			}
		});

		 		$("#ceo_join_conum").keyup(function() {  //-------phone 정규식 검사
		 			
		 			$(this).val($(this).val().replace(/[^0-9]/g, "").replace(
		 					/(\d{3})(\d{2})(\d{5})/g,"$1-$2-$3")
						.replace("--", "-"));
		
		 var inputvalue = $("#ceo_join_conum").val();
		
		 if(!regceonum.test(inputvalue)){
		 $("#ceo_conum_check").html("잘못된 형식 입니다.");
		 $("#ceo_conum_check").css({
		 "color":"red"
		 });
		 }else {
		 $("#ceo_conum_check").html(check);
		 $("#ceo_conum_check").css({
		 "color":"blue"
		 });
		 } 
		 }); 
		 
		 $("#nickname_false").click(function(){
		 });
		 
	 	 

		//------------------------------- 로그인 ajax ---------------------------------
		$.fn.login = (function() {

			var login_id = $(".idinput").val();
			var login_pwd = $(".pwdinput").val();

			if ($(".pwdinput").val() !== "" && $(".idinput").val() !== "") {
				$.ajax({
					mathod : "post",
					url : "<%=request.getContextPath()%>/ogj/data/login.jsp", 
					data : {

						id : login_id,
						pwd : login_pwd
					},
					success : function(data) {
						if (data == 0) {
							$(".idinput").val("");
							$(".pwdinput").val("");
							$("#login_pwd_check").html("아이디가 존재하지 않습니다.");

						} else if (data == -1) {  // 비밀번호 틀림.
							$(".pwdinput").val("");
							$("#login_id_check").html("");
							$("#login_pwd_check").html("비밀번호가 틀렸습니다.");
						} else if (data == 1) { // 닉네임 없음
							$(".idinput").val("");
							$(".pwdinput").val("");
							$("#login_pwd_check").html("");
							$().reset();
							$(".nick-name").trigger("click");
						} else if (data == 2){ // 닉네임 있음.
							$().reset();
							var user = "member";
							if(login_id == "admin1"){
								user = "admin";
							}
							location.href = "<%=request.getContextPath() %>/user_login_ok.do?id="+login_id+"&user="+user;
							 
						}
						
					},
					error : function(data) {
						alert("통신 오류 입니다.~~");
					}
				});
			}

		}); // ----------------------------로그인 ajax 끝------------------------------------
		
		//------------------------------- ceo 로그인 ajax ---------------------------------
		$.fn.ceologin = (function() {

			var login_id = $(".idinput").val();
			var login_pwd = $(".pwdinput").val();

			if ($(".pwdinput").val() !== "" && $(".idinput").val() !== "") {
				$.ajax({
					mathod : "post",
					url : "<%=request.getContextPath()%>/ogj/data/ceologin.jsp",
					data : {

						id : login_id,
						pwd : login_pwd
					},
					success : function(data) {
						if (data == 0) {
							$(".idinput").val("");
							$(".pwdinput").val("");
							$("#login_pwd_check").html("아이디가 존재하지 않습니다.");

						} else if (data == -1) {  // 비밀번호 틀림.
							$(".pwdinput").val("");
							$("#login_id_check").html("");
							$("#login_pwd_check").html("비밀번호가 틀렸습니다.");
						}  else if (data == 1){ // 성공
							$().reset();
							location.href = "<%=request.getContextPath() %>/ceo_login_ok.do?id="+login_id+"&user=ceo";
							
							
						}
						
					},
					error : function(data) {
						alert("통신 오류 입니다.~~");
					}
				});
			} 

		}); // ---------------------------- ceo 로그인 ajax 끝------------------------------------
		
		//------------------------------- 닉네임 ajax ---------------------------------
		$("#nickname_ok").click(function() {


			if ($("#nickname_set").val() !== "") {
				if (nickname == 0) {
					$("#nickname_set").focus();
				} else {
				
				$.ajax({
					mathod : "post",
					url : "<%=request.getContextPath()%>/ogj/data/nickname_set.jsp",
					data : {
						
						nickname : $("#nickname_set").val()
					},
					success : function(data) {
						if (data < 0) {
							$("#nickname_set").val("");
							$("#nickname_check").html("존재하는 닉네임 입니다.");

						} else if (data > 0) {
							$("#nickname_set").val("");
							alert("변경 성공");
							 $('#nick-name-choice').modal('hide')
						}
					},
					error : function(data) {
						alert("통신 오류 입니다.~~")
					}
				});
				}
			}

		}); // ----------------------------닉네임 ajax 끝------------------------------------

		//--------------------------------회원가입 ajax --------------------------------

		$(".join_ok").click(
				function() {

					if ($("#user_join_id").val() !== ""
							&& $("#user_join_pwd").val() !== ""
							&& $("#user_join_pwd_check").val() !== ""
							&& $("#user_join_name").val() !== ""
							&& $("#user_join_phone").val() !== ""
							&& $("#user_join_email-1").val() !== ""
							&& $("#user_join_email-2").val() !== "") {
						if (idok == 0) {
							$("#user_join_id").focus();
						} else if (pwok == 0) {
							$("#user_join_pwd").focus();
						} else if (pw2ok == 0) {
							$("#user_join_pwd_check").focus();
						} else if (nameok == 0) {
							$("#user_join_name").focus();
						} else if (phoneok == 0) {
							$("#user_join_phone").focus();
						} else if (emailok == 0) {
							$("#user_join_email-1").focus();
						} else {
							//--ajax 영역
							$.ajax({
								mathod : "post",
								url : "<%=request.getContextPath()%>/ogj/data/join.jsp",
								data : {

									id : $("#user_join_id").val(),
									pwd : $("#user_join_pwd").val(),
									name : $("#user_join_name").val(),
									phone : $("#user_join_phone").val(),
									email : $("#user_join_email-1").val() + "@"
											+ $("#user_join_email-2").val()
								},
								success : function(data) {
									if (data > 0) {

										$().reset();

										$(".okdk").trigger("click");

									} else if (data < 0) {
										//중복 아이디 있음
										$("#user_join_id").focus();
										$("#user_join_id").val("");

										$("#join_id_check").html(
												"중복된 아이디가 있습니다.");

										$("#join_id_check").css({
											"color" : "red"
										});
										idok = 0;

									} else {
										// 걍 실패
										alert("통신 오류 입니다. 다시 시도해 주세요")
									}
								},
								error : function(data) {
									alert("통신 오류 입니다.~~")
								}
							});

						}
					}
				});

		//---------------------------------------회원가입 ajax 끝 --------------
		//---------------------------------------ceo 회원가입 ajax 시작 --------------

		$(".ceoJoin_ok").click(
				function() {

					if ($("#ceo_join_id").val() !== ""
							&& $("#ceo_join_pwd").val() !== ""
							&& $("#ceo_join_pwd2").val() !== ""
							&& $("#ceo_join_name").val() !== ""
							&& $("#ceo_join_phone").val() !== ""
							&& $("#ceo_join_email1").val() !== ""
							&& $("#ceo_join_email2").val() !== "") {
						if (idok == 0) {
							$("#ceo_join_id").focus();
						} else if (pwok == 0) {
							$("#ceo_join_pwd").focus();
						} else if (pw2ok == 0) {
							$("#ceo_join_pwd2").focus();
						} else if (nameok == 0) {
							$("#ceo_join_name").focus();
						} else if (phoneok == 0) {
							$("#ceo_join_phone").focus();
						} else if (emailok == 0) {
							$("#ceo_join_email1").focus();

						} else {
							//--ajax 영역
							$.ajax({
								mathod : "post",
								url : "<%=request.getContextPath()%>/ogj/data/ceo_join.jsp",
								data : {

									id : $("#ceo_join_id").val(),
									pwd : $("#ceo_join_pwd").val(),
									name : $("#ceo_join_name").val(),
									phone : $("#ceo_join_phone").val(),
									email : $("#ceo_join_email1").val() + "@"
											+ $("#ceo_join_email2").val(),
									conum : $("#ceo_join_conum").val()
								},
								success : function(data) {
									if (data > 0) {

										$().reset();

										$(".okdk").trigger("click");

									} else if (data == -1) { // 아이디 중복
										//중복 아이디 있음
										$("#ceo_join_id").focus();
										$("#ceo_join_id").val("");

										$("#join_ceoid_check").html(
												"중복된 아이디가 있습니다.");

										$("#join_ceoid_check").css({
											"color" : "red"
										});
										idok = 0;

									}else if(data == -2){ // 이메일 중복
										$("#ceo_join_email1").focus();
										$("#ceo_join_email1").val("");
										$("#ceo_join_email2").val("");

										$("#ceo_email_check").html(
												"중복된 이메일이 있습니다.");

										$("#ceo_email_check").css({
											"color" : "red"
										});
										emailok = 0;
									}else {
										// 걍 실패
										alert("통신 오류 입니다. 다시 시도해 주세요")
									}
								},
								error : function(data) {
									alert("통신 오류 입니다.~~")
								}
							});

							//--ajax영역 끝
						}
					}
				});

		//---------------------------------------ceo 회원가입 ajax 끝 --------------

		$.fn.reset = function() {

			$("#staticBackdrop input").val("");
			$("#staticBackdrop3 input").val("");
			$("#staticBackdrop2 input").val("");
			$("#search input").val("");

			$(".erorcheck").html("");

			$(".login_form input").val("");

			idok = 0;
			pwok = 0;
			pw2ok = 0;
			nameok = 0;
			emailok = 0;
			phoneok = 0;

		}

		//-------------------------------------- 회원가입 ajax 끝----------------------------------------------

		//------------------------------회원 아이디찾기 -------------------------------
		$(".searchBtn").click(
				function() {

					if (searchUser == 1) {

						if ($("#searchinput1").val !== ""
								&& $("#search_email1").val !== ""
								&& $("#search_email2").val !== "") {
							if (idok == 0) {
								$("#searchinput1").focus();
							} else if (emailok == 0) {
								$("#search_email1").focus();

							} else {
								$.ajax({
									mathod : "post",
									url : usersearchURL,
									data : {

										name : $("#searchinput1").val(),
										email : $("#search_email1").val() + "@"
												+ $("#search_email2").val()
									},
									success : function(data) {

										const data1 = $.trim(data);
										if (data1 == "false") {
											$("#search_val").html(
													"회원 정보가 존재하지 않습니다.");
											$("#search_val").css({
												"color" : "red"
											});

										} else {
											$("#search_val")
													.html(
															"당신의 아이디는 " + data
																	+ "입니다.");
											$("#search_val").css({
												"color" : "blue"
											});
										}

									},
									error : function(data) {
										alert("통신 오류 입니다.~~")
									}
								});
							}

							//--ajax영역 끝

						}
					} else if (searchUser == 2) {
						if ($("#searchinput2").val !== ""
								&& $("#search_email1").val !== ""
								&& $("#search_email2").val !== "") {
							if (idok == 0) {
								$("#searchinput2").focus();
							} else if (emailok == 0) {
								$("#search_email1").focus();

							} else {
								$.ajax({
									mathod : "post",
									url : usersearchURL,
									data : {

										id : $("#searchinput2").val(),
										email : $("#search_email1").val() + "@"
												+ $("#search_email2").val()
									},
									success : function(data) {

										const data1 = $.trim(data);
										if (data1 == "false") {
											$("#search_val").html(
													"회원 정보가 존재하지 않습니다.");
											$("#search_val").css({
												"color" : "red"
											});

										} else {
											$("#search_val").html(
													"당신의 비밀번호는 " + data
															+ "입니다.");
											$("#search_val").css({
												"color" : "blue"
											});
										}

									},
									error : function(data) {
										alert("통신 오류 입니다.~~")
									}
								});
							}

							//--ajax영역 끝

						}

					}

				});
				
				
				
				$("#rate1").click(function(){
					var a = $("input:radio[name:reviewStar]").val();
					alert(a);
				})
				
				$("insertOk").click(function(){
					
				});

	});