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

import manager.home.LoginManager;
import dao.home.FamilyHomeBean;
import dao.home.FamilyHomeVO;
import dao.home.FamilyMemberVO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		out.println("LoginServelt : get-method");
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		//App�� ���� PrintWriter�� �����Ѵ�.
		PrintWriter out = response.getWriter();
		
		String userId = request.getParameter("user-id");
		String userPw = request.getParameter("user-pw");
		
		//Web�� App�� �������ش�.
		String serviceRoute = request.getParameter("serviceRoute");
		
		
		//�α��� �Ŵ����� �����Ѵ�.
		LoginManager loginManager = new LoginManager();
		
		//�α��� ������ Ȯ���Ѵ�.
		FamilyMemberVO vo = loginManager.checkLogin(userId, userPw);
		System.out.println(vo);
		
		RequestDispatcher rd = null;
		if (vo != null) {
			String[][] familyMemberList = null;
			if (serviceRoute.equals("2000")) {
				// web ���� ������ �� ������ �� 
				System.out.println("Web ����");
				
				//���� ��ü�� �����Ѵ�.
				HttpSession session = request.getSession();
				
				//session ��ü�� �α��� ������ �����Ѵ�.
				session.setAttribute("userInfo", vo);
				
				//session ��ü�� �������� ����
				session.setAttribute("serviceRoute", serviceRoute);
				
				FamilyMemberVO managerVO = loginManager.homeManagerInfo(vo.getFamilyHomecode());
				
				familyMemberList = loginManager.getMemberListWeb(vo.getFamilyHomecode(), managerVO.getMemberCode());
				String birth = format(vo.getMemberBirth());
				
				String homeName = loginManager.getHomeName(vo.getFamilyHomecode());
				System.out.println(homeName);
				FamilyHomeBean bean = new FamilyHomeBean(homeName, managerVO.getMemberNickName(), birth, managerVO.getMemberPhoto(), familyMemberList); 
				request.setAttribute("familyHome", bean);
				
				//�α����� ������� Ȩ���� ���ǿ� ����
				FamilyHomeVO homeInfo = new FamilyHomeVO(vo.getFamilyHomecode(), homeName);
				session.setAttribute("homeInfo", homeInfo);
				
				rd = request.getRequestDispatcher("JSP/home/myhome.jsp");
				rd.forward(request, response);
			} else {
				// �ȵ���̵� �ڵ� �� ��~!
				System.out.println("APP ����");
				
				//���� �������� ������ �޾ƿ´�.
				familyMemberList = loginManager.getMemberListApp(vo.getFamilyHomecode());
				
				String birth = format(vo.getMemberBirth());
				//�α��� �� ȸ���� ������ �����Ѵ�.
				out.println("200|" + vo.getMemberCode() + "|" + vo.getFamilyHomecode() + "|" +
					vo.getMemberName() + "|" + vo.getMemberPhone() + "|" +  vo.getMemberEmail() + "|" +
					vo.getMemberId() + "|" + vo.getMemberPw() + "|" + vo.getMemberPhoto() + "|" +
					vo.getMemberNickName() + "|" + vo.getMemberColor() + "|" +
					birth + "|" + vo.getMemberRole());
					
				//���� �������� ������ �����Ѵ�.
				for (String[] simpleFamilyMemberInfo : familyMemberList) {
					out.println("300|" + simpleFamilyMemberInfo[0] + "|" + simpleFamilyMemberInfo[1] +
							"|" + simpleFamilyMemberInfo[2] + "|" + simpleFamilyMemberInfo[3] +
							"|" + simpleFamilyMemberInfo[4]);
				}
			}
		} else {
			if (serviceRoute.equals("2000")) {
				// web ���� ������ �� ������ �� 
				System.out.println("Web ����");
				rd = request.getRequestDispatcher("main.html");
				rd.forward(request, response);
			} else {
				// �ȵ���̵� �ڵ� �� ��~!
				System.out.println("APP ����");
				out.println("500");
			}
		}
	}
	
	
	/*//Date Ÿ���� ��¥�� String���� ��ȯ ���ִ� �޼ҵ�
	private String sendChangeDate(Date dateTime) {
		if (dateTime == null) {
			return "00�� 00��";
		} else {
			String mon = (dateTime.getMonth())>9 ? "" + (dateTime.getMonth()) : "0" + (dateTime.getMonth());
			String day = dateTime.getDate()>9 ? "" +dateTime.getDate() : "0" + dateTime.getDate();
			return  mon +"�� " + day + "��";
		}
	}*/
	
	public String format(Date d){ // Date�� String���� ������/ ������ ���� �� ���
	    SimpleDateFormat fmt = new SimpleDateFormat("yy-MM-dd");
	    String date = fmt.format(d);
	    return date;
	}
}
