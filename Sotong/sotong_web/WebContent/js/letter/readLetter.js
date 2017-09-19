$(document).ready(function(){
	$("#deleteBtn").click(function(){
		if(confirm("삭제하시겠습니까?")==true)
			{
				//삭제작업
				alert("삭제되었습니다.");
				window.location.href = "letterbox.jsp";
			}
	});
	
	$("#writeBtn").click(function(){
		window.location.href = "writeLetter.jsp";
	});

});