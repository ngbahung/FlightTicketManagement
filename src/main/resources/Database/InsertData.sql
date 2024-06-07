
--insert dữ liệu


-- TABLE : SANBAY

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD001', 'Tân Sơn Nhất', 'SGN', 'Ho Chi Minh City', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD002', 'Nội Bài', 'HAN', 'Ha Noi', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD003', 'Chu Lai', 'VCL', 'Quang Nam', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD004', 'Phú Quốc', 'PQC', 'Kien Giang', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD005', 'Đà Nẵng', 'DAD', 'Da Nang', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD006', 'Phù Cát', 'UIH', 'Bình Dinh', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD007', 'Pleiku', 'PXU', 'Gia Lai', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD008', 'Vân Đồn', 'VDO', 'Quang Ninh', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD009', 'Cát Bi', 'HPH', 'Hai Phong', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD010', 'Vinh', 'VII', 'Nghe An', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD011', 'Phú Bài', 'HUI', 'Huế', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD012', 'Cam Ranh', 'CXR', 'Khách Hòa', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD013', 'Liên Khương', 'DLI', 'Lâm Đồng', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD014', 'Cần Thơ', 'VCA', 'Cần Thơ', 1);

-- TABLE: DUONGBAY

-- Routes from quangnam(Chu lai - VCL)

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay,trangthai)
VALUES ('DB001', 'SBD003', 'SBD002', 'VCL-HAN', 9800,1);

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay,trangthai)
VALUES ('DB002', 'SBD003', 'SBD004','VCL-PQC', 9800,1);

-- Routes from binhdinh (phu cat - UIH)

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay,trangthai)
VALUES ('DB003', 'SBD006', 'SBD002', 'UIH-HAN', 9800,1);

-- Routes from Hanoi (Noi Bai - HAN)

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay,trangthai)
VALUES ('DB004', 'SBD002', 'SBD001', 'HAN-SGN', 1200,1);

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay,trangthai)
VALUES ('DB005', 'SBD002', 'SBD003', 'HAN-VCL', 9800,1);

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay,trangthai)
VALUES ('DB006', 'SBD002', 'SBD005','HAN-DAD', 6700,1);

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay,trangthai)
VALUES ('DB007', 'SBD002', 'SBD006','HAN-UIH', 9800,1);

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay,trangthai)
VALUES ('DB008', 'SBD002', 'SBD004', 'HAN-PQC', 1250,1); -- Hanoi to Phu Quoc

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay,trangthai)
VALUES ('DB009', 'SBD002', 'SBD007', 'HAN-PXU', 760,1); -- Hanoi to Pleiku

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay,trangthai)
VALUES ('DB010', 'SBD002', 'SBD010', 'HAN-VII', 290,1); -- Hanoi to Vinh

-- Routes from Ho Chi Minh City (Tan Son Nhat - SGN)
INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay,trangthai)
VALUES ('DB011', 'SBD001', 'SBD002', 'SGN-HAN', 1150,1); -- Ho Chi Minh City to Hanoi

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay,trangthai)
VALUES ('DB012', 'SBD001', 'SBD005', 'SGN-DAD', 610,1); -- Ho Chi Minh City to Da Nang

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay,trangthai)
VALUES ('DB013', 'SBD001', 'SBD004', 'SGN-PQC', 300,1); -- Ho Chi Minh City to Phu Quoc

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay,trangthai)
VALUES ('DB14', 'SBD001', 'SBD006', 'SGN-UIH', 440,1); -- Ho Chi Minh City to Quy Nhon

-- Routes from Da Nang (Da Nang - DAD)
INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay,trangthai)
VALUES ('DB015', 'SBD005', 'SBD002', 'DAD-HAN', 630,1); -- Da Nang to Hanoi

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay,trangthai)
VALUES ('DB016', 'SBD005', 'SBD001', 'DAD-SGN', 610,1); -- Da Nang to Ho Chi Minh City

-- Routes from Phu Quoc (Phu Quoc - PQC)
INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay,trangthai)
VALUES ('DB017', 'SBD004', 'SBD002', 'PQC-HAN', 1250,1); -- Phu Quoc to Hanoi

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay,trangthai)
VALUES ('DB018', 'SBD004', 'SBD001', 'PQC-SGN', 300,1); -- Phu Quoc to Ho Chi Minh City

-- Routes from Vinh (Vinh - VII)
INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay,trangthai)
VALUES ('DB019', 'SBD010', 'SBD002', 'VII-HAN', 290,1); -- Vinh to Hanoi

-- Routes from Can Tho (Can Tho - VCA)
INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay,trangthai)
VALUES ('DB020', 'SBD014', 'SBD002', 'VCA-HAN', 1220,1); -- Can Tho to Hanoi

-- Routes from Van Don (Van Don - VDO)
INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay,trangthai)
VALUES ('DB021', 'SBD008', 'SBD001', 'VDO-SGN', 1270,1); -- Van Don to Ho Chi Minh City

-- Routes from Hai Phong (Cat Bi - HPH)
INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay,trangthai)
VALUES ('DB022', 'SBD009', 'SBD001', 'HPH-SGN', 1120,1); -- Hai Phong to Ho Chi Minh City

-- Routes from Hue (Phu Bai - HUI)
INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay,trangthai)
VALUES ('DB023', 'SBD011', 'SBD002', 'HUI-HAN', 540,1); -- Hue to Hanoi

-- Routes from Cam Ranh (Cam Ranh - CXR)
INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay,trangthai)
VALUES ('DB024', 'SBD012', 'SBD001', 'CXR-SGN', 350,1); -- Cam Ranh to Ho Chi Minh City

-- Routes from Lien Khuong (Lien Khuong - DLI)
INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay,trangthai)
VALUES ('DB025', 'SBD013', 'SBD001', 'DLI-SGN', 250,1); -- Lien Khuong to Ho Chi Minh City

-- Routes from Pleiku (Pleiku - PXU)
INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay,trangthai)
VALUES ('DB026', 'SBD007', 'SBD001', 'PXU-SGN', 370,1); -- Pleiku to Ho Chi Minh City


-- TABLE : CHUYENBAY

-- Past flights in 2023

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB001', 'DB001', TO_TIMESTAMP('2023-01-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-01-11 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 850000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB002', 'DB002', TO_TIMESTAMP('2023-02-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-11 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 850000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB003', 'DB003', TO_TIMESTAMP('2023-03-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-03-11 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 850000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB004', 'DB004', TO_TIMESTAMP('2023-04-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-04-11 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 850000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB005', 'DB005', TO_TIMESTAMP('2023-05-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-05-11 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 850000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB006', 'DB006', TO_TIMESTAMP('2023-06-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-11 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 850000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB007', 'DB007', TO_TIMESTAMP('2023-07-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-07-11 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 850000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB008', 'DB008', TO_TIMESTAMP('2023-08-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-08-11 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 850000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB009', 'DB009', TO_TIMESTAMP('2023-09-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-09-11 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 850000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB010', 'DB010', TO_TIMESTAMP('2023-10-10 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-10-11 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 900000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB011', 'DB011', TO_TIMESTAMP('2023-11-10 11:45:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-11-11 14:15:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 950000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB012', 'DB012', TO_TIMESTAMP('2024-12-10 16:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-12-11 19:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 1000000);

-- Future flights in 2024


INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB013', 'DB001', TO_TIMESTAMP('2024-07-01 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-07-02 12:30:00', 'YYYY-MM-DD HH24:MI:SS'), 0, 1000000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB014', 'DB002', TO_TIMESTAMP('2024-08-15 14:15:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-08-16 16:45:00', 'YYYY-MM-DD HH24:MI:SS'), 0, 1000000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB015', 'DB003', TO_TIMESTAMP('2024-09-20 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-09-21 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), 0, 1000000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB016', 'DB001', TO_TIMESTAMP('2024-10-10 18:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-10-11 20:45:00', 'YYYY-MM-DD HH24:MI:SS'), 0, 1000000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB017', 'DB002', TO_TIMESTAMP('2024-11-25 06:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-11-26 09:00:00', 'YYYY-MM-DD HH24:MI:SS'),0, 1000000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB018', 'DB003', TO_TIMESTAMP('2024-12-30 23:59:59', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-12-31 01:30:00', 'YYYY-MM-DD HH24:MI:SS'), 0, 1000000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB019', 'DB001', TO_TIMESTAMP('2024-06-15 07:45:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-06-16 10:15:00', 'YYYY-MM-DD HH24:MI:SS'), 0, 1000000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB020', 'DB008', TO_TIMESTAMP('2024-08-10 06:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-08-10 08:30:00', 'YYYY-MM-DD HH24:MI:SS'), 0, 1050000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB021', 'DB009', TO_TIMESTAMP('2024-09-15 13:15:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-09-15 15:45:00', 'YYYY-MM-DD HH24:MI:SS'),0, 1100000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB022', 'DB010', TO_TIMESTAMP('2024-10-20 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-10-20 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), 0, 1150000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB023', 'DB011', TO_TIMESTAMP('2024-11-25 07:45:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-11-25 10:15:00', 'YYYY-MM-DD HH24:MI:SS'), 0, 1200000);


-- TABLE : HANGVE

INSERT INTO HANGVE (MaHangVe, TenHangVe, HeSo, TrangThai)
VALUES ('HV001', 'Phổ Thông', 1, 1);

INSERT INTO HANGVE (MaHangVe, TenHangVe, HeSo, TrangThai)
VALUES ('HV002', 'Phòng Đặc Biệt', 1.5, 1);

INSERT INTO HANGVE (MaHangVe, TenHangVe, HeSo, TrangThai)
VALUES ('HV003', 'Thương Gia', 2.0, 1);

INSERT INTO HANGVE (MaHangVe, TenHangVe, HeSo, TrangThai)
VALUES ('HV004', 'Hạng Nhất', 2.5,  1);

INSERT INTO HANGVE (MaHangVe, TenHangVe, HeSo, TrangThai)
VALUES ('HV005', 'Ghế Trẻ Em', 0.5,  1);

INSERT INTO HANGVE (MaHangVe, TenHangVe, HeSo, TrangThai)
VALUES ('HV006', 'Ghế Phụ', 0.2,  1);



-- TABLE : VE

--Past
-- Tickets for flight CB001
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0001', 'CB001', 'HV001', 1, 850000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0002', 'CB001', 'HV002', 2, 1275000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0003', 'CB001', 'HV001', 3, 850000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0004', 'CB001', 'HV003', 4, 1700000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0005', 'CB001', 'HV001', 5, 850000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0006', 'CB001', 'HV004', 6, 2125000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0007', 'CB003', 'HV001', 7, 850000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0008', 'CB003', 'HV005', 8, 425000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0009', 'CB003', 'HV001', 9, 850000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0010', 'CB003', 'HV006', 10, 170000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0011', 'CB002', 'HV001', 11, 850000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0012', 'CB002', 'HV002', 12, 1275000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0013', 'CB002', 'HV001', 13, 850000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0014', 'CB002', 'HV003', 14, 1700000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0015', 'CB002', 'HV001', 15, 850000);

-- Tickets for flight CB002
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0016', 'CB002', 'HV004', 6, 2250000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0017', 'CB002', 'HV001', 7, 900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0018', 'CB002', 'HV005', 8, 450000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0019', 'CB003', 'HV001', 9, 900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0020', 'CB003', 'HV006', 10, 180000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0021', 'CB003', 'HV001', 11, 900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0022', 'CB005', 'HV002', 12, 1350000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0023', 'CB005', 'HV001', 13, 900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0024', 'CB005', 'HV003', 14, 1800000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0025', 'CB007', 'HV001', 15, 900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0026', 'CB007', 'HV001', 1, 900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0027', 'CB007', 'HV002', 2, 1350000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0028', 'CB009', 'HV001', 3, 900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0029', 'CB009', 'HV003', 4, 1800000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0030', 'CB009', 'HV001', 5, 900000);


-- Tickets for flight CB003
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0031', 'CB003', 'HV001', 1, 950000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0032', 'CB003', 'HV002', 2, 1425000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0033', 'CB003', 'HV001', 3, 950000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0034', 'CB010', 'HV003', 4, 1900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0035', 'CB010', 'HV001', 5, 950000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0036', 'CB010', 'HV004', 6, 2375000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0037', 'CB010', 'HV001', 7, 950000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0038', 'CB010', 'HV005', 8, 475000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0039', 'CB004', 'HV001', 9, 950000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0040', 'CB004', 'HV006', 10, 190000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0041', 'CB004', 'HV001', 11, 950000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0042', 'CB004', 'HV002', 12, 1425000);


-- Tickets for flight CB004
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0043', 'CB004', 'HV001', 1, 1000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0044', 'CB004', 'HV002', 2, 1500000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0045', 'CB004', 'HV001', 3, 1000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0046', 'CB012', 'HV003', 4, 2000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0047', 'CB012', 'HV001', 5, 1000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0048', 'CB012', 'HV004', 6, 2500000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0049', 'CB012', 'HV001', 7, 1000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0050', 'CB012', 'HV005', 8, 500000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0051', 'CB011', 'HV001', 9, 1000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0052', 'CB011', 'HV006', 10, 200000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0053', 'CB011', 'HV001', 11, 1000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0054', 'CB011', 'HV002', 12, 1500000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0055', 'CB011', 'HV001', 13, 1000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0056', 'CB011', 'HV003', 14, 2000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0057', 'CB011', 'HV001', 15, 1000000);



-- Tickets for flight CB005
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0058', 'CB005', 'HV001', 1, 1050000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0059', 'CB005', 'HV002', 2, 1575000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0060', 'CB005', 'HV001', 3, 1050000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0061', 'CB005', 'HV003', 4, 2100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0062', 'CB005', 'HV001', 5, 1050000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0063', 'CB007', 'HV004', 6, 2625000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0064', 'CB007', 'HV001', 7, 1050000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0065', 'CB007', 'HV005', 8, 525000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0066', 'CB007', 'HV001', 9, 1050000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0067', 'CB009', 'HV006', 10, 210000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0068', 'CB009', 'HV001', 11, 1050000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0069', 'CB009', 'HV002', 12, 1575000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0070', 'CB009', 'HV001', 13, 1050000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0071', 'CB009', 'HV003', 14, 2100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0072', 'CB009', 'HV001', 15, 1050000);


-- Tickets for flight CB006
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0073', 'CB006', 'HV001', 1, 1100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0074', 'CB006', 'HV002', 2, 1650000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0075', 'CB006', 'HV001', 3, 1100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0076', 'CB006', 'HV003', 4, 2200000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0077', 'CB006', 'HV001', 5, 1100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0078', 'CB006', 'HV004', 6, 2750000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0079', 'CB006', 'HV001', 7, 1100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0080', 'CB006', 'HV005', 8, 550000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0081', 'CB006', 'HV001', 9, 1100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0082', 'CB006', 'HV006', 10, 220000);


-- Tickets for flight CB007
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0083', 'CB007', 'HV003', 1, 1500000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0084', 'CB007', 'HV002', 2, 2400000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0085', 'CB007', 'HV005', 3, 1250000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0086', 'CB007', 'HV001', 4, 1000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0087', 'CB008', 'HV004', 5, 2200000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0088', 'CB008', 'HV006', 6, 1600000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0089', 'CB008', 'HV002', 7, 1800000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0090', 'CB008', 'HV003', 8, 1400000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0091', 'CB009', 'HV001', 9, 2000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0092', 'CB009', 'HV005', 10, 1300000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0093', 'CB009', 'HV006', 11, 2500000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0094', 'CB009', 'HV004', 12, 2300000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0095', 'CB011', 'HV003', 13, 1900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0096', 'CB011', 'HV002', 14, 1750000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0097', 'CB011', 'HV001', 15, 2100000);


-- Tickets for flight CB008
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0098', 'CB008', 'HV002', 1, 1350000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0099', 'CB008', 'HV004', 2, 2250000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0100', 'CB008', 'HV006', 3, 1600000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0101', 'CB008', 'HV003', 4, 1950000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0102', 'CB008', 'HV005', 5, 1250000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0103', 'CB011', 'HV001', 6, 1100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0104', 'CB011', 'HV002', 7, 1700000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0105', 'CB011', 'HV004', 8, 2100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0106', 'CB011', 'HV001', 9, 1150000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0107', 'CB012', 'HV006', 10, 2200000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0108', 'CB012', 'HV003', 11, 1850000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0109', 'CB012', 'HV002', 12, 1450000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0110', 'CB012', 'HV005', 13, 1550000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0111', 'CB012', 'HV004', 14, 2350000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0112', 'CB012', 'HV001', 15, 2000000);


-- Tickets for flight CB009
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0113', 'CB009', 'HV004', 1, 2100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0114', 'CB009', 'HV002', 2, 1600000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0115', 'CB009', 'HV003', 3, 2300000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0116', 'CB009', 'HV001', 4, 1100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0117', 'CB011', 'HV005', 5, 1200000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0118', 'CB011', 'HV006', 6, 1750000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0119', 'CB011', 'HV001', 7, 1300000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0120', 'CB011', 'HV004', 8, 2000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0121', 'CB011', 'HV002', 9, 1800000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0122', 'CB011', 'HV003', 10, 2100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0123', 'CB011', 'HV005', 11, 1350000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0124', 'CB011', 'HV006', 12, 1900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0125', 'CB012', 'HV001', 13, 1000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0126', 'CB012', 'HV002', 14, 1500000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0127', 'CB012', 'HV004', 15, 2200000);

-- Tickets for flight CB010
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0128', 'CB010', 'HV005', 1, 1250000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0129', 'CB010', 'HV001', 2, 1450000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0130', 'CB010', 'HV003', 3, 1950000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0131', 'CB010', 'HV002', 4, 1500000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0132', 'CB010', 'HV006', 5, 2200000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0133', 'CB010', 'HV004', 6, 2050000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0134', 'CB010', 'HV005', 7, 1350000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0135', 'CB010', 'HV001', 8, 1100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0136', 'CB010', 'HV003', 9, 1950000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0137', 'CB010', 'HV004', 10, 1800000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0138', 'CB010', 'HV002', 11, 1450000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0139', 'CB010', 'HV006', 12, 1750000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0140', 'CB010', 'HV005', 13, 1250000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0141', 'CB010', 'HV001', 14, 1300000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0142', 'CB010', 'HV003', 15, 2000000);



-- Tickets for flight CB011
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0143', 'CB011', 'HV002', 1, 1400000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0144', 'CB011', 'HV005', 2, 1350000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0145', 'CB011', 'HV003', 3, 1900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0146', 'CB011', 'HV004', 4, 2100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0147', 'CB011', 'HV006', 5, 1700000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0148', 'CB011', 'HV001', 6, 1250000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0149', 'CB011', 'HV002', 7, 1500000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0150', 'CB011', 'HV005', 8, 1350000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0151', 'CB011', 'HV003', 9, 1950000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0152', 'CB011', 'HV001', 10, 1200000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0153', 'CB011', 'HV004', 11, 2200000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0154', 'CB011', 'HV006', 12, 1800000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0155', 'CB011', 'HV002', 13, 1400000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0156', 'CB011', 'HV003', 14, 2000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0157', 'CB011', 'HV001', 15, 1100000);


-- Tickets for flight CB012

INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0158', 'CB012', 'HV002', 1, 1550000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0159', 'CB012', 'HV004', 2, 2250000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0160', 'CB012', 'HV003', 3, 2050000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0161', 'CB012', 'HV005', 4, 1300000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0162', 'CB012', 'HV001', 5, 1000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0163', 'CB012', 'HV002', 6, 1600000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0164', 'CB012', 'HV006', 7, 1900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0165', 'CB012', 'HV003', 8, 2100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0166', 'CB012', 'HV005', 9, 1350000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0167', 'CB012', 'HV001', 10, 1050000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0168', 'CB012', 'HV002', 11, 1500000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0169', 'CB012', 'HV004', 12, 2200000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0170', 'CB012', 'HV006', 13, 1850000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0171', 'CB012', 'HV003', 14, 2050000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0172', 'CB012', 'HV001', 15, 1200000);


--Future
-- Tickets for flight CB013
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0173', 'CB013', 'HV001', 1, 2100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0174', 'CB013', 'HV002', 2, 1450000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0175', 'CB013', 'HV003', 3, 2250000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0176', 'CB013', 'HV004', 4, 1300000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0177', 'CB013', 'HV005', 5, 2000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0178', 'CB013', 'HV006', 6, 1800000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0179', 'CB013', 'HV001', 7, 1500000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0180', 'CB013', 'HV002', 8, 2050000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0181', 'CB013', 'HV003', 9, 1350000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0182', 'CB013', 'HV004', 10, 2200000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0183', 'CB013', 'HV005', 11, 1900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0184', 'CB013', 'HV006', 12, 1750000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0185', 'CB013', 'HV001', 13, 1100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0186', 'CB013', 'HV002', 14, 1600000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0187', 'CB013', 'HV003', 15, 1950000);


-- Tickets for flight CB014

INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0188', 'CB014', 'HV001', 1, 1200000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0189', 'CB014', 'HV002', 2, 1300000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0190', 'CB014', 'HV003', 3, 1300000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0191', 'CB014', 'HV004', 4, 1350000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0192', 'CB014', 'HV005', 5, 2000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0193', 'CB014', 'HV006', 6, 1400000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0194', 'CB014', 'HV001', 7, 1550000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0195', 'CB014', 'HV002', 8, 1700000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0196', 'CB014', 'HV003', 9, 1850000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0197', 'CB014', 'HV004', 10, 2100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0198', 'CB014', 'HV005', 11, 1350000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0199', 'CB014', 'HV006', 12, 1300000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0200', 'CB014', 'HV001', 13, 1100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0201', 'CB014', 'HV002', 14, 2000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0202', 'CB014', 'HV003', 15, 1350000);


-- Tickets for flight CB015
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0203', 'CB015', 'HV001', 1, 1100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0204', 'CB015', 'HV002', 2, 1450000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0205', 'CB015', 'HV003', 3, 1950000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0206', 'CB015', 'HV004', 4, 1300000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0207', 'CB015', 'HV005', 5, 1750000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0208', 'CB015', 'HV006', 6, 1800000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0209', 'CB015', 'HV001', 7, 1500000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0210', 'CB015', 'HV002', 8, 2050000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0211', 'CB015', 'HV003', 9, 1350000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0212', 'CB015', 'HV004', 10, 2200000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0213', 'CB015', 'HV005', 11, 1900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0214', 'CB015', 'HV006', 12, 1100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0215', 'CB015', 'HV001', 13, 1100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0216', 'CB015', 'HV002', 14, 1500000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0217', 'CB015', 'HV003', 15, 1500000);


-- Tickets for flight CB016

INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0218', 'CB016', 'HV001', 1, 2150000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0219', 'CB016', 'HV002', 2, 1480000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0220', 'CB016', 'HV003', 3, 2300000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0221', 'CB016', 'HV004', 4, 1320000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0222', 'CB016', 'HV005', 5, 1980000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0223', 'CB016', 'HV006', 6, 1830000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0224', 'CB016', 'HV001', 7, 1520000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0225', 'CB016', 'HV002', 8, 2080000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0226', 'CB016', 'HV003', 9, 1380000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0227', 'CB016', 'HV004', 10, 2230000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0228', 'CB016', 'HV005', 11, 1930000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0229', 'CB016', 'HV006', 12, 1780000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0230', 'CB016', 'HV001', 13, 1130000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0231', 'CB016', 'HV002', 14, 1630000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0232', 'CB016', 'HV003', 15, 1980000);



-- Tickets for flight CB017
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0233', 'CB017', 'HV004', 1, 1350000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0234', 'CB017', 'HV003', 2, 2100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0235', 'CB017', 'HV006', 3, 1780000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0236', 'CB017', 'HV002', 4, 1490000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0237', 'CB017', 'HV005', 5, 2200000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0238', 'CB017', 'HV001', 6, 1880000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0239', 'CB017', 'HV004', 7, 1420000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0240', 'CB017', 'HV002', 8, 1550000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0241', 'CB017', 'HV006', 9, 2000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0242', 'CB017', 'HV005', 10, 1750000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0243', 'CB017', 'HV001', 11, 1100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0244', 'CB017', 'HV003', 12, 2300000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0245', 'CB017', 'HV002', 13, 1950000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0246', 'CB017', 'HV004', 14, 1250000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0247', 'CB017', 'HV003', 15, 1600000);


-- Tickets for flight CB018
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0248', 'CB018', 'HV001', 1, 1850000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0249', 'CB018', 'HV002', 2, 1470000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0250', 'CB018', 'HV003', 3, 1650000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0251', 'CB018', 'HV004', 4, 2000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0252', 'CB018', 'HV005', 5, 1900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0253', 'CB018', 'HV006', 6, 1750000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0254', 'CB018', 'HV001', 7, 2150000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0255', 'CB018', 'HV002', 8, 1380000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0256', 'CB018', 'HV003', 9, 1450000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0257', 'CB018', 'HV004', 10, 2200000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0258', 'CB018', 'HV005', 11, 1600000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0259', 'CB018', 'HV006', 12, 1700000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0260', 'CB018', 'HV001', 13, 1400000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0261', 'CB018', 'HV002', 14, 1550000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0262', 'CB018', 'HV003', 15, 1900000);


-- Tickets for flight CB019
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0263', 'CB019', 'HV001', 1, 1500000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0264', 'CB019', 'HV002', 2, 1800000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0265', 'CB019', 'HV003', 3, 1700000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0266', 'CB019', 'HV004', 4, 1600000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0267', 'CB019', 'HV005', 5, 1900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0268', 'CB019', 'HV006', 6, 2200000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0269', 'CB019', 'HV001', 7, 1400000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0270', 'CB019', 'HV002', 8, 2000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0271', 'CB019', 'HV003', 9, 1450000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0272', 'CB019', 'HV004', 10, 2300000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0273', 'CB019', 'HV005', 11, 1600000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0274', 'CB019', 'HV006', 12, 1950000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0275', 'CB019', 'HV001', 13, 1700000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0276', 'CB019', 'HV002', 14, 2150000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0277', 'CB019', 'HV003', 15, 1800000);

-- Tickets for flight CB020
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0278', 'CB020', 'HV001', 1, 1250000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0279', 'CB020', 'HV002', 2, 1450000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0280', 'CB020', 'HV003', 3, 1600000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0281', 'CB020', 'HV004', 4, 2300000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0282', 'CB020', 'HV005', 5, 1400000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0283', 'CB020', 'HV006', 6, 2100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0284', 'CB020', 'HV001', 7, 1900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0285', 'CB020', 'HV002', 8, 1350000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0286', 'CB020', 'HV003', 9, 1550000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0287', 'CB020', 'HV004', 10, 2000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0288', 'CB020', 'HV005', 11, 1850000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0289', 'CB020', 'HV006', 12, 1800000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0290', 'CB020', 'HV001', 13, 1450000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0291', 'CB020', 'HV002', 14, 1650000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0292', 'CB020', 'HV003', 15, 1750000);

-- Tickets for flight CB021
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0293', 'CB021', 'HV004', 1, 1750000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0294', 'CB021', 'HV002', 2, 1500000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0295', 'CB021', 'HV006', 3, 2200000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0296', 'CB021', 'HV001', 4, 1800000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0297', 'CB021', 'HV005', 5, 2000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0298', 'CB021', 'HV003', 6, 1550000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0299', 'CB021', 'HV004', 7, 1650000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0300', 'CB021', 'HV002', 8, 1900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0301', 'CB021', 'HV001', 9, 1400000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0302', 'CB021', 'HV006', 10, 2100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0303', 'CB021', 'HV005', 11, 1250000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0304', 'CB021', 'HV003', 12, 1950000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0305', 'CB021', 'HV004', 13, 1450000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0306', 'CB021', 'HV002', 14, 1700000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0307', 'CB021', 'HV001', 15, 1850000);

-- Tickets for flight CB022
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0308', 'CB022', 'HV003', 1, 1600000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0309', 'CB022', 'HV005', 2, 1700000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0310', 'CB022', 'HV002', 3, 1550000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0311', 'CB022', 'HV006', 4, 2100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0312', 'CB022', 'HV001', 5, 1250000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0313', 'CB022', 'HV004', 6, 1950000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0314', 'CB022', 'HV003', 7, 1400000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0315', 'CB022', 'HV005', 8, 1800000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0316', 'CB022', 'HV002', 9, 1900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0317', 'CB022', 'HV001', 10, 2000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0318', 'CB022', 'HV006', 11, 1450000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0319', 'CB022', 'HV004', 12, 1850000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0320', 'CB022', 'HV003', 13, 1500000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0321', 'CB022', 'HV005', 14, 2300000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0322', 'CB022', 'HV002', 15, 1750000);

-- Tickets for flight CB023
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0323', 'CB023', 'HV005', 1, 1500000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0324', 'CB023', 'HV003', 2, 1700000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0325', 'CB023', 'HV002', 3, 1550000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0326', 'CB023', 'HV001', 4, 2200000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0327', 'CB023', 'HV006', 5, 1900000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0328', 'CB023', 'HV004', 6, 1250000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0329', 'CB023', 'HV003', 7, 2000000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0330', 'CB023', 'HV005', 8, 1350000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0331', 'CB023', 'HV002', 9, 1800000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0332', 'CB023', 'HV001', 10, 2100000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0333', 'CB023', 'HV006', 11, 1400000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0334', 'CB023', 'HV004', 12, 1950000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0335', 'CB023', 'HV003', 13, 1450000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0336', 'CB023', 'HV005', 14, 1700000);
INSERT INTO VE (MaVe, MaChuyenBay, MaHangVe, MaGhe, GiaTien) VALUES ('VE0337', 'CB023', 'HV002', 15, 1850000);



-- TABLE : KHACHHANG

INSERT INTO KHACHHANG (MaKhachHang, HoTen, CCCD, SDT, Email, DiaChi)
VALUES ('KH001', 'Nguyễn Văn A', 123456789, 987654321, 'nguyenvana@example.com', '123 Đường ABC, Quận 1, TP.HCM');

INSERT INTO KHACHHANG (MaKhachHang, HoTen, CCCD, SDT, Email, DiaChi)
VALUES ('KH002', 'Trần Thị B', 987654321, 123456789, 'tranthib@example.com', '456 Đường XYZ, Quận 2, TP.HCM');

INSERT INTO KHACHHANG (MaKhachHang, HoTen, CCCD, SDT, Email, DiaChi)
VALUES ('KH003', 'Lê Văn C', 654321987, 789123456, 'levanc@example.com', '789 Đường KLM, Quận 3, TP.HCM');

INSERT INTO KHACHHANG (MaKhachHang, HoTen, CCCD, SDT, Email, DiaChi)
VALUES ('KH004', 'Phạm Thị D', 321987654, 321789456, 'phamthid@example.com', '987 Đường XYZ, Quận 4, TP.HCM');

INSERT INTO KHACHHANG (MaKhachHang, HoTen, CCCD, SDT, Email, DiaChi)
VALUES ('KH005', 'Hoàng Văn E', 987123654, 456789123, 'hoangvane@example.com', '456 Đường ABC, Quận 5, TP.HCM');

INSERT INTO KHACHHANG (MaKhachHang, HoTen, CCCD, SDT, Email, DiaChi)
VALUES ('KH006', 'Trần Văn F', 456321789, 987123654, 'tranvanf@example.com', '789 Đường KLM, Quận 6, TP.HCM');

INSERT INTO KHACHHANG (MaKhachHang, HoTen, CCCD, SDT, Email, DiaChi)
VALUES ('KH007', 'Nguyễn Thị G', 789654123, 321987654, 'nguyenthig@example.com', '123 Đường XYZ, Quận 7, TP.HCM');


-- TABLE : CT_HANGVE

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB001', 'HV001', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB001', 'HV002', 12, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB001', 'HV003', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB001', 'HV004', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB001', 'HV005', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB001', 'HV006', 15, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB002', 'HV001', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB002', 'HV002', 11, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB002', 'HV003', 12, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB002', 'HV004', 13, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB002', 'HV005', 14, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB002', 'HV006', 10, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB003', 'HV001', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB003', 'HV002', 13, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB003', 'HV003', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB003', 'HV004', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB003', 'HV005', 14, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB003', 'HV006', 10, 0);


INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB004', 'HV001', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB004', 'HV002', 13, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB004', 'HV003', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB004', 'HV004', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB004', 'HV005', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB004', 'HV006', 11, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB005', 'HV001', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB005', 'HV002', 13, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB005', 'HV003', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB005', 'HV004', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB005', 'HV005', 12, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB005', 'HV006', 10, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB006', 'HV001', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB006', 'HV002', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB006', 'HV003', 11, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB006', 'HV004', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB006', 'HV005', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB006', 'HV006', 15, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB007', 'HV001', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB007', 'HV002', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB007', 'HV003', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB007', 'HV004', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB007', 'HV005', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB007', 'HV006', 10, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB008', 'HV001', 12, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB008', 'HV002', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB008', 'HV003', 13, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB008', 'HV004', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB008', 'HV005', 13, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB008', 'HV006', 10, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB009', 'HV001', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB009', 'HV002', 12, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB009', 'HV003', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB009', 'HV004', 12, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB009', 'HV005', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB009', 'HV006', 10, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB010', 'HV001', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB010', 'HV002', 14, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB010', 'HV003', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB010', 'HV004', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB010', 'HV005', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB010', 'HV006', 10, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB011', 'HV001', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB011', 'HV002', 12, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB011', 'HV003', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB011', 'HV004', 12, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB011', 'HV005', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB011', 'HV006', 12, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB012', 'HV001', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB012', 'HV002', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB012', 'HV003', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB012', 'HV004', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB012', 'HV005', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB012', 'HV006', 15, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB013', 'HV001', 13, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB013', 'HV002', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB013', 'HV003', 13, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB013', 'HV004', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB013', 'HV005', 13, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB013', 'HV006', 10, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB014', 'HV001', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB014', 'HV002', 14, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB014', 'HV003', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB014', 'HV004', 14, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB014', 'HV005', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB014', 'HV006', 14, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB015', 'HV001', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB015', 'HV002', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB015', 'HV003', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB015', 'HV004', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB015', 'HV005', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB015', 'HV006', 15, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB016', 'HV001', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB016', 'HV002', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB016', 'HV003', 11, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB016', 'HV004', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB016', 'HV005', 12, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB016', 'HV006', 10, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB017', 'HV001', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB017', 'HV002', 12, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB017', 'HV003', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB017', 'HV004', 13, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB017', 'HV005', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB017', 'HV006', 14, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB018', 'HV001', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB018', 'HV002', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB018', 'HV003', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB018', 'HV004', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB018', 'HV005', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB018', 'HV006', 12, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB019', 'HV001', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB019', 'HV002', 11, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB019', 'HV003', 13, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB019', 'HV004', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB019', 'HV005', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB019', 'HV006', 12, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB020', 'HV001', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB020', 'HV002', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB020', 'HV003', 13, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB020', 'HV004', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB020', 'HV005', 12, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB020', 'HV006', 11, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB021', 'HV001', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB021', 'HV002', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB021', 'HV003', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB021', 'HV004', 15, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB021', 'HV005', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB021', 'HV006', 15, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB022', 'HV001', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB022', 'HV002', 11, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB022', 'HV003', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB022', 'HV004', 12, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB022', 'HV005', 13, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB022', 'HV006', 15, 0);

INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ('CB023', 'HV001', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB023', 'HV002', 14, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB023', 'HV003', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB023', 'HV004', 12, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB023', 'HV005', 10, 0);
INSERT INTO CT_HANGVE (MaChuyenBay, MaHangVe, SoGheTrong, SoGheDat)
VALUES ( 'CB023', 'HV006', 15, 0);


INSERT INTO QUYEN VALUES ('RL0001', N'Admin');
INSERT INTO QUYEN VALUES ('RL0002', N'Manager');
INSERT INTO QUYEN VALUES ('RL0003', N'Staff');


INSERT INTO TAIKHOAN VALUES ('1', N'Nguyễn Thị Trinh', '023455678', 'trinhnt@gmail.com', 'trinhnt', TO_DATE('1/1/2024', 'DD/MM/YYYY'), 'RL0001');
INSERT INTO TAIKHOAN VALUES('2', N'Nguyễn Bá Hưng', '087654321', 'hungnb@gmail.com', 'hungnb', TO_DATE('2/1/2024', 'DD/MM/YYYY'), 'RL0001');
INSERT INTO TAIKHOAN VALUES('3', N'Đào Trung Hiếu', '034669633', 'hieudt@gmail.com', 'hieudt', TO_DATE('3/1/2024', 'DD/MM/YYYY'), 'RL0002');
INSERT INTO TAIKHOAN VALUES ('4', N'Trần Đức Hùng', '0378872312', 'hungtd@gmail.com', 'hungtd', TO_DATE('30/04/2024', 'DD/MM/YYYY'), 'RL0002');
INSERT INTO TAIKHOAN VALUES ('5', N'Mai Văn Tân', '0378872334', 'tanmv@gmail.com', 'tanmv', TO_DATE('1/05/2024', 'DD/MM/YYYY'), 'RL0003');
INSERT INTO TAIKHOAN VALUES ('6', N'Huỳnh Trung', '0378872356', 'trungh@gmail.com', 'trungh', TO_DATE('2/05/2024', 'DD/MM/YYYY'), 'RL0003');


-- TABLE : THAMSO

INSERT INTO THAMSO (MaThuocTinh, TenThuocTinh, GiaTri) VALUES ('TGBTT', 'ThoiGianBayToiThieu', 1);
INSERT INTO THAMSO (MaThuocTinh, TenThuocTinh, GiaTri) VALUES ('TGDTT', 'ThoiGianDungToiThieu', 10);
INSERT INTO THAMSO (MaThuocTinh, TenThuocTinh, GiaTri) VALUES ('TGDTD', 'ThoiGianDungToiDa', 20);
INSERT INTO THAMSO (MaThuocTinh, TenThuocTinh, GiaTri) VALUES ('TGTTDV', 'ThoiGianToiThieuDatVe', 24);
INSERT INTO THAMSO (MaThuocTinh, TenThuocTinh, GiaTri) VALUES ('TGTT_HV', 'ThoiGianToiThieuHuyVe', 24);



-- TABLE : CT_DATVE
--past



INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV001', 'VE0001', 'KH001',TO_TIMESTAMP('2023-01-01 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-01-02 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV002', 'VE0002', 'KH001',TO_TIMESTAMP('2023-01-02 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-01-03 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV003', 'VE0003', 'KH001',TO_TIMESTAMP('2023-01-03 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-01-04 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV004', 'VE0004', 'KH001',TO_TIMESTAMP('2023-01-04 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-01-05 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV005', 'VE0005', 'KH001',TO_TIMESTAMP('2023-01-05 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-01-06 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV006', 'VE0006', 'KH001',TO_TIMESTAMP('2023-01-06 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-01-07 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV007', 'VE0007', 'KH001',TO_TIMESTAMP('2023-01-07 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-01-08 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV008', 'VE0008', 'KH001',TO_TIMESTAMP('2023-01-08 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-01-09 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV009', 'VE0009', 'KH001',TO_TIMESTAMP('2023-01-08 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-01-09 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV0010', 'VE0010', 'KH001',TO_TIMESTAMP('2023-01-08 3:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-01-09 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV011', 'VE0011', 'KH001',TO_TIMESTAMP('2023-01-02 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-01-03 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV012', 'VE0012', 'KH001',TO_TIMESTAMP('2023-01-03 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-01-04 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV013', 'VE0013', 'KH001',TO_TIMESTAMP('2023-01-04 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-01-05 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV014', 'VE0014', 'KH001',TO_TIMESTAMP('2023-01-06 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-01-07 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV015', 'VE0015', 'KH001',TO_TIMESTAMP('2023-01-07 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-01-08 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);


INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV016', 'VE0016', 'KH002', TO_TIMESTAMP('2023-02-01 14:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-02 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV017', 'VE0017', 'KH002', TO_TIMESTAMP('2023-02-02 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-03 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV018', 'VE0018', 'KH002', TO_TIMESTAMP('2023-02-03 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-04 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV019', 'VE0019', 'KH002', TO_TIMESTAMP('2023-02-04 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-05 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV020', 'VE0020', 'KH002', TO_TIMESTAMP('2023-02-05 12:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-06 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV021', 'VE0021', 'KH002', TO_TIMESTAMP('2023-02-06 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-07 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV022', 'VE0022', 'KH002', TO_TIMESTAMP('2023-02-07 09:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-08 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV023', 'VE0023', 'KH002', TO_TIMESTAMP('2023-02-08 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-09 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV024', 'VE0024', 'KH002', TO_TIMESTAMP('2023-02-08 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-09 11:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV025', 'VE0025', 'KH002', TO_TIMESTAMP('2023-02-01 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-02 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV026', 'VE0026', 'KH002', TO_TIMESTAMP('2023-02-03 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-04 16:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV027', 'VE0027', 'KH002', TO_TIMESTAMP('2023-02-05 14:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-06 13:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV028', 'VE0028', 'KH002', TO_TIMESTAMP('2023-02-07 13:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-08 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV029', 'VE0029', 'KH002', TO_TIMESTAMP('2023-02-09 7:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-09 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV030', 'VE0030', 'KH002', TO_TIMESTAMP('2023-02-08 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-09 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1);



INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV031', 'VE0031', 'KH002', TO_TIMESTAMP('2023-02-01 09:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-03 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV032', 'VE0032', 'KH002', TO_TIMESTAMP('2023-02-03 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-05 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV033', 'VE0033', 'KH002', TO_TIMESTAMP('2023-02-05 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-07 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV034', 'VE0034', 'KH002', TO_TIMESTAMP('2023-02-07 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-09 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV035', 'VE0035', 'KH002', TO_TIMESTAMP('2023-02-02 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-02 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV036', 'VE0036', 'KH002', TO_TIMESTAMP('2023-02-02 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-02 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV037', 'VE0037', 'KH002', TO_TIMESTAMP('2023-02-04 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-05 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV038', 'VE0038', 'KH002', TO_TIMESTAMP('2023-02-05 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-06 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV039', 'VE0039', 'KH002', TO_TIMESTAMP('2023-02-06 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-07 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV040', 'VE0040', 'KH002', TO_TIMESTAMP('2023-02-07 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-08 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV041', 'VE0041', 'KH002', TO_TIMESTAMP('2023-02-08 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-09 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV042', 'VE0041', 'KH002', TO_TIMESTAMP('2023-02-09 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-02-09 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);




INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV043', 'VE0043', 'KH002', TO_TIMESTAMP('2023-03-01 09:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-03-03 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV044', 'VE0044', 'KH002', TO_TIMESTAMP('2023-03-03 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-03-05 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV045', 'VE0045', 'KH002', TO_TIMESTAMP('2023-03-05 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-03-07 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV046', 'VE0046', 'KH002', TO_TIMESTAMP('2023-03-07 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-03-09 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV047', 'VE0047', 'KH002', TO_TIMESTAMP('2023-03-02 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-03-02 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV048', 'VE0048', 'KH002', TO_TIMESTAMP('2023-03-02 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-03-02 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV049', 'VE0049', 'KH002', TO_TIMESTAMP('2023-03-04 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-03-05 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV050', 'VE0050', 'KH002', TO_TIMESTAMP('2023-03-05 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-03-06 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV051', 'VE0051', 'KH002', TO_TIMESTAMP('2023-03-06 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-03-07 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV052', 'VE0052', 'KH002', TO_TIMESTAMP('2023-03-07 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-03-08 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV053', 'VE0053', 'KH002', TO_TIMESTAMP('2023-03-08 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-03-09 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV054', 'VE0054', 'KH002', TO_TIMESTAMP('2023-03-09 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-03-10 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV055', 'VE0055', 'KH002', TO_TIMESTAMP('2023-03-01 09:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-03-03 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV056', 'VE0056', 'KH002', TO_TIMESTAMP('2023-03-03 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-03-05 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV057', 'VE0057', 'KH002', TO_TIMESTAMP('2023-03-05 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-03-07 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);




INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV058', 'VE0058', 'KH002', TO_TIMESTAMP('2023-04-15 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-04-16 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV059', 'VE0059', 'KH002', TO_TIMESTAMP('2023-04-16 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-04-17 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV060', 'VE0060', 'KH002', TO_TIMESTAMP('2023-04-17 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-04-18 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV061', 'VE0061', 'KH002', TO_TIMESTAMP('2023-04-18 09:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-04-20 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV062', 'VE0062', 'KH002', TO_TIMESTAMP('2023-04-20 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-04-22 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV063', 'VE0063', 'KH002', TO_TIMESTAMP('2023-04-22 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-04-24 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV064', 'VE0064', 'KH002', TO_TIMESTAMP('2023-04-23 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-04-25 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV065', 'VE0065', 'KH002', TO_TIMESTAMP('2023-04-24 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-04-26 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV066', 'VE0066', 'KH002', TO_TIMESTAMP('2023-04-25 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-04-27 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV067', 'VE0067', 'KH002', TO_TIMESTAMP('2023-04-26 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-04-28 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV068', 'VE0068', 'KH002', TO_TIMESTAMP('2023-04-27 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-04-29 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV069', 'VE0069', 'KH002', TO_TIMESTAMP('2023-04-28 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-04-30 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV070', 'VE0070', 'KH002', TO_TIMESTAMP('2023-04-29 09:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-05-01 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV071', 'VE0071', 'KH002', TO_TIMESTAMP('2023-05-01 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-05-03 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV072', 'VE0072', 'KH002', TO_TIMESTAMP('2023-05-02 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-05-04 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);




INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV073', 'VE0073', 'KH002', TO_TIMESTAMP('2023-05-27 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-05-29 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV074', 'VE0074', 'KH002', TO_TIMESTAMP('2023-05-28 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-05-30 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV075', 'VE0075', 'KH002', TO_TIMESTAMP('2023-05-29 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-05-31 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV076', 'VE0076', 'KH002', TO_TIMESTAMP('2023-05-30 09:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-01 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV077', 'VE0077', 'KH002', TO_TIMESTAMP('2023-06-01 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-03 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV078', 'VE0078', 'KH002', TO_TIMESTAMP('2023-06-02 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-04 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV079', 'VE0079', 'KH002', TO_TIMESTAMP('2023-06-03 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-05 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV080', 'VE0080', 'KH002', TO_TIMESTAMP('2023-06-04 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-06 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV081', 'VE0081', 'KH002', TO_TIMESTAMP('2023-06-05 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-07 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV082', 'VE0082', 'KH002', TO_TIMESTAMP('2023-06-06 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-08 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);





INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV083', 'VE0083', 'KH002', TO_TIMESTAMP('2023-06-01 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-03 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV084', 'VE0084', 'KH002', TO_TIMESTAMP('2023-06-02 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-04 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV085', 'VE0085', 'KH002', TO_TIMESTAMP('2023-06-03 09:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-05 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV086', 'VE0086', 'KH002', TO_TIMESTAMP('2023-06-04 11:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-06 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV087', 'VE0087', 'KH002', TO_TIMESTAMP('2023-06-05 10:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-07 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV088', 'VE0088', 'KH002', TO_TIMESTAMP('2023-06-06 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-08 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV089', 'VE0089', 'KH002', TO_TIMESTAMP('2023-06-07 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-09 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV090', 'VE0090', 'KH002', TO_TIMESTAMP('2023-06-08 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-10 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV091', 'VE0091', 'KH002', TO_TIMESTAMP('2023-06-09 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-11 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV092', 'VE0092', 'KH002', TO_TIMESTAMP('2023-06-10 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-12 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV093', 'VE0093', 'KH002', TO_TIMESTAMP('2023-06-11 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-13 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV094', 'VE0094', 'KH002', TO_TIMESTAMP('2023-06-12 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-14 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV095', 'VE0095', 'KH002', TO_TIMESTAMP('2023-06-13 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-15 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV096', 'VE0096', 'KH002', TO_TIMESTAMP('2023-06-14 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-16 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV097', 'VE0097', 'KH002', TO_TIMESTAMP('2023-06-15 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-06-17 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);





INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV098', 'VE0098', 'KH002', TO_TIMESTAMP('2023-07-05 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-07-07 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV099', 'VE0099', 'KH002', TO_TIMESTAMP('2023-07-06 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-07-08 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV100', 'VE0100', 'KH002', TO_TIMESTAMP('2023-07-07 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-07-09 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV101', 'VE0101', 'KH002', TO_TIMESTAMP('2023-07-08 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-07-10 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV102', 'VE0102', 'KH002', TO_TIMESTAMP('2023-07-09 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-07-11 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV103', 'VE0103', 'KH002', TO_TIMESTAMP('2023-07-10 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-07-12 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV104', 'VE0104', 'KH002', TO_TIMESTAMP('2023-07-11 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-07-13 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV105', 'VE0105', 'KH002', TO_TIMESTAMP('2023-07-12 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-07-14 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV106', 'VE0106', 'KH002', TO_TIMESTAMP('2023-07-13 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-07-15 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV107', 'VE0107', 'KH002', TO_TIMESTAMP('2023-07-14 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-07-16 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV108', 'VE0108', 'KH002', TO_TIMESTAMP('2023-07-15 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-07-17 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV109', 'VE0109', 'KH002', TO_TIMESTAMP('2023-07-16 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-07-18 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV110', 'VE0110', 'KH002', TO_TIMESTAMP('2023-07-17 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-07-19 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV111', 'VE0111', 'KH002', TO_TIMESTAMP('2023-07-18 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-07-20 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV112', 'VE0112', 'KH002', TO_TIMESTAMP('2023-07-19 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-07-21 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);





INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV113', 'VE0113', 'KH002', TO_TIMESTAMP('2023-08-09 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-08-11 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV114', 'VE0114', 'KH002', TO_TIMESTAMP('2023-08-10 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-08-12 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV115', 'VE0115', 'KH002', TO_TIMESTAMP('2023-08-11 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-08-13 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV116', 'VE0116', 'KH002', TO_TIMESTAMP('2023-08-12 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-08-14 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV117', 'VE0117', 'KH002', TO_TIMESTAMP('2023-08-13 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-08-15 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV118', 'VE0118', 'KH002', TO_TIMESTAMP('2023-08-14 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-08-16 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV119', 'VE0119', 'KH002', TO_TIMESTAMP('2023-08-15 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-08-17 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV120', 'VE0120', 'KH002', TO_TIMESTAMP('2023-08-16 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-08-18 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV121', 'VE0121', 'KH002', TO_TIMESTAMP('2023-08-17 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-08-19 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV122', 'VE0122', 'KH002', TO_TIMESTAMP('2023-08-18 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-08-20 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV123', 'VE0123', 'KH002', TO_TIMESTAMP('2023-08-19 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-08-21 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV124', 'VE0124', 'KH002', TO_TIMESTAMP('2023-08-20 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-08-22 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV125', 'VE0125', 'KH002', TO_TIMESTAMP('2023-08-21 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-08-23 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV126', 'VE0126', 'KH002', TO_TIMESTAMP('2023-08-22 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-08-24 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV127', 'VE0127', 'KH002', TO_TIMESTAMP('2023-08-23 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-08-25 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);



INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV128', 'VE0128', 'KH002', TO_TIMESTAMP('2023-09-01 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-09-02 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV129', 'VE0129', 'KH002', TO_TIMESTAMP('2023-09-02 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-09-03 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV130', 'VE0130', 'KH002', TO_TIMESTAMP('2023-09-03 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-09-04 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV131', 'VE0131', 'KH002', TO_TIMESTAMP('2023-09-04 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-09-05 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV132', 'VE0132', 'KH002', TO_TIMESTAMP('2023-09-05 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-09-06 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV133', 'VE0133', 'KH002', TO_TIMESTAMP('2023-09-06 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-09-07 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV134', 'VE0134', 'KH002', TO_TIMESTAMP('2023-09-07 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-09-08 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV135', 'VE0135', 'KH002', TO_TIMESTAMP('2023-09-08 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-09-09 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV136', 'VE0136', 'KH002', TO_TIMESTAMP('2023-09-09 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-09-10 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV137', 'VE0137', 'KH002', TO_TIMESTAMP('2023-09-10 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-09-11 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV138', 'VE0138', 'KH002', TO_TIMESTAMP('2023-09-11 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-09-12 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV139', 'VE0139', 'KH002', TO_TIMESTAMP('2023-09-12 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-09-13 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV140', 'VE0140', 'KH002', TO_TIMESTAMP('2023-09-13 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-09-14 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV141', 'VE0141', 'KH002', TO_TIMESTAMP('2023-09-14 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-09-15 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV142', 'VE0142', 'KH002', TO_TIMESTAMP('2023-09-15 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-09-16 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);




INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV143', 'VE0143', 'KH002', TO_TIMESTAMP('2023-10-11 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-10-12 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV144', 'VE0144', 'KH002', TO_TIMESTAMP('2023-10-12 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-10-13 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV145', 'VE0145', 'KH002', TO_TIMESTAMP('2023-10-13 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-10-14 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV146', 'VE0146', 'KH002', TO_TIMESTAMP('2023-10-14 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-10-15 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV147', 'VE0147', 'KH002', TO_TIMESTAMP('2023-10-15 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-10-16 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV148', 'VE0148', 'KH002', TO_TIMESTAMP('2023-10-16 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-10-17 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV149', 'VE0149', 'KH002', TO_TIMESTAMP('2023-10-17 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-10-18 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV150', 'VE0150', 'KH002', TO_TIMESTAMP('2023-10-18 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-10-19 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV151', 'VE0151', 'KH002', TO_TIMESTAMP('2023-10-19 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-10-20 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV152', 'VE0152', 'KH002', TO_TIMESTAMP('2023-10-20 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-10-21 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV153', 'VE0153', 'KH002', TO_TIMESTAMP('2023-10-21 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-10-22 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV154', 'VE0154', 'KH002', TO_TIMESTAMP('2023-10-23 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-10-24 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV155', 'VE0155', 'KH002', TO_TIMESTAMP('2023-10-24 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-10-25 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV156', 'VE0156', 'KH002', TO_TIMESTAMP('2023-10-25 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-10-26 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV157', 'VE0157', 'KH002', TO_TIMESTAMP('2023-10-26 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-10-27 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);




INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV158', 'VE0158', 'KH002', TO_TIMESTAMP('2023-11-01 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-11-02 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV159', 'VE0159', 'KH002', TO_TIMESTAMP('2023-11-02 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-11-03 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV160', 'VE0160', 'KH002', TO_TIMESTAMP('2023-11-03 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-11-04 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV161', 'VE0161', 'KH002', TO_TIMESTAMP('2023-11-04 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-11-05 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV162', 'VE0162', 'KH002', TO_TIMESTAMP('2023-11-05 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-11-06 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV163', 'VE0163', 'KH002', TO_TIMESTAMP('2023-11-06 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-11-07 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV164', 'VE0164', 'KH002', TO_TIMESTAMP('2023-11-07 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-11-08 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV165', 'VE0165', 'KH002', TO_TIMESTAMP('2023-11-08 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-11-09 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV166', 'VE0166', 'KH002', TO_TIMESTAMP('2023-11-09 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-11-10 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV167', 'VE0167', 'KH002', TO_TIMESTAMP('2023-11-10 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-11-11 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV168', 'VE0168', 'KH002', TO_TIMESTAMP('2023-11-11 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-11-12 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV169', 'VE0169', 'KH002', TO_TIMESTAMP('2023-11-12 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-11-13 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV170', 'VE0170', 'KH002', TO_TIMESTAMP('2023-11-13 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-11-14 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV171', 'VE0171', 'KH002', TO_TIMESTAMP('2023-11-14 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-11-15 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);
INSERT INTO CT_DATVE (MaCT_DATVE, MaVe, MaKhachHang, NgayMuaVe, NgayThanhToan, TrangThai)
VALUES ('CTDV172', 'VE0172', 'KH002', TO_TIMESTAMP('2023-11-15 09:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-11-16 15:29:59', 'YYYY-MM-DD HH24:MI:SS'), 1);


--- TRUNGHIEU thêm
-- Thêm sân bay quốc tế
INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD050', 'Seletar', 'XSP', 'Singapore', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD051', 'Changi', 'SIN', 'Singapore', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD052', 'Phuket', 'PKT', 'Thai Lan', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD053', 'Chiang Mai', 'CMT', 'Thai Lan', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD054', 'Incheon', 'ICN', 'Han Quoc', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD055', 'Gimhae', 'PUS', 'Han Quoc', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD056', 'Chicago', 'ORD', 'Hoa Ky', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD057', 'Dallas', 'DFW', 'Hoa Ky', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD058', 'Abingdon', 'ABB', 'Anh', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD059', 'Bedford', 'BFC', 'Anh', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD060', 'Berlin', 'TXL', 'Duc', 1);

INSERT INTO SANBAY (MaSanBay, TenSanBay, TenVietTat, DiaChi, TrangThai)
VALUES ('SBD061', 'Frankfurt am Main', 'FRA', 'Duc', 1);

-- Thêm Đường bay
INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay,trangthai)
VALUES ('DB050', 'SBD001', 'SBD057', 'SGN-DFW', 9683,1); -- Ho Chi Minh City to Hoa Ky

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay,trangthai)
VALUES ('DB051', 'SBD003', 'SBD055', 'VCL-PUS', 2755,1); -- Quang Nam to Han Quoc

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay,trangthai)
VALUES ('DB052', 'SBD012', 'SBD051', 'CXR-SIN', 3475,1); -- Khanh Hoa to Singapore

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay,trangthai)
VALUES ('DB053', 'SBD005', 'SBD058', 'DAD-ABB', 6542,1); -- Da Nang to Anh

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay,trangthai)
VALUES ('DB054', 'SBD014', 'SBD052', 'VCA-PKT', 1074,1); -- Can Tho to Thai Lan

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay,trangthai)
VALUES ('DB055', 'SBD011', 'SBD061', 'HUI-FRA', 4556,1); -- Hue to Duc

INSERT INTO DUONGBAY (MaDuongBay, MaSanBayDi, MaSanBayDen, TenDuongBay, DoDaiDuongBay,trangthai)
VALUES ('DB056', 'SBD013', 'SBD050', 'DLI-XSP', 20331,1); -- Lam Dong to Singapore


-- Thêm sân bay trung gian
INSERT INTO SANBAYTG (MaDuongBay, MaSanBay, ThuTu, ThoiGianDung)
VALUES ('DB050', 'SBD059', 1, INTERVAL '0 1:30:00' DAY TO SECOND);

INSERT INTO SANBAYTG (MaDuongBay, MaSanBay, ThuTu, ThoiGianDung)
VALUES ('DB051', 'SBD005', 1, INTERVAL '0 1:00:00' DAY TO SECOND);

INSERT INTO SANBAYTG (MaDuongBay, MaSanBay, ThuTu, ThoiGianDung)
VALUES ('DB052', 'SBD001', 1, INTERVAL '0 00:30:00' DAY TO SECOND);

INSERT INTO SANBAYTG (MaDuongBay, MaSanBay, ThuTu, ThoiGianDung)
VALUES ('DB054', 'SBD001', 1, INTERVAL '0 00:30:00' DAY TO SECOND);

INSERT INTO SANBAYTG (MaDuongBay, MaSanBay, ThuTu, ThoiGianDung)
VALUES ('DB055', 'SBD002', 1, INTERVAL '0 1:00:00' DAY TO SECOND);

INSERT INTO SANBAYTG (MaDuongBay, MaSanBay, ThuTu, ThoiGianDung)
VALUES ('DB055', 'SBD058', 2, INTERVAL '0 2:45:00' DAY TO SECOND);

INSERT INTO SANBAYTG (MaDuongBay, MaSanBay, ThuTu, ThoiGianDung)
VALUES ('DB056', 'SBD001', 1, INTERVAL '0 0:50:00' DAY TO SECOND);

-- Thêm chuyến bay
INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB040', 'DB050', TO_TIMESTAMP('2023-01-10 15:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-01-12 22:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 9500000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB041', 'DB051', TO_TIMESTAMP('2023-05-15 19:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-5-16 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 5000000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB042', 'DB052', TO_TIMESTAMP('2023-07-31 7:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-08-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 4300000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB043', 'DB053', TO_TIMESTAMP('2024-01-10 9:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-01-11 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 8760000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB044', 'DB054', TO_TIMESTAMP('2023-12-12 22:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-12-13 13:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 2360000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB045', 'DB055', TO_TIMESTAMP('2024-04-23 18:45:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2024-04-24 6:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 7760000);

INSERT INTO CHUYENBAY (MaChuyenBay, MaDuongBay, TGXP, TGKT, TrangThai, GiaVe)
VALUES ('CB046', 'DB056', TO_TIMESTAMP('2023-05-17 23:30:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2023-05-18 17:00:00', 'YYYY-MM-DD HH24:MI:SS'), 1, 3530000);

--future

/*
-- Xóa dữ liệu trong bảng CT_DATVE
DELETE FROM CT_DATVE;

-- Xóa dữ liệu trong bảng CT_HANGVE
DELETE FROM CT_HANGVE;

-- Xóa dữ liệu trong bảng KHACHHANG
DELETE FROM KHACHHANG;

-- Xóa dữ liệu trong bảng VE
DELETE FROM VE;

-- Xóa dữ liệu trong bảng HANGVE
DELETE FROM HANGVE;

-- Xóa dữ liệu trong bảng DUONGBAY
DELETE FROM DUONGBAY;

-- Xóa dữ liệu trong bảng SANBAY
DELETE FROM SANBAY;

-- Xóa dữ liệu trong bảng CHUYENBAY
DELETE FROM CHUYENBAY;

-- Xóa dữ liệu trong bảng baocaonam
DELETE FROM baocaonam;

-- Xóa dữ liệu trong bảng BAOCAOTHANG
DELETE FROM BAOCAOTHANG;

-- Xóa dữ liệu trong bảng TAIKHOAN
DELETE FROM TAIKHOAN;

-- Xóa dữ liệu trong bảng QUYEN
DELETE FROM QUYEN;

-- Xóa dữ liệu trong bảng THAMSO
DELETE FROM THAMSO;

-- Xóa dữ liệu trong bảng SANBAYTG
DELETE FRROM SANBAYTG;
*/
