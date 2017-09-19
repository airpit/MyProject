package dao.schedule;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import dao.DBConnectionModule;

public class ScheduleViewDAO implements Serializable{

	private static final long serialVersionUID = -8210501748682327108L;
	private DBConnectionModule connModule;
	private Connection conn;
	
	public ScheduleViewDAO() {
		connModule = DBConnectionModule.getInstance();
		conn = connModule.getConn();
	}
	public String makeCode(){
		Date d = new Date();
		StringTokenizer st = new StringTokenizer(new SimpleDateFormat().format(d),". ");
			
		String year = st.nextToken();
		String month = st.nextToken();
		String day = st.nextToken();
			
		String scheduleCode = "2000"+day+year+month+d.getHours()+d.getMinutes()+d.getSeconds();
		return scheduleCode;
	}
	
	public String format(Date d){ // Date를 String으로 변경함/ 데이터 넣을 때 사용
	    SimpleDateFormat fmt = new SimpleDateFormat("yy-MM-dd");
	    String date = fmt.format(d);
	    return date;
	}
	
	public Date changeDate(String dateTime) { // String으로 가져온 data를 Date로 변경
	      int year = Integer.parseInt(dateTime.substring(0,2));
	      int mon = Integer.parseInt(dateTime.substring(3,5));
	      int date = Integer.parseInt(dateTime.substring(6,8));
	 
	      Date reDate = new Date(year,mon,date);
	      return reDate; 
	}
	
	public ScheduleViewVO[] selectScheduleView(String schedule_code){
		ScheduleViewVO vo[] = null;
		PreparedStatement pstmt = null;
		ArrayList<ScheduleViewVO> list = new ArrayList<ScheduleViewVO>();
		try{
			String sql = "select * from schedule_view where schedule_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, schedule_code);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				
				String scheduleCode1 = rs.getString("schedule_code");
				String scheduleMember1 = rs.getString("schedule_member");
				String scheduleTitle1 = rs.getString("schedule_title");
				String schedulePlace1 = rs.getString("schedule_place");
				
				Date scheduleStartDate1 = changeDate(rs.getString("schedule_start_date")); 
				Date scheduleEndDate1 = changeDate(rs.getString("schedule_end_date"));
				Date scheduleAlarm1 = changeDate(rs.getString("schedule_alarm"));
				
				int scheduleRepeat1 = rs.getInt("schedule_repeat");
				String scheduleMemo1 = rs.getString("schedule_memo");
				String alarmMember1 = rs.getString("alarm_member");
				
				list.add(new ScheduleViewVO(scheduleCode1, scheduleMember1, scheduleTitle1, 
						schedulePlace1, scheduleStartDate1, scheduleEndDate1, scheduleAlarm1, scheduleRepeat1, scheduleMemo1, alarmMember1));
				
			}
			vo = new ScheduleViewVO[list.size()];
			
			for(int cnt=0; cnt<list.size(); cnt++){
				vo[cnt]=list.get(cnt);
			}
			
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		return vo;
		
		
	}
	/*
	public int yearCut(String date){
		Integer yearInt = new Integer(date.substring(0, 2));
		return yearInt.intValue()+100;
		
	}
	public int monthCut(String date){
		Integer yearInt = new Integer(date.substring(3, 5));
		return yearInt.intValue();
	}
	public int dateCut(String date){
		Integer yearInt = new Integer(date.substring(6, 8));
		return yearInt.intValue();
	}
	*/
	public static void main(String[] args) {
		ScheduleViewDAO dao = new ScheduleViewDAO();
		ScheduleViewVO []vo = dao.selectScheduleView("s10");
		for(int cnt=0; cnt<vo.length; cnt++){
			System.out.println(vo[cnt]);
		}
	}
	
}
