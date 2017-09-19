
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
	
	//이웃프로필 상세보기로 이동
	$("#family-profile-ul li").click(function(){

		var index = $("#family-profile-ul .family-profile-li").index(this);
		$("#neighbor-profile-form"+index).submit();
	});
	
});
