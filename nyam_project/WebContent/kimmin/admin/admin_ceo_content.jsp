<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>



<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="js/jquery-3.6.0.js"></script>
<link rel="icon" href="favicon.ico">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/main.css">
<link rel="stylesheet" href="/css/bootstrap.css">
<script type="text/javascript" src="js/jquery-3.6.0.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> <script type="text/javascript" src="/js/bootstrap.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS --> <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> <!-- 부가적인 테마 --> <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css"> <!-- 제이쿼리 --> <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> <!-- 합쳐지고 최소화된 최신 자바스크립트 --> <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>


<style type="text/css">

.map{
	background-color:pink;
	width:250px;
	height:120px;
}

.title {
	background-color: #e0e0e0;
    width: 400px;
    margin: 0 auto;
    padding: 0 10px;
    margin-top: 30px;
    text-align: center;
}

.user-button-items input{
	font-weight: bold;
}


</style>

			
</head>
<body>
<jsp:include page="../include/admin_navi.jsp" />

	<div class="title">
	
	</div>
	
	
	<div class="panel panel-primary">
	
	<div class="panel-heading">가게정보</div>

	<table class="table">
 		
 		
	<c:set var="dto" value="${content }"/>
 		<form method="post" enctype="multipart/form-data"
 		action="<%=request.getContextPath() %>/admin_ceo_update.do?num=${dto.getCeo_num() }">
 		
 			<c:if test="${!empty content }">
 			
 				<tr class="user-info-items">
 					<th>가게번호</th>
 					<td class="cont">${dto.getCeo_num() }</td>
 					
 					<th>가게아이디</th>
 					<td class="cont">${dto.getCeo_id() }</td>
 					
 					 <th>평균별점</th>	
 					<td class="cont">${dto.getCeo_avgrate() }</td>
 				</tr>
 				

 				
 				<tr class="user-info-items">
 					<th>상호명</th>
 					<td class="cont">
 					<input name="name" value="${dto.getCeo_name() }" ></td>
 					
 					<th>가게비밀번호</th>
 					<td class="cont">
 					<input name="pwd" value="${dto.getCeo_pwd() }" ></td>
 					
 					<th>사업자등록번호</th>
 					<td class="cont">
 					<input name="conum" value="${dto.getCeo_conum() }" ></td>
 					

 				</tr>	
 				
 				<tr class="user-info-items">
 				<th>가게연락처</th>	
 					<td class="cont">
 					<input name="phone" value="${dto.getCeo_phone() }" ></td>
 				<th>가게주소</th>
 					<td class="cont" >
 					<input name="addr"  value="${dto.getCeo_addr() }" ></td>
 					
 			     <th rowspan="2">주차장</th>
 					<td class="cont" rowspan="2">
 					<input name="car" value="${dto.getCeo_car() }" ></td>
 				</tr>
 				
 				<tr class="user-info-items">
 				<th>가게메일</th>	
 					<td class="cont">
 					<input name="phone" value="${dto.getCeo_mail() }" ></td>
 					
 				<th>가입일</th>	
 					<td class="cont">
 					<input name="phone" value="${dto.getCeo_date() }" ></td>
 				<th></th>	
 					<td class="cont">
 				</tr>
 			
 				
 				
 				

 			<tr class="user-info-items">	
 				<th>지도정보</th>
 					<td class="cont">
 						
 						<div class="map" id="map">
   							<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b080b8ef85bcf688039ba83331323528"></script>
                    		 <script>
                       			 var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
                          		  mapOption = { 
                              	  center: new kakao.maps.LatLng("${dto.getCeo_lat()}", "${dto.getCeo_lng()}"), // 지도의 중심좌표
                               	 level: 3 // 지도의 확대 레벨
                           			 };
                        
                    		 	console.log("${dto.getCeo_lat()}");
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
 						
 					</td>
 					
	
 				<th>가게이미지</th>
 					<td class="cont">
 					<img src="<%=request.getContextPath() %>/upload/${dto.getCeo_image() } "
 									width="190" height="120">
 						<input type="hidden" name="image_new">
          				<input type="hidden" name="image_old"
          				            value="${dto.getCeo_image() }">
          				</td>
 					
 				<th>가게소개</th>
 					<td class="cont" >
 					<textarea  rows="7" cols="20" name="ceocont">${dto.getCeo_cont() }</textarea>
 					</td>
 					
 				</tr>	
 		
 				
 		</c:if>
 		
 		 		<c:if test="${empty content }">
 			<tr class="user-info-items">
 				<td colspan="6" class="cont">
 					<h3 align="center">가입된 업체가 없습니다</h3>
 				</td>
 			</tr>
 		</c:if>
 		
 		 	<tr class="user-button-items">
	 			<td colspan="6" align="center">
	 			
	 			<input type="submit" class="btn btn-default navbar-btn" value="회원정보 수정">
	 			<input type="button" class="btn btn-default navbar-btn" value="회원 정보 삭제"
	 				onclick="if(confirm('회원을 삭제하시겠습니까?')){
	 				location.href='admin_ceo_delete.do?num=${dto.getCeo_num() }'
	 				}else{ return; }">
	 			<input type="submit" class="btn btn-default navbar-btn" value="전체가게목록"
	 				onclick="location.href='admin_ceo_Management.do'">
	 			
	 			</td>
	 		</tr>
 		</form>  
 	</table>
 	
 </div>

</body>
</html>