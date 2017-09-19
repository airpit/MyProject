package com.example.finalproject;

import WebServerThread.WebServerGetSimpleLetterListThread;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentManager.OnBackStackChangedListener;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainMenuActivity extends ActionBarActivity {

	public ImageButton postBoxBtn;
	public ImageButton handsBtn;
	/*public ImageButton scheduleBtn;
	*/	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		final String homeCode=getIntent().getStringExtra("homeCode");
		postBoxBtn = (ImageButton)findViewById(R.id.postboxBtn);
		handsBtn = (ImageButton)findViewById(R.id.handsBtn);
		
		postBoxBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//태영 추가. 웹서버 연결 스레드 추가. 전달인자로 context, 멤버 코드(임의 데이터), 홈코드(임의데이터)
				WebServerGetSimpleLetterListThread letterThread=new WebServerGetSimpleLetterListThread(MainMenuActivity.this, MainMenuActivity.this, "m1",homeCode);
				letterThread.start();
				
				
				/*Intent intent = new Intent(getApplicationContext(),PostBoxActivity.class);
				startActivity(intent);*/
		
			}
		});
		/*scheduleBtn=(ImageButton)findViewById(R.id.scheduleBtn2);
		scheduleBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),ScheduleActivity.class);
				startActivity(intent);
			}
		});*/
		
		//View rootView = inflater.inflate(R.layout.fragment_home, container, false);
		//context = rootView.getContext();
		
		
		
		
	}

	@SuppressLint("NewApi")
	public void selectFrag(View view){
		FragmentManager fm = getFragmentManager();
		FragmentTransaction fragmentTransaction = fm.beginTransaction();
		Fragment fr=null;
		
		if(view == findViewById(R.id.familyStoryBtn)){
			fr = new FragmentFamily();
		}else if(view == findViewById(R.id.diaryBtn)){
			fr = new FragmentDiary();
		}else if(view == findViewById(R.id.albumBtn)){
			fr = new FragmentAlbum();
		}else if(view == findViewById(R.id.scheduleBtn)){
			fr = new FragmentSchedule();
		}
		else if(view == findViewById(R.id.FamscheduleBtn)){
			fr = new FragmentsFamSchedule();
			
		}else{
			fr = new FragmentHome();
		}
		
		//FragmentManager fm = getFragmentManager();
		//FragmentTransaction fragmentTransaction = fm.beginTransaction();
		fragmentTransaction.replace(R.id.fragment_place, fr);
		fragmentTransaction.commit();
		
	
		 
	}
	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	*/
}
