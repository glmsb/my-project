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
	 * 如何在自己的程序中访问其他应用程序的数据,1、只需要获取到该应用程序的内容 URI，2、然后借助 ContentResolver 进行CRUD操作就可以了
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.provider_activity);
		
		contactsView=(ListView) findViewById(R.id.contacts_view);//获取了 ListView控件的实例
		
		readContacts();
		adapter=new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1,contactsList);
		contactsView.setAdapter(adapter);//设置适配器
		
	}

	private void readContacts() {// 查询联系人数据
		Cursor cursor=null;		
		try {
//			Uri allContacts=Uri.parse("content://contacts/people");
//			CONTENT_URI个常量就是使用 Uri.parse()方法解析出来的结果,与上面的allContacts结果一样；
			//查询系统的联系人数据,投影，筛选（条件，参数），排序
			cursor=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null,
					ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC");//按名字升序排列
			while(cursor.moveToNext()){
			// 获取联系人姓名
			String name=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
			// 获取联系人手机号
			String number=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			//两个数据都取出之后，将它们进行拼接，并且中间加上换行符
			contactsList.add(name+"\n"+number);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if (cursor!=null) {
				cursor.close();//将 Cursor 对象关闭掉
			}
		}
		
	}

}
