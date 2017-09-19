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

public class MenuDAO implements Serializable{

	private static final long serialVersionUID = 4928199630700378540L;
	private DBConnectionModule connModule;
	private Connection conn;
	
	public MenuDAO() {
		connModule = DBConnectionModule.getInstance();
		conn = connModule.getConn();
	}
	public MenuDAO(DBConnectionModule connModule, Connection conn) {
		super();
		this.connModule = connModule;
		this.conn = conn;
	}
	
	public String makeMenuCode(){
		Date d = new Date();
		StringTokenizer st = new StringTokenizer(new SimpleDateFormat().format(d),". ");
			
		String year = st.nextToken();
		String month = st.nextToken();
		String day = st.nextToken();
			
		String clientId = "MD08"+day+year+month+d.getHours()+d.getMinutes()+d.getSeconds();
		return clientId;
	}
	
	public int insertMenu(String menuCode, String dinner, Date shareDate){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		String nowDate = sendChangeDate(shareDate);
		try{
			String sql = "insert into menu_tb values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,menuCode);
			pstmt.setString(2, dinner);
			pstmt.setString(3, nowDate);
			
					
			rowNum = pstmt.executeUpdate();
			
			if (rowNum != 0) {
				conn.commit();
			} else {
				conn.rollback();
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
		return rowNum;
	}
	
	public int updateMenu(String menuCode, String dinner, Date shareDate){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		 String nowDate = sendChangeDate(shareDate);
		
		
		try{
			String sql = "update menu_tb set dinner=?,share_date=? where menu_code=?";
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(1, dinner);
			pstmt.setString(2, nowDate);
			pstmt.setString(3, menuCode);
			
			rowNum = pstmt.executeUpdate();
			
			if (rowNum != 0) {
				conn.commit();
			} else {
				conn.rollback();
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
		return rowNum;
	}
	
	public int deleteCode(String menuCode){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from menu_tb where menu_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, menuCode);
			rowNum = pstmt.executeUpdate();
			
			if (rowNum != 0) {
				conn.commit();
			} else {
				conn.rollback();
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
		return rowNum;
	}
	
	public MenuVO selectCode(String menuCode){
		MenuVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select * from menu_tb where menu_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, menuCode);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				String code = rs.getString("menu_code");
				String dinner = rs.getString("dinner");
				String shareDate = rs.getString("share_date");
				
				String year = shareDate.substring(0,2);
				String mon = shareDate.substring(3,5);
				String date = shareDate.substring(6,8);
				
				Date dat = new Date(Integer.parseInt(year),Integer.parseInt(mon), Integer.parseInt(date));
				
				vo = new MenuVO(code,dinner,dat);
				System.out.println(vo.getShareDate());
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
	
	public String sendChangeDate(Date date) {
		int year= date.getYear() - 100;
		String mon = (date.getMonth()+1)>9 ? "" + (date.getMonth()+1) : "0" + (date.getMonth()+1);
		String day = date.getDate()>9 ? "" +date.getDate() : "0" + date.getDate();
	  
		return  year + "-" + mon +"-" + day; 
	}
	
	public static void main(String[] args) {
		MenuDAO dao = new MenuDAO();
		
	//	System.out.println(dao.selectCode("menu1"));
		System.out.println(dao.deleteCode("menu1"));
		System.out.println(dao.deleteCode("200029157195248"));
		System.out.println(dao.updateMenu("menu1", "masasas1", new Date()));
		System.out.println(dao.updateMenu("200029157195113", "masasas1", new Date()));
	}
}
