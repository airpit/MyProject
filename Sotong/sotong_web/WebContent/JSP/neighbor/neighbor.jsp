<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>소통 - 이웃</title>

<!-- 부트스트랩 -->
 <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
 <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<!-- 내꺼 -->
<link rel="stylesheet" href="css/neighbor/neighbor.css">
<script src="js/neighbor/neighbor.js" charset="UTF-8" type="text/javascript"></script>
</head>
<body>
<div>
	<jsp:include page="../../mainMenu.jsp"></jsp:include>
</div>

<div class="tabbable tabs-left">
  <!-- 탭 리스트 -->
  <ul class="nav nav-pills" role="tablist" id="neighborTab">
    <li role="presentation" class="active"><a href="#showNeighbor" aria-controls="showNeighbor" role="tab" data-toggle="tab">이웃보기</a></li>
    <li role="presentation"><a href="#searchNeighbor" aria-controls="searchNeighbor" role="tab" data-toggle="tab">이웃찾기</a></li>
  </ul>

  <!-- 탭 내용 -->
  <div class="tab-content">
  
  	<!-- 이웃보기 -->
    <div role="tabpanel" class="tab-pane fade in active" id="showNeighbor">
     
    <ul id="neighbor-list-ul">
   <c:forEach var="neighbor" items="${neighborList }" varStatus="i">
  
   	<li class="neighbor-list-li">
   	 <a href="#" class="neighbor-home-a">
		<div class="form-group" id="homeList">
			<form id="neighbor-form${i.index }" method="get" action="neighborHome.do">
			<%-- <label class="countPlus"> ${count.count} </label> --%>		
				<img src="img/neighbor/home.png" id="homeImg" width="47px" height="47px"/>
				<label class="homeTitle" id="homeTitle">${neighbor.homeName}</label>
				<img src="img/neighbor/manager.png" id="managerImg" width="47px" height="47px"/>
				<label class="managerName" id="managerName">${neighbor.managerName }</label>
				<input type="hidden" id="homeCode" name="homeCode" value="${neighborList[i.index].homeCode}">
							
			</form>
		</div>
		</a>
	</li>
	
</c:forEach>
</ul>
    </div>
    
    <!-- 이웃 찾기 -->
    <div role="tabpanel" class="tab-pane fade" id="searchNeighbor">
    
    <!-- 검색폼 -->
    
    <form id="search">
    	<select id="category">
    		<option>이웃 홈 이름</option>
    		<option>가족 구성원 이름</option>
    		<option>휴대폰 번호</option>
    	</select>
    	
    	<!-- 
    	<input type="text" id="searchBar" placeholder="검색할 단어를 2단어 이상 입력해주세요." size="50px">
    	<input type="image" id="searchBtn" src="neighbor/search2.png" alt="검색버튼" width="30px" height="30px">
    	 -->
    	<div id="searchDiv" class="form-group has-feedback has-feedback-right">
				   <input type="text" id="searchInput" class="form-control" placeholder="검색할 단어를 2단어 이상 입력해주세요." />
				   <i id="searchBtn" class="glyphicon glyphicon-search form-control-feedback" style="color: #b2ccff"></i>
			 </div>
    </form>
    </div>


</body>
</html>