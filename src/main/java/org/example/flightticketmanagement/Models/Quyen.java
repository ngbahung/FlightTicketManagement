package org.example.flightticketmanagement.Models;

public class Quyen {
    private String MaQuyen;
    private String TenQuyen;

    public Quyen(String maQuyen, String tenQuyen) {
        this.MaQuyen = maQuyen;
        this.TenQuyen = tenQuyen;
    }

    public String getTenQuyen() {
        return TenQuyen;
    }

    public void setTenQuyen(String tenQuyen) {
        this.TenQuyen = tenQuyen;
    }

    public String getMaQuyen() {
        return MaQuyen;
    }

    public void setMaQuyen(String roleID) {
        this.MaQuyen = roleID;
    }
}
