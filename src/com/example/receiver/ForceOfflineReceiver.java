package com.example.receiver;

import com.example.abstractAtactivity.ActivityCollector;
import com.example.activity.LoginActivity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.WindowManager;

public class ForceOfflineReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(final Context context, Intent intent) {
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
		dialogBuilder.setTitle("警告");
		dialogBuilder.setMessage("你已被迫强制下线，请再次登录");
		dialogBuilder.setCancelable(false);
		dialogBuilder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//销毁掉所有活动，并重新启动 LoginActivity 这个活动
						ActivityCollector.finishAll();
						Intent intent = new Intent(context, LoginActivity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//在广播接收器里启动活动
						context.startActivity(intent);
					}
				});	
		AlertDialog alertDialog = dialogBuilder.create();
		//需要设置AlertDialog的类型，保证在广播接收器中可以正常弹出
		alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		alertDialog.show();
	}

}
