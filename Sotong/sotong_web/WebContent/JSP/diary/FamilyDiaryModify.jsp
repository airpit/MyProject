<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href='css/familyDiary/familyDiaryModify.css' rel='stylesheet'>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="js/familyDiary/familyDiaryModify.js"></script>
<title>가족 일기 작성하다</title>
</head>
<body>
	
	<div>
		<jsp:include page="../../mainMenu.jsp"></jsp:include>
	</div>
	
	<div id='family-diary-all'>
		<div id="family-diary-body">	
			
			<!-- 왼쪽에 제공 되는 목록 틀이다. -->
			<div id="family-diary-list">
				<span id="family-diary-list-title">우리 가족일기</span>
				<div id="family-diary-list-info">
					<input type="image" src="image/upBtn.JPG" id="up-btn"/>	
					<div id="family">
						<form>	
							<!-- foreach 구문 작성한다. -->
							<div id="family-diary-list-summary">
								<input type="button" value="2015-07-21" id="family-diary-list-title-btn"/>
							</div>							
							<!-- foreach 구문 작성한다. -->
						</form>
					</div>
					<input type="image" src="image/downBtn.JPG" id="down-btn"/>
				</div>
			</div>
			
			<!-- 오른쪽 가족 일기를 제공 해주는 전체 틀이다. -->
			<div id="family-diary-board">
					<!-- 수정 이미지  -->
				<input type="image" id="modify-btn" class="write" src="image/image-modifyBtn.png"/>				
				<!-- 가족 이야기 게시물의 전체 테두리 -->
				<div id="family-diary-board-all">
					<!-- 프로필, 가족이야기 한 사람의 게시판의 전체 틀 -->
					<div id="family-diary-board-info">
						<form>
							<!-- 한 사람이 작성한 일기의 내용 -->
							<div id="family-diary-board-content">
								<div id="family-diary-textarea">
									<textarea id='family-diary-board-textarea'>축하드립니다~ 으라라라라랏차~!</textarea>
								</div>
								<!-- 사진 버튼 -->
								<div id="add-photo-button" class="family-diary-button">
									<input type="image" id="add-photo-letter" class="famil-diary-button" src="image/letter-addPhoto.png"/>
									<input type="image" id="add-photo-btn" class="famil-diary-button" src="image/image-addPhotoBtn.png"/>
									
								</div>
								<!-- 확인 버튼 -->
								<div id="ok-button" class="family-diary-button">
									<input type="image" id="ok-letter" class="famil-diary-button" src="image/letter-ok.png"/>
									<input type="image" id="ok-btn" class="famil-diary-button" src="image/image-okBtn.png"/>
								</div>
								<!-- 돌아가기 버튼 -->
								<div id="back-button" class="family-diary-button">
									<input type="image" id="back-letter" class="famil-diary-button" src="image/letter-back.png"/>
									<input type="image" id="back-btn" class="famil-diary-button" src="image/image-backBtn.png"/>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>			
		</div>
	</div>
</body>
</html>