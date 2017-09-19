package com.example.finalproject;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StoryModifyActivity extends ActionBarActivity {

	public Button storyModifyCancelBtn;
	public Button storyModifyCompleteBtn;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_story_modify);
		
		storyModifyCancelBtn = (Button)findViewById(R.id.storyModifyCancelBtn);
		storyModifyCompleteBtn = (Button)findViewById(R.id.storyModifyCompleteBtn);
		
		storyModifyCancelBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		
	}

	
}
