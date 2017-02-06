package com.example.receiver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;


public class NetworkChangedReceiver extends BroadcastReceiver {
	
	/**
	  1、 被动态注册的广播接收器不需要在配置文件中申明
      2、每当网络状态发生变化时，onReceive()方法就会得到执行
      3、程序启动之后才能接收到广播
      */
	
	@Override
	public void onReceive(Context context, Intent intent) {
		//专门用于管理网络连接的系统服务类。 
		ConnectivityManager connectivitymanager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo=connectivitymanager.getActiveNetworkInfo();
		if(!(networkInfo!=null&&networkInfo.isAvailable()))
			Toast.makeText(context, "网络出问题，请检查...", Toast.LENGTH_LONG).show();		
	}
}
