package dao.sotong;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import dao.DBConnectionModule;

public class SotongCategoryDAO implements Serializable{

	private static final long serialVersionUID = 2501895732722185319L;
	private DBConnectionModule connModule;
	private Connection conn;
	
	public SotongCategoryDAO() {
		connModule = DBConnectionModule.getInstance();
		conn = connModule.getConn();
	}
	
	public String makeSotongCode(){ //임시코드
		Date d = new Date();
		StringTokenizer st = new StringTokenizer(new SimpleDateFormat().format(d),". ");
			
		String year = st.nextToken();
		String month = st.nextToken();
		String day = st.nextToken();
			
		String sotongCode = "SC20"+day+year+month+d.getHours()+d.getMinutes()+d.getSeconds();
		return sotongCode;
	}
	
	public int insertSotongCategory(String SotongCategory)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "insert into sotong_category_tb values(?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, makeSotongCode());
			pstmt.setString(2, SotongCategory);
						
			rowNum = pstmt.executeUpdate();
			if(rowNum!=0)
			{
				conn.commit();
			}
			else
			{
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
	
	public int updateSotongCategory(String sotongCode, String sotongCategory){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "update sotong_category_tb set sotong_category=? where sotong_category_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, sotongCategory);
			pstmt.setString(2, sotongCode);
			
			rowNum = pstmt.executeUpdate();
			if(rowNum!=0)
			{
				conn.commit();
			}
			else
			{
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
	
	public int updateSotongCategoryByCategory(String SotongCategory, String changeCategory){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "update sotong_category_tb set sotong_category=? where sotong_category=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, changeCategory);
			pstmt.setString(2, SotongCategory);
			
			rowNum = pstmt.executeUpdate();
			if(rowNum!=0)
			{
				conn.commit();
			}
			else
			{
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
	
	public int deleteSotongCategory(String sotongCode){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from sotong_category_tb where sotong_category_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, sotongCode);
			rowNum = pstmt.executeUpdate();
			if(rowNum!=0)
			{
				conn.commit();
			}
			else
			{
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
	
	public int deleteSotongCategoryByCategory(String SotongCategory){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from sotong_category_tb where sotong_category=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, SotongCategory);
			rowNum = pstmt.executeUpdate();
			if(rowNum!=0)
			{
				conn.commit();
			}
			else
			{
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
	
	public SotongCategoryVO selectSotongCategory(String sotongCode)
	{
		SotongCategoryVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select * from sotong_category_tb where sotong_category_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sotongCode);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
								
				String SotongCategory = rs.getString("sotong_category");
		
				vo = new SotongCategoryVO(sotongCode,SotongCategory);
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
	
	public SotongCategoryVO selectSotongCategoryByCategory(String SotongCategory)
	{
		SotongCategoryVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select * from sotong_category_tb where sotong_category=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, SotongCategory);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				
				
				String sotongCode = rs.getString("sotong_category_code");
		
				vo = new SotongCategoryVO(sotongCode,SotongCategory);
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
		SotongCategoryDAO dao = new SotongCategoryDAO();
		System.out.println("삽입"+dao.insertSotongCategory("편지"));
		System.out.println("조회-카테고리명"+dao.selectSotongCategoryByCategory("일기"));
		System.out.println("수정"+dao.updateSotongCategory("sc2", "가족일기"));
		System.out.println("조회-코드"+dao.selectSotongCategory("sc2"));
		System.out.println("삭제"+dao.deleteSotongCategory("sc1"));
		System.out.println("조회-코드"+dao.selectSotongCategoryByCategory("편지"));
	}
	
}
