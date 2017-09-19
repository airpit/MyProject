package com.example.finalproject;

import java.util.Date;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class IndividualDiaryWriteActivity extends ActionBarActivity {

	public TextView individualDiaryWriteWriteDate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_individual_diary_write);
		
		Date today = new Date();
		String year = new String(""+(today.getYear()-2130));
		String todayString = new String(year.substring(1)+"-"+(today.getMonth()+1)+"-"+today.getDate());
		
		individualDiaryWriteWriteDate = (TextView)findViewById(R.id.individualDiaryWriteWriteDate);
		individualDiaryWriteWriteDate.setText("작성일자 : "+todayString);
		
	
	}

	
}
