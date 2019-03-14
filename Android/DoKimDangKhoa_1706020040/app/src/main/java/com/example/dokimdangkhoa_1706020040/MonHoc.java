package com.example.dokimdangkhoa_1706020040;

public class MonHoc {
    private String tenMonHoc;
    private String maMonHoc;
    private int soTinChi;
    private String hoten;
    private int maSV;
    private String nganh;
    private String giaVien;

    public MonHoc(String tenMonHoc, String maMonHoc, int soTinChi, String hoten, int maSV, String nganh, String giaVien) {
        this.tenMonHoc = tenMonHoc;
        this.maMonHoc = maMonHoc;
        this.soTinChi = soTinChi;
        this.hoten = hoten;
        this.maSV = maSV;
        this.nganh = nganh;
        this.giaVien = giaVien;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public String getMaMonHoc() {
        return maMonHoc;
    }

    public void setMaMonHoc(String maMonHoc) {
        this.maMonHoc = maMonHoc;
    }

    public int getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(int soTinChi) {
        this.soTinChi = soTinChi;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public int getMaSV() {
        return maSV;
    }

    public void setMaSV(int maSV) {
        this.maSV = maSV;
    }

    public String getNganh() {
        return nganh;
    }

    public void setNganh(String nganh) {
        this.nganh = nganh;
    }

    public String getGiaVien() {
        return giaVien;
    }

    public void setGiaVien(String giaVien) {
        this.giaVien = giaVien;
    }
}
