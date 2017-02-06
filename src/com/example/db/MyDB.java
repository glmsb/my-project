package com.example.db;

import java.util.ArrayList;
import java.util.List;

import com.example.bean.Book;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MyDB {
	
	public static final String DB_NAME = "library_record";//数据库名
	public static final int VERSION = 1;//数据库版本
	private static MyDB mDB;//MyDB的实例
	private SQLiteDatabase db;//创建数据库连接
	
	/**
	 * 将构造方法私有化。
 	       当第一次调用构造方法创建对象时，就会检测到当前程序中并没有 library_record.db这个数据库， 于是会创建该数据库并调用MyOpenHelper中的 onCreate()方法，
	       这样 Book表也就得到了创建，然后会弹出一个 Toast提示创建成功。再次创建对象时，会发现此时已经存在  library_record.db数据库了，因此不会再创建一次。			
	 */
	private MyDB(Context context) {
		MyOpenHelper dbHelper = new MyOpenHelper(context,DB_NAME, null, VERSION);
		db = dbHelper.getWritableDatabase();
	}

	/**
	 * 获取MyDB的实例。
	 */
	public synchronized static MyDB getInstance(Context context) {
		if (mDB == null) {
			mDB = new MyDB(context);
		}
		return mDB;
	}
	
	
	public void insert(Book book){
		final String INSERT_BOOK = "INSERT INTO book(书名,作者,价格,页数) " + " VALUES('"+book.getBook_name() + "','"
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
				String book_name=cursor.getString(cursor.getColumnIndex("书名"));
				String author=cursor.getString(cursor.getColumnIndex("作者"));
				float price=cursor.getFloat(cursor.getColumnIndex("价格"));
				int pages=cursor.getInt(cursor.getColumnIndex("页数"));
				Book book=new Book(book_name, author, price, pages);
				list.add(book);
			} while (cursor.moveToNext());
		}
		return list;
	}

}
