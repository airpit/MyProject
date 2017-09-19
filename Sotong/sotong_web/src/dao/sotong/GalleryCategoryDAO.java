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

public class GalleryCategoryDAO implements Serializable{

	private static final long serialVersionUID = -4147830331977592176L;
	private DBConnectionModule connModule;
	private Connection conn;
	
	public GalleryCategoryDAO() {
		connModule = DBConnectionModule.getInstance();
		conn = connModule.getConn();
	}
	
	public String makeGalleryCode(){ //임시코드
		Date d = new Date();
		StringTokenizer st = new StringTokenizer(new SimpleDateFormat().format(d),". ");
			
		String year = st.nextToken();
		String month = st.nextToken();
		String day = st.nextToken();
			
		String galleryCode = "GC18"+day+year+month+d.getHours()+d.getMinutes()+d.getSeconds();
		return galleryCode;
	}
	
	public int insertGalleryCategory(String galleryCategory)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "insert into gallery_category_tb values(?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, makeGalleryCode());
			pstmt.setString(2, galleryCategory);
						
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
	
	public int updateGalleryCategory(String galleryCode, String galleryCategory){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "update gallery_category_tb set gallery_category=? where gallery_category_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, galleryCategory);
			pstmt.setString(2, galleryCode);
			
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
	
	public int updateGalleryCategoryByCategory(String galleryCategory, String changeCategory){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "update gallery_category_tb set gallery_category=? where gallery_category=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, changeCategory);
			pstmt.setString(2, galleryCategory);
			
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
	
	public int deleteGalleryCategory(String galleryCode){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from gallery_category_tb where gallery_category_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, galleryCode);
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
	
	public int deleteGalleryCategoryByCategory(String galleryCategory){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from gallery_category_tb where gallery_category=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, galleryCategory);
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
	
	public GalleryCategoryVO selectGalleryCategory(String galleryCode)
	{
		GalleryCategoryVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select * from gallery_category_tb where gallery_category_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, galleryCode);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
								
				String galleryCategory = rs.getString("gallery_category");
		
				vo = new GalleryCategoryVO(galleryCode,galleryCategory);
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
	
	public GalleryCategoryVO selectGalleryCategoryByCategory(String galleryCategory)
	{
		GalleryCategoryVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select * from gallery_category_tb where gallery_category=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, galleryCategory);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				
				
				String galleryCode = rs.getString("gallery_category_code");
		
				vo = new GalleryCategoryVO(galleryCode,galleryCategory);
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
		GalleryCategoryDAO dao = new GalleryCategoryDAO();
		System.out.println("삽입"+dao.insertGalleryCategory("연락"));
		System.out.println("조회-카테고리명"+dao.selectGalleryCategoryByCategory("편지"));
		System.out.println("수정"+dao.updateGalleryCategory("c2", "가족일기"));
		System.out.println("조회-코드"+dao.selectGalleryCategory("c2"));
		System.out.println("삭제"+dao.deleteGalleryCategory("4000301579048"));
		System.out.println("조회-코드"+dao.selectGalleryCategoryByCategory("연락"));
	}
	
}
