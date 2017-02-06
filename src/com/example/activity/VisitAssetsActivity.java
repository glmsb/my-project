package com.example.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class VisitAssetsActivity extends Activity {
	private Handler handler= new Handler();
	private WebView webView;
	private ImageView imageView;
	private AnimationDrawable ad;
	private LinearLayout anim;

	@SuppressLint({ "NewApi", "SetJavaScriptEnabled" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.third_layout);
		
		imageView = (ImageView) findViewById(R.id.imageView);
		ad = (AnimationDrawable) imageView.getDrawable(); // 获取图片内容, 强转为动画对象
		anim=(LinearLayout)findViewById(R.id.anim);
		ad.start(); // 开始播放
		webView = (WebView) findViewById(R.id.web_view);				// 根据ID找到WebView
		webView.getSettings().setJavaScriptEnabled(true);			// 允许JS
		webView.setWebViewClient(new WebViewClient(){
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);// 根据传入的参数再去加载新的网页
			return true;// 表示当前WebView可以处理打开新网页的请求，不用借助系统浏览器		
		}
		});		
		webView.loadUrl("file:///android_asset/index.html");		// 加载页面
	    webView.addJavascriptInterface(new Contact(), "contact");	// 创建Contact对象, 传给WebView, 作为JS对象	
	}
	
	class Contact {
		@JavascriptInterface
		public void showContacts() {
			handler.post(new Runnable(){
				@Override
				public void run(){
					String json = "[{name:\"王小二\", amount:\"12345\", phone:\"18600012345\"}, {name:\"黎明\", amount:\"54321\", phone:\"18600054321\"}]";
					webView.loadUrl("javascript:show('" + json + "')");		// 调用JS方法//   把js数据,传递给html页面
					
					ad.stop();	
					webView.setVisibility(View.VISIBLE);
					anim.setVisibility(View.GONE);
				}
			});	
		}
		
		@JavascriptInterface
		public void call(final String phone) {
			startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel://" + phone)));
		}
	}
	
}
