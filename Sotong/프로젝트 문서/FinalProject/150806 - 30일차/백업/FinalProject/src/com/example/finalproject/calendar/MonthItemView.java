package com.example.finalproject.calendar;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;
//MonthItem�� �ð������� �� �� �ֵ��� ���� �����ϴ� Ŭ����.
public class MonthItemView extends TextView{
	
	private MonthItem item;
	
	public MonthItemView(Context context){
		super(context);
		
		init();
	}
	
	public MonthItemView(Context context, AttributeSet attrs){
		super(context,attrs);
		init();
	}
	
	private void init(){//��������
		setBackgroundColor(Color.WHITE);
	}
	
	public MonthItem getItem(){
		return item;
	}
	
	public void setItem(MonthItem item){
		this.item = item;
		
		int day = item.getDay();
		if(day !=0){//�Է��� ��¥�� ��ȿ���� 0�� �ƴϸ�
			setText(String.valueOf(day));
			
			//�Է��� ��¥�� ���� ���ڿ��� �ٲپ� �ؽ�Ʈ�� ����ִ´�.
		}else{
			setText("");
		}
	}
}
