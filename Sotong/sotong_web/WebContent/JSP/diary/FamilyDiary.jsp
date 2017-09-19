<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href='css/familyDiary/familyDiary.css' rel='stylesheet'>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="js/familyDiary/familyDiary.js"></script>
<title>가족 일기 보다</title>
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
							<c:forEach var="diaryInfo" items="${simpleFamilyDiaryList}">
							<div id="family-diary-list-summary">
								<input type="hidden" value = "${diaryInfo.familyDiaryCode }">
								<input type="button" value="${diaryInfo.familyDiaryDate }" id="family-diary-list-title-btn"/>
							</div>		
							</c:forEach>					
							<!-- foreach 구문 작성한다. -->							
						</form>
					</div>
					<input type="image" src="image/downBtn.JPG" id="down-btn"/>
				</div>
				
			</div>
			
			<!-- 오른쪽 가족 일기를 제공 해주는 전체 틀이다. -->
			<div id="family-diary-board">
				
				<!-- 글쓰기 이미지  -->
				<div id="family-diary-board-button">
					<a href="<%=request.getContextPath()%>/familyDiaryList.do">
					<div id="family-diary-board-write-button" class="family-diary-button">
						<input type="image" id="write-btn" class="famil-diary-button" src="image/image-wirteBtn.png"/>
						<input type="image" id="write-letter" class="famil-diary-button" src="image/wirteBtn.png"/>
					</div>
					</a>
					<!-- 삭제 버튼 이다. -->
					<div id="family-diary-board-delete-button">
						<input type="image" id="delete-letter" class="famil-diary-button" src="image/deleteBtn.png"/>
						<input type="image" id="delete-btn" class="famil-diary-button" src="image/image-deleteBtn.png"/>
					</div>
					<!-- 수정 버튼 이다. -->
					<div id="family-diary-board-modify-button">
						<input type="image" id="modify-btn" class="famil-diary-button" src="image/image-modifyBtn.png"/>
						<input type="image" id="modify-letter" class="famil-diary-button" src="image/modifyBtn.png"/>
					</div>
				</div>
				
				<!-- 가족 이야기 게시물의 전체 테두리 -->
				<div id="family-diary-board-all">
					<!-- 프로필, 가족이야기 한 사람의 게시판의 전체 틀 -->
					<div id="family-diary-board-info">
						<form>
						
							<input type="hidden" value="${fDiaryInfo.familyDiaryCode}"/>
							<!-- foreach 구문 -->
							<!-- 프로필 정보를 갖고 있는 틀 -->
							<c:forEach var="diaryPartInfo" items="${fDiaryInfo.familyDiaryPart}">
							
							<div id="family-diary-profile-info">
								<input type="hidden" value="${diaryPartInfo.familyDiaryPartCode }"/>
								<!-- 프로필 사진 -->
								<div id="profile-photo">
								</div>
								<!-- 이름 -->
								<div id="profile-name">
									<span id="profile-name">${diaryPartInfo.memberNickname}</span>
								</div>
								<!-- 작성 날짜 -->
								<div id="family-diary-write-date">
									<span id="family-diary-write-date">${diaryPartInfo.familyDiaryPartDate}에 작성</span>
								</div>
							</div>
							
							<!-- 한 사람이 작성한 일기의 내용 -->
							<div id="family-diary-board-content">
								<div id="family-diary-textarea">
									<textarea id='family-diary-board-textarea' readonly>
									${diaryPartInfo.contents}
									</textarea>
								</div>
							</div>
							</c:forEach>	
							<!-- foreach 구문 -->						
						</form>
					</div>
				</div>
				
				<div id="comment-write" class="family-board-down">
					<div id="comment-photo">
						<div id="comment-profile"></div>
						<div id="comment-content-all">
							<input type="text" id="comment-content" maxlength=50/>
							<input type="image" id='comment-emoticon' src="image/emoticon-cool.png"/>
							<input type="button" id="comment-btn" value="전송"/>
						</div>
					</div>
				</div>
			</div>			
		</div>
	</div>
</body>
</html>