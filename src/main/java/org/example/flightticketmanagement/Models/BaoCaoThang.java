package org.example.flightticketmanagement.Models;

public class BaoCaoThang {
    private String maBCThang;
    private String maBCNam;
    private Integer nam;
    private Integer thang;
    private String maChuyenBay;
    private Integer soVeDaBan;
    private Float doanhThu;
    private Float tyLe;
    private Integer stt;


    public BaoCaoThang(String maBCThang, String maBCNam, Integer nam, Integer thang, String maChuyenBay, Integer soVeDaBan, Float doanhThu, Float tyLe) {
        this.maBCThang = maBCThang;
        this.maBCNam = maBCNam;
        this.nam = nam;
        this.thang = thang;
        this.maChuyenBay = maChuyenBay;
        this.soVeDaBan = soVeDaBan;
        this.doanhThu = doanhThu;
        this.tyLe = tyLe;
    }

    public String getMaBCThang() {
        return maBCThang;
    }

    public void setMaBCThang(String maBCThang) {
        this.maBCThang = maBCThang;
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

    public String getMaChuyenBay() {
        return maChuyenBay;
    }

    public void setMaChuyenBay(String maChuyenBay) {
        this.maChuyenBay = maChuyenBay;
    }

    public Integer getSoVeDaBan() {
        return soVeDaBan;
    }

    public void setSoVeDaBan(Integer soVeDaBan) {
        this.soVeDaBan = soVeDaBan;
    }

    public Float getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(Float doanhThu) {
        this.doanhThu = doanhThu;
    }

    public Float getTyLe() {
        return tyLe;
    }

    public void setTyLe(Float tyLe) {
        this.tyLe = tyLe;
    }

    public Integer getStt() {
        return stt;
    }

    public void setStt(Integer stt) {
        this.stt = stt;
    }
}
