package com.example.db;

import java.util.ArrayList;
import java.util.List;

import com.example.bean.Book;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MyDB {
	
	public static final String DB_NAME = "library_record";//���ݿ���
	public static final int VERSION = 1;//���ݿ�汾
	private static MyDB mDB;//MyDB��ʵ��
	private SQLiteDatabase db;//�������ݿ�����
	
	/**
	 * �����췽��˽�л���
 	       ����һ�ε��ù��췽����������ʱ���ͻ��⵽��ǰ�����в�û�� library_record.db������ݿ⣬ ���ǻᴴ�������ݿⲢ����MyOpenHelper�е� onCreate()������
	       ���� Book��Ҳ�͵õ��˴�����Ȼ��ᵯ��һ�� Toast��ʾ�����ɹ����ٴδ�������ʱ���ᷢ�ִ�ʱ�Ѿ�����  library_record.db���ݿ��ˣ���˲����ٴ���һ�Ρ�			
	 */
	private MyDB(Context context) {
		MyOpenHelper dbHelper = new MyOpenHelper(context,DB_NAME, null, VERSION);
		db = dbHelper.getWritableDatabase();
	}

	/**
	 * ��ȡMyDB��ʵ����
	 */
	public synchronized static MyDB getInstance(Context context) {
		if (mDB == null) {
			mDB = new MyDB(context);
		}
		return mDB;
	}
	
	
	public void insert(Book book){
		final String INSERT_BOOK = "INSERT INTO book(����,����,�۸�,ҳ��) " + " VALUES('"+book.getBook_name() + "','"
									+ book.getAuthor() + "','" + book.getPrice() + "','" + book.getPages()+"')";
		db.execSQL(INSERT_BOOK);
	}
	

	public List<Book> query(){
		final String QUERY_BOOK="select * from book";
		List<Book> list = new ArrayList<Book>();
		Cursor cursor=db.rawQuery(QUERY_BOOK, null);
		if (cursor.moveToFirst()) {
			do {
				//int id=cursor.getInt(cursor.getColumnIndex("id"));
				String book_name=cursor.getString(cursor.getColumnIndex("����"));
				String author=cursor.getString(cursor.getColumnIndex("����"));
				float price=cursor.getFloat(cursor.getColumnIndex("�۸�"));
				int pages=cursor.getInt(cursor.getColumnIndex("ҳ��"));
				Book book=new Book(book_name, author, price, pages);
				list.add(book);
			} while (cursor.moveToNext());
		}
		return list;
	}

}
