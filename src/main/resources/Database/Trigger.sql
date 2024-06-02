

/* R1: Thời gian xuất phát của một chuyến bay phải nhỏ hơn thời gian kết thúc */

ALTER TABLE CHUYENBAY
    ADD CONSTRAINT Check_ThoiGian_CB CHECK (TGXP < TGKT);


/* R2: Ngày thanh toán vé phải sau ngày mua vé */

ALTER TABLE CT_DATVE
    ADD CONSTRAINT Check_NgayThanhToan_VE CHECK (NgayMuaVe <= NgayThanhToan);


/* R3:Tổng giá tiền thanh toán = giá vé x hệ số */

CREATE OR REPLACE TRIGGER Tienthanhtoan_VE
    AFTER INSERT OR UPDATE ON VE
    FOR EACH ROW
DECLARE
    v_GiaVe CHUYENBAY.GiaVe%TYPE;
    v_HeSo HANGVE.HeSo%TYPE;
BEGIN

    SELECT cb.GiaVe, hv.HeSo
    INTO v_GiaVe, v_HeSo
    FROM CHUYENBAY cb, HANGVE hv
    WHERE cb.MaChuyenBay = :NEW.MaChuyenBay
      AND hv.MaHangVe = :NEW.MaHangVe;


    IF :NEW.GiaTien <> v_HeSo * v_GiaVe THEN
        RAISE_APPLICATION_ERROR(-20002, 'Tổng giá tiền thanh toán không hợp lệ.');
    END IF;
END;
/

/* R4: Cập nhật doanh thu  tháng khi có thêm doanh thu chuyến bay.*/

-- DROP TRIGGER Update_DoanhThu_Thang;

CREATE OR REPLACE TRIGGER Update_DoanhThu_Thang
    FOR INSERT OR update ON CT_DATVE
    COMPOUND TRIGGER
    v_nam NUMBER;
    v_thang NUMBER;
    giave NUMBER;
    v_dem NUMBER;
    v_dem_nam NUMBER;
    v_machuyenbay VARCHAR2(10);
    v_countMaChuyenBay NUMBER;
    CheckExist NUMBER := 0 ;
    v_count NUMBER;


BEFORE STATEMENT IS
BEGIN
    NULL;
END BEFORE STATEMENT;

    BEFORE EACH ROW IS
    BEGIN

        IF (:NEW.NgayThanhToan = NULL) THEN
            BEGIN
                SELECT 0,0 INTO v_nam,V_THANG
                FROM dual;
            END;
        ELSE
            begin
                SELECT EXTRACT(YEAR FROM :NEW.NgayThanhToan), EXTRACT(MONTH FROM :NEW.NgayThanhToan) INTO v_nam,v_thang
                FROM dual;

                SELECT Giatien INTO giave
                FROM VE v
                WHERE V.MaVE = :NEW.MaVE;

                SELECT Machuyenbay INTO v_machuyenbay
                FROM ve
                WHERE Ve.MaVE = :NEW.MAVE;
            END;
        END IF;

        IF (:NEW.TrangThai = 2) THEN
            BEGIN
                CheckExist := 1;
            END;
        END IF;

    END BEFORE EACH ROW;


    AFTER EACH ROW IS
    BEGIN
        NULL;
    END AFTER EACH ROW;


    AFTER STATEMENT IS
    BEGIN
        IF (v_nam > 0 AND v_thang > 0 AND CheckExist = 0) THEN
            BEGIN
                SELECT COUNT(*) INTO v_dem
                FROM baocaothang
                WHERE thang = v_thang AND nam = v_nam;

                SELECT COUNT(*) INTO v_dem_nam
                FROM baocaonam
                WHERE nam = v_nam;

                SELECT COUNT(*) INTO v_countMaChuyenBay
                FROM BAOCAOTHANG
                WHERE machuyenbay = v_machuyenbay;

                IF (v_dem > 0) THEN
                    begin
                        IF (v_countMaChuyenBay > 0 ) THEN
                            begin
                                UPDATE BAOCAOTHANG
                                SET DoanhThu = DoanhThu + GiaVe,
                                    SoVeDaBan = SoVeDaBan + 1
                                WHERE nam = v_Nam AND thang = v_Thang AND machuyenbay = v_machuyenbay;
                            END;
                        ELSE
                            BEGIN
                                INSERT INTO BAOCAOTHANG ( MaChuyenBay, SoVeDaBan, DoanhThu, Thang, Nam)
                                VALUES (v_machuyenbay, 1, giave, v_thang, v_nam);
                            END;
                        END IF;
                    END;
                ELSE
                    BEGIN
                        INSERT INTO BAOCAOTHANG ( MaChuyenBay, SoVeDaBan, DoanhThu, Thang, Nam)
                        VALUES (v_machuyenbay, 1, giave, v_thang, v_nam);
                    END;
                END IF;

                UPDATE baocaonam
                SET doanhthu = doanhthu + GIAVE
                WHERE nam = v_nam AND thang = v_thang;

            END;
        END IF;

        ----
        IF (CheckExist = 1 ) THEN
            BEGIN
                SELECT sovedaban INTO v_count
                FROM BAOCAOTHANG
                WHERE thang = v_thang AND nam = v_nam AND machuyenbay = v_machuyenbay ;

                IF (v_count > 1 ) THEN
                    begin
                        UPDATE BAOCAOTHANG
                        SET DoanhThu = DoanhThu - GiaVe,
                            SoVeDaBan = SoVeDaBan - 1
                        WHERE nam = v_Nam AND thang = v_Thang AND machuyenbay = v_machuyenbay;

                        UPDATE BAOCAONAM
                        SET doanhthu = doanhthu - giave
                        WHERE nam = v_nam AND thang = v_thang;
                    END;
                ELSE
                    BEGIN
                        UPDATE BAOCAONAM
                        SET doanhthu = doanhthu - giave, SOCHUYENBAY = SOCHUYENBAY - 1
                        WHERE nam = v_nam AND thang = v_thang;

                        DELETE FROM baocaothang
                        WHERE nam = v_Nam AND thang = v_Thang AND machuyenbay = v_machuyenbay;

                    END;
                END IF;
            END;
        END IF;


    END AFTER STATEMENT;
    END Update_DoanhThu_Thang;


--trigger cho delete
--DROP TRIGGER Update_DoanhThu_Thang_delete;

CREATE OR REPLACE TRIGGER Update_DoanhThu_Thang_delete
    FOR delete ON CT_DATVE
    COMPOUND TRIGGER
    v_nam NUMBER;
    v_thang NUMBER;
    giave NUMBER;
    v_dem NUMBER;
    v_machuyenbay VARCHAR2(10);
    v_count NUMBER;

BEFORE STATEMENT IS
BEGIN
    NULL;
END BEFORE STATEMENT;

    BEFORE EACH ROW IS
    BEGIN
        SELECT EXTRACT(YEAR FROM :OLD.NgayThanhToan), EXTRACT(MONTH FROM :OLD.NgayThanhToan) INTO v_nam,v_thang
        FROM dual;
        SELECT Giatien INTO giave
        FROM VE v
        WHERE V.MaVE = :OLD.MaVE;

        SELECT machuyenbay INTO v_machuyenbay
        FROM VE
        WHERE mave = :OLD.mave;
    END BEFORE EACH ROW;

    AFTER EACH ROW IS
    BEGIN
        NULL;
    END AFTER EACH ROW;

    AFTER STATEMENT IS
    BEGIN
        SELECT sovedaban INTO v_count
        FROM BAOCAOTHANG
        WHERE thang = v_thang AND nam = v_nam AND machuyenbay = v_machuyenbay ;

        IF (v_count > 1 ) THEN
            begin
                UPDATE BAOCAOTHANG
                SET DoanhThu = DoanhThu - GiaVe,
                    SoVeDaBan = SoVeDaBan - 1
                WHERE nam = v_Nam AND thang = v_Thang AND machuyenbay = v_machuyenbay;

                UPDATE BAOCAONAM
                SET doanhthu = doanhthu - giave
                WHERE nam = v_nam AND thang = v_thang;
            END;
        ELSE
            BEGIN
                UPDATE BAOCAOTHANG
                SET DoanhThu = DoanhThu - GiaVe,
                    SoVeDaBan = SoVeDaBan - 1
                WHERE nam = v_Nam AND thang = v_Thang AND machuyenbay = v_machuyenbay;

                UPDATE BAOCAONAM
                SET doanhthu = doanhthu - giave, SOCHUYENBAY = SOCHUYENBAY - 1
                WHERE nam = v_nam AND thang = v_thang;

                DELETE FROM baocaothang
                WHERE nam = v_Nam AND thang = v_Thang AND machuyenbay = v_machuyenbay;

            END;
        END IF;
    END AFTER STATEMENT;

    END Update_DoanhThu_Thang_delete;

/* R4: Mỗi vé phải đặt trước một khoảng thời gian nhất định trước khi máy bay xuất phát. */
--DROP TRIGGER trigger_CT_DATVE

CREATE OR REPLACE TRIGGER trigger_CT_DATVE
    BEFORE INSERT OR update ON CT_DATVE
    FOR EACH ROW
DECLARE
    v_MinBookingTime NUMBER;
    v_Interval INTERVAL DAY(4) TO SECOND;
    v_TimeMin INTERVAL DAY(4) TO SECOND;
BEGIN
    SELECT GiaTri into v_MinBookingTime
    FROM THAMSO
    WHERE TenThuocTinh = 'ThoiGianToiThieuDatVe';

    v_Interval := NUMTODSINTERVAL(v_MinBookingTime, 'HOUR');

    SELECT (TGXP - :NEW.NgayMuaVe  ) INTO v_TimeMin
    FROM chuyenbay cb, ve v
    WHERE V.MAVE = :NEW.MaVE  AND CB.MACHUYENBAY = V.MACHUYENBAY;


    IF v_TimeMin < v_Interval THEN
        RAISE_APPLICATION_ERROR(-20004, 'Mỗi vé phải được đặt trước ít nhất ' || v_MinBookingTime || ' giờ trước khi máy bay xuất phát.');
    END IF;

END;
/



/* R5: Cập nhật Số chuyến bay báo cáo năm khi có chuyến bay mới được update vào baocaothang.*/
--DROP TRIGGER Update_DoanhThu_Nam;
SHOW
CREATE OR REPLACE TRIGGER Update_DoanhThu_Nam
    BEFORE INSERT ON baocaothang
    FOR EACH ROW
DECLARE
    v_checkExist NUMBER;
BEGIN

    SELECT COUNT(*) INTO V_CHECKEXIST
    FROM baocaonam
    WHERE nam = :NEW.Nam AND thang = :NEW.thang;

    IF (V_CHECKEXIST > 0) THEN
        BEGIN
            UPDATE BAOCAONAM
            SET sochuyenbay = sochuyenbay + 1
            WHERE thang = :NEW.thang AND nam = :NEW.nam;
        END;
    ELSE
        BEGIN
            INSERT INTO baocaonam(thang,nam,sochuyenbay,doanhthu) VALUES (:NEW.thang,:NEW.nam,1,0);
        END;
    END IF;
END;
/





/* R6:  Giá tiền của Vé là một số không âm. */

ALTER TABLE VE
    ADD CONSTRAINT Check_GiaTien_ CHECK (GiaTien >= 0);


/* R7: Trạng thái là chưa đổi hoặc đã đổi */

ALTER TABLE HANGVE
    ADD CONSTRAINT Check_TrangThai_HV CHECK (TrangThai IN (0,1));


/* R8.1: Trạng thái mặc định ban đầu là chưa đổi */

ALTER TABLE HANGVE
    MODIFY TrangThai DEFAULT 1;


/* R18.2: Trạng thái mặc định ban đầu là chưa đổi */

ALTER TABLE CT_DATVE
    MODIFY TrangThai DEFAULT 1;



/* R9: Số vé là một số không âm. */

ALTER TABLE baocaothang
    ADD CONSTRAINT Check_SoVe CHECK (SoVeDaBan >= 0);


/* R10: Doanh thu là một số không âm. */

ALTER TABLE baocaothang
    ADD CONSTRAINT Check_DoanhThu CHECK (DoanhThu >= 0);


/* R11: Doanh thu mặc định là 0. */

ALTER TABLE BAOCAONAM
    MODIFY DoanhThu DEFAULT 0;


/* R12: So Ve mặc định là 0.  */

ALTER TABLE baocaothang
    MODIFY SoVeDaBan DEFAULT 0;


/* R13: Thời gian bay phải không nhỏ hơn thời gian bay tối thiểu. */
--DROP TRIGGER Check_ThoiGianXuatPhat

CREATE OR REPLACE TRIGGER Check_ThoiGianXuatPhat
    BEFORE INSERT OR UPDATE ON CHUYENBAY
    FOR EACH ROW
DECLARE
    v_ThoiGianToiThieu THAMSO.GiaTri%TYPE;
    thoigianbay NUMBER;
    distance INTERVAL DAY TO SECOND;
BEGIN
    SELECT GiaTri INTO v_ThoiGianToiThieu
    FROM THAMSO
    WHERE TenThuocTinh = 'ThoiGianBayToiThieu';

    distance := :NEW.TGKT - :NEW.TGXP;

    SELECT EXTRACT(DAY FROM (distance)) * 24 +
           EXTRACT(HOUR FROM (distance)) +
           EXTRACT(MINUTE FROM (distance)) / 60 +
           EXTRACT(SECOND FROM (distance)) / 3600 into thoigianbay
    FROM dual;

    IF thoigianbay  < v_ThoiGianToiThieu THEN
        RAISE_APPLICATION_ERROR(-20008, 'Thời gian bay phải lớn hơn hoặc bằng thời gian bay tối thiểu.');
    END IF;
END;
/


/* R14: sân bay đến và sân bay đi của đường bay không trùng nhau */
--DROP TRIGGER Check_DuongBay

CREATE OR REPLACE TRIGGER Check_DuongBay
    BEFORE INSERT OR UPDATE ON DUONGBAY
    FOR EACH ROW
BEGIN
    IF :NEW.MaSanBayDi = :NEW.MaSanBayDen THEN
        RAISE_APPLICATION_ERROR(-20009, 'Sân bay đi và sân bay đến không được trùng nhau.');
    END IF;
END;
/

/* R15: nếu số lượng ghế trống = 0 thì không cho đặt vé */
--DROP TRIGGER check_SL_GheTrong;

CREATE OR REPLACE TRIGGER check_SL_GheTrong
    BEFORE INSERT ON CT_DATVE
    FOR EACH ROW
DECLARE
    v_soghetrong CT_HANGVE.SOGHETRONG%TYPE;
BEGIN

    SELECT soghetrong INTO v_soghetrong
    FROM CT_HANGVE cthv, ve v
    WHERE v.MaVE = :NEW.MaVe AND v.machuyenbay = cthv.machuyenbay AND v.MAHANGVE = cthv.MAHANGVE;


    IF (v_SoGheTrong = 0) THEN
        RAISE_APPLICATION_ERROR(-20009, 'Hết vé trống');
    END IF;
END;
/

/* R16: xóa VE và CT_HANGVE liên quan đến chuyến bay đã xóa */
-- DROP TRIGGER rf_VE_CT_DATVE_delete_CHUYENBAY;

CREATE OR REPLACE TRIGGER rf_VE_CT_HANGVE_delete_CHUYENBAY
    AFTER DELETE ON CHUYENBAY
    FOR EACH ROW
BEGIN
    -- Xóa các bản ghi liên quan từ bảng VE
    FOR rec IN (SELECT MaVe FROM VE WHERE MaChuyenBay = :OLD.MaChuyenBay) LOOP
            delete_CT_DATVE_by_VE(rec.MaVe);
        END LOOP;

    DELETE FROM VE
    WHERE MaChuyenBay = :OLD.MaChuyenBay;

    -- Xóa các bản ghi liên quan từ bảng CT_HANGVE
    DELETE FROM CT_HANGVE
    WHERE MaChuyenBay = :OLD.MaChuyenBay;
END;
/
