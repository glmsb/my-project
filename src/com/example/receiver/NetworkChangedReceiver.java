package com.example.receiver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;


public class NetworkChangedReceiver extends BroadcastReceiver {
	
	/**
	  1�� ����̬ע��Ĺ㲥����������Ҫ�������ļ�������
      2��ÿ������״̬�����仯ʱ��onReceive()�����ͻ�õ�ִ��
      3����������֮����ܽ��յ��㲥
      */
	
	@Override
	public void onReceive(Context context, Intent intent) {
		//ר�����ڹ����������ӵ�ϵͳ�����ࡣ 
		ConnectivityManager connectivitymanager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo=connectivitymanager.getActiveNetworkInfo();
		if(!(networkInfo!=null&&networkInfo.isAvailable()))
			Toast.makeText(context, "��������⣬����...", Toast.LENGTH_LONG).show();		
	}
}
