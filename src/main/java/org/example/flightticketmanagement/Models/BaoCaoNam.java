package org.example.flightticketmanagement.Models;

import java.math.BigDecimal;

public class BaoCaoNam {

    private String maBCNam;
    private Integer nam;
    private Integer thang;
    private Integer soChuyenBay;
    private BigDecimal doanhThu;
    private Float tyLe;

    public BaoCaoNam(String maBCNam, Integer nam, Integer thang, Integer soChuyenBay, BigDecimal doanhThu, Float tyLe) {
        this.maBCNam = maBCNam;
        this.nam = nam;
        this.thang = thang;
        this.soChuyenBay = soChuyenBay;
        this.doanhThu = doanhThu;
        this.tyLe = tyLe;
    }

    public BaoCaoNam(Integer thang, Integer soChuyenBay, BigDecimal doanhThu, Float tyLe) {
        this.thang = thang;
        this.soChuyenBay = soChuyenBay;
        this.doanhThu = doanhThu;
        this.tyLe = tyLe;
    }

    public String getMaBCNam() {
        return maBCNam;
    }

    public void setMaBCNam(String maBCNam) {
        this.maBCNam = maBCNam;
    }

    public Integer getNam() {
        return nam;
    }

    public void setNam(Integer nam) {
        this.nam = nam;
    }

    public Integer getThang() {
        return thang;
    }

    public void setThang(Integer thang) {
        this.thang = thang;
    }

    public Integer getSoChuyenBay() {
        return soChuyenBay;
    }

    public void setSoChuyenBay(Integer soChuyenBay) {
        this.soChuyenBay = soChuyenBay;
    }

    public BigDecimal getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(BigDecimal doanhThu) {
        this.doanhThu = doanhThu;
    }

    public Float getTyLe() {
        return tyLe;
    }

    public void setTyLe(Float tyLe) {
        this.tyLe = tyLe;
    }

    @Override
    public String toString() {
        return "BaoCaoNam{" +
                "maBCNam='" + maBCNam + '\'' +
                ", nam=" + nam +
                ", thang=" + thang +
                ", soChuyenBay=" + soChuyenBay +
                ", doanhThu=" + doanhThu +
                ", tyLe=" + tyLe +
                '}';
    }
}
