package com.example.finalproject;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;

public class AlbumDialog extends Dialog{
	public Gallery album;
	public Button exitBtn;
	public Context context;
	public ArrayList<Integer> images;
	
	
	public AlbumDialog(Context context, ArrayList<Integer> images) {
		super(context);
		this.context = context;
		this.images = images;
		// TODO Auto-generated constructor stub
	}
	
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.album_view);
		album = (Gallery)findViewById(R.id.gallery1);
		album.setAdapter(new ImgAdapter(this.context));
		exitBtn = (Button)findViewById(R.id.exitBtn);
		
		exitBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				dismiss();
			}
		});
		
	}
	
	
	class ImgAdapter extends BaseAdapter{
		private final Context context;
		public LayoutInflater inflater;
		
		public ImgAdapter(Context c) {
			context = c;
			inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		public int getCount() {
			return images.size();
		}
		public Object getItem(int position) {
			
			return images.get(position);
		}
		
		public long getItemId(int position) {
		
			return position;
		}
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView == null){
				convertView = inflater.inflate(R.layout.album_item, parent,false);
			}
			
			ImageView imgView = (ImageView)convertView.findViewById(R.id.imgGalView1);
			imgView.setImageResource(images.get(position));
			
			return convertView;
		}
	}
	
}
