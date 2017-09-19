package com.example.finalproject;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class StoryWriteActivity extends ActionBarActivity {

	public Button storyWriteRegisterBtn;
	public Button storyWriteCancelBtn;
	
	public ImageButton storyWriteImoticonBtn;
	public ImageButton storyWriteAlbumBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_story_write);
		
		storyWriteRegisterBtn = (Button)findViewById(R.id.storyWriteRegisterBtn);
		storyWriteCancelBtn = (Button)findViewById(R.id.storyWriteCancelBtn);
		storyWriteCancelBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		
		
		storyWriteImoticonBtn = (ImageButton)findViewById(R.id.storyWriteImoticonBtn);
		storyWriteAlbumBtn = (ImageButton)findViewById(R.id.storyWriteAlbumBtn);
		
		storyWriteAlbumBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
			}
		});
		
		
	}

	
}
