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

public class ConnectionWaitingViewDAO implements Serializable{

	private static final long serialVersionUID = 1171327299317759427L;
	private DBConnectionModule connModule;
	private Connection conn;
	
	public String makeConnectionWaitingCode(){ //임시코드
		Date d = new Date();
		StringTokenizer st = new StringTokenizer(new SimpleDateFormat().format(d),". ");
			
		String year = st.nextToken();
		String month = st.nextToken();
		String day = st.nextToken();
			
		String EmoticonCode = "1300"+day+year+month+d.getHours()+d.getMinutes()+d.getSeconds();
		return EmoticonCode;
	}
	
	public ConnectionWaitingViewDAO() {
		connModule = DBConnectionModule.getInstance();
		conn = connModule.getConn();
	}
	
	/** 우리홈에 연결요청한 사람 목록
	 * @param homeCode 우리 가족 홈 코드
	 * @return 우리 가족에 연결요청한 홈의 정보 목록
	 */
	public ConnectionWaitingViewVO[] selectGetConnectionWaitingList(String homeCode)
	{
		ArrayList<ConnectionWaitingViewVO> voList = null;
		ConnectionWaitingViewVO vo = null;
		PreparedStatement pstmt = null;
		try{
			voList = new ArrayList<ConnectionWaitingViewVO>();
			String sql = "select * from connection_waiting_view where receiver_home_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, homeCode);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				
				String waitingCode = rs.getString("waiting_code");
				String senderHomeCode = rs.getString("sender_home_code");				
				String familyHomeName = rs.getString("family_home_name");
				String senderHomeManagerName = rs.getString("member_name");
				String receiverHomeCode = rs.getString("receiver_home_code");
				
							
				vo = new ConnectionWaitingViewVO(waitingCode,senderHomeCode,familyHomeName,senderHomeManagerName,receiverHomeCode);
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
			return voList.toArray(new ConnectionWaitingViewVO[voList.size()]);
		}
	}
	
	/** 내가 연결요청 보낸 홈 목록
	 * @param homeCode 우리 홈 코드
	 * @return 내가(우리 홈 매니저가) 연결요청한 홈 목록
	 */
	public ConnectionWaitingViewVO[] selectGetConnectionSendList(String homeCode)
	{
		ArrayList<ConnectionWaitingViewVO> voList = null;
		ConnectionWaitingViewVO vo = null;
		PreparedStatement pstmt = null;
		try{
			voList = new ArrayList<ConnectionWaitingViewVO>();
			String sql = "select * from connection_waiting_view where sender_home_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, homeCode);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				
				String waitingCode = rs.getString("waiting_code");
				String senderHomeCode = rs.getString("sender_home_code");				
				String familyHomeName = rs.getString("family_home_name");
				String senderHomeManagerName = rs.getString("member_name");
				String receiverHomeCode = rs.getString("receiver_home_code");
				
							
				vo = new ConnectionWaitingViewVO(waitingCode,senderHomeCode,familyHomeName,senderHomeManagerName,receiverHomeCode);
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
			return voList.toArray(new ConnectionWaitingViewVO[voList.size()]);
		}
	}
	
	public static void main(String[] args)
	{
		ConnectionWaitingViewDAO dao = new ConnectionWaitingViewDAO();
		ConnectionWaitingViewVO[] voList = dao.selectGetConnectionWaitingList("h1");
		for(int i=0; i<voList.length;i++)
		{
			System.out.println(voList[i]);
		}
		
		ConnectionWaitingViewVO[] voList2 = dao.selectGetConnectionSendList("h4");
		for(int i=0; i<voList2.length;i++)
		{
			System.out.println(voList[i]);
		}
		
	}
}
