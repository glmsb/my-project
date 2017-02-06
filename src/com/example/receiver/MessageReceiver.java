package com.example.receiver;

import com.example.activity.MessageActivity;
import com.example.activity.R;
import com.example.bean.Msg;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class MessageReceiver extends BroadcastReceiver {
	
	@SuppressWarnings("deprecation")
	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle=intent.getExtras();//从 Intent参数中取出了一个 Bundle 对象
		//使用 pdu密钥来提取一个 SMS pdus 数组，其中每一个 pdu 都表示一条短信消息
		Object[] pdus=(Object[]) bundle.get("pdus");// 提取短信消息
		SmsMessage[] messages=new SmsMessage[pdus.length];
		for (int i = 0; i < messages.length; i++) {
			//将每一个 pdu 字节数组转换为 SmsMessage 对象
			messages[i]=SmsMessage.createFromPdu((byte[]) pdus[i]);
		}
		
		String address=messages[0].getOriginatingAddress();//提取发送方号码
		String fullMessage="";
		for (SmsMessage message:messages) {
			//获取到短信的内容，然后将每一个 SmsMessage 对象中的短信内容拼接起来，就组成了一条完整的短信
			fullMessage +=message.getMessageBody();
		}
		
		NotificationManager notificationManager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification=new Notification(R.drawable.message,fullMessage,System.currentTimeMillis());
		Intent notificationIntent=new Intent("com.example.activitytest.ACTION_START");
		notificationIntent.addCategory("com.example.activitytest.MSG");
		PendingIntent pIntent=PendingIntent.getBroadcast(context, 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
		notification.setLatestEventInfo(context, address, fullMessage, pIntent);
		notification.defaults = Notification.DEFAULT_ALL;
		notificationManager.notify(2,notification);
		
		Msg msg = new Msg(fullMessage, Msg.TYPE_RECEIVED);
		MessageActivity.msgList.add(msg);
		MessageActivity.adapter.notifyDataSetChanged();
		MessageActivity.msgListView.setSelection(MessageActivity.msgList.size());
		abortBroadcast();//中止掉广播的继续传递

	}

}
