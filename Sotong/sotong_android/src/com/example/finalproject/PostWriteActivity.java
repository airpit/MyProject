package com.example.finalproject;

import java.util.Date;

import WebServerThread.WebServerSendLetterThread;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PostWriteActivity extends ActionBarActivity {
	
	public Button postWriteReceiverBtn;
	public Button postWriteSendBtn;
	public CheckBox postWriteReservationCheck;
	public String[] familyNames;
	public ActionBarActivity actionBarActivity = this;
	
	private AlertDialog choiceDialog;
	private AlertDialog.Builder alert;
	
	private DatePickerDialog sendDateDialog;
	
	private Handler handler = new Handler();
	public TextView postWriteReceiverText;
	public TextView postWriteReservationTime;
	//태영추가
	public EditText postWriteTitleInputText;
	public EditText postWriteContentText;
	//
	public String memberCode;
	public String homeCode;
	
	//
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_write);
		postWriteTitleInputText=(EditText)findViewById(R.id.postWriteTitleInputText);
		postWriteContentText=(EditText)findViewById(R.id.postWriteContentText);
		postWriteReceiverText = (TextView)findViewById(R.id.postWriteReceiverText);
		postWriteReservationTime = (TextView)findViewById(R.id.postWriteReservationTime);
		familyNames = new String[]{"아빠","엄마","누나","동생"};
		memberCode="m1";
		boolean []familyNamesChecked = new boolean[familyNames.length];
		
		for(int cnt=0; cnt<familyNames.length; cnt++){
			familyNamesChecked[cnt] = true;
		}
		
		//dialog = new Dialog(this);
		//dialog.setContentView(R.layout.dialog_family_list);
		
		postWriteReceiverBtn = (Button)findViewById(R.id.postWriteReceiverBtn);
		
		postWriteReceiverBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				alert = new AlertDialog.Builder(actionBarActivity);
				choiceDialog = alert.setTitle(" 가족을 선택해주세요 ")
						.setIcon(R.drawable.ic_launcher).setSingleChoiceItems(familyNames, 0, null)
						.setPositiveButton("선택", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								ListView lw = ((AlertDialog)dialog).getListView();
								final Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
								
								handler.post(new Runnable() {
									public void run() {
										postWriteReceiverText.setText("받는 사람 : "+checkedItem.toString());
									}
								});
								
							}
						}).setNegativeButton("취소", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								Toast.makeText(getApplicationContext(), ""+which, Toast.LENGTH_SHORT).show();
								
							}
						}).show();
				
			}
		});
		
		
		postWriteReservationCheck = (CheckBox)findViewById(R.id.postWriteReservationCheck);
		postWriteReservationCheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
				if(isChecked){
					OnDateSetListener callBack = new OnDateSetListener() {
						public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
							postWriteReservationTime.setText("예약 전송 시간 : "+year+""+(monthOfYear+1)+""+dayOfMonth);
						}
					};
					Date date = new Date();
					sendDateDialog = new DatePickerDialog(actionBarActivity,callBack,date.getYear()+1900,date.getMonth(),date.getDate());
					sendDateDialog.show();
				}
			}
		});
		
		postWriteSendBtn = (Button)findViewById(R.id.postWriteSendBtn);
		postWriteSendBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				WebServerSendLetterThread sendThread=new WebServerSendLetterThread(PostWriteActivity.this,PostWriteActivity.this , "m1", "m4", postWriteTitleInputText.getText().toString(), postWriteContentText.getText().toString(), "images/profile/pic6", "em1", postWriteReservationTime.getText().toString());
				sendThread.start();
				//actionBarActivity.finish();
			}
		});
		
	}

	
}
