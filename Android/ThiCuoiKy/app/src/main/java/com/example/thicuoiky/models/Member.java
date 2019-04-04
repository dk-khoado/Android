package com.example.thicuoiky.models;

import java.io.Serializable;

public class Member implements Serializable {
    private int ID;
    private String Name;
    private int soLuongSach;
    private String ngayMuon;
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public  Member(){

    }
    public Member(int ID, String name, int soLuongSach, String ngayMuon) {
        this.ID = ID;
        Name = name;
        this.soLuongSach = soLuongSach;
        this.ngayMuon = ngayMuon;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getSoLuongSach() {
        return soLuongSach;
    }

    public void setSoLuongSach(int soLuongSach) {
        this.soLuongSach = soLuongSach;
    }

    public String getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(String  ngayMuon) {
        this.ngayMuon = ngayMuon;
    }
}
