package com.example.sqliteproba1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sqliteproba1.db.Constans;
import com.example.sqliteproba1.db.SqliteDbHelper;

public class MainActivity extends AppCompatActivity {
    SqliteDbHelper sqliteDbHelper;
    Button btnAdd, btnRead, btnClear;
    EditText edFirstName, edLastName;
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SqliteDbHelper sqliteDbHelper = new SqliteDbHelper(this);
        edFirstName = findViewById(R.id.edFirstName);
        edLastName = findViewById(R.id.edLastName);
        tvTitle = findViewById(R.id.tvTitle);
    }


    public void buttonAdd(View view) {
        String firstName = edFirstName.getText().toString();
        String lastName = edLastName.getText().toString();
        SQLiteDatabase database = sqliteDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constans.FIRST_NAME,firstName);
        contentValues.put(Constans.LAST_NAME,lastName);
        database.insert(Constans.TABLE_NAME,null, contentValues);
    }

    public void buttonRead(View view) {
        SQLiteDatabase database = sqliteDbHelper.getReadableDatabase();
        Cursor cursor = database.query(Constans.TABLE_NAME,null,null,null,null,null,null);

    }

    public void buttonClear(View view) {
    }
}