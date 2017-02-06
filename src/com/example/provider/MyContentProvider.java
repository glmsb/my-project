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
	//返回相应的 MIME类型
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
	//projection参数用于确定查询哪些列，selection和 selectionArgs参数用于约束查询哪些行，
	//sortOrder 参数用于对结果进行排序，查询的结果存放在 Cursor 对象中返回
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,String sortOrder) {
		// TODO Auto-generated method stub
		switch (uriMatcher.match(uri)) {
		case TABLE1_DIR:
			//查询table1表中的所有数据
			break;
		case TABLE1_ITEM:
			// 查询table1表中的单条数据
			break;
		case TABLE2_DIR:
			//查询table2表中的所有数据
			break;
		case TABLE2_ITEM:
			// 查询table2表中的单条数据
	
	break;

		default:
			break;
		}
		return null;
	}

	@Override
	//selection和 selectionArgs参数用于约束删除哪些行，被删除的行数将作为返回值返回
	public int delete(Uri arg0, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	

	@Override
	//待添加的数据保存在 values 参数中。添加完成后，返回一个用于表示这条新记录的 URI
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
	//新数据保存在 values 参数中，selection 和 selectionArgs 参数用于约束更新哪些行，受影响的行数将作为返回值返回。
	public int update(Uri arg0, ContentValues values, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
