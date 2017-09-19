<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href='css/wishList/wishList.css' rel='stylesheet'>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="js/wishList/wishList.js"></script>
<title>소망상자 진행화면</title>
</head>
<body>


	<div>
		<jsp:include page="../../mainMenu.jsp"></jsp:include>
	</div>
	
	<div id='wish-list-all'>
		<div id="wish-list-body">
			
			<!-- 왼쪽에 제공되는 개인 일기 목록이다. -->
			<div id="wish-list-list">
				<span id="wish-list-list-title">소망 상자</span>
				<!-- 글쓰기 버튼 -->
				<div id="write-btn-border">
					<input type='image' id="write-btn" class='famil-list-button' src='image/image-wirteBtn.png'/>
					<input type='image' id="write-letter" class='famil-list-button' src='image/wirteBtn.png'/>
				</div>
				<div id="wish-list-list-info">
					<input type="image" src="image/upBtn.JPG" id="up-btn"/>
					<div id='wish'>
						<form>	<!-- 위에 버튼 아래 버튼 설정 할 수 있게 form 처리 -->
						<!--  여기서 부터 목록을 가져오는 foreach 구문  -->
							<div id="wish-list-summary">	
								<input type="text" id="wish-list-date" readonly value="2015-07-21" size=9/>
								<br>
								<input type="button" value="제목1" id="wish-list-title-btn"/>
							</div>
						<!-- 여기까지 foreach로 묶으면 도니다. -->
						</form>
					</div>
					<input type="image" src="image/downBtn.JPG" id="down-btn"/>
				</div>
				
			</div>			
			
			<!-- 오른쪽 위시 리스트 보다를 제공 해주는 전체 틀이다. -->
			<div id="wish-list-board-border">
				<!-- foreach 구문 -->
				<!-- 한 사람의 위시 리스트 소망 상자 게시물 -->
				<div id="wish-list-board">
					<!-- 작성자 : 작성자 명   작성날짜 : 작성날짜 의 테두리이다. -->
					<div id="wish-list-write-text-border">
						<div id="wish-list-write-text">	
							<span class="write-text" id='write-text-name-span'>작성자 : </span>
							<input type='text' class='write-text' id='write-text-name' value='김철연'/>
							<span class="write-text" id='write-text-date-span'>작성날짜 : </span>
							<input type='text' class='write-text' id='write-text-date' value='2015-07-25'/>
						</div>
					</div>
					<!-- 소망상자의 제목 테두리이다. -->
					<div id="wish-list-title-text-border">
						<input type='text' id='wish-list-title-text' value="파이널프로젝트 무사히 완료!!" readonly/>
					</div>
					<!-- 소망상자의 내용 테두리이다. -->
					<div id="wish-list-content-text-border">
						<input type='textarea' id='wish-list-content-text' value="얼마 안남았다!! 모두 힘내서 마무리해요! " readonly/>
					</div>
					<!-- 삭제, 수정, 완료 버튼 테두리 -->
					<div id="wish-list-button-border">
						<!-- 완료 버튼 테두리 -->
						<div id='wish-list-finish-button-border' class='wish-list-button'>
							<input type='image' id='wish-list-finish-button-image' class='famil-list-button' src='image/image-okBtn.png'/>
							<input type='image' id='wish-list-finish-button-letter' class='famil-list-button' src='image/letter-finish-btn.png'/>							
						</div>
						<!-- 수정 버튼 테두리 -->
						<div id='wish-list-modify-button-border' class='wish-list-button'>
							<input type='image' id='wish-list-modify-button-image' class='famil-list-button' src='image/image-modifyBtn.png'/>
							<input type='image' id='wish-list-modify-button-letter' class='famil-list-button' src='image/modifyBtn.png'/>							
						</div>
						<!-- 삭제 버튼 테두리 -->
						<div id='wish-list-delete-button-border' class='wish-list-button'>
							<input type='image' id='wish-list-delete-button-image' class='famil-list-button' src='image/image-deleteBtn.png'/>
							<input type='image' id='wish-list-delete-button-letter' class='famil-list-button' src='image/deleteBtn.png'/>							
						</div>
					</div>
					<!-- 일정날짜 테두리 -->
					<div id='wish-list-schedule-border' class='schedule-date-border'>
						<span id='wish-list-schedule' class='schedule-date'>일정날짜 : </span>
						<input type='text' id='wish-list-schedule-date' class='schedule-date' value='2015-08-28'/>
					</div>
					<!-- 일정 완료날짜 테두리 -->
					<div id='wish-list-finish-schedule-border' class='schedule-date-border'>
						<span id='wish-list-finish-schedule' class='schedule-date'>완료날짜 : </span>
						<input type='text' id='wish-list-finish-schedule-date' class='schedule-date' value='2015-08-28'/>
					</div>
				</div>
				<!-- foreach 구문 -->	
				<div id="wish-list-board">
					<!-- 작성자 : 작성자 명   작성날짜 : 작성날짜 의 테두리이다. -->
					<div id="wish-list-write-text-border">
						<div id="wish-list-write-text">	
							<span class="write-text" id='write-text-name-span'>작성자 : </span>
							<input type='text' class='write-text' id='write-text-name' value='김철연'/>
							<span class="write-text" id='write-text-date-span'>작성날짜 : </span>
							<input type='text' class='write-text' id='write-text-date' value='2015-07-25'/>
						</div>
					</div>
					<!-- 소망상자의 제목 테두리이다. -->
					<div id="wish-list-title-text-border">
						<input type='text' id='wish-list-title-text'/>
					</div>
					<!-- 소망상자의 내용 테두리이다. -->
					<div id="wish-list-content-text-border">
						<input type='textarea' id='wish-list-content-text'/>
					</div>
					<!-- 삭제, 수정, 완료 버튼 테두리 -->
					<div id="wish-list-button-border">
						<!-- 완료 버튼 테두리 -->
						<div id='wish-list-finish-button-border' class='wish-list-button'>
							<input type='image' id='wish-list-finish-button-image' class='famil-list-button' src='image/image-okBtn.png'/>
							<input type='image' id='wish-list-finish-button-letter' class='famil-list-button' src='image/letter-finish-btn.png'/>							
						</div>
						<!-- 수정 버튼 테두리 -->
						<div id='wish-list-modify-button-border' class='wish-list-button'>
							<input type='image' id='wish-list-modify-button-image' class='famil-list-button' src='image/image-modifyBtn.png'/>
							<input type='image' id='wish-list-modify-button-letter' class='famil-list-button' src='image/modifyBtn.png'/>							
						</div>
						<!-- 삭제 버튼 테두리 -->
						<div id='wish-list-delete-button-border' class='wish-list-button'>
							<input type='image' id='wish-list-delete-button-image' class='famil-list-button' src='image/image-deleteBtn.png'/>
							<input type='image' id='wish-list-delete-button-letter' class='famil-list-button' src='image/deleteBtn.png'/>							
						</div>
					</div>
					<!-- 일정날짜 테두리 -->
					<div id='wish-list-schedule-border' class='schedule-date-border'>
						<span id='wish-list-schedule' class='schedule-date'>일정날짜 : </span>
						<input type='text' id='wish-list-schedule-date' class='schedule-date' value='2015-08-28'/>
					</div>
					<!-- 일정 완료날짜 테두리 -->
					<div id='wish-list-finish-schedule-border' class='schedule-date-border'>
						<span id='wish-list-finish-schedule' class='schedule-date'>완료날짜 : </span>
						<input type='text' id='wish-list-finish-schedule-date' class='schedule-date' value='2015-08-28'/>
					</div>
				</div>
				<!-- foreach 구문 -->	<div id="wish-list-board">
					<!-- 작성자 : 작성자 명   작성날짜 : 작성날짜 의 테두리이다. -->
					<div id="wish-list-write-text-border">
						<div id="wish-list-write-text">	
							<span class="write-text" id='write-text-name-span'>작성자 : </span>
							<input type='text' class='write-text' id='write-text-name' value='김철연'/>
							<span class="write-text" id='write-text-date-span'>작성날짜 : </span>
							<input type='text' class='write-text' id='write-text-date' value='2015-07-25'/>
						</div>
					</div>
					<!-- 소망상자의 제목 테두리이다. -->
					<div id="wish-list-title-text-border">
						<input type='text' id='wish-list-title-text'/>
					</div>
					<!-- 소망상자의 내용 테두리이다. -->
					<div id="wish-list-content-text-border">
						<input type='textarea' id='wish-list-content-text'/>
					</div>
					<!-- 삭제, 수정, 완료 버튼 테두리 -->
					<div id="wish-list-button-border">
						<!-- 완료 버튼 테두리 -->
						<div id='wish-list-finish-button-border' class='wish-list-button'>
							<input type='image' id='wish-list-finish-button-image' class='famil-list-button' src='image/image-okBtn.png'/>
							<input type='image' id='wish-list-finish-button-letter' class='famil-list-button' src='image/letter-finish-btn.png'/>							
						</div>
						<!-- 수정 버튼 테두리 -->
						<div id='wish-list-modify-button-border' class='wish-list-button'>
							<input type='image' id='wish-list-modify-button-image' class='famil-list-button' src='image/image-modifyBtn.png'/>
							<input type='image' id='wish-list-modify-button-letter' class='famil-list-button' src='image/modifyBtn.png'/>							
						</div>
						<!-- 삭제 버튼 테두리 -->
						<div id='wish-list-delete-button-border' class='wish-list-button'>
							<input type='image' id='wish-list-delete-button-image' class='famil-list-button' src='image/image-deleteBtn.png'/>
							<input type='image' id='wish-list-delete-button-letter' class='famil-list-button' src='image/deleteBtn.png'/>							
						</div>
					</div>
					<!-- 일정날짜 테두리 -->
					<div id='wish-list-schedule-border' class='schedule-date-border'>
						<span id='wish-list-schedule' class='schedule-date'>일정날짜 : </span>
						<input type='text' id='wish-list-schedule-date' class='schedule-date' value='2015-08-28'/>
					</div>
					<!-- 일정 완료날짜 테두리 -->
					<div id='wish-list-finish-schedule-border' class='schedule-date-border'>
						<span id='wish-list-finish-schedule' class='schedule-date'>완료날짜 : </span>
						<input type='text' id='wish-list-finish-schedule-date' class='schedule-date' value='2015-08-28'/>
					</div>
				</div>
				<!-- foreach 구문 -->	<div id="wish-list-board">
					<!-- 작성자 : 작성자 명   작성날짜 : 작성날짜 의 테두리이다. -->
					<div id="wish-list-write-text-border">
						<div id="wish-list-write-text">	
							<span class="write-text" id='write-text-name-span'>작성자 : </span>
							<input type='text' class='write-text' id='write-text-name' value='김철연'/>
							<span class="write-text" id='write-text-date-span'>작성날짜 : </span>
							<input type='text' class='write-text' id='write-text-date' value='2015-07-25'/>
						</div>
					</div>
					<!-- 소망상자의 제목 테두리이다. -->
					<div id="wish-list-title-text-border">
						<input type='text' id='wish-list-title-text'/>
					</div>
					<!-- 소망상자의 내용 테두리이다. -->
					<div id="wish-list-content-text-border">
						<input type='textarea' id='wish-list-content-text'/>
					</div>
					<!-- 삭제, 수정, 완료 버튼 테두리 -->
					<div id="wish-list-button-border">
						<!-- 완료 버튼 테두리 -->
						<div id='wish-list-finish-button-border' class='wish-list-button'>
							<input type='image' id='wish-list-finish-button-image' class='famil-list-button' src='image/image-okBtn.png'/>
							<input type='image' id='wish-list-finish-button-letter' class='famil-list-button' src='image/letter-finish-btn.png'/>							
						</div>
						<!-- 수정 버튼 테두리 -->
						<div id='wish-list-modify-button-border' class='wish-list-button'>
							<input type='image' id='wish-list-modify-button-image' class='famil-list-button' src='image/image-modifyBtn.png'/>
							<input type='image' id='wish-list-modify-button-letter' class='famil-list-button' src='image/modifyBtn.png'/>							
						</div>
						<!-- 삭제 버튼 테두리 -->
						<div id='wish-list-delete-button-border' class='wish-list-button'>
							<input type='image' id='wish-list-delete-button-image' class='famil-list-button' src='image/image-deleteBtn.png'/>
							<input type='image' id='wish-list-delete-button-letter' class='famil-list-button' src='image/deleteBtn.png'/>							
						</div>
					</div>
					<!-- 일정날짜 테두리 -->
					<div id='wish-list-schedule-border' class='schedule-date-border'>
						<span id='wish-list-schedule' class='schedule-date'>일정날짜 : </span>
						<input type='text' id='wish-list-schedule-date' class='schedule-date' value='2015-08-28'/>
					</div>
					<!-- 일정 완료날짜 테두리 -->
					<div id='wish-list-finish-schedule-border' class='schedule-date-border'>
						<span id='wish-list-finish-schedule' class='schedule-date'>완료날짜 : </span>
						<input type='text' id='wish-list-finish-schedule-date' class='schedule-date' value='2015-08-28'/>
					</div>
				</div>
				<!-- foreach 구문 -->	
			</div>			
		</div>
	</div>
</body>
</html>