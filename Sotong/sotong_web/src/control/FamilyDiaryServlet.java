package control;

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

import manager.FamilyDiaryManager;
import model.bean.FamilyDiaryPartBean;
import model.bean.FamilyDiaryViewBean;
import dao.diary.FamilyDiaryVO;
import dao.diary.FamilyDiaryViewVO;
import dao.home.FamilyMemberVO;

/**
 * Servlet implementation class FamilyDiaryServlet
 */
@WebServlet(urlPatterns={"/familyDiary.do" , "/familyDiaryList.do", "/familyDiary_insert.do"})
public class FamilyDiaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FamilyDiaryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI();
		int lastIndex = uri.lastIndexOf("/");
		String action = uri.substring(lastIndex+1);

		if(action.equals("familyDiary.do") || action.equals("familyDiaryList.do"))
		{
			requestGetSimpleFamilyDiaryList(request,response);
		}
		else if(action.equals("familyDiary_insert.do"))
		{
			requestAddFamilyDiary(request,response);
		}
	}
	
	
	private void requestGetSimpleFamilyDiaryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		FamilyDiaryManager manager = new FamilyDiaryManager();
		HttpSession session = request.getSession();
		String homeCode = null;
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute"); // 웹은 세션에서 받아옴
		String serviceRouteApp = null;
		
		if(serviceRouteWeb==null) // 앱으로 접속함
		{
			serviceRouteApp = request.getParameter("serviceRoute");
			if(serviceRouteApp == null)
			{
				System.out.println("접속 에러");
			}
			else	//앱으로 접속시 처리
			{			
				System.out.println("APP FamilyDiary 심플리스트 메소드 접속.");	
				homeCode = request.getParameter("homeCode");	
			}
		}
		else	//웹 접속 처리
		{
			FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
			homeCode = userInfo.getFamilyHomecode();		
		}
		
		FamilyDiaryVO[] res = manager.getSimpleFamilyDiaryList(homeCode);
		
		
		if(serviceRouteWeb == null)	//앱 접속시 처리.
		{
			PrintWriter out = response.getWriter();

			if(res != null)	// 개인일기 간략정보 읽기 성공 시 처리
			{
				for(FamilyDiaryVO temp : res)
				{
					//out.println("200|" + simpleDiary[0] + "|" + simpleDiary[1] + "|" + simpleDiary[2]);
					//앱으로 데이터 보내는 부분.
				}
			}
			else	// 실패 시
			{
				out.println("500");
			}
		}
		else	// 웹 접속시 처리.
		{	
			request.setAttribute("simpleFamilyDiaryList", res);
			
			String uri = request.getRequestURI();
			int lastIndex = uri.lastIndexOf("/");
			String action = uri.substring(lastIndex+1);
			
			if(action.equals("familyDiaryList.do"))
			{
				RequestDispatcher rd = request.getRequestDispatcher("JSP/diary/FamilyDiaryWrite.jsp");
				rd.forward(request, response);	
			}
			else
			{
				if(res!=null)
				{
					FamilyDiaryViewVO[] familyDiaryInfo = manager.getFamilyDiaryInfo(res[0].getFamilyDiaryCode());
					
					if(familyDiaryInfo != null)
					{
						FamilyDiaryPartBean[] partBean = new FamilyDiaryPartBean[familyDiaryInfo.length];
					
						for(int i = 0 ; i < familyDiaryInfo.length ; i++)	
						{
							partBean[i] = new FamilyDiaryPartBean(familyDiaryInfo[i].getFamilyDiaryPartCode(),familyDiaryInfo[i].getMemberNickname(),familyDiaryInfo[i].getFamilyDiaryPartDate(),familyDiaryInfo[i].getSotongContentsCode(),familyDiaryInfo[i].getContents(),familyDiaryInfo[i].getEmoticonName(),familyDiaryInfo[i].getEmoticonRoute(),familyDiaryInfo[i].getImageName(),familyDiaryInfo[i].getImageWrittenDate());
						}
					
						FamilyDiaryViewBean fDiaryInfo = new FamilyDiaryViewBean(familyDiaryInfo[0].getFamilyDiaryCode(),familyDiaryInfo[0].getFamilyHomeCode(),familyDiaryInfo[0].getFamilyDiaryDate(),partBean);
						
						request.setAttribute("fDiaryInfo", fDiaryInfo);
					}		
				}
				RequestDispatcher rd = request.getRequestDispatcher("JSP/diary/FamilyDiary.jsp");
				rd.forward(request, response);	
			}
		}
	}

	private void requestAddFamilyDiary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		FamilyDiaryManager manager = new FamilyDiaryManager();
		
		HttpSession session = request.getSession();
		FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
		
		String homeCode = userInfo.getFamilyHomecode();
		String memberCode = userInfo.getMemberCode();
		String familyDiaryDate = format();
		
		String contents = request.getParameter("FamilyDiaryContents");
		String imageName = "img/nono";	//request.getParameter("imageName");
		String imageWrittenDate = familyDiaryDate;
		String emoticonCode = "em1";	//request.getParameter("emoticonCode");
		
		manager.addFamilyDiary(homeCode, memberCode, familyDiaryDate, contents, imageName, imageWrittenDate, emoticonCode);
	}
	
	public String format(){ // Date를 String으로 변경함/ 데이터 넣을 때 사용
		Date today = new Date();
	    SimpleDateFormat fmt = new SimpleDateFormat("yy-MM-dd");
	    String date = fmt.format(today);
	    return date;
	}

}
