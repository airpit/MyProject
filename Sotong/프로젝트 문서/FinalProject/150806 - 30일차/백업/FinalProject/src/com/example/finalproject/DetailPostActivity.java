package com.example.finalproject;

import WebServerThread.WebServerDeleteLetterThread;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class DetailPostActivity extends ActionBarActivity {

	public TextView postDetailInfoTitle;
	public TextView postDetailInfoContent;
	public ImageButton postDetailInfoDeleteBtn;
	public ImageButton postDetailInfoWriteBtn;
	public String letterCode;
	public Handler handler;
	public String[] value;

	public ActionBarActivity actionBarActivity = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_post);

		Intent intent = getIntent();
		value = intent.getStringArrayExtra("postInfo");
		this.letterCode = intent.getStringExtra("letterCode"); // 해당 편지를 삭제하기 위해 intent로부터 편지 코드 받음
		System.out.println("letterCode");

		handler = new Handler();

		postDetailInfoTitle = (TextView) findViewById(R.id.postDetailInfoTitle);
		postDetailInfoContent = (TextView) findViewById(R.id.postDetailInfoContent);

		postDetailInfoDeleteBtn = (ImageButton) findViewById(R.id.postDetailInfoDeleteBtn);
		postDetailInfoWriteBtn = (ImageButton) findViewById(R.id.postDetailInfoWriteBtn);

		handler.post(new Runnable() {
			public void run() {
				postDetailInfoTitle.setText(value[0] + value[2]);// 수신자,날짜
				postDetailInfoContent.setText(value[1] + "\n" + value[3]);// 내용//발신자
			}
		});

		postDetailInfoDeleteBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder builder = new Builder(actionBarActivity);
				builder.setMessage("정말로 삭제하시겠습니까?");
				builder.setTitle("삭제");

				builder.setPositiveButton("예",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// 편지 삭제하기 스레드 추가
								WebServerDeleteLetterThread deleteThread = new WebServerDeleteLetterThread(
										DetailPostActivity.this,
										DetailPostActivity.this, letterCode);
								deleteThread.start();
								Toast.makeText(actionBarActivity, "삭제합니다",
										Toast.LENGTH_SHORT).show();
							}
						});

				builder.setNegativeButton("아니오",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();
							}
						});

				builder.create().show();
			}
		});

	}

}
