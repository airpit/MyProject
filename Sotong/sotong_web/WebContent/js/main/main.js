$(document).ready(function(){
    $("#loginImg").click(function(){
        $("#myLogin").modal();
    });
    
    $("#signupImg").click(function(){
    	$('.form-control').val("");
        $("#mySignup").modal();
    });  
   
    $("#inBtn").click(function() {
    	if($("#userId").val()=="" || $("#userPw").val()=="") {
    			alert("ID 또는 PW를 입력 하시오");
    	} else {
    		//Web 은 앞에 2000으로 넣어 구분해준다.		
    		$('#form-login').submit();
    		//window.location.href = "JSP/home/myhome.jsp";
    	}
    });
    
    $("#okBtn").click(function() {   	
    	var phoneNum = $('#Phone-1').val() + '-' + $('#Phone-2').val() + '-' + $('#Phone-3').val();
    	$('#join-phone-num').val(phoneNum);
    	
    	$("#mySignup").modal("hide");
      	
    	$('#form-join').submit();
    });
    
    $("#cancelBtn").click(function(){
    	$("#mySignup").modal("hide");
    });
});