package dao.wish;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import dao.DBConnectionModule;

public class WishDAO implements Serializable{

private static final long serialVersionUID = 3466705589424033336L;
private DBConnectionModule connModule;
private Connection conn;

public WishDAO()
{
	connModule=DBConnectionModule.getInstance();
	conn=connModule.getConn();
}

public String makeWishCode() {
	Date d = new Date();
	StringTokenizer st = new StringTokenizer(new SimpleDateFormat().format(d),". ");
		
	String year = st.nextToken();
	String month = st.nextToken();
	String day = st.nextToken();
		
	String wishCode = "WD23"+day+year+month+d.getHours()+d.getMinutes()+d.getSeconds();
	return wishCode;
}

public int insertWish(String sotongContentsCode,String memberCode,String wishTitle,Date wishDate,Date wishEndDate,byte wishFinish)
{
	int rowNum=0;
	Date date=null;
	PreparedStatement pstmt=null;
	try
	{date=new Date();
		String sql="insert into wish_tb values(?,?,?,?,?,?,?)";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, makeWishCode());
		pstmt.setString(2,sotongContentsCode);
		pstmt.setString(3,memberCode);
		pstmt.setString(4,wishTitle);
		String date2=format(wishDate);
		String date3=format(wishEndDate);
		pstmt.setString(5,date2);
		pstmt.setString(6,date3);
		if(wishFinish==(byte)1)
		{
			pstmt.setString(7,"�Ϸ�");
		}
		else
		{
			pstmt.setString(7,"������");

		}
		rowNum=pstmt.executeUpdate();
	}
	catch(SQLException se)
	{
		se.printStackTrace();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		try
		{
			if(pstmt!=null)
			{
				pstmt.close();
			}
		}
		catch(SQLException se)
		{
			//se.printStackTrace();
		}
	}
	
	return rowNum;
			
}
public int deleteWish(String wishCode)
{
	int rowNum=0;
	
	PreparedStatement pstmt=null;
	try
	{
		String sql="Delete FROM wish_tb WHERE wish_code=?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, wishCode);
		rowNum=pstmt.executeUpdate();
	}
	catch(SQLException se)
	{
		//se.printStackTrace();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		try
		{
			if(pstmt!=null)
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
public int updateWish(String wishCode,String sotongContentsCode,String memberCode,String wishTitle,Date wishDate,Date wishEndDate,byte isFinished)
{
	int rowNum=0;
	
	PreparedStatement pstmt=null;
	try
	{
		
		String sql="UPDATE wish_tb SET sotong_contents_code=?,member_code=?,wish_title=?,wish_date=?,wish_end_date=?, wish_finish=? WHERE wish_code=?";
		
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1,sotongContentsCode);
		pstmt.setString(2,memberCode);
		pstmt.setString(3,wishTitle);
		pstmt.setString(4,format(wishDate));
		pstmt.setString(5,format(wishEndDate));
		
		
		if(isFinished==(byte)1)
		{
			pstmt.setString(6,"�Ϸ�");
		}
		else
		{
			pstmt.setString(6,"������");
		}
		pstmt.setString(7,wishCode);
		rowNum=pstmt.executeUpdate();
	}
	catch(SQLException se)
	{
		se.printStackTrace();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		try
		{
			if(pstmt!=null)
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
public int updateWish(String wishCode,WishVO infoVo)
{
	int rowNum=0;
	
	PreparedStatement pstmt=null;
	try
	{
		
		String sql="UPDATE wish_tb SET sotong_contents_code=?,member_code=?,wish_title=?,wish_date=?,wish_end_date=?, wish_finish=? WHERE wish_code=?";
		
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1,infoVo.getSotongContentsCode());
		pstmt.setString(2,infoVo.getMemberCode());
		pstmt.setString(3,infoVo.getWishTitle());
		pstmt.setString(4,format(infoVo.getWishDate()));
		pstmt.setString(5,format(infoVo.getWishEndDate()));
		
		Byte isFinished=infoVo.getWishFinish();
		if(isFinished==(byte)1)
		{
			pstmt.setString(6,"�Ϸ�");
		}
		else
		{
			pstmt.setString(6,"������");
		}
		pstmt.setString(7,wishCode);
		rowNum=pstmt.executeUpdate();
	}
	catch(SQLException se)
	{
		se.printStackTrace();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		try
		{
			if(pstmt!=null)
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
public WishVO selectWish(String wishCode)
{
	int rowNum=0;
	
	PreparedStatement pstmt=null;
	WishVO infoVo=null;
	try
	{
		
		String sql="SELECT * FROM wish_tb where WISH_CODE=?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, wishCode);
		ResultSet rs=pstmt.executeQuery();
		rs.next();
		infoVo=new WishVO();
		infoVo.setWishCode(rs.getString("wish_code"));
		infoVo.setSotongContentsCode(rs.getString("sotong_contents_code"));
		infoVo.setMemberCode(rs.getString("member_code"));
		infoVo.setWishTitle(rs.getString("wish_title"));
	
		infoVo.setWishDate(changeDate(rs.getString("wish_date")));
	
		infoVo.setWishEndDate(changeDate(rs.getString("wish_end_date")));
		
		String str=rs.getString("wish_Finish");
		if(str.equals("�Ϸ�"))
		{
			infoVo.setWishFinish((byte)1);
		}
		else
		{
			infoVo.setWishFinish((byte)0);
		}
	
		rowNum=pstmt.executeUpdate();
	}
	catch(SQLException se)
	{
		se.printStackTrace();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		try
		{
			if(pstmt!=null)
			{
				pstmt.close();
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}
	
	return infoVo;
			
}
public int finishWish(String wishCode,Date finishDate)
{
int rowNum=0;
	
	PreparedStatement pstmt=null;
	try
	{
		
		String sql="UPDATE wish_tb SET wish_end_date=?, wish_finish=? WHERE wish_code=?";
		
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1,format(finishDate));
		pstmt.setString(2,"�Ϸ�");
		pstmt.setString(3,wishCode);
		
		
		rowNum=pstmt.executeUpdate();
	}
	catch(SQLException se)
	{
		se.printStackTrace();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		try
		{
			if(pstmt!=null)
			{
				pstmt.close();
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}	
				
	return 1;
}
public String format(Date d){ // Date�� String���� ������/ ������ ���� �� ���
    SimpleDateFormat fmt = new SimpleDateFormat("yy-MM-dd");
    String date = fmt.format(d);
    return date;
}
	
public Date changeDate(String dateTime) { // String���� ������ data�� Date�� ����
	Date dt = null;
	try {
		dt = new SimpleDateFormat("yy-MM-dd").parse(dateTime);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return dt; 
}
public static void main(String args[])
{
	WishDAO dao=new WishDAO();
	//dao.delete("1563091630");
	//dao.insert("scontents4", "m3", "���������׽�Ʈ1", new Date(),new Date(115,6,30),(byte)0);
	//dao.update("��",new WishInfoVO("w4","scontents1","m2","test2",new Date(),new Date(115,12,1),(byte)1));//����ڰ� ��¥ ������ ex)2015-7-30�Է½� 115,6,30���� �ʱ�ȭ (�������� -1900�� ��, ������ -1�Ѱ�.)
	//System.out.println(dao.select("w2")); 
	dao.finishWish("w2", new Date(115,6,31));
}
}
