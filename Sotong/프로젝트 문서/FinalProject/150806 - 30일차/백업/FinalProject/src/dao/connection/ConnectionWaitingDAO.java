package dao.connection;

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

public class ConnectionWaitingDAO implements Serializable{

	private static final long serialVersionUID = -863359229673091405L;
	private DBConnectionModule connModule;
	private Connection conn;
	
	public String makeConnectionWaitingCode(){ //�ӽ��ڵ�
		Date d = new Date();
		StringTokenizer st = new StringTokenizer(new SimpleDateFormat().format(d),". ");
			
		String year = st.nextToken();
		String month = st.nextToken();
		String day = st.nextToken();
			
		String connectionWaitingCode = "CW24"+day+year+month+d.getHours()+d.getMinutes()+d.getSeconds();
		return connectionWaitingCode;
	}
	
	public ConnectionWaitingDAO() {
		connModule = DBConnectionModule.getInstance();
		conn = connModule.getConn();
	}
	
	public ConnectionWaitingVO[] selectGetConnectionWaitingList(String homeCode)
	{
		ArrayList<ConnectionWaitingVO> voList = null;
		ConnectionWaitingVO vo = null;
		PreparedStatement pstmt = null;
		try{
			voList = new ArrayList<ConnectionWaitingVO>();
			String sql = "select * from connection_waiting_tb where receiver_home_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, homeCode);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String waitingCode = rs.getString("waiting_code");
				String receiverHomeCode = rs.getString("receiver_home_code");
				String senderHomeCode = rs.getString("sender_home_code");
				Date waitingDate = changeDate(rs.getString("waiting_date"));
							
				vo = new ConnectionWaitingVO(waitingCode,receiverHomeCode, senderHomeCode, waitingDate);
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
		if(voList==null || voList.size()<1)
		{
			return null;
		}
		else
		{
			return voList.toArray(new ConnectionWaitingVO[voList.size()]);
		}
	}
	
	public ConnectionWaitingVO[] selectGetMyConnectionSenderList(String homeCode)
	{
		ArrayList<ConnectionWaitingVO> voList = null;
		ConnectionWaitingVO vo = null;
		PreparedStatement pstmt = null;
		try{
			voList = new ArrayList<ConnectionWaitingVO>();
			String sql = "select * from connection_waiting_tb where sender_home_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, homeCode);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String waitingCode = rs.getString("waiting_code");
				String receiverHomeCode = rs.getString("receiver_home_code");
				String senderHomeCode = rs.getString("sender_home_code");
				Date waitingDate = changeDate(rs.getString("waiting_date"));
							
				vo = new ConnectionWaitingVO(waitingCode,receiverHomeCode, senderHomeCode, waitingDate);
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
		if(voList==null || voList.size()<1)
		{
			return null;
		}
		else
		{
			return voList.toArray(new ConnectionWaitingVO[voList.size()]);
		}
	}
	
	public int insertConnectionWaiting(String homeCode, String neighborCode)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "insert into connection_waiting_tb values (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, makeConnectionWaitingCode());
			pstmt.setString(2, homeCode);
			pstmt.setString(3, neighborCode);
			pstmt.setString(4, format(new Date()));
						
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
	
	public int deleteConnectionWaiting(String waitingCode)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from connection_waiting_tb where waiting_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, waitingCode);
			
			rowNum = pstmt.executeUpdate();
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
	
	public int deleteConnectionWaiting(String homeCode, String neighborCode)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from connection_waiting_tb where receiver_home_code=? AND sender_home_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, homeCode);
			pstmt.setString(2, neighborCode);
			
			rowNum = pstmt.executeUpdate();
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
	
	public String format(Date d){ // Date�� String���� ������/ ������ ���� �� ���
	    SimpleDateFormat fmt = new SimpleDateFormat("yy-MM-dd");
	    String date = fmt.format(d);
	    return date;
	}
		
	public Date changeDate(String dateTime) { // String���� ������ data�� Date�� ����
		int year = Integer.parseInt(dateTime.substring(0,2));
		int mon = Integer.parseInt(dateTime.substring(3,5));
		int date = Integer.parseInt(dateTime.substring(6,8));
 
		Date reDate = new Date(year,mon,date);
		return reDate; 
	}
	
	public static void main(String[] args)
	{
		ConnectionWaitingDAO dao = new ConnectionWaitingDAO();
//		
//		System.out.println("�߰� : " + dao.insertConnectionWaiting("h2", "h3"));
//		System.out.println("Ȯ�� : ");
//		ConnectionWaitingVO[] voList = dao.selectGetConnectionWaitingList("h2");
//		for(int i=0; i<voList.length;i++)
//		{
//			System.out.println(voList[i]);
//		}
		System.out.println("���� : " + dao.deleteConnectionWaiting("h2", "h3"));
	}
}
