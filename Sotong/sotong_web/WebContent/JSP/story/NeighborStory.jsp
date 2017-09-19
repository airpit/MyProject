<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="css/story/NeighborStroy.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script src="js/story/story.js"></script>
	<title>가족 이야기</title>
</head>
<body>
	
	
	<div>
		<jsp:include page="../../mainMenu.jsp"></jsp:include>
	</div>
	
	
	<div id="all">	
		<header>
		
		</header>
		 <div id="family-body">
		 	<div class="family-board">
		 		<!-- 여기 부터 foreach 구문 돌려라 -->
		 		<c:forEach var='storyInfo' items="${neighborList }" >
		 		
			 	<div id="family-board-read" class="famil-board">
			 		<form class="form-horizontal" id="family-body-read">
			 			<div class="famil-board-text">
				 			<div class="profile-border">
				    			<div id="read-profile-photo" class="family-board-image">
				    				<image src=${storyInfo.memberPhoto } class='profile-image'/>
				    			</div>
				    			<input type="text" id="read-profile-name" class="family-board-name" value=${storyInfo.memberNickname }>
		    				</div>
			 				
		    				<div class="family-board-content">
			    				<div id="family-board-top">
						 			<span id="neighbor-story">${storyInfo.familyHomeName }</span>
						 			<input type="image" id="negihbor-story-btn" src="image/home.png"/>
						 			<div id="heart-image">
					 					<span id="heartNo">3</span>
					 					<input type="image" src="image/clickHeart.png"/>
					 				</div>
				 				</div>
						   		<div id="read-content" class="famil-board-text">
					     			<textarea id="family-board-content-read" class="form-control" readonly>${storyInfo.contents }</textarea>
								</div>
				 				<div id="comment-write" class="family-board-down">
				 					<div id="comment-photo">
				 						<div id="comment-profile">
				 							<image src=${userInfo.memberPhoto }>
				 						</div>
				 						<div id="comment-content-all">
				 							<input type="text" id="comment-content"/>
				 							<input type="button" id="comment-btn" value="댓글"/>
				 						</div>
				 					</div>
				 				</div>
				 			</div>
				 			
				 		</div>
				 		<div class="family-board-date">
				  			${storyInfo.storyModifyDate }
				  		</div>
			 		</form>
		 		</div>
		 		</c:forEach>
		 		<!-- 여기 까지 foreach 구문 돌려라 -->
		 	</div>
		</div>
		<footer>
		 
		</footer>
	</div>
</body>
</html>