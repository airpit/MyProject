package control.home;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.home.JoinManager;

/**
 * Servlet implementation class JoinServlet
 */
@WebServlet("/join.do")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		RequestDispatcher rd = null;
		
		String joinServiceRoute = request.getParameter("joinServiceRoute");
		String joinId = request.getParameter("joinId");
		String joinPassword = request.getParameter("joinPassword");
		String joinName = request.getParameter("joinName");
		String joinEmail = request.getParameter("joinEmail");
		String joinHomeCode = request.getParameter("joinHomeCode");
		String joinPhoneNum = request.getParameter("joinPhoneNum");
		
		System.out.println("serviceRoute : " + joinServiceRoute);
		System.out.println("joinId : " + joinId);
		System.out.println("joinPassword : " + joinPassword);
		System.out.println("joinName : " + joinName);
		System.out.println("joinEmail : " + joinEmail);
		System.out.println("joinHomeCode : " + joinHomeCode);
		System.out.println("joinPhoneNum : " + joinPhoneNum);		
		
		
		JoinManager joinManager = new JoinManager();
		
		int checkJoin = 0;
		byte checkId = joinManager.checkId(joinId);

		if (checkId == -1) {
			//���̵� ���� ���� 
			
			if (joinHomeCode.trim().equals("no")) {
				// �ʴ� �޾� ���� �ϴ� ���
				System.out.println("ȣũ�尡 ����");
				checkJoin = joinManager.joinMember(joinId, joinPassword, joinName, joinEmail, joinPhoneNum);
			} else {
				
				System.out.println("ȣũ�尡 �ִ�.");
				// �ʴ� ���� �ʰ� ���� ���� ���
				checkJoin = joinManager.joinMember(joinId, joinPassword, joinName, joinEmail, joinPhoneNum, joinHomeCode);
			}
			
			if (joinServiceRoute.equals("2000")) {
				// web���� ȸ�� ���� �ϴ� ���
			
				if (checkJoin != 0) {
					rd = request.getRequestDispatcher("Main.jsp");
					rd.forward(request,response);
				} else {
					rd = request.getRequestDispatcher("Main.jsp");
					rd.forward(request,response);
				}
			} else {
				// App���� ȸ�� �����ϴ� ���
				System.out.println("App ���Գ�?");
				System.out.println(checkJoin);
				
				if (checkJoin != 0) {
					System.out.println("����");
					out.println("success");
				} else {
					System.out.println("����");
					out.println("fail");
				}
			}
		} else {
			//���̵� �̹� �ִ� ���
			if (joinServiceRoute.equals("2000")) {
				// web���� ȸ�� ���� �ϴ� ���
				rd = request.getRequestDispatcher("Main.jsp");
				rd.forward(request,response);
			} else {
				// App���� ȸ�� �����ϴ� ���
				System.out.println("App ���Գ�?");
				System.out.println("����");
				out.println("fail");
			}
			
		}
		
	}

}
