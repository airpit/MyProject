package com.example.finalproject;

import java.util.ArrayList;

import org.taptwo.android.widget.TitleFlowIndicator;
import org.taptwo.android.widget.ViewFlow;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

@SuppressLint("NewApi")
public class FragmentAlbum extends Fragment{
	
	private ViewFlow viewFlow;
	public Context context;
	
	public Button viewPicBtn;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_album, container, false);
		context = rootView.getContext();
		
		viewFlow = (ViewFlow)rootView.findViewById(R.id.viewflow_album);
		AlbumVersionAdapter adapter = new AlbumVersionAdapter(context);
		viewFlow.setAdapter(adapter,0);
		TitleFlowIndicator indicator = (TitleFlowIndicator)rootView.findViewById(R.id.viewflowindic_album);
		indicator.setTitleProvider(adapter);
		viewFlow.setFlowIndicator(indicator);
		/////////////////////////////////////
		
		final ArrayList<Integer> images = new ArrayList<Integer>();
		for(int cnt=1; cnt<5; cnt++){
			images.add(getResources().getIdentifier("cat"+cnt, "drawable", rootView.getContext().getPackageName()));
		}
		
		final AlbumDialog albumDialog = new AlbumDialog(rootView.getContext(),images);
		
		viewPicBtn = (Button)rootView.findViewById(R.id.viewPic);
		viewPicBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				albumDialog.show();
			}
		});
		
		
		/////////////////////////////////////
		
		
		return rootView;
	}
}
