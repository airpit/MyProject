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

public class EmoticonDAO implements Serializable{

	private static final long serialVersionUID = 4900314399848899713L;
	private DBConnectionModule connModule;
	private Connection conn;
	
	public EmoticonDAO() {
		connModule = DBConnectionModule.getInstance();
		conn = connModule.getConn();
	}
	
	public String makeEmoticonCode(){ //�ӽ��ڵ�
		Date d = new Date();
		StringTokenizer st = new StringTokenizer(new SimpleDateFormat().format(d),". ");
			
		String year = st.nextToken();
		String month = st.nextToken();
		String day = st.nextToken();
			
		String emoticonCode = "ED17"+day+year+month+d.getHours()+d.getMinutes()+d.getSeconds();
		return emoticonCode;
	}
	
	public int insertEmoticon(String emoticonName, String emoticonRoute, String emoticonCategoryCode)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "insert into Emoticon_tb values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, makeEmoticonCode());
			pstmt.setString(2, emoticonName);
			pstmt.setString(3, emoticonRoute);
			pstmt.setString(4, emoticonCategoryCode);
						
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
	
	/**
	 * �̸�Ƽ�� �ĺ��ڵ�� �̸�Ƽ�� �˻��ؼ� ����
	 * @param emoticonCode �̸�Ƽ�� �ĺ��ڵ�
	 * @param emoticonName �̸�Ƽ�� �̸�
	 * @param emoticonRoute �̸�Ƽ�� ���
	 * @param emoticonCategoryCode �̸�Ƽ�� ī�װ� �ڵ�
	 * @return ������, ��������
	 */
	public int updateEmoticon(String emoticonCode, String emoticonName, String emoticonRoute, String emoticonCategoryCode){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "update emoticon_tb set emoticon_name=?, emoticon_route=?, emoticon_category_code=? where emoticon_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, emoticonName);
			pstmt.setString(2,emoticonRoute);
			pstmt.setString(3,emoticonCategoryCode);
			pstmt.setString(4,emoticonCode);
			
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
	
	
	/**
	 * �̸�Ƽ�� �̸����� �˻��ؼ� �̸�Ƽ�� ���� ����
	 * @param emoticonName �̸�Ƽ�� �̸�
	 * @param newEmoticonName ������ �̸�Ƽ�� �̸�
	 * @param emoticonRoute ������ �̸�Ƽ�� ���
	 * @param emoticonCategoryCode ������ �̸�Ƽ�� ī�װ� 
	 * @return ������, ��������
	 */
	public int updateEmoticonByEmoticonName(String emoticonName, String newEmoticonName, String emoticonRoute, String emoticonCategoryCode){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "update emoticon_tb set emoticon_name=?, emoticon_route=?, emoticon_category_code=? where emoticon_name=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, newEmoticonName);
			pstmt.setString(2,emoticonRoute);
			pstmt.setString(3,emoticonCategoryCode);
			pstmt.setString(4,emoticonName);
			
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
	
	/**
	 * �̸�Ƽ�� �ڵ�� �˻��ؼ� ����
	 * @param emoticonCode ������ �̸�Ƽ�� �ڵ�
	 * @return ������, ��������
	 */
	public int deleteEmoticon(String emoticonCode){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from emoticon_tb where emoticon_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, emoticonCode);
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
	
	
	/**
	 * �̸�Ƽ�� �̸����� �˻��Ͽ� ����
	 * @param emoticonName ������ �̸�Ƽ�� �̸�
	 * @return ������, ��������
	 */
	public int deleteEmoticonByEmoticonName(String emoticonName){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from emoticon_tb where emoticon_name=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, emoticonName);
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
	
	/**
	 * �̸�Ƽ�� �ڵ�� �̸�Ƽ��VO ������
	 * @param emoticonCode ��ȸ�� �̸�Ƽ�� �ڵ�
	 * @return ��ȸ ��� ���� VO��ü
	 */
	public EmoticonVO selectEmoticon(String emoticonCode)
	{
		EmoticonVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select * from emoticon_tb where emoticon_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emoticonCode);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
								
				String emoticonName = rs.getString("emoticon_name");
				String emoticonRoute = rs.getString("emoticon_route");
				String emoticonCategoryCode = rs.getString("emoticon_category_code");
		
				vo = new EmoticonVO(emoticonCode,emoticonName,emoticonRoute,emoticonCategoryCode);
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
	
	/**
	 * @param emoticonName ��ȸ�� �̸�Ƽ�� �̸�
	 * @return ��ȸ �˻� ��� VO ��ü
	 */
	public EmoticonVO selectEmoticonByEmoticonName(String emoticonName)
	{
		EmoticonVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select * from emoticon_tb where emoticon_name=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emoticonName);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
								
				String emoticonCode = rs.getString("emoticon_code");
				String emoticonRoute = rs.getString("emoticon_route");
				String emoticonCategoryCode = rs.getString("emoticon_category_code");
		
				vo = new EmoticonVO(emoticonCode,emoticonName,emoticonRoute,emoticonCategoryCode);
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
	public EmoticonVO[] selectEmoticonByCategory(String emoticonCategoryCode)
	{
		ArrayList<EmoticonVO> voList = null;
		EmoticonVO vo = null;
		PreparedStatement pstmt = null;
		try{
			voList = new ArrayList<EmoticonVO>();
			String sql = "select * from emoticon_tb where emoticon_category_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emoticonCategoryCode);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
					
				String emoticonName = rs.getString("emoticon_name");
				String emoticonCode = rs.getString("emoticon_code");
				String emoticonRoute = rs.getString("emoticon_route");
								
				vo = new EmoticonVO(emoticonCode,emoticonName,emoticonRoute,emoticonCategoryCode);
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
			return voList.toArray(new EmoticonVO[voList.size()]);
		}
		else
		{
			return null;
		}
	}
	
	
	public static void main(String[] args)
	{
		EmoticonDAO dao = new EmoticonDAO();
//		System.out.println("���� : " +dao.insertEmoticon("�ȳ�", "emoticon/anbu/123.jpg", "ec1"));
//		System.out.println("���� : " +dao.updateEmoticon("em2", "����", "emoticon/sad/TOT.png", "ec2"));
//		System.out.println("��ȸ : " + dao.selectEmoticonByEmoticonName("����"));
//		System.out.println("���� - �̸�Ƽ�� �̸� : " + dao.updateEmoticonByEmoticonName("������", "����", "emoticon/celebration/1.png", "ec4"));
//		System.out.println("��ȸ - �̸�Ƽ�� �̸� : " + dao.selectEmoticonByEmoticonName("�ȳ�"));
//		System.out.println("���� : " + dao.deleteEmoticon("6000301571044"));
//		System.out.println("���� - �̸�Ƽ�� �̸� : "+dao.deleteEmoticonByEmoticonName("��Ʈ"));
		
//		EmoticonVO[] volist = dao.selectEmoticonByCategory("ec4");
//		System.out.println("ī�װ� ��ȸ : ");
//		for(int i=0;i<volist.length;i++)
//		{
//			System.out.println(volist[i]);
//		}
//		
	}
	
}
