<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>

<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.6.0.js"></script>
		<script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>

<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />


<script type="text/javascript">
$.noConflict();
var J = jQuery;

J(function(){
	
	
	J('.slide_div').slick({
		slidesToShow: 4,
		  slidesToScroll: 1,
		  autoplay: true,
		  autoplaySpeed: 3000,
		  nextArrow:$('.next'),
		  prevArrow:$('.prev'),
	});
	J('.slide_div2').slick({
		slidesToShow: 4,
		  slidesToScroll: 1,
		  autoplay: true,
		  autoplaySpeed: 3000,
		  nextArrow:$('.next2'),
		  prevArrow:$('.prev2'),
	});
});
</script>
<style type="text/css">

.post-slider{
  width:90%;
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
  top:53%;
  transform : translateY(-50%);
  color:gray;
  cursor: pointer;
}
.post-slider .fas2{
  position:absolute;
  z-index : 10;
  top:88%;
  transform : translateY(-50%);
  color:gray;
  cursor: pointer;
}
.post-slider .prev{
left : 0;
}
.post-slider .next{
right: 130px;
}
.post-slider .prev2{
left : 0;
}
.post-slider .next2{
right: 130px;
}

.slide_div button {
position: absolute;
z-index:10;
top:50%;
transform:translateY(-50%);
width:71px
height:71px;
}
.slide_div button.slick-prev{
lift:0;}
.slide_div button.slick-next{
right:0;}

.post-slider .post-wrapper{

  width:84%;
  height:350px;
  margin:0px auto;
  overflow: hidden;
  padding:10px 0px 10px 0px;
    margin-right : 200px;
}
.post-slider .post-wrapper wrapper2{

  width:84%;
  height:1000px;
  margin:0px auto;
  overflow: hidden;
  padding:10px 0px 10px 0px;
    margin-right : 200px;
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