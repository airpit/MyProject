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

public class FamilyScheduleDAO implements Serializable{

	private static final long serialVersionUID = 990620150672335541L;
	private DBConnectionModule connModule;
	private Connection conn;
	
	public FamilyScheduleDAO() {
		connModule = DBConnectionModule.getInstance();
		conn = connModule.getConn();
	}
	
	public String makeCode(){
		Date d = new Date();
		StringTokenizer st = new StringTokenizer(new SimpleDateFormat().format(d),". ");
			
		String year = st.nextToken();
		String month = st.nextToken();
		String day = st.nextToken();
			
		String familyScheduleCode = "FS13"+day+year+month+d.getHours()+d.getMinutes()+d.getSeconds();
		return familyScheduleCode;
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
	
	public int updateFamilySchedule(String family_schedule_code, String family_schedule_title, String family_schedule_place, Date family_schedule_start_date, Date family_schedule_end_date, Date family_schedule_alarm, int family_schedule_repeat, String family_schedule_memo){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		try{
			String sql = "update family_schedule_tb set family_schedule_title=?, family_schedule_place=?, family_schedule_start_date=?, family_schedule_end_date=?, family_schedule_alarm=?, family_schedule_repeat=?, family_schedule_memo=? where family_schedule_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, family_schedule_title);
			pstmt.setString(2, family_schedule_place);
			
			pstmt.setString(3, format(family_schedule_start_date));
			pstmt.setString(4, format(family_schedule_end_date));
			pstmt.setString(5, format(family_schedule_alarm));
			
			pstmt.setInt(6, family_schedule_repeat);
			pstmt.setString(7, family_schedule_memo);
			pstmt.setString(8, family_schedule_code);
			
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
	
	
	
	public int insertFamilySchedule(String family_schedule_code, String family_home_code, String member_code, String family_schedule_title, String family_schedule_place, Date family_schedule_start_date, Date family_schedule_end_date, Date family_schedule_alarm, int family_schedule_repeat, String family_schedule_memo){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		try{
			String sql = "insert into family_schedule_tb values(?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, family_schedule_code);
			pstmt.setString(2, family_home_code);
			pstmt.setString(3, member_code);
			pstmt.setString(4, family_schedule_title);
			pstmt.setString(5, family_schedule_place);
			
			pstmt.setString(6, format(family_schedule_start_date));
			pstmt.setString(7, format(family_schedule_end_date));
			pstmt.setString(8, format(family_schedule_alarm));
			
			pstmt.setInt(9, family_schedule_repeat);
			pstmt.setString(10, family_schedule_memo);
			
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
	public int insertFamilySchedule(String family_home_code, String member_code, String family_schedule_title, String family_schedule_place, Date family_schedule_start_date, Date family_schedule_end_date, Date family_schedule_alarm, int family_schedule_repeat, String family_schedule_memo){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		try{
			String sql = "insert into family_schedule_tb values(?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, makeCode());
			pstmt.setString(2, family_home_code);
			pstmt.setString(3, member_code);
			pstmt.setString(4, family_schedule_title);
			pstmt.setString(5, family_schedule_place);
			
			pstmt.setString(6, format(family_schedule_start_date));
			pstmt.setString(7, format(family_schedule_end_date));
			pstmt.setString(8, format(family_schedule_alarm));
			
			pstmt.setInt(9, family_schedule_repeat);
			pstmt.setString(10, family_schedule_memo);
			
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
	
	
	public int deleteFamilySchedule(String family_schedule_code){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from family_schedule_tb where family_schedule_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, family_schedule_code);
			
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
	
	
	public FamilyScheduleVO selectFamilySchedule(String family_schedule_code){
		FamilyScheduleVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select * from family_schedule_tb where family_schedule_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, family_schedule_code);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String familyScheduleCode1 = rs.getString("family_schedule_code");
				String familyHomeCode1 = rs.getString("family_home_code");
				String memberCode1 = rs.getString("member_code");
				String familyScheduleTitle1 = rs.getString("family_schedule_title");
				String familySchedulePlace1 = rs.getString("family_schedule_place");
				Date familyScheduleStartDate1 = changeDate(rs.getString("family_schedule_start_date")); 
				Date familyScheduleEndDate1 = changeDate(rs.getString("family_schedule_end_date"));
				Date familyScheduleAlarm1 = changeDate(rs.getString("family_schedule_alarm"));
				
				int familyScheduleRepeat1 = rs.getInt("family_schedule_repeat");
				String familyScheduleMemo1 = rs.getString("family_schedule_memo");
				
				vo = new FamilyScheduleVO(familyScheduleCode1, familyHomeCode1, memberCode1, familyScheduleTitle1, familySchedulePlace1, familyScheduleStartDate1, familyScheduleEndDate1, familyScheduleAlarm1, familyScheduleRepeat1, familyScheduleMemo1);		
				
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
	
	public String[][] selectSimpleFamilyScheduleInfoList(String homeCode, Date family_schedule_start_date){
		String returnStr[][] = null;
		PreparedStatement pstmt = null;
		ArrayList<String[]> list = new ArrayList<String[]>();
		
		try{
			String sql = "select family_schedule_code, family_schedule_title from family_schedule_tb where family_home_code=? AND family_schedule_start_date=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, homeCode);
			pstmt.setString(2, format(family_schedule_start_date));
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String familyScheduleCode1 = rs.getString("family_schedule_code");
				String familyScheduleTitle1 = rs.getString("family_schedule_title");
				list.add(new String[]{familyScheduleCode1,familyScheduleTitle1});
				
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
		FamilyScheduleDAO dao = new FamilyScheduleDAO();
		
		//dao.insertFamilySchedule("fs10", "h1", "m1", "패밀리주제", "패밀리장소", new Date(), new Date(), new Date(), 3, "가족메모");
		//dao.updateFamilySchedule("fs1", "수정제목", "수정장소", new Date(), new Date(), new Date(), 99, "수정메모");
		//dao.deleteFamilySchedule("fs10");
		
		//System.out.println(dao.selectFamilySchedule("fs3"));
		String retString[][] = dao.selectSimpleFamilyScheduleInfoList("h1", new Date(2015,6,29));
		for(int cnt=0; cnt<retString.length; cnt++){
			System.out.println(retString[cnt][0]+"-"+retString[cnt][1]);
		}
		
	}
	
}
