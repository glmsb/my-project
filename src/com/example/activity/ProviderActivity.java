package com.example.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ProviderActivity extends Activity {

	private ListView contactsView;
	ArrayAdapter<String> adapter=null;
	List<String> contactsList=new ArrayList<String>();
	
	/**
	 * ������Լ��ĳ����з�������Ӧ�ó��������,1��ֻ��Ҫ��ȡ����Ӧ�ó�������� URI��2��Ȼ����� ContentResolver ����CRUD�����Ϳ�����
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.provider_activity);
		
		contactsView=(ListView) findViewById(R.id.contacts_view);//��ȡ�� ListView�ؼ���ʵ��
		
		readContacts();
		adapter=new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1,contactsList);
		contactsView.setAdapter(adapter);//����������
		
	}

	private void readContacts() {// ��ѯ��ϵ������
		Cursor cursor=null;		
		try {
//			Uri allContacts=Uri.parse("content://contacts/people");
//			CONTENT_URI����������ʹ�� Uri.parse()�������������Ľ��,�������allContacts���һ����
			//��ѯϵͳ����ϵ������,ͶӰ��ɸѡ��������������������
			cursor=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null,
					ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC");//��������������
			while(cursor.moveToNext()){
			// ��ȡ��ϵ������
			String name=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
			// ��ȡ��ϵ���ֻ���
			String number=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			//�������ݶ�ȡ��֮�󣬽����ǽ���ƴ�ӣ������м���ϻ��з�
			contactsList.add(name+"\n"+number);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if (cursor!=null) {
				cursor.close();//�� Cursor ����رյ�
			}
		}
		
	}

}
