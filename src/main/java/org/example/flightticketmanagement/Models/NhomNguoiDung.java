package org.example.flightticketmanagement.Models;

public class NhomNguoiDung {
    private String maNHom;
    private String tenNhom;

    public NhomNguoiDung(String maNHom, String tenNhom) {
        this.maNHom = maNHom;
        this.tenNhom = tenNhom;
    }

    public String getMaNHom() {
        return maNHom;
    }

    public void setMaNHom(String maNHom) {
        this.maNHom = maNHom;
    }

    public String getTenNhom() {
        return tenNhom;
    }

    public void setTenNhom(String tenNhom) {
        this.tenNhom = tenNhom;
    }
}
