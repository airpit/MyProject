package WebServerThread;

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
import android.widget.Toast;

import com.example.finalproject.MainMenuActivity;

public class WebServerConnectThread extends Thread {
   //private static final String SERVER_URL ="http://192.168.0.20:8089/AndroidHomeworkServer/ServletLogin";
   
   private static final String LOGIN_URL = "http://192.168.0.110:8089/so-tong/login.do";
   
    //철연 : http://192.168.0.110:8089/so-tong/login.do;
   //"user-id"   , "user-pw"
    
   private Context context;
   private String userId;
   private String userPassword;
   //private boolean loginCheck;
   
   private Handler handler;
   
   public WebServerConnectThread(Context context, String userId, String userPassword) {
      // TODO Auto-generated constructor stub
      this.context = context;
      this.userId = userId;
      this.userPassword = userPassword;
      //this.loginCheck = false;
      this.handler = new Handler();
   }
   
   

   public void run() {
      String returnString = request();
      if(returnString.equals("success")){
      //   handler.post(new Runnable() {
      //      public void run() {
               //loginCheck = true;
               Intent loginIntent = new Intent();
               loginIntent.setClass(context, MainMenuActivity.class);
               context.startActivity(loginIntent);
               
               /*
                Intent userAndFriendsIntent = new Intent();
                userAndFriendsIntent.setClass(context, 

MemberActivity.class);
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
               Toast.makeText(context, "아이디 혹은 비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
            }
         });
      
      
      }
   }
    
   private String request(){
      String line = null;
      try{ 
         HttpClient client = new DefaultHttpClient();
         HttpPost httpPost = new HttpPost(LOGIN_URL);
         List<NameValuePair> fields = new ArrayList<NameValuePair>();
         fields.add(new BasicNameValuePair("serviceRoute","1000"));
         fields.add(new BasicNameValuePair("user-id", userId));
         fields.add(new BasicNameValuePair("user-pw", userPassword));
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