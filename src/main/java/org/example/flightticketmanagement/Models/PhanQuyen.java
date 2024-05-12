package org.example.flightticketmanagement.Models;

public class PhanQuyen {
    private String ten;
    private String matKhau;
    private String nhom;

    public PhanQuyen(String tenDangNhap, String matKhau, String nhom) {
        this.ten = tenDangNhap;
        this.matKhau = matKhau;
        this.nhom = nhom;
    }

    public String getTen() {
        return ten;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public String getNhom() {
        return nhom;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setNhom(String nhom) {
        this.nhom = nhom;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
