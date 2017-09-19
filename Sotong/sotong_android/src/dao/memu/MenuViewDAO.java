package dao.memu;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import dao.DBConnectionModule;

public class MenuViewDAO implements Serializable{

	private static final long serialVersionUID = -4654035843337687349L;
	private DBConnectionModule connModule;
	private Connection conn;
	
	public MenuViewDAO() {
		connModule = DBConnectionModule.getInstance();
		conn = connModule.getConn();
	}
	public MenuViewDAO(DBConnectionModule connModule, Connection conn) {
		super();
		this.connModule = connModule;
		this.conn = conn;
	}
	
	public String makeMenuCode() {
		Date d = new Date();
		StringTokenizer st = new StringTokenizer(new SimpleDateFormat().format(d),". ");
			
		String year = st.nextToken();
		String month = st.nextToken();
		String day = st.nextToken();
			
		String clientId = "2000"+day+year+month+d.getHours()+d.getMinutes()+d.getSeconds();
		return clientId;
	}
	
	public MenuViewVO selectCode(String code){
		MenuViewVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select * from menu_view where menu_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){

				String familyHomeCode = rs.getString("family_home_code");
				String shareDate = rs.getString("share_date");
				String memberNickname = rs.getString("member_nickname");
				String lunch = rs.getString("lunch");
				String dinner = rs.getString("dinner");
				
				Date nowDate = changeDate(shareDate);
				
				vo = new MenuViewVO(code, familyHomeCode, nowDate, memberNickname, lunch, dinner);
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
	
	public Date changeDate(String dateTime) {
	      int year = Integer.parseInt(dateTime.substring(0,2));
	      int mon = Integer.parseInt(dateTime.substring(3,5));
	      int date = Integer.parseInt(dateTime.substring(6,8));
	      
	      Date reDate = new Date(year,mon,date);
	      return reDate; 
	}
	
	
	public static void main(String[] args) {
		MenuViewDAO dao = new MenuViewDAO();
		System.out.println(dao.selectCode("menu1"));
		
	}	
}
