package com.example.sqlite;

import android.content.Context;
import android.database.Cursor;
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

    //thêm dữ liệu vào database
    public void Insert(String username, String password) {
        String insert = "insert into "+TABLE_NAME+"(" + TABLE_a_username + "," + TABLE_a_password + ") values ('" + username + "','" + password + "')";
        database.execSQL(insert);
    }
    //xóa dữ liệu
    public void Delete(int ID){
        String delete = "delete * from where "+ TABLE_a_id+" ="+ ID;
        database.execSQL(delete);
    }
    //xóa dữ liệu
    public void Delete(String name){
        String delete = "delete * from where "+ TABLE_a_id+" ="+ name;
        database.execSQL(delete);
    }

    /**
     * kiểm tra tên đăng nhập và mật khẩu có đúng có trong database
     * @return trả về true nếu dữ liệu co trong db và false nếu không có
     */
    public Boolean CheckData(String username, String password){
        Cursor cursor;
        String query = "select *from "+ TABLE_NAME+" where "+TABLE_a_username+"='"+ username+"' and "+TABLE_a_password+"='"+password+"'";
        cursor = database.rawQuery(query,null);
        if (cursor.getCount()>0){
            Log.i("kashdaskdkasdjkasdjk",Integer.toString(cursor.getCount()));
            return true;
        }
        cursor.close();
        return false;
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
