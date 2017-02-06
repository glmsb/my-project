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
	
	public TitleLayout(Context context, AttributeSet attrs) {//在布局中引入 TitleLayout控件就会调用这个构造函数
		super(context, attrs);
		
		 /**
		  * LayoutInflater 的 from()方法可以构建出一个 LayoutInflater对象，
		  * 然后调用 inflate()方法就可以动态加载一个布局文件;
		  * inflate()方法接收两个参数，第一个参数是要加载的布局文件的 id，
		  * 第二个参数是给加载好的布局再添加一个父布局，这里我们想要指定为 TitleLayout，于是直接传入 this
		 */
		LayoutInflater.from(context).inflate(R.layout.title, this);//对标题栏布局进行动态加载
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
				Toast.makeText(getContext(), "分享。。。。", Toast.LENGTH_SHORT).show();			
			}
		});
		
		set.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Toast.makeText(getContext(), "sgezhi。。。。", Toast.LENGTH_SHORT).show();			
			}
		});
		
	}

}
