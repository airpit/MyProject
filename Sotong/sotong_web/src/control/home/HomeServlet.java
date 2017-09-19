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
		
		//������ ����������� �ڵ带 �޾ƿ�
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
		
		// ������ Ȩ ������ �ش��ϴ� Ȩ �ڵ带 �޴´�.
		String homeCode = request.getParameter("homeCode");
		System.out.println(homeCode);
		
		PrintWriter out = response.getWriter();
		
		if(homeCode==null)
		{
			// Ȩ�ڵ尡 ���ٸ� �������� �̵�
			response.sendRedirect("main.html");
		}
		
		//�������� ������ ����
		String[][] familyMemberList = null;
		
		// Ȩ �̸� ��������
		String homeName = manager.getHomeInfo(homeCode).getFamilyHomeName();
		
		//�Ŵ��� ���� ��������
		FamilyMemberVO homeManager = manager.homeManagerInfo(homeCode);
		
		//��� ��� ��������
		familyMemberList = manager.getMemberList(homeCode, homeManager.getMemberCode());
		
		//Ȩ�Ŵ��� ��������
		String birth = format(homeManager.getMemberBirth());
		
		
		// ���� ����
			HttpSession session = request.getSession();
			String serviceRouteWeb = (String)session.getAttribute("serviceRoute");
			String serviceRouteApp = null;
			if(serviceRouteWeb == null) // web�� null
			{
				serviceRouteApp = request.getParameter("serviceRoute"); //app���� ������ �������� ����
				if(serviceRouteApp == null) // �ۿ��� null�� ���
				{
					System.out.println("���� ���� - �̿� Ȩ");
				}
				else // �� ����
				{
					//�� ó��
					for (String[] simpleFamilyMemberInfo : familyMemberList) {
						out.println("300|" + simpleFamilyMemberInfo[0] + "|" + simpleFamilyMemberInfo[1] +
								"|" + simpleFamilyMemberInfo[2] + "|" + simpleFamilyMemberInfo[3] +
								"|" + simpleFamilyMemberInfo[4]);
					}
				}
			}
			else // �� ����
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
//		//����� ����
//		FamilyMemberVO userInfo = (FamilyMemberVO) session.getAttribute("userInfo");
//		System.out.println("����� ���� : " + userInfo.getMemberCode());
//		
		// ���� ����
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute");
		String serviceRouteApp = null;
		if(serviceRouteWeb == null)
		{
			serviceRouteApp = request.getParameter("serviceRoute");
		}
		
//		if(userInfo != null) //����� ������ ���� ���
//		{
//		
			String code = request.getParameter("code");
			System.out.println("code�� : " + code);
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
				//�������
				System.out.println("���濡 �����Ͽ����ϴ�.");
				return;
			}
			
			if(serviceRouteApp == null) // Web����
			{	
	//			RequestDispatcher rd = request.getRequestDispatcher("JSP/home/modifyFamilyProfile.jsp");
	//			rd.forward(request, response);
				System.out.println("�� ���� ����");
				response.sendRedirect("home.do");
			}
			else
			{
				System.out.println("�� ���� ����");
				response.getWriter().println("200|success");
			}
		}
//		else
//		{
//			if(serviceRouteApp==null) // web ����
//			{
//				System.out.println("����� ���� ����");
//			}
//			else
//			{
//				System.out.println("�� ���� ����");
//				response.getWriter().println("500|fail");
//			}
//		}
		
	private void requestUpdateMemberProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HomeManager manager = new HomeManager();
		
		HttpSession session = request.getSession();
		
		//����� ����
		FamilyMemberVO userInfo = (FamilyMemberVO) session.getAttribute("userInfo");
		System.out.println("����� ���� : " + userInfo.getMemberCode());
		
		//Ȩ ����
		String homeName = manager.getHomeInfo(userInfo.getFamilyHomecode()).getFamilyHomeName();
		request.setAttribute("homeName", homeName);
		
		// ���� ����
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute");
		String serviceRouteApp = null;
		
		if(serviceRouteWeb == null)
		{
			serviceRouteApp = request.getParameter("serviceRoute");
		}
		
		if(serviceRouteApp == null) // Web����
		{	
			String birth = format(userInfo.getMemberBirth());
			request.setAttribute("birth", birth);
			RequestDispatcher rd = request.getRequestDispatcher("JSP/home/modifyFamilyProfile.jsp");
			rd.forward(request, response);
		}
		else // App����
		{
			System.out.println("App ���� - ���� ����");
			String birth = format(userInfo.getMemberBirth());
			PrintWriter out = response.getWriter();
			out.println("200|" + userInfo.getMemberCode() + "|" + userInfo.getFamilyHomecode() + "|" +
					userInfo.getMemberName() + "|" + userInfo.getMemberPhone() + "|" +  userInfo.getMemberEmail() + "|" +
					userInfo.getMemberId() + "|" + userInfo.getMemberPw() + "|" + userInfo.getMemberPhoto() + "|" +
					userInfo.getMemberNickName() + "|" + userInfo.getMemberColor() + "|" +birth + "|" + userInfo.getMemberRole());
		}
//		else
//		{
//			System.out.println("���� ����");
//		}
		
	}
	
	private void requestRename(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HomeManager manager = new HomeManager();
		
		HttpSession session = request.getSession();
		
		//����� ����
		FamilyMemberVO userInfo = (FamilyMemberVO) session.getAttribute("userInfo");
		System.out.println("����� ���� : " + userInfo.getMemberCode());
		
		//Ȩ �̸� ���� ���ؼ� jsp�� name �߰�
		String homeName = request.getParameter("homeName");
		System.out.println("Ȩ�̸� : " + homeName);
		
		//������� Ȩ �ڵ�
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
		
		// ����� ����
		FamilyMemberVO userInfo = (FamilyMemberVO) session.getAttribute("userInfo");
		
		// ���� ����
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute");
		String serviceRouteApp = null;
		if(serviceRouteWeb == null)
		{
			serviceRouteApp = request.getParameter("serviceRoute");
		}
		PrintWriter out = response.getWriter();
		
		RequestDispatcher rd = null;
		if (userInfo != null) { // ����� ������ null�� �ƴϰ�
			String[][] familyMemberList = null;
			if (serviceRouteApp==null) { // ������ ����
				
				if(userInfo.getFamilyHomecode() == null)
				{
					// Ȩ �ڵ尡 ���ٸ�
					rd = request.getRequestDispatcher(""); // Ȩ ���� ȭ������ �̵�
					rd.forward(request, response);
				}
				else // Ȩ�ڵ尡 �ִٸ�
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
				
				// �ȵ���̵� �ڵ� �� ��~!
				System.out.println("APP ����");
				
				//���� �������� ������ �޾ƿ´�.
				familyMemberList = manager.getMemberList(userInfo.getFamilyHomecode(), userInfo.getMemberCode());
				
				String birth = format(userInfo.getMemberBirth());
				
				//�α��� �� ȸ���� ������ �����Ѵ�.
			
				out.println("200|" + userInfo.getMemberCode() + "|" + userInfo.getFamilyHomecode() + "|" +
						userInfo.getMemberName() + "|" + userInfo.getMemberPhone() + "|" +  userInfo.getMemberEmail() + "|" +
						userInfo.getMemberId() + "|" + userInfo.getMemberPw() + "|" + userInfo.getMemberPhoto() + "|" +
						userInfo.getMemberNickName() + "|" + userInfo.getMemberColor() + "|" +
					birth + "|" + userInfo.getMemberRole());
					
				//���� �������� ������ �����Ѵ�.
				for (String[] simpleFamilyMemberInfo : familyMemberList) {
					out.println("300|" + simpleFamilyMemberInfo[0] + "|" + simpleFamilyMemberInfo[1] +
							"|" + simpleFamilyMemberInfo[2] + "|" + simpleFamilyMemberInfo[3] +
							"|" + simpleFamilyMemberInfo[4]);
				}
			}
		} else {
			if (serviceRouteWeb!=null) {
				// web ���� ������ �� ������ �� 
				System.out.println("Web ����");
				rd = request.getRequestDispatcher("main.html");
				rd.forward(request, response);
			} else {
				// �ȵ���̵� �ڵ� �� ��~!
				System.out.println("Ȩ - APP ����");
				out.println("500");
			}
		}
	}
	
	private void requestMemberInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HomeManager manager = new HomeManager();
		
		HttpSession session = request.getSession();
		
		
				
		// ���� ����
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute");
		String serviceRouteApp = null;
		if(serviceRouteWeb == null)
		{
			serviceRouteApp = request.getParameter("serviceRoute");
		}
		
		PrintWriter out = response.getWriter();
		RequestDispatcher rd = null;
					
		if(serviceRouteApp==null) // ���̸�
		{
			System.out.println("������");
			// ����� ����
			FamilyMemberVO userInfo = (FamilyMemberVO) session.getAttribute("userInfo");
			
			if(userInfo!=null)
			{
				//���õ� ����ڵ� �޾ƿ���
				String memberCode = request.getParameter("memberInfo");
				System.out.println("���õ� ����ڵ� ����" + memberCode);
				
				FamilyHomeVO familyHome = manager.getHomeInfo(userInfo.getFamilyHomecode());
				
				FamilyMemberVO memberDetail = manager.getMemberDetailInfo(memberCode);
				
				String  birth = format(memberDetail.getMemberBirth());
				
				
				//��� �������� Ȩ���� ����				
				
				request.setAttribute("memberDetail", memberDetail); 
				request.setAttribute("familyHome", familyHome);
				request.setAttribute("memberBirth", birth);
				
				rd = request.getRequestDispatcher("JSP/home/familyProfile.jsp");
				rd.forward(request, response);
			}
			else
			{
				 response.sendRedirect("/main.html");
				//System.out.println("����� ������ �����ϴ�.");
			}
		}
		else
		{
			System.out.println("�������� - APP ���� - ����");
			
			String memberCode = request.getParameter("memberInfo");
			System.out.println("���õ� ����ڵ� ����" + memberCode);
			
			FamilyMemberVO memberDetail = manager.getMemberDetailInfo(memberCode);
			String  birth = format(memberDetail.getMemberBirth());
			
			out.println("200|" + memberDetail.getMemberCode() + "|" +
					memberDetail.getMemberName() + "|" + memberDetail.getMemberPhone() + "|" +  memberDetail.getMemberEmail() + "|" +
					memberDetail.getMemberPhoto() +"|"+ memberDetail.getMemberNickName() + "|" + memberDetail.getMemberColor() + "|" +
				birth + "|" + memberDetail.getMemberRole());				
		}
	}

	

}
