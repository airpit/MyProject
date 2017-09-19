package com.example.finalproject;

import org.taptwo.android.widget.TitleProvider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class AlbumVersionAdapter extends BaseAdapter implements TitleProvider{
	private LayoutInflater mInflater;
	private static final String[] versions = {"1.5","1.6","2.1","2.2","2.3","3.0","x.y" };
	private static final String[] names = {"捞具扁举裹","老扁举裹"};
	
	public AlbumVersionAdapter(Context context) {
		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public int getCount() {	
		return names.length;
	}
	public Object getItem(int position) {
		return position;
	}	
	public long getItemId(int position) {
		return position;
	}
	public String getTitle(int position) {
		
		return names[position];
	}
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView == null){
			
			convertView = mInflater.inflate(R.layout.story_album, null);
			
			if(position == 0){
				
			}else if(position == 1){
				
			}
			
			
			/*
			if(position == 0){
				convertView = mInflater.inflate(R.layout.story_album, null);
			}else if(position == 1){
				convertView = mInflater.inflate(R.layout.diary_album, null);
			}
			*/
		}
		
		return convertView;
	}
	
	
	
}
