
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
   
   private static final String DELETE_SINGLE_SCHEDULE_URL = "http://192.168.0.110:8089/so-tong/single_delete";
   
    //철연 : http://192.168.0.110:8089/so-tong/login.do;
   //"user-id"   , "user-pw"
   private ActionBarActivity actionBarActivity;
   private Context context;
   private String memberCode;
  private String scheduleCode;
   
   private Handler handler;
   
   public WebServerDeleteSingleScheduleThread(ActionBarActivity actionBarActivity, Context context, String memberCode,String letterCode) {
	this.actionBarActivity=actionBarActivity;
	this.context = context;
	this.memberCode=memberCode;
	this.scheduleCode=scheduleCode;
	this.handler =new Handler();
}




   
   

   public void run() {
      String returnString = request();
      if(returnString.equals("success")){
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
         
      }else if(returnString.equals("fail")){
         handler.post(new Runnable() {
            @Override
            public void run() {
               //loginCheck = false;
               Toast.makeText(context, "삭제실패", Toast.LENGTH_SHORT).show();
            }
         });
      
      
      }
   }
    
   private String request(){
      String line = null;
      try{ 
         HttpClient client = new DefaultHttpClient();
         HttpPost httpPost = new HttpPost(DELETE_SINGLE_SCHEDULE_URL);
         List<NameValuePair> fields = new ArrayList<NameValuePair>();
         fields.add(new BasicNameValuePair("serviceRoute","1000"));
         fields.add(new BasicNameValuePair("singleScheduleMemberCode",memberCode ));
         fields.add(new BasicNameValuePair("singleScheduleCode", scheduleCode));
        
         
         httpPost.setEntity(new UrlEncodedFormEntity(fields));
         
         HttpResponse response = client.execute(httpPost);
         InputStream inStream = response.getEntity().getContent();
         BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
         
         //while(true){
         line = reader.readLine();
         
         
      }catch(IOException ioe){
         ioe.printStackTrace();
      }
      return line;
   }
   
   
   
}