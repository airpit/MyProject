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
import android.widget.Toast;

public class ViewSingleScActivity extends Activity {
	Button editBtn, deleteBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final AlertDialog.Builder deleteDialog=new AlertDialog.Builder(this);
		setContentView(R.layout.activity_view_sc_single);
		editBtn=(Button)findViewById(R.id.singleScEditBtn);
		deleteBtn=(Button)findViewById(R.id.singleScDeleteBtn);
		deleteBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				deleteDialog.setTitle("삭제 확인")
							.setMessage("정말 삭제하시겠습니까?")
							.setNegativeButton("취소", new DialogInterface.OnClickListener(){

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									
									// TODO Auto-generated method stub
									Toast.makeText(ViewSingleScActivity.this, "삭제 취소되었음",Toast.LENGTH_LONG).show();
									dialog.dismiss();
								}
								
							})
							.setPositiveButton("확인", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									Toast.makeText(ViewSingleScActivity.this, "삭제완료",Toast.LENGTH_LONG).show();
									dialog.dismiss();
								}
							})
							
							.show();
			}
		});
editBtn.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent=new Intent(ViewSingleScActivity.this,EditSingleScActivity.class);
	//	intent.putExtra
		startActivity(intent);
	}
});
}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_sc_single, menu);
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
