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
		//点击通知图标时取消对应的通知信息。
		NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		manager.cancel(2);
		
		
		inputMsg = (EditText) findViewById(R.id.msg_input);
		inputRcv=(EditText) findViewById(R.id.rcv_input);
		send = (Button) findViewById(R.id.send);
		msgListView = (ListView) findViewById(R.id.msg_list_view);
		send.setOnClickListener(this);
		adapter = new MsgAdapter(MessageActivity.this, R.layout.msg_item, msgList);
		msgListView.setAdapter(adapter);
		
		//对 SendStatuaReceiver 进行动态注册，监听短信的发送状态
		sendFilter=new IntentFilter();
		sendFilter.addAction("SENT_SMS_ACTION");
		sendStatuaReceiver=new SendStatuaReceiver();
		registerReceiver(sendStatuaReceiver, sendFilter);
		
		//对 MessageReceiver 进行动态注册，注册之后才能接收到短信广播
		receiveFilter = new IntentFilter();
		receiveFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
		receiveFilter.setPriority(100);//系统发出的短信广播是一条有序广播,提高 MessageReceiver的优先级，让它能够先于系统短信程序接收到短信广播
		messageReceiver = new MessageReceiver();
		registerReceiver(messageReceiver, receiveFilter);	
	}
	
	@Override
	public void onClick(View v) {
		if (v.getId()==R.id.send){
			content = inputMsg.getText().toString();
			recerver=inputRcv.getText().toString();
			if (!("".equals(content))&&!( "".equals(recerver))) {//内容和接收方不能为空
			SmsManager smsManager=SmsManager.getDefault();
			
			Intent intent=new Intent("SENT_SMS_ACTION");
			PendingIntent pi =PendingIntent.getBroadcast(getBaseContext(), 0, intent, 0);
			
			smsManager.sendTextMessage(recerver, null, content, pi, null);//发送短信,第四个参数用来对短信的发送状态进行监控
			
			Msg msg = new Msg(content, Msg.TYPE_SENT);
			msgList.add(msg);
			adapter.notifyDataSetChanged();
			msgListView.setSelection(msgList.size());
			inputMsg.setText("");
			}
		}		
	}
	
	
	
	/**
	 * SendStatuaReceiver 这个广播接收器 专门用于监听短信发送状态的
	 */
	class SendStatuaReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context arg0, Intent arg1) {
			if (SendStatuaReceiver.this.getResultCode()==RESULT_OK) {
				Toast.makeText(arg0, "发送成功。",Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(arg0, "发送失败，请稍后再试 !",Toast.LENGTH_SHORT).show();
			}
		}	
	}
	
	
	
		/**
		创建一个广播接收器来接收系统发出的短信广播
		*/
//		class MessageReceiver extends BroadcastReceiver{
//			
//			@SuppressWarnings("deprecation")
//			@Override
//			public void onReceive(Context context, Intent intent) {
//				Bundle bundle=intent.getExtras();//从 Intent参数中取出了一个 Bundle 对象
//				//使用 pdu密钥来提取一个 SMS pdus 数组，其中每一个 pdu 都表示一条短信消息
//				Object[] pdus=(Object[]) bundle.get("pdus");// 提取短信消息
//				SmsMessage[] messages=new SmsMessage[pdus.length];
//				for (int i = 0; i < messages.length; i++) {
//					//将每一个 pdu 字节数组转换为 SmsMessage 对象
//					messages[i]=SmsMessage.createFromPdu((byte[]) pdus[i]);
//				}
//				
//				String address=messages[0].getOriginatingAddress();//提取发送方号码
//				String fullMessage="";
//				for (SmsMessage message:messages) {
//					//获取到短信的内容，然后将每一个 SmsMessage 对象中的短信内容拼接起来，就组成了一条完整的短信
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
//				abortBroadcast();//中止掉广播的继续传递
//			}			
//		}
	
		
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(sendStatuaReceiver);
		unregisterReceiver(messageReceiver);//取消注册
	}

	

}
