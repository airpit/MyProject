package com.example.finalproject;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class IndividualDetailDiaryActivity extends ActionBarActivity {

	public TextView individualDiaryDetailWriteDate;
	public TextView individualDiaryDetailTitle;
	public TextView individualDiaryDetailContent;
	
	public ImageButton individualDiaryDetailDeleteBtn;
	public ImageButton individualDiaryDetailModifyBtn;
	public ImageButton individualDiaryDetailWriteBtn;
	
	public Handler handler;
	public ActionBarActivity actionBarActivity = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_individual_detail_diary);
		
		handler = new Handler();
		Intent intent = getIntent();
		final String []value = intent.getStringArrayExtra("individualDiaryInfo");
		
		individualDiaryDetailDeleteBtn = (ImageButton)findViewById(R.id.individualDiaryDetailDeleteBtn);
		individualDiaryDetailModifyBtn = (ImageButton)findViewById(R.id.individualDiaryDetailModifyBtn);
		individualDiaryDetailWriteBtn = (ImageButton)findViewById(R.id.individualDiaryDetailWriteBtn);
		
		individualDiaryDetailDeleteBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder builder = new Builder(actionBarActivity);
				builder.setMessage("정말로 삭제하시겠습니까?");
				builder.setTitle("삭제");
				
				builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(actionBarActivity, "삭제합니다", Toast.LENGTH_SHORT).show();
					}
				});
				
				builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
				
				builder.create().show();
			}
		});
		
		individualDiaryDetailModifyBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(actionBarActivity.getApplicationContext(),IndividualDiaryModifyActivity.class);
				intent.putExtra("modifyInfo", value);
				startActivity(intent);
			}
		});
		
		
		individualDiaryDetailWriteDate = (TextView)findViewById(R.id.individualDiaryDetailWriteDate);
		individualDiaryDetailTitle = (TextView)findViewById(R.id.individualDiaryDetailTitle);
		individualDiaryDetailContent = (TextView)findViewById(R.id.individualDiaryDetailContent);
		
		handler.post(new Runnable() {
			public void run() {
				individualDiaryDetailWriteDate.setText("작성일자 : "+value[2]);
				individualDiaryDetailTitle.setText(value[0]);
				individualDiaryDetailContent.setText(value[1]);
				
			}
		});
	
		
	}

	
}
