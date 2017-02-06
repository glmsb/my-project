package com.example.abstractAtactivity;

import com.example.activity.R;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TitleLayout extends LinearLayout {
	private Button back,share;
	private ImageView set;
	
	public TitleLayout(Context context, AttributeSet attrs) {//�ڲ��������� TitleLayout�ؼ��ͻ����������캯��
		super(context, attrs);
		
		 /**
		  * LayoutInflater �� from()�������Թ�����һ�� LayoutInflater����
		  * Ȼ����� inflate()�����Ϳ��Զ�̬����һ�������ļ�;
		  * inflate()��������������������һ��������Ҫ���صĲ����ļ��� id��
		  * �ڶ��������Ǹ����غõĲ��������һ�������֣�����������Ҫָ��Ϊ TitleLayout������ֱ�Ӵ��� this
		 */
		LayoutInflater.from(context).inflate(R.layout.title, this);//�Ա��������ֽ��ж�̬����
		back=(Button)findViewById(R.id.retrun);
		share=(Button)findViewById(R.id.share);
		set=(ImageView)findViewById(R.id.set);
		
		
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				((Activity) getContext()).finish();		
			}
		});
		
		share.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Toast.makeText(getContext(), "����������", Toast.LENGTH_SHORT).show();			
			}
		});
		
		set.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Toast.makeText(getContext(), "sgezhi��������", Toast.LENGTH_SHORT).show();			
			}
		});
		
	}

}
