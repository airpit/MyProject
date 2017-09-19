package com.example.finalproject;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class ViewFamScActivity extends Activity {
	
	Button edit,delete;
	TextView title,place,startTime,endTime,alarmTime,loopCycle,memo,event;
	CheckBox dayCheck,alarmCheck,loopCheck,eventCheck;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_fam_sc);
		final AlertDialog.Builder deleteDialog=new AlertDialog.Builder(this);
		edit=(Button)findViewById(R.id.famEdit);
		delete=(Button)findViewById(R.id.famDelete);
		edit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(ViewFamScActivity.this,EditFamScActivity.class);
				startActivity(intent);
			}
		});
		delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				deleteDialog.setTitle("���� Ȯ��")
				.setMessage("���� �����Ͻðڽ��ϱ�?")
				.setNegativeButton("���", new DialogInterface.OnClickListener(){

					@Override
					public void onClick(DialogInterface dialog,
							int which) {
						
						// TODO Auto-generated method stub
						Toast.makeText(ViewFamScActivity.this, "���� ��ҵǾ���",Toast.LENGTH_LONG).show();
						dialog.dismiss();
					}
					
				})
				.setPositiveButton("Ȯ��", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Toast.makeText(ViewFamScActivity.this, "�����Ϸ�",Toast.LENGTH_LONG).show();
						dialog.dismiss();
					}
				})
				
				.show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_fam_sc, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
