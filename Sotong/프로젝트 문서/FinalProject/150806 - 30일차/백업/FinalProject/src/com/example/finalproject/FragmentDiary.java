package com.example.finalproject;

import java.util.ArrayList;
import java.util.Date;

import org.taptwo.android.widget.TitleFlowIndicator;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.finalproject.model.IndividualDiaryInfo;
import com.example.finalproject.model.SimpleIndividualDiaryInfo;

@SuppressLint("NewApi")
public class FragmentDiary extends Fragment{

	private ViewFlow viewFlow;
	public Context context;
	
	public ArrayList<SimpleIndividualDiaryInfo> data = null;
	public ArrayList<IndividualDiaryInfo> detailData = null;
	public ListView listView = null;
	public SimpleDiaryInfoListAdapter simpleDiaryInfoListAdapter = null;
	
	public ImageButton individualDiaryWriteBtn;
	
	// 가족 다이어리쪽 변수
	
	public ArrayList<String> familyData = null;
	public String []familyListData;
	
	public ListView familyListView = null;
	public ArrayAdapter<String> simpleFamilyInfoListAdapter;
	public ImageButton familyDiaryWriteBtn;
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_diary, container, false);
		context = rootView.getContext();
		
		viewFlow = (ViewFlow)rootView.findViewById(R.id.viewflow_diary);
		DiaryVersionAdapter adapter = new DiaryVersionAdapter(context);
		viewFlow.setAdapter(adapter, 0);
		TitleFlowIndicator indicator = (TitleFlowIndicator)rootView.findViewById(R.id.viewflowindic_diary);
		indicator.setTitleProvider(adapter);
		viewFlow.setFlowIndicator(indicator);
		
		
		data = new ArrayList<SimpleIndividualDiaryInfo>();
		data.add(new SimpleIndividualDiaryInfo("맛있는 것", new Date(2015,7,23)));
		data.add(new SimpleIndividualDiaryInfo("학교", new Date(2015,7,22)));
		data.add(new SimpleIndividualDiaryInfo("행복이란", new Date(2015,7,21)));
		
		simpleDiaryInfoListAdapter = new SimpleDiaryInfoListAdapter(getActivity(),data);
		listView = (ListView)rootView.findViewById(R.id.individualDiaryListView);
		listView.setAdapter(simpleDiaryInfoListAdapter);
		
		detailData = new ArrayList<IndividualDiaryInfo>();
		detailData.add(new IndividualDiaryInfo("맛있는 것", "세상에는 참 맛있는 게 많다", new Date(2015,7,23)));
		detailData.add(new IndividualDiaryInfo("학교", "학교로 돌아가고 싶다\n그곳에는 내 삶이 있지", new Date(2015,7,22)));
		detailData.add(new IndividualDiaryInfo("행복이란", "행복하고 싶다", new Date(2015,7,21)));
		 
		listView.setOnItemClickListener(new OnItemClickListener() {
			
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(context.getApplicationContext(),IndividualDetailDiaryActivity.class);
				 
				String value[] = new String[3];
				value[0] = detailData.get(position).getIndiDiaryTitle();
				value[1] = detailData.get(position).getIndiDiaryContent();
				value[2] = detailData.get(position).getIndiDiaryDateToString();
				intent.putExtra("individualDiaryInfo", value);
				
				startActivity(intent);
				
			}
		});
		
		individualDiaryWriteBtn = (ImageButton)rootView.findViewById(R.id.individualDiaryWriteBtn);
		individualDiaryWriteBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(context.getApplicationContext(),IndividualDiaryWriteActivity.class);
				startActivity(intent);
			}
		});
		
		// 가족 다이어리 시작
//		familyData = new ArrayList<String>();
//		familyData.add("2015-07-23");
//		familyData.add("2015-07-22");
//		familyData.add("2015-07-21");
		
		familyListData = new String[3];
		familyListData[0] = "2015-07-23";
		familyListData[1] = "2015-07-22";
		familyListData[2] = "2015-07-21";
		
		familyListView = (ListView)rootView.findViewById(R.id.familyDiaryListView);
		simpleFamilyInfoListAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, familyListData);
																			
		familyListView.setAdapter(simpleFamilyInfoListAdapter);
																	
		familyListView.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(context.getApplicationContext(), FamilyDetailDiaryActivity.class);
				startActivity(intent);
			}
		});
		
		/*
		 listView.setOnItemClickListener(new OnItemClickListener() {
			
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(context.getApplicationContext(),IndividualDetailDiaryActivity.class);
				 
				String value[] = new String[3];
				value[0] = detailData.get(position).getIndiDiaryTitle();
				value[1] = detailData.get(position).getIndiDiaryContent();
				value[2] = detailData.get(position).getIndiDiaryDateToString();
				intent.putExtra("individualDiaryInfo", value);
				
				startActivity(intent);
				
			}
		}); 
		  */
		
		return rootView;
	}
}
