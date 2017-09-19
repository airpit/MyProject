package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;
import android.widget.TextView;

public class IndividualDiaryModifyActivity extends ActionBarActivity {

	public TextView individualDiaryModifyWriteDate;
	public EditText individualDiaryModifyTitle;
	public EditText individualDiaryModifyContent;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_individual_diary_modify);
		
		Intent intent = getIntent();
		String []modifyValue = intent.getStringArrayExtra("modifyInfo");
		
		
		
		individualDiaryModifyWriteDate = (TextView)findViewById(R.id.individualDiaryModifyWriteDate);
		individualDiaryModifyTitle = (EditText)findViewById(R.id.individualDiaryModifyTitle);
		individualDiaryModifyContent = (EditText)findViewById(R.id.individualDiaryModifyContent);
		
		individualDiaryModifyWriteDate.setText("작성일자 : "+modifyValue[2]);
		individualDiaryModifyTitle.setText(modifyValue[0]);
		individualDiaryModifyContent.setText(modifyValue[1]);
		
	}

	
}
