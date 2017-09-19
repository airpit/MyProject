package com.example.finalproject;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalproject.model.FamilyDiaryDetail;

public class FamilyDetailVersionAdapter extends BaseAdapter{
	public Context context;
	public ArrayList<FamilyDiaryDetail> data;
	public LayoutInflater layoutInflater;
	
	public FamilyDetailVersionAdapter() {
		super();
	}

	public FamilyDetailVersionAdapter(Context context, ArrayList<FamilyDiaryDetail> data) {
		super();
		this.context = context;
		this.data = data;
		this.layoutInflater = LayoutInflater.from(this.context);
	}
	
	public int getCount() {
		return data.size();
	}
	public Object getItem(int position) {
	
		return data.get(position);
	}
	public long getItemId(int position) {
		return position;
	}
	public View getView(int position, View convertView, ViewGroup parent) {
		View itemLayout = layoutInflater.inflate(R.layout.family_diary_form, null);
		
		ImageView familyDetailDiaryPicture = (ImageView)itemLayout.findViewById(R.id.familyDetailDiaryPicture);
		TextView familyDetailDiaryName = (TextView)itemLayout.findViewById(R.id.familyDetailDiaryName);
		TextView familyDetailDiaryContent = (TextView)itemLayout.findViewById(R.id.familyDetailDiaryContent);
		
		familyDetailDiaryPicture.setImageResource(R.drawable.brad);
		familyDetailDiaryName.setText(data.get(position).getName());
		familyDetailDiaryContent.setText(data.get(position).getContent());
		
		//View itemLayout2 = layoutInflater
		
		return itemLayout;
	}
	

}
