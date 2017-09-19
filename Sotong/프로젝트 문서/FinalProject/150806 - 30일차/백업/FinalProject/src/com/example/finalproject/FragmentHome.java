package com.example.finalproject;

import org.taptwo.android.widget.ViewFlow;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;

@SuppressLint("NewApi")
public class FragmentHome extends Fragment {
	
	//백태영 추가
	private ViewFlow viewFlow;
	
	//
	public ImageButton postBoxBtn;
	public ImageButton handsBtn;
	public LinearLayout select1,select2,select3;
	public Context context;
	 
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_home, container, false);
		context=rootView.getContext();
		select1=(LinearLayout)rootView.findViewById(R.id.select1);
		select2=(LinearLayout)rootView.findViewById(R.id.select2);
		select3=(LinearLayout)rootView.findViewById(R.id.select3);
		OnClickListener listener=new OnClickListener() {
			
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(context.getApplicationContext(),DetailInfoActivity.class);
				startActivity(intent);
			}
		};
		select1.setOnClickListener(listener);
		select2.setOnClickListener(listener);
		select3.setOnClickListener(listener);
		return rootView;

		
	}
}
