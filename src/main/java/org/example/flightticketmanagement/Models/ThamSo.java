package org.example.flightticketmanagement.Models;

public class ThamSo {
    private String maThuocTinh;
    private String tenThuocTinh;
    private Integer giaTri;

    public ThamSo(String maThuocTinh, String tenThuocTinh, Integer giaTri) {
        this.maThuocTinh = maThuocTinh;
        this.tenThuocTinh = tenThuocTinh;
        this.giaTri = giaTri;
    }

    public String getMaThuocTinh() {
        return maThuocTinh;
    }

    public void setMaThuocTinh(String maThuocTinh) {
        this.maThuocTinh = maThuocTinh;
    }

    public String getTenThuocTinh() {
        return tenThuocTinh;
    }

    public void setTenThuocTinh(String tenThuocTinh) {
        this.tenThuocTinh = tenThuocTinh;
    }

    public Integer getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(Integer giaTri) {
        this.giaTri = giaTri;
    }
}
