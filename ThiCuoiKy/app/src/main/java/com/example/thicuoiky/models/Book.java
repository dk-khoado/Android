package com.example.thicuoiky.models;

import java.io.Serializable;

public class Book implements Serializable {
    private String maSach;
    private String loaiSach;
    private String tenTG;
    private String tenSach;
    private String NXB;
    private int soLuong;
    private int ID;
    private int soLuongOrgin;

    public int getSoLuongOrgin() {
        return soLuongOrgin;
    }

    public void setSoLuongOrgin(int soLuongOrgin) {
        this.soLuongOrgin = soLuongOrgin;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


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
