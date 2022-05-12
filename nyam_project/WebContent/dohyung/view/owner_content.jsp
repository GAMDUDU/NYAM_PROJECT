<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dohyung/js/rep.js"></script>

<script type="text/javascript">

	/*
		이벤트 종류 
		1. 마우스 이벤트:	사용자가 마우스 버튼을 눌렀을 때 발생하는 이벤트
		2. 키보드 이벤트:	사용자가 키보드를 눌렀을 때 발생하는 이벤트
										이 때 눌린 키에 대한 정보가 이벤트에 담긴
		3. 태그 요소 고유 이벤트: 요소마다 발생하는 고유의 이벤트
		4. 사용자 정의 이벤트: 개발자가 직접 만들어 사용하는 이벤트.
		
	*/
	
	$(function(){
			//이벤트 형식: $("요소선택").on("이벤트이름", 이벤트리스너)
			//이벤트 리스너는 보통 무명클래스를 이용하여 처리를 함.
			
			$("#setrate").click(function(){
				let cw=900;     /* //화면 넓이 */
		        let ch=300;   /*  //화면 높이 */

		        let sw=cw/2;   /*  //띄울 창의 넓이 */
		        let sh=ch/2;    /* //띄울 창의 높이 */

		        let ml=500; /* //가운데 띄우기위한 창의 x위치 */
		        let mt=700; /* //가운데 띄우기위한 창의 y위치 */

		      /*   var url = $("#sel").val();
		        if(url != '0'){
		            window.open(url, 'tst','width='+sw+',height='+sh+',top='+mt+',left='+ml+',resizable=no,scrollbars=yes');
		        } */
				let wno=$("#c_num").val();
				
				<%-- location href='<%=request.getContextPath() %>/review_rate.do?no='+wno;
					"<%=request.getContextPath() %>/review_rate.do?no="+wno
					view/setrate.jsp
				<%=request.getContextPath()%>
				 --%>
				
				
				
				window.open('<%=request.getContextPath()%>/review_rate.do?no='+wno,'_blank','width='+sw+',height='+sh+',top='+mt+',left='+ml+',resizable=no,scrollbars=yes'); 
				
				
				<%-- window.open('<%=request.getContextPath() %>/review_rate.do?no="+wno, "_blank" ,"width="+sw+",height="+sh+",top="+mt+",left="+ml+",resizable=no,scrollbars=yes'); --%>
			});
	});


</script>
<style type="text/css">


 ul li {
	list-style: none;

}




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

.submitBtn{
	width: 100px;
	height: 35px;
	border-radius: 15px;
	border: none;
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
	<jsp:include page="../include/navi_user.jsp"/>
		<c:set var="num" value="${num }" />
		<c:set var="dto" value="${cont }" />
		<section class="shop_insert">
				<h1>가게 홍보글</h1>
				<div class="shop_cont1">
					<div class="imgCon">
					<img src="<%=request.getContextPath()%>/dohyung/upload/${dto.getCeo_image()}"
						width="350" height="255"> 
					
					
					</div>
					
					<div class="cont1_info">
						<h1>${dto.getCeo_name() }</h1>
						
						<c:choose>
							<c:when test="${Double.parseDouble(dto.getCeo_avgrate())>=4.8 }"> 
							${dto.getCeo_avgrate() }<img src="<%=request.getContextPath()%>/dohyung/upload/5star.png" width="80"></c:when>
							<c:when test="${Double.parseDouble(dto.getCeo_avgrate())>=4.4 }"> 
							${dto.getCeo_avgrate() }<img src="<%=request.getContextPath()%>/dohyung/upload/4_5star.png" width="80"></c:when>
							<c:when test="${Double.parseDouble(dto.getCeo_avgrate())>=4.0 }"> 
							${dto.getCeo_avgrate() }<img src="<%=request.getContextPath()%>/dohyung/upload/4star.png" width="80"></c:when>
							<c:when test="${Double.parseDouble(dto.getCeo_avgrate())>=3.5 }"> 
							${dto.getCeo_avgrate() }<img src="<%=request.getContextPath()%>/dohyung/upload/3_5star.png" width="80"></c:when>
							<c:when test="${Double.parseDouble(dto.getCeo_avgrate())>=3.0 }"> 
							${dto.getCeo_avgrate() }<img src="<%=request.getContextPath()%>/dohyung/upload/3star.png" width="80"></c:when>
							<c:when test="${Double.parseDouble(dto.getCeo_avgrate())>=2.5 }"> 
							${dto.getCeo_avgrate() }<img src="<%=request.getContextPath()%>/dohyung/upload/2_5star.png" width="80"></c:when>
							<c:when test="${Double.parseDouble(dto.getCeo_avgrate())>=2.0 }"> 
							${dto.getCeo_avgrate() }<img src="<%=request.getContextPath()%>/dohyung/upload/2star.png" width="80"></c:when>
							<c:when test="${Double.parseDouble(dto.getCeo_avgrate())>=1.5 }"> 
							${dto.getCeo_avgrate() }<img src="<%=request.getContextPath()%>/dohyung/upload/1_5star.png" width="80"></c:when>
							<c:when test="${Double.parseDouble(dto.getCeo_avgrate())>=1.0 }"> 
							${dto.getCeo_avgrate() }<img src="<%=request.getContextPath()%>/dohyung/upload/1star.png" width="80"></c:when>
							
						</c:choose>
						
						
						
						
						
						<p>${dto.getCeo_cont() }</p>
						
						
					</div>
				</div>
				
				<div class="shop_cont2">
					<h1>가게정보</h1>
					<div class="cont2_info">
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
						
						<input id = "setrate" class="submitBtn" type="submit" value="별점주기">
						<input type="hidden" id="c_num" value="${dto.getCeo_num() }">
						<input class="submitBtn" type="submit" value="리뷰쓰기" onclick="location.href='review_write.do?cnum=${dto.getCeo_num()}'">
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
					</div>
				</div>
				
				
			
	
		<form name="reply_su" id="reply_su" method="post">
			<input type="hidden" id="r_c_num" name="r_c_num" value="${dto.getCeo_num() }">
			<input type="hidden" id="r_num" name="r_num" value="0"> 
			
			<table border="0" cellspacing="0">
				<tr>
					<th colspan="3">댓글</th>
				</tr>
				<tr>
					<td></td>
					<td><input type="text" id = "cont" name="cont" size=100> </td>
					<td><input type="button" value="등록" id="btns"><input type="reset"value="취소"></td>
				</tr>
			</table>
		</form>	
		
			<table id="reply" cellspacing="0">
				<tr>
					<th>작성자</th><th width=650></th><th></th>
					
				</tr>
				
			</table>
			
			<br><br>
			
			
			<c:set var="list" value="${List}"/>
		
			<c:if test="${empty list}">
			<span>리뷰 목록이 없습니다.</span><br>
				</c:if>
		
			<c:if test="${!empty list}">
				<table border="1" cellspacing="0" width= "800">
					<tr>
						<th>작성자</th> <th> 글제목</th><th>평점</th>
						<th>좋아요</th><th>작성일자</th>
					</tr>
					
					
			
			
			
				<c:forEach items="${list}" var="dtr">
				<tr>
					<td> ${dtr.getReview_id()}</td>
					
					<td><a href="<%=request.getContextPath() %>/review_content.do?no=${dtr.getReview_num()}">
					
					${dtr.getReview_title() }</a></td>
					<td>${dtr.getReview_rate() }</td>
					<td>${dtr.getReview_like() }</td> 
					
					<td> ${dtr.getReview_date().substring(0,10) }</td>
				</tr>
				
				
				
				</c:forEach>
			</table>
			
			 </c:if>
			
			<%-- <c:if test="${page >block }">
				<a href="owner_content.do?page=1">◀◀	</a>
				<a href="owner_content.do?page=${startBlock -1 }">◀</a>
			</c:if>
			<c:forEach begin="${startBlock }" end="${endBlock}" var="i">
				<c:if test="${i==page }">
					<b><a href="owner_content.do?page=${i}">[${i}]</a></b>
				
				</c:if>
				<c:if test="${i!=page }">
					<a href="owner_content.do?page=${i }">[${i }]</a>
				
				</c:if>
			
			</c:forEach>
			<c:if test="${endBlock < allPage }">
				<a href="owner_content.do?page=${endBlock +1 }">▶</a>
				<a href="owner_content.do?page=${allPage }">▶▶</a>
			
			</c:if>   --%>
			
			<br>
			<br>
			<br>
			
			
		
		
		
		</section>
		
		
		
		
			
		
		
		
		
		
		
		
			
		
		</div>
	</div>
</body>
</html>