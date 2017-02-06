package com.example.activity;

import com.example.abstractAtactivity.ActivityCollector;
import com.example.abstractAtactivity.BaseActivity;
import com.example.receiver.NetworkChangedReceiver;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

@SuppressLint("SetJavaScriptEnabled")
public class ThirdActivity extends BaseActivity{
	private WebView webView;
	private Button retrun;
	private ImageView imageView;
	private AnimationDrawable ad;
	private LinearLayout anim;
	private NetworkChangedReceiver networkChangeReceiver;
	private IntentFilter intentFilter;
	private Handler handler = new Handler();
	private Runnable runnable=new Runnable() {			
		@Override
		public void run() {
			ad.stop();	
			webView.setVisibility(View.VISIBLE);
			anim.setVisibility(View.GONE);
		}
	};
	 

	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.third_layout);

		imageView = (ImageView) findViewById(R.id.imageView);
		ad = (AnimationDrawable) imageView.getDrawable(); // ��ȡͼƬ����, ǿתΪ��������
		anim=(LinearLayout)findViewById(R.id.anim);
		ad.start(); // ��ʼ����
		
		
		
		
		/**
		 ������״̬�����仯ʱ��ϵͳ����������һ��ֵΪ android.net.conn.CONNECTIVITY_CHANGE �Ĺ㲥��
		 Ҳ����˵���ǵĹ㲥��������Ҫ����ʲô�㲥���������������Ӧ��action ������
		*/
		intentFilter=new IntentFilter();
		intentFilter.addAction(android.net.ConnectivityManager.CONNECTIVITY_ACTION);
		//NetworkChangedReceiver�ͻ��յ�����ֵΪandroid.net.conn.CONNECTIVITY_CHANGE�Ĺ㲥��Ҳ��ʵ���˼�������仯�Ĺ��ܡ�
		networkChangeReceiver=new NetworkChangedReceiver();
		registerReceiver(networkChangeReceiver, intentFilter);
		
		
		
		
		
		/**
		 * ʹ��webview�ؼ���Ӧ�ó�����չʾһЩ��ҳ��
		 * �������������Լ���Ӧ�ó�����Ƕ��һ����������Ӷ��ǳ����ɵ�չʾ���ָ�������ҳ
		 * */
		webView = (WebView) findViewById(R.id.web_view);
		Intent intent=getIntent();
		final String url=intent.getStringExtra("url");	
		webView.getSettings().setJavaScriptEnabled(true);//����һЩ�����������
		webView.setWebViewClient(new WebViewClient(){
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);// ���ݴ���Ĳ�����ȥ�����µ���ҳ
			return true;// ��ʾ��ǰWebView���Դ��������ҳ�����󣬲��ý���ϵͳ�����		
		}
		});				
						
		handler.post(new Runnable() {				
				public void run() {	
				webView.loadUrl(url);				
		        handler.post(runnable);
				}	
				
		});
		

		
		/**
		 * һ���˳�����
		 * */
		retrun=(Button) findViewById(R.id.retrun);
		//ʹ�������෽ʽע�������		
				retrun.setOnClickListener(new OnClickListener(){
					public void onClick(View v){
						ActivityCollector.finishAll();//�˳����򣬹ر����л
					}
					
				});
	
	}
	
	@Override
	protected void onDestroy() {
	super.onDestroy();
	unregisterReceiver(networkChangeReceiver);//��̬ע��Ĺ㲥������һ����Ҫȡ��ע�����
	}
	
	

}
