package com.example.dokimdangkhoa_1706020040.Module;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SQLControl extends SQLiteOpenHelper {
    //property-->
    private static  final  String DATABASE_NAME = "database.db";
    private static  final  String TABLE_NAME = "book";
    private static  final  String TABLE_f_maSach = "maSach";
    private static  final  String TABLE_f_tenSach = "tenSach";
    private static  final  String TABLE_f_tenTG = "tenTG";
    private static  final  String TABLE_f_NXB = "NXB";
    private static  final  String TABLE_f_soLuong = "soLuong";
    private static  final  String TABLE_f_loaiSach = "loaiSach";
    //<--end

    private SQLiteDatabase databaseWrite;
    private SQLiteDatabase databaseRead;
    public SQLControl(Context context) {
        super(context, DATABASE_NAME, null, 1);
        databaseRead = this.getReadableDatabase();
        databaseWrite = this.getWritableDatabase();
    }
    //thêm dữ liệu vào database
    public void Insert(String maSach, String tenSach, String tenTG, String NXB, int soLuong, String theLoai){
        String query = String.format("insert into %s(%s, %s, %s, %s, %s, %s) values('%s','%s', '%s', '%s', '%s','%s')",
                TABLE_NAME, TABLE_f_maSach, TABLE_f_tenSach, TABLE_f_tenTG, TABLE_f_NXB, TABLE_f_soLuong,TABLE_f_loaiSach,
                maSach, tenSach, tenTG, NXB, soLuong, theLoai);
        ContentValues values = new ContentValues();
        values.put(TABLE_f_maSach,maSach);//0
        values.put(TABLE_f_tenSach,tenSach);//1
        values.put(TABLE_f_tenTG,tenTG);//2
        values.put(TABLE_f_NXB, NXB);//3
        values.put(TABLE_f_soLuong, soLuong);//4
        values.put(TABLE_f_loaiSach, theLoai);//5
        //databaseWrite.insert(TABLE_NAME, null, values);
        databaseWrite.execSQL(query);
    }
    //getAll
    public List<Book> getAll(){
        String query = "select * from "+ TABLE_NAME;
        List<Book> bookList = new ArrayList<>();
        Cursor cursor = databaseWrite.rawQuery(query,null);
        for (int i = 0; i < cursor.getCount(); i++){
            cursor.moveToNext();
            Book item = new Book();
            item.setMaSach(cursor.getString(0));
            item.setTenSach(cursor.getString(1));
            item.setTenTG(cursor.getString(2));
            item.setNXB(cursor.getString(3));
            item.setSoLuong(cursor.getInt(4));
            item.setLoaiSach(cursor.getString(5));
            bookList.add(item);
        }
        cursor.close();
        return bookList;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = String.format("CREATE TABLE %s (" +
                "%s TEXT primary key not null, " +
                "%s TEXT," +
                "%s TEXT," +
                "%s TEXT," +
                "%s INTEGER NOT NULL,"+
                "%s TEXT NOT NULL);", TABLE_NAME, TABLE_f_maSach, TABLE_f_tenSach, TABLE_f_tenTG, TABLE_f_NXB, TABLE_f_soLuong,TABLE_f_loaiSach);
        Log.i("test", query);
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
