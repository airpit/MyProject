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
		
		//App을 위해 PrintWriter를 생성한다.
		PrintWriter out = response.getWriter();
		
		String userId = request.getParameter("user-id");
		String userPw = request.getParameter("user-pw");
		
		//Web와 App을 구분해준다.
		String serviceRoute = request.getParameter("serviceRoute");
		
		
		//로그인 매니저를 생성한다.
		LoginManager loginManager = new LoginManager();
		
		//로그인 승인을 확인한다.
		FamilyMemberVO vo = loginManager.checkLogin(userId, userPw);
		System.out.println(vo);
		
		RequestDispatcher rd = null;
		if (vo != null) {
			String[][] familyMemberList = null;
			if (serviceRoute.equals("2000")) {
				// web 으로 접속할 때 들어오는 곳 
				System.out.println("Web 접속");
				
				//세션 객체를 생성한다.
				HttpSession session = request.getSession();
				
				//session 객체에 로그인 정보를 저장한다.
				session.setAttribute("userInfo", vo);
				
				//session 객체에 접속정보 저장
				session.setAttribute("serviceRoute", serviceRoute);
				
				FamilyMemberVO managerVO = loginManager.homeManagerInfo(vo.getFamilyHomecode());
				
				familyMemberList = loginManager.getMemberListWeb(vo.getFamilyHomecode(), managerVO.getMemberCode());
				String birth = format(vo.getMemberBirth());
				
				String homeName = loginManager.getHomeName(vo.getFamilyHomecode());
				System.out.println(homeName);
				FamilyHomeBean bean = new FamilyHomeBean(homeName, managerVO.getMemberNickName(), birth, managerVO.getMemberPhoto(), familyMemberList); 
				request.setAttribute("familyHome", bean);
				
				//로그인한 사용자의 홈정보 세션에 세팅
				FamilyHomeVO homeInfo = new FamilyHomeVO(vo.getFamilyHomecode(), homeName);
				session.setAttribute("homeInfo", homeInfo);
				
				rd = request.getRequestDispatcher("JSP/home/myhome.jsp");
				rd.forward(request, response);
			} else {
				// 안드로이드 코드 들어갈 곳~!
				System.out.println("APP 접속");
				
				//가족 구성원의 정보를 받아온다.
				familyMemberList = loginManager.getMemberListApp(vo.getFamilyHomecode());
				
				String birth = format(vo.getMemberBirth());
				//로그인 한 회원의 정보를 전송한다.
				out.println("200|" + vo.getMemberCode() + "|" + vo.getFamilyHomecode() + "|" +
					vo.getMemberName() + "|" + vo.getMemberPhone() + "|" +  vo.getMemberEmail() + "|" +
					vo.getMemberId() + "|" + vo.getMemberPw() + "|" + vo.getMemberPhoto() + "|" +
					vo.getMemberNickName() + "|" + vo.getMemberColor() + "|" +
					birth + "|" + vo.getMemberRole());
					
				//가족 구성원의 정보를 전송한다.
				for (String[] simpleFamilyMemberInfo : familyMemberList) {
					out.println("300|" + simpleFamilyMemberInfo[0] + "|" + simpleFamilyMemberInfo[1] +
							"|" + simpleFamilyMemberInfo[2] + "|" + simpleFamilyMemberInfo[3] +
							"|" + simpleFamilyMemberInfo[4]);
				}
			}
		} else {
			if (serviceRoute.equals("2000")) {
				// web 으로 접속할 때 들어오는 곳 
				System.out.println("Web 접속");
				rd = request.getRequestDispatcher("main.html");
				rd.forward(request, response);
			} else {
				// 안드로이드 코드 들어갈 곳~!
				System.out.println("APP 접속");
				out.println("500");
			}
		}
	}
	
	
	/*//Date 타입의 날짜를 String으로 변환 해주는 메소드
	private String sendChangeDate(Date dateTime) {
		if (dateTime == null) {
			return "00월 00일";
		} else {
			String mon = (dateTime.getMonth())>9 ? "" + (dateTime.getMonth()) : "0" + (dateTime.getMonth());
			String day = dateTime.getDate()>9 ? "" +dateTime.getDate() : "0" + dateTime.getDate();
			return  mon +"월 " + day + "일";
		}
	}*/
	
	public String format(Date d){ // Date를 String으로 변경함/ 데이터 넣을 때 사용
	    SimpleDateFormat fmt = new SimpleDateFormat("yy-MM-dd");
	    String date = fmt.format(d);
	    return date;
	}
}
