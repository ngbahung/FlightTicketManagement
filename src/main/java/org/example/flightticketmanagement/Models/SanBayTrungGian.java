package org.example.flightticketmanagement.Models;

public class SanBayTrungGian {
    private String maDuongBay;
    private String maSanBay;
    private Integer thuTu;
    private String thoiGianDung;

    public SanBayTrungGian(String maDuongBay, String maSanBay, Integer thuTu, String thoiGianDung) {
        this.maDuongBay = maDuongBay;
        this.maSanBay = maSanBay;
        this.thuTu = thuTu;
        this.thoiGianDung = thoiGianDung;
    }

    public String getMaDuongBay() {
        return maDuongBay;
    }

    public void setMaDuongBay(String maDuongBay) {
        this.maDuongBay = maDuongBay;
    }

    public String getThoiGianDung() {
        return thoiGianDung;
    }

    public void setThoiGianDung(String thoiGianDung) {
        this.thoiGianDung = thoiGianDung;
    }

    public Integer getThuTu() {
        return thuTu;
    }

    public void setThuTu(Integer thuTu) {
        this.thuTu = thuTu;
    }

    public String getMaSanBay() {
        return maSanBay;
    }

    public void setMaSanBay(String maSanBay) {
        this.maSanBay = maSanBay;
    }
}
