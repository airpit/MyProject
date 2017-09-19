<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>소통-가족 프로필</title>

<!-- 외부 링크 -->
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<!-- 내꺼 -->
<link href="css/neighbor/neighborProfile.css" rel="stylesheet">
<script src="js/neighbor/neighborProfile.js" type="text/javascript" charset="utf-8"></script>

</head>
<body>

<div>
	<jsp:include page="../../mainMenu.jsp"></jsp:include>
</div>

<div id="homeTitle">
	<span>${homeName}</span> <%-- EL로 바꿀 곳 --%>
</div>

	<input type="button" id="backBtn">

	<div id="tableDiv" class="table-responsive">
		<table class="span12" id="neighborProfile">
		
		<tr>
			<th colspan="2" data-align="center">
				<img src="${memberInfo.memberPhoto }" class="img-circle" id="profileImg"/>			
			</th>
		</tr>
		<tr>
			<th colspan="2" data-align="center">
				<label class="text-center">${memberInfo.memberName }</label>
			</th>
		</tr>
		
		<tr>
			<th>이메일 주소</th>
			<td>${memberInfo.memberEmail}</td>
		</tr>
		<tr>
			<th>생일</th>
			<td>
			${birth}
			</td>
		</tr>
		<tr>
			<th>별명</th>
			<td>${memberInfo.memberNickName}</td>
		</tr>
		<tr>
			<th>색상</th>
			<td>
			<input type="color" id="personColor" value="${memberInfo.memberColor }" disabled>
			</td>
		</tr>
		</table>
	</div>
	
</body>
</html>