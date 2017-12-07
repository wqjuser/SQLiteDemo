package com.wqj.mrwen.sqlitedemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private MyDBHelper myDBHelper;
    private Button btnCreateDB;
    private Button btnInsertData;
    private Button btnUpdateData;
    private Button btnDeleteData;
    private Button btnQueryData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCreateDB = findViewById(R.id.btn_createDatabase);
        btnInsertData = findViewById(R.id.btn_insert_data);
        btnUpdateData = findViewById(R.id.btn_update_data);
        btnDeleteData = findViewById(R.id.btn_delete_data);
        btnQueryData = findViewById(R.id.btn_query_data);
        myDBHelper = new MyDBHelper(this, "book.db", null, 5);
        ButterKnife.bind(this);
        btnCreateDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDBHelper.getWritableDatabase();
            }
        });
        btnInsertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = myDBHelper.getWritableDatabase();
                db.execSQL(getString(R.string.insertData), new String[]{"My Code", "WQJ", "123", "0.01"});
                ContentValues values = new ContentValues();
                //开始插入数据
//                values.put("name", "The First Vinci Code");
//                values.put("author", "Dan Brown");
//                values.put("pages", "245");
//                values.put("price", "13.23");
//                values.put("","");
//                db.insert("Book", null, values);
//                values.clear();
                values.put("name", "The Second Vinci Code");
                values.put("author", "Dan Brown");
                values.put("pages", "345");
                values.put("price", "13.23");
//                values.put("","");
                db.insert("Book", null, values);
                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });
        btnUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = myDBHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price", "324.324");
                db.update("Book", values, "name=?", new String[]{"The Da Vinci Code"});
            }
        });
        btnDeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = myDBHelper.getWritableDatabase();
                db.delete("Book", "pages>?", new String[]{"300"});
            }
        });
        btnQueryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = myDBHelper.getWritableDatabase();
                Cursor cursor = db.query("Book", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        //遍历Cursor对象
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        String result = "The book is" + name + "and author is " + author + "and pages is "
                                + String.valueOf(pages) + "and price is " + String.valueOf(price);
                        Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        });
    }
}
