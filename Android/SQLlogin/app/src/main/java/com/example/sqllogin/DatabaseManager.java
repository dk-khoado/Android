package com.example.sqllogin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.print.PrinterId;
import android.util.Log;
import android.widget.Toast;

public class DatabaseManager {
    private final  String DATABASE_NAME = "SQL";
    private final  String  TABLE = "demo";
    private final  String  id = "ID";
    private final  String  USER = "username";
    private final  String  PASSWORD = "password";

    //String query = "create table "+ TABLE+ "("+id +" integer primary key autoincrement not null"+", "+USER+" text not null, "+PASSWORD+" text not null);";
    SQLiteDatabase db;

    public DatabaseManager(Context context) {
        Custom SQLcustom = new Custom(context);
        db = SQLcustom.getWritableDatabase();
    }

    /**
     * Thêm dữ liệu vào database
     * @param user
     * @param password
     */
    public void Insert(String user, String password){
        String query = "insert into "+ TABLE +"("+USER+", "+PASSWORD+") values ('"+user+"', '"+password+"')";
        db.execSQL(query);
    }

    /**
     * kiểm tra dữ liệu trong database
     * @param user
     * @param password
     * @return
     */
    @SuppressLint("Recycle")
    public boolean CheckInfo(String user, String password){
        Cursor cursor;
        String query = "select* from "+ TABLE +" Where "+USER+" ='"+user+"' and "+PASSWORD+"='"+password+"'";
        cursor = db.rawQuery(query,null);
        if (cursor.getCount() >1){
            return true;
        }
        return false;
    }
    /**
     * tùy chỉnh lại SQLieteOpenHelper
     */
    class  Custom extends  SQLiteOpenHelper{

        public Custom(Context context) {
            super(context, DATABASE_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            String query = "create table "+ TABLE+ "("+id +" integer primary key autoincrement not null"+", "+USER+" text not null, "+PASSWORD+" text not null);";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}
