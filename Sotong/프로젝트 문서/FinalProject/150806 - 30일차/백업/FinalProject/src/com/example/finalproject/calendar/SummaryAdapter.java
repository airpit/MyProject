package com.example.finalproject.calendar;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalproject.R;
import com.example.finalproject.model.ScheduleSummary;
//import com.lec.sotongtest.R;

public class SummaryAdapter extends ArrayAdapter<ScheduleSummary> {

   public SummaryAdapter(Context context, int resource,
         int textViewResourceId, List<ScheduleSummary> objects) {
      super(context, resource, textViewResourceId, objects);
      // TODO Auto-generated constructor stub
   }
   class ViewHolder
   {
      ImageView mV;
      TextView mScheduleTv;
   }
   @Override
   public View getView(int position,View convertView,ViewGroup parent)
   {
      View itemLayout=super.getView(position, convertView, parent);
      ViewHolder viewHolder=(ViewHolder)itemLayout.getTag();
      if(viewHolder==null)
      {
         viewHolder=new ViewHolder();
         viewHolder.mV=(ImageView)itemLayout.findViewById(R.id.view1);
         viewHolder.mScheduleTv=(TextView)itemLayout.findViewById(R.id.schedule1);
      itemLayout.setTag(viewHolder);
      }
      //viewHolder.mV.setImageURI(Uri.parse(getItem(position).getImageUri()));
      viewHolder.mV.setImageResource(R.drawable.ic_launcher); //�� �κ��� ����ڰ� �����ؼ� ������ �� �ֵ��� �ٲܰ�.
      viewHolder.mScheduleTv.setText(getItem(position).getSchedule());
      return itemLayout;
   }
}