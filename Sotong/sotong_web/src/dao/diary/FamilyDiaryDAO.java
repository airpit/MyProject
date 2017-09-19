package dao.diary;

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

public class FamilyDiaryDAO implements Serializable{
	private static final long serialVersionUID = -8509569950698005672L;
	private Connection conn;
	
	public FamilyDiaryDAO() {
		conn = DBConnectionModule.getInstance().getConn();
	}
	
	public int insertDiary(String familyDiaryCode, String familyHomeCode, String familyDiaryDate)
	{	
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "insert into family_diary_tb values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, familyDiaryCode);
			pstmt.setString(2, familyHomeCode);
			pstmt.setString(3, familyDiaryDate);
					
			rowNum = pstmt.executeUpdate();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if(pstmt != null)
				{
					pstmt.close();
				}
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		return rowNum;
	}	
	// �����ϱ� ���ʻ�����, �����ϱ⸦ �����ϰ� �װ��� �ڵ带 ��������� �����ϱ� ������Ʈ�� �߰��� �� �ִ�!
	public String insertDiary(String familyHomeCode,String familyDiaryDate)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		String code = makeCode();	// �ڵ� �����ϴ� �޼ҵ� ȣ��.
		try{
			String sql = "insert into family_diary_tb values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, code);	
			pstmt.setString(2, familyHomeCode);
			pstmt.setString(3, familyDiaryDate);
					
			rowNum = pstmt.executeUpdate();
			//���ϰ�� ���� ���� ó���� ���� ���� �ǳ�
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if(pstmt != null)
				{
					pstmt.close();
				}
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		return code;
	}	
	
	public int delete(String familyDiaryCode){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from family_diary_tb where family_diary_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, familyDiaryCode);
			rowNum = pstmt.executeUpdate();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if(pstmt != null)
				{
					pstmt.close();
				}
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		return rowNum;
	}
	
	
	public FamilyDiaryVO selectDiaryInfo(String familyHomeCode, String familyDiaryDate)
	{
		FamilyDiaryVO res = null;
		PreparedStatement pstmt = null;
		
		try
		{
			String sql = "select * from family_diary_tb where family_home_code =? and family_diary_date = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, familyHomeCode);
			pstmt.setString(2, familyDiaryDate);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				String familyDiaryCode = rs.getString("family_diary_code");
				res = new FamilyDiaryVO(familyDiaryCode, familyHomeCode, familyDiaryDate);
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if(pstmt != null)
				{
					pstmt.close();
				}
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		
		return res;
	}
	
	public FamilyDiaryVO[] selectAllDiaryInfo(String familyHomeCode)
	{
		ArrayList<FamilyDiaryVO> list = new ArrayList<FamilyDiaryVO>();
		PreparedStatement pstmt = null;
		
		try
		{
			String sql = "select * from family_diary_tb where family_home_code=?  order by family_diary_date DESC";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, familyHomeCode);
		
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				String familyDiaryCode = rs.getString("family_diary_code");
				String familyDiaryDate = rs.getString("family_diary_date");
				
				list.add(new FamilyDiaryVO(familyDiaryCode, familyHomeCode, familyDiaryDate));
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if(pstmt != null)
				{
					pstmt.close();
				}
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
		
		return list.toArray(new FamilyDiaryVO[list.size()]);
	}
	
	//�ĺ��ڵ� ����
	public String makeCode(){
		Date d = new Date();
		StringTokenizer st = new StringTokenizer(new SimpleDateFormat().format(d),". ");
			
		String year = st.nextToken();
		String month = st.nextToken();
		String day = st.nextToken();
			
		String clientId = "FD02"+day+year+month+d.getHours()+d.getMinutes()+d.getSeconds();
		return clientId;
	}

	/*
	public static void main(String[] args)
	{
		FamilyDiaryDAO dao = new FamilyDiaryDAO();
		
		
		//int res = dao.insertDiary("h1", new Date());
		
		//System.out.println("insert ��� : " + res);
		
		
		//int res = dao.insertDiary("fdc4", "h1", new Date());
		
		//System.out.println("insert ��� : " + res);
		
		FamilyDiaryVO[] diary = dao.selectAllDiaryInfo("h1");
		
		System.out.println("��� �����ϱ� ���");
		for(FamilyDiaryVO temp : diary)
		{
			System.out.println(temp);
		}
		
		
		System.out.println("�ϳ��� �ϱ� ��� ���");
		
	//	System.out.println(dao.selectDiaryInfo("h1", new Date()));
		
		res = dao.delete("fdc3");
		
		System.out.println("delete ��� : " + res);
		
		
		
	}
	 */
}
