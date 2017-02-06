package com.example.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private Button loginBtn;
	private EditText accountEdit,passwordEdit;
	private CheckBox rememberPass;
	private TextView help,register;
	private SharedPreferences pref;
	private ProgressDialog dialog;
	private Handler handler = new Handler(new Handler.Callback() {  
	public boolean handleMessage(Message msg){ 
      	dialog.dismiss();//对话框消失  
        Toast.makeText(getBaseContext(), "登陆成功", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this, FirstActivity.class);
		startActivity(intent);
		finish();
		return false;     
      }  
  });  

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		
		loginBtn = (Button)findViewById(R.id.login);
		accountEdit = (EditText)findViewById(R.id.count);
		passwordEdit = (EditText)findViewById(R.id.password);
		rememberPass=(CheckBox)findViewById(R.id.remember_pass);
		
		help=(TextView)findViewById(R.id.help);
		//将HTML转化为一个Spanned（跨度）对象，格式化文本内容
		help.setText(Html.fromHtml("<a href='http://www.baidu.com'>无法登陆？</a>"));
		help.setMovementMethod(LinkMovementMethod.getInstance()); 
		register=(TextView)findViewById(R.id.register);
		register.setText(Html.fromHtml("<a href='http://10.88.1.127:8080/RentHouse/register.jsp'>新用户</a>"));
		register.setMovementMethod(LinkMovementMethod.getInstance()); 
		
		pref=PreferenceManager.getDefaultSharedPreferences(this);//获取SharedPreferences对象
		Boolean isRemember=pref.getBoolean("remember_password", false);//对存储的 数据进行读取
		
		if (isRemember) {//读取偏好数据
			//  将账号和密码都设置到文本框中
			String account = pref.getString("account", "");
			String password = pref.getString("password", "");
			accountEdit.setText(account);
			passwordEdit.setText(password);
			rememberPass.setChecked(true);
		}
	
		loginBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {	
				final String account = accountEdit.getText().toString();
				final String password = passwordEdit.getText().toString();
				if (account.equals("")) {
					Toast.makeText(getBaseContext(), "请输入账户名！！", Toast.LENGTH_SHORT).show();
				}
				else if (password.equals("")) {
					Toast.makeText(getBaseContext(), "请输入密码！！", Toast.LENGTH_SHORT).show();
				}
				
				else if(account.equals("admin") && password.equals("123456")){
					dialog = ProgressDialog.show(LoginActivity.this,  "提示", "正在登陆中.....", false);				    
					new Thread(new Runnable() {			
						@Override
						public void run() {
							try {	
								/**
								  向 SharedPreferences文件中存储数据
								 */
							Editor editor=pref.edit();//获取一个 SharedPreferences.Editor对象
							if (rememberPass.isChecked()) {// 检查复选框是否被选中
								editor.putBoolean("remember_password", true);
								editor.putString("account", account);
								editor.putString("password", password);
							}
							else {
								editor.clear();
							}
							editor.commit();//将添加的数据提交，从而完成数据存储操作
								
							Thread.sleep(1000);//模拟后台操作
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
							handler.sendEmptyMessage(0);//传送消息  
						}
					}).start();					
			} 
				else {
					Toast.makeText(getBaseContext(), "用户名和密码不匹配！！", Toast.LENGTH_SHORT).show();
				}
				
			}
			
		});
	}
}