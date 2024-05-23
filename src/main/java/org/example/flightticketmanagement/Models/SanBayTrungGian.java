package org.example.flightticketmanagement.Models;

public class SanBayTrungGian {
    private String maSanBayTrungGian;
    private String maDuongBay;
    private Integer thuTu;
    private String thoiGianDung;
    private String ghiChu;

    public SanBayTrungGian(String maSanBayTrungGian, String maDuongBay, Integer thuTu, String thoiGianDung, String ghiChu) {
        this.maSanBayTrungGian = maSanBayTrungGian;
        this.maDuongBay = maDuongBay;
        this.thuTu = thuTu;
        this.thoiGianDung = thoiGianDung;
        this.ghiChu = ghiChu;
    }

    public String getMaSanBayTrungGian() {
        return maSanBayTrungGian;
    }

    public void setMaSanBayTrungGian(String maSanBayTrungGian) {
        this.maSanBayTrungGian = maSanBayTrungGian;
    }

    public String getMaDuongBay() {
        return maDuongBay;
    }

    public void setMaDuongBay(String maDuongBay) {
        this.maDuongBay = maDuongBay;
    }

    public Integer getThuTu() {
        return thuTu;
    }

    public void setThuTu(Integer thuTu) {
        this.thuTu = thuTu;
    }

    public String getThoiGianDung() {
        return thoiGianDung;
    }

    public void setThoiGianDung(String thoiGianDung) {
        this.thoiGianDung = thoiGianDung;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
