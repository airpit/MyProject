package dao.letter;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import dao.DBConnectionModule;

public class LetterDAO implements Serializable{

	private static final long serialVersionUID = 8110200064008302372L;
private DBConnectionModule connModule;
private Connection conn;

public LetterDAO()
{
	connModule=DBConnectionModule.getInstance();
	conn=connModule.getConn();
}

public String makeLetterCode(){ //�ӽ��ڵ�
	Date d = new Date();
	StringTokenizer st = new StringTokenizer(new SimpleDateFormat().format(d),". ");
		
	String year = st.nextToken();
	String month = st.nextToken();
	String day = st.nextToken();
		
	String letterCode = "LD06"+day+year+month+d.getHours()+d.getMinutes()+d.getSeconds();
	return letterCode;
}

public int insert(String sotongContentsCode,String senderCode,String receiverCode,String letterTitle,String sendDate)
{
	int rowNum=0;
	Date date=null;
	PreparedStatement pstmt=null;
	try
	{date=new Date();
		String sql="insert into letter_tb values(?,?,?,?,?,?)";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, makeLetterCode());
		pstmt.setString(2,sotongContentsCode);
		pstmt.setString(3,senderCode);
		pstmt.setString(4,receiverCode);
		pstmt.setString(5,letterTitle);
		pstmt.setString(6,sendDate);
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
public int delete(String letterCode)
{
	int rowNum=0;
	
	PreparedStatement pstmt=null;
	try
	{
		String sql="Delete FROM letter_tb WHERE letter_code=?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, letterCode);
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
public int update(String letterCode,String sotongContentsCode,String senderCode,String receiverCode,String letterTitle,String sendDate)
{
	int rowNum=0;
	
	PreparedStatement pstmt=null;
	try
	{
		
		String sql="UPDATE letter_tb SET sotong_contents_code=?,sender_code=?,receiver_code=?,letter_title=?,send_date=? WHERE letter_code=?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, sotongContentsCode);
		pstmt.setString(2, senderCode);
		pstmt.setString(3, receiverCode);
		pstmt.setString(4, letterTitle);
		pstmt.setString(5, sendDate);
		pstmt.setString(6, letterCode);
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
public int update(String letterCode,LetterVO infoVo)
{
	int rowNum=0;
	
	PreparedStatement pstmt=null;
	try
	{
		
		String sql="UPDATE letter_tb SET sotong_contents_code=?,sender_code=?,receiver_code=?,letter_title=?,send_date=? WHERE letter_code=?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, infoVo.getSotongContentsCode());
		pstmt.setString(2, infoVo.getSenderCode());
		pstmt.setString(3, infoVo.getReceiverCode());
		pstmt.setString(4, infoVo.getLetterTitle());
		pstmt.setString(5, infoVo.getSendDate());
		pstmt.setString(6, letterCode);
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
public LetterVO select(String letterCode)
{
	int rowNum=0;
	
	PreparedStatement pstmt=null;
	LetterVO infoVo=null;
	try
	{
		
		String sql="SELECT * FROM letter_tb where LETTER_CODE=?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, letterCode);
		ResultSet rs=pstmt.executeQuery();
		rs.next();
		infoVo=new LetterVO();
		infoVo.setLetterCode(rs.getString("letter_code"));
		infoVo.setSotongContentsCode(rs.getString("sotong_contents_code"));
		infoVo.setSenderCode(rs.getString("sender_code"));
		infoVo.setReceiverCode(rs.getString("receiver_code"));
		infoVo.setLetterTitle(rs.getString("letter_title"));
		infoVo.setSendDate(rs.getString("send_date"));
	
		
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
public List<LetterVO> selectByMember(String memberCode)
{
	int rowNum=0;
	
	PreparedStatement pstmt=null;
	List<LetterVO> list=new ArrayList<LetterVO>();
	LetterVO infoVo=null;
	try
	{
		
		String sql="SELECT * FROM letter_tb where receiver_code=?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, memberCode);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
		infoVo=new LetterVO();
		infoVo.setLetterCode(rs.getString("letter_code"));
		infoVo.setSotongContentsCode(rs.getString("sotong_contents_code"));
		infoVo.setSenderCode(rs.getString("sender_code"));
		infoVo.setReceiverCode(rs.getString("receiver_code"));
		infoVo.setLetterTitle(rs.getString("letter_title"));
		infoVo.setSendDate(rs.getString("send_date"));
		list.add(infoVo);
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
	
	return list;
			
}
public String format(Date d){ // Date�� String���� ������/ ������ ���� �� ���
    SimpleDateFormat fmt = new SimpleDateFormat("yy-MM-dd");
    String date = fmt.format(d);
    return date;
}
	
public Date changeDate(String dateTime) { // String���� ������ data�� Date�� ����. �¿����� 8/6 21:10
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
	LetterDAO dao=new LetterDAO();
	//dao.delete("201563202230");
	//dao.insert("scontents5", "m4", "m1", "��wq��w��w��", new Date());
	//dao.update("15113193819", dao.select("l2"));
	System.out.println(dao.select("1563012207"));
}
}
