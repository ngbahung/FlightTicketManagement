package org.example.flightticketmanagement.Models;

import java.util.Objects;

public class SanBay {
    private String maSanBay;
    private String tenSanBay;
    private String tenVietTat;
    private String diaChi;
    private Integer trangThai;
    private Integer soThuTu;

    public SanBay(String maSanBay, String tenSanBay, String tenVietTat, String diaChi, Integer trangThai, Integer soThuTu) {
        this.maSanBay = maSanBay;
        this.tenSanBay = tenSanBay;
        this.tenVietTat = tenVietTat;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
        this.soThuTu = soThuTu;
    }

    public String getMaSanBay() {
        return maSanBay;
    }

    public void setMaSanBay(String maSanBay) {
        this.maSanBay = maSanBay;
    }

    public String getTenSanBay() {
        return tenSanBay;
    }

    public void setTenSanBay(String tenSanBay) {
        this.tenSanBay = tenSanBay;
    }

    public String getTenVietTat() {
        return tenVietTat;
    }

    public void setTenVietTat(String tenVietTat) {
        this.tenVietTat = tenVietTat;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }
<<<<<<< Updated upstream

    public Integer getSoThuTu() {
        return soThuTu;
    }

    public void setSoThuTu(Integer soThuTu) {
        this.soThuTu = soThuTu;
=======
    @Override
    public String toString() {
        return "SanBay{" +
                "maSanBay='" + maSanBay + '\'' +
                ", tenSanBay='" + tenSanBay + '\'' +
                ", tenVietTat='" + tenVietTat + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", trangThai=" + trangThai +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SanBay sanBay = (SanBay) o;
        return maSanBay.equals(sanBay.maSanBay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maSanBay);
>>>>>>> Stashed changes
    }
}
