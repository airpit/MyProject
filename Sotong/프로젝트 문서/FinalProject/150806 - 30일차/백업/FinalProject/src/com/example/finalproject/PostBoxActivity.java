package com.example.finalproject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import WebServerThread.WebServerGetDetailLetterInfoThread;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.finalproject.model.PostInfo;
import com.example.finalproject.model.SimplePostInfo;

public class PostBoxActivity extends ActionBarActivity {

	public ArrayList<SimplePostInfo> data = null;
	public ArrayList<PostInfo> detailData = null;
	public ListView listView = null;
	public SimplePostInfoListAdapter adapter = null;
	public ActionBarActivity postBoxActivity = this;

	public ImageButton postBoxWriteBtn;
	public ImageButton postBoxDeleteBtn;

	public ActionBarActivity actionBarActivity = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_box);

		Intent intent = getIntent();
		final Bundle bundle = intent.getBundleExtra("SimpleLetterInfos");
		final int letterCount = intent.getIntExtra("letterCount", 0);

		// String[] letterInfo=bundle.getStringArray("simpleLetterInfo1");
		/*
		 * Log.v("myTag", "����� ������ �����°� "+letterInfo[0]); Log.v("myTag",
		 * "����� ������ �����°� "+letterInfo[1]); Log.v("myTag",
		 * "����� ������ �����°� "+letterInfo[2]); Log.v("myTag",
		 * "����� ������ �����°� "+letterInfo[3]);
		 */
		postBoxWriteBtn = (ImageButton) findViewById(R.id.postBoxWriteButton);
		postBoxDeleteBtn = (ImageButton) findViewById(R.id.postBoxDeleteButton);
		postBoxWriteBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(actionBarActivity
						.getApplicationContext(), PostWriteActivity.class);
				startActivity(intent);

				// Intent intent = new Intent(context,PostBoxActivity.class);
				// startActivity(intent);
			}
		});

		postBoxDeleteBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

			}
		});
		
		data = new ArrayList<SimplePostInfo>();
		/*
		 * data.add(new SimplePostInfo("����ϴ� �Ƶ�","����",new Date(2015,7,25)));
		 * data.add(new SimplePostInfo("����ϴ� ��","����",new Date(2015,7,23)));
		 * data.add(new SimplePostInfo("����ϴ� ����","��",new Date(2015,7,24)));
		 */
		for (int i = 0; i < letterCount; i++) {
			String[] str = bundle.getStringArray("simpleLetterInfo" + (i + 1));
			Date date = null;
			try {
				date = new SimpleDateFormat("yy-MM-dd").parse(str[1]);

			} catch (Exception e) {

			}
			System.out.println(date);
			data.add(new SimplePostInfo(str[0], str[2], date));
		}
		if(letterCount!=0)
		{
		adapter = new SimplePostInfoListAdapter(postBoxActivity, data);
		listView = (ListView) findViewById(R.id.postListView);
		listView.setAdapter(adapter);
		}
		/*
		 * detailData = new ArrayList<PostInfo>();
		 * 
		 * 
		 * detailData.add(new PostInfo("����ϴ� �Ƶ�","����ϴ� �Ƶ�� !! ����", new
		 * Date(2015,7,25), "����")); detailData.add(new
		 * PostInfo("����ϴ� ��","����ϴ� �� !! ����", new Date(2015,7,23), "����"));
		 * detailData.add(new PostInfo("����ϴ� ����","����ϴ� ���� !! ����", new
		 * Date(2015,7,24), "��"));
		 */

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// �¿� �߰�:
				String[] str = bundle.getStringArray("simpleLetterInfo"
						+ (letterCount - position));
				WebServerGetDetailLetterInfoThread detailInfoThread = new WebServerGetDetailLetterInfoThread(
						PostBoxActivity.this, PostBoxActivity.this, str[3]);
				detailInfoThread.start();
				//

			}
		});

	}

}
