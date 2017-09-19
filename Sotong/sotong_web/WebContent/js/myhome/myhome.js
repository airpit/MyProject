
$(document).ready(function(){
	
	$("#neighborBtn2").click(function(){
		window.location.href = "neighbor.do"; 		
	});
	
	$('#neighborBtn').hover(function(){
        //마우스를 수정 버튼으로 올리시 이벤트 발생
        $("#neighborBtn").css("display", "none");
        $("#neighborBtn2").css("display", "block");
     });
	
	$('#inviteBtn').hover(function(){
        //마우스를 수정 버튼으로 올리시 이베튼 발생
        $("#inviteBtn").css("display", "none");
        $("#inviteBtn2").css("display", "block");
     });
	
	$('#neighborBtn2').mouseout(function(){
        //마우스를 수정 버튼으로 올리시 이베튼 발생
        $("#neighborBtn2").css("display", "none");
        $("#neighborBtn").css("display", "block");
     });
	
	$('#inviteBtn2').mouseout(function(){
        //마우스를 수정 버튼으로 올리시 이베튼 발생
        $("#inviteBtn2").css("display", "none");
        $("#inviteBtn").css("display", "block");
     });
	
	$("#editName").click(function(){
		$("#changeNameModal").modal();
	});
	
	$(".li-family-list .delete").click(function(){
		if(confirm("해당 가족구성원과 연결을 끊으시겠습니까?")==true)
			{
				var index = $("li .delete").index(this);
				
				$(".li-family-list").eq(index+1).remove();
				
				//연결끊기 작업수행
				
				/*$("#detail-user-form").submit();*/
			}			
	});
	
	$("#inviteBtn2").click(function(){
		$("#inviteModal").modal();
	});
	
	$("#changeCancelBtn").click(function(){
		$("#changeNameModal").modal("hide");
	});
	
	$("#inviteCancelBtn").click(function(){
		$("#inviteModal").modal("hide");
	});
	
	$("#changeOkBtn").click(function(){
//		var newName = $("#changeName").val();
//		$("#homeName").text(newName);
		
		var homeName = $("#changeName").val();
		alert(homeName);
//		loadXMLDoc(homeName);
//		$("#rename-home-form").submit();
//		$.ajax({
//            url: "renameHome.do",
//            type: "get",
//            data: homeName,
//            success: function(data) {
		
			$.get('renameHome.do', function(homeName){
				alert(homeName);
				$('#homeName').val(homeName);
				$("#changeNameModal").modal("hide");
        		alert("홈 이름이 변경되었습니다.");
			});

	});
	
	$("#inviteOkBtn").click(function(){
		
		
		$("#inviteModal").modal("hide");
		alert("초대가 완료되었습니다.");	
	
	});
	
	$("#family-member-info-a").click(function(){
		
		$("#detail-user-form").submit();
	});	
	
//	function loadXMLDoc(name)
//	{
//	var xmlhttp;
//	
//	if (window.XMLHttpRequest)
//	  {// code for IE7+, Firefox, Chrome, Opera, Safari
//		xmlhttp=new XMLHttpRequest();
//	  }
//	else
//	  {// code for IE6, IE5
//		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
//	  }
//	xmlhttp.onreadystatechange=function()
//	  {
//	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
//	    {
//		  var data = xmlhttp.responseText;
//		  alert(data);
////		  $("#homeName").text(xmlhttp.responseText);
//	    }
//	  }
//	xmlhttp.open("GET","/renameHome.do",true);
//	xmlhttp.send(name);
//	}
	
	
});
