package control.home;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manager.home.HomeManager;
import dao.home.FamilyHomeBean;
import dao.home.FamilyHomeVO;
import dao.home.FamilyMemberVO;
import dao.home.HomeInfoViewVO;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet({"/home.do","/renameHome.do", "/deleteMember.do","/detailMember.do", "/modifyMember.do","/modifyMemberOk.do", "/neighborHome.do", "/neighborProfile.do"})
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
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

	public String format(Date d){ // Date를 String으로 변경함/ 데이터 넣을 때 사용
	    SimpleDateFormat fmt = new SimpleDateFormat("yy-MM-dd");
	    String date = fmt.format(d);
	    return date;
	}
	
	public Date changeDate(String dateTime) { // String으로 가져온 data를 Date로 변경
	      int year = Integer.parseInt(dateTime.substring(0,2));
	      int mon = Integer.parseInt(dateTime.substring(3,5));
	      int date = Integer.parseInt(dateTime.substring(6,8));
	   
	      Date reDate = new Date(year,mon,date);
	      return reDate; 
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{		
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf("/");
		String action = uri.substring(lastIndex+1);
		
		
		if(action.equals("home.do"))
		{
			requestHome(request,response);			
		}
		else if(action.equals("detailMember.do"))
		{
			requestMemberInfo(request,response);
		}
		else if(action.equals("renameHome.do"))
		{
			requestRename(request,response);
		}
		else if(action.equals("modifyMember.do"))
		{
			requestUpdateMemberProfile(request,response);
		}
		else if(action.equals("modifyMemberOk.do"))
		{
			requestUpdateMemberProfileOk(request,response);
		}
		else if(action.equals("neighborHome.do"))
		{
			requestNeighborHome(request,response);
		}
		else if(action.equals("deleteMember.do"))
		{
			requestDeleteMember(request,response);
		}
		else if(action.equals("neighborProfile.do"))
		{
			requestNeighborProfile(request,response);
		}
	}
	
	private void requestNeighborProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HomeManager manager = new HomeManager();
		
		//선택한 멤버구성원의 코드를 받아옴
		String memberCode = request.getParameter("memberCode"); 
		System.out.println(memberCode);
		
		FamilyMemberVO memberInfo = manager.getMemberDetailInfo(memberCode);
		
		request.setAttribute("homeName", manager.getHomeInfo(memberInfo.getFamilyHomecode()).getFamilyHomeName());
		request.setAttribute("birth",format(memberInfo.getMemberBirth()));
		request.setAttribute("memberInfo", memberInfo);
		
		RequestDispatcher rd = request.getRequestDispatcher("JSP/neighbor/neighborProfile.jsp");
		rd.forward(request, response);		
		
	}
	
	private void requestDeleteMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HomeManager manager = new HomeManager();
		
		String memberCode = request.getParameter("memberInfo");
		System.out.println(memberCode);
		
		
		
	}
	
	private void requestNeighborHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HomeManager manager = new HomeManager();
		
		// 선택한 홈 정보에 해당하는 홈 코드를 받는다.
		String homeCode = request.getParameter("homeCode");
		System.out.println(homeCode);
		
		PrintWriter out = response.getWriter();
		
		if(homeCode==null)
		{
			// 홈코드가 없다면 메인으로 이동
			response.sendRedirect("main.html");
		}
		
		//멤버목록을 저장할 공간
		String[][] familyMemberList = null;
		
		// 홈 이름 가져오기
		String homeName = manager.getHomeInfo(homeCode).getFamilyHomeName();
		
		//매니저 정보 가져오기
		FamilyMemberVO homeManager = manager.homeManagerInfo(homeCode);
		
		//멤버 목록 가져오기
		familyMemberList = manager.getMemberList(homeCode, homeManager.getMemberCode());
		
		//홈매니저 생일정보
		String birth = format(homeManager.getMemberBirth());
		
		
		// 접속 정보
			HttpSession session = request.getSession();
			String serviceRouteWeb = (String)session.getAttribute("serviceRoute");
			String serviceRouteApp = null;
			if(serviceRouteWeb == null) // web이 null
			{
				serviceRouteApp = request.getParameter("serviceRoute"); //app에서 던지는 접속정보 받음
				if(serviceRouteApp == null) // 앱역시 null일 경우
				{
					System.out.println("접속 에러 - 이웃 홈");
				}
				else // 앱 접속
				{
					//앱 처리
					for (String[] simpleFamilyMemberInfo : familyMemberList) {
						out.println("300|" + simpleFamilyMemberInfo[0] + "|" + simpleFamilyMemberInfo[1] +
								"|" + simpleFamilyMemberInfo[2] + "|" + simpleFamilyMemberInfo[3] +
								"|" + simpleFamilyMemberInfo[4]);
					}
				}
			}
			else // 웹 접속
			{
				FamilyHomeBean bean = new FamilyHomeBean(homeName, homeManager.getMemberNickName(), birth, homeManager.getMemberPhoto(), familyMemberList); 
				
				request.setAttribute("managerBirth", birth);
				request.setAttribute("homeManager", homeManager);
				request.setAttribute("familyHome", bean);
				
				RequestDispatcher rd = request.getRequestDispatcher("JSP/neighbor/neighborHome.jsp");
				rd.forward(request, response);
				
			}
		
		
		
	}
	private void requestUpdateMemberProfileOk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HomeManager manager = new HomeManager();
		
		HttpSession session = request.getSession();
//		
//		//사용자 정보
//		FamilyMemberVO userInfo = (FamilyMemberVO) session.getAttribute("userInfo");
//		System.out.println("사용자 정보 : " + userInfo.getMemberCode());
//		
		// 접속 정보
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute");
		String serviceRouteApp = null;
		if(serviceRouteWeb == null)
		{
			serviceRouteApp = request.getParameter("serviceRoute");
		}
		
//		if(userInfo != null) //사용자 정보가 있을 경우
//		{
//		
			String code = request.getParameter("code");
			System.out.println("code값 : " + code);
			String name = request.getParameter("name");
			System.out.println(name);
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String pw = request.getParameter("pw");
			System.out.println(pw);
			String nickName = request.getParameter("nickName");
			System.out.println(nickName);
			String color = request.getParameter("color");
			Date birth = changeDate(request.getParameter("birth"));
			String photo = request.getParameter("photo");
//			byte role = Byte.parseByte(request.getParameter("role"));
//			System.out.println(role);
			
			int num = manager.updateMemberProfile(code, name, phone, email, pw, photo, nickName, color, birth);
			if(num==-1)
			{
				//변경실패
				System.out.println("변경에 실패하였습니다.");
				return;
			}
			
			if(serviceRouteApp == null) // Web접속
			{	
	//			RequestDispatcher rd = request.getRequestDispatcher("JSP/home/modifyFamilyProfile.jsp");
	//			rd.forward(request, response);
				System.out.println("웹 접속 성공");
				response.sendRedirect("home.do");
			}
			else
			{
				System.out.println("앱 접속 성공");
				response.getWriter().println("200|success");
			}
		}
//		else
//		{
//			if(serviceRouteApp==null) // web 접속
//			{
//				System.out.println("사용자 정보 없음");
//			}
//			else
//			{
//				System.out.println("앱 접속 실패");
//				response.getWriter().println("500|fail");
//			}
//		}
		
	private void requestUpdateMemberProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HomeManager manager = new HomeManager();
		
		HttpSession session = request.getSession();
		
		//사용자 정보
		FamilyMemberVO userInfo = (FamilyMemberVO) session.getAttribute("userInfo");
		System.out.println("사용자 정보 : " + userInfo.getMemberCode());
		
		//홈 정보
		String homeName = manager.getHomeInfo(userInfo.getFamilyHomecode()).getFamilyHomeName();
		request.setAttribute("homeName", homeName);
		
		// 접속 정보
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute");
		String serviceRouteApp = null;
		
		if(serviceRouteWeb == null)
		{
			serviceRouteApp = request.getParameter("serviceRoute");
		}
		
		if(serviceRouteApp == null) // Web접속
		{	
			String birth = format(userInfo.getMemberBirth());
			request.setAttribute("birth", birth);
			RequestDispatcher rd = request.getRequestDispatcher("JSP/home/modifyFamilyProfile.jsp");
			rd.forward(request, response);
		}
		else // App접속
		{
			System.out.println("App 접속 - 정보 수정");
			String birth = format(userInfo.getMemberBirth());
			PrintWriter out = response.getWriter();
			out.println("200|" + userInfo.getMemberCode() + "|" + userInfo.getFamilyHomecode() + "|" +
					userInfo.getMemberName() + "|" + userInfo.getMemberPhone() + "|" +  userInfo.getMemberEmail() + "|" +
					userInfo.getMemberId() + "|" + userInfo.getMemberPw() + "|" + userInfo.getMemberPhoto() + "|" +
					userInfo.getMemberNickName() + "|" + userInfo.getMemberColor() + "|" +birth + "|" + userInfo.getMemberRole());
		}
//		else
//		{
//			System.out.println("접속 에러");
//		}
		
	}
	
	private void requestRename(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HomeManager manager = new HomeManager();
		
		HttpSession session = request.getSession();
		
		//사용자 정보
		FamilyMemberVO userInfo = (FamilyMemberVO) session.getAttribute("userInfo");
		System.out.println("사용자 정보 : " + userInfo.getMemberCode());
		
		//홈 이름 변경 위해서 jsp에 name 추가
		String homeName = request.getParameter("homeName");
		System.out.println("홈이름 : " + homeName);
		
		//사용자의 홈 코드
		String homeCode = userInfo.getFamilyHomecode();
		
		int num=manager.modifyHomeName(homeCode, homeName);
		
		if(num==1)
		{
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(homeName);
		}
		else
		{
			return;
		}
	}
	
	private void requestHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		HomeManager manager= new HomeManager();		
		HttpSession session = request.getSession();		
		
		// 사용자 정보
		FamilyMemberVO userInfo = (FamilyMemberVO) session.getAttribute("userInfo");
		
		// 접속 정보
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute");
		String serviceRouteApp = null;
		if(serviceRouteWeb == null)
		{
			serviceRouteApp = request.getParameter("serviceRoute");
		}
		PrintWriter out = response.getWriter();
		
		RequestDispatcher rd = null;
		if (userInfo != null) { // 사용자 정보가 null이 아니고
			String[][] familyMemberList = null;
			if (serviceRouteApp==null) { // 웹에서 접속
				
				if(userInfo.getFamilyHomecode() == null)
				{
					// 홈 코드가 없다면
					rd = request.getRequestDispatcher(""); // 홈 생성 화면으로 이동
					rd.forward(request, response);
				}
				else // 홈코드가 있다면
				{	
					FamilyMemberVO managerVO = manager.homeManagerInfo(userInfo.getFamilyHomecode());
					
					familyMemberList = manager.getMemberList(userInfo.getFamilyHomecode(), managerVO.getMemberCode());
					String birth = format(userInfo.getMemberBirth());
					
					String homeName = manager.getHomeInfo(userInfo.getFamilyHomecode()).getFamilyHomeName();
					System.out.println(homeName);
					
					FamilyHomeBean bean = new FamilyHomeBean(homeName, userInfo.getMemberNickName(), birth, userInfo.getMemberPhoto(), familyMemberList); 
					request.setAttribute("familyHome", bean);
					rd = request.getRequestDispatcher("JSP/home/myhome.jsp");
					rd.forward(request, response);
				}
			} else {
				
				// 안드로이드 코드 들어갈 곳~!
				System.out.println("APP 접속");
				
				//가족 구성원의 정보를 받아온다.
				familyMemberList = manager.getMemberList(userInfo.getFamilyHomecode(), userInfo.getMemberCode());
				
				String birth = format(userInfo.getMemberBirth());
				
				//로그인 한 회원의 정보를 전송한다.
			
				out.println("200|" + userInfo.getMemberCode() + "|" + userInfo.getFamilyHomecode() + "|" +
						userInfo.getMemberName() + "|" + userInfo.getMemberPhone() + "|" +  userInfo.getMemberEmail() + "|" +
						userInfo.getMemberId() + "|" + userInfo.getMemberPw() + "|" + userInfo.getMemberPhoto() + "|" +
						userInfo.getMemberNickName() + "|" + userInfo.getMemberColor() + "|" +
					birth + "|" + userInfo.getMemberRole());
					
				//가족 구성원의 정보를 전송한다.
				for (String[] simpleFamilyMemberInfo : familyMemberList) {
					out.println("300|" + simpleFamilyMemberInfo[0] + "|" + simpleFamilyMemberInfo[1] +
							"|" + simpleFamilyMemberInfo[2] + "|" + simpleFamilyMemberInfo[3] +
							"|" + simpleFamilyMemberInfo[4]);
				}
			}
		} else {
			if (serviceRouteWeb!=null) {
				// web 으로 접속할 때 들어오는 곳 
				System.out.println("Web 접속");
				rd = request.getRequestDispatcher("main.html");
				rd.forward(request, response);
			} else {
				// 안드로이드 코드 들어갈 곳~!
				System.out.println("홈 - APP 접속");
				out.println("500");
			}
		}
	}
	
	private void requestMemberInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HomeManager manager = new HomeManager();
		
		HttpSession session = request.getSession();
		
		
				
		// 접속 정보
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute");
		String serviceRouteApp = null;
		if(serviceRouteWeb == null)
		{
			serviceRouteApp = request.getParameter("serviceRoute");
		}
		
		PrintWriter out = response.getWriter();
		RequestDispatcher rd = null;
					
		if(serviceRouteApp==null) // 웹이면
		{
			System.out.println("웹접속");
			// 사용자 정보
			FamilyMemberVO userInfo = (FamilyMemberVO) session.getAttribute("userInfo");
			
			if(userInfo!=null)
			{
				//선택된 멤버코드 받아오기
				String memberCode = request.getParameter("memberInfo");
				System.out.println("선택된 멤버코드 전송" + memberCode);
				
				FamilyHomeVO familyHome = manager.getHomeInfo(userInfo.getFamilyHomecode());
				
				FamilyMemberVO memberDetail = manager.getMemberDetailInfo(memberCode);
				
				String  birth = format(memberDetail.getMemberBirth());
				
				
				//멤버 상세정보와 홈정보 전달				
				
				request.setAttribute("memberDetail", memberDetail); 
				request.setAttribute("familyHome", familyHome);
				request.setAttribute("memberBirth", birth);
				
				rd = request.getRequestDispatcher("JSP/home/familyProfile.jsp");
				rd.forward(request, response);
			}
			else
			{
				 response.sendRedirect("/main.html");
				//System.out.println("사용자 정보가 없습니다.");
			}
		}
		else
		{
			System.out.println("상세프로필 - APP 접속 - 성공");
			
			String memberCode = request.getParameter("memberInfo");
			System.out.println("선택된 멤버코드 전송" + memberCode);
			
			FamilyMemberVO memberDetail = manager.getMemberDetailInfo(memberCode);
			String  birth = format(memberDetail.getMemberBirth());
			
			out.println("200|" + memberDetail.getMemberCode() + "|" +
					memberDetail.getMemberName() + "|" + memberDetail.getMemberPhone() + "|" +  memberDetail.getMemberEmail() + "|" +
					memberDetail.getMemberPhoto() +"|"+ memberDetail.getMemberNickName() + "|" + memberDetail.getMemberColor() + "|" +
				birth + "|" + memberDetail.getMemberRole());				
		}
	}

	

}
