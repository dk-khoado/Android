package com.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    //properties table
    private static String TABLE_NAME = "users";
    private static String TABLE_a_id = "ID";
    private static String TABLE_a_username = "UserName";
    private static String TABLE_a_password = "Password";

    //query-->
    private String createTable = "create table " + TABLE_NAME + "(" +
            TABLE_a_id + " integer primary key  autoincrement not null, " +
            TABLE_a_username + " text not null, " +
            TABLE_a_password + " text not null);";
    //<--end
    private SQLiteDatabase database;
    private static final String DATABASE_NAME = "SQL.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        database = this.getWritableDatabase();
    }

    public void Insert(String username, String password) {
        String insert = "insert into "+TABLE_NAME+"(" + TABLE_a_username + "," + TABLE_a_password + ") values ('" + username + "','" + password + "')";
        database.execSQL(insert);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("quần què trinh", createTable);
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Log.i("quần què trinh", Users.createTable);


    }
}
