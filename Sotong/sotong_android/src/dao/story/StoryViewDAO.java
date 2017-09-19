package dao.story;

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

public class StoryViewDAO implements Serializable{

	private static final long serialVersionUID = 4399317021029198850L;
	private DBConnectionModule connModule;
	private Connection conn;
	
	public StoryViewDAO() {
		connModule = DBConnectionModule.getInstance();
		conn = connModule.getConn();
	}
	public StoryViewDAO(DBConnectionModule connModule, Connection conn) {
		super();
		this.connModule = connModule;
		this.conn = conn;
	}
	
	public String makeMenuCode() {
		Date d = new Date();
		StringTokenizer st = new StringTokenizer(new SimpleDateFormat().format(d),". ");
			
		String year = st.nextToken();
		String month = st.nextToken();
		String day = st.nextToken();
			
		String clientId = "2000"+day+year+month+d.getHours()+d.getMinutes()+d.getSeconds();
		return clientId;
	}
	
	public StoryViewVO selectCode(String code){
		StoryViewVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select * from story_view where story_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				String familyHomeCode = rs.getString("family_home_code");
				String familyHomeName = rs.getString("family_home_name");
				String memberNickname = rs.getString("member_nickname");
				String contents = rs.getString("contents");
				String imageName = rs.getString("image_name");
				String imageWritenDate = rs.getString("image_writen_date");
				String emoticonName = rs.getString("emoticon_name");
				String emoticonRoute = rs.getString("emoticon_route");
				String storyDate = rs.getString("story_date");
				int stroyHeart = rs.getInt("stroy_heart");
				String storyModifyDate = rs.getString("story_modify_date");
				String storyScope = rs.getString("story_scope");
				
				Date imgD = changeDate(imageWritenDate);
				Date storyD = changeDate(storyDate);
				Date storyModifyD = changeDate(storyModifyDate);
				Boolean scope = false;
				if (storyScope.equals("ÀÌ¿ô°ø°³")) {
					scope = true;
				}
				
				vo = new StoryViewVO(code,familyHomeCode,familyHomeName,memberNickname,contents,
						imageName,imgD,emoticonName,emoticonRoute,storyD,stroyHeart,storyModifyD,scope);
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
	
	public Date changeDate(String dateTime) {
	      int year = Integer.parseInt(dateTime.substring(0,2));
	      int mon = Integer.parseInt(dateTime.substring(3,5));
	      int date = Integer.parseInt(dateTime.substring(6,8));
	      
	      Date reDate = new Date(year,mon,date);
	      return reDate; 
	}
	
	public StoryViewVO[] getStoryList(String homeCode) {
		
		ArrayList<StoryViewVO> list = new ArrayList<StoryViewVO>();
		
		
		StoryViewVO vo = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "select * from story_view where FAMILY_HOME_CODE=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, homeCode);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				String storyCode = rs.getString("story_code");
				String familyHomeName = rs.getString("family_home_name");
				String memberNickname = rs.getString("member_nickname");
				String contents = rs.getString("contents");
				String imageName = rs.getString("image_name");
				String imageWritenDate = rs.getString("IMAGE_WRITTEN_DATE");
				String emoticonName = rs.getString("emoticon_name");
				String emoticonRoute = rs.getString("emoticon_route");
				String storyDate = rs.getString("story_date");
				int stroyHeart = rs.getInt("STORY_HEART");
				String storyModifyDate = rs.getString("story_modify_date");
				String storyScope = rs.getString("story_scope");
				
				Date imgD = changeDate(imageWritenDate);
				Date storyD = changeDate(storyDate);
				Date storyModifyD = changeDate(storyModifyDate);
				Boolean scope = false;
				if (storyScope.equals("ÀÌ¿ô°ø°³")) {
					scope = true;
				}
				
				vo = new StoryViewVO(storyCode,homeCode,familyHomeName,memberNickname,contents,
						imageName,imgD,emoticonName,emoticonRoute,storyD,stroyHeart,storyModifyD,scope);
				list.add(vo);
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
		
		return list.toArray(new StoryViewVO[list.size()]);
	}
	
	
	public static void main(String[] args) {
		StoryViewDAO dao = new StoryViewDAO();
		//System.out.println(dao.selectCode("cheal"));
		
		for (StoryViewVO vo : dao.getStoryList("h1")) {
			System.out.println(vo);
		}
	}
}
