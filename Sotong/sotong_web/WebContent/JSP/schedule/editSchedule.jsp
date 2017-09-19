<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	
	<link rel="stylesheet" href="../../css/schedule/editSchedule.css">
	<script src="../../js/schedule/editSchedule.js" type="text/javascript" charset="utf-8"></script>
	
</head>
<body>
<input type="button" id="editScheBtn" alt="일정편집하기"/>

  <!-- 일정추가 Modal -->
  <div class="modal fade" id="myEditSche" role="dialog">
    <div class="modal-dialog">
    
      <!-- 일정추가 Modal 내용 -->
     <div class="modal-content">
     	<!-- 일정추가 최상단부 -->
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4>
          	<span class="glyphicon glyphicon-lock"></span>
          	<label id="menuName">일정 상세보기</label>
          </h4>
        </div>
        <div class="modal-body">
          <form class = "form-horizontal" role="form">
          
          <!-- 제목 -->
          
            <div class="form-group">
                <label for="txtTel" class="col-sm-2 control-label">제목</label>
            <div class="col-sm-10">
           		<input type="text" class="form-control" id="scheduleTitle" placeholder="일정의 제목을 입력하세요." readonly>
        	</div>
        	</div>
        	
          <!-- 장소 -->
                      
            <div class="form-group">
              	<label for="txtTel" class="col-sm-2 control-label">장소</label>
              <div class="col-sm-10">
              	<input type="text" class="form-control" id="schedulePlace" placeholder="일정의 장소를 입력하세요." readonly>
              </div>
            </div>
            
           <!-- 일시 -->
           
           <div class="form-group">
              <label for="txtTel" class="col-sm-2 control-label">시작일시</label>
              <div class="col-sm-5">
              	<input type="date" class="form-control" id="startDate" readonly>
              </div>
              
              <div class="col-sm-4">
              	<input type="time" class="form-control" id="startTime" readonly>
              </div>
           </div>
           
           <div class="form-group">
              <label for="txtTel" class="col-sm-2 control-label">종료일시</label>             
              <div class="col-sm-5">
              	<input type="date" class="form-control" id="endDate" readonly>
              </div>
              
              <div class="col-sm-4">
              	<input type="time" class="form-control" id="endTime" readonly>
              </div>
           </div>
            
            
            <!-- 하루종일 버튼. -->
            <div class="form-group">
            	<div id="allDayDiv" >
            		<input type="checkbox" name="allDayBtn" id="allDayBtn" readonly>하루종일
            	</div>
            </div>
            
            
            <!-- 반복 -->
                   	
            <div class="form-group">
            	<label for="txtTel" class="col-sm-2 control-label">반복</label>
            	<div class="col-sm-10">
            		<select class="col-sm-4" id="repeatSelec">
            			<option>일회성 일정</option>
            			<option>매주</option>
            			<option>매월</option>
					</select>
					<script language="javascript">
						document.all["repeatSelec"].disabled=true;
					</script>
					
					<label class ="col-sm-4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;알림 가족 설정</label>
					<select class="col-sm-4" id="familyAlarmSelec">
						<option>엄마♡</option>
						<option>아빠♡</option>
						<option>동생♡</option>
					</select>
					<script language="javascript">
						document.all["familyAlarmSelec"].disabled=true;
					</script>
            	</div>
            </div>	
            	
           	<!-- 알림 -->
            	
            <div class="form-group">
            	<label for="txtTel" class="col-sm-2 control-label">알람</label>
            	<div class="col-sm-10">
            		<select class="col-sm-4" id="alarmSelec">
            			<option>없     음</option>
            			<option>15 분전</option>
            			<option>30 분전</option>
            			<option>1시간 전</option>
            			<option>2시간 전</option>
					</select>
					<script language="javascript">
						document.all["alarmSelec"].disabled=true;
					</script>
					
					<label class ="col-sm-4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;엄마♡, 아빠♡</label>
					
            	</div>
            </div>	
            	
            <!-- 메모 -->
            
            <div class="form-group">
              	<label for="txtTel" class="col-sm-2 control-label">메모</label>
              <div class="col-sm-10">
              	<input type="text" class="form-control" id="userEmail" placeholder="메모 입력하세요" readonly>
              </div>
            </div>
              	<div class="btnDiv">          
              		<input type="button" class="editScheBtn" id="modifyBtn" alt="수정"/> 
              		<input type="button" class="editScheBtn" id="deleteBtn" alt="삭제"/>	
              		<input type="button" class="editScheBtn" id="backBtn" alt="돌아가기"/>
              		<input type="button" class="addScheBtn" id="okBtn" alt="등록"/>		
              		<input type="button" class="addScheBtn" id="cancelBtn" alt="취소"/>  
          		</div>
          </form>
        </div>
      </div> 
    </div>
  </div> 
</body>
</html>