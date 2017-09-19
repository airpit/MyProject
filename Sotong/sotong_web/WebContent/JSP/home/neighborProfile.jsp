<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>소통-가족 프로필</title>

<!-- 외부 링크 -->
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<style>

#tableDiv{
	width:60%;
	margin:0 auto;
}

 table { 
 	margin:0 auto;

 }
 
 #neighborProfile {
 	float : left;
	width : 60%;
	height : 80%;
 	margin: 50px 40px 0px 40px;
  	border:2px solid #00bfff;
  	border-radius:40px;
  	width:600px;;
 }
 
 table th, table td { 
 	text-align : center;
 	overflow: hidden;
 }
 
 table td {
 	height : 30px;
 }
 
 #homeTitle {
 	margin-top:50px;
 	font-size : 50px;
 }
 
 #profileImg {
 	border : 2px solid red;
 	width : 50px;
 	height : 50px;
 	margin-top:10px;
 	
 }
 
 #backBtn {
 width : 100px;
 height : 150px;
 float : left;
 background:url("img/neighbor/back.png") no-repeat;
 background-size : cover;
 border:none;
 margin-right : 30px;
 }
 
 
 
</style>

</head>
<body>

<div>
	<jsp:include page="../../mainMenu.jsp"></jsp:include>
</div>

<div id="homeTitle">
	<span>철연이네 홈</span> <%-- EL로 바꿀 곳 --%>
</div>

	<input type="button" id="backBtn">

	<div id="tableDiv" class="table-responsive">
		<table class="span12" id="neighborProfile">
		
		<tr>
			<th colspan="2" data-align="center">
				<img src="img/myhome/profile3.jpg" class="img-circle" id="profileImg"/>			
			</th>
		</tr>
		<tr>
			<th colspan="2" data-align="center">
				<label class="text-center">김경아</label>
			</th>
		</tr>
		
		<tr>
			<th>이메일 주소</th>
			<td>email@sotong.com</td>
		</tr>
		<tr>
			<th>생일</th>
			<td>
			1990<strong>년</strong>
			5<strong>월</strong>
			10<strong>일</strong>
			</td>
		</tr>
		<tr>
			<th>별명</th>
			<td>할머니</td>
		</tr>
		<tr>
			<th>색상</th>
			<td>
			<input type="color" id="personColor" value="#ff0000" disabled>
			</td>
		</tr>
		</table>
	</div>
		
	<script>
		$(document).ready(function(){
			
			$("#backBtn").click(function(){
				history.back();
			});
			
			$("#personColor").click(function(){
				alert(this.val());
			});			
		});
		
	</script>
</body>
</html>