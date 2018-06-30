package com.captain.wds.IPC;

import android.annotation.SuppressLint;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by wds on 2018/4/2.
 */

public class BookContenProvider extends ContentProvider {

	private static final String		TAG					= "com.captain.wds.IPC.BookContenProvider";

	private Context					context;

	private SQLiteDatabase			mDb;

	public static final String		AUTHORITY			= "com.captain.wds.IPC.BookContenProvider";

	public static final Uri			BOOK_CONTENT_URI	= Uri.parse("content://" + AUTHORITY + "/book");

	public static final Uri			USER_CONTENT_URI	= Uri.parse("content://" + AUTHORITY + "/user");

	public static final int			BOOK_URI_CODE		= 0;

	public static final int			USER_URI_CODE		= 1;

	public static final UriMatcher	sUriMatcher			= new UriMatcher(UriMatcher.NO_MATCH);

	static {
		sUriMatcher.addURI(AUTHORITY, "book", BOOK_URI_CODE);
		sUriMatcher.addURI(AUTHORITY, "user", USER_URI_CODE);
	}

	/**
	 * ContentProvider 创建后调用，Android开机后，contentProvider在其他应用第一次访问会被创建
	 * @return
	 */
	@SuppressLint("LongLogTag") @Override public boolean onCreate() {
		Log.e(TAG, "onCreate onCurrent thread：" + Thread.currentThread().getName());
		context = getContext();
		initProviderDate();
		return true;
	}

	private void initProviderDate() {
		mDb = new DBOpenHelper(context).getWritableDatabase();
		mDb.execSQL("delete from " + DBOpenHelper.BOOK_TABLE_NAME);
		mDb.execSQL("delete from " + DBOpenHelper.USER_TABLE_NAME);

		mDb.execSQL("insert into book values(3,'Android');");
		mDb.execSQL("insert into book values(4,'IOS');");
		mDb.execSQL("insert into book values(5,'Html5');");
		mDb.execSQL("insert into user values(1,'jake');");
		mDb.execSQL("insert into user values(2,'Jasmine');");
	}

	/**
	 * 该方法用于供外部应用往ContentProvide获取数据数据
	 * @param uri
	 * @param projection
	 * @param selection
	 * @param selectionArgs
	 * @param sortOrder
	 * @return
	 */
	@SuppressLint("LongLogTag") @Nullable @Override public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs,
			@Nullable String sortOrder) {
		Log.i(TAG, "query,current thread:" + Thread.currentThread().getName());
		String table = getTableName(uri);
		if (table == null) {
			throw new IllegalArgumentException("Unsupported URI:" + uri);
		}
		return mDb.query(table, projection, selection, selectionArgs, null, null, sortOrder, null);
	}

	/**
	 * 该方法用于返回当前Uri所代表数据的MIME类型。如果操作的数据属于集合数据，那么MIME类型字符串应该以vnd.android.cursor.dir/开头
	 *
	 * @param uri
	 * @return
	 */
	@SuppressLint("LongLogTag")
	@Nullable
	@Override
	public String getType(@NonNull Uri uri) {
		Log.i(TAG, "getType");
		return null;
	}

	/**
	 * 该方法用于供外部应用往ContentProvide添加数据
	 * @param uri
	 * @param values
	 * @return
	 */
	@SuppressLint("LongLogTag") @Nullable @Override public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
		Log.i(TAG, "insert");
		String table = getTableName(uri);
		if (table == null) {
			throw new IllegalArgumentException("Unsupported URI:" + uri);
		}
		mDb.insert(table, null, values);
		context.getContentResolver().notifyChange(uri, null);
		return uri;
	}

	/**
	 * 该方法用于供外部应用往ContentProvide删除数据
	 * @param uri
	 * @param selection
	 * @param selectionArgs
	 * @return
	 */
	@SuppressLint("LongLogTag") @Override public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
		Log.i(TAG, "delete");
		String table = getTableName(uri);
		if (table == null) {
			throw new IllegalArgumentException("Unsupported URI:" + uri);
		}
		int count = mDb.delete(table, selection, selectionArgs);
		if (count > 0) {
			getContext().getContentResolver().notifyChange(uri, null);
		}
		return count;
	}

	/**
	 * 该方法用于供外部应用往ContentProvide更新数据
	 * @param uri
	 * @param values
	 * @param selection
	 * @param selectionArgs
	 * @return
	 */
	@SuppressLint("LongLogTag") @Override public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
		Log.i(TAG, "update");
		String table = getTableName(uri);
		if (table == null) {
			throw new IllegalArgumentException("Unsupported URI:" + uri);
		}
		int row = mDb.update(table, values, selection, selectionArgs);
		if (row > 0) {
			getContext().getContentResolver().notifyChange(uri, null);
		}
		return row;
	}

	private String getTableName(Uri uri) {
		String tableName = null;
		switch (sUriMatcher.match(uri)) {
			case BOOK_URI_CODE:
				tableName = DBOpenHelper.BOOK_TABLE_NAME;
				break;
			case USER_URI_CODE:
				tableName = DBOpenHelper.USER_TABLE_NAME;
				break;
		}
		return tableName;
	}
}
