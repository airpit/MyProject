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
		
		//����
		
		//���� ���ǿ��� ������
		String serviceRouteWeb = (String)session.getAttribute("serviceRoute");
		
		//���� serviceRoute���� �����
		String serviceRouteApp = null;
		
		//Ȩ �ڵ�
		String homeCode = null;
		if(serviceRouteWeb == null) // �� ���� null�̸� 
		{
			serviceRouteApp = request.getParameter("serviceRoute"); // �� ���񽺰��� �޾ƿ�
			if(serviceRouteApp == null) // �� ���� null �̸�
			{
				System.out.println("���� ����");
				out.println("500|");
				response.sendRedirect("main.html");
			}
			else
			{
				System.out.println("�� ���� - �̿� ��� ����");
				//�� ó��
				
				homeCode = request.getParameter("homeCode");
				
				//first : first_home �� �����ϴ� �̿���� (second�� �Ķ���� �� Ȩ��)
				ConnectedNeighborViewVO[] firstNeighborList = manager.getFirstConnectedNeighborList(homeCode);
				
				//second : second_home �� �����ϴ� �̿���� (first�� �Ķ���� �� Ȩ)
				ConnectedNeighborViewVO[] secondNeighborList = manager.getSecondConnectedNeighborList(homeCode);
				
				
				//���� ���
				ArrayList<String[]> neighborList = new ArrayList<String[]>();
				
				if(firstNeighborList!=null)
				{
					for(ConnectedNeighborViewVO vo : firstNeighborList)
					{
						//���� ��Ͽ� �߰�
						//NeighborListBean bean = new NeighborListBean(vo.getFirstHomeCode(), vo.getFirstHomeName(), vo.getFirstHomeManagerName());
						neighborList.add(new String[]{vo.getFirstHomeCode(), vo.getFirstHomeName(), vo.getFirstHomeManagerName()});
					}
				}
			
				if(secondNeighborList!=null)
				{
					for(ConnectedNeighborViewVO vo : secondNeighborList)
					{
						//���� ��Ͽ� �߰�
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
		else // �� ���� null�� �ƴϸ� - �� ����
		{
			System.out.println("�� ���� - �̿� ��� ����");
			//�� ó��
			
			FamilyMemberVO userInfo = (FamilyMemberVO)session.getAttribute("userInfo");
			
			System.out.println("Ȩ �ڵ� : " + userInfo.getFamilyHomecode());
			
			homeCode = userInfo.getFamilyHomecode();
	
			NeighborListBean[] neighborList = manager.getAllNeighborList(homeCode);
			
				
				//�̿� ��� ����
				request.setAttribute("neighborList", neighborList);
			
				RequestDispatcher rd = request.getRequestDispatcher("JSP/neighbor/neighbor.jsp");
				rd.forward(request, response);	
			
			
		}
	}
	
}


