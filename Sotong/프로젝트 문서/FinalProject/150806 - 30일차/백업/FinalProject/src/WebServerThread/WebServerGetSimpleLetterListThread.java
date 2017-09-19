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
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.finalproject.PostBoxActivity;

public class WebServerGetSimpleLetterListThread extends Thread{

	   private static final String GET_SIMPLE_LETTER_LIST_URL = "http://192.168.0.35:8089/Server/letter.do";
	   
	    //철연 : http://192.168.0.110:8089/so-tong/login.do;
	   //"user-id"   , "user-pw"
	   private ActionBarActivity actionBarActivity;
	   private Context context;
	   private String memberCode;
	   private String homeCode;
	   //private ListView lv;
	   	
	   
	   private Handler handler;
	 

	 

	public WebServerGetSimpleLetterListThread(ActionBarActivity actionBarActivity,
			Context context, String memberCode, String homeCode) {
		this.actionBarActivity = actionBarActivity;
		this.context = context;
		this.memberCode = memberCode;
		this.homeCode=homeCode;
		this.handler=new Handler();
	}

	public void run() {
	     ArrayList<String[]> list=request();
	      if(list!=null){
	      //   handler.post(new Runnable() {
	      //      public void run() {
	               //loginCheck = true;
	              Intent intent=new Intent();
	              Bundle bundle=new Bundle();
	              for(int i=0;i<list.size();i++)
	              {
	            	  bundle.putStringArray("simpleLetterInfo"+(i+1),list.get(i));
	              }
	              intent.putExtra("letterCount", list.size());
	              intent.putExtra("SimpleLetterInfos", bundle);
	              intent.setClass(context,PostBoxActivity.class);
	              context.startActivity(intent);
	              
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
	               Toast.makeText(context, "편지 목록이 없습니다.", Toast.LENGTH_SHORT).show();
	            }
	         });
	      
	      
	      }
	   }
	    
	   private ArrayList<String[]> request(){
	      String line = null;
	      ArrayList<String[]> list=new ArrayList<String[]>();
	      try{ 
	         HttpClient client = new DefaultHttpClient();
	         HttpPost httpPost = new HttpPost(GET_SIMPLE_LETTER_LIST_URL);
	         List<NameValuePair> fields = new ArrayList<NameValuePair>();
	         fields.add(new BasicNameValuePair("serviceRoute","1000"));
	         fields.add(new BasicNameValuePair("memberCode",memberCode));

	         httpPost.setEntity(new UrlEncodedFormEntity(fields));
	         
	         HttpResponse response = client.execute(httpPost);
	         InputStream inStream = response.getEntity().getContent();
	         BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
	         
	         while(true){
	         line = reader.readLine();
	         //Log.v("@@@test",line);
	         if(line==null){
	        	 break;
	         }
	         else
	         {
	        	 if(line.startsWith("200"))
	        	 {
	        		StringTokenizer st=new StringTokenizer(line,"|");
	        		String[] simpleLetterList=new String[4];
	        		String code=st.nextToken();
	        		while(st.hasMoreTokens())
	        		{
	        			simpleLetterList[0]=st.nextToken();//편지제목
	        			simpleLetterList[1]=st.nextToken();//발신날짜
	        			simpleLetterList[2]=st.nextToken();//발신자명
	        			simpleLetterList[3]=st.nextToken();//편지코드
	        			list.add(simpleLetterList);
	        		}
	        	 }
	        	
	        	 else if(line.startsWith("500"))
	        	 {
	        		 Toast.makeText(context, "편지를 읽어올수 없음", Toast.LENGTH_SHORT).show();
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

