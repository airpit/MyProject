package com.example.finalproject;

import org.taptwo.android.widget.TitleFlowIndicator;
import org.taptwo.android.widget.ViewFlow;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

@SuppressLint("NewApi")
public class FragmentFamily extends Fragment{
	
	private ViewFlow viewFlow;
	public Context context;
	
	public Button storyWriteBtn;
	public Button storyModifyBtn;
	public Button storyDeleteBtn;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_family, container, false);
		context = rootView.getContext();

		viewFlow = (ViewFlow)rootView.findViewById(R.id.viewflow);
		AndroidVersionAdapter adapter = new AndroidVersionAdapter(context);
		viewFlow.setAdapter(adapter, 3);
		TitleFlowIndicator indicator = (TitleFlowIndicator) rootView.findViewById(R.id.viewflowindic);
		indicator.setTitleProvider(adapter);
		viewFlow.setFlowIndicator(indicator);
	
		storyWriteBtn = (Button)rootView.findViewById(R.id.storyWriteBtn);
		storyModifyBtn = (Button)rootView.findViewById(R.id.storyModifyBtn);
		storyDeleteBtn = (Button)rootView.findViewById(R.id.storyDeleteBtn);
		storyWriteBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(context.getApplicationContext(),StoryWriteActivity.class);
				startActivity(intent);
			}
		});
		
		storyModifyBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(context.getApplicationContext(),StoryModifyActivity.class);
				startActivity(intent);
			}
		});
		
		storyDeleteBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder builder = new Builder(context);
				builder.setMessage("정말로 삭제하시겠습니까?");
				builder.setTitle("삭제");
				
				builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(context, "삭제합니다", Toast.LENGTH_SHORT).show();
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
		
		return rootView;
		
	}

}
