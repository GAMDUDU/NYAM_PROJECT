
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가게 홍보글 등록</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/eunchae/cssCeo/title.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/eunchae/cssCeo/insert_ceo.css">
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic:400,700,800&amp;subset=korean" rel="stylesheet">
</head>
<body>
	<jsp:include page="../../navi/main_navi.jsp"/>
	<c:set var="num" value="${num }"/>
	<c:set var="dto" value="${content }" />
		<section class="right_container">
			<form method="post" name="latLng" enctype="multipart/form-data" action="<%=request.getContextPath()%>/owner_insert_ok.do">
				<input type="hidden" name="ceo_num" value="${dto.getCeo_num() }">
				<input type="hidden" name="no" value="${dto.getCeo_num() }">
				<h1 class="title">가게 홍보글 등록</h1>
				<div class="shop_cont1">
					<div class="imgCon"><img id="preview" src="<%=request.getContextPath() %>/image/ceoimage/${dto.getCeo_image()}" width="450" height="275"></div>
					
					<div class="cont1_info">
						<h1>${dto.getCeo_name() }</h1>
						<textarea name="cont" class="insert_txt" >${dto.getCeo_cont() }</textarea>
						<input type="file" name="upload_file" id="input-image" style="display: block"> 
						<script> 
							function readImage(input) {
							    // 인풋 태그에 파일이 있는 경우
							    if(input.files && input.files[0]) {
							        // 이미지 파일인지 검사 (생략)
							        // FileReader 인스턴스 생성
							        const reader = new FileReader();
							        // 이미지가 로드가 된 경우
							        reader.onload = e => {
							            const previewImage = document.getElementById("preview");
							            previewImage.src = e.target.result;
							        }
							        // reader가 이미지 읽도록 하기
							        reader.readAsDataURL(input.files[0]);
							    }
							}
							// input file에 change 이벤트 부여
							const inputImage = document.getElementById("input-image")
							inputImage.addEventListener("change", e => {
							    readImage(e.target)
							});
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
		
		<jsp:include page="../../navi/footer.jsp"/>
		
		</div>
	</div>
</body>
</html>