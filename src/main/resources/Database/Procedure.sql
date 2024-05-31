--UPDATE TRANGTHAI
CREATE OR REPLACE PROCEDURE update_ticket_status(
    p_maCT_DATVE IN VARCHAR2,
    p_trangThai IN INT
) AS
    v_maChuyenBay VARCHAR2(10);
v_maHangVe VARCHAR2(10);
v_oldTrangThai INT;
BEGIN
-- Fetch the current status of the ticket
SELECT TrangThai INTO v_oldTrangThai
FROM CT_DATVE
WHERE MaCT_DATVE = p_maCT_DATVE;

-- Update the status of the ticket in CT_DATVE
UPDATE CT_DATVE
SET TrangThai = p_trangThai,
    NgayThanhToan = CASE WHEN p_trangThai IN (1, 2) THEN SYSDATE ELSE NULL END
WHERE MaCT_DATVE = p_maCT_DATVE;

-- Fetch related MaChuyenBay and MaHangVe
SELECT V.MaChuyenBay, V.MaHangVe INTO v_maChuyenBay, v_maHangVe
FROM VE V
         JOIN CT_DATVE CDV ON V.MaVe = CDV.MaVe
WHERE CDV.MaCT_DATVE = p_maCT_DATVE;

-- Adjust the available seats in CT_HANGVE
IF p_trangThai = 1 THEN
        IF v_oldTrangThai != 0 THEN
            -- If old status is not 0, decrement available seats and increment booked seats
UPDATE CT_HANGVE
SET SoGheTrong = SoGheTrong - 1,
    SoGheDat = SoGheDat + 1
WHERE MaChuyenBay = v_maChuyenBay AND MaHangVe = v_maHangVe;
END IF;
ELSIF p_trangThai = 2 THEN
        -- If ticket is cancelled, increment available seats and decrement booked seats
UPDATE CT_HANGVE
SET SoGheTrong = SoGheTrong + 1,
    SoGheDat = SoGheDat - 1
WHERE MaChuyenBay = v_maChuyenBay AND MaHangVe = v_maHangVe;
END IF;

COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        -- Handle exceptions
ROLLBACK;
RAISE;
END;
/

--SELL ticket
CREATE OR REPLACE PROCEDURE SellTicket(
    p_maKH IN VARCHAR2,
    p_hoten IN VARCHAR2,
    p_cccd IN VARCHAR2,
    p_email IN VARCHAR2,
    p_sdt IN VARCHAR2,
    p_diaChi IN VARCHAR2,
    p_maVe IN VARCHAR2,
    p_ngayMuaVe IN TIMESTAMP,
    p_ngayThanhToan IN TIMESTAMP,
    p_trangThai IN INTEGER
) AS
    v_customerExists INTEGER;
v_newMaCT_DATVE VARCHAR2(10);
BEGIN
-- Check if customer exists
SELECT COUNT(*) INTO v_customerExists
FROM KHACHHANG
WHERE MAKHACHHANG = p_maKH;

-- If customer does not exist, insert into KHACHHANG
IF v_customerExists = 0 THEN
INSERT INTO KHACHHANG (MAKHACHHANG, HOTEN, CCCD, EMAIL, SDT, DIACHI)
VALUES (p_maKH, p_hoten, p_cccd, p_email, p_sdt, p_diaChi);
END IF;

    -- Generate new MaCT_DATVE
SELECT 'CTDV' || LPAD(NVL(MAX(TO_NUMBER(SUBSTR(MaCT_DATVE, 5))), 0) + 1, 3, '0') INTO v_newMaCT_DATVE
FROM CT_DATVE;

-- Insert booking into CT_DATVE
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES (v_newMaCT_DATVE, p_maVe, p_maKH, p_ngayMuaVe, p_ngayThanhToan, p_trangThai);

COMMIT;
EXCEPTION
    WHEN OTHERS THEN
ROLLBACK;
RAISE;
END SellTicket;

-- SO GHE trong
CREATE OR REPLACE PROCEDURE update_soGheTrong(
    p_maCT_DATVE IN VARCHAR2,
    p_trangThai IN INT
) AS
    v_maChuyenBay VARCHAR2(10);
v_maHangVe VARCHAR2(10);
BEGIN
-- Lấy thông tin chuyến bay và hạng vé từ bảng CT_DATVE
SELECT V.MaChuyenBay, V.MaHangVe
INTO v_maChuyenBay, v_maHangVe
FROM VE V
         JOIN CT_DATVE CDV ON V.MaVe = CDV.MaVe
WHERE CDV.MaCT_DATVE = p_maCT_DATVE;

-- Cập nhật số ghế trống và đặt cho hợp lý dựa trên trạng thái mới của vé
IF p_trangThai IN (0, 1) THEN
        -- Trạng thái mới là 1 (đã đặt vé), giảm số ghế trống và tăng số ghế đã đặt
UPDATE CT_HANGVE
SET SoGheTrong = SoGheTrong - 1,
    SoGheDat = SoGheDat + 1
WHERE MaChuyenBay = v_maChuyenBay AND MaHangVe = v_maHangVe;
END IF;

COMMIT;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        -- Xử lý ngoại lệ khi không tìm thấy dữ liệu
        DBMS_OUTPUT.PUT_LINE('Không tìm thấy dữ liệu cho mã CT_DATVE: ' || p_maCT_DATVE);
WHEN OTHERS THEN
        -- Xử lý ngoại lệ khác và rollback
ROLLBACK;
DBMS_OUTPUT.PUT_LINE('Lỗi: ' || SQLERRM);
END;
