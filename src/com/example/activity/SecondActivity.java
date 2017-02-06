package com.example.activity;

import com.example.abstractAtactivity.BaseActivity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends BaseActivity{
	
	private TextView tv;
	private ImageView iv;
	
	/** 启动活动的最佳写法
	 * */	
	public static void actionStart(Context context,String data1,String data2){
		Intent intent=new Intent(context,SecondActivity.class);
		intent.putExtra("param1", data1);
		intent.putExtra("param2", data2);
		context.startActivity (intent);
	}
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_layout);
		NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		manager.cancel(1);
		tv=(TextView) findViewById(R.id.transmit_value);
		iv=(ImageView)findViewById(R.id.photo);
		
		/**
		 * 接收活动一传过来的数据*/
		Intent intent=getIntent();
		String data1=intent.getStringExtra("param1");
		String data2=intent.getStringExtra("param2");
		byte [] bis=intent.getByteArrayExtra("BMP");  
	//	Bitmap bitmap=intent.getParcelableExtra("bitmap");只能小于40kb
        if (intent !=null) {
        	if(!(data1==null) && !(data2==null)){
    			tv.setText(data1+"//n"+data2);
    			tv.setTextColor(Color.GREEN);
    			tv.setTextSize(18);
    		}
        	if (bis !=null) {
        		//取出来字节数组之后，用BitmapFactory中的decodeByteArray方法组合成一个bitmap就可以了
        		Bitmap bitmap=BitmapFactory.decodeByteArray(bis, 0, bis.length);  
        		iv.setImageBitmap(bitmap);
			}
    		
		}
		
		
	}
	
	
	/**
	  按下返回键时调用
	  */
	public void onBackPressed(){
		Intent intent=new Intent();//这个 Intent 仅仅是用于传递数据而已，它没有指定任何的“意图” 
		intent.putExtra("data_return", "回传成功");
		setResult(RESULT_OK,intent);//专门用于向上一个活动返回数据的
		finish();// 在 SecondActivity被销毁之后会回调上一个活动的 onActivityResult()方法
	}
}
