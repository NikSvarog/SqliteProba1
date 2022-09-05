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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SqliteDbHelper sqliteDbHelper;
    Button btnAdd, btnRead, btnClear;
    EditText edFirstName, edLastName;
    TextView tvTitle;
    public static final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqliteDbHelper = new SqliteDbHelper(this);
        edFirstName = findViewById(R.id.edFirstName);
        edLastName = findViewById(R.id.edLastName);
        tvTitle = findViewById(R.id.tvTitle);
    }


    public void buttonAdd(View view) {
        SQLiteDatabase db = sqliteDbHelper.getWritableDatabase();
        String firstName = edFirstName.getText().toString();
        String lastName = edLastName.getText().toString();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constans.FIRST_NAME,firstName);
        contentValues.put(Constans.LAST_NAME,lastName);
        db.insert(Constans.TABLE_NAME,null, contentValues);
       // tvTitle.append(firstName + " " + lastName);
      //  tvTitle.append("\n");
    }

    public void buttonRead(View view) {
        tvTitle.setText("");
        SQLiteDatabase db = sqliteDbHelper.getReadableDatabase();
        Cursor cursor = db.query(Constans.TABLE_NAME,null,null,null,null,null,null);
        List <String> itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(Constans._ID));
            String titleName = cursor.getString(cursor.getColumnIndex(Constans.FIRST_NAME));
            String titleLastName = cursor.getString(cursor.getColumnIndex(Constans.LAST_NAME));
            String namber = Integer.toString(id);
            String title = (namber + " " +titleName + " " + titleLastName);
            itemIds.add(title);
        }
        cursor.close();
        for (String name : itemIds){
            tvTitle.append(name);
            tvTitle.append("\n");
        }
    }

    public void buttonClear(View view) {
        tvTitle.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sqliteDbHelper.close();
    }
}