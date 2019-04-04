package com.example.thicuoiky.models;

public class order_model{
    int ID_member;
    int ID_book;
    String maSach;
    String tenSach;
    String tenTG;
    int soLuong;

    public int getID_book() {
        return ID_book;
    }

    public void setID_book(int ID_book) {
        this.ID_book = ID_book;
    }

    public order_model(){
        soLuong = 0;
    }
    public int getID_member() {
        return ID_member;
    }

    public void setID_member(int ID_member) {
        this.ID_member = ID_member;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTenTG() {
        return tenTG;
    }

    public void setTenTG(String tenTG) {
        this.tenTG = tenTG;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
