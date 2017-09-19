package dao.home;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import dao.DBConnectionModule;


public class FamilyMemberDAO implements Serializable{

	private static final long serialVersionUID = -918484301002759652L;
	private DBConnectionModule connModule;
	private Connection conn;
	
	public FamilyMemberDAO() {
		connModule = DBConnectionModule.getInstance();
		conn = connModule.getConn();
	}
	
	public String makeMemberCode(){ //�ӽ��ڵ�
		Date d = new Date();
		StringTokenizer st = new StringTokenizer(new SimpleDateFormat().format(d),". ");
			
		String year = st.nextToken();
		String month = st.nextToken();
		String day = st.nextToken();
			
		String memberCode = "FM05"+day+year+month+d.getHours()+d.getMinutes()+d.getSeconds();
		return memberCode;
	}
	
	public int insertMember(String familyHomecode, 
			String memberName, String memberPhone, String memberEmail,
			String memberId, String memberPw, byte memberRole) {
		
		System.out.println("FamilyMemberDAO : Homcode : " + familyHomecode);
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "insert into family_member_tb values (?, ?, ?, ?, ?, ?, ?, 'img/profile/default.JPG', ?, null, null, ?)";
			pstmt = conn.prepareStatement(sql);
			System.out.println(memberPhone);
			
			pstmt.setString(1, makeMemberCode());
			pstmt.setString(2, familyHomecode);
			pstmt.setString(3, memberName);
			pstmt.setString(4, memberPhone);
			pstmt.setString(5, memberEmail);
			pstmt.setString(6, memberId);
			pstmt.setString(7, memberPw);
			pstmt.setString(8, memberName); // ���� ��ġ	
			pstmt.setByte(9, memberRole);   // �Ŵ������� �б�� 1 �Ǵ� 0 , 1 : �Ŵ���  0 : ����
					
			rowNum = pstmt.executeUpdate();
			if(rowNum != 0)
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
	
	public FamilyMemberVO checkLogin(String id, String pw)
	{		
		FamilyMemberVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select * from family_member_tb where member_id=? and MEMBER_PW=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				String memberCode = rs.getString("member_code");
				String familyHomecode = rs.getString("family_home_code");
				String memberName = rs.getString("member_name");
				String memberPhone = rs.getString("member_phone");
				String memberEmail = rs.getString("member_email");
			
				String memberPhoto = rs.getString("member_photo");
				String memberNickName = rs.getString("member_nickname");
				String memberColor = rs.getString("member_color");
				String memberBirth = rs.getString("member_birth");
				String memberRole = rs.getString("member_role");
				
				if (memberColor == null) {
					memberColor = "#fff";
				}
				Date birth = null;
				if (memberBirth != null) {
					birth = changeDate(memberBirth);
				}
				vo = new FamilyMemberVO(memberCode,familyHomecode,memberName,memberPhone,memberEmail,id,pw,memberPhoto,memberNickName,memberColor,birth,memberRole );
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
	
	public int insertMemberAllInfo(String familyHomecode, 
			String memberName, String memberPhone, String memberEmail,
			String memberId, String memberPw, String memberPhoto,
			String memberNickName, String memberColor, Date memberBirth,
			byte memberRole) {
		
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "insert into family_member_tb values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, makeMemberCode());
			pstmt.setString(2, familyHomecode);
			pstmt.setString(3, memberName);
			pstmt.setString(4, memberPhone);
			pstmt.setString(5, memberEmail);
			pstmt.setString(6, memberId);
			pstmt.setString(7, memberPw);
			pstmt.setString(8, memberPhoto);
			pstmt.setString(9, memberNickName);
			pstmt.setString(10, memberColor);
			pstmt.setString(11, format(memberBirth));
			pstmt.setByte(12, memberRole);
					
			rowNum = pstmt.executeUpdate();
			if(rowNum != 0)
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
	
	public int updateMember(String memberCode, String familyHomecode, 
			String memberName, String memberPhone, String memberEmail,
			String memberId, String memberPw, String memberPhoto,
			String memberNickName, String memberColor, Date memberBirth,
			byte memberRole) {
		
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "update family_member_tb set family_home_code = ?, member_name=?, member_phone=?, member_email=?, member_id=?, member_pw=?, member_photo=?, member_nickname=?, member_color=?, member_birth=?, member_role=? where member_code = ?";
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, familyHomecode);
			pstmt.setString(2, memberName);
			pstmt.setString(3, memberPhone);
			pstmt.setString(4, memberEmail);
			pstmt.setString(5, memberId);
			pstmt.setString(6, memberPw);
			pstmt.setString(7, memberPhoto);
			pstmt.setString(8, memberNickName);
			pstmt.setString(9, memberColor);
			pstmt.setString(10, format(memberBirth));
			pstmt.setByte(11, memberRole);
			
			pstmt.setString(12, memberCode);
					
			rowNum = pstmt.executeUpdate();
			if(rowNum != 0)
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
	
	public int updateMember(String memberCode, 
			String memberName, String memberPhone, String memberEmail,
			String memberPw, String memberPhoto,
			String memberNickName, String memberColor, Date memberBirth)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "update family_member_tb set member_name=?, member_phone=?, member_email=?,member_pw=?, member_photo=?, member_nickname=?, member_color=?, member_birth=?where member_code = ?";
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, memberName);
			pstmt.setString(2, memberPhone);
			pstmt.setString(3, memberEmail);
			pstmt.setString(4, memberPw);
			pstmt.setString(5, memberPhoto);
			pstmt.setString(6, memberNickName);
			pstmt.setString(7, memberColor);
			pstmt.setString(8, format(memberBirth));
			pstmt.setString(9, memberCode);
					
			rowNum = pstmt.executeUpdate();
			if(rowNum != 0)
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
	
	public int updateMemberById(String familyHomecode, 
			String memberName, String memberPhone, String memberEmail,
			String memberId, String memberPw, String memberPhoto,
			String memberNickName, String memberColor, Date memberBirth,
			byte memberRole)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "update family_member_tb set family_home_code = ?, member_name=?, member_phone=?, member_email=?, member_id=?, member_pw=?, member_photo=?, member_nickname=?, member_color=?, member_birth=?, member_role=? where member_id = ?";
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, familyHomecode);
			pstmt.setString(2, memberName);
			pstmt.setString(3, memberPhone);
			pstmt.setString(4, memberEmail);
			pstmt.setString(5, memberId);
			pstmt.setString(6, memberPw);
			pstmt.setString(7, memberPhoto);
			pstmt.setString(8, memberNickName);
			pstmt.setString(9, memberColor);
			pstmt.setString(10, format(memberBirth));
			pstmt.setByte(11, memberRole);
			
			pstmt.setString(12, memberId);
					
			rowNum = pstmt.executeUpdate();
			if(rowNum != 0)
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
	
	public int updateMemberById(String memberId, 
			String memberName, String memberPhone, String memberEmail,
			String memberPw, String memberPhoto,
			String memberNickName, String memberColor, Date memberBirth,
			byte memberRole)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "update family_member_tb set member_name=?, member_phone=?, member_email=?,member_pw=?, member_photo=?, member_nickname=?, member_color=?, member_birth=?, member_role=? where member_id = ?";
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, memberName);
			pstmt.setString(2, memberPhone);
			pstmt.setString(3, memberEmail);
			pstmt.setString(4, memberPw);
			pstmt.setString(5, memberPhoto);
			pstmt.setString(6, memberNickName);
			pstmt.setString(7, memberColor);
			pstmt.setString(8, format(memberBirth));
			pstmt.setByte(9, memberRole);
			
			pstmt.setString(10, memberId);
					
			rowNum = pstmt.executeUpdate();
			if(rowNum != 0)
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
	
	public int deleteMember(String memberCode)
	{
		int rowNum=0;
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from family_member_tb where member_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberCode);
			rowNum = pstmt.executeUpdate();
			if(rowNum != 0)
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
	
	public int deleteMemberById(String memberId)
	{
		int rowNum=0;
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from family_member_tb where member_id=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);
			rowNum = pstmt.executeUpdate();
			if(rowNum != 0)
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
		
	public FamilyMemberVO getMemberInfo(String memberCode)
	{
		FamilyMemberVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select * from family_member_tb where member_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberCode);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				String familyHomecode = rs.getString("family_home_code");
				String memberName = rs.getString("member_name");
				String memberPhone = rs.getString("member_phone");
				String memberEmail = rs.getString("member_email");
				String memberId = rs.getString("member_id");
				String memberPw = rs.getString("member_pw");
				String memberPhoto = rs.getString("member_photo");
				String memberNickName = rs.getString("member_nickname");
				String memberColor = rs.getString("member_color");
				Date memberBirth = changeDate(rs.getString("member_birth"));
				String memberRole = rs.getString("member_role");
				
				
				vo = new FamilyMemberVO(memberCode,familyHomecode,memberName,memberPhone,memberEmail,memberId,memberPw,memberPhoto,memberNickName,memberColor,memberBirth,memberRole );
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
	
	public FamilyMemberVO getMemberInfoById(String memberId)
	{
		FamilyMemberVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select * from family_member_tb where member_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				String memberCode = rs.getString("member_code");
				String familyHomecode = rs.getString("family_home_code");
				String memberName = rs.getString("member_name");
				String memberPhone = rs.getString("member_phone");
				String memberEmail = rs.getString("member_email");
				
				String memberPw = rs.getString("member_pw");
				String memberPhoto = rs.getString("member_photo");
				String memberNickName = rs.getString("member_nickname");
				String memberColor = rs.getString("member_color");
				Date memberBirth = changeDate(rs.getString("member_birth"));
				String memberRole = rs.getString("member_role");
				
				vo = new FamilyMemberVO(memberCode,familyHomecode,memberName,memberPhone,memberEmail,memberId,memberPw,memberPhoto,memberNickName,memberColor,memberBirth,memberRole );
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
	
	public byte checkId(String memberId)
	{
		PreparedStatement pstmt = null;
		try{
			String sql = "select member_id from family_member_tb where member_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
		
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()==false) {return -1;}
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
		return 1;
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
		   	   
	/**
	 * �Ŵ����� ������ �����Ѵ�.
	 * @param managerMemberCode ���� �Ŵ����� ����� �ĺ��ڵ�
	 * @param memberCode �Ŵ��� ������ ���� ����� �ĺ��ڵ�
	 * @return ��������
	 */
	public byte changeRole(String managerMemberCode, String memberCode)
	{
		PreparedStatement pstmt = null;
		int check1 = -1; // �Ŵ��� ���� ������ üũ
		int check2 = -1; // �Ŵ��� ���� �ø��� üũ
		try{
			
			//�Ŵ��� ������ ����� �������������� ������
			String sql = "update family_member_tb set member_role = '0' where member_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, managerMemberCode);
			
			check1 = pstmt.executeUpdate();
			if(check1==-1) // ������ ���н� -1 ����
			{
				conn.rollback(); 
				return -1;
			}
			else
			{
				String sql2 = "update family_member_tb set member_role = '1' where member_code=?";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, memberCode);
				
				check2 = pstmt.executeUpdate();
				if(check2==-1)
				{
					conn.rollback();
					return -1;
				}
				else
				{
					conn.commit();
				}
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
		return 1;
	}
	
	public int updateCutMember(String memberCode)
	{
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "update family_member_tb set family_home_code = null, member_photo=null, member_nickname=member_name, member_color=null, member_birth=null, member_role='-1' where member_code = ?";
			pstmt = conn.prepareStatement(sql);
					
			pstmt.setString(1, memberCode);
					
			rowNum = pstmt.executeUpdate();
			if(rowNum != 0)
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
	
	public int checkInviterHomeCode(String homeCode)
	{
		FamilyMemberVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select family_home_code from family_member_tb where family_home_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, homeCode);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()==false)
			{
				return -1;
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
		return 1;
	}
	public static void main(String[] args)
	{
		FamilyMemberDAO dao = new FamilyMemberDAO();
		
//		System.out.println("�߰�"+dao.insertMemberAllInfo("h1", "����x��d", "1235454", "aa+s", "id42", "pw1", "��������̶���", "����x�x��", "#ff88ff", new Date(), (byte)1));
//		System.out.println("�߰�"+dao.insertMember("h1", "����x��s", "1235454", "aa+s", "id46", "pw1", "��������̶���", "����x�x��", "#ff88ff", new Date(), (byte)1));
//		System.out.println("�߰�"+dao.insertMember("h1", "����x��a", "1235454", "aa+s", "id47", "pw1", "��������̶���", "����x�x��", "#ff88ff", new Date(), (byte)1));
//		System.out.println("����"+dao.updateMember("m1", "h1", "���翵", "�翵����~", "�翵���̸�", "jaeyoung", "123", "�翵������~", "���翵", "#6688ff", new Date(), (byte)2));
//		System.out.println("��ȸ"+dao.selectMember("m1"));
//		System.out.println("����"+dao.updateMemberById("h3", "���翵", "�翵����~�ٲ�~", "�翵���̸�ιٲ�~", "jaeyoung", "123", "�翵�������ιٲ�~", "����", "#6688ff", new Date(), (byte)2));
//		System.out.println("�Ƶ����ȸ"+dao.selectMemberById("jaeyoung"));
//		System.out.println("����"+dao.deleteMember("300029157202637"));
//		System.out.println("�Ƶ�λ���"+dao.deleteMemberById("id44"));
		
//		System.out.println("�Ŵ��� ����" + dao.changeRole("m1", "m4"));
//		System.out.println("���̵� üũ(X) : " + dao.checkId("Fe3"));
//		System.out.println("���̵� üũ(O) : " + dao.checkId("Fe22"));
//		System.out.println("�α��� üũ(X) : " + dao.checkLogin("lee2", "123234"));
//		System.out.println("�α��� üũ(O) : " + dao.checkLogin("lee2", "1234"));
//		System.out.println("���� ���� : " + dao.updateMember("m2", "�ך���", "012-3457-8888", "test@email.com", "5678", "image/profile/photo", "�׽��x", "#fffff", new Date(), (byte)0));
//	
//		System.out.println(dao.updateCutMember("m1"));
//		System.out.println(dao.updateCutMember("m5"));
//		System.out.println(dao.checkInviterHomeCode("h1"));
//		System.out.println(dao.checkInviterHomeCode("h5"));
		
		
	}
	
	//���� �ܰ迡�� �߰�
	public int insertMember(String memberName, String memberPhone, String memberEmail,
			String memberId, String memberPw, byte memberRole) {
		
		System.out.println("FamilyMemberDAO : memberName : " + memberName);
		
		
		int rowNum = 0;
		PreparedStatement pstmt = null;
		try{
			String sql = "insert into family_member_tb values (?, ?, ?, ?, ?, ?, ?, 'img/profile/default.JPG', ?, null, null, ?)";
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(1, makeMemberCode());
			pstmt.setString(2, null);
			pstmt.setString(3, memberName);
			pstmt.setString(4, memberPhone);
			pstmt.setString(5, memberEmail);
			pstmt.setString(6, memberId);
			pstmt.setString(7, memberPw);
			pstmt.setString(8, memberName); // ���� ��ġ	
			pstmt.setByte(9, memberRole);   // �Ŵ������� �б�� 1 �Ǵ� 0 , 1 : �Ŵ���  0 : ����
					
			rowNum = pstmt.executeUpdate();
			if(rowNum != 0)
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
	

	//home manager ������ �������� �޼ҵ�
	public FamilyMemberVO homeManagerInfo(String homeCode)
	{		
		FamilyMemberVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select * from family_member_tb where FAMILY_HOME_CODE=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, homeCode);
			
			ResultSet rs = pstmt.executeQuery();
		
			while(rs.next()){
				
				String memberRole = rs.getString("member_role");
				
				if (memberRole.equals("1")) {
					
					System.out.println("�Ŵ����� ������ �Դ�.");
					
					String memberCode = rs.getString("member_code");
					String memberName = rs.getString("member_name");
					String memberPhone = rs.getString("member_phone");
					String memberEmail = rs.getString("member_email");
				
					String memberPhoto = rs.getString("member_photo");
					String memberNickName = rs.getString("member_nickname");
					String memberColor = rs.getString("member_color");
					String memberBirth = rs.getString("member_birth");
					String memberId = rs.getString("MEMBER_ID");
					String memberPw = rs.getString("MEMBER_PW");
					
					if (memberColor == null) {
						memberColor = "#fff";
					}
					Date birth = null;
					if (memberBirth != null) {
						birth = changeDate(memberBirth);
					}
					vo = new FamilyMemberVO(memberCode,homeCode,memberName,memberPhone,memberEmail,
							memberId,memberPw,memberPhoto,memberNickName,memberColor,birth,memberRole);
					
					break;
				}
				
				
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
}
