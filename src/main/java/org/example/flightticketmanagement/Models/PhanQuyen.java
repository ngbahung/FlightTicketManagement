package org.example.flightticketmanagement.Models;

public class PhanQuyen {
    private String maChucNang;
    private String maNhom;

    public PhanQuyen(String maChucNang, String maNhom) {
        this.maChucNang = maChucNang;
        this.maNhom = maNhom;
    }

    public String getMaChucNang() {
        return maChucNang;
    }

    public void setMaChucNang(String maChucNang) {
        this.maChucNang = maChucNang;
    }

    public String getMaNhom() {
        return maNhom;
    }

    public void setMaNhom(String maNhom) {
        this.maNhom = maNhom;
    }
}
