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
		dialogBuilder.setTitle("����");
		dialogBuilder.setMessage("���ѱ���ǿ�����ߣ����ٴε�¼");
		dialogBuilder.setCancelable(false);
		dialogBuilder.setPositiveButton("ȷ��",new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//���ٵ����л������������ LoginActivity ����
						ActivityCollector.finishAll();
						Intent intent = new Intent(context, LoginActivity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//�ڹ㲥�������������
						context.startActivity(intent);
					}
				});	
		AlertDialog alertDialog = dialogBuilder.create();
		//��Ҫ����AlertDialog�����ͣ���֤�ڹ㲥�������п�����������
		alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		alertDialog.show();
	}

}
