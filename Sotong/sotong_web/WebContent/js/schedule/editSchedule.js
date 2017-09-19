$(document).ready(function(){
    $("#editScheBtn").click(function(){	// 일정편집 버튼 클릭 시, 모달창 나옴.
        $("#myEditSche").modal();
    });

    $("#inHomeImg").click(function(){	// 모달 창 밖에 클릭 시, 모달창 꺼지는거
    	$("#myEditSche").fadeIn();
    });   
    
    $("#okBtn").click(function() {		// 등록버튼.
    	if($("#scheduleTitle").val() == "")		// 제목을 입력하지 않았을 경우.
    	{
    		window.alert("제목을 입력해주세요.");
    	}
    	else
    	{
    		if($("#allDayBtn").prop("checked"))		//체크가 되어 있다면
        	{
        		$("#startTime").val("00:01");	// 하루종일 클릭시 일정 시간을 00:00~23:59 로 설정. (임시)
        		$("#endTime").val("23:59");   		
        	}
    		$(".form-horizontal").submit();
    		window.alert("일정 수정이 완료되었습니다.");
    	} 
    });
    
    $("#cancelBtn").click(function(){	//취소 버튼 클릭 시,
    	$(".editScheBtn").show();		// 편집 버튼 들을 다시 보이게 한다.
    	$(".addScheBtn").hide();		// 수정 버튼들은 다시 숨긴다
    	
    	$("#menuName").text("일정 상세보기");	// 제목을 일정 상세보기로 수정한다.
    	$("input").attr("readonly",true);	// 수정이 불가능하도록 고친다.
    	document.all["familyAlarmSelec"].disabled=true;		// select 수정 불가능하게 바꾸는거.
    	document.all["alarmSelec"].disabled=true;
    	document.all["repeatSelec"].disabled=true;
    });
  
    $("#modifyBtn").click(function(){	// 수정 버튼 클릭 시,
    	
    	$(".editScheBtn").hide();		// 편집 버튼들을 숨긴다.
    	$(".addScheBtn").show();		// 수정 버튼들을 보이게 한다.
    	
    	$("#menuName").text("일정 편집하기");	// 제목을 일정 편집하기로 수정한다.
    	$("input").attr("readonly",false);	// 수정이 가능하게 한다.
    	document.all["familyAlarmSelec"].disabled=false;
    	document.all["alarmSelec"].disabled=false;
    	document.all["repeatSelec"].disabled=false;
    });
    
    $("#deleteBtn").click(function(){
    	alert("삭제 하시겠습니까?");
    });
    
    $("#backBtn").click(function(){
    	  $("#myEditSche").modal("hide");
    });
  
    $("#allDayBtn").click(function(){		//하루종일 버튼 클릭 시 동작.
    	
		if($("#menuName").text()=="일정 상세보기")		//상세보기 모드일 경우에는 편집이 되지 않도록.
		{
			return false;	
		}
    	
    	if($("#allDayBtn").prop("checked"))		//체크가 되어 있다면
    	{
	  		$("#startTime").hide();				// 시간 관련 input태그들을 안보이게 하고
    		$("#endTime").hide();
    		$("#startDate").css('width','300px');	// 날짜 관련 input 태그들의 길이를 늘린다.
    		$("#endDate").css('width','300px');
    	}
    	else
    	{
    		$("#startTime").show();					//아니라면 원래대로 복구.
    		$("#endTime").show();
    		$("#startDate").css('width','205px');
    		$("#endDate").css('width','205px');
    	}
    });
});