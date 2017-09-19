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
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;



public class WebServerDeleteSingleScheduleThread extends Thread {
   //private static final String SERVER_URL = "http://192.168.0.20:8089/AndroidHomeworkServer/ServletLogin";
   
   private static final String DELETE_SINGLE_SCHEDULE_URL = "http://192.168.0.35:8089/Server/schedule_delete.do";
   
    //철연 : http://192.168.0.110:8089/so-tong/login.do;
   //"user-id"   , "user-pw"
   private ActionBarActivity actionBarActivity;
   private Context context;
   private String scheduleCode;
   //private ListView lv;
   	
   
   private Handler handler;
 

 

public WebServerDeleteSingleScheduleThread(ActionBarActivity actionBarActivity,
		Context context, String scheduleCode) {
	this.actionBarActivity = actionBarActivity;
	this.context = context;
	this.scheduleCode = scheduleCode;
	this.handler=new Handler();
}

public void run() {
     int res=request();
      if(res!=-1){
      //   handler.post(new Runnable() {
      //      public void run() {
               //loginCheck = true;
              actionBarActivity.finish();
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
    
   private int request(){
      String line = null;
      int res=0;
      try{ 
         HttpClient client = new DefaultHttpClient();
         HttpPost httpPost = new HttpPost(DELETE_SINGLE_SCHEDULE_URL);
         List<NameValuePair> fields = new ArrayList<NameValuePair>();
         fields.add(new BasicNameValuePair("serviceRoute","1000"));
         fields.add(new BasicNameValuePair("scheduleCode",scheduleCode));

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
        		 Toast.makeText(context, "삭제성공", Toast.LENGTH_SHORT).show();
                 
        	 }
        	
        	 else if(line.startsWith("500"))
        	 {
        		 Toast.makeText(context, "접속불량", Toast.LENGTH_SHORT).show();
                 
        		 res=-1;
        	 }
         }
         }
         
      }catch(IOException ioe){
         ioe.printStackTrace();
      }
      return res;
   }
   
   
   
}