<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="assets/css/bootstrap-responsive.css" rel="stylesheet">
<title>우리 홈</title>
<style>

body {
	width : 100%;
}

#homeName{
	height:50px;
	font-size : 50px;
	font-weight : bold;
    width: 66%;
}

#homeName span {
	float:right;
}

#homeTitle {
    text-align: center;
    font-size: 50px;
    width: 60%;
    font-weight: bold;
    margin: 15px;
    border-bottom: 1px solid #00bfff;
    margin-top: 50px;
    margin-bottom: 70px;
    margin: 50px auto 70px auto;
}

#profile_info, #profile_info label{
	font-size:25px;	
}


#homeInfo{
	height:50px;
	float :left;
	width:60%; 
	margin: 0 auto;
	padding:0 210px;
	border-bottom: 1px solid #00bfff;
}

#profile_info label {
	font-size:25px;
	margin-bottom:10px;
}
  
#invite {
	height:50px;
	float : left;
	width: 20%;
}
   
.btns{
	width:50px;
	height:50px;
	border : none;
}

.overbtns{
	width:250px;
	height:50px;
	border : none;
}

#familyList {
	width : 1300px;
	margin : 0 auto;

}
  
.modal-header, h4, .close {
    background-color: #0070C0;
    color:white !important;
    text-align: center;
    font-size: 30px;
}
.modal-footer {
    background-color: #f9f9f9;
}

#managerIcon, #familyIcon {
	width : 20px;
	height : 20px;
}
  
/* 가족프로필의 사진 크기 */
#profileImg {
	width : 130px;
	height : 130px;
	margin: 20px auto;
}

.buttons {
	width : 50px;
	height : 50px;
	border : none;
}  

#myButton {
	text-align : center;
}

#profile_info{
	font-size:25px;	
}

.form-inline{
	width:90%;
	border:2px solid #00bfff;
	border-radius:40px;
	margin-right:20px;
	margin-left:20px;
	margin-bottom:20px;
}
  
#connectImg {
	float:right;
    width: 20%;
    position: relative;
    left: 200px;
}
</style>


 <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>


<body>

<div>
	<jsp:include page="../../mainMenu.jsp"></jsp:include>
</div>

<div id="homeTitle">
	<input type="image" id="disconnectImg" src="../../img/neighbor/disconnect.png" alt="이웃과 연결끊기" title="이웃과 연결끊기"/>
	<input type="image" id="connectImg" src="../../img/neighbor/connect.png" alt="이웃과 연결하기" title="이웃과 연결하기"/>
		
	<!-- 홈 이름 -->
	<label id="homeName"><span>철연이네 홈</span></label> <%-- EL로 바꿀 곳 --%>
	
</div>

<!-- 이웃 가족 구성원 목록 -->

<div class="row" id="familyList" class="text-center">

<!-- 매니저 프로필 -->
	<div class="col-md-6" class="famInfo">
		<div id="profile" class="form-inline">	
		
			<div id="manager_level" class="form-group">
				<img src=".img/myhome/manager.png" id="managerIcon" alt="매니저"/>
			</div>
			
			<div id="profile_pic" class="form-group">
				<img src="img/myhome/profile.jpg" id="profileImg" alt="프로필사진" class="img-circle"/>
				
			</div>
			
			<div id="profile_info" class="form-group">
				<label>김철연</label> <%--EL로 바꿀곳 --%>	
				<span class="glyphicon glyphicon-gift" aria-hidden="true"  style="color: #FF66CC"></span>생일 : 3월 28일 <%--EL로 바꿀곳 --%>
			</div>
			
			
		</div>
	</div>
	
<!-- 가족 구성원 프로필 -->
	<c:forEach begin="0" end="5" step="1" varStatus="count"> 
	<a href="neighborProfile.jsp">
	<div class="col-md-6" class="famInfo">
		<div id="profile" class="form-inline">	
		
			<div id="manager_level" class="form-group">
				<img src="img/myhome/hearts.png" id="familyIcon" alt="가족"/>
			</div>
			
			<div id="profile_pic" class="form-group">
				<img src="img/myhome/profile3.jpg" id="profileImg" alt="프로필사진" class="img-circle"/>
				
			</div>
			
			<div id="profile_info" class="form-group">
				<label>김경아</label> <%--EL로 바꿀곳 --%>	
				<span class="glyphicon glyphicon-gift" aria-hidden="true"  style="color: #FF66CC"></span>생일 : 5월  10일 <%--EL로 바꿀곳 --%>
			</div>
		</div>
	</div>
	</a>
	
	</c:forEach>
	
</div>

<!-- 연결 요청 모달 -->

<div class="modal fade" id="connectModal" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><span class="glyphicon glyphicon-ok">  연결 요청하기</span></h4>
      </div>
      <div class="modal-body" id="connectBody">
        <p><strong>철연이네 홈</strong>에 연결요청을 하시겠습니까?</p>
      </div>
      <div class="modal-footer" id="myButton">
     	<input type="button" id="connectOkBtn" class="buttons" alt="확인"/>
        <input type="button" id="connectCancelBtn" class="buttons" alt="취소"/> 
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 연결 끊기 모달 -->

<div class="modal fade" id="disconnectModal" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title"><span class="glyphicon glyphicon-remove">  연결 해제하기</span></h4>
      </div>
      <div class="modal-body" id="connectBody">
        <p><strong>철연이네 홈</strong>과 연결을 끊으시겠습니까?</p>
      </div>
      <div class="modal-footer" id="myButton">
     	<input type="button" id="disconnectOkBtn" class="buttons" alt="확인"/>
        <input type="button" id="disconnectCancelBtn" class="buttons" alt="취소"/> 
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script>

$(document).ready(function(){
	
	var neighborCheck = 0; // 연결상태 0:연결X, 1:연결O -> 후에 DB에서 받아옴
	
	//연결상태 체크
	
	if(neighborCheck == 1) // 연결 상태이면
	{
		$("#connectImg").hide(); // 연결요청 이미지 X
		$("#alreadyConnectImg").show(); // 이미연결됨 이미지 O
		$("#disconnectImg").show(); // 연결끊기 이미지 O
	}
	else // 연결상태가 아니면
	{
		$("#connectImg").show(); // 연결요청 이미지O
		$("#alreadyconnectImg").hide(); // 이미연결됨 이미지 X
		$("#disconnectImg").hide(); // 연결끊기 이미지 X
	}
	
	
	//연결요청
	
	$("#connectImg").click(function(){
		$("#connectModal").modal();
	});
	
	$("#connectOkBtn").click(function(){
		neighborCheck=1;
		$("#connectModal").modal("hide");
		alert("연결되셨습니다." + neighborCheck);
	});
	
	$("#connectCancelBtn").click(function(){
		$("#connectModal").modal("hide");
	});
	
	
	//연결해제
	
	$("#disconnectOkBtn").click(function(){ // 연결해제가 되면
		neighborCheck=0;
		$("#disconnectModal").modal("hide");
		$("#connectImg").show(); // 연결요청 이미지 보이기
	
		alert("연결이 끊겼습니다." + neighborCheck);
	});
	
	$("#disconnectCancelBtn").click(function(){
		$("#disconnectModal").modal("hide");
	});
	
	$("#disconnectImg").click(function(){
		$("#disconnectModal").modal();
	});
	
});


</script>
</body>
</html>