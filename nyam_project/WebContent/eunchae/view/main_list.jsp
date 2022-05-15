<%@page import="com.review.model.Ceo_NyamDTO"%>
<%@page import="com.review.model.Ceo_NyamDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	Ceo_NyamDAO dao = Ceo_NyamDAO.getInstance();

List<Ceo_NyamDTO> list = dao.getCeoList();

request.setAttribute("List", list);

List<Ceo_NyamDTO> list2 = dao.getCeoList2();

request.setAttribute("List2", list2);

int listSize = list.size(); // 리스트의 갯수가 4개 이하면 slick 에 들어갈 값을 반복해주어야함 (slick 값의 최소갯수가 5개이기 때문(화면에 출력되는 이미지 갯수가 4개이기 때문))
int listSize2 = list2.size();
int repeat = 1;
int repeat2 = 1;

switch (listSize) {
case 1:
	repeat = 6;
	break;
case 2:
	repeat = 3;
	break;
case 3:
	repeat = 2;
	break;
case 4:
	repeat = 2;
	break;
default:
	repeat = 1;
}

switch (listSize2) {
case 1:
	repeat2 = 6;
	break;
case 2:
	repeat2 = 3;
	break;
case 3:
	repeat2 = 2;
	break;
case 4:
	repeat2 = 2;
	break;
default:
	repeat2 = 1;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NyamNyam</title>
<link rel="icon" href="favicon.ico">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">

</head>
<body>


	<jsp:include page="../../navi/main_navi.jsp" />
	<jsp:include page="../../ogj/newtest.jsp" />




	<section class="rightCon post-slider">
		<div class="main_title">
			<div class="mainSearch">
				<form method="post"
					action="<%=request.getContextPath()%>/review_search.do">
					<select class="mainSelect" name="search_field">
						<option value="title_content" select>내용</option>
						<option value="adress">위치</option>
						<option value="rate">별점</option>
					</select> <input class="mainInput" name="search_keyword"
						placeholder="맛집을 검색해보세요!"> <input type="submit" value="검색"
						class="btn btn-primary">
				</form>
			</div>
		</div>

		<!-- 따끈따끈 스토리 -->
		<br> <br>
		<hr style="color: gray;">
		<br>
		<h3>따끈따끈 최근 등록된 맛집</h3>

		<br> <br>
		<div class="new-story">
			<i class="fas fa-chevron-left prev"> <span
				class="material-symbols-outlined"> arrow_back_ios </span>
			</i> <i class="fas fa-chevron-right next"> <span
				class="material-symbols-outlined"> arrow_forward_ios </span>
			</i>
			<div class="mainReviewCon slide_div post-wrapper">
				<c:set var="list" value="${List}" />
				<c:if test="${empty list}">
					<span>리뷰 목록이 없습니다.</span>
					<br>
				</c:if>






				<c:if test="${!empty list}">

					<%
						for (int i = 0; i < repeat; i++) {
					%>
					<c:forEach items="${list }" var="dto">



						<div class="post">
							<a
								href="<%=request.getContextPath()%>/review_mycontent.do?no=${dto.getCeo_num()}">
								<img
								src="<%=request.getContextPath()%>/image/ceoimage/${dto.getCeo_image()}"
								class="slider-image" width="300" height="300">
							</a>
						</div>



					</c:forEach>
					<%
						}
					%>

				</c:if>
			</div>
		</div>

		<!-- 인기절정 -->
		<br> <br>
		<hr style="color: gray;">
		<br>
		<h3>BEST 인기 맛집</h3>

		<div class="hot-story">

			<br> <br> <i class="fas2 fa-chevron-left prev2"> <span
				class="material-symbols-outlined"> arrow_back_ios </span>
			</i> <i class="fas2 fa-chevron-right next2"> <span
				class="material-symbols-outlined"> arrow_forward_ios </span>
			</i>
			<div class="mainReviewCon slide_div2 post-wrapper wrapper2">
				<c:set var="list2" value="${List2}" />
				<c:if test="${empty list2}">
					<span>리뷰 목록이 없습니다.</span>
					<br>
				</c:if>






				<c:if test="${!empty list2}">

					<%
						for (int i = 0; i < repeat; i++) {
					%>
					<c:forEach items="${list2 }" var="dto2">



						<div class="post">
							<a
								href="<%=request.getContextPath()%>/review_mycontent.do?no=${dto2.getCeo_num()}">
								<img
								src="<%=request.getContextPath()%>/image/ceoimage/${dto2.getCeo_image()}"
								class="slider-image" width="300" height="300">
							</a>
						</div>



					</c:forEach>
					<%
						}
					%>

				</c:if>
			</div>
		</div>
	</section>

	<jsp:include page="../../navi/footer.jsp" />

	</div>
	</div>
</body>
</html>