package com.example.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * SQLiteOpenHelper ��һ�������࣬����ζ�����������Ҫʹ�����Ļ���
	����Ҫ����һ���Լ��İ�����ȥ�̳����� ���������Ϳ��Էǳ��򵥵ض����ݿ���д�����������
	1.SQLiteOpenHelper �����������󷽷����ֱ���onCreate()�� onUpgrade()�����Ǳ������Լ��İ�����������д������������
	      Ȼ��ֱ���������������ȥʵ�ִ������������ݿ���߼���
	2.SQLiteOpenHelper �� �� �� �� �� �� �� �� Ҫ �� ʵ �� �� �� �� getReadableDatabase() ��getWritableDatabase().
	     ���������������Դ������һ�����е����ݿ⣨������ݿ��Ѵ�����ֱ�Ӵ򿪣����򴴽�һ���µ����ݿ⣩ ��������һ���ɶ����ݿ���ж�д�����Ķ���
	3.SQLiteOpenHelper�����������췽���ɹ���д���ڶ������������ݿ����������������������ڲ�ѯ���ݵ�ʱ�򷵻�һ���Զ���� Cursor��һ�㶼�Ǵ��� null��
	    ���ĸ�������ʾ��ǰ���ݿ�İ汾�ţ������ڶ����ݿ��������������
	4.������SQLiteOpenHelper��ʵ��֮���ٵ������� getReadableDatabase()�� getWritableDatabase()�������ܹ��������ݿ��ˣ�
	     ���ݿ��ļ�������/data/data/<package name>/databases/Ŀ¼�¡�
  */

public class MyOpenHelper extends SQLiteOpenHelper {
	
	// integer��ʾ���ͣ�real ��ʾ�����ͣ�text ��ʾ�ı����ͣ�blob ��ʾ���������͡�
	// primary key�� id ����Ϊ���������� autoincrement�ؼ��ֱ�ʾ id������������
	public static final String CREATE_BOOK="create table book("
			+"id integer primary key autoincrement,"
			+"����   text,"
			+"����   text,"//һ��Ҫע���text���͵��ֶεĲ���,Ҫ����' ' ����ͻ����no such column xxx����
			+"�۸�   real,"
			+"ҳ��   integer)";
	
	public static final String CREATE_CATEGORY = "create table Category ("
			+ "id integer primary key autoincrement, "
			+ "category_name text, "
			+ "category_code integer)";
	
	private Context mContext;
	public MyOpenHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);//���ø���Ĺ��췽��
		mContext=context;
	}

	public MyOpenHelper(Context context,String name,CursorFactory factory,int version,DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
		mContext=context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {//�������ݿ�
		// ���ݿⴴ����ɵ�ͬʱ������
		db.execSQL(CREATE_BOOK);
		//�ٴε����ťʱ���ñ��ᱻ��������Ϊ��ʱ Book.db ���ݿ��Ѿ������ˣ�֮�󲻹��������������ť�� onCreate()�����������ٴ�ִ��
		db.execSQL(CREATE_CATEGORY);
		Toast.makeText(mContext, "���ݿⴴ���ɹ���", Toast.LENGTH_LONG).show();
	};

	@Override
	//����һ���� 1 ������� �Ϳ����� onUpgrade()�����õ�ִ����
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {//�������ݿ�
		// �����һ�� Category�����ڼ�¼�鼮�ķ���
		db.execSQL("drop table if exists Book");
		db.execSQL("drop table if exists Category");
		onCreate(db);
	}

}
