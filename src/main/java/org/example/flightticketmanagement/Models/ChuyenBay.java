package org.example.flightticketmanagement.Models;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ChuyenBay {
    private String maChuyenBay;
    private String maDuongBay;
    private Integer soLuongGhe;
    private Integer soChuyenBay;
    private LocalDateTime thoiGianXuatPhat;
    private LocalDateTime thoiGianKetThuc ;
    private String trangThai;
    private Float giaVe;

    public ChuyenBay(String maChuyenBay, String maDuongBay, Integer soLuongGhe, Integer soChuyenBay, LocalDateTime thoiGianXuatPhat, LocalDateTime thoiGianKetThuc, String trangThai, Float giaVe) {
        this.maChuyenBay = maChuyenBay;
        this.maDuongBay = maDuongBay;
        this.soLuongGhe = soLuongGhe;
        this.soChuyenBay = soChuyenBay;
        this.thoiGianXuatPhat = thoiGianXuatPhat;
        this.thoiGianKetThuc = thoiGianKetThuc;
        this.trangThai = trangThai;
        this.giaVe = giaVe;
    }

    public String getMaChuyenBay() {
        return maChuyenBay;
    }

    public void setMaChuyenBay(String maChuyenBay) {
        this.maChuyenBay = maChuyenBay;
    }

    public String getMaDuongBay() {
        return maDuongBay;
    }

    public void setMaDuongBay(String maDuongBay) {
        this.maDuongBay = maDuongBay;
    }

    public Integer getSoLuongGhe() {
        return soLuongGhe;
    }

    public void setSoLuongGhe(Integer soLuongGhe) {
        this.soLuongGhe = soLuongGhe;
    }

    public LocalDateTime getThoiGianXuatPhat() {
        return thoiGianXuatPhat;
    }

    public void setThoiGianXuatPhat(LocalDateTime thoiGianXuatPhat) {
        this.thoiGianXuatPhat = thoiGianXuatPhat;
    }

    public Integer getSoChuyenBay() {
        return soChuyenBay;
    }

    public void setSoChuyenBay(Integer soChuyenBay) {
        this.soChuyenBay = soChuyenBay;
    }

    public LocalDateTime getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(LocalDateTime thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Float getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(Float giaVe) {
        this.giaVe = giaVe;
    }

    public static class ChuyenBayDAO {
        public List<ChuyenBay> getChuyenBays(String departure, String destination, LocalDateTime date, Integer maxPrice) {
            List<ChuyenBay> chuyenBays = new ArrayList<>();
            String sql = "SELECT * FROM CHUYENBAY WHERE MaDuongBay LIKE ? AND TGXP >= ? AND GiaVe <= ?";

            try (Connection conn = DatabaseDriver.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, departure + "%" + destination);
                pstmt.setTimestamp(2, Timestamp.valueOf(date));
                pstmt.setInt(3, maxPrice);

                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    ChuyenBay chuyenBay = new ChuyenBay(
                            rs.getString("MaChuyenBay"),
                            rs.getString("MaDuongBay"),
                            rs.getInt("SoLuongGhe"),
                            rs.getInt("SoChuyenBay"),
                            rs.getTimestamp("TGXP").toLocalDateTime(),
                            rs.getTimestamp("TGKT").toLocalDateTime(),
                            rs.getString("TrangThai"),
                            rs.getFloat("GiaVe")
                    );
                    chuyenBays.add(chuyenBay);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return chuyenBays;
        }
    }
}
