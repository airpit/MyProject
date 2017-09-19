<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>소통 - 이웃</title>
 <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
 <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
 
 <style>
 	body {
 		width : 100%;
 		height : 100%;
 	}
 	
 	.tab-content{
 		width:60%;
 		margin:0 auto;
 	}
 	
 	.homeTitle {
 		float:left;
 		font-size : 25px;
 		line-height:25px;
 		margin:10px auto;
 		height:50px;
 		width:200px;
 	}

 	.managerName {
 		float:left;
 		font-size : 25px;
 		margin:10px auto;
 		height:50px;
 		width:100px;
 	}
 	
 	a:hover {
 		color : #00bfff !important;
 		text-decoration : none;
 	}
 	 	
 	#showNeighbor {
 		border : 2px solid #ff9999;
 		padding: 10px 40px;
 		width : 700px;
 		border-radious : 20px; 
 		background : #ff9999;
 		margin:0 auto;
 	}
 	
 	#homeList {
 		height:80px;
 		line-height:100px;
 		padding: 10px 40px;
 		background : #ffffff;
 		border-radious : 20px;
 		margin:0 auto;
 	}
 	
 	.countPlus {
 		font-size : 15px;
 		margin-right : 30px;
 	}
 	
 	#searchNeighbor {
 		border : 2px solid #b2ccff;
 		padding: 10px 20px;
 		width : 100%;
 		height:100px;
 		border-radious : 20px; 
 		background : #b2ccff;
 		margin:0 auto;
 	}
 	
 	#category{
 		float:left;
 		margin-right:5px;
 		height:30px;
 	}
 	
 	#searchInput{
 		width:70%;
 		float:left;
 		height:30px;	
 	}
 	
 	#searchBtn{
 		float:left;
 		width:50px;
 		height:30px;
 		margin-left:5px;
 	}
 	
 	#homeImg{
 		margin-left:30px;
 		float:left;
 	}
 	
 	#managerImg{
 		margin-left:40px;
 		float:left;
 	}
 	
 	

 	
 </style>
 
 <script>
 	
 	
 </script>
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
     
 <!--  <c:forEach begin="0" end="3" step="1" varStatus="count">  -->	
		<div class="form-group" id="homeList">
			<label class="countPlus"> ${count.count} </label>
			<a href="neighborHome.jsp"><img src="img/neighbor/home.png" id="homeImg" width="47px" height="47px"/>
				<label class="homeTitle" id="homeTitle">쩌려니의 홈</label>
				<img src="img/neighbor/manager.png" id="managerImg" width="47px" height="47px"/>
				<label class="managerName" id="managerName">김철연</label>
			</a>
		</div>

<!-- 	</c:forEach> -->
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
    
    <!-- 
    <form class="form-horizontal">
 	 <div class="container">
    <div class="row">    
	<div class=""></div>
        	
            <div class="input-group">
                <div class="input-group-btn search-panel">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                        <span id="search_concept">검색분류 선택</span> <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                      <li><a href="#contains">이웃 홈 이름</a></li>
                      <li><a href="#its_equal">가족 구성원 이름</a></li>
                      <li><a href="#greather_than">휴대폰 번호</a></li>
                    </ul>
                </div>
                
			 <div class="form-group has-feedback has-feedback-right">
				   <input type="text" class="form-control" placeholder="검색할 단어를 2단어 이상 입력해주세요." />
				   <i class="glyphicon glyphicon-search form-control-feedback" style="color: #b2ccff"></i>
			 </div>
  </div>
		
	

</div>
</div>
</form>
 -->

<script>
$(document).ready(function(){
	
	$('#neighborTab a[href="#showNeighbor"]').click(function(e) {
		e.preventDefault();
		$(this).tab('show');
	});
	
	$('#neighborTab a[href="#searchNeighbor"]').click(function(e) {
		e.preventDefault();
		$(this).tab('show');
	});	
	

	  $("ul.nav-tabs > li > a").on("shown.bs.tab", function (e) {
	        var id = $(e.target).attr("href").substr(1);
	        window.location.hash = id;
	    });
	    // on load of the page: switch to the currently selected tab
	    var hash = window.location.hash;
	    $('#neighborTab a[href="' + hash + '"]').tab('show');
	
	
	$('#searchBtn').click(function(){
		alert(('#category').val());	
		return false;
	});
});

</script>
</body>
</html>