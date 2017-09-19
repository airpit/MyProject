$(document).ready(function(){
		$('#write-btn').on('click',storyWrite);	//가족 이야기 작성 버트 클릭 시 작동.
		$('.li-story-list .delete').on('click',storyDelete);
		
		$('#modify-btn').on('click',familyBorderModify);	//가족 이야기 수정 버튼 클릭 시 familyBorderModify 함수 호출
		$('#comment-btn').on('click',commentWrite);	//댓글 작성 시 실행 되는 창
		
});

function storyWrite() {
	//가족 이야기 작성 버튼 클릭 시 작동하는 함수
	var content = $('#family-board-content-write').val();
	if (content == "") {
		alert("내용을 입력하세요");
	} else {
		$('#content').val("");
		$('#family-body-write').submit();
	}
}

function familyBorderModify() {
	//가족 이야기 수정 버튼 클릭 시 작동한느 함수
	$('#family-board-content-read').val("이미로 처리 한다.");	//textarea 값 넣어서 보여주는 코드
	var content = $('#family-board-content-read').val();	//내용의 데이터를 가져온다.
	alert(content);		//수정된 값을 가져온다.
	
}

function commentWrite() {
	var comment = $('#comment-content').val();
	if (comment == "") {
		alert("댓글을 작성해주세요");
	} else {
		alert(comment);
	}
}

// 삭제 버튼이 크릭 되었을ㄸ ㅐ!
function storyDelete() {
	//삭제 처리 함수	
	var result = confirm('정말로 삭제 하시겠습니까?' + "\n" + '삭제되면 복구가 불가능합니다.');
	 
     if(result) {
    	var index = $("li .delete").index(this);
    	$('#story-index').val(index); 
    	alert("들어가자이제");
    	$('.li-story-list .family-delete-form').submit();
     } else {
    	 //no
     }	
}
