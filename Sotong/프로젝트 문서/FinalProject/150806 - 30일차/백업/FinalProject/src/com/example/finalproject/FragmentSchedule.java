package com.example.finalproject;

import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.widget.Toast;

import com.example.finalproject.calendar.CalendarAdapter;
import com.example.finalproject.calendar.CalendarView;
import com.example.finalproject.calendar.DayData;
import com.example.finalproject.calendar.MonthItem;
import com.example.finalproject.calendar.OnDataSelectionListener;
import com.example.finalproject.calendar.SummaryAdapter;
import com.example.finalproject.model.ScheduleSummary;

@SuppressLint("NewApi")
public class FragmentSchedule extends Fragment implements OnTimeChangedListener{
public Context context;
	///////////////////////////////////
	 CalendarView monthView; //Ŭ���� ����
	 CalendarAdapter monthViewAdapter;//Ŭ���� ����2
	 ArrayList<ScheduleSummary> summaryList;
	 TextView monthText; //���� 
	 private final int DIALOG_CUSTOM_ID = 1;
	 private final int ADD_SINGLE_SCHEDULE=3;
	 ListView lv;
	 ArrayList<DayData> dayData; //��¥���� ���� �������� �����ϴ� ����Ʈ
	   
	 int curYear;//���� ����
	 int curMonth;//���� ��
	 int curDay;//���� ��
	 String txt ="";
	 int curHour;//���� �ð�
	 int curMin;//���� ��
	 
	 public Button monthPrevious;
	 public Button monthNext;
	 public Button addSingleScheduleBtn;  
	 EditText et; //���̾�α׿���  ���� ���� �Է� ����
	 Button save; //���̾�α׿��� �����ư
	   
	   //ArrayAdapter<String> adapter; //�ش� ��¥�� ����� ������ ������  ����Ʈ ��
	 ArrayAdapter<ScheduleSummary> adapter;
	 ArrayList<String> as;//����Ʈ�信 ������ ������ �����ϴ� ����Ʈ
	
	
	///////////////////////////////////
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_single_schedule, container, false);
		
		context=rootView.getContext();
		////////////////////////////////////////////////
		dayData = new ArrayList<DayData>();//��¥���� 
	      
	//	return rootView;

	      
	    lv = (ListView)rootView.findViewById(R.id.listView);//����Ʈ �並 xml�κ��� ����
	      
	      monthView = (CalendarView)rootView.findViewById(R.id.monthView);
	      monthViewAdapter = new CalendarAdapter(context);
	      
	      monthView.setAdapter(monthViewAdapter);
	      monthView.setOnDataSelectionListener(new OnDataSelectionListener(){ //��¥ ���ý� ������
	         public void onDataSelected(AdapterView parent, View v, int position, long id){
	            MonthItem curItem = (MonthItem) monthViewAdapter.getItem(position); //���� ������ ��¥�� ��.
	            curDay = curItem.getDay();//���� ������ ��¥�� �޾ƿ�.
	            
	            as = new ArrayList<String>();
	            summaryList=new ArrayList<ScheduleSummary>();
	            for(int i =0; i< dayData.size();i++){ //���� ������ ó������ ������ �˻�
	               if(dayData.get(i).getDay() ==curDay){ //���� ���� ������ ��¥�� ���������� ����Ǿ��ִٸ�
	                  as.add(dayData.get(i).getSchedule()); //�����ֱ� ����Ʈ�� ������.
	                  summaryList.add(new ScheduleSummary(dayData.get(i).getSchedule()));
	                  
	               }
	            }
	            
	            updateLv();//����Ʈ�並 ������Ʈ
	            
	         }
	      });
	      
	      
	      
	      monthText = (TextView)rootView.findViewById(R.id.monthText);
	      setMonthText(); //�ʱ�ȭ
	      
	      monthPrevious = (Button)rootView.findViewById(R.id.monthPrevious);
	      monthPrevious.setOnClickListener(new OnClickListener() {
	         
	         @Override
	         public void onClick(View v) {
	            // TODO Auto-generated method stub
	            monthViewAdapter.setPreviousMonth(); //���� ���� ���� �޷� ����.
	            monthViewAdapter.notifyDataSetChanged(); //�����Ͱ� ����Ǹ�,�� �����Ͱ� ��Ÿ������ �Ѵ�.
	            
	            setMonthText();
	      
	         }
	      });
	      
	      monthNext = (Button)rootView.findViewById(R.id.monthNext);
	      monthNext.setOnClickListener(new OnClickListener() {
	         
	         @Override
	         public void onClick(View v) {
	            // TODO Auto-generated method stub
	            monthViewAdapter.setNextMonth();
	            monthViewAdapter.notifyDataSetChanged();//�����Ͱ� ����Ǹ�,�� �����Ͱ� ��Ÿ������ �Ѵ�.

	            setMonthText();
	         }
	         
	      }
	            );
	      addSingleScheduleBtn=(Button)rootView.findViewById(R.id.addSingleScheduleBtn);
	      addSingleScheduleBtn.setOnClickListener(new OnClickListener()
	      {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(context,AddSingleScActivity.class);
				startActivity(intent);
			}
	    	  
	      });
		////////////////////////////////////////////////
	    setHasOptionsMenu(true);  
		return rootView;
	}
	
/*
	public void onResume() {
	
		super.onResume();
		monthViewAdapter.notifyDataSetChanged();
	}
*/	
	public void updateLv(){
	      //adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,as);//����͸� �ʱ�ȭ�Ѵ�.��¥������ ���� ��������Ʈ�� ����ͺ�� �����ֵ�����.
	      //adapter= new ArrayAdapter<String>(this,R.layout.schedule_info,as);
		  adapter = new SummaryAdapter(context,R.layout.schedule_info,R.id.schedule1,summaryList);
		  //adapter = new SummaryAdapter(context,R.layout.schedule_info,R.id.schedule1,summaryList);
	      
	            //����͸� �ʱ�ȭ�Ѵ�.��¥������ ���� ��������Ʈ�� ����ͺ�� �����ֵ�����.
	      
	      lv.setAdapter(adapter);
	      lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
	      {@Override
	         public void onItemClick(AdapterView<?> parent, View view, int position,long id)
	         {
	    	  /*���� �󼼺���*/
	    	  
	            Toast.makeText(context, "�����󼼺��� ������", Toast.LENGTH_LONG).show();
	            Intent intent=new Intent(context,ViewSingleScActivity.class);
	          startActivity(intent);
	            //intent.setClass(context.getApplicationContext(), ViewScActivity.class);
	            //startActivity(intent);
	         }
	      });
	   }
	   
	   private void setMonthText(){
	      curYear = monthViewAdapter.getCurYear();//���� ������ �޾ƿ�
	      curMonth = monthViewAdapter.getCurMonth();//���� ���� �޾ƿ�.
	      
	      monthText.setText(curYear + "��" + (curMonth+1) + "��");
	      
	   }

	   
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.main, menu);
		
		
	}
	
	 
	   
	   
	public boolean onOptionsItemSelected(MenuItem item) {
		int curId = item.getItemId(); // ���̵� ����
		
		if(curId == R.id.action_settings){
			final DayData dd = new DayData(curYear, curMonth, curDay);
			AlertDialog.Builder builder = new AlertDialog.Builder(context); // ���� ȭ�鿡�� ���̾�α� ����
			View dia = View.inflate(context, R.layout.dia, null); // dia.xml���� ���̾�α� ���� �о�ͼ� ��� ����
			builder.setTitle("�����߰�");
			builder.setView(dia);
			save = (Button)dia.findViewById(R.id.quizButton); // ���̾�α׿��� �����ư
			//Button cancel=(Button)dia.findViewById(R.id.button2);//���̾�α׿��� ��� ��ư.
			et = (EditText)dia.findViewById(R.id.editText1); // ���̾�α׿��� ���� �ۼ� �ؽ�Ʈ�ʵ�
			final TimePicker tp = (TimePicker)dia.findViewById(R.id.timePicker1); // �ð��� �����ϴ� Ŭ����
			
			tp.setOnTimeChangedListener(this);
			final DialogInterface mPopupDlg = builder.show();
			View.OnClickListener saveListener = new View.OnClickListener(){
				public void onClick(View v) {
					txt = et.getText().toString();
					dd.setTime(tp.getCurrentHour(), tp.getCurrentMinute());
					dd.setString(txt);
					dayData.add(dd);
					mPopupDlg.dismiss();	
				}
			};
			save.setOnClickListener(saveListener);//������ ����
		}
		/*
		else if(curId==R.id.FamView){
	         Intent intent=new Intent(ScheduleActivity.this,ViewFamScActivity.class);
	         startActivity(intent);
	    }
	     ����� ���� ���� �߰��Ѵ� (�޴����� �Ϸ��� �߾�����) 
	    */
		return true;
	}
	   
	   /*
	   
	   public boolean onOptionsItemSelected(MenuItem item){
	      int curId = item.getItemId(); //���̵� �޾ƿ�
	      if(curId == R.id.action_settings){//���� ���̵�� 
	         final DayData dd = new DayData(curYear, curMonth, curDay);
	         AlertDialog.Builder builder = new AlertDialog.Builder(this); //���� ȭ�鿡�� ���̾�α� ����
	         View dia = View.inflate(this, R.layout.dia, null);//dia.xml���� ���̾�α� ���� �о�ͼ� ��� ����..
	         builder.setTitle("�����߰�");
	         builder.setView(dia);//���� �並 ����.
	         save = (Button)dia.findViewById(R.id.quizButton);//���̾�α׿��� ���� ��ư
	         //Button cancel=(Button)dia.findViewById(R.id.button2);//���̾�α׿��� ��� ��ư.
	         et = (EditText)dia.findViewById(R.id.editText1);//���̾�α׿��� ���� �ۼ� �ؽ�Ʈ�ʵ�.
	         final TimePicker tp = (TimePicker)dia.findViewById(R.id.timePicker1);//�ð��� �����ϴ� Ŭ����
	         tp.setOnTimeChangedListener(this);//�ð� ����� ������ ����.
	         final DialogInterface mPopupDlg=builder.show();
	         View.OnClickListener saveListener = new View.OnClickListener() {//�����ư Ŭ������ ������.
	            public void onClick(View v) {
	               txt = et.getText().toString();
	               dd.setTime(tp.getCurrentHour(), tp.getCurrentMinute());
	               dd.setString(txt);
	               dayData.add(dd);
	               
	            mPopupDlg.dismiss();
	            }
	         };
	         save.setOnClickListener(saveListener);//������ ����
	         
	      }
	      else if(curId==R.id.FamView)
	      {
	         Intent intent=new Intent(ScheduleActivity.this,ViewFamScActivity.class);
	         startActivity(intent);
	      }
	      return true;
	   }
*/
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	// TODO Auto-generated method stub
	super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==ADD_SINGLE_SCHEDULE)
		{
			String startDate=data.getExtras().getString("startDate");
			String startTime=data.getExtras().getString("startTime");
			String endDate=data.getExtras().getString("endDate");
			String endTime=data.getExtras().getString("endTime");
			String title=data.getExtras().getString("title");
			String place=data.getExtras().getString("place");
			String txt=startDate+" "+startTime+"~"+endDate+" "+endTime+"\n"+"����:"+title+" ���:"+place;
			StringTokenizer token=new StringTokenizer(startDate, ":");
			String startYear=token.nextToken();
			String startMonth=token.nextToken();
			String startDay=token.nextToken();
			token=new StringTokenizer(endDate, "-");
			String startHour=token.nextToken();
			String startMin=token.nextToken();
			Date start=new Date(Integer.parseInt(startYear)-1900,Integer.parseInt(startMonth)-1,Integer.parseInt(startDay));
			
			DayData dd=new DayData(Integer.parseInt(startYear),Integer.parseInt(startMonth),Integer.parseInt(startDay));
			DayData dd2=new DayData(2015,8,6);
			dd2.setTime(15,7);
			dd2.setString("test2");
			dd.setTime(18,7);
			dd.setString("test");
			
			dayData.add(dd);
			dayData.add(dd2);
			
			//Date end=new Date
		}
	}
	   @Override
	   public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {//���� �ð��� �����ϴ� �޼ҵ�
	      // TODO Auto-generated method stub
	      curHour = hourOfDay;
	      curMin = minute;
	   }
	
	
	
	
 }

