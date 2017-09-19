package control.letter;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.letter.LetterManager;
import dao.letter.LetterViewVO;

/**
 * Servlet implementation class LetterServlet
 */
@WebServlet({ "/LetterServlet", "/letter.do","/letter_write.do","/letter_delete.do","/letter_detail.do" })
public class LetterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LetterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/*if(request.getRequestURI().contains("/letter_write.do"))
		{
			
		}
		else if(request.getRequestURI().contains("/letter_delete.do"))
		{
			
		}
		else
		{
			requestGettingDetailLetterContents(request,response);
		}*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		if(request.getRequestURI().contains("/letter_write.do"))
		{
			requestSendingLetter(request,response);
		}
		else if(request.getRequestURI().contains("/letter_delete.do"))
		{
			requestDeleteLetter(request,response);
		}
		else if(request.getRequestURI().contains("/letter_detail.do"))
		{
			requestViewDetailLetterInfo(request,response);
		}
		else
		{
			requestGettingDetailLetterContents(request,response);
		}
	}
	public void requestGettingDetailLetterContents(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
	{
		PrintWriter out=response.getWriter();
	
		String memberCode=request.getParameter("memberCode");
		String devide=request.getParameter("serviceRoute");
		LetterManager manager=new LetterManager();
		
		RequestDispatcher view=null;
		String[][] info= manager.getStringLetterInfoList(memberCode);
		/*for(int i=0;i<info.length;i++)
		{
			for(int j=0;j<info[i].length;j++)
			{
				System.out.println(info[i][j]);
			}
		}*/
		//제목,날짜,이름,코드
		System.out.println("");
		if(info!=null)
		{
			if(devide.equals("1000"))
			{
				System.out.println("App 접속");
				String str="200|";
				String data="";
				for(int i=0;i<info.length;i++)
				{
					for(int j=0;j<info[i].length;j++)
					{
						data=data+info[i][j]+"|".trim();
						
					}
					out.println(str+data+"".trim());
					data="";
				}
				System.out.println("eewewr"+str+data+"".trim());
				
				
			}
			else if(devide.equals("2000"))
			{
				System.out.println("Web 접속");
				//웹 코드 들어가야함.
			}
		}
		else
		{
			if(devide.equals("1000"))
			{
				System.out.println("App 접속");
				
			}
			else if(devide.equals("2000"))
			{
				System.out.println("Web 접속");
				//웹 코드 들어가야함.
			}
		}
	}
	public void requestDeleteLetter(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		
		PrintWriter out=response.getWriter();
		String letterCode=request.getParameter("letterCode");
		
		String devide=request.getParameter("serviceRoute");
		LetterManager manager=new LetterManager();
		
		RequestDispatcher view=null;
		System.out.println("letterCode:"+letterCode);
		boolean isDeleted=manager.deleteLetterInfo(letterCode);									
		//제목,날짜,이름,코드
		System.out.println("");
		if(isDeleted)
		{
			if(devide.equals("1000"))
			{
				System.out.println("App 접속 성공-delete");
				String str="200|";
				out.println(str);
				
			}
			else if(devide.equals("2000"))
			{
				System.out.println("Web 접속");
				//웹 코드 들어가야함.
			}
		}
		else
		{
			if(devide.equals("1000"))
			{
				System.out.println("App 접속");
				String str="500|";
				out.println(str);
				
				
			}
			else if(devide.equals("2000"))
			{
				System.out.println("Web 접속");
				//웹 코드 들어가야함.
			}
		}
	}
	
	
	public void requestSendingLetter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out=response.getWriter();
		
		String senderCode=request.getParameter("senderCode");
		String receiverCode=request.getParameter("receiverCode");
		String letterTitle=request.getParameter("title");
		String contents=request.getParameter("contents");
		String imageName=request.getParameter("imageName");
		String emoticonCode=request.getParameter("emoticonCode");
		
		String letterWrittenDate=request.getParameter("letterWrittenDate");
		String devide=request.getParameter("serviceRoute");
		LetterManager manager=new LetterManager();
		
		RequestDispatcher view=null;
		System.out.println(senderCode+"/"+receiverCode+"/"+letterTitle+"/"+imageName+"/"+emoticonCode+"/");
		boolean isSuccessed= manager.addLetterInfo(senderCode, receiverCode, letterTitle, contents, imageName, emoticonCode, letterWrittenDate);
														
		//제목,날짜,이름,코드
		System.out.println("");
		if(isSuccessed)
		{
			if(devide.equals("1000"))
			{
				System.out.println("App 접속 성공");
				String str="200|";
				out.println(str);
				
			}
			else if(devide.equals("2000"))
			{
				System.out.println("Web 접속");
				//웹 코드 들어가야함.
			}
		}
		else
		{
			if(devide.equals("1000"))
			{
				System.out.println("App 접속");
				String str="500|";
				out.println(str);
				
				
			}
			else if(devide.equals("2000"))
			{
				System.out.println("Web 접속");
				//웹 코드 들어가야함.
			}
		}
	}
	public void requestViewDetailLetterInfo(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out=response.getWriter();
		String letterCode=request.getParameter("letterCode");
		String devide=request.getParameter("serviceRoute");
		LetterManager manager=new LetterManager();
		
		RequestDispatcher view=null;
		System.out.println(letterCode+"/");
		LetterViewVO vo=manager.getLetterInfo(letterCode);												
		//제목,날짜,이름,코드
		System.out.println("");
		if(vo!=null)
		{
			if(devide.equals("1000"))
			{
				System.out.println("App 접속 성공");
				String str="200|"+vo.getReceiver()+"|"+vo.getContents()+"|"+changeDate(vo.getSendDate())+"|"+vo.getSender()+"|";
				out.println(str);
				
			}
			else if(devide.equals("2000"))
			{
				System.out.println("Web 접속");
				//웹 코드 들어가야함.
			}
		}
		else
		{
			if(devide.equals("1000"))
			{
				System.out.println("App 접속");
				String str="500|";
				out.println(str);
				
				
			}
			else if(devide.equals("2000"))
			{
				System.out.println("Web 접속");
				//웹 코드 들어가야함.
			}
		}
	}
	public Date format(String date)
	{
		Date newDate=null;
		try {
			newDate=new SimpleDateFormat("yy-MM-dd").parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newDate;
	}
	public String changeDate(Date date)
	{
		return new SimpleDateFormat("yy-MM-dd").format(date);
	}
}
