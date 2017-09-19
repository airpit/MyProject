$(document).ready(function(){
			
		$("#deleteBtn").click(function(){
			alert("가족구성원 삭제 클릭됨");
		});
		
		$("#managerBtn").click(function(){
			alert("매니저권한 위임 선택됨");
		});
		
		$("#personColor").click(function(){
			alert(this.val());
		});
		
		
		$('#manager').hover(function(){
			//마우스를 수정 버튼으로 올리시 이베튼 발생
			$("#manager").css("display", "none");
			$("#managerBtn").css("display", "block");
		});
		$('#delete').hover(function(){
			//마우스를 글쓰기 버튼으로 올리시 이벤트 발생
			$("#delete").css("display", "none");
			$("#deleteBtn").css("display", "block");
		});
		$('#managerBtn').mouseout(function(){
			//마우스를 요소에서 제거 하면 발생하는 함수
			$("#managerBtn").css("display", "none");
			$("#manager").css("display", "block");
		});
		$('#deleteBtn').mouseout(function(){
			//마우스를 요소에서 제거 하면 발생하는 함수
			$("#deleteBtn").css("display", "none");
			$("#delete").css("display", "block");		
		});
		
		
	});
