package com.wqj.mrwen.sqlitedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private MyDBHelper myDBHelper;
    private Button btnCreateDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCreateDB = findViewById(R.id.btn_createDatabase);
        myDBHelper = new MyDBHelper(this, "book.db", null, 1);
        ButterKnife.bind(this);
        btnCreateDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDBHelper.getWritableDatabase();
            }
        });
    }
}
