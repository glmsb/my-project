package com.example.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * SQLiteOpenHelper 是一个抽象类，这意味着如果我们想要使用它的话，
	就需要创建一个自己的帮助类去继承它， 借助这个类就可以非常简单地对数据库进行创建和升级。
	1.SQLiteOpenHelper 中有两个抽象方法，分别是onCreate()和 onUpgrade()，我们必须在自己的帮助类里面重写这两个方法，
	      然后分别在这两个方法中去实现创建、升级数据库的逻辑。
	2.SQLiteOpenHelper 中 还 有 两 个 非 常 重 要 的 实 例 方 法 ， getReadableDatabase() 和getWritableDatabase().
	     这两个方法都可以创建或打开一个现有的数据库（如果数据库已存在则直接打开，否则创建一个新的数据库） ，并返回一个可对数据库进行读写操作的对象。
	3.SQLiteOpenHelper中有两个构造方法可供重写。第二个参数是数据库名；三个参数允许我们在查询数据的时候返回一个自定义的 Cursor，一般都是传入 null。
	    第四个参数表示当前数据库的版本号，可用于对数据库进行升级操作。
	4.构建出SQLiteOpenHelper的实例之后，再调用它的 getReadableDatabase()或 getWritableDatabase()方法就能够创建数据库了，
	     数据库文件会存放在/data/data/<package name>/databases/目录下。
  */

public class MyOpenHelper extends SQLiteOpenHelper {
	
	// integer表示整型，real 表示浮点型，text 表示文本类型，blob 表示二进制类型。
	// primary key将 id 列设为主键，并用 autoincrement关键字表示 id列是自增长的
	public static final String CREATE_BOOK="create table book("
			+"id integer primary key autoincrement,"
			+"书名   text,"
			+"作者   text,"//一定要注意对text类型的字段的插入,要加上' ' 否则就会出现no such column xxx错误
			+"价格   real,"
			+"页数   integer)";
	
	public static final String CREATE_CATEGORY = "create table Category ("
			+ "id integer primary key autoincrement, "
			+ "category_name text, "
			+ "category_code integer)";
	
	private Context mContext;
	public MyOpenHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);//调用父类的构造方法
		mContext=context;
	}

	public MyOpenHelper(Context context,String name,CursorFactory factory,int version,DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
		mContext=context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {//创建数据库
		// 数据库创建完成的同时创建表
		db.execSQL(CREATE_BOOK);
		//再次点击按钮时，该表不会被创建，因为此时 Book.db 数据库已经存在了，之后不管我们怎样点击按钮， onCreate()方法都不会再次执行
		db.execSQL(CREATE_CATEGORY);
		Toast.makeText(mContext, "数据库创建成功！", Toast.LENGTH_LONG).show();
	};

	@Override
	//传入一个比 1 大的数， 就可以让 onUpgrade()方法得到执行了
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {//升级数据库
		// 再添加一张 Category表用于记录书籍的分类
		db.execSQL("drop table if exists Book");
		db.execSQL("drop table if exists Category");
		onCreate(db);
	}

}
