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
		this.letterCode = intent.getStringExtra("letterCode"); // �ش� ������ �����ϱ� ���� intent�κ��� ���� �ڵ� ����
		System.out.println("letterCode");

		handler = new Handler();

		postDetailInfoTitle = (TextView) findViewById(R.id.postDetailInfoTitle);
		postDetailInfoContent = (TextView) findViewById(R.id.postDetailInfoContent);

		postDetailInfoDeleteBtn = (ImageButton) findViewById(R.id.postDetailInfoDeleteBtn);
		postDetailInfoWriteBtn = (ImageButton) findViewById(R.id.postDetailInfoWriteBtn);

		handler.post(new Runnable() {
			public void run() {
				postDetailInfoTitle.setText(value[0] + value[2]);// ������,��¥
				postDetailInfoContent.setText(value[1] + "\n" + value[3]);// ����//�߽���
			}
		});

		postDetailInfoDeleteBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				AlertDialog.Builder builder = new Builder(actionBarActivity);
				builder.setMessage("������ �����Ͻðڽ��ϱ�?");
				builder.setTitle("����");

				builder.setPositiveButton("��",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// ���� �����ϱ� ������ �߰�
								WebServerDeleteLetterThread deleteThread = new WebServerDeleteLetterThread(
										DetailPostActivity.this,
										DetailPostActivity.this, letterCode);
								deleteThread.start();
								Toast.makeText(actionBarActivity, "�����մϴ�",
										Toast.LENGTH_SHORT).show();
							}
						});

				builder.setNegativeButton("�ƴϿ�",
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
