package WebServerThread;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;
import android.widget.Toast;



public class WebServerGetSimpleSingleScheduleThread extends Thread {
   //private static final String SERVER_URL = "http://192.168.0.20:8089/AndroidHomeworkServer/ServletLogin";
   
   private static final String GET_SIMPLE_SCHEDULE_URL = "http://192.168.0.35:8089/so-tong/get_simple_single_schedule";
   
    //철연 : http://192.168.0.110:8089/so-tong/login.do;
   //"user-id"   , "user-pw"
   private ActionBarActivity actionBarActivity;
   private Context context;
   private String memberCode;
   private String year;
   private String month;
   private String day;
   //private ListView lv;
   	
   
   private Handler handler;
 

 

public WebServerGetSimpleSingleScheduleThread(
		ActionBarActivity actionBarActivity, Context context,
		String memberCode, String year, String month, String day) {
	this.actionBarActivity = actionBarActivity;
	this.context = context;
	this.memberCode = memberCode;
	this.year = year;
	this.month = month;
	this.day = day;
	this.handler=new Handler();
}

public void run() {
      ArrayList<String[]>list = request();
      if(list!=null){
      //   handler.post(new Runnable() {
      //      public void run() {
               //loginCheck = true;
              
               /*
                Intent userAndFriendsIntent = new Intent();
                userAndFriendsIntent.setClass(context, MemberActivity.class);
                userAndFriendsIntent.putExtras(myBundle);
                context.startActivity(userAndFriendsIntent);
                */
      //      }
      //   });
         
      }
      else{
         handler.post(new Runnable() {
            @Override
            public void run() {
               //loginCheck = false;
               Toast.makeText(context, "해당 날짜에 일정이 없습니다.", Toast.LENGTH_SHORT).show();
            }
         });
      
      
      }
   }
    
   private ArrayList<String[]> request(){
      String line = null;
      ArrayList<String[]>list=new ArrayList<String[]>();
      try{ 
         HttpClient client = new DefaultHttpClient();
         HttpPost httpPost = new HttpPost(GET_SIMPLE_SCHEDULE_URL);
         List<NameValuePair> fields = new ArrayList<NameValuePair>();
         fields.add(new BasicNameValuePair("serviceRoute","1000"));
         fields.add(new BasicNameValuePair("memberCode",memberCode));
         fields.add(new BasicNameValuePair("year",year));
         fields.add(new BasicNameValuePair("month",month));
         fields.add(new BasicNameValuePair("day",day));
         httpPost.setEntity(new UrlEncodedFormEntity(fields));
         
         HttpResponse response = client.execute(httpPost);
         InputStream inStream = response.getEntity().getContent();
         BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
         
         while(true){
         line = reader.readLine();
         if(line==null){
        	 break;
         }
         else
         {
        	 if(line.startsWith("200"))
        	 {
        		 StringTokenizer st=new StringTokenizer(line,"|");
        		 String []simpleInfo=new String[3];
        		 String code=st.nextToken();
        		 simpleInfo[0]=st.nextToken();//스케쥴코드
        		 simpleInfo[1]=st.nextToken();//일정제목
        		 simpleInfo[2]=st.nextToken();//사진경로
        		 list.add(simpleInfo);
        	 }
        	
        	 else if(line.startsWith("500"))
        	 {
        		 list=null;
        	 }
         }
         }
         
      }catch(IOException ioe){
         ioe.printStackTrace();
      }
      return list;
   }
   
   
   
}