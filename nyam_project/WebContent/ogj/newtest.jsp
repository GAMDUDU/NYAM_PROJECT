<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>

<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.6.0.js"></script>
		<script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>

<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>



<script type="text/javascript">
$.noConflict();
var J = jQuery;

J(function(){
	
	
	
	J('.slide_div').slick({
		slidesToShow: 3,
		  slidesToScroll: 1,
		  autoplay: true,
		  autoplaySpeed: 2000,
		  nextArrow:$('.next'),
		  prevArrow:$('.prev'),
	});
});
</script>
<style type="text/css">

.post-slider{
  width:70%;
  margin:0px auto;
  position:relative;
}
.post-slider .silder-title{
  text-align:center;
  margin:30px auto;
}
.post-slider .fas{
  position:absolute;
  z-index : 10;
  top:78%;
  transform : translateY(-50%);
  right:30px;
  color:gray;
  cursor: pointer;
}
.post-slider .prev{
left : 0;
}
.post-slider .next{
right: 0;
}


.post-slider .post-wrapper{

  width:84%;
  height:350px;
  margin:0px auto;
  overflow: hidden;
  padding:10px 0px 10px 0px;
}
.post-slider .post-wrapper .post{
  width:300px;
  height:300px;
  margin:0px 10px;
  display:inline-block;
  background:white;
  border-radius: 5px;
}
.post-slider .post-wrapper .post .post-info{
  font-size:15px;
  height:30%;
  padding-left:10px;
}
.post-slider .post-wrapper .post .slider-image{
  width:100%;
  height:100%;
  border-top-left-radius:5px;
  border-top-right-radius:5px;
}
</style>


</head>
<body>

<!-- <div class="your-class">
  <div>your content</div>
  <div>your content</div>
  <div>your content</div>
</div> -->





</body>
</html>