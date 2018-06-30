package com.captain.wds.IPC;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by wds on 2018/4/2.
 */

public class DBOpenHelper extends SQLiteOpenHelper {

	public static final int		DB_VERSION			= 2;

	public static final String	DB_NAME				= "book_provider.db";

	public static String		USER_TABLE_NAME		= "user";

	public static String		BOOK_TABLE_NAME		= "book";

	private String				CREATE_BOOK_TABLE	= "CREATE TABLE IF NOT EXISTS " + BOOK_TABLE_NAME + "(_id INTEGER PRIMARY KEY,"+"name TEXT)";

	private String				CREATE_USER_TABLE	= "CREATE TABLE IF NOT EXISTS " + USER_TABLE_NAME + "(_id INTEGER PRIMARY KEY,"+"name TEXT)";

	public DBOpenHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION, null);
	}

	// public DBOpenHelper(Context context, String name,
	// SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler
	// errorHandler) {
	// super(context, DB_NAME, factory, DB_VERSION, errorHandler);
	// }
	String sql = "create table personData("+
			"id        INTEGER PRIMARY KEY," +
			"name      TEXT,)";
	@Override public void onCreate(SQLiteDatabase db) {
		/*创建user 和book数据库表格*/
		db.execSQL(CREATE_BOOK_TABLE);
		db.execSQL(CREATE_USER_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.e("TAG", "oldVersion = " + oldVersion + "\tnewVersion = " + newVersion);
	}
}
