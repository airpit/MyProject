package connection;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conn.ManageData;
import conn.keeper.KeeperDAO;
import conn.silver.SilverDAO;
import conn.silver.vo.SilverAddressVO;
import conn.silver.vo.SilverHeartRateVO;
import conn.silver.vo.SilverVO;

/**
 * created by hyunjin
 * connection of server
 * This servlet receive data from silverApp to server.
 * 
 * 
 * Servlet implementation class receiveData
 */

@WebServlet("/receiveData.do")
public class receiveData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	       
	private SilverDAO sDAO;
	private KeeperDAO kDAO;
	private Connect connect;
	private HashMap<String,String> dataMap;
	private String db;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public receiveData() {
        super();
        // TODO Auto-generated constructor stub
        
        sDAO = new SilverDAO();
        kDAO =new KeeperDAO();
        connect = new Connect();

		dataMap = new HashMap<String,String>();
		db = "";
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
   /* protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	System.out.println("!!receiveData");
		dataMap = connect.getData(request, response);
		System.out.println("--"+connect.getType());
		
		switch(connect.getType())
		{
		case "sendBLE":
			db = "update silverMiBandConn set BLESTATUS="+dataMap.get("BLESTATUS")+" where silverID="+dataMap.get("silverID");
			connect.sendData(db, request, response);
			break;
		case "sendSilverData":
			db = "insert into silverData("+connect.getKey()+") values("+connect.getValue()+")";
			connect.sendData(db, request, response);
			break;
		case "sendSilverToken":
			break;
		case "sendHomeComing":
			db = "update silverHomeComingStatus set homeComing="+connect.getKey()+" where silverID="+connect.getValue();
			connect.sendData(db, request, response);
			break;
		case "sendIdentifyNumber":
			break;
		case "sendKeeperName":
			break;
		case "sendKeeperToken":
			db = "insert into keeperData("+connect.getKey()+") values("+connect.getValue()+")";
	    	connect.sendData(db, request, response);
			System.out.println("getKeeperToken :"+db);
			break;
		case "sendRaspIP":
			break;
		case "sendRole":
			break;
		//?쒖쁺異붽?
		case "checkJoin":
			checkJoinProcess(dataMap, request, response);
			break;
		}
    }*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		System.out.println("!!receiveData");
		dataMap = connect.getData(request, response);
		System.out.println("--"+connect.getType());
		
		switch(connect.getType())
		{
		/*case "sendBLE":
			db = "update silverMiBandConn set BLESTATUS="+dataMap.get("BLESTATUS")+" where silverID="+dataMap.get("silverID");
			connect.sendData(db, request, response);
			break;
		case "sendSilverData":
			db = "insert into silverData("+connect.getKey()+") values("+connect.getValue()+")";
			connect.sendData(db, request, response);
			break;
		case "sendSilverToken":
			break;
		case "sendHomeComing":
			db = "update silverHomeComingStatus set homeComing="+connect.getKey()+" where silverID="+connect.getValue();
			connect.sendData(db, request, response);
			break;
		case "sendIdentifyNumber":
			break;
		case "sendKeeperName":
			break;
		case "sendKeeperToken":
			db = "insert into keeperData("+connect.getKey()+") values("+connect.getValue()+")";
	    	connect.sendData(db, request, response);
			System.out.println("getKeeperToken :"+db);
			break;
		case "sendRaspIP":
			break;
		case "sendRole":
			break;*/
		//?쒖쁺異붽?
		case "checkJoin": //?ъ슜?먭? ?좉퇋 ?ъ슜?먯씤吏, 湲곗〈 ?ъ슜?먯씤吏 援щ텇?섎뒗 ?곗궛.
			checkJoinProcess(dataMap, request, response);
			break;
		case "sendSilverDataToKeeper": //?ㅽ띁濡쒕????앸퀎踰덊샇瑜?諛쏆븘, ?대떦?섎뒗 ?ㅻ쾭???뺣낫瑜?蹂대궡???곗궛
			sendSilverDataToKeeperProcess(dataMap,request,response);
			break;
		case "sendSilverData" ://?ㅻ쾭濡쒕????앸퀎踰덊샇瑜?諛쏆븘, ?대떦 ?뺣낫瑜?蹂대궡???곗궛
			sendSilverDataProcess(dataMap,request,response);
			break;
		case "receiveSilverData": //?ㅻ쾭濡쒕????ㅻ쾭???앹껜?곗씠?곕? 諛쏆븘 ?섏쭛?섏뿬 怨꾩궛?섎뒗 ?곗궛.
			receiveSilverDataProcess(dataMap,request,response);
			break;
		/*case "checkEmergency":
			checkSilverEmergencyStatusProcess(dataMap,request,response);
			break;*/
		case "joinSilver":
			joinSilverProcess(dataMap,request,response);
			break;
		case "joinKeeper":
			joinKeeperProcess(dataMap,request,response);
			break;
		case "checkJoinKeeper":
			checkJoinKeeperProcess(dataMap,request,response);
			break;
		case "checkCreateKeeperID":
			checkCreateKeeperIDProcess(dataMap,request,response);
			break;
		case "requestSOS":
			requestSOSProcess(dataMap,request,response);
			break;
		}
	}
	protected void checkJoinProcess(HashMap<String,String> dataMap,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
		String androidID=dataMap.get("androidID");
		System.out.println("androidID="+androidID+"\n");
		//String token=dataMap.get("token");
		//System.out.println("token="+token+"\n");
		String silverID=sDAO.selectSilverID(androidID);    			
		String keeperID=kDAO.selectKeeperID(androidID);
		HashMap<String,String> sendMap=new HashMap<String,String>();
		
		if(!silverID.contains("noData"))
		{
			sendMap.put("result", "silver");
			sendMap.put("silverID",silverID);
			System.out.println("S:"+silverID);
		}
		else if(!keeperID.contains("noData"))
		{
			sendMap.put("result", "keeper");
			sendMap.put("keeperID",keeperID);
			
			System.out.println("K:"+keeperID);
		}
		else
		{
			sendMap.put("result", "new");
			sendMap.put("androidID", androidID);
		}
		
		connect.setData(sendMap, request, response);
		
		
    }
	protected void sendSilverDataToKeeperProcess(HashMap<String,String> dataMap,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
		String keeperID=dataMap.get("keeperID");
		System.out.println("keeperID="+keeperID+"\n");
		String silverID=kDAO.selectSilverID(keeperID);
		SilverVO vo=sDAO.selectSilverData(silverID);
		
		if(vo==null)
		{
			System.out.println("vo==null");
			return;
		}
		HashMap<String,String> sendMap=new HashMap<String,String>();
		int walkCount=sDAO.sumWalkCount(silverID);
		int heartRate=vo.getHeartRate();
		String currentTime=vo.getCurrentTime();
		boolean connMiBand=vo.getCheckMiBand();
		
		//silver Emergency ?곹솴 泥댄겕 ?곗궛 異붽?.
				SilverHeartRateVO[] hrList=sDAO.selectSilverHeartRateVO(silverID);
				SilverVO[] voList=sDAO.selectFixtedNumberSilverDataArray(silverID);
				
				ManageData manage=new ManageData();
				
				System.out.println("hrList length:"+hrList.length+"\n");
				System.out.println("voList length:"+voList.length+"\n");
				int result=manage.checkEmergencyLevel(hrList, voList);
				
				String status;
				System.out.println("Result:"+result+"\n");
				if(result<50&&result>=0)
				{
					 status="emergency";
				}
				else if(result>=50&&result<=69)
				{
					status="warning";
				}
				else if(result>=70)
				{
					status="safe";
				}
				else
				{
					status="error";
				}
				
				//
				if(status.contains("emergency"))
				{
					sendPushAlarm push=new sendPushAlarm();
					String message="보호대상자에게 이상현상 발생. 영상 스트리밍으로 연결합니다.";
					SilverAddressVO addr =sDAO.selectSilverAddress(silverID);
					String keeperToken=kDAO.selectKeeperToken(keeperID);
					String raspIP=addr.getRassberryPiURL();
					String data=message+"|role=viewStreaming|keeperID="+keeperID+"|raspIP="+raspIP;
					System.out.println("data:"+data+"\n");		
					push.send_FCM_Notification(keeperToken, "AAAAB7TI-uE:APA91bFKkm7OJcvHl8dJv8cswAzz7Ulg42odqafOF-9FayoYWvzAIf5VunKRgFBPLDzPSyCjy_BCfURzNU-ojWcHb7ULO23JjsaqdG-a42YCXbmGJ8n-Wmo_qE7Q61TwzQJHpDjSKeOc ", data);
				}
		System.out.println("--------------------sendSilverDataToKeeper-----------------\n");
		System.out.println("walkCount:"+walkCount+"\n");
		System.out.println("heartRate:"+heartRate+"\n");
		System.out.println("currentTime:"+currentTime+"\n");
		System.out.println("connMiBand:"+connMiBand+"\n");
		System.out.println("status:"+status+"\n");
		System.out.println("----------------------------------------------------------\n");

		sendMap.put("walkCount", walkCount+"");
		sendMap.put("heartRate", heartRate+"");
		sendMap.put("currentTime", currentTime);
		sendMap.put("connMiBand",connMiBand+"");
		sendMap.put("status", status);
		connect.setData(sendMap, request, response);
    }
	protected void sendSilverDataProcess(HashMap<String,String> dataMap,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String silverID=dataMap.get("silverID");
		System.out.println("silverID="+silverID+"\n");
		SilverVO vo=sDAO.selectSilverData(silverID);
		if(vo==null)
		{
			return;
		}
		int identifyNumber=sDAO.selectIdentifyNumber(silverID);
		int walkCount=sDAO.sumWalkCount(silverID);
		int heartRate=vo.getHeartRate();
		String currentTime=vo.getCurrentTime();
		boolean connMiBand=vo.getCheckMiBand();
		HashMap<String,String> sendMap=new HashMap<String,String>();
		
		
		//silver Emergency ?곹솴 泥댄겕 ?곗궛 異붽?.
		SilverHeartRateVO[] hrList=sDAO.selectSilverHeartRateVO(silverID);
		SilverVO[] voList=sDAO.selectFixtedNumberSilverDataArray(silverID);
		
		ManageData manage=new ManageData();
		
		System.out.println("hrList length:"+hrList.length+"\n");
		System.out.println("voList length:"+voList.length+"\n");
		int result=manage.checkEmergencyLevel(hrList, voList);
		
		String status;
		System.out.println("Result:"+result+"\n");
		if(result<50&&result>=0)
		{
			 status="emergency";
		}
		else if(result>=50&&result<=69)
		{
			status="warning";
		}
		else if(result>=70)
		{
			status="safe";
		}
		else
		{
			status="error";
		}
		
		//
		if(status.contains("emergency"))
		{
			//?ㅽ띁?깆쑝濡??몄떆?뚮┝ 蹂대궡???곗궛 異붽? ?꾩슂.
			
		}
		
		
		System.out.println("---------------------sendSilverDataProcess----------------\n");
		System.out.println("walkCount:"+walkCount+"\n");
		System.out.println("heartRate:"+heartRate+"\n");
		System.out.println("currentTime:"+currentTime+"\n");
		System.out.println("connMiBand:"+connMiBand+"\n");
		System.out.println("identifyNumber:"+identifyNumber+"\n");
		System.out.println("status:"+status+"\n");
		System.out.println("----------------------------------------------------------\n");

		sendMap.put("walkCount", walkCount+"");
		sendMap.put("heartRate", heartRate+"");
		sendMap.put("currentTime", currentTime);
		sendMap.put("connMiBand",connMiBand+"");
		sendMap.put("identifyNumber", identifyNumber+"");
		sendMap.put("status",status);
		/*sendPushAlarm push=new sendPushAlarm();
		String message="蹂댄샇??곸옄?먭쾶 ?댁긽 ?꾩긽 諛쒖깮. ?곸긽 ?ㅽ듃由щ컢?쇰줈 ?곌껐?⑸땲??";
		
		SilverAddressVO addr =sDAO.selectSilverAddress(silverID);
		String silverToken=addr.getSilverToken();
		String raspIP=addr.getRassberryPiURL();
		String data=message+"|role=viewStreaming|keeperID="+"KP_000000002"+"|raspIP="+raspIP;
		System.out.println("data:"+data+"\n");		
		push.send_FCM_Notification(silverToken, "AAAAB7TI-uE:APA91bFKkm7OJcvHl8dJv8cswAzz7Ulg42odqafOF-9FayoYWvzAIf5VunKRgFBPLDzPSyCjy_BCfURzNU-ojWcHb7ULO23JjsaqdG-a42YCXbmGJ8n-Wmo_qE7Q61TwzQJHpDjSKeOc ", data);
		*/
		
		connect.setData(sendMap, request, response);
	}
	protected void receiveSilverDataProcess(HashMap<String,String> dataMap,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String silverID=dataMap.get("silverID");
		String walkCount=dataMap.get("walkCount");
		String heartRate=dataMap.get("heartRate");
		String currentTime=dataMap.get("currentTime");
		String connMiBand=dataMap.get("connMiBand");
		System.out.println("---------------------receiveSilverDataProcess----------------\n");
		System.out.println("silverID:"+silverID+"\n");
		System.out.println("walkCount:"+walkCount+"\n");
		System.out.println("heartRate:"+heartRate+"\n");
		System.out.println("currentTime:"+currentTime+"\n");
		System.out.println("connMiBand:"+connMiBand);
		System.out.println("----------------------------------------------------------\n");
		boolean conn;
		if(connMiBand.contains("true"))
		{
			conn=true;
		}
		else conn=false;
		HashMap<String,String>sendMap=new HashMap<String,String>();
		SilverVO newData=new SilverVO(Integer.parseInt(heartRate),Integer.parseInt(walkCount),currentTime,conn);
				
		int result=sDAO.insertSilverData(silverID, newData);

		setHeartRateData(silverID);
		
		if(result!=0)
			sendMap.put("insertResult","success");
		else
			sendMap.put("insertResult", "fail");
		connect.setData(sendMap, request, response);
	}
	/*protected void checkSilverEmergencyStatusProcess(HashMap<String,String>dataMap,HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
	{
		String silverID=dataMap.get("silverID");
		System.out.println("----------------checkEmergency--------------------\n");
		System.out.println("silverID:"+silverID+"\n");
		SilverHeartRateVO[] hrList=sDAO.selectSilverHeartRateVO(silverID);
		SilverVO[] voList=sDAO.selectFixtedNumberSilverDataArray(silverID);
		HashMap<String,String> sendMap=new HashMap<String,String>();
		ManageData manage=new ManageData();
		
		System.out.println("hrList length:"+hrList.length+"\n");
		System.out.println("voList length:"+voList.length+"\n");
		int result=manage.checkEmergencyLevel(hrList, voList);
		
		String status;
		System.out.println("Result:"+result+"\n");
		if(result<50&&result>=0)
		{
			 status="emergency";
		}
		else if(result>=50&&result<=69)
		{
			status="warning";
		}
		else if(result>=70)
		{
			status="safe";
		}
		else
		{
			status="error";
		}
		System.out.println("status:"+status+"\n");
		sendMap.put("status",status);
		//SilverHeartRateVO[] voList=sDAO.selectSilverHeartRateVO(silverID);
		connect.setData(sendMap, request, response);
	}*/
	public void setHeartRateData(String silverID)
	{
		
			
		SilverVO[] voList=sDAO.selectSilverDataArray(silverID);
		//ManageData manage=new ManageData();
		
		ArrayList<Integer>maxList=new ArrayList<Integer>();
		ArrayList<Integer>minList=new ArrayList<Integer>();
		
		int	temp=0,max=0,min=1000,length=voList.length;
		
		if(length>10)
		{
			length=10;
		}
		else if(length==0)
		{
			return;
		}
			for(int x=0;x<length;x++)
			{
				
				temp=voList[x].getHeartRate();
				if(max<temp)
				{
					max=temp;
					
				}

			}
			for(int y=0;y<length;y++)
			{
				temp=voList[y].getHeartRate();
				if(min>temp)
				{
					min=temp;
				}
			
			}
			sDAO.insertHeartRate(silverID, min, max, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS")).format(new Date(System.currentTimeMillis())));

	}
	protected void joinSilverProcess(HashMap<String,String> dataMap,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HashMap<String,String>sendMap=new HashMap<String,String>();
		String androidID=dataMap.get("androidID");
		String raspIP=dataMap.get("raspIP");
		String token=dataMap.get("token");
		System.out.println("token:"+token);
		
		if(token==null)
		{
			token="TOKEN NODATA";
		}
		ManageData manage=new ManageData();
		String newId=manage.createSilverID(androidID);
		int newIdNum=manage.createIdentifyNumber();
			sDAO.insertSilverID(newId);
			sDAO.insertSilverAddress(newId, raspIP, token, "SSIDNODATA");
			sDAO.insertIdentifyNumber(newId, newIdNum);
			sendMap.put("result", "success");
			sendMap.put("silverID", newId);
			sendMap.put("identifyNumber", newIdNum+"");
			sendMap.put("token", token);
			System.out.println("silverID:"+sendMap.get("silverID")+"\n");
			System.out.println("result:"+sendMap.get("result")+"\n");
			
			connect.setData(sendMap, request, response);
			System.out.println("---------------------------------------------");
				
		
		
		
		
		
		
	}
	protected void joinKeeperProcess(HashMap<String,String> dataMap,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HashMap<String,String>sendMap=new HashMap<String,String>();
		String androidID=dataMap.get("androidID");
		String inputNumber=dataMap.get("inputNumber");
		String inputName=dataMap.get("inputName");
		System.out.println("inputName : "+inputName+"\n");
		System.out.println("inputNumber: "+inputNumber+"\n");
		
		String silverID=sDAO.checkIdentifyNumber(Integer.parseInt(inputNumber));
		String result;
		if(silverID==null)
			{
				result="noIdNum";
			}
	
			result="idNumConfirm";
			SilverAddressVO vo=sDAO.selectSilverAddress(silverID);
			String silverToken=vo.getSilverToken();
			if(vo==null)
			{
				System.out.println("---------vo is null------\n");
				return;
			}
			sendPushAlarm push=new sendPushAlarm();
			String message=inputName+"님이 키퍼 등록을 요청하고 있습니다.";
			String data=message+"|role=joinKeeper|silverID="+silverID+"|KeeperAndroidId="+androidID+"|keeperName="+inputName;
			System.out.println("data:"+data+"\n");		
			push.send_FCM_Notification(silverToken, "AAAAB7TI-uE:APA91bFKkm7OJcvHl8dJv8cswAzz7Ulg42odqafOF-9FayoYWvzAIf5VunKRgFBPLDzPSyCjy_BCfURzNU-ojWcHb7ULO23JjsaqdG-a42YCXbmGJ8n-Wmo_qE7Q61TwzQJHpDjSKeOc ", data);
			/*sendMap.put("silverID",)*/
		connect.setData(sendMap, request, response);
		return;
	}
	protected void checkJoinKeeperProcess(HashMap<String,String> dataMap,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String result=null;
		String kpAndroid=dataMap.get("KeeperAndroidID");
		if(kpAndroid==null)
		{
			result="fail";
		}
		String silverID=dataMap.get("silverID");
		ManageData manage=new ManageData();
		String keeperID=manage.createKeeperID(kpAndroid);
		HashMap<String,String> sendMap=new HashMap<String,String>();
		if(kDAO.insertKeeperID(silverID, keeperID)!=-1)
		{
			result="success";
			
		}
		sendMap.put("keeperID",keeperID);
		sendMap.put("result",result);
		
		
		connect.setData(sendMap, request, response);
	}
	protected void checkCreateKeeperIDProcess(HashMap<String,String> dataMap,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String androidID=dataMap.get("androidID");
		String token=dataMap.get("token");
		String keeperID=kDAO.selectKeeperID(androidID);
		String result;
		HashMap<String,String> sendMap=new HashMap<String,String>();
		
		ManageData manage=new ManageData();
		if(!keeperID.contains("noData"))
		{
			result="created";
			System.out.println("checkCreateKeeperID:"+result);
			kDAO.insertKeeperToken(keeperID, token);
		}
		else
		{
			result="fail";
			System.out.println("checkCreateKeeperID:"+result);
		}
			sendMap.put("result", result);
			System.out.println("----------result:"+result+"-----------");
			
			connect.setData(sendMap, request, response);
	}
	protected void requestSOSProcess(HashMap<String,String> dataMap,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
		String silverID=dataMap.get("silverID");
		
		sendPushAlarm push=new sendPushAlarm();
		String message="보호대상자에게 이상현상 발생. 영상 스트리밍으로 연결합니다.";
		SilverAddressVO addr =sDAO.selectSilverAddress(silverID);
		String[] keeperIDs=kDAO.selectAllKeeperID(silverID);
		for(int i=0;i<keeperIDs.length;i++)
		{
			
		String keeperToken=kDAO.selectKeeperToken(keeperIDs[i]);
		String raspIP=addr.getRassberryPiURL();
		System.out.println("----------requestSOSProcess------------");
		System.out.println("keeperToken:"+keeperToken+"\n");
		System.out.println("raspIP:"+raspIP+"\n");
		String data=message+"|role=viewStreaming|keeperID="+keeperIDs[i]+"|raspIP="+raspIP;
		System.out.println("data:"+data+"\n");		
		push.send_FCM_Notification(keeperToken, "AAAAB7TI-uE:APA91bFKkm7OJcvHl8dJv8cswAzz7Ulg42odqafOF-9FayoYWvzAIf5VunKRgFBPLDzPSyCjy_BCfURzNU-ojWcHb7ULO23JjsaqdG-a42YCXbmGJ8n-Wmo_qE7Q61TwzQJHpDjSKeOc ", data);
		}
    }
}



