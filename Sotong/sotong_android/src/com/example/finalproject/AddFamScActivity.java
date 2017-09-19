package com.example.finalproject;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class AddFamScActivity extends Activity {
	Button save;
	EditText title,place,memo;
	TextView startDate,endDate,startTime,endTime,alarmTime,loopCycle,event;
	CheckBox dayCheck,alarmCheck,loopCheck,eventCheck;
	DatePickerDialog dpd;
	TimePickerDialog tpd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_fam_sc);
		save=(Button)findViewById(R.id.famSave);
		//EditText
		title=(EditText)findViewById(R.id.famAddTitle);
		place=(EditText)findViewById(R.id.famAddPlace);
		memo=(EditText)findViewById(R.id.famAddContent);
		//TextView
		startDate=(TextView)findViewById(R.id.famAddStartDate);
		endDate=(TextView)findViewById(R.id.famAddEndDate);
		startTime=(TextView)findViewById(R.id.famAddStartTime);
		endTime=(TextView)findViewById(R.id.famAddEndTime);
		alarmTime=(TextView)findViewById(R.id.famAddAlarm);
		loopCycle=(TextView)findViewById(R.id.famAddLoop);
		event=(TextView)findViewById(R.id.famAddEvent);
		//CheckBox
		dayCheck=(CheckBox)findViewById(R.id.famAddCheck1);
		alarmCheck=(CheckBox)findViewById(R.id.famAddCheck2);
		loopCheck=(CheckBox)findViewById(R.id.famAddCheck3);
		eventCheck=(CheckBox)findViewById(R.id.famAddCheck4);
		//dialog
		
		final AlertDialog.Builder alarmTimeDialog=new AlertDialog.Builder(this);
		final AlertDialog.Builder loopCycleDialog=new AlertDialog.Builder(this);
		final String items[]={"2 시간 전","1 시간 전","30 분 전"};
		final String items2[]={"매주","매월"};
		
		
		eventCheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{
					AlertDialog.Builder alert = new AlertDialog.Builder(AddFamScActivity.this);

					alert.setTitle("가족에게 보낼 질문 입력");
					

					// Set an EditText view to get user input
					final EditText input = new EditText(AddFamScActivity.this);
					alert.setView(input);

					alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {
							String value = input.getText().toString();
							event.setText(value);
							value.toString();
							// Do something with value!
						}
					});


					alert.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int whichButton) {
									// Canceled.
								}
							});
					
					alert.show(); 
				}
				else 
					event.setText(" ");
				
			}
		});
		alarmCheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{
					alarmTimeDialog.setTitle("알림 시간 선택")
					
					.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							
							alarmTime.setText(items[which]);
	
						}

					})
					.setNegativeButton("취소", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							alarmTime.setText(" ");
							dialog.dismiss();
						}

					})
					.setPositiveButton("확인", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Toast.makeText(AddFamScActivity.this, "등록확인",Toast.LENGTH_LONG).show();
						}
					})
					.show();
				}
				
			}
		});
		//일정 하루종일 등록
		dayCheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
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
		
		//시작날짜 등록
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
						Toast.makeText(getApplicationContext(), "시작날짜등록", Toast.LENGTH_LONG).show();
						}
					
				};
				Date date=new Date();
				dpd=new DatePickerDialog(AddFamScActivity.this,os, date.getYear()+1900, date.getMonth(),date.getDay());
				dpd.show();
			}
		});
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
				dpd=new DatePickerDialog(AddFamScActivity.this,os, date.getYear()+1900, date.getMonth(),date.getDay());
				dpd.show();
			}
		});
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
						Toast.makeText(AddFamScActivity.this, "시작시간등록", Toast.LENGTH_LONG).show();
					}
					
				};
				Date date=new Date();
				
				tpd=new TimePickerDialog(AddFamScActivity.this, callBack, date.getHours(), date.getMinutes(),true);
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
						Toast.makeText(AddFamScActivity.this, "종료시간등록", Toast.LENGTH_LONG).show();
					}
					
				};
				Date date=new Date();
				
				tpd=new TimePickerDialog(AddFamScActivity.this, callBack, date.getHours(), date.getMinutes(),true);
				tpd.show();
			}
			
		});
		loopCheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{
					loopCycleDialog.setTitle("반복 주기 설정")
					.setSingleChoiceItems(items2,0,new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							loopCycle.setText(items2[which]);
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
							Toast.makeText(AddFamScActivity.this, "반복등록확인",Toast.LENGTH_LONG).show();
						}
					})
					.show();
				}
				else
				{
					loopCycle.setText(" ");
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_fam_sc, menu);
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
