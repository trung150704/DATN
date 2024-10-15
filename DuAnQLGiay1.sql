use master
go
CREATE database DuAnQLGiay
 go
use DuAnQLGiay
go
CREATE TABLE KhuyenMai (
    MaKhuyenMai VARCHAR(255) PRIMARY KEY,
    Code VARCHAR(255),
    MoTa TEXT,
    GiamGia DECIMAL(5, 2),
    NgayBD DATE,
    NgayKT DATE,
    TrangThai bit
)
go
CREATE TABLE DanhMuc (
    MaDanhMuc VARCHAR(255) PRIMARY KEY,
    TenDanhMuc VARCHAR(255),
    MoTaChiTiet TEXT
)
go
CREATE TABLE SanPham (
    MaSanPham VARCHAR(255) PRIMARY KEY,
    TenSanPham VARCHAR(255),
    MoTaSanPham TEXT,
    Gia DECIMAL(10, 2),
    AnhSanPham TEXT,
    NgayTao DATE,
    MaDanhMuc VARCHAR(255),
    FOREIGN KEY (MaDanhMuc) REFERENCES DanhMuc(MaDanhMuc)
)
go
CREATE TABLE NguoiDung (
    MaNguoiDung VARCHAR(255) PRIMARY KEY,
    TaiKhoan VARCHAR(255),
    MatKhau VARCHAR(255),
    HoVaTen VARCHAR(255),
    VaiTro VARCHAR(50),
    Email VARCHAR(255),
    DiaChi TEXT,
    Created_at DATE
)
go
CREATE TABLE HoaDon (
    MaHoaDon VARCHAR(255) PRIMARY KEY,
    NgayDatHang DATE,
    TongTien DECIMAL(10, 2),
    TrangThai bit,
    DiaChiGiaoHang TEXT,
    PhuongThucThanhToan VARCHAR(255),
    MaNguoiDung VARCHAR(255),
    FOREIGN KEY (MaNguoiDung) REFERENCES NguoiDung(MaNguoiDung)
)
go
CREATE TABLE SanPhamKhuyenMai (
    MaSanPhamKM VARCHAR(255) PRIMARY KEY,
    NgayTao DATE,
    MaKhuyenMai VARCHAR(255),
    MaSanPham VARCHAR(255),
    FOREIGN KEY (MaKhuyenMai) REFERENCES KhuyenMai(MaKhuyenMai),
    FOREIGN KEY (MaSanPham) REFERENCES SanPham(MaSanPham)
)
go
CREATE TABLE ChiTietHoaDon (
    MaChiTietHD VARCHAR(255) PRIMARY KEY,
    SoLuong INT,
    Gia DECIMAL(10, 2),
    MaHoaDon VARCHAR(255),
    MaSanPham VARCHAR(255),
    FOREIGN KEY (MaHoaDon) REFERENCES HoaDon(MaHoaDon),
    FOREIGN KEY (MaSanPham) REFERENCES SanPham(MaSanPham)
)
go
CREATE TABLE SanPhamYeuThich (
    MaSanPhamYeuThich VARCHAR(255) PRIMARY KEY,
    MaNguoiDung VARCHAR(255),
    MaSanPham VARCHAR(255),
    FOREIGN KEY (MaNguoiDung) REFERENCES NguoiDung(MaNguoiDung),
    FOREIGN KEY (MaSanPham) REFERENCES SanPham(MaSanPham)
)
go
CREATE TABLE ThanhToan (
    MaThanhToan VARCHAR(255) PRIMARY KEY,
    NgayThanhToan DATE,
    SoLuong DECIMAL(10, 2),
    PhuongThucTT VARCHAR(255),
    MaHoaDon VARCHAR(255),
    FOREIGN KEY (MaHoaDon) REFERENCES HoaDon(MaHoaDon)
)
go
CREATE TABLE Size (
    MaSize VARCHAR(10) PRIMARY KEY,
    KichCo INT
)
go
CREATE TABLE SanPham_Size (
    MaSanPham VARCHAR(255),
    MaSize VARCHAR(10),
    SoLuong INT,
    PRIMARY KEY (MaSanPham, MaSize),
    FOREIGN KEY (MaSanPham) REFERENCES SanPham(MaSanPham),
    FOREIGN KEY (MaSize) REFERENCES Size(MaSize)
)
go
INSERT INTO DanhMuc (MaDanhMuc, TenDanhMuc, MoTaChiTiet) VALUES
('DM01', N'Giày Nam', N'Giày của Nam'),
('DM02', N'Giày Nữ', N'Giày của Nữ')
go
INSERT INTO SanPham (MaSanPham, TenSanPham, MoTaSanPham, Gia, AnhSanPham, NgayTao, MaDanhMuc) VALUES
('SP01', 'Basas Workaday - High Top', 'Basas', 540.00, 'Basas Workaday - High Top.jpg', '2024-10-10', 'DM01'),
('SP02', 'Basas Workaday - Low Top', 'Basas', 550.00, 'Basas Workaday - Low Top.jpg', '2024-10-10', 'DM02'),
('SP03', 'Basas RAW - High Top', 'Basas', 450.00, 'Basas RAW - High Top.jpg', '2024-10-10', 'DM01'),
('SP04', 'Basas Evergreen - Low Top', 'Basas', 570.00, 'Basas Evergreen - Low Top.jpg', '2024-10-10', 'DM02'),
('SP05', 'Basas Simple Life NE - Mule', 'Basas', 550.00, 'Basas Simple Life NE - Mule.jpeg', '2024-10-10', 'DM01'),
('SP06', 'Basas Mono Black NE - Low Top', 'Basas', 650.00, 'Basas Mono Black NE - Low Top.jpg', '2024-10-10', 'DM01'),
('SP07', 'Basas Bumper Gum EXT NE - Low Top', 'Basas', 350.00, 'Basas Bumper Gum EXT NE - Low Top.jpg', '2024-10-10', 'DM01'),
('SP08', 'Basas Bumper Gum NE - Mule', 'Basas', 350.00, 'Basas Bumper Gum NE - Mule.jpeg', '2024-10-10', 'DM02')

go
INSERT INTO Size (MaSize, KichCo) VALUES
('SZ01', 38),
('SZ02', 39),
('SZ03', 40),
('SZ04', 41),
('SZ05', 42)
go
INSERT INTO SanPham_Size (MaSanPham, MaSize, SoLuong) VALUES
('SP01', 'SZ01', 100),  
('SP01', 'SZ02', 100),  
('SP01', 'SZ03', 100),  
('SP01', 'SZ04', 100), 
('SP01', 'SZ05', 100),
('SP02', 'SZ01', 100),  
('SP02', 'SZ02', 100),  
('SP02', 'SZ03', 100),  
('SP02', 'SZ04', 100), 
('SP02', 'SZ05', 100), 
('SP03', 'SZ01', 100),  
('SP03', 'SZ02', 100),  
('SP03', 'SZ03', 100),  
('SP03', 'SZ04', 100), 
('SP03', 'SZ05', 100), 
('SP04', 'SZ01', 100),  
('SP04', 'SZ02', 100),  
('SP04', 'SZ03', 100),  
('SP04', 'SZ04', 100), 
('SP04', 'SZ05', 100), 
('SP05', 'SZ01', 100),  
('SP05', 'SZ02', 100),  
('SP05', 'SZ03', 100),  
('SP05', 'SZ04', 100), 
('SP05', 'SZ05', 100), 
('SP06', 'SZ01', 100),  
('SP06', 'SZ02', 100),  
('SP06', 'SZ03', 100),  
('SP06', 'SZ04', 100), 
('SP06', 'SZ05', 100), 
('SP07', 'SZ01', 100),  
('SP07', 'SZ02', 100),  
('SP07', 'SZ03', 100),  
('SP07', 'SZ04', 100), 
('SP07', 'SZ05', 100), 
('SP08', 'SZ01', 100),  
('SP08', 'SZ02', 100),  
('SP08', 'SZ03', 100),  
('SP08', 'SZ04', 100), 
('SP08', 'SZ05', 100)
go
INSERT INTO SanPham (MaSanPham, TenSanPham, MoTaSanPham, Gia, AnhSanPham, NgayTao, MaDanhMuc) VALUES
('SP09', 'Track 6 2.Blues - Low Top', 'Track 6', 540.00, 'Track 6 2.Blues - Low Top.jpeg', '2024-10-10', 'DM01'),
('SP10', 'Track 6 Class E - Low Top', 'Track 6', 550.00, 'Track 6 Class E - Low Top.jpg', '2024-10-10', 'DM02'),
('SP11', 'Track 6 Jazico - Low Top', 'Track 6', 450.00, 'Track 6 Jazico - Low Top.jpeg', '2024-10-10', 'DM01'),
('SP12', 'Track 6 OG - Low Top', 'Track 6', 570.00, 'Track 6 OG - Low Top.jpg', '2024-10-10', 'DM02'),
('SP13', 'Track 6 Suede Moonphase - Low Top', 'Track 6', 550.00, 'Track 6 Suede Moonphase - Low Top.jpg', '2024-10-10', 'DM01'),
('SP14', 'Track 6 Triple Black - Low Top', 'Track 6', 650.00, 'Track 6 Triple Black - Low Top.jpg', '2024-10-10', 'DM01'),
('SP15', 'Track 6 Unnamed No.1 In C Minor - Low Top', 'Track 6', 550.00, 'Track 6 Unnamed No.1 In C Minor - Low Top.jpg', '2024-10-10', 'DM01'),
('SP16', 'Track 6 Utility Gum Sole - Low Top', 'Track 6', 750.00, 'Track 6 Utility Gum Sole - Low Top.jpg', '2024-10-10', 'DM02'),

('SP17', 'Pattas Tomo - Low Top', 'Pattas', 540.00, 'Pattas Tomo - Low Top.jpeg', '2024-10-10', 'DM01'),
('SP18', 'Pattas Tomo - Mule', 'Pattas', 550.00, 'Pattas Tomo - Mule.jpeg', '2024-10-10', 'DM02'),
('SP19', 'Pattas Tomo - High Top', 'Pattas', 450.00, 'Pattas Tomo - High Top.jpeg', '2024-10-10', 'DM01'),
('SP20', 'Pattas Polka Dots - Low Top', 'Pattas', 570.00, 'Pattas Polka Dots - Low Top.jpg', '2024-10-10', 'DM02'),
('SP21', 'Pattas Polka Dots - High Top', 'Pattas', 650.00, 'Pattas Polka Dots - High Top.jpg', '2024-10-10', 'DM01'),
('SP22', 'Pattas Living Journey - High Top', 'Pattas', 850.00, 'Pattas Living Journey - High Top.jpg', '2024-10-10', 'DM01'),
('SP23', 'Ananas X Doraemon 50 Years Pattas', 'Pattas', 1250.00, 'Ananas X Doraemon 50 Years Pattas.jpg', '2024-10-10', 'DM01'),

('SP24', 'Urbas SC - Low Top', 'Urbas', 550.00, 'Urbas SC - Low Top.jpg', '2024-10-10', 'DM01'),
('SP25', 'Urbas SC - Mule', 'Urbas', 670.00, 'Urbas SC - Mule.jpg', '2024-10-10', 'DM02'),
('SP26', 'Urbas SC - High Top', 'Urbas', 480.00, 'Urbas SC - High Top.jpg', '2024-10-10', 'DM01'),
('SP27', 'Urbas Retrospective - Mid Top', 'Urbas', 390.00, 'Urbas Retrospective - Mid Top.jpg', '2024-10-10', 'DM02'),
('SP28', 'Urbas Love+ 23 - Slip On', 'Urbas', 400.00, 'Urbas Love+ 23 - Slip On.jpg', '2024-10-10', 'DM01'),
('SP29', 'Urbas Irrelevant NE - Low Top', 'Urbas', 550.00, 'Urbas Irrelevant NE - Low Top.jpg', '2024-10-10', 'DM02'),
('SP30', 'Urbas Corluray Mix - Low Top', 'Urbas', 560.00, 'Urbas Corluray Mix - Low Top.jpeg', '2024-10-10', 'DM01'),
('SP31', 'Urbas Corluray Mix - High Top', 'Urbas', 520.00, 'Urbas Corluray Mix - High Top.jpeg', '2024-10-10', 'DM02'),

('SP32', 'Vintas Aunter - Low Top', 'Vintas', 630.00, 'Vintas Aunter - Low Top.jpg', '2024-10-10', 'DM01'),
('SP33', 'Vintas Jazico - High Top', 'Vintas', 650.00, 'Vintas Jazico - High Top.jpeg', '2024-10-10', 'DM02'),
('SP34', 'Vintas Mister NE - High Top', 'Vintas', 770.00, 'Vintas Mister NE - High Top.jpg', '2024-10-10', 'DM01'),
('SP35', 'Vintas Monoguso - Low Top', 'Vintas', 520.00, 'Vintas Monoguso - Low Top.jpeg', '2024-10-10', 'DM02'),
('SP36', 'Vintas Nauda EXT - High Top', 'Vintas', 850.00, 'Vintas Nauda EXT - High Top.jpeg', '2024-10-10', 'DM01'),
('SP37', 'Vintas Public 2000s - Low Top', 'Vintas', 750.00, 'Vintas Public 2000s - Low Top.jpg', '2024-10-10', 'DM02'),
('SP38', 'Vintas Soda Pop - High Top', 'Vintas', 580.00, 'Vintas Soda Pop - High Top.jpg', '2024-10-10', 'DM01'),
('SP39', 'Vintas Vivu - Low Top', 'Vintas', 620.00, 'Vintas Vivu - Low Top.jpeg', '2024-10-10', 'DM02')
 go
INSERT INTO SanPham_Size (MaSanPham, MaSize, SoLuong) VALUES
('SP09', 'SZ01', 100),  
('SP09', 'SZ02', 100),  
('SP09', 'SZ03', 100),  
('SP09', 'SZ04', 100), 
('SP09', 'SZ05', 100),
('SP10', 'SZ01', 100),  
('SP10', 'SZ02', 100),  
('SP10', 'SZ03', 100),  
('SP10', 'SZ04', 100), 
('SP10', 'SZ05', 100), 
('SP11', 'SZ01', 100),  
('SP11', 'SZ02', 100),  
('SP11', 'SZ03', 100),  
('SP11', 'SZ04', 100), 
('SP11', 'SZ05', 100), 
('SP12', 'SZ01', 100),  
('SP12', 'SZ02', 100),  
('SP12', 'SZ03', 100),  
('SP12', 'SZ04', 100), 
('SP12', 'SZ05', 100), 
('SP13', 'SZ01', 100),  
('SP13', 'SZ02', 100),  
('SP13', 'SZ03', 100),  
('SP13', 'SZ04', 100), 
('SP13', 'SZ05', 100), 
('SP14', 'SZ01', 100),  
('SP14', 'SZ02', 100),  
('SP14', 'SZ03', 100),  
('SP14', 'SZ04', 100), 
('SP14', 'SZ05', 100), 
('SP15', 'SZ01', 100),  
('SP15', 'SZ02', 100),  
('SP15', 'SZ03', 100),  
('SP15', 'SZ04', 100), 
('SP15', 'SZ05', 100), 
('SP16', 'SZ01', 100),  
('SP16', 'SZ02', 100),  
('SP16', 'SZ03', 100),  
('SP16', 'SZ04', 100), 
('SP16', 'SZ05', 100),
('SP17', 'SZ01', 100),  
('SP17', 'SZ02', 100),  
('SP17', 'SZ03', 100),  
('SP17', 'SZ04', 100), 
('SP17', 'SZ05', 100),
('SP18', 'SZ01', 100),  
('SP18', 'SZ02', 100),  
('SP18', 'SZ03', 100),  
('SP18', 'SZ04', 100), 
('SP18', 'SZ05', 100), 
('SP19', 'SZ01', 100),  
('SP19', 'SZ02', 100),  
('SP19', 'SZ03', 100),  
('SP19', 'SZ04', 100), 
('SP19', 'SZ05', 100), 
('SP20', 'SZ01', 100),  
('SP20', 'SZ02', 100),  
('SP20', 'SZ03', 100),  
('SP20', 'SZ04', 100), 
('SP20', 'SZ05', 100), 
('SP21', 'SZ01', 100),  
('SP21', 'SZ02', 100),  
('SP21', 'SZ03', 100),  
('SP21', 'SZ04', 100), 
('SP21', 'SZ05', 100), 
('SP22', 'SZ01', 100),  
('SP22', 'SZ02', 100),  
('SP22', 'SZ03', 100),  
('SP22', 'SZ04', 100), 
('SP22', 'SZ05', 100), 
('SP23', 'SZ01', 100),  
('SP23', 'SZ02', 100),  
('SP23', 'SZ03', 100),  
('SP23', 'SZ04', 100), 
('SP23', 'SZ05', 100), 
('SP24', 'SZ01', 100),  
('SP24', 'SZ02', 100),  
('SP24', 'SZ03', 100),  
('SP24', 'SZ04', 100), 
('SP24', 'SZ05', 100),
('SP25', 'SZ01', 100),  
('SP25', 'SZ02', 100),  
('SP25', 'SZ03', 100),  
('SP25', 'SZ04', 100), 
('SP25', 'SZ05', 100),
('SP26', 'SZ01', 100),  
('SP26', 'SZ02', 100),  
('SP26', 'SZ03', 100),  
('SP26', 'SZ04', 100), 
('SP26', 'SZ05', 100), 
('SP27', 'SZ01', 100),  
('SP27', 'SZ02', 100),  
('SP27', 'SZ03', 100),  
('SP27', 'SZ04', 100), 
('SP27', 'SZ05', 100), 
('SP28', 'SZ01', 100),  
('SP28', 'SZ02', 100),  
('SP28', 'SZ03', 100),  
('SP28', 'SZ04', 100), 
('SP28', 'SZ05', 100), 
('SP29', 'SZ01', 100),  
('SP29', 'SZ02', 100),  
('SP29', 'SZ03', 100),  
('SP29', 'SZ04', 100), 
('SP29', 'SZ05', 100), 
('SP30', 'SZ01', 100),  
('SP30', 'SZ02', 100),  
('SP30', 'SZ03', 100),  
('SP30', 'SZ04', 100), 
('SP30', 'SZ05', 100), 
('SP31', 'SZ01', 100),  
('SP31', 'SZ02', 100),  
('SP31', 'SZ03', 100),  
('SP31', 'SZ04', 100), 
('SP31', 'SZ05', 100), 
('SP32', 'SZ01', 100),  
('SP32', 'SZ02', 100),  
('SP32', 'SZ03', 100),  
('SP32', 'SZ04', 100), 
('SP32', 'SZ05', 100),
('SP33', 'SZ01', 100),  
('SP33', 'SZ02', 100),  
('SP33', 'SZ03', 100),  
('SP33', 'SZ04', 100), 
('SP33', 'SZ05', 100), 
('SP34', 'SZ01', 100),  
('SP34', 'SZ02', 100),  
('SP34', 'SZ03', 100),  
('SP34', 'SZ04', 100), 
('SP34', 'SZ05', 100), 
('SP35', 'SZ01', 100),  
('SP35', 'SZ02', 100),  
('SP35', 'SZ03', 100),  
('SP35', 'SZ04', 100), 
('SP35', 'SZ05', 100),
('SP36', 'SZ01', 100),  
('SP36', 'SZ02', 100),  
('SP36', 'SZ03', 100),  
('SP36', 'SZ04', 100), 
('SP36', 'SZ05', 100), 
('SP37', 'SZ01', 100),  
('SP37', 'SZ02', 100),  
('SP37', 'SZ03', 100),  
('SP37', 'SZ04', 100), 
('SP37', 'SZ05', 100), 
('SP38', 'SZ01', 100),  
('SP38', 'SZ02', 100),  
('SP38', 'SZ03', 100),  
('SP38', 'SZ04', 100), 
('SP38', 'SZ05', 100),
('SP39', 'SZ01', 100),  
('SP39', 'SZ02', 100),  
('SP39', 'SZ03', 100),  
('SP39', 'SZ04', 100), 
('SP39', 'SZ05', 100)
go
