package control.story;



import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manager.story.StoryManager;
import dao.home.FamilyMemberVO;
import dao.story.StoryViewVO;

/**
 * Servlet implementation class StoryServlet
 */
@WebServlet({ "/story.do", "/story-updateLoding.do", "/story-update.do", "/story-heart.do", "/story-write.do", "/story-delete.do" })
public class StoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
		
		StoryManager manager = new StoryManager();
		StoryViewVO[] storyList = manager.getStoryList(userInfo.getFamilyHomecode());
		
		session.setAttribute("storyInfoList", storyList);
		request.setAttribute("storyList", storyList);
		RequestDispatcher rd = request.getRequestDispatcher("JSP/story/FamilyStory.jsp");
		rd.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf("/");
		String action = uri.substring(lastIndex+1);
		
		switch(action) {
			case "story-updateLoding.do" :
				requestUdateLodingStory(request, response);
				break;
			case "story-update.do" :
							
				break;
			case "story-heart.do" :
				
				break;
			case "story-write.do" :
				requestAddStory(request, response);
				break;
			case "story-delete.do" :
				requestDeleteStory(request, response);
				break;
				
		}
		
		doGet(request,response);
	}
	
	private void requestAddStory(HttpServletRequest request, HttpServletResponse response) {
		
		String serviceRoute = request.getParameter("serviceRoute");
		
		StoryManager manager = new StoryManager();
		
		HttpSession session = request.getSession();
		
		FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
		
		String storyContents = request.getParameter("family-board-content-write");
		String storyDate = newDate();
		String imageName = "img/nono";
		String emoticonCode = "em1";
		String scope = request.getParameter("public-scope");
		
		manager.addStory(userInfo.getFamilyHomecode(), userInfo.getMemberCode(),
				imageName, storyContents, emoticonCode, storyDate, scope); 
	}
	
	private void requestDeleteStory(HttpServletRequest request, HttpServletResponse response) {
		
		String serviceRoute = request.getParameter("serviceRoute");
		
		StoryManager manager = new StoryManager();
		
		HttpSession session = request.getSession();
		StoryViewVO[] storyList = (StoryViewVO[])session.getAttribute("storyInfoList");
		
		String storyIndex = request.getParameter("story-index");
		int index = Integer.parseInt(storyIndex);
		int rowNum = 0;
		String storyCode = null;
		for (StoryViewVO superBee : storyList) { 
			if (rowNum == index) {
				storyCode = superBee.getStoryCode();
				break;
			}
			rowNum++;
		}		
		System.out.println(serviceRoute);
		if (serviceRoute.equals("2000")) {
			//댓글도 삭제 해주어야한다. 
			//일단 댓글을 제거 하고 시작 한다. 
			manager.deleteStory(storyCode);
			session.removeAttribute("storyInfoList");
		} else {
			// 안드로이드 가자
		}
		
		
		
		/*
		 * 
		 * FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
		 * String homeCode = userInfo.getFamilyHomecode();
		String storyIndex = request.getParameter("story-index");
		String storyCode = request.getParameter("story-code");
		System.out.println("이야기 번호 : " + storyIndex);
		System.out.println("storyCode : " + storyCode);
		String homeCode = userInfo.getFamilyHomecode();
		
		System.out.println("homeCode : " + homeCode);
		if (serviceRoute.equals("2000")) {
			//댓글도 삭제 해주어야한다. 
			//일단 댓글을 제거 하고 시작 한다. 
			int index = Integer.parseInt(storyIndex);
			boolean og = manager.deleteStory(homeCode, index);
			System.out.println("결과 : " + og);
		} else {
			// 안드로이드 가자
		}
		*/
	}
	
	private void requestUdateLodingStory(HttpServletRequest request, HttpServletResponse response) {
		
		String serviceRoute = request.getParameter("serviceRoute");
		
		StoryManager manager = new StoryManager();
		
		HttpSession session = request.getSession();
		FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
		
		String storyIndex = request.getParameter("story-index");
		
		System.out.println("이야기 번호 : " + storyIndex);
		
		String homeCode = userInfo.getFamilyHomecode();
		
		System.out.println("homeCode : " + homeCode);
		if (serviceRoute.equals("2000")) {
			//댓글도 삭제 해주어야한다. 
			//일단 댓글을 제거 하고 시작 한다. 
			int index = Integer.parseInt(storyIndex);
			boolean og = manager.deleteStory(homeCode, index);
			System.out.println("결과 : " + og);
		} else {
			// 안드로이드 가자
		}
	}
	
	public String newDate() {
		
		Date dateTime = new Date();
		
		int year= dateTime.getYear() - 100;
		String mon = (dateTime.getMonth()+1)>9 ? "" + (dateTime.getMonth()+1) : "0" + (dateTime.getMonth()+1);
		String day = dateTime.getDate()>9 ? "" +dateTime.getDate() : "0" + dateTime.getDate();
	    String hour = dateTime.getHours()>9 ? "" + dateTime.getHours() : "0" + dateTime.getHours(); 
	    String min = dateTime.getMinutes()>9 ? "" + dateTime.getMinutes() : "0" + dateTime.getMinutes();
	   
		return  year + "-" + mon +"-" + day + " " + hour + ":" + min; 
	}
}
