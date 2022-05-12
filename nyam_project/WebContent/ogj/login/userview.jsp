<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

                    <form action="login_script.php" method="POST">  <%-- 회원 로그인 --%>
                        <div class="form-group py-2">
                            <div class="input-field"> <span class="far fa-user p-2"></span> <input type="text" placeholder="아이디" required> </div>
                        </div>
                        <div class="form-group py-1 pb-2">
                            <div class="input-field"> <span class="fas fa-lock px-2"></span> <input type="password" placeholder="비밀번호" required> <button class="btn bg-white text-muted"> <span class="far fa-eye-slash"></span> </button> </div>
                        </div>
                        <div class="form-inline"> <input type="checkbox" name="remember" id="remember"> <label for="remember" class="text-muted">아이디 기억하기</label>&nbsp;&nbsp;&nbsp; <a href="#" id="forgot" class="font-weight-bold">비밀번호를 잊으셨습니까?</a> </div>
                        <div class="btn btn-primary btn-block mt-3">로그인</div>
                        <div class="text-center pt-4 text-muted">계정이 없습니까? <a href="#">회원가입</a> </div>
                    </form>

</body>
</html>