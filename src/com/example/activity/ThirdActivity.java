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
		ad = (AnimationDrawable) imageView.getDrawable(); // 获取图片内容, 强转为动画对象
		anim=(LinearLayout)findViewById(R.id.anim);
		ad.start(); // 开始播放
		
		
		
		
		/**
		 当网络状态发生变化时，系统发出的正是一条值为 android.net.conn.CONNECTIVITY_CHANGE 的广播，
		 也就是说我们的广播接收器想要监听什么广播，就在这里添加相应的action 就行了
		*/
		intentFilter=new IntentFilter();
		intentFilter.addAction(android.net.ConnectivityManager.CONNECTIVITY_ACTION);
		//NetworkChangedReceiver就会收到所有值为android.net.conn.CONNECTIVITY_CHANGE的广播，也就实现了监听网络变化的功能。
		networkChangeReceiver=new NetworkChangedReceiver();
		registerReceiver(networkChangeReceiver, intentFilter);
		
		
		
		
		
		/**
		 * 使用webview控件在应用程序里展示一些网页，
		 * 借助它可以在自己的应用程序里嵌入一个浏览器，从而非常轻松地展示各种各样的网页
		 * */
		webView = (WebView) findViewById(R.id.web_view);
		Intent intent=getIntent();
		final String url=intent.getStringExtra("url");	
		webView.getSettings().setJavaScriptEnabled(true);//设置一些浏览器的属性
		webView.setWebViewClient(new WebViewClient(){
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);// 根据传入的参数再去加载新的网页
			return true;// 表示当前WebView可以处理打开新网页的请求，不用借助系统浏览器		
		}
		});				
						
		handler.post(new Runnable() {				
				public void run() {	
				webView.loadUrl(url);				
		        handler.post(runnable);
				}	
				
		});
		

		
		/**
		 * 一键退出程序
		 * */
		retrun=(Button) findViewById(R.id.retrun);
		//使用匿名类方式注册监听器		
				retrun.setOnClickListener(new OnClickListener(){
					public void onClick(View v){
						ActivityCollector.finishAll();//退出程序，关闭所有活动
					}
					
				});
	
	}
	
	@Override
	protected void onDestroy() {
	super.onDestroy();
	unregisterReceiver(networkChangeReceiver);//动态注册的广播接收器一定都要取消注册才行
	}
	
	

}
