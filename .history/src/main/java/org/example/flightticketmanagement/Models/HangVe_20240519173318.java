package org.example.flightticketmanagement.Models;

public class HangVe {
    private String maHangVe;
    private String tenHangVe;
    private Float heSo;
    private String trangThai;

    public HangVe(String maHangVe, String tenHangVe, Float heSo, String trangThai) {
        this.maHangVe = maHangVe;
        this.tenHangVe = tenHangVe;
        this.heSo = heSo;
        this.trangThai = trangThai;
    }

    public String getMaHangVe() {
        return maHangVe;
    }

    public void setMaHangVe(String maHangVe) {
        this.maHangVe = maHangVe;
    }

    public String getTenHangVe() {
        return tenHangVe;
    }

    public void setTenHangVe(String tenHangVe) {
        this.tenHangVe = tenHangVe;
    }

    public Float getHeSo() {
        return heSo;
    }

    public void setHeSo(Float heSo) {
        this.heSo = heSo;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
