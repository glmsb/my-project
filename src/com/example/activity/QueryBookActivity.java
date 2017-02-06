package com.example.activity;

import java.util.List;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abstractAtactivity.BaseActivity;
import com.example.bean.Book;
import com.example.db.MyDB;
import com.example.util.LogUtil;

public class QueryBookActivity extends BaseActivity {
	private MyDB myDB;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book_list);
		myDB=MyDB.getInstance(this);
			
		List<Book> iBooks = myDB.query();
		if (iBooks.equals(null)) {
			Toast.makeText(getBaseContext(), "����ͼ�飬������ӣ�", Toast.LENGTH_SHORT)
					.show();
			LogUtil.d("������Ϣ", "Ϊ��");
		}

		else {
			CreateTable(iBooks);
			// for(int i=0;i<iBooks.size();i++){
			// LogUtil.d("id", iBooks.get(i).getId()+"");
			// LogUtil.d("book_name", iBooks.get(i).getBook_name());
			// LogUtil.d("author", iBooks.get(i).getAuthor());
			// LogUtil.d("price", iBooks.get(i).getPrice()+"");
			// LogUtil.d("pages", iBooks.get(i).getPages()+"");
			//
			// }
		}

	}
	
	
	/**
	 * �Ѳ�ѯ����ͼ����Ϣ���浽booklist�У���ȡ��д��ҳ�沼�ֵı���С�
	 */
	private void CreateTable(List<Book> booklist) {
		TableLayout table = (TableLayout) findViewById(R.id.QUERY_BOOK_ACTIVITY_TableLayout);

		for (int i = 0; i < booklist.size(); i++) {
			TableRow row = new TableRow(this);
			TextView tid = new TextView(this);
			TextView tbname = new TextView(this);
			TextView tauthor = new TextView(this);
			TextView tprice = new TextView(this);
			TextView tpages = new TextView(this);
			tid.setText(booklist.get(i).getId()+"");
			tid.setGravity(Gravity.CENTER);
			tbname.setText(booklist.get(i).getBook_name());
			tbname.setGravity(Gravity.CENTER);
			tauthor.setText(booklist.get(i).getAuthor());
			tauthor.setGravity(Gravity.CENTER);
			tprice.setText(booklist.get(i).getPrice()+"");
			tprice.setGravity(Gravity.CENTER);
			tpages.setText(booklist.get(i).getPages()+"");
			tpages.setGravity(Gravity.CENTER);
			row.addView(tid);
			row.addView(tbname);
			row.addView(tauthor);
			row.addView(tprice);
			row.addView(tpages);
			table.addView(row);
		}
	}

}
