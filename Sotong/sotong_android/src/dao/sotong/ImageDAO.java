package dao.sotong;


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

public class ImageDAO implements Serializable{
		
	private static final long serialVersionUID = -3754330764297334529L;
	private DBConnectionModule connModule;
	private Connection conn;
	
	public ImageDAO() {
		connModule = DBConnectionModule.getInstance();
		conn = connModule.getConn();
	}
	
	public String makeImageCode(){ //임시코드
		Date d = new Date();
		StringTokenizer st = new StringTokenizer(new SimpleDateFormat().format(d),". ");
			
		String year = st.nextToken();
		String month = st.nextToken();
		String day = st.nextToken();
			
		String ImageCode = "ID19"+day+year+month+d.getHours()+d.getMinutes()+d.getSeconds();
		return ImageCode;
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
	
	public String insertImageAndReturnImageCode(String imageName, Date imageWrittenDate, String galleryCategoryCode){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		String returnImageCode = null;
		try{
			String sql = "insert into image_tb values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			returnImageCode = makeImageCode();
			pstmt.setString(1, returnImageCode);
			pstmt.setString(2, imageName);
			pstmt.setString(3, format(imageWrittenDate));
			pstmt.setString(4, galleryCategoryCode);
			
			rowNum = pstmt.executeUpdate();
			if(rowNum!=0)
			{
				conn.commit();
			}
			else
			{
				conn.rollback();
				returnImageCode = null;
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
		return returnImageCode;
	}
	
	public int insertImage(String imageName, Date imageWrittenDate, String galleryCategoryCode)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "insert into Image_tb values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, makeImageCode());
			pstmt.setString(2, imageName);
			pstmt.setString(3, format(imageWrittenDate));
			pstmt.setString(4, galleryCategoryCode);
						
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
	

	public int updateImage(String imageCode, String imageName, Date imageWrittenDate, String galleryCategoryCode){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "update image_tb set image_name=?, image_written_date=?, gallery_category_code=? where image_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, imageName);
			pstmt.setString(2,format(imageWrittenDate));
			pstmt.setString(3,galleryCategoryCode);
			pstmt.setString(4,imageCode);
			
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
	

	public int deleteImage(String imageCode){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from image_tb where image_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, imageCode);
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

	public ImageVO selectImage(String imageCode)
	{
		ImageVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select * from image_tb where image_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, imageCode);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
								
				String imageName = rs.getString("image_name");
				Date imageWrittenDate = changeDate(rs.getString("image_written_date"));
				String galleryCategoryCode = rs.getString("gallery_category_code");
		
				vo = new ImageVO(imageCode,imageName,imageWrittenDate,galleryCategoryCode);
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
	
	public ImageVO[] selectImageByCategory(String galleryCategoryCode)
	{
		ArrayList<ImageVO> voList = null;
		ImageVO vo = null;
		PreparedStatement pstmt = null;
		try{
			voList = new ArrayList<ImageVO>();
			String sql = "select * from image_tb where gallery_category_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, galleryCategoryCode);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
								
				String imageCode = rs.getString("image_code");
				String imageName = rs.getString("image_name");
				Date imageWrittenDate = changeDate(rs.getString("image_written_date"));
						
				vo = new ImageVO(imageCode,imageName,imageWrittenDate,galleryCategoryCode);
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
		if(voList != null && voList.size()>=1)
		{
			return voList.toArray(new ImageVO[voList.size()]);
		}
		else
		{
			return null;
		}
	}

	public static void main(String[] args)
	{
		ImageDAO dao = new ImageDAO();
		
		System.out.println(dao.insertImageAndReturnImageCode("테스트이미지", new Date(), "c1"));
		
		
		
//		System.out.println("삽입 : " +dao.insertImage("원피스이미지", new Date(15,11,22), "c1"));
//		System.out.println("수정 : " +dao.updateImage("i2", "image/onepiece/luffy.png",new Date(), "c2"));
//		System.out.println("조회 : " + dao.selectImage("i2"));
//		System.out.println("삭제 : " + dao.deleteImage("i4"));
//		
//		ImageVO[] volist = dao.selectImageByCategory("c1");
//		System.out.println("카테고리 조회 : ");
//		for(int i=0;i<volist.length;i++)
//		{
//			System.out.println(volist[i]);
//		}
//		
//		
	}
	
}
