package com.captain.wds;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		Uri parse = Uri.parse("content://com.captain.wds.IPC.BookContenProvider/book");
		ContentValues values = new ContentValues();
		values.put("_id", 6);
		values.put("name", "android 开发");

		getContentResolver().insert(parse, values);

		Cursor cursor = getContentResolver().query(parse, new String[] { "_id", "name" }, null, null, null);

		while (cursor.moveToNext()) {
			int id = cursor.getInt(0);
			String name = cursor.getString(1);

			Log.e("TAG", "id=" + id + "\t name=" + name);
		}
		cursor.close();




		Uri userUri = Uri.parse("content://com.captain.wds.IPC.BookContenProvider/user");
		ContentValues userValues = new ContentValues();
		userValues.put("_id",3);
		userValues.put("name","小崔");
		getContentResolver().insert(userUri, userValues);

		Cursor userCursor = getContentResolver().query(userUri,new String[]{"_id","name"},null,null,null);
		while (userCursor.moveToNext()){
			int id = userCursor.getInt(0);
			String name = userCursor.getString(1);

			Log.e("TAG", "id=" + id + "\t name=" + name );
		}
		userCursor.close();


	}


}
