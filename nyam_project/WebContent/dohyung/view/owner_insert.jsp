<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
*{
	margin:0;
	padding:0;
}

 ul li {
	list-style: none;

}

textarea{
	resize: none;
}
/*
a{
	padding: 15px;
}
align{
	float: left;
} */

/*insert*/
.shop_insert{
	width: 100%;
	height: 100%;
}

.shop_insert h1{
	padding-bottom: 2vh;
}

.shop_cont1{
	width: 60vw;
	height: 30vh;
/*  	background-color: pink; */
	display: flex;
}

.imgCon{
	width: 30vw;
	height: 30vh;
	background-color: skyblue;
}

.cont1_info{
/* 	background-color: orange; */
	width: 40vw;
	margin-left: 3vw;
	margin-top: 2vh;
}

.cont1_info textarea{
	width: 25vw;
	height: 20vh;
}

.shop_cont2{
	width: 60vw;
	height: 45vh;
	margin-top: 3vh;
/* 	background-color: blue; */
}

.cont2_info{
	/*  background-color: orange; */
	width: 26vw;
	height: 30vh;
	margin-top: 1vh;
/* 	margin-left: 3vw; */
	float:left;
}

.cont2_info table{
	margin-bottom: 20vh;
	margin-left: 1vw;
}

.cont2_info table th{
	text-align: right;
	padding-right: 1vw;
}

.cont2_info table input{
	width:15vw;
	height: 3vh;
}



.submitBtn:hover{
	cursor: pointer;
	background-color: #e0e0e0;
}

.loca{
	width:25vw;
	height:35vh;
	background-color: skyblue;
	margin-left: 28vw;
}

.map{
	width: 25vw;
	height: 35vh;
}

.loca p{
	margin-top: 1vh;
/* 	background-color: pink; */
}

/* ower_reply */
.container{
	background-color: pink;
	width: 80vw;
	height: 100vh;
}

.comment{
	margin-top: 10vh;	
}
</style>

</head>
<body>
	<jsp:include page="../../navi/main_navi.jsp"/>
		<c:set var="num" value="${num }" />
		<c:set var="dto" value="${content }" />
		<section class="shop_insert">
			<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath()%>/owner_insert_ok.do">
				<input type="hidden" name="no" value="${dto.getCeo_num() }">
				<h1>가게 홍보글 등록</h1>
				<div class="shop_cont1">
					<div class="imgCon"><img alt="이미지나올곳" src=""></div>
					
					<div class="cont1_info">
						<h1>가게이름 : <input name="name"></h1>
						<textarea name="cont" placeholder="가게소개를 적어주세요"></textarea>
					</div>
				</div>
				
				<div class="shop_cont2">
					<h1>가게정보</h1>
					<div class="cont2_info">
						<table border ="0" cellspacing ="0" width="800">
						<tr>
							<th>가게아이디</th>
							<td><input name="id"></td>
						</tr>
						<tr>
							<th>가게 비밀번호</th>
							<td><input type="password" name="pwd">
						</tr>
						<tr>
							<th>가게 이미지</th>
							<td><input type="file" name="image"></td>
						</tr>
						
						
							
							
								<th>연락처: </th>
								<td><input name="phone" value="${dto.getCeo_phone() }"></td>
							</tr>
							
							<tr>
								<th>주소: </th>
								<td><input name="addr" value="${dto.getCeo_addr() }"></td>
							</tr>
							
							<tr>
								<th>주차가능여부: </th>
								<td><input name="car" value="${dto.getCeo_car() }"></td>
							</tr>
							<tr>
								<th>사업자등록번호:</th>
								<td><input name="conum">
								</td>
							</tr>
							<tr>
								<td colspan="2" align="left">
								<input class="submitBtn" type="submit" value="글 등록하기">
								</td>
							
						</table>
						
						
						
						
					</div>
		
					<div class="loca">
						<div class="map" id="map">
		    				<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=692e1f73c3a0256f966eeb53fcd5a257"></script>
							<script>
								var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
								    mapOption = { 
								        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
								        level: 3 // 지도의 확대 레벨
								    };
								
								var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
								
								// 지도를 클릭한 위치에 표출할 마커입니다
								var marker = new kakao.maps.Marker({ 
								    // 지도 중심좌표에 마커를 생성합니다 
								    position: map.getCenter() 
								}); 
								// 지도에 마커를 표시합니다
								marker.setMap(map);
								
								// 지도에 클릭 이벤트를 등록합니다
								// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
								kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
								    
								    // 클릭한 위도, 경도 정보를 가져옵니다 
								    var latlng = mouseEvent.latLng; 
								    
								    // 마커 위치를 클릭한 위치로 옮깁니다
								    marker.setPosition(latlng);
								    
								    var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
								    message += '경도는 ' + latlng.getLng() + ' 입니다';
								    
								    console.log(message);
								    
								});
							</script>
						</div>
						<p>*지도에 음식점의 위치를 표시해주세요</p>
					</div>
				</div>
			</form>
		</section>
		</div>
	</div>
</body>
</html>