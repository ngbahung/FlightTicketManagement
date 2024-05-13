package org.example.flightticketmanagement.Models;

public class DuongBay {
    private String maDuongBay;
    private String maQuocGiaDi;
    private String maQuocGiaDen;
    private String tenDuongBay;
    private Float doDaiDuongBay;

    public DuongBay(String maDuongBay, String maQuocGiaDi, String maQuocGiaDen, String tenDuongBay, Float doDaiDuongBay) {
        this.maDuongBay = maDuongBay;
        this.maQuocGiaDi = maQuocGiaDi;
        this.maQuocGiaDen = maQuocGiaDen;
        this.tenDuongBay = tenDuongBay;
        this.doDaiDuongBay = doDaiDuongBay;
    }

    public String getMaDuongBay() {
        return maDuongBay;
    }

    public void setMaDuongBay(String maDuongBay) {
        this.maDuongBay = maDuongBay;
    }

    public String getMaQuocGiaDi() {
        return maQuocGiaDi;
    }

    public void setMaQuocGiaDi(String maQuocGiaDi) {
        this.maQuocGiaDi = maQuocGiaDi;
    }

    public String getTenDuongBay() {
        return tenDuongBay;
    }

    public void setTenDuongBay(String tenDuongBay) {
        this.tenDuongBay = tenDuongBay;
    }

    public String getMaQuocGiaDen() {
        return maQuocGiaDen;
    }

    public void setMaQuocGiaDen(String maQuocGiaDen) {
        this.maQuocGiaDen = maQuocGiaDen;
    }

    public float getDoDaiDuongBay() {
        return doDaiDuongBay;
    }

    public void setDoDaiDuongBay(Float doDaiDuongBay) {
        this.doDaiDuongBay = doDaiDuongBay;
    }
}
