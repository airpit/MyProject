package dao.home;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import dao.DBConnectionModule;

public class FamilyHomeDAO implements Serializable{

	private static final long serialVersionUID = 9218927421203109456L;
	private DBConnectionModule connModule;
	private Connection conn;
	
	public FamilyHomeDAO() {
		connModule = DBConnectionModule.getInstance();
		conn = connModule.getConn();
	}
	
	public String makeHomeCode(){ //�ӽ��ڵ�
		Date d = new Date();
		StringTokenizer st = new StringTokenizer(new SimpleDateFormat().format(d),". ");
			
		String year = st.nextToken();
		String month = st.nextToken();
		String day = st.nextToken();
			
		String homeCode = "FH04"+day+year+month+d.getHours()+d.getMinutes()+d.getSeconds();
		return homeCode;
	}
	
	public int insertHome(String familyHomeName){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		try{
			String sql = "insert into family_home_tb values(?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, makeHomeCode());
			pstmt.setString(2, familyHomeName);
								
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
	
	public int updateHome(String familyHomeCode, String familyHomeName){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		try{
			String sql = "update family_home_tb set family_home_name=? where family_home_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, familyHomeName);
			pstmt.setString(2, familyHomeCode);
			
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
	
	public int deleteHome(String familyHomeCode){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from family_home_tb where family_home_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, familyHomeCode);
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
	
	public FamilyHomeVO selectHome(String familyHomeCode)
	{
		FamilyHomeVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select family_home_code, family_home_name from family_home_tb where family_home_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, familyHomeCode);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String familyHomeName = rs.getString("family_home_name");
							
				vo = new FamilyHomeVO(familyHomeCode,familyHomeName);
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
	

	public static void main(String[] args)
	{
		FamilyHomeDAO dao = new FamilyHomeDAO();
		
		System.out.println(dao.insertHome("�׽�Ʈ Ȩ �̸�"));
		System.out.println(dao.selectHome("200029157194823"));
		System.out.println(dao.updateHome("200029157194823", "������ Ȩ �̸�"));
		System.out.println(dao.selectHome("200029157194823"));
		System.out.println(dao.deleteHome("200029157194823"));
	}
	
	
	

}
