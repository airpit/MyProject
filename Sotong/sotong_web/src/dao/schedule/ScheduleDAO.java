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

public class ScheduleDAO implements Serializable{

	private static final long serialVersionUID = -7047063386060139076L;
	private DBConnectionModule connModule;
	private Connection conn;
	
	public ScheduleDAO() {
		connModule = DBConnectionModule.getInstance();
		conn = connModule.getConn();
		
	}
	//�ӽ�
	public String makeCode(){
		Date d = new Date();
		StringTokenizer st = new StringTokenizer(new SimpleDateFormat().format(d),". ");
			
		String year = st.nextToken();
		String month = st.nextToken();
		String day = st.nextToken();
			
		String scheduleCode = "SD14"+day+year+month+d.getHours()+d.getMinutes()+d.getSeconds();
		return scheduleCode;
	}
	
	public String format(Date d){ // Date�� String���� ������/ ������ ���� �� ���
	    SimpleDateFormat fmt = new SimpleDateFormat("yy-MM-dd hh:mm");
	    String date = fmt.format(d);
	    return date;
	}
	
	public Date changeDate(String dateTime) { // String���� ������ data�� Date�� ����
	      int year = Integer.parseInt(dateTime.substring(0,2));
	      int mon = Integer.parseInt(dateTime.substring(3,5));
	      int date = Integer.parseInt(dateTime.substring(6,8));
	      int hour = Integer.parseInt(dateTime.substring(9,11));
	      int min = Integer.parseInt(dateTime.substring(12,14));
	 
	      Date reDate = new Date(year,mon,date,hour,min);
	      return reDate; 
	}
	
	
	public int insertSchedule(String schedule_code, String member_code, String schedule_title, String schedule_place, Date schedule_start_date, Date schedule_end_date, Date schedule_alarm, int schedule_repeat, String schedule_memo){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		try{
			String sql = "insert into schedule_tb values(?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, schedule_code);
			pstmt.setString(2, member_code);
			pstmt.setString(3, schedule_title);
			pstmt.setString(4, schedule_place);
			
			pstmt.setString(5, format(schedule_start_date));
			pstmt.setString(6, format(schedule_end_date));
			pstmt.setString(7, format(schedule_alarm));
			pstmt.setInt(8, schedule_repeat);
			pstmt.setString(9,schedule_memo);
			
			rowNum = pstmt.executeUpdate();
			
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
		return rowNum;
		
	}
	
	public int insertSchedule(String member_code, String schedule_title, String schedule_place, Date schedule_start_date, Date schedule_end_date, Date schedule_alarm, int schedule_repeat, String schedule_memo){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "insert into schedule_tb values(?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, makeCode());
			pstmt.setString(2, member_code);
			pstmt.setString(3, schedule_title);
			pstmt.setString(4, schedule_place);
			pstmt.setString(5, format(schedule_start_date));
			pstmt.setString(6, format(schedule_end_date));
			pstmt.setString(7, format(schedule_alarm));
			pstmt.setInt(8, schedule_repeat);
			pstmt.setString(9,schedule_memo);
			
			rowNum = pstmt.executeUpdate();
			
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
		return rowNum;
		
	}
	
	public String insertScheduleAndReturnCode(String member_code, String schedule_title, String schedule_place, Date schedule_start_date, Date schedule_end_date, Date schedule_alarm, int schedule_repeat, String schedule_memo){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		String scheduleCode = null;
		try{
			String sql = "insert into schedule_tb values(?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			scheduleCode = makeCode();
			pstmt.setString(1, scheduleCode);
			pstmt.setString(2, member_code);
			pstmt.setString(3, schedule_title);
			pstmt.setString(4, schedule_place);
			pstmt.setString(5, format(schedule_start_date));
			pstmt.setString(6, format(schedule_end_date));
			pstmt.setString(7, format(schedule_alarm));
			pstmt.setInt(8, schedule_repeat);
			pstmt.setString(9,schedule_memo);
			
			rowNum = pstmt.executeUpdate();
					
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
		return scheduleCode;
		
	}
	
	public int deleteSchedule(String schedule_code){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from schedule_tb where schedule_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, schedule_code);
			
			rowNum  = pstmt.executeUpdate();
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
		
		return rowNum;
	}
	
	public int updateSchedule(String schedule_code, String schedule_title, String schedule_place, Date schedule_start_date, Date schedule_end_date, Date schedule_alarm, int schedule_repeat, String schedule_memo){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "update schedule_tb set schedule_title=?, schedule_place=?, schedule_start_date=?, schedule_end_date=?, schedule_alarm=?, schedule_repeat=?, schedule_memo=? where schedule_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, schedule_title);
			pstmt.setString(2, schedule_place);
			pstmt.setString(3, format(schedule_start_date));
			pstmt.setString(4, format(schedule_end_date));
			pstmt.setString(5, format(schedule_alarm));
			pstmt.setInt(6,schedule_repeat);
			pstmt.setString(7, schedule_memo);
			pstmt.setString(8, schedule_code);
			
			rowNum = pstmt.executeUpdate();
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
		return rowNum;
		
	}
	
	public ScheduleVO[] selectScheduleByMemberCode(String member_code){
		ScheduleVO vo[] = null;
		PreparedStatement pstmt = null;
		ArrayList<ScheduleVO> list = new ArrayList<ScheduleVO>();
		try{
			String sql = "select * from schedule_tb where member_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_code);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String scheduleCode1 = rs.getString("schedule_code");
				String memberCode1 = rs.getString("member_code");
				String scheduleTitle1 = rs.getString("schedule_title");
				String schedulePlace1 = rs.getString("schedule_place");
				Date scheduleStartDate1 = changeDate(rs.getString("schedule_start_date")); 
				Date scheduleEndDate1 = changeDate(rs.getString("schedule_end_date"));
				Date scheduleAlarm1 = changeDate(rs.getString("schedule_alarm"));
				int scheduleRepeat1 = rs.getInt("schedule_repeat");
				String scheduleMemo1 = rs.getString("schedule_memo");
				
				list.add(new ScheduleVO(scheduleCode1,memberCode1,scheduleTitle1,
						schedulePlace1,scheduleStartDate1,scheduleEndDate1,scheduleAlarm1,scheduleRepeat1,scheduleMemo1));
			}
			
			vo = new ScheduleVO[list.size()];
			for(int cnt=0; cnt<list.size(); cnt++){
				vo[cnt] = list.get(cnt);
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
	
	public ScheduleVO selectSchedule(String schedule_code){
		ScheduleVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select * from schedule_tb where schedule_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, schedule_code);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String scheduleCode1 = rs.getString("schedule_code");
				String memberCode1 = rs.getString("member_code");
				String scheduleTitle1 = rs.getString("schedule_title");
				String schedulePlace1 = rs.getString("schedule_place");
				Date scheduleStartDate1 = changeDate(rs.getString("schedule_start_date")); 
				Date scheduleEndDate1 = changeDate(rs.getString("schedule_end_date"));
				Date scheduleAlarm1 = changeDate(rs.getString("schedule_alarm"));
				
				int scheduleRepeat1 = rs.getInt("schedule_repeat");
				String scheduleMemo1 = rs.getString("schedule_memo");
				
				vo = new ScheduleVO(scheduleCode1,memberCode1,scheduleTitle1,
						schedulePlace1,scheduleStartDate1,scheduleEndDate1,scheduleAlarm1,scheduleRepeat1,scheduleMemo1);
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
	
	public ScheduleVO[] selectSimpleScheduleInfoListWeb(String memberCode, String year,String mon)
	{
		ScheduleVO vo = null;
		PreparedStatement pstmt = null;
		ArrayList<ScheduleVO> voList = null;
		try{
			voList = new ArrayList<ScheduleVO>();
			String sql = "select * from schedule_tb where substr(schedule_start_date,1,2)=? AND substr(schedule_start_date,4,2)=? AND member_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, year);
			pstmt.setString(2, mon);
			pstmt.setString(3, memberCode);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String scheduleCode1 = rs.getString("schedule_code");
				String memberCode1 = rs.getString("member_code");
				String scheduleTitle1 = rs.getString("schedule_title");
				String schedulePlace1 = rs.getString("schedule_place");
				Date scheduleStartDate1 = changeDate(rs.getString("schedule_start_date")); 
				Date scheduleEndDate1 = changeDate(rs.getString("schedule_end_date"));
				Date scheduleAlarm1 = changeDate(rs.getString("schedule_alarm"));
				
				int scheduleRepeat1 = rs.getInt("schedule_repeat");
				String scheduleMemo1 = rs.getString("schedule_memo");
				
				vo = new ScheduleVO(scheduleCode1,memberCode1,scheduleTitle1,
						schedulePlace1,scheduleStartDate1,scheduleEndDate1,scheduleAlarm1,scheduleRepeat1,scheduleMemo1);
				
				voList.add(vo);
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
		if(voList.size()<1 || voList==null)
		{
			return null;
		}
		else
		{
			return voList.toArray(new ScheduleVO[voList.size()]);
		}
	}
	
	public String[][] selectSimpleScheduleInfoByDate(String member_code, String year, String month, String date){
		String [][]returnStr = null;
		PreparedStatement pstmt = null;
		ArrayList<String[]> list = new ArrayList<String[]>();
		Date searchDate = new Date(Integer.parseInt(year)-1900, Integer.parseInt(month)-1, Integer.parseInt(date));
		System.out.println(searchDate);
		String choiceDay = format(searchDate);
		try{
			String sql = "select schedule_code, schedule_title from schedule_tb where member_code=? AND schedule_start_date=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_code);
			pstmt.setString(2, choiceDay);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String scheduleCode1 = rs.getString("schedule_code");
				String scheduleTitle1 = rs.getString("schedule_title");
				
				list.add(new String[]{scheduleCode1,scheduleTitle1});
				
			}
			
			returnStr = new String[list.size()][2];
			for(int cnt=0; cnt<list.size(); cnt++){
				returnStr[cnt][0] = list.get(cnt)[0];
				returnStr[cnt][1] = list.get(cnt)[1];
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
		return returnStr;
	}
	public ScheduleVO[] selectScheduleByDate(Date date){
		ScheduleVO vo[] = null;
		PreparedStatement pstmt = null;
		ArrayList<ScheduleVO> list = new ArrayList<ScheduleVO>();
		String strDate = format(date);
		try{
			String sql = "select * from schedule_tb where schedule_start_date=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, strDate);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String scheduleCode1 = rs.getString("schedule_code");
				String memberCode1 = rs.getString("member_code");
				String scheduleTitle1 = rs.getString("schedule_title");
				String schedulePlace1 = rs.getString("schedule_place");
				Date scheduleStartDate1 = changeDate(rs.getString("schedule_start_date")); 
				Date scheduleEndDate1 = changeDate(rs.getString("schedule_end_date"));
				Date scheduleAlarm1 = changeDate(rs.getString("schedule_alarm"));
				int scheduleRepeat1 = rs.getInt("schedule_repeat");
				String scheduleMemo1 = rs.getString("schedule_memo");
				
				list.add(new ScheduleVO(scheduleCode1,memberCode1,scheduleTitle1,
						schedulePlace1,scheduleStartDate1,scheduleEndDate1,scheduleAlarm1,scheduleRepeat1,scheduleMemo1));
			}
			
			vo = new ScheduleVO[list.size()];
			for(int cnt=0; cnt<list.size(); cnt++){
				vo[cnt] = list.get(cnt);
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
		String date = "15-08-11";
		
		ScheduleDAO dao = new ScheduleDAO();
		//dao.insertSchedule("s11", "m1", "�׽�Ʈ�׽�Ʈ", "�׽�Ʈ���", new Date(), new Date(), new Date(), 6, "�׽�Ʈ�޸�");
		//dao.updateSchedule("s11", "�����׽�Ʈ", "�������", new Date(), new Date(), new Date(), 4, "�����޸�");
		//dao.updateSchedule("s10", "����1", "�������",new Date() , new Date(), new Date(), 100, "�����޸�");
		//dao.deleteSchedule("s10");
		//dao.insertSchedule("s10", "m1", "�׽�Ʈ1", "�׽�Ʈ���", new Date(), new Date(), new Date(), 3, "�޸�");
		
		System.out.println(dao.insertSchedule("m1", "�׽�Ʈ�׽�Ʈ", "�׽�Ʈ���", new Date(), new Date(), new Date(), 6, "�׽�Ʈ�޸�"));
		
		//System.out.println(dao.selectSchedule("s1"));
		/*
		ScheduleVO []vo = dao.selectScheduleByMemberCode("m1");
		for(int cnt=0; cnt<vo.length; cnt++){
			System.out.println(vo[cnt]);
		}
		*/
		//String [][]returnStr = dao.selectSimpleScheduleInfoByDate("m1", "15", "8", "8");
		
		//for(int cnt=0; cnt<returnStr.length; cnt++){
		//	System.out.println(returnStr[cnt][0]+"//"+returnStr[cnt][1]);
		//}
//		ScheduleVO []vo = dao.selectScheduleByDate(new Date(2015,7,8));
//		for(int cnt=0; cnt<vo.length; cnt++){
//			System.out.println(vo[cnt]);
//		}
		
		ScheduleVO[] voList = dao.selectSimpleScheduleInfoListWeb("m1", "15", "08");
		for(int i=0; i<voList.length;i++)
		{
			System.out.println(voList[i]);
		}
		
		
	}

	
	
}
