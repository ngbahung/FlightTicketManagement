-- LỊCH CHUYẾN BAY CONTROLLER
-- GET SAN BAY DI
CREATE OR REPLACE PROCEDURE GET_SANBAYDI (
    p_MaDuongBay IN VARCHAR2,
    p_TenSanBay OUT VARCHAR2
)
    IS
BEGIN
    SELECT sb.TenSanBay INTO p_TenSanBay
    FROM SANBAY sb
             JOIN DUONGBAY db ON sb.MaSanBay = db.MaSanBayDi
    WHERE db.MaDuongBay = p_MaDuongBay;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_TenSanBay := 'N/A'; -- Trả về giá trị mặc định nếu không tìm thấy dữ liệu
END GET_SANBAYDI;
/
--GET SAN BAY ĐẾN
CREATE OR REPLACE PROCEDURE GET_SANBAYDEN (
    p_MaDuongBay IN VARCHAR2,
    p_TenSanBay OUT VARCHAR2
)
    IS
BEGIN
    SELECT sb.TenSanBay INTO p_TenSanBay
    FROM SANBAY sb
             JOIN DUONGBAY db ON sb.MaSanBay = db.MaSanBayDen
    WHERE db.MaDuongBay = p_MaDuongBay;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_TenSanBay := 'N/A'; -- Trả về giá trị mặc định nếu không tìm thấy dữ liệu
END GET_SANBAYDEN;
/
--GET SỐ GHẾ TRỐNG
CREATE OR REPLACE PROCEDURE GET_SOGHETRONG (
    p_MaChuyenBay IN VARCHAR2,
    p_SoGheTrong OUT NUMBER
)
    IS
BEGIN
    SELECT SUM(SoGheTrong) INTO p_SoGheTrong
    FROM CT_HANGVE
    WHERE MaChuyenBay = p_MaChuyenBay;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_SoGheTrong := 0; -- Trả về giá trị mặc định nếu không tìm thấy dữ liệu
END GET_SOGHETRONG;
/
--GET SỐ Ghế
CREATE OR REPLACE PROCEDURE GET_SOGHE (
    p_MaChuyenBay IN VARCHAR2,
    p_SoGhe OUT NUMBER
)
    IS
BEGIN
    SELECT SUM(SoGheTrong + SoGheDat) INTO p_SoGhe
    FROM CT_HANGVE
    WHERE MaChuyenBay = p_MaChuyenBay;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_SoGhe := 0; -- Trả về giá trị mặc định nếu không tìm thấy dữ liệu
END GET_SOGHE;
/

--------------------------------------------------------------------------------------
-- TRA CỨU DẶT VÉ CONTROLLER
-- PROCEDURE 1: Lấy tên khách hàng từ mã khách hàng
CREATE OR REPLACE PROCEDURE GET_TEN_KHACH_HANG(
    p_MaKhachHang IN VARCHAR2,
    p_HoTen OUT VARCHAR2
) AS
BEGIN
    SELECT HoTen INTO p_HoTen
    FROM KHACHHANG
    WHERE MaKhachHang = p_MaKhachHang;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_HoTen := NULL;
    WHEN OTHERS THEN
        -- Xử lí các trường hợp ngoại lệ khác ở đây
        NULL;
END GET_TEN_KHACH_HANG;
/

-- PROCEDURE 2: Lấy sân bay đi từ mã vé
CREATE OR REPLACE PROCEDURE GET_SAN_BAY_DI(
    p_MaVe IN VARCHAR2,
    p_SanBayDi OUT VARCHAR2
) AS
BEGIN
    SELECT SBDi.TenSanBay INTO p_SanBayDi
    FROM Ve V
             JOIN CHUYENBAY CB ON V.MaChuyenBay = CB.MaChuyenBay
             JOIN DUONGBAY DB ON CB.MaDuongBay = DB.MaDuongBay
             JOIN SANBAY SBDi ON DB.MaSanBayDi = SBDi.MaSanBay
    WHERE V.MAVE = p_MaVe;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_SanBayDi := NULL;
    WHEN OTHERS THEN
        NULL;
END GET_SAN_BAY_DI;
/

-- PROCEDURE 3: Lấy sân bay đến từ mã vé
CREATE OR REPLACE PROCEDURE GET_SAN_BAY_DEN(
    p_MaVe IN VARCHAR2,
    p_SanBayDen OUT VARCHAR2
) AS
BEGIN
    SELECT SBDen.TenSanBay INTO p_SanBayDen
    FROM Ve V
             JOIN CHUYENBAY CB ON V.MaChuyenBay = CB.MaChuyenBay
             JOIN DUONGBAY DB ON CB.MaDuongBay = DB.MaDuongBay
             JOIN SANBAY SBDen ON DB.MaSanBayDen = SBDen.MaSanBay
    WHERE V.MAVE = p_MaVe;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_SanBayDen := NULL;
    WHEN OTHERS THEN
        NULL;
END GET_SAN_BAY_DEN;
/

-- PROCEDURE 4: Lấy ngày bay từ mã vé
CREATE OR REPLACE PROCEDURE GET_NGAY_BAY(
    p_MaVe IN VARCHAR2,
    p_NgayBay OUT TIMESTAMP
) AS
BEGIN
    SELECT CB.TGXP INTO p_NgayBay
    FROM CT_DATVE CDV
             JOIN Ve V ON CDV.MaVe = V.MaVe
             JOIN CHUYENBAY CB ON V.MaChuyenBay = CB.MaChuyenBay
    WHERE CDV.MaVe = p_MaVe;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_NgayBay := NULL;
    WHEN OTHERS THEN
        NULL;
END GET_NGAY_BAY;
/

-- PROCEDURE 5: Lấy giờ bay từ mã vé
CREATE OR REPLACE PROCEDURE GET_GIO_BAY(
    p_MaVe IN VARCHAR2,
    p_GioBay OUT TIMESTAMP
) AS
BEGIN
    SELECT CB.TGXP INTO p_GioBay
    FROM CT_DATVE CDV
             JOIN Ve V ON CDV.MaVe = V.MaVe
             JOIN CHUYENBAY CB ON V.MaChuyenBay = CB.MaChuyenBay
    WHERE CDV.MaVe = p_MaVe;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_GioBay := NULL;
    WHEN OTHERS THEN
        NULL;
END GET_GIO_BAY;
/

-- PROCEDURE 6: Lấy số điện thoại từ mã khách hàng
CREATE OR REPLACE PROCEDURE GET_SDT(
    p_MaKhachHang IN VARCHAR2,
    p_SDT OUT VARCHAR2
) AS
BEGIN
    SELECT SDT INTO p_SDT
    FROM KHACHHANG
    WHERE MaKhachHang = p_MaKhachHang;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_SDT := NULL;
    WHEN OTHERS THEN
        NULL;
END GET_SDT;
/

-- PROCEDURE 7: Lấy mã ghế từ mã vé
CREATE OR REPLACE PROCEDURE GET_MA_GHE(
    p_MaVe IN VARCHAR2,
    p_MaGhe OUT NUMBER
) AS
BEGIN
    SELECT MaGhe INTO p_MaGhe
    FROM VE
    WHERE MAVE = p_MaVe;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_MaGhe := NULL;
    WHEN OTHERS THEN
        NULL;
END GET_MA_GHE;
/

-- PROCEDURE 8: Lấy tên hạng vé từ mã vé
CREATE OR REPLACE PROCEDURE GET_TEN_HANG_VE(
    p_MaVe IN VARCHAR2,
    p_TenHangVe OUT VARCHAR2
) AS
BEGIN
    SELECT HV.TenHangVe INTO p_TenHangVe
    FROM HANGVE HV
             JOIN VE V ON HV.MaHangVe = V.MaHangVe
    WHERE V.MAVE = p_MaVe;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_TenHangVe := NULL;
    WHEN OTHERS THEN
        NULL;
END GET_TEN_HANG_VE;
/

-- PROCEDURE 9: Lấy giá tiền từ mã vé
CREATE OR REPLACE PROCEDURE GET_GIA_TIEN(
    p_MaVe IN VARCHAR2,
    p_GiaTien OUT NUMBER
) AS
BEGIN
    SELECT GiaTien INTO p_GiaTien
    FROM VE
    WHERE MAVE = p_MaVe;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_GiaTien := NULL;
    WHEN OTHERS THEN
        NULL;
END GET_GIA_TIEN;
/

-- PROCEDURE 9: update lại số ghế trống, số ghế đặt khi thay đổi trạng thái của ct_datve */
--UPDATE TRANGTHAI
CREATE OR REPLACE PROCEDURE update_ticket_status(
    p_maCT_DATVE IN VARCHAR2,
    p_trangThai IN number
) AS
    v_maVe VARCHAR2(10);
    v_maChuyenBay VARCHAR2(10);
    v_maHangVe VARCHAR2(10);
    v_oldTrangThai number;
    v_NgayThanhToan TIMESTAMP;
BEGIN
    -- Fetch the current status of the ticket
    SELECT TrangThai INTO v_oldTrangThai
    FROM CT_DATVE
    WHERE MaCT_DATVE = p_maCT_DATVE;

    SELECT mave,ngaythanhtoan INTO v_maVe,v_NgayThanhToan
    FROM CT_DATVE cd
    WHERE CD.MACT_DATVE = P_MACT_DATVE;

-- Update the status of the ticket in CT_DATVE
    UPDATE CT_DATVE
    SET
        TrangThai = p_trangThai,
        NgayThanhToan =  CASE
                             WHEN p_trangThai = 1 THEN SYSDATE
                             ELSE v_NgayThanhToan
            END
    WHERE
        MaCT_DATVE = p_maCT_DATVE;


-- Fetch related MaChuyenBay and MaHangVe
    SELECT V.MaChuyenBay, V.MaHangVe INTO v_maChuyenBay, v_maHangVe
    FROM VE V
    WHERE v.MaVe = v_maVe;

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

--XÁC NHẬN ĐẶT VÉ CONTROLLER
--PROCEDURE 1: SELL ticket
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

--PROCEDURE 2: SO GHE trong
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

--PROCEDURE 3: TẠO MÃ KHÁCH hàng
CREATE OR REPLACE PROCEDURE GenerateCustomerId(
    p_cccd IN VARCHAR2,
    p_maKH OUT VARCHAR2
) AS
    v_maxMaKH VARCHAR2(10);
BEGIN
    -- Kiểm tra xem khách hàng đã tồn tại hay chưa
    SELECT MAKHACHHANG
    INTO p_maKH
    FROM KHACHHANG
    WHERE CCCD = p_cccd;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        -- Lấy mã khách hàng lớn nhất
        SELECT MAX(MAKHACHHANG)
        INTO v_maxMaKH
        FROM KHACHHANG;

        IF v_maxMaKH IS NOT NULL THEN
            -- Tăng mã khách hàng lên 1
            p_maKH := 'KH' || TO_CHAR(TO_NUMBER(SUBSTR(v_maxMaKH, 3)) + 1, 'FM000');
        ELSE
            -- Nếu không có khách hàng nào, bắt đầu từ KH001
            p_maKH := 'KH001';
        END IF;
END GenerateCustomerId;

----------------------------------------------------------------------------------
-- MÀN HÌNH ĐẶT VÉ CONTROLLER

CREATE OR REPLACE PROCEDURE GET_VE_FOR_FLIGHT (
    p_maChuyenBay IN VARCHAR2,
    p_result OUT SYS_REFCURSOR
)
    IS
BEGIN
    OPEN p_result FOR
        SELECT DISTINCT
            hv.TENHANGVE,
            hv.MAHANGVE,
            (cb.GiaVe * hv.HeSo) AS GiaTien
        FROM
            CT_HANGVE ct
                JOIN
            HANGVE hv ON ct.MaHangVe = hv.MaHangVe
                JOIN
            CHUYENBAY cb ON ct.MaChuyenBay = cb.MaChuyenBay
        WHERE
            ct.MaChuyenBay = p_maChuyenBay
          AND ct.SoGheTrong > 0;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('No tickets found for the specified flight.');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('An error occurred: ' || SQLERRM);
END GET_VE_FOR_FLIGHT;
/

CREATE OR REPLACE PROCEDURE GET_TENHANGVE (
    p_maHangVe IN VARCHAR2,
    p_tenHangVe OUT VARCHAR2
)
    IS
BEGIN
    SELECT HV.TENHANGVE
    INTO p_tenHangVe
    FROM HANGVE HV
    WHERE HV.MAHANGVE = p_maHangVe;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_tenHangVe := 'Không tìm thấy';
    WHEN OTHERS THEN
        p_tenHangVe := 'Lỗi: ' || SQLERRM;
END GET_TENHANGVE;
/


CREATE OR REPLACE PROCEDURE GENERATE_MA_VE (
    p_newMaVe OUT VARCHAR2
)
    IS
    v_maxMaVe VE.MAVE%TYPE;
    v_newMaVeNumber NUMBER;
BEGIN
    -- Lấy giá trị lớn nhất của MAVE từ bảng VE
    SELECT MAX(MAVE) INTO v_maxMaVe FROM VE;

    -- Nếu không có MAVE nào trong bảng VE, bắt đầu với VE001
    IF v_maxMaVe IS NULL THEN
        p_newMaVe := 'VE001';
    ELSE
        -- Tăng giá trị của MAVE lớn nhất thêm 1
        v_newMaVeNumber := TO_NUMBER(SUBSTR(v_maxMaVe, 3)) + 1;
        -- Tạo MAVE mới với định dạng VExxx
        p_newMaVe := 'VE' || TO_CHAR(v_newMaVeNumber, 'FM000');
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        p_newMaVe := 'VE001'; -- Trả về giá trị mặc định nếu có lỗi
END GENERATE_MA_VE;
/

CREATE OR REPLACE PROCEDURE GENERATE_MA_GHE (
    p_newMaGhe OUT NUMBER
)
    IS
    v_maxMaGhe VE.MAGHE%TYPE;
BEGIN
    -- Lấy giá trị lớn nhất của MAGHE từ bảng VE
    SELECT MAX(MAGHE) INTO v_maxMaGhe FROM VE;

    -- Nếu không có MAGHE nào trong bảng VE, bắt đầu với 1
    IF v_maxMaGhe IS NULL THEN
        p_newMaGhe := 1;
    ELSE
        -- Tăng giá trị của MAGHE lớn nhất thêm 1
        p_newMaGhe := v_maxMaGhe + 1;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        p_newMaGhe := 1; -- Trả về giá trị mặc định nếu có lỗi
END GENERATE_MA_GHE;
/


CREATE OR REPLACE PROCEDURE SAVE_TICKET (
    p_maVe IN VARCHAR2,
    p_maChuyenBay IN VARCHAR2,
    p_maHangVe IN VARCHAR2,
    p_maGhe IN VARCHAR2,
    p_giaTien IN NUMBER
)
    IS
BEGIN
    INSERT INTO VE (MAVE, MACHUYENBAY, MAHANGVE, MAGHE, GIATIEN)
    VALUES (p_maVe, p_maChuyenBay, p_maHangVe, p_maGhe, p_giaTien);
EXCEPTION
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20001, 'Error occurred while saving ticket: ' || SQLERRM);
END SAVE_TICKET;
/


----------------------------------------------------------------------------
-- PHÂN QUYÊN CONTROLLER
CREATE OR REPLACE PROCEDURE GET_TAIKHOAN_PHANQUYEN (
    p_result OUT SYS_REFCURSOR
)
    IS
BEGIN
    OPEN p_result FOR
        SELECT
            TaiKhoan.maTaiKhoan,
            TaiKhoan.ten,
            TaiKhoan.sdt,
            TaiKhoan.email,
            TaiKhoan.password,
            TaiKhoan.created,
            Quyen.tenQuyen
        FROM
            TaiKhoan
                JOIN
            Quyen ON TaiKhoan.maQuyen = Quyen.maQuyen;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error occurred: ' || SQLERRM);
        RAISE;
END GET_TAIKHOAN_PHANQUYEN;
/



------------------------------------------------------------------
-- QUY ĐỊNH CONTROLLER
-- PROCEDURE 1: TẠO MÃ ĐƯỜNG BAY
CREATE OR REPLACE PROCEDURE GENERATE_MA_DUONGBAY (
    p_newMaDB OUT VARCHAR2
)
    IS
    v_maxMaDB VARCHAR2(6);
    v_maxNumber NUMBER;
    v_newMaDBNumber NUMBER;
BEGIN
    -- Lấy giá trị lớn nhất của phần số trong MADUONGBAY
    SELECT MAX(TO_NUMBER(SUBSTR(MADUONGBAY, 3))) INTO v_maxNumber FROM DUONGBAY;

    -- Nếu không có MADUONGBAY nào trong bảng DUONGBAY, bắt đầu với DB001
    IF v_maxNumber IS NULL THEN
        p_newMaDB := 'DB001';
    ELSE
        -- Tăng giá trị của phần số lớn nhất thêm 1
        v_newMaDBNumber := v_maxNumber + 1;
        -- Tạo MADUONGBAY mới với định dạng DBxxx
        p_newMaDB := 'DB' || TO_CHAR(v_newMaDBNumber, 'FM000');
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        p_newMaDB := 'DB001'; -- Trả về giá trị mặc định nếu có lỗi
END GENERATE_MA_DUONGBAY;


-----------------------------------------------------------------------------
-- XÓA CT_DATVE LIÊN QUAN ĐẾN VE ĐÃ XÓA

CREATE OR REPLACE PROCEDURE delete_CT_DATVE_by_VE (p_MaVe VARCHAR2) IS
BEGIN
    DELETE FROM CT_DATVE
    WHERE MaVe = p_MaVe;
END;