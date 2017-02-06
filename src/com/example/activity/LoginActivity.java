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
      	dialog.dismiss();//�Ի�����ʧ  
        Toast.makeText(getBaseContext(), "��½�ɹ�", Toast.LENGTH_SHORT).show();
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
		//��HTMLת��Ϊһ��Spanned����ȣ����󣬸�ʽ���ı�����
		help.setText(Html.fromHtml("<a href='http://www.baidu.com'>�޷���½��</a>"));
		help.setMovementMethod(LinkMovementMethod.getInstance()); 
		register=(TextView)findViewById(R.id.register);
		register.setText(Html.fromHtml("<a href='http://10.88.1.127:8080/RentHouse/register.jsp'>���û�</a>"));
		register.setMovementMethod(LinkMovementMethod.getInstance()); 
		
		pref=PreferenceManager.getDefaultSharedPreferences(this);//��ȡSharedPreferences����
		Boolean isRemember=pref.getBoolean("remember_password", false);//�Դ洢�� ���ݽ��ж�ȡ
		
		if (isRemember) {//��ȡƫ������
			//  ���˺ź����붼���õ��ı�����
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
					Toast.makeText(getBaseContext(), "�������˻�������", Toast.LENGTH_SHORT).show();
				}
				else if (password.equals("")) {
					Toast.makeText(getBaseContext(), "���������룡��", Toast.LENGTH_SHORT).show();
				}
				
				else if(account.equals("admin") && password.equals("123456")){
					dialog = ProgressDialog.show(LoginActivity.this,  "��ʾ", "���ڵ�½��.....", false);				    
					new Thread(new Runnable() {			
						@Override
						public void run() {
							try {	
								/**
								  �� SharedPreferences�ļ��д洢����
								 */
							Editor editor=pref.edit();//��ȡһ�� SharedPreferences.Editor����
							if (rememberPass.isChecked()) {// ��鸴ѡ���Ƿ�ѡ��
								editor.putBoolean("remember_password", true);
								editor.putString("account", account);
								editor.putString("password", password);
							}
							else {
								editor.clear();
							}
							editor.commit();//����ӵ������ύ���Ӷ�������ݴ洢����
								
							Thread.sleep(1000);//ģ���̨����
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
							handler.sendEmptyMessage(0);//������Ϣ  
						}
					}).start();					
			} 
				else {
					Toast.makeText(getBaseContext(), "�û��������벻ƥ�䣡��", Toast.LENGTH_SHORT).show();
				}
				
			}
			
		});
	}
}