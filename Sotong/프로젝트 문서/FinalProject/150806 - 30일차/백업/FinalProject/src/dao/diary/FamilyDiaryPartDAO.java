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


public class FamilyDiaryPartDAO implements Serializable{
	private static final long serialVersionUID = 3295648143329183021L;
	private Connection conn;
	
	public FamilyDiaryPartDAO() {
		conn = DBConnectionModule.getInstance().getConn();
	}
	
	public int insertFamilyDiaryPart(String familyDiaryPartCode, String familyDiaryCode, String memberCode, String sotongContentsCode, Date familyDiaryPartDate)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		try
		{
			String sql = "insert into family_diary_part_tb values(?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, familyDiaryPartCode);
			pstmt.setString(2, familyDiaryCode);
			pstmt.setString(3, memberCode);
			pstmt.setString(4, sotongContentsCode);
			pstmt.setString(5, calDate(familyDiaryPartDate));
			
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
	
	
	public int insertFamilyDiaryPart(String familyDiaryCode, String memberCode, String sotongContentsCode, Date familyDiaryPartDate)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		try
		{
			String sql = "insert into family_diary_part_tb values(?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, makeCode());
			pstmt.setString(2, familyDiaryCode);
			pstmt.setString(3, memberCode);
			pstmt.setString(4, sotongContentsCode);
			pstmt.setString(5, calDate(familyDiaryPartDate));
			
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
	
	
	public int delete(String familyDiaryPartCode){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from family_diary_part_tb where family_diary_code=? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, familyDiaryPartCode);
			
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
	
	public int updateFamilyDiaryPart(String familyDiaryPartCode,String sotongContentsCode){
		int rowNum = 0;
		PreparedStatement pstmt = null;
		
		try
		{
			String sql = "update family_diary_part_tb set sotong_contents_code=? where family_diary_part_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, sotongContentsCode);
			pstmt.setString(2, familyDiaryPartCode);
			
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
	
	public FamilyDiaryPartVO[] selectAllDiaryPartInfo(String familyDiaryCode)
	{
		ArrayList<FamilyDiaryPartVO> list = new ArrayList<FamilyDiaryPartVO>();
		PreparedStatement pstmt = null;
		
		try
		{
			String sql = "select * from family_diary_part_tb where family_diary_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, familyDiaryCode);
		
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				String familyDiaryPartCode = rs.getString("family_diary_part_code");
				String memberCode = rs.getString("member_code");
				String sotongContentsCode = rs.getString("sotong_contents_code");
				Date familyDiaryPartDate = dateCut(rs.getString("family_diary_part_date"));	//지금은 날짜 메소드이지만 시간 메소드로 변경해야한다.
				
				
				list.add(new FamilyDiaryPartVO(familyDiaryPartCode, familyDiaryCode, memberCode, sotongContentsCode, familyDiaryPartDate));
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
		
		return list.toArray(new FamilyDiaryPartVO[list.size()]);
	}
	
	public FamilyDiaryPartVO selectDiaryInfo(String familyDiaryPartCode)
	{
		FamilyDiaryPartVO res = null;
		PreparedStatement pstmt = null;
		
		try
		{
			String sql = "select * from family_diary_part_tb where family_diary_part_code =? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, familyDiaryPartCode);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				String familyDiaryCode = rs.getString("family_diary_code");
				String memberCode = rs.getString("member_code");
				String sotongContentsCode = rs.getString("sotong_contents_code");
				Date familyDiaryPartDate = dateCut(rs.getString("family_diary_part_date"));
				
				res = new FamilyDiaryPartVO(familyDiaryPartCode, familyDiaryCode, memberCode, sotongContentsCode, familyDiaryPartDate);
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
	
	//식별코드 생성
	public String makeCode(){
		Date d = new Date();
		StringTokenizer st = new StringTokenizer(new SimpleDateFormat().format(d),". ");
			
		String year = st.nextToken();
		String month = st.nextToken();
		String day = st.nextToken();
			
		String clientId = "FP03"+day+year+month+d.getHours()+d.getMinutes()+d.getSeconds();
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
		FamilyDiaryPartDAO dao = new FamilyDiaryPartDAO();
		
		int rowNum = dao.insertFamilyDiaryPart("fdc1", "m1", "scontents2", new Date());
		
		System.out.println("insert 결과 : " + rowNum);
		
		rowNum = dao.insertFamilyDiaryPart("fdpc3", "fdc1", "m2", "scontents4", new Date());
		
		System.out.println("insert 결과 : " + rowNum);
		
		FamilyDiaryPartVO[] res = dao.selectAllDiaryPartInfo("fdc1");
		
		for(FamilyDiaryPartVO temp : res)
		{
			System.out.println(temp);
		}
		
		rowNum = dao.updateFamilyDiaryPart("fdpc3", "scontents2");
		
		System.out.println("update 결과 : " + rowNum + " - " + dao.selectDiaryInfo("fdpc3"));
		
		rowNum = dao.delete("fdpc3");
		
		System.out.println("delete 결과 :" +  rowNum);
	}
}
