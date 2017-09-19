package control.story;

import java.io.IOException;

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
 * Servlet implementation class NeighborStoryServlet
 */
@WebServlet({ "/neighborStory.do", "/neightborStoryHeart.do" })
public class NeighborStoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
response.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
		
		StoryManager manager = new StoryManager();
		StoryViewVO[] neighborList = manager.getNeighborStoryList(userInfo.getFamilyHomecode());
		
		for (StoryViewVO vo : neighborList) {
			System.out.println(vo.getContents());
			System.out.println(vo.getMemberNickname());
			System.out.println(vo.getFamilyHomeName());
		}
		
		request.setAttribute("neighborList", neighborList);
		RequestDispatcher rd = request.getRequestDispatcher("JSP/story/NeighborStory.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		
	}

}
