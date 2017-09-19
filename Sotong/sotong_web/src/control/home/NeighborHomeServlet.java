package control.home;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manager.home.NeighborHomeManager;
import model.bean.NeighborListBean;
import dao.home.FamilyMemberVO;
import dao.neighbor.ConnectedNeighborViewVO;

/**
 * Servlet implementation class NeighborHomeServlet
 */
@WebServlet({"/neighbor.do"})
public class NeighborHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NeighborHomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf("/");
		String action = uri.substring(lastIndex+1);
		
		
		if(action.equals("neighbor.do"))
		{
			requestConnectedNeighborList(request,response);			
		}
		
	}
	
	
	private void requestConnectedNeighborList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		NeighborHomeManager manager = new NeighborHomeManager();
		
		HttpSession session = request.getSession();
		
		PrintWriter out = response.getWriter();
		
		//접속
		
		//웹은 세션에서 가져옴
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute");
		
		//앱은 serviceRoute에서 끊어옴
		String serviceRouteApp = null;
		
		//홈 코드
		String homeCode = null;
		if(serviceRouteWeb == null) // 웹 값이 null이면 
		{
			serviceRouteApp = request.getParameter("serviceRoute"); // 앱 서비스값을 받아옴
			if(serviceRouteApp == null) // 앱 값도 null 이면
			{
				System.out.println("접속 에러");
				out.println("500|");
				response.sendRedirect("main.html");
			}
			else
			{
				System.out.println("앱 접속 - 이웃 목록 보기");
				//앱 처리
				
				homeCode = request.getParameter("homeCode");
				
				//first : first_home 에 존재하는 이웃목록 (second가 파라미터 값 홈임)
				ConnectedNeighborViewVO[] firstNeighborList = manager.getFirstConnectedNeighborList(homeCode);
				
				//second : second_home 에 존재하는 이웃목록 (first가 파라미터 값 홈)
				ConnectedNeighborViewVO[] secondNeighborList = manager.getSecondConnectedNeighborList(homeCode);
				
				
				//연결 목록
				ArrayList<String[]> neighborList = new ArrayList<String[]>();
				
				if(firstNeighborList!=null)
				{
					for(ConnectedNeighborViewVO vo : firstNeighborList)
					{
						//연결 목록에 추가
						//NeighborListBean bean = new NeighborListBean(vo.getFirstHomeCode(), vo.getFirstHomeName(), vo.getFirstHomeManagerName());
						neighborList.add(new String[]{vo.getFirstHomeCode(), vo.getFirstHomeName(), vo.getFirstHomeManagerName()});
					}
				}
			
				if(secondNeighborList!=null)
				{
					for(ConnectedNeighborViewVO vo : secondNeighborList)
					{
						//연결 목록에 추가
						//NeighborListBean bean = new NeighborListBean(vo.getSecondHomeCode(), vo.getSecondHomeName(), vo.getSecondHomeManagerName());
						neighborList.add(new String[]{vo.getFirstHomeCode(), vo.getFirstHomeName(), vo.getFirstHomeManagerName()});
					}
				}
				
				if(neighborList.size() > 1)
				{
//					NeighborListBean[] neighbor = neighborList.toArray(new NeighborListBean[neighborList.size()]);
//					System.out.println(neighbor[0].getHomeName());
			
					for(String[] neighbor : neighborList)
					{
						System.out.println(neighbor[0]);
						out.println("300|" + neighbor[0] + "|" + neighbor[1] + "|" + neighbor[2]);
					}
				}
			}
		}
		else // 웹 값이 null이 아니면 - 웹 접속
		{
			System.out.println("웹 접속 - 이웃 목록 보기");
			//웹 처리
			
			FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
			
			System.out.println("홈 코드 : " + userInfo.getFamilyHomecode());
			
			homeCode = userInfo.getFamilyHomecode();
	
			NeighborListBean[] neighborList = manager.getAllNeighborList(homeCode);
			
				
				//이웃 목록 세팅
				request.setAttribute("neighborList", neighborList);
			
				RequestDispatcher rd = request.getRequestDispatcher("JSP/neighbor/neighbor.jsp");
				rd.forward(request, response);	
			
			
		}
	}
	
}


