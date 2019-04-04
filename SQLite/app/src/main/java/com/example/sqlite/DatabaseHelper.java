package com.example.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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
    private SQLiteDatabase ReadDatabase;
    private static final String DATABASE_NAME = "SQL.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        database = this.getWritableDatabase();
        ReadDatabase = this.getReadableDatabase();
    }

    //thêm dữ liệu vào database
    public void Insert(String username, String password) {
        String insert = "insert into " + TABLE_NAME + "(" + TABLE_a_username + "," + TABLE_a_password + ") values ('" + username + "','" + password + "')";
        database.execSQL(insert);
    }

    //xóa dữ liệu -->
    public void Delete(int ID) {
        String delete = "delete  from "+TABLE_NAME+" where " + TABLE_a_id + " =" + ID;
        database.execSQL(delete);
    }

    public void Delete(String name) {
        String delete = "delete  from "+TABLE_NAME+" where " + TABLE_a_id + " =" + name;
        database.execSQL(delete);
    }
//<--end
    public  void Update(int ID, String name, String password){
        String query = "update "+ TABLE_NAME+" set "+TABLE_a_username+"='"+name+"', "+TABLE_a_password+"= '"+password+"' where "+ TABLE_a_id+"="+ID;
        database.execSQL(query);
    }
    /**
     * Lấy dữ tất cả dữ liệu có trong database
     */
    public List<Users> getAllData(){
        List<Users> getUser= new ArrayList<>();
        String query = "select * from "+TABLE_NAME;
        Cursor cursor =  ReadDatabase.rawQuery(query, null);
        for (int i = 0; i < cursor.getCount(); i++ ){
            cursor.moveToNext();
            getUser.add(new Users(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
        }
        cursor.close();
        return getUser;
    }
    /**
     * kiểm tra tên đăng nhập và mật khẩu có đúng có trong database
     *
     * @return trả về true nếu dữ liệu co trong db và false nếu không có
     */
    public Boolean CheckData(String username, String password) {
        Cursor cursor;
        String query = "select *from " + TABLE_NAME + " where " + TABLE_a_username + "='" + username + "' and " + TABLE_a_password + "='" + password + "'";
        cursor = database.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            return true;
        }
        cursor.close();
        return false;
    }

    /**
     * kiểm tra xem tên đăng nhập đã có trong database chưa
     * @param name tên đăng nhập
     * @return trả về tên đăng nhập
     */
    public boolean CheckNameExsit(String name){
        String query = "select *from " + TABLE_NAME + " where " + TABLE_a_username + "='"+name+"'";
        Cursor cursor = database.rawQuery(query, null);
        if(cursor.getCount() > 0){
            return false;
        }
        cursor.close();
        return true;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}