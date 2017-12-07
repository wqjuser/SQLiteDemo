package com.wqj.mrwen.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by MR.WEN on 2017/12/7.
 */

public class MyDBHelper extends SQLiteOpenHelper {
    private static final String CREATE_BOOK = "create table Book("
            + "ID integer primary key autoincrement,"
            + "author text,"
            + "price real,"
            + "pages integer,"
            + "name text)";
    private Context context;


    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_BOOK);
        Toast.makeText(context, "创建数据库成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
