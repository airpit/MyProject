package com.example.finalproject;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddSingleScActivity extends ActionBarActivity {
	Button saveBtn;
	EditText title,place,memo;
	TextView startDate,startTime,endDate,endTime,alarmTime,alarmTime2,loopCycle;
	CheckBox scheduleCheck,alarmCheck, familyAlarmCheck,loopCheck;
	DatePickerDialog dpd;
	TimePickerDialog tpd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_sc_single);
		saveBtn=(Button)findViewById(R.id.singleScAddButton);
		
		title=(EditText)findViewById(R.id.singleScAddTitle);
		place=(EditText)findViewById(R.id.singleScAddPlace);
		memo=(EditText)findViewById(R.id.singleScAddMemo);
		
		startDate=(TextView)findViewById(R.id.singleScAddStartDate);
		startTime=(TextView)findViewById(R.id.singleScAddStartTime);
		endDate=(TextView)findViewById(R.id.singleScAddEndDate);
		endTime=(TextView)findViewById(R.id.singleScAddEndTime);
		alarmTime=(TextView)findViewById(R.id.singleScAddAlarmTime);
		loopCycle=(TextView)findViewById(R.id.singleScAddLoop);
		
		scheduleCheck=(CheckBox)findViewById(R.id.singleScAddCheck1);
		alarmCheck=(CheckBox)findViewById(R.id.singleScAddCheck2);
		familyAlarmCheck=(CheckBox)findViewById(R.id.singleScAddCheck3);
		loopCheck=(CheckBox)findViewById(R.id.singleScAddCheck4);
	
		saveBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String sTitle=title.getText().toString();
				String sPlace=place.getText().toString();
				String sMemo=memo.getText().toString();
				
				String sStartDate=startDate.getText().toString();
				String sStartTime=startTime.getText().toString();
				String sEndDate=endDate.getText().toString();
				String sEndTime=endTime.getText().toString();
				String sAlarmTime=alarmTime.getText().toString();
				String sLoopCycle=loopCycle.getText().toString();
				//WebServerAddSingleScheduleThread thread=new WebServerAddSingleScheduleThread(AddSingleScActivity.this,getIntent().getExtras().getString(""),sTitle,sPlace,sMemo,sStartDate,sStartTime,sEndDate,sEndTime,sAlarmTime,sLoopCycle);
				Intent intent=new Intent();
				
				intent.putExtra("title", sTitle);
				intent.putExtra("place", sPlace);
				intent.putExtra("memo",sMemo);
				intent.putExtra("startDate",sStartDate);
				intent.putExtra("startTime",sStartTime);
				intent.putExtra("endDate",sEndDate);
				intent.putExtra("alarmTime",sAlarmTime);
				intent.putExtra("loopCycle",sLoopCycle);
				setResult(RESULT_OK,intent);
				finish();
			}
		});
		
		
		
		
	
			
			
			String sTitle,sPlace,sContent;
			 final String sTime;
			String eTime, aTime, aTime2;
			int sYear,sMonth,sDay,sHour,sMin,eYear,eMonth,eDay,eHour,eMin;
			
		
			
			
			
			
			final AlertDialog.Builder dialog=new AlertDialog.Builder(this);//알람시간 설정 다이얼로그
			final String items[]={"2 시간 전","1 시간 전","30 분 전"};

			
			
			alarmCheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					
				}
			});
			/////////일정 알람 시간 설정. 라디오버튼 리스너 연결///////////
			alarmCheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					if(isChecked)
					{
						dialog.setTitle("알림시간 선택")
						.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener()
						{
							@Override
							public void onClick(DialogInterface dialog, int which)
							{
								alarmTime2.setText(" ");
								alarmTime.setText(items[which]);
									

							}

						})
						.setNegativeButton("취소", new DialogInterface.OnClickListener()
						{
							@Override
							public void onClick(DialogInterface dialog, int which)
							{
								dialog.dismiss();
							}

						})
						.setPositiveButton("확인", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Toast.makeText(AddSingleScActivity.this, "등록확인",Toast.LENGTH_LONG).show();
							}
						})
						.show();
					}
					else
					{
						alarmTime.setText(" ");
					}
				}
			});
			familyAlarmCheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					if(isChecked)
					{
						dialog.setTitle("알림시간 선택")
						.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener()
						{
							@Override
							public void onClick(DialogInterface dialog, int which)
							{
								alarmTime.setText(" ");
								alarmTime2.setText(items[which]);
									

							}

						})
						.setNegativeButton("취소", new DialogInterface.OnClickListener()
						{
							@Override
							public void onClick(DialogInterface dialog, int which)
							{
								dialog.dismiss();
							}

						})
						.setPositiveButton("확인", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Toast.makeText(AddSingleScActivity.this, "등록확인",Toast.LENGTH_LONG).show();
							}
						})
						.show();
					}
					else
					{
						alarmTime2.setText(" ");
					}
				}
			});
			
			//
			
				
			/////////일정 하루종일 반복할 것인지 체크박스. 리스너 연결./////////////
				scheduleCheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					if(isChecked)
					{
						Date date=new Date();
						SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
						startDate.setText(sf.format(date));
						endDate.setText(sf.format(date));
						startTime.setText("00:01");
						endTime.setText("23:59");
					}
					else
					{
						Date date=new Date();
						SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
											
						startDate.setText(sf.format(date));
						endDate.setText(sf.format(date));
						sf=new SimpleDateFormat("HH:mm");
						startTime.setText(sf.format(date));
						endTime.setText(sf.format(date));
						
					}
						
				}
			});
			//
			
			sTitle=title.getText().toString().trim();
			sPlace=place.getText().toString().trim();
			sContent=memo.getText().toString().trim();
			
		
			//////////시작날짜 등록. 리스너 연결///////////
			startDate.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					OnDateSetListener os=new OnDateSetListener() {
					
					
						@Override
						public void onDateSet(DatePicker view, int year, int monthOfYear,
								int dayOfMonth) {
							// TODO Auto-generated method stub
							
								
							startDate.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
							Toast.makeText(AddSingleScActivity.this, "시작날짜등록", Toast.LENGTH_LONG).show();
							}
						
					};
					Date date=new Date();
					dpd=new DatePickerDialog(AddSingleScActivity.this,os, date.getYear()+1900, date.getMonth(),date.getDay());
					dpd.show();
				}
			});
			//
			
			////////////종료날짜 등록.///////////
			endDate.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					OnDateSetListener os=new OnDateSetListener() {
						
						@Override
						public void onDateSet(DatePicker view, int year, int monthOfYear,
								int dayOfMonth) {
							// TODO Auto-generated method stub
							endDate.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
							//Toast.makeText(getApplicationContext(), "종료날짜등록", Toast.LENGTH_LONG).show();
							}
						
					};
					Date date=new Date();
					dpd=new DatePickerDialog(AddSingleScActivity.this,os, date.getYear()+1900, date.getMonth(),date.getDay());
					dpd.show();
				}
			});
			
			
			////////////////시작 날짜의 일정 시작 시간 입력.///////////////
			startTime.setOnClickListener(new OnClickListener() 
			{		
				
			
				@Override
				public void onClick(View v){
					// TODO Auto-generated method stub
					OnTimeSetListener callBack=new OnTimeSetListener() 
					{		
						
						@Override
						public void onTimeSet(TimePicker view, int hourOfDay, int minute) 
						{// TODO Auto-generated method stub
							startTime.setText(hourOfDay+":"+minute);
							Toast.makeText(AddSingleScActivity.this, "시작시간등록", Toast.LENGTH_LONG).show();
						}
						
					};
					Date date=new Date();
					
					tpd=new TimePickerDialog(AddSingleScActivity.this, callBack, date.getHours(), date.getMinutes(),true);
					tpd.show();
				}
				
			});
			
			//////////////종료 날짜의 일정 종료 시간 입력////////////
			endTime.setOnClickListener(new OnClickListener() 
			{		
				
			
				@Override
				public void onClick(View v){
					// TODO Auto-generated method stub
					OnTimeSetListener callBack=new OnTimeSetListener() 
					{		
						
						@Override
						public void onTimeSet(TimePicker view, int hourOfDay, int minute) 
						{// TODO Auto-generated method stub
							endTime.setText(hourOfDay+":"+minute);
							Toast.makeText(AddSingleScActivity.this, "종료시간등록", Toast.LENGTH_LONG).show();
						}
						
					};
					Date date=new Date();
					
					tpd=new TimePickerDialog(AddSingleScActivity.this, callBack, date.getHours(), date.getMinutes(),true);
					tpd.show();
				}
				
			});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_sc_single, menu);
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
}
