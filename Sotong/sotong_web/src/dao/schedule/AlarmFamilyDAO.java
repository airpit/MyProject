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

public class AlarmFamilyDAO implements Serializable{

	private static final long serialVersionUID = 3975035524067186390L;
	private DBConnectionModule connModule;
	private Connection conn;
	
	public AlarmFamilyDAO() {
		connModule = DBConnectionModule.getInstance();
		conn = connModule.getConn();
	}
	public String makeCode(){
		Date d = new Date();
		StringTokenizer st = new StringTokenizer(new SimpleDateFormat().format(d),". ");
			
		String year = st.nextToken();
		String month = st.nextToken();
		String day = st.nextToken();
			
		String clientId = "AF10"+day+year+month+d.getHours()+d.getMinutes()+d.getSeconds();
		return clientId;
	}
	
	public int insertAlarmFamily(String alarm_family_code, String member_code, String schedule_code){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "insert into alarm_family_tb values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, alarm_family_code);
			pstmt.setString(2, member_code);
			pstmt.setString(3, schedule_code);
			
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
	
	public int insertAlarmFamily(String member_code, String schedule_code){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "insert into alarm_family_tb values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, makeCode());
			pstmt.setString(2, member_code);
			pstmt.setString(3, schedule_code);
			
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
	public int deleteAlarmFamilyByScheduleCode(String schedule_code){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		try{
			String sql = "delete from alarm_family_tb where schedule_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, schedule_code);
			
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
	
	public int deleteAlarmFamily(String alarm_family_code){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from alarm_family_tb where alaram_family_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, alarm_family_code);
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
	
	public int updateAlarmFamily(String alarm_family_code, String member_code, String schedule_code){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "update alarm_family_tb set member_code=?, schedule_code=? where alaram_family_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_code);
			pstmt.setString(2, schedule_code);
			pstmt.setString(3, alarm_family_code);
			
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
	
	public String[] selectMemberCodeArrayByScheduleCode(String schedule_code){
		String []memberCode = null;
		PreparedStatement pstmt = null;
		ArrayList<String> list = new ArrayList<String>();
		try{
			String sql = "select member_code from alarm_family_tb where schedule_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, schedule_code);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String memberCode1 = rs.getString("member_code");
				list.add(memberCode1);
			}
			
			memberCode = new String[list.size()];
			for(int cnt=0; cnt<list.size(); cnt++){
				memberCode[cnt] = list.get(cnt);
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
		return memberCode;
	}
	
	public AlarmFamilyVO selectAlarmFamily(String alarm_family_code){
		AlarmFamilyVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select * from alarm_family_tb where alaram_family_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, alarm_family_code);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String alarmFamilyCode1 = rs.getString("alaram_family_code");
				String memberCode1 = rs.getString("member_code");
				String scheduleCode1 = rs.getString("schedule_code");
				
				vo = new AlarmFamilyVO(alarmFamilyCode1, memberCode1, scheduleCode1);
				
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
	public static void main(String[] args) {
		AlarmFamilyDAO dao = new AlarmFamilyDAO();
		//dao.insertAlarmFamily("a10", "m1", "s10");
		//dao.insertAlarmFamily("a11", "m2", "s10");
		//dao.insertAlarmFamily("a12", "m3", "s10");
		
		//dao.updateAlarmFamily("a12", "m5", "s1");
		//dao.deleteAlarmFamily("a12");
	
		//System.out.println(dao.selectAlarmFamily("a11"));
		
		//String memberCode[] = dao.selectMemberCodeArrayByScheduleCode("s10");
		
		//for(int cnt=0; cnt<memberCode.length; cnt++){
		//	System.out.println(memberCode[cnt]);
		//}
		
		dao.deleteAlarmFamilyByScheduleCode("s3");
	}
	

}
