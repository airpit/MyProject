<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>우체통</title>

<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
 <link href="css/letter/letterbox.css" rel="stylesheet"></link>
 <script src="js/letter/letterbox.js" type="text/javascript" charset="UTF-8"></script>

</head>
<body>	
	<div>
		<jsp:include page="../../mainMenu.jsp"></jsp:include>
	</div>

	<div id="letter-head-list-border">
		<input type="text" id="letter-head-list" value="편지읽기"/>
	</div>
	<div id="letterView">
		<div id="letterHead" class="letter-border">
			<ol class="letters">
				<li class="letterInfo">
					<span class="check"><input type="checkbox" id="checkAll" class="checkbox-danger" ></span>
					<span class="title"><label id="letter-title-sample" class="letterTitle">편지제목</label></span>
					<span class="writer"><label id="letter-writer-sample" class="letterWriter">보낸이</label></span>
					<span class="date"><label id="letter-date-sample" class="letterDate">날짜</label></span>
				</li>
			</ol>
		</div>
		<div id="letterList" class="letter-border">
			<!-- 편지 목록 -->
			<ol class="letters">
				<!-- 편지 한개의 정보 -->
				<li class="letterInfo">		
					<span class="check"><input type="checkbox" id="check" class="checkbox-danger">
					</span>				
					<a href="readLetter.jsp"><span class="title"><label class="letterTitle" id="letterTitle">철연아 힘내dddddddddddddddddddddddddd</label>
					</span></a>				
					<span class="writer"><label class="letterWriter" id="letterWriter">장한별</label>
					</span>				
					<span class="date"><label class="letterDate" id="letterDate">2015/07/23</label>
					</span>
				</li>
			</ol>
		</div>
		
	</div>

	<div id="button-side">
		<input type="button" id="backBtn" class="buttons">
		<input type="button" id="deleteBtn" class="buttons">
		<input type="button" id="writeBtn" class="buttons">
	</div>
</body>
</html>