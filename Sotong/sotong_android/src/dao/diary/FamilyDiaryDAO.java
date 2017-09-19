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
	// 가족일기 최초생성시, 가족일기를 생성하고 그것의 코드를 리턴해줘야 가족일기 개인파트를 추가할 수 있다!
	public String insertDiary(String familyHomeCode,Date familyDiaryDate)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		String code = makeCode();	// 코드 생성하는 메소드 호출.
		try{
			String sql = "insert into family_diary_tb values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, code);	
			pstmt.setString(2, familyHomeCode);
			pstmt.setString(3, calDate(familyDiaryDate));
					
			rowNum = pstmt.executeUpdate();
			//리턴결과 값에 따라 처리할 내용 추후 의논
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
	
	//사실 홈코드가 필요가 없긴 한대..지워야 할까
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
	
	//식별코드 생성
	public String makeCode(){
		Date d = new Date();
		StringTokenizer st = new StringTokenizer(new SimpleDateFormat().format(d),". ");
			
		String year = st.nextToken();
		String month = st.nextToken();
		String day = st.nextToken();
			
		String clientId = "FD02"+day+year+month+d.getHours()+d.getMinutes()+d.getSeconds();
		return clientId;
	}
	
	//DB에서 데이터를 읽어왔을때 DATE 객체로 변환
	public Date dateCut(String date)
	{
		 Integer year = new Integer(date.substring(0, 2));
		 Integer month = new Integer(date.substring(3, 5));
		 Integer day = new Integer(date.substring(6, 8));
		 
		 return new Date(year+100,month-1,day);
	}
	
	//DATE 객체의 값을 String으로 변환
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
		
		System.out.println("insert 결과 : " + res);
		*/
		
		int res = dao.insertDiary("fdc4", "h1", new Date());
		
		System.out.println("insert 결과 : " + res);
		
		FamilyDiaryVO[] diary = dao.selectAllDiaryInfo("h1");
		
		System.out.println("모든 가족일기 출력");
		for(FamilyDiaryVO temp : diary)
		{
			System.out.println(temp);
		}
		
		
		System.out.println("하나의 일기 결과 출력");
		
		System.out.println(dao.selectDiaryInfo("h1", new Date()));
		
		res = dao.delete("fdc3");
		
		System.out.println("delete 결과 : " + res);
		
		
		
	}
}
