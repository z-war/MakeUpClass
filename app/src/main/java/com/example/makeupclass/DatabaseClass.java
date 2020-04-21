package com.example.makeupclass;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseClass extends SQLiteOpenHelper {
    private static String DbName = "mydb.db";
    private static int DbVersion = 1;
    private Context context;
    private String querytocreatetabel = "create table mytable (name varchar(255),location varchar(255))";

    public DatabaseClass(@Nullable Context context) {
        super(context, DbName, null, DbVersion);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(querytocreatetabel);
            Toast.makeText(context, "Table is Created ", Toast.LENGTH_SHORT).show();



        } catch (Exception e) {
            Toast.makeText(context, "Error On Create"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
