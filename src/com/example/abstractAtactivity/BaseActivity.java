package com.example.abstractAtactivity;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/**�ҵ�ÿ�������Ӧ�Ļ*/
		Log.d("BaseActivity",getClass().getSimpleName());//��ȡ���
		
		//����ǰ���ڴ����Ļ��ӵ����������
		ActivityCollector.addActivity(this);
	}
	
	//��д onDestroy()����
	protected void onDestory(){
		super.onDestroy();
		
		//��һ������Ҫ���ٵĻ�ӻ���������Ƴ�
		ActivityCollector.removeActivity(this);
	}

}
