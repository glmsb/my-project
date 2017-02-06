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
	
	/** ����������д��
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
		 * ���ջһ������������*/
		Intent intent=getIntent();
		String data1=intent.getStringExtra("param1");
		String data2=intent.getStringExtra("param2");
		byte [] bis=intent.getByteArrayExtra("BMP");  
	//	Bitmap bitmap=intent.getParcelableExtra("bitmap");ֻ��С��40kb
        if (intent !=null) {
        	if(!(data1==null) && !(data2==null)){
    			tv.setText(data1+"//n"+data2);
    			tv.setTextColor(Color.GREEN);
    			tv.setTextSize(18);
    		}
        	if (bis !=null) {
        		//ȡ�����ֽ�����֮����BitmapFactory�е�decodeByteArray������ϳ�һ��bitmap�Ϳ�����
        		Bitmap bitmap=BitmapFactory.decodeByteArray(bis, 0, bis.length);  
        		iv.setImageBitmap(bitmap);
			}
    		
		}
		
		
	}
	
	
	/**
	  ���·��ؼ�ʱ����
	  */
	public void onBackPressed(){
		Intent intent=new Intent();//��� Intent ���������ڴ������ݶ��ѣ���û��ָ���κεġ���ͼ�� 
		intent.putExtra("data_return", "�ش��ɹ�");
		setResult(RESULT_OK,intent);//ר����������һ����������ݵ�
		finish();// �� SecondActivity������֮���ص���һ����� onActivityResult()����
	}
}
