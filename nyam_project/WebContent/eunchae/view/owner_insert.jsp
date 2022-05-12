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
	<jsp:include page="../include/navi_ceo.jsp"/>
		<c:set var="num" value="${num }" />
		<c:set var="dto" value="${content }" />
		<section class="right_container">
			<form method="post" name="latLng" enctype="multipart/form-data" action="<%=request.getContextPath()%>/owner_insert_ok.do">
				<input type="hidden" name="no" value="${dto.getCeo_num() }">
				<h1 class="title">가게 홍보글 등록</h1>
				<div class="shop_cont1">
					<div class="imgCon"><img src="<%=request.getContextPath() %>/upload/${dto.getCeo_image()}" width="450" height="275"></div>
					
					<div class="cont1_info">
						<h1>${dto.getCeo_name() }</h1>
						<textarea name="cont">${dto.getCeo_cont() }</textarea>
						<input type="file" name="upload_file" id="image" accept="image/*" onchange="setThumbnail(event)">
					<script> 
							function setThumbnail(event) { 
								for (var image of event.target.files) { 
									var reader = new FileReader(); 
									
									reader.onload = function(event) { 
										var img = document.createElement("img"); 
										img.setAttribute("src", event.target.result); 
										img.setAttribute("width", 400);
										document.querySelector(".imgCon").appendChild(img); 
										}; 
										
										console.log(image); 
										reader.readAsDataURL(image); 
										} 
								} 
							</script>

					</div>
				</div>
				
				<div class="shop_cont2">
					<div class="cont2_info">
						<h1>가게정보</h1>
						<table>
							<tr>
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
						</table>
						
						<input class="submitBtn" type="submit" value="글 등록하기">
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
								
								var latClicked;
								var lngClicked;

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
							 	
							    latClicked = latlng.getLat();
							    lngClicked = latlng.getLng();
							    
							   /*  console.log("test" + lat); */
							   
							    document.latLng.lat.value = latClicked;
							    document.latLng.lng.value = lngClicked;
							    
							    // 마커 위치를 클릭한 위치로 옮깁니다
							    marker.setPosition(latlng);
							    
							    var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
							    message += '경도는 ' + latlng.getLng() + ' 입니다';
							    
							    console.log(message);
								})
								;
							</script>
							
							<input type="hidden" name="lat" value="${dto.getCeo_lat()}">
							<input type="hidden" name="lng" value="${dto.getCeo_lng()}">
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