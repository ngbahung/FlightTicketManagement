package org.example.flightticketmanagement.Models;

import java.util.*;

public class NguoiDung {
    private String tenDangNhap;
    private String maKhau;
    private String maNhom;

    public NguoiDung(String tenDangNhap, String maKhau, String maNhom) {
        this.tenDangNhap = tenDangNhap;
        this.maKhau = maKhau;
        this.maNhom = maNhom;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMaKhau() {
        return maKhau;
    }

    public void setMaKhau(String maKhau) {
        this.maKhau = maKhau;
    }

    public String getMaNhom() {
        return maNhom;
    }

    public void setMaNhom(String maNhom) {
        this.maNhom = maNhom;
    }
}
