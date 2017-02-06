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
		ad = (AnimationDrawable) imageView.getDrawable(); // ��ȡͼƬ����, ǿתΪ��������
		anim=(LinearLayout)findViewById(R.id.anim);
		ad.start(); // ��ʼ����
		webView = (WebView) findViewById(R.id.web_view);				// ����ID�ҵ�WebView
		webView.getSettings().setJavaScriptEnabled(true);			// ����JS
		webView.setWebViewClient(new WebViewClient(){
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);// ���ݴ���Ĳ�����ȥ�����µ���ҳ
			return true;// ��ʾ��ǰWebView���Դ��������ҳ�����󣬲��ý���ϵͳ�����		
		}
		});		
		webView.loadUrl("file:///android_asset/index.html");		// ����ҳ��
	    webView.addJavascriptInterface(new Contact(), "contact");	// ����Contact����, ����WebView, ��ΪJS����	
	}
	
	class Contact {
		@JavascriptInterface
		public void showContacts() {
			handler.post(new Runnable(){
				@Override
				public void run(){
					String json = "[{name:\"��С��\", amount:\"12345\", phone:\"18600012345\"}, {name:\"����\", amount:\"54321\", phone:\"18600054321\"}]";
					webView.loadUrl("javascript:show('" + json + "')");		// ����JS����//   ��js����,���ݸ�htmlҳ��
					
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
