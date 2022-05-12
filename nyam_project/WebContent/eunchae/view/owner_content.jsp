<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<jsp:include page="<%=request.getContextPath() %>/eunchae/include/navi_ceo.jsp"/>
		<c:set var="num" value="${num }" />
		<c:set var="dto" value="${content }" />
		<section class="right_container">
			<h1 class="title">가게 홍보글</h1>
			<div class="shop_cont1">
				<div class="imgCon"><img src="<%=request.getContextPath() %>/upload/${dto.getCeo_image()}" width="450" height="275"></div>
				
				<div class="cont1_info">
					<h1>${dto.getCeo_name() }</h1>
					<h3>별점: ${dto.getCeo_avgrate() }</h3>
					<br>
					<p>${dto.getCeo_cont() }</p>
				</div>
			</div>
			
			<div class="shop_cont2">
				<div class="cont2_info">
					<h1>가게정보</h1>
					<table>
						<tr>
							<th>연락처: </th>
							<td><p>${dto.getCeo_phone() }</p></td>
						</tr>
						
						<tr>
							<th>주소: </th>
							<td><p>${dto.getCeo_addr() }</p></td>
						</tr>
						
						<tr>
							<th>주차가능여부: </th>
							<td><p>${dto.getCeo_car() }</p></td>
						</tr>
					</table>
					
					<input class="submitBtn" type="submit" value="별점주기">
					<input class="submitBtn" type="submit" value="리뷰쓰기">
				</div>
	
				<div class="loca">
					<div class="map" id="map">
	    				<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=692e1f73c3a0256f966eeb53fcd5a257"></script>
						<script>
							var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
							    mapOption = { 
							        center: new kakao.maps.LatLng("${dto.getCeo_lat()}", "${dto.getCeo_lng()}"), // 지도의 중심좌표
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
						</script>
					</div>
				</div>
			</div>
		</section>
		</div>
	</div>
</body>
</html>