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
	
	public int insertDiary(String familyDiaryCode, String familyHomeCode, Date familyDiaryDate)
	{	
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "insert into family_diary_tb values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, familyDiaryCode);
			pstmt.setString(2, familyHomeCode);
			pstmt.setString(3, calDate(familyDiaryDate));
					
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
	public String insertDiary(String familyHomeCode,Date familyDiaryDate)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		String code = makeCode();	// �ڵ� �����ϴ� �޼ҵ� ȣ��.
		try{
			String sql = "insert into family_diary_tb values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, code);	
			pstmt.setString(2, familyHomeCode);
			pstmt.setString(3, calDate(familyDiaryDate));
					
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
	
	//��� Ȩ�ڵ尡 �ʿ䰡 ���� �Ѵ�..������ �ұ�
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
	
	
	public FamilyDiaryVO selectDiaryInfo(String familyHomeCode, Date familyDiaryDate)
	{
		FamilyDiaryVO res = null;
		PreparedStatement pstmt = null;
		
		try
		{
			String sql = "select * from family_diary_tb where family_home_code =? and family_diary_date = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, familyHomeCode);
			pstmt.setString(2, calDate(familyDiaryDate));
			
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
			String sql = "select * from family_diary_tb where family_home_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, familyHomeCode);
		
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				String familyDiaryCode = rs.getString("family_diary_code");
				Date familyDiaryDate = dateCut(rs.getString("family_diary_date"));
				
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
	
	//DB���� �����͸� �о������ DATE ��ü�� ��ȯ
	public Date dateCut(String date)
	{
		 Integer year = new Integer(date.substring(0, 2));
		 Integer month = new Integer(date.substring(3, 5));
		 Integer day = new Integer(date.substring(6, 8));
		 
		 return new Date(year+100,month-1,day);
	}
	
	//DATE ��ü�� ���� String���� ��ȯ
	public String calDate(Date date)
	{
		int year = date.getYear()-100;
		int month = date.getMonth()+1;
		int day = date.getDate();
		
		if(month < 10)
		 {
			 return year + "-0" + month + "-" + day;
		 }		 
		 return year + "-" + month + "-" + day;
	}
	
	public static void main(String[] args)
	{
		FamilyDiaryDAO dao = new FamilyDiaryDAO();
		
		/*
		int res = dao.insertDiary("h1", new Date());
		
		System.out.println("insert ��� : " + res);
		*/
		
		int res = dao.insertDiary("fdc4", "h1", new Date());
		
		System.out.println("insert ��� : " + res);
		
		FamilyDiaryVO[] diary = dao.selectAllDiaryInfo("h1");
		
		System.out.println("��� �����ϱ� ���");
		for(FamilyDiaryVO temp : diary)
		{
			System.out.println(temp);
		}
		
		
		System.out.println("�ϳ��� �ϱ� ��� ���");
		
		System.out.println(dao.selectDiaryInfo("h1", new Date()));
		
		res = dao.delete("fdc3");
		
		System.out.println("delete ��� : " + res);
		
		
		
	}
}
