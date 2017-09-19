<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>So-Tong</title>




<script src="js/menu_bar/sidebar.js"></script>
<link rel="stylesheet" href="css/menu_bar/nav_bootstrap.css" type="text/css" media="screen"> <!-- 메인메뉴 위치 -->
<link rel="stylesheet" href="css/menu_bar/nav_style.css" type="text/css" media="screen">
<link rel="stylesheet" href="css/menu_bar/side_bar_style.css">


</head>
<body>
<div id="navHeader">
	<div class="container clearfix">
		<div class="row">
			<div class="span12">
				<div class="navbar navbar_">
					<div class="container">
						
						<div id="loggg">
							<h1 class="brand brand_"><a href="test.html"></a><input type="image" src="img/logo.png" id="showSidebar"></h1>
						</div>
						<div class="nav-collapse nav-collapse_  collapse">
							<ul class="nav sf-menu">
								<li class="active" id="homeBoard"><a href="<%=request.getContextPath()%>/home.do">홈 보기</a>		<!-- a에다가 뒤에 파란색 되는거랑 글씨체 적용되어있다. -->
									<ul>
										<li id="ourHomeView" class="main-sub-menu"><a href="JSP/home/myhome.jsp">가족 홈보기</a></li>
										<li id="neighborHomeView" class="main-sub-menu"><a href="JSP/home/neighborHome.jsp">이웃 홈보기</a></li>
									</ul>								
								</li>
								
								<li class="sub-menu" id="storyBoard"><a href="<%=request.getContextPath()%>/story.do">이야기</a>
									<ul id="story-sub-menu">
										<li id="ourStory"><a href="<%=request.getContextPath()%>/story.do">가족 이야기</a></li>
										<li id="neighborStory"><a href="<%=request.getContextPath()%>/neighborStory.do">이웃 이야기</a></li>
									</ul>
								</li>
								
								<li class="sub-menu" id="diaryBoard"><a href="<%=request.getContextPath()%>/diary.do" >일기장</a>
									<ul id="diary-sub-menu">
										<li id="myDiary"><a href="<%=request.getContextPath()%>/diary.do">개인 일기장</a></li>
										<li id="ourDiary"><a href="<%=request.getContextPath()%>/familyDiary.do">가족 일기장</a></li>
									</ul>
								</li>
								<li class="sub-menu" id="scheduleBoard"><a href="JSP/schedule/selfCalendar.jsp">일정</a>
									<ul id="schedule-sub-menu">
										<li id="myScheduleBoard"><a href="JSP/schedule/selfCalendar.jsp">개인 일정</a></li>
										<li id="ourScheduleBoard"><a href="#">가족 일정</a></li>
									</ul>
								</li>
						
								<li class="sub-menu" id="wishList"><a href="JSP/wishList/WishList.jsp">소망상자</a></li>
								
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
	<div class="sidebarDiv">
      <!--sidebar start-->
    	<aside id="sidebar">
      
			<div id="mainProfileImg">
				<image src=${userInfo.memberPhoto }>
			</div>
      
          	<div id="side-menu"  class="nav-collapse ">
              <!-- sidebar menu start-->
              	<ul class="sidebar-menu" id="nav-accordion">    
                  	<li class="mt active">
                      	<a class="active" href="#">
                          	<i class="fa fa-dashboard"></i>
                          	<span>Side Menu</span>
                     	</a>
                  	</li>
                  	<li class="side-sub-menu">
                      	<a href="JSP/letter/letterbox.jsp" >
                         	<i class="fa fa-desktop"></i>
                          	<span>우체통</span>
                      	</a>
                  	</li>
                  <li class="side-sub-menu">
                      <a href="javascript:;" >
                          <i class="fa fa-cogs"></i>
                          <span>앨범</span>
                      </a>
                  </li>
                  <li class="side-sub-menu">
                      <a href="JSP/saveMemory/saveMemory.jsp" >
                          <i class="fa fa-tasks"></i>
                          <span>추억쌓기</span>
                      </a>
                  </li>
                  <li class="side-sub-menu">
                      <a href="modifyMember.do" >
                          <i class="fa fa-tasks"></i>
                          <span>프로필 수정하기</span>
                      </a>
                  </li>
              </ul>
              <!-- sidebar menu end-->
          </div>
      </aside>
	</div>     
</body>
</html>