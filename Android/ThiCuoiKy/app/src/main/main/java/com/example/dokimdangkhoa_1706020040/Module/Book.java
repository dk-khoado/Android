package com.example.dokimdangkhoa_1706020040.Module;

import android.widget.ArrayAdapter;

public class Book {
    String maSach;
    String loaiSach;
    String tenTG;
    String tenSach;
    String NXB;
    int soLuong;
    public Book(){

    }

    public String getTenTG() {
        return tenTG;
    }

    public void setTenTG(String tenTG) {
        this.tenTG = tenTG;
    }

    public Book(String maSach, String loaiSach, String tenSach, String NXB, int soLuong) {
        this.maSach = maSach;
        this.loaiSach = loaiSach;
        this.tenSach = tenSach;
        this.NXB = NXB;
        this.soLuong = soLuong;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getLoaiSach() {
        return loaiSach;
    }

    public void setLoaiSach(String loaiSach) {
        this.loaiSach = loaiSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getNXB() {
        return NXB;
    }

    public void setNXB(String NXB) {
        this.NXB = NXB;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
