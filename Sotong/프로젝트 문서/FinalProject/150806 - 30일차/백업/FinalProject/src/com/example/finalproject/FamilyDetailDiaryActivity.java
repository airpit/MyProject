package com.example.finalproject;

import java.util.ArrayList;
import java.util.Date;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import com.example.finalproject.model.FamilyDiaryDetail;

public class FamilyDetailDiaryActivity extends ActionBarActivity {

	
	public ListView familyDetailDiaryListView;
	public FamilyDetailVersionAdapter adapter;
	
	
	public ArrayList<FamilyDiaryDetail> data = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_family_detail_diary);
		
		familyDetailDiaryListView = (ListView)findViewById(R.id.familyDetailDiaryListView);
		data = new ArrayList<FamilyDiaryDetail>();
		data.add(new FamilyDiaryDetail("���翵", "@drawable/brad", "���� ��ſ� �Ļ翴���ϴ�\n�ȳ����ֹ�����", new Date(2015,7,23)));
		data.add(new FamilyDiaryDetail("�̰��", "@drawable/brad", "���� ��ſ� �����̿����ϴ�\n�ȳ����ֹ�����", new Date(2015,7,22)));
		data.add(new FamilyDiaryDetail("��ö��", "@drawable/brad", "���õ� ��ſ� �Ϸ翴���ϴ�\n�ȳ����ֹ�����", new Date(2015,7,22)));
		
		adapter = new FamilyDetailVersionAdapter(FamilyDetailDiaryActivity.this, data);
		familyDetailDiaryListView = (ListView)findViewById(R.id.familyDetailDiaryListView);
		familyDetailDiaryListView.setAdapter(adapter);
		
		
	}

	
}
