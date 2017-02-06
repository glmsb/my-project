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
		Bundle bundle=intent.getExtras();//�� Intent������ȡ����һ�� Bundle ����
		//ʹ�� pdu��Կ����ȡһ�� SMS pdus ���飬����ÿһ�� pdu ����ʾһ��������Ϣ
		Object[] pdus=(Object[]) bundle.get("pdus");// ��ȡ������Ϣ
		SmsMessage[] messages=new SmsMessage[pdus.length];
		for (int i = 0; i < messages.length; i++) {
			//��ÿһ�� pdu �ֽ�����ת��Ϊ SmsMessage ����
			messages[i]=SmsMessage.createFromPdu((byte[]) pdus[i]);
		}
		
		String address=messages[0].getOriginatingAddress();//��ȡ���ͷ�����
		String fullMessage="";
		for (SmsMessage message:messages) {
			//��ȡ�����ŵ����ݣ�Ȼ��ÿһ�� SmsMessage �����еĶ�������ƴ���������������һ�������Ķ���
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
		abortBroadcast();//��ֹ���㲥�ļ�������

	}

}
