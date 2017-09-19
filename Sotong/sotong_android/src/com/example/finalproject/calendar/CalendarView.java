package com.example.finalproject.calendar;

//�޷��� 
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;

public class CalendarView extends GridView{

	private OnDataSelectionListener selectionListener;
	
	CalendarAdapter adapter;
	
	public CalendarView(Context context){
		super(context);
		init();
	}
	
	public CalendarView(Context context,AttributeSet attr){
		super(context, attr);
		init();
	}
	
	private void init(){
		setBackgroundColor(Color.WHITE);
		setVerticalSpacing(6);//��  ����
		setHorizontalSpacing(6);//�� ����
		setStretchMode(GridView.STRETCH_COLUMN_WIDTH);//
		setNumColumns(7);//�� ���� ����
		
		setOnItemClickListener(new OnItemClickAdapter()); //�ش� �׸���信 �����ʸ� ����
	}
	
	
	
	
	class OnItemClickAdapter implements OnItemClickListener {//������(�Ϸ糯¥) Ŭ������ ������
		public OnItemClickAdapter(){
			
		}
		
		public void onItemClick(AdapterView parent, View v, int position, long id){
			if(adapter != null){
				adapter.setSelectedPosition(position);//���õ� ��ġ��, �Է¹��� ������ �����Ѵ�.
				adapter.notifyDataSetInvalidated();//CalendarView ����.
				
			}
			
			if(selectionListener!=null){ //�����ʰ� �ΰ��� �ƴϸ�,  ������ ����.
				selectionListener.onDataSelected(parent, v,position, id);
			}
		}
	}
	
	public void setAdapter(BaseAdapter adapter){
		super.setAdapter(adapter);
		this.adapter = (CalendarAdapter) adapter;
	}
	
	public BaseAdapter getAdapter(){
		return (BaseAdapter)super.getAdapter();
	}
	
	public void setOnDataSelectionListener(OnDataSelectionListener listener){
		this.selectionListener = listener;
	}
	
	public OnDataSelectionListener getOnDataSelectionListener(){
		return selectionListener;
	}
}
