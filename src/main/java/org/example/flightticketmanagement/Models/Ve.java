package org.example.flightticketmanagement.Models;

import java.time.LocalDateTime;

public class Ve {
    private String maVe;
    private String maChuyenBay;
    private String maHangVe;
    private Integer maGhe;
    private Float giaTien;

    public Ve(){}

    public Ve(String maVe, String maChuyenBay, String maHangVe, Integer maGhe, Float giaTien) {
        this.maVe = maVe;
        this.maChuyenBay = maChuyenBay;
        this.maHangVe = maHangVe;
        this.maGhe = maGhe;
        this.giaTien = giaTien;
    }

    public String getMaVe() {
        return maVe;
    }

    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }

    public String getMaChuyenBay() {
        return maChuyenBay;
    }

    public void setMaChuyenBay(String maChuyenBay) {
        this.maChuyenBay = maChuyenBay;
    }

    public String getMaHangVe() {
        return maHangVe;
    }

    public void setMaHangVe(String maHangVe) {
        this.maHangVe = maHangVe;
    }

    public Integer getMaGhe() {
        return maGhe;
    }

    public void setMaGhe(Integer maGhe) {
        this.maGhe = maGhe;
    }

    public Float getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(Float giaTien) {
        this.giaTien = giaTien;
    }
}
