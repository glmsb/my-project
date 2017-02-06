package com.example.adapter;

import java.util.List;

import com.example.activity.R;
import com.example.bean.Msg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;



public class MsgAdapter extends ArrayAdapter<Msg> {

	private int resourceId;
	public MsgAdapter(Context context, int textViewResourceId, List<Msg> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		resourceId=textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Msg message=getItem(position);
		View view;
		ViewHolder viewHolder;
		if(convertView==null){
			view=LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder=new ViewHolder();
			viewHolder.leftLayout=(LinearLayout) view.findViewById(R.id.left_layout);
			viewHolder.righLayout=(LinearLayout) view.findViewById(R.id.right_layout);
			viewHolder.sentMsg=(TextView) view.findViewById(R.id.right_msg);
			viewHolder.receivedeMsg=(TextView) view.findViewById(R.id.left_msg);
			view.setTag(viewHolder);
		}
		else {
			view=convertView;
			viewHolder=(ViewHolder) view.getTag();
		}
		if(message.getType()==Msg.TYPE_RECEIVED){
			viewHolder.leftLayout.setVisibility(View.VISIBLE);
			viewHolder.righLayout.setVisibility(View.GONE);
			viewHolder.receivedeMsg.setText(message.getContent());
		}
		else if(message.getType()==Msg.TYPE_SENT){
			viewHolder.righLayout.setVisibility(View.VISIBLE);
			viewHolder.leftLayout.setVisibility(View.GONE);
			viewHolder.sentMsg.setText(message.getContent());
		}
		return view;
	}
	
	class ViewHolder{
		LinearLayout leftLayout;
		LinearLayout righLayout;
		TextView sentMsg;
		TextView receivedeMsg;
	}
	
	

}

