package com.example.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

/**
 * @author TMD
 *
 */
public class MyContentProvider extends ContentProvider {

	public static final int TABLE1_DIR=0;
	public static final int TABLE1_ITEM=1;
	public static final int TABLE2_DIR=2;
	public static final int TABLE2_ITEM=3;
	public static final String AUTHORITY = "com.example.contactstest.provider";
	private static UriMatcher uriMatcher;
	static{
		uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(AUTHORITY, "/table1", TABLE1_DIR);
		uriMatcher.addURI(AUTHORITY, "/table1/#", TABLE1_ITEM);
		uriMatcher.addURI("com.example.app.provider", "/table2", TABLE2_DIR);
		uriMatcher.addURI("com.example.app.provider", "/table2/#", TABLE2_ITEM);
	}
	
	
	@Override
	//������Ӧ�� MIME����
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		switch (uriMatcher.match(uri)) {
		case TABLE1_DIR:
			return "vnd.android.cursor.dir.vnd.com.example.app.provider.table1";
			
		case TABLE1_ITEM:
			return "vnd.android.cursor.item.vnd.com.example.app.provider.table1";
			
		case TABLE2_DIR:
			return "vnd.android.cursor.dir.vnd.com.example.app.provider.table2";
			
		case TABLE2_ITEM:
			return "vnd.android.cursor.item.vnd.com.example.app.provider.table2";
	
	

		default:
			return null;
		
		}
		
	}
	
	@Override
	//projection��������ȷ����ѯ��Щ�У�selection�� selectionArgs��������Լ����ѯ��Щ�У�
	//sortOrder �������ڶԽ���������򣬲�ѯ�Ľ������� Cursor �����з���
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,String sortOrder) {
		// TODO Auto-generated method stub
		switch (uriMatcher.match(uri)) {
		case TABLE1_DIR:
			//��ѯtable1���е���������
			break;
		case TABLE1_ITEM:
			// ��ѯtable1���еĵ�������
			break;
		case TABLE2_DIR:
			//��ѯtable2���е���������
			break;
		case TABLE2_ITEM:
			// ��ѯtable2���еĵ�������
	
	break;

		default:
			break;
		}
		return null;
	}

	@Override
	//selection�� selectionArgs��������Լ��ɾ����Щ�У���ɾ������������Ϊ����ֵ����
	public int delete(Uri arg0, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	

	@Override
	//����ӵ����ݱ����� values �����С������ɺ󣬷���һ�����ڱ�ʾ�����¼�¼�� URI
	public Uri insert(Uri arg0, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	
	@Override
	//�����ݱ����� values �����У�selection �� selectionArgs ��������Լ��������Щ�У���Ӱ�����������Ϊ����ֵ���ء�
	public int update(Uri arg0, ContentValues values, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
