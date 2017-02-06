package com.example.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.adapter.MsgAdapter;
import com.example.bean.Msg;
import com.example.receiver.MessageReceiver;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MessageActivity extends Activity implements OnClickListener {
	
	private EditText inputMsg,inputRcv;
	private Button send;
	private String content,recerver;
	public static ListView msgListView;
	public static MsgAdapter adapter;
	public static List<Msg> msgList = new ArrayList<Msg>();
	private IntentFilter sendFilter,receiveFilter;
	private SendStatuaReceiver sendStatuaReceiver;
	private MessageReceiver messageReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.message);
		//���֪ͨͼ��ʱȡ����Ӧ��֪ͨ��Ϣ��
		NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		manager.cancel(2);
		
		
		inputMsg = (EditText) findViewById(R.id.msg_input);
		inputRcv=(EditText) findViewById(R.id.rcv_input);
		send = (Button) findViewById(R.id.send);
		msgListView = (ListView) findViewById(R.id.msg_list_view);
		send.setOnClickListener(this);
		adapter = new MsgAdapter(MessageActivity.this, R.layout.msg_item, msgList);
		msgListView.setAdapter(adapter);
		
		//�� SendStatuaReceiver ���ж�̬ע�ᣬ�������ŵķ���״̬
		sendFilter=new IntentFilter();
		sendFilter.addAction("SENT_SMS_ACTION");
		sendStatuaReceiver=new SendStatuaReceiver();
		registerReceiver(sendStatuaReceiver, sendFilter);
		
		//�� MessageReceiver ���ж�̬ע�ᣬע��֮����ܽ��յ����Ź㲥
		receiveFilter = new IntentFilter();
		receiveFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
		receiveFilter.setPriority(100);//ϵͳ�����Ķ��Ź㲥��һ������㲥,��� MessageReceiver�����ȼ��������ܹ�����ϵͳ���ų�����յ����Ź㲥
		messageReceiver = new MessageReceiver();
		registerReceiver(messageReceiver, receiveFilter);	
	}
	
	@Override
	public void onClick(View v) {
		if (v.getId()==R.id.send){
			content = inputMsg.getText().toString();
			recerver=inputRcv.getText().toString();
			if (!("".equals(content))&&!( "".equals(recerver))) {//���ݺͽ��շ�����Ϊ��
			SmsManager smsManager=SmsManager.getDefault();
			
			Intent intent=new Intent("SENT_SMS_ACTION");
			PendingIntent pi =PendingIntent.getBroadcast(getBaseContext(), 0, intent, 0);
			
			smsManager.sendTextMessage(recerver, null, content, pi, null);//���Ͷ���,���ĸ����������Զ��ŵķ���״̬���м��
			
			Msg msg = new Msg(content, Msg.TYPE_SENT);
			msgList.add(msg);
			adapter.notifyDataSetChanged();
			msgListView.setSelection(msgList.size());
			inputMsg.setText("");
			}
		}		
	}
	
	
	
	/**
	 * SendStatuaReceiver ����㲥������ ר�����ڼ������ŷ���״̬��
	 */
	class SendStatuaReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context arg0, Intent arg1) {
			if (SendStatuaReceiver.this.getResultCode()==RESULT_OK) {
				Toast.makeText(arg0, "���ͳɹ���",Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(arg0, "����ʧ�ܣ����Ժ����� !",Toast.LENGTH_SHORT).show();
			}
		}	
	}
	
	
	
		/**
		����һ���㲥������������ϵͳ�����Ķ��Ź㲥
		*/
//		class MessageReceiver extends BroadcastReceiver{
//			
//			@SuppressWarnings("deprecation")
//			@Override
//			public void onReceive(Context context, Intent intent) {
//				Bundle bundle=intent.getExtras();//�� Intent������ȡ����һ�� Bundle ����
//				//ʹ�� pdu��Կ����ȡһ�� SMS pdus ���飬����ÿһ�� pdu ����ʾһ��������Ϣ
//				Object[] pdus=(Object[]) bundle.get("pdus");// ��ȡ������Ϣ
//				SmsMessage[] messages=new SmsMessage[pdus.length];
//				for (int i = 0; i < messages.length; i++) {
//					//��ÿһ�� pdu �ֽ�����ת��Ϊ SmsMessage ����
//					messages[i]=SmsMessage.createFromPdu((byte[]) pdus[i]);
//				}
//				
//				String address=messages[0].getOriginatingAddress();//��ȡ���ͷ�����
//				String fullMessage="";
//				for (SmsMessage message:messages) {
//					//��ȡ�����ŵ����ݣ�Ȼ��ÿһ�� SmsMessage �����еĶ�������ƴ���������������һ�������Ķ���
//					fullMessage +=message.getMessageBody();
//				}
//				
//				NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//				Notification notification=new Notification(R.drawable.message,fullMessage,System.currentTimeMillis());
//				Intent notificationIntent=new Intent(MessageActivity.this, MessageActivity.class);
//				PendingIntent pIntent=PendingIntent.getBroadcast(getBaseContext(), 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
//				notification.setLatestEventInfo(getBaseContext(), address, fullMessage, pIntent);
//				notification.defaults = Notification.DEFAULT_ALL;
//				notificationManager.notify(2,notification);
//				
//				Msg msg = new Msg(fullMessage, Msg.TYPE_RECEIVED);
//				msgList.add(msg);
//				adapter.notifyDataSetChanged();
//				msgListView.setSelection(msgList.size());
//				abortBroadcast();//��ֹ���㲥�ļ�������
//			}			
//		}
	
		
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(sendStatuaReceiver);
		unregisterReceiver(messageReceiver);//ȡ��ע��
	}

	

}
