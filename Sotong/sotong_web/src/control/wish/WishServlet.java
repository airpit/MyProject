package control.wish;

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

import manager.wish.WishManager;
import dao.wish.WishViewVO;

/**
 * Servlet implementation class WishServlet
 */
@WebServlet({ "/WishServlet", "/wish_add.do", "/wish_delete.do",
		"/wish_update.do", "/wish_finish","/wish.do" })
public class WishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WishServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		if (uri.contains("/wish_add.do")) {
			requestAddWish(request, response);
		} else if (uri.contains("/wish_delete.do")) {
			requestDeleteWish(request, response);
		} else if (uri.contains("/wish_update.do")) {
			requestUpdateWish(request, response);
		} else if (uri.contains("/wish_finish.do")) {
			requestFinishWish(request, response);
		} else if(uri.contains("/wish.do")){
			requestWishList(request, response);
		}

	}

	public void requestWishList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		String homeCode=request.getParameter("homeCode");
		
		String devide=request.getParameter("serviceRoute");
		
		WishManager manager= new WishManager();
		RequestDispatcher view = null;
		WishViewVO[] wishList = manager.getWishList(homeCode);
		if (wishList!= null) {
			if (devide.equals("1000")) {
				System.out.println("App 立加");
				String str = "200|";
				String data = "";
				for (int i = 0; i < wishList.length; i++) {
						data = 	wishList[i].getWishCode()+"|"
								+wishList[i].getMemberNickName()+"|"
								+wishList[i].getSotongContentsCode()+"|"
								+wishList[i].getWishTitle()+"|"
								+wishList[i].getContents()+"|"
								+wishList[i].getEmoticonName()+"|"
								+wishList[i].getEmoticonRoute()+"|"
								+changeDateToString(wishList[i].getWishDate())+"|"
								+changeDateToString(wishList[i].getWishEndDate())+"|"
								+wishList[i].getWishFinish()+"|".trim();
								out.println(str + data + "".trim());
							System.out.println("wishInfo" + str + data + "".trim());
				}
				
				

			} else if (devide.equals("2000")) {
				System.out.println("Web 立加");
				// 昆 内靛 甸绢啊具窃.
			}
		} else {
			if (devide.equals("1000")) {
				System.out.println("App 立加");

			} else if (devide.equals("2000")) {
				System.out.println("Web 立加");
				// 昆 内靛 甸绢啊具窃.
			}
		}
	}

	public void requestAddWish(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		String homeCode=request.getParameter("homeCode");
		String memberCode=request.getParameter("memberCode");
		String wishTitle=request.getParameter("wishTitle");
		String contents=request.getParameter("contents");
		String emoticonCode=request.getParameter("emoticonCode");
		String imageName=request.getParameter("imageName");
		String wishWrittenDate=request.getParameter("wishWrittenDate");
		String wishEndDate=request.getParameter("wishEndDate");
		
		String devide=request.getParameter("serviceRoute");
		
		WishManager manager= new WishManager();
		RequestDispatcher view = null;
		int res = manager.addWish(homeCode,memberCode,wishTitle,contents,emoticonCode,imageName,changeStringToDate(wishWrittenDate),changeStringToDate(wishEndDate));
		if (res!= 0) {
			if (devide.equals("1000")) 
			{
				System.out.println("App 立加");
				String str = "200|";
				out.println(str);
			}	
			 else if (devide.equals("2000")) 
			{
				System.out.println("Web 立加");
				// 昆 内靛 甸绢啊具窃.
			}
		} 
		else {
			if (devide.equals("1000")) {
				System.out.println("App 立加");

			} else if (devide.equals("2000")) {
				System.out.println("Web 立加");
				// 昆 内靛 甸绢啊具窃.
			}
		}
	}
	

	public void requestDeleteWish(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String wishCode=request.getParameter("wishCode");
		String devide=request.getParameter("serviceRoute");
		
		WishManager manager= new WishManager();
		RequestDispatcher view = null;
		int res = manager.deleteWish(wishCode);
		if (res!= 0) {
			if (devide.equals("1000")) 
			{
				System.out.println("App 立加");
				String str = "200|";
				out.println(str);
			}	
			 else if (devide.equals("2000")) 
			{
				System.out.println("Web 立加");
				// 昆 内靛 甸绢啊具窃.
			}
		} 
		else {
			if (devide.equals("1000")) {
				System.out.println("App 立加");

			} else if (devide.equals("2000")) {
				System.out.println("Web 立加");
				// 昆 内靛 甸绢啊具窃.
			}
		}
	}

	public void requestUpdateWish(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String homeCode=request.getParameter("homeCode");
		String wishCode=request.getParameter("wishCode");
		String memberCode=request.getParameter("memberCode");
		String wishTitle=request.getParameter("wishTitle");
		String contents=request.getParameter("contents");
		String emoticonCode=request.getParameter("emoticonCode");
		String imageName=request.getParameter("imageName");
		String wishWrittenDate=request.getParameter("wishWrittenDate");
		String wishEndDate=request.getParameter("wishEndDate");
		
		String devide=request.getParameter("serviceRoute");
		
		WishManager manager= new WishManager();
		RequestDispatcher view = null;
		int res = manager.updateWish(wishCode,homeCode,memberCode,wishTitle,contents,emoticonCode,imageName,changeStringToDate(wishWrittenDate),changeStringToDate(wishEndDate));
		if (res!= 0) {
			if (devide.equals("1000")) 
			{
				System.out.println("App 立加");
				String str = "200|";
				out.println(str);
			}	
			 else if (devide.equals("2000")) 
			{
				System.out.println("Web 立加");
				// 昆 内靛 甸绢啊具窃.
			}
		} 
		else {
			if (devide.equals("1000")) {
				System.out.println("App 立加");

			} else if (devide.equals("2000")) {
				System.out.println("Web 立加");
				// 昆 内靛 甸绢啊具窃.
			}
		}
	}

	public void requestFinishWish(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String wishCode=request.getParameter("wishCode");
		String finishDate=request.getParameter("finishDate");
		
		String devide=request.getParameter("serviceRoute");
		
		WishManager manager= new WishManager();
		RequestDispatcher view = null;
		int res = manager.finishWish(wishCode,changeStringToDate(finishDate));
		if (res!= 0) {
			if (devide.equals("1000")) 
			{
				System.out.println("App 立加");
				String str = "200|";
				out.println(str);
			}	
			 else if (devide.equals("2000")) 
			{
				System.out.println("Web 立加");
				// 昆 内靛 甸绢啊具窃.
			}
		} 
		else {
			if (devide.equals("1000")) {
				System.out.println("App 立加");

			} else if (devide.equals("2000")) {
				System.out.println("Web 立加");
				// 昆 内靛 甸绢啊具窃.
			}
		}
	}

	public Date changeStringToDate(String date) {
		Date dt = null;
		try {
			dt = new SimpleDateFormat("yy-MM-dd").parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dt;

	}

	public String changeDateToString(Date date) {
		return new SimpleDateFormat("yy-MM-dd").format(date);
	}

}
