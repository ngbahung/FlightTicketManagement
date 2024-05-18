package org.example.flightticketmanagement.Models;

public class CT_HangVe {
    private String maChuyenBay;
    private String maHangVe;
    private Integer soLuongGhe;
    private Integer soGheTrong;
    private Integer soGheDat;

    public CT_HangVe(String maChuyenBay, String maHangVe, Integer soLuongGhe, Integer soGheTrong, Integer soGheDat) {
        this.maChuyenBay = maChuyenBay;
        this.maHangVe = maHangVe;
        this.soLuongGhe = soLuongGhe;
        this.soGheTrong = soGheTrong;
        this.soGheDat = soGheDat;
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

    public Integer getSoLuongGhe() {
        return soLuongGhe;
    }

    public void setSoLuongGhe(Integer soLuongGhe) {
        this.soLuongGhe = soLuongGhe;
    }

    public Integer getSoGheTrong() {
        return soGheTrong;
    }

    public void setSoGheTrong(Integer soGheTrong) {
        this.soGheTrong = soGheTrong;
    }

    public Integer getSoGheDat() {
        return soGheDat;
    }

    public void setSoGheDat(Integer soGheDat) {
        this.soGheDat = soGheDat;
    }
}
