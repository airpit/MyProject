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

public class FamilyScheduleViewDAO implements Serializable{

	private static final long serialVersionUID = 6552634636191891884L;
	private DBConnectionModule connModule;
	private Connection conn;
	
	public FamilyScheduleViewDAO() {
		connModule = DBConnectionModule.getInstance();
		conn = connModule.getConn();
	}
	public String makeCode(){
		Date d = new Date();
		StringTokenizer st = new StringTokenizer(new SimpleDateFormat().format(d),". ");
			
		String year = st.nextToken();
		String month = st.nextToken();
		String day = st.nextToken();
			
		String familyScheduleCode = "2000"+day+year+month+d.getHours()+d.getMinutes()+d.getSeconds();
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
	
	public FamilyScheduleViewVO[] selectFamilyScheduleView(String family_schedule_code){
		FamilyScheduleViewVO []vo = null;
		PreparedStatement pstmt = null;
		ArrayList<FamilyScheduleViewVO> list = new ArrayList<FamilyScheduleViewVO>();
		try{
			String sql = "select * from family_schedule_view where family_schedule_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, family_schedule_code);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String familyScheduleCode1 = rs.getString("family_schedule_code");
				String familyScheduleTitle1 = rs.getString("family_schedule_title");
				String familySchedulePlace1 = rs.getString("family_schedule_place");
				Date familyScheduleStartDate1 = changeDate(rs.getString("family_schedule_start_date")); 
				Date familyScheduleEndDate1 = changeDate(rs.getString("family_schedule_end_date"));
				Date familyScheduleAlarm1 = changeDate(rs.getString("family_schedule_alarm"));
				int familyScheduleRepeat1 = rs.getInt("family_schedule_repeat");
				String familyScheduleMemo1 = rs.getString("family_schedule_memo");
				String familyEventRequest1 = rs.getString("family_event_request");
				String memberName1 = rs.getString("member_name");
				String familyResponseContents1 = rs.getString("family_response_contents");
				
				list.add(new FamilyScheduleViewVO(familyScheduleCode1, familyScheduleTitle1, familySchedulePlace1, familyScheduleStartDate1, familyScheduleEndDate1, familyScheduleAlarm1, familyScheduleRepeat1, familyScheduleMemo1, familyEventRequest1, memberName1, familyResponseContents1));
				
			}
			
			vo = new FamilyScheduleViewVO[list.size()];
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
		FamilyScheduleViewDAO dao = new FamilyScheduleViewDAO();
		FamilyScheduleViewVO []vo = dao.selectFamilyScheduleView("fs1");
		for(int cnt=0; cnt<vo.length; cnt++){
			System.out.println(vo[cnt]);
		}
		
	}
	
}
