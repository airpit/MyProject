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
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.example.finalproject.PostWriteActivity;

public class WebServerSendLetterThread extends Thread{

	   private static final String GET_SIMPLE_LETTER_LIST_URL = "http://192.168.0.35:8089/Server/letter_write.do";
	   
	    //철연 : http://192.168.0.110:8089/so-tong/login.do;
	   //"user-id"   , "user-pw"
	   private ActionBarActivity actionBarActivity;
	   private Context context;
	   private String senderCode;
	   private String receiverCode;
	   private String title;
	   private String contents;
	   private String imageName;
	   private String emoticonCode;
	   private String letterWrittenDate;
	   private Handler handler;
	 

	 

	

	public WebServerSendLetterThread(ActionBarActivity actionBarActivity,
			Context context, String senderCode, String receiverCode,
			String title, String contents, String imageName,
			String emoticonCode, String letterWrittenDate) {
		this.actionBarActivity = actionBarActivity;
		this.context = context;
		this.senderCode = senderCode;
		this.receiverCode = receiverCode;
		this.title = title;
		this.contents = contents;
		this.imageName = imageName;
		this.emoticonCode = emoticonCode;
		this.letterWrittenDate = letterWrittenDate;
		this.handler=new Handler();
		
	}

	public void run() {
	     int res=request();
	      if(res!=-1){
	      
		         handler.post(new Runnable() {
		            @Override
		            public void run(){
		               //loginCheck = false;
		            	
		            	((PostWriteActivity)context).finish();
		      
			            }
		         });
		      
		      
				
	              
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
	    	  Handler handle=new Handler(Looper.myLooper());
	    	  
	         handler.post(new Runnable() {
	            @Override
	            public void run() {
	               //loginCheck = false;
	            	Toast.makeText(context.getApplicationContext(), "편지 전송실패", Toast.LENGTH_SHORT).show();
		            }
	         });
	      
	      
	      }
	   }
	    
	   private int request(){
	      String line = null;
	      int res=0;
	      try{ 
	         HttpClient client = new DefaultHttpClient();
	         HttpPost httpPost = new HttpPost(GET_SIMPLE_LETTER_LIST_URL);
	         List<NameValuePair> fields = new ArrayList<NameValuePair>();
	         fields.add(new BasicNameValuePair("serviceRoute","1000"));
	         fields.add(new BasicNameValuePair("senderCode",senderCode));
	         fields.add(new BasicNameValuePair("receiverCode",receiverCode));
	         fields.add(new BasicNameValuePair("title",title));
	         fields.add(new BasicNameValuePair("contents",contents));
	         fields.add(new BasicNameValuePair("imageName",imageName));
	         fields.add(new BasicNameValuePair("emoticonCode",emoticonCode));
	         fields.add(new BasicNameValuePair("letterWrittenDate",letterWrittenDate));
	         
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

	  	           
	        	 }
	        	
	        	 else if(line.startsWith("500"))
	        	 {
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

