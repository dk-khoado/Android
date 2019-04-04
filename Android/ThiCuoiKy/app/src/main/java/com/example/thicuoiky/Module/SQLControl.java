package com.example.thicuoiky.Module;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.thicuoiky.models.Book;
import com.example.thicuoiky.models.Detail;
import com.example.thicuoiky.models.Member;
import com.example.thicuoiky.models.lv_model;

import java.util.ArrayList;
import java.util.List;

public class SQLControl extends SQLiteOpenHelper {
    //property -->
    private static final String DATABASE_NAME = "database.db";
    //book
    private static final String TABLE_NAME = "book";
    private static final String TABLE_f_ID = "ID";
    private static final String TABLE_f_maSach = "maSach";
    private static final String TABLE_f_tenSach = "tenSach";
    private static final String TABLE_f_tenTG = "tenTG";
    private static final String TABLE_f_NXB = "NXB";
    private static final String TABLE_f_soLuong = "soLuong";
    private static final String TABLE_f_loaiSach = "loaiSach";
    private static final String TABLE_f_soLuongDaMuon = "soLuongDaMuon";
    //người mượn sách
    private static final String TABLE_NAME2 = "member";
    private static final String TABLE2_f_IDmenber = "ID_member";
    private static final String TABLE2_f_tenNguoiMuon = "tenNguoiMuon";
    private static final String TABLE2_f_soSachMuon = "soSachMuong";
    private static final String TABLE2_f_ngayMuon = "ngayMuon";
    private static final String TABLE2_f_status = "status";
    //danh sách order
    private static final String TABLE_NAME3 = "orderBook";
    private static final String TABLE3_f_IDmenber = "ID_member";
    private static final String TABLE3_f_maSach = "maSach";
    //Đăng Nhập
    private static final String TABLE_NAME4 = "login";
    private static final String TABLE4_f_ID = "ID";
    private static final String TABLE4_f_User = "User";
    private static final String TABLE4_f_password = "Password";
    //<--end

    private SQLiteDatabase databaseWrite;
    private SQLiteDatabase databaseRead;

    public SQLControl(Context context) {
        super(context, DATABASE_NAME, null, 1);
        databaseRead = this.getReadableDatabase();
        databaseWrite = this.getWritableDatabase();
    }

    //thêm dữ liệu sách vào database
    public void Insert(String maSach, String tenSach, String tenTG, String NXB, int soLuong, String theLoai) {
        String query = String.format("insert into %s(%s, %s, %s, %s, %s, %s) values('%s','%s', '%s', '%s', '%s','%s')",
                TABLE_NAME, TABLE_f_maSach, TABLE_f_tenSach, TABLE_f_tenTG, TABLE_f_NXB, TABLE_f_soLuong, TABLE_f_loaiSach,
                maSach, tenSach, tenTG, NXB, soLuong, theLoai);
//        ContentValues values = new ContentValues();
//        values.put(TABLE_f_maSach,maSach);//0
//        values.put(TABLE_f_tenSach,tenSach);//1
//        values.put(TABLE_f_tenTG,tenTG);//2
//        values.put(TABLE_f_NXB, NXB);//3
//        values.put(TABLE_f_soLuong, soLuong);//4
//        values.put(TABLE_f_loaiSach, theLoai);//5
        //databaseWrite.insert(TABLE_NAME, null, values);
        databaseWrite.execSQL(query);
    }

    /**
     * thêm dữ liệu vào member
     */
    public void Insert(String name, int soLuong, String ngayMuong) {
        String query = String.format("insert into %s(%s, %s,%s) values('%s' ,'%s' ,'%s');",
                TABLE_NAME2, TABLE2_f_tenNguoiMuon, TABLE2_f_soSachMuon, TABLE2_f_ngayMuon,
                name, soLuong, ngayMuong);
        databaseWrite.execSQL(query);
    }

    /**
     * thêm dữ liệu vào borrow book
     */
    public void Insert(int ID_Member, String name) {
        String query = String.format("insert into %s(%s, %s) values('%s' ,'%s');",
                TABLE_NAME3, TABLE3_f_IDmenber, TABLE3_f_maSach, ID_Member, name);
        databaseWrite.execSQL(query);
    }

    /**
     * Tạo bảng thứ 2 bảng thành viên
     *
     * @param db
     */
    private void CreateTable_Menber(SQLiteDatabase db) {
        String query = "create table " + TABLE_NAME2 + "(" +
                TABLE2_f_IDmenber + " integer not null primary key autoincrement ," +
                TABLE2_f_tenNguoiMuon + " text not null," +
                TABLE2_f_soSachMuon + " int not null," +
                TABLE2_f_ngayMuon + " text not null," +
                TABLE2_f_status + " text default 'false')";
        db.execSQL(query);
    }

    /**
     * Tạo bảng thứ 3 bảng order sách
     *
     * @param db
     */
    private void CreateTable_order(SQLiteDatabase db) {
        String query = "create table " + TABLE_NAME3 + "(" + TABLE3_f_IDmenber + " int not null," + TABLE3_f_maSach + " text not null)";

        db.execSQL(query);
    }

    //getAll Danh sách sách
    public List<Book> getAllBook() {
        String query = "select * from " + TABLE_NAME;
        List<Book> bookList = new ArrayList<>();
        Cursor cursor = databaseWrite.rawQuery(query, null);
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            Book item = new Book();
            item.setID(cursor.getInt(0));
            item.setMaSach(cursor.getString(1));
            item.setTenSach(cursor.getString(2));
            item.setTenTG(cursor.getString(3));
            item.setNXB(cursor.getString(4));
            item.setSoLuong(cursor.getInt(5)- cursor.getInt(7));
            item.setLoaiSach(cursor.getString(6));
            item.setSoLuongOrgin(cursor.getInt(7));
            bookList.add(item);
        }
        cursor.close();
        return bookList;
    }

    /**
     * lấy tất cả dữ liệu từ bảng Member
     *
     * @return
     */
    public List<Member> getAllMember() {
        List<Member> memberList = new ArrayList<>();
        String query = "select * from " + TABLE_NAME2;
        Cursor cursor = databaseWrite.rawQuery(query, null);
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            Member member = new Member();
            member.setID(cursor.getInt(0));
            member.setName(cursor.getString(1));
            member.setSoLuongSach(cursor.getInt(2));
            member.setNgayMuon(cursor.getString(3));
            member.setStatus(Boolean.valueOf(cursor.getString(4)));
            memberList.add(member);
        }
        cursor.close();
        return memberList;
    }

    public List<lv_model> getAllBookList() {
        List<lv_model> memberList = new ArrayList<>();
        String query = "select * from " + TABLE_NAME;
        Cursor cursor = databaseRead.rawQuery(query, null);
        for (int i = 0; i < cursor.getCount(); i++) {
            lv_model item = new lv_model();
            cursor.moveToNext();
            item.setID(cursor.getInt(0));
            item.setMaSach(cursor.getString(1));
            item.setTenSach(cursor.getString(2));
            item.setTenTG(cursor.getString(3));
            item.setSoLuong(cursor.getInt(5)- cursor.getInt(7));
            memberList.add(item);
        }
        cursor.close();
        return memberList;
    }

    //lấy dòng dữ liệu cuối cùng của bảng member
    public Member getLastMember() {
        Member item = new Member();
        String query = "select * from " + TABLE_NAME2;
        Cursor cursor = databaseWrite.rawQuery(query, null);
        cursor.moveToLast();
        item.setID(cursor.getInt(0));
        item.setName(cursor.getString(1));
        item.setSoLuongSach(cursor.getInt(2));
        item.setNgayMuon(cursor.getString(3));
        item.setStatus(Boolean.valueOf(cursor.getString(4)));
        cursor.close();
        return item;
    }

    //Lấy dữ liệu Detail
    public Detail getDetail(int ID_member) {
        Detail item = new Detail();
        String query = String.format("select  %s.*,%s.* from %s, %s, %s\n" +
                        "where %s.%s = %s.%s \n" +
                        "and %s.%s = %s.%s\n" +
                        "and %s.%s = %s",
                TABLE_NAME2, TABLE_NAME, TABLE_NAME, TABLE_NAME2, TABLE_NAME3,
                TABLE_NAME, TABLE_f_maSach, TABLE_NAME3, TABLE3_f_maSach,
                TABLE_NAME2, TABLE2_f_IDmenber, TABLE_NAME3, TABLE3_f_IDmenber,
                TABLE_NAME3, TABLE3_f_IDmenber, ID_member);
        Cursor cursor = databaseRead.rawQuery(query, null);
        //cursor.moveToFirst();

        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            item.setID(cursor.getInt(0));
            item.setName(cursor.getString(1));
            item.setSoLuongSach(cursor.getInt(2));
            item.setNgayMuon(cursor.getString(3));
            item.setStatus(Boolean.parseBoolean(cursor.getString(4)));
            Book book = new Book();
            book.setID(cursor.getInt(5));
            book.setMaSach(cursor.getString(6));
            book.setTenSach(cursor.getString(7));
            book.setTenTG(cursor.getString(8));
            book.setNXB(cursor.getString(9));
            book.setLoaiSach(cursor.getString(11));
            item.addItemBookList(book);
        }
        cursor.close();
        return item;
    }

    /**
     * tìm kiếm theo tên sách áp dụng cho thêm phiếu mượn
     *
     * @param Name
     * @return
     */
    public List<lv_model> SearchBookforOrder(String Name) {
        List<lv_model> lv_modelList = new ArrayList<>();
        String query = "Select * from " + TABLE_NAME + " where " + TABLE_f_tenSach + " like " + "'%" + Name + "%'";
        Cursor cursor = databaseWrite.rawQuery(query, null);
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            lv_model item = new lv_model();
            item.setID(cursor.getInt(0));
            item.setMaSach(cursor.getString(1));
            item.setTenSach(cursor.getString(2));
            item.setTenTG(cursor.getString(3));
            item.setSoLuong(cursor.getInt(5)- cursor.getInt(7));
            lv_modelList.add(item);
        }
        cursor.close();
        return lv_modelList;
    }

    /**
     * tìm kiếm sách theo tên sách
     *
     * @param Name
     * @return
     */
    public List<Book> SearchBook(String Name) {
        List<Book> bookList = new ArrayList<>();
        String query = "Select * from " + TABLE_NAME + " where " + TABLE_f_tenSach + " like " + "'%" + Name + "%'";
        Cursor cursor = databaseWrite.rawQuery(query, null);
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            Book item = new Book();
            item.setID(cursor.getInt(0));
            item.setMaSach(cursor.getString(1));
            item.setTenSach(cursor.getString(2));
            item.setTenTG(cursor.getString(3));
            item.setNXB(cursor.getString(4));
            item.setSoLuong(cursor.getInt(5) - cursor.getInt(7));
            item.setLoaiSach(cursor.getString(6));
            item.setSoLuongOrgin(cursor.getInt(7));
            bookList.add(item);
        }
        cursor.close();
        return bookList;
    }

    public Boolean CheckMaSach(String maSach) {
        String query = "select * from " + TABLE_NAME + " where " + TABLE_f_maSach + " = '" + maSach + "'";
        Cursor cursor = databaseRead.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    //câp nhâp trạng thái của Member
    public void UpdateStatusMenber(int ID_member, String status) {
        String query = String.format("update %s set %s = '%s' where %s = %s",
                TABLE_NAME2, TABLE2_f_status, status, TABLE2_f_IDmenber, ID_member);
        databaseWrite.execSQL(query);
    }

    public void UpdateBook(int ID, String tenSach, String tenTG, String tenNXB, String theLoai, int soLuong) {
        String query = String.format("update %s set %s = '%s', %s = '%s', %s = '%s', %s = '%s',%s = '%s' where %s = '%s'", TABLE_NAME,
                TABLE_f_tenSach, tenSach, TABLE_f_tenTG, tenTG, TABLE_f_NXB, tenNXB, TABLE_f_loaiSach, theLoai, TABLE_f_soLuong, soLuong,
                TABLE_f_ID, ID);
        databaseWrite.execSQL(query);
    }
    public void UpdateSoluongBook(int ID, int soLuong){
        String query = String.format("update %s set %s = %s where %s = %s",TABLE_NAME, TABLE_f_soLuongDaMuon,soLuong, TABLE_f_ID, ID);
        databaseWrite.execSQL(query);
    }
    public int getSoLuongSachDaMuon(int ID){
        String query = String.format("select * from %s where %s = %s",TABLE_NAME, TABLE_f_ID,ID);
        Cursor cursor = databaseRead.rawQuery(query, null);
        cursor.moveToFirst();
        int soLuong = cursor.getInt(7);
        cursor.close();
        return soLuong;
    }
    public void TraSach(){

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = String.format("CREATE TABLE %s (" +
                "%s INTEGER primary key autoincrement not null," +
                "%s TEXT not null, " +
                "%s TEXT not null," +
                "%s TEXT not null," +
                "%s TEXT not null," +
                "%s INTEGER NOT NULL," +
                "%s TEXT NOT NULL,"+
                "%s INTEGER NOT NULL default(0))", TABLE_NAME, TABLE_f_ID, TABLE_f_maSach, TABLE_f_tenSach, TABLE_f_tenTG, TABLE_f_NXB, TABLE_f_soLuong, TABLE_f_loaiSach,TABLE_f_soLuongDaMuon);
        db.execSQL(query);
//        String query2 = String.format("Create Table %s (%s integer primary key autoincrement , %s text not null, %s text not null)",
//                TABLE_NAME4, TABLE4_f_ID, TABLE4_f_User, TABLE4_f_password);
//        db.execSQL(query2);
        CreateTable_Menber(db);
        CreateTable_order(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
