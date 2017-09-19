$(document).ready(function(){
    $("#addFamScheBtn").click(function(){	// 일정추가 버튼 클릭 시, 모달창 나옴.
        $("#ourAddSche").modal();
    });

    $("#inHomeImg").click(function(){	// 모달 창 밖에 클릭 시, 모달창 꺼지는거
    	$("#ourAddSche").fadeIn();
    });   
    
    $("#okBtn").click(function() {		// 등록버튼.
    	if($("#scheduleTitle").val() == "")		// 제목을 입력하지 않았을 경우.
    	{
    		window.alert("제목을 입력해주세요.");	
    	}
    	else if($("#eventSelecBtn").prop("checked"))
    	{
    		if($("#eventQ").val() == "")
    		{
    			window.alert("요청할 질문을 입력해주세요.");
    		}
    	}
    	else
    	{
    		if($("#allDayBtn").prop("checked"))		//체크가 되어 있다면
        	{
        		$("#startTime").val("00:01");	// 하루종일 클릭시 일정 시간을 00:00~23:59 로 설정. (임시)
        		$("#endTime").val("23:59");   		
        	}
    		$(".form-horizontal").submit();
    		window.alert($("#startTime").val());
    	} 
    });
    
    
    $("#allDayBtn").click(function(){		//하루종일 버튼 클릭 시 동작.
    	
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
    
    
    $("#eventSelecBtn").click(function(){		//하루종일 버튼 클릭 시 동작.
    	
    	if($("#eventSelecBtn").prop("checked"))		//체크가 되어 있다면
    	{
	  		$("#famSelec").show();				// 시간 관련 input태그들을 안보이게 하고
    		$("#eventDiv").show();
    	}
    	else
    	{
    		$("#famSelec").hide();					//아니라면 원래대로 복구.
    		$("#eventDiv").hide();
    	}
    });
});