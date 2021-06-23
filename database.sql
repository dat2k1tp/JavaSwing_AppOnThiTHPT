--create database DU_AN_1_THPT
--GO

--USE Du_An_1_THPT
--GO

drop table GiaoVien
drop table MONHOC
drop table CAUHOI
drop table DETHICHITIET
drop table DETHI
drop table HocSinh
drop table KETQUA
drop table DANHGIA
drop table monhocchitiet

create table GiaoVien
(	
	Ma_GV nvarchar(15) not null primary key,
	HoTen  nvarchar(75) not null,
	NgaySinh date not null,
	GioiTinh bit not null,
	SDT  nvarchar(13) not null,
	Email   nvarchar(55) not null,
	DiaChi  nvarchar(255) not null,
	Hinh  nvarchar(255) not null,
	MatKhau  nvarchar(25) not null,
	--VaiTro  nvarchar(15) not null,
	NgayTao  date not null,
	Delete_At  nvarchar(50) ,
	Delete_User  nvarchar(50) ,
)
go

create table MONHOC
(
	Ma_MonHoc nvarchar(15) not null primary key,
	TenMonHoc nvarchar(55) not null,
	Ma_GV nvarchar(15) not null,
	constraint FK_THELOAI_GV foreign key(Ma_GV)  references GIAOVIEN(Ma_GV)
)
go

create table MONHOCCHITIET
(
	Ma_MonHocChiTiet nvarchar(15) not null primary key,
	TenMonHoc nvarchar(55) not null,
	Ma_MonHoc nvarchar(15) not null,
	NgayTao date not null,
	Hinh nvarchar(100) not null,
	-- TheLoai nvarchar(255) not null,
	GhiChu nvarchar(555) not null,
	Delete_At nvarchar(50) ,
	Delete_User nvarchar(50) ,
	constraint PK_MONHOCCT_MonHoc foreign key(Ma_MonHoc) references MonHoc(Ma_MonHoc)
)
go

create table CAUHOI
(
	Ma_CauHoi nvarchar(15) not null primary key,
	Ma_MonHocChiTiet nvarchar(15) not null,
	-- Ma_GV nvarchar(15) not null,
	NoiDung_CauHoi  nvarchar(555) not null,
	NgayTao_CauHoi date not null,
	Hinh nvarchar(50) ,
	DapAn_A nvarchar(255) not null,
	DapAn_B nvarchar(255) not null,
	DapAn_C nvarchar(255) not null,
	DapAn_D nvarchar(255) not null,
	DapAn_Dung nvarchar(255) not null,
	DoKho nvarchar(12) not null,
	Delete_At nvarchar(50) ,
	Delete_User nvarchar(50) ,
	constraint FK_CAUHOI_MONHOCCT foreign key(Ma_MonHocChiTiet)  references MONHOCCHITIET(Ma_MonHocChiTiet)
)
GO

create table DETHI
(
	Ma_DeThi nvarchar(15) not null primary key,
	Ma_MonHoc nvarchar(15) not null,
	Ma_GV nvarchar(15) not null,
	TenDeThi nvarchar(255) not null,
	ThoiGianLamBai int not null,
	TongSoCau int not null,
	NgayTao date not null,
	DoKho nvarchar(15) not null,
	Delete_At nvarchar(50) ,
	Delete_User nvarchar(50),
	constraint FK_DETHI_GV foreign key(Ma_GV)  references GIAOVIEN(Ma_GV),
	constraint PK_DETHI_THELOAI foreign key(Ma_MonHoc) references MONHOC(Ma_MonHoc)
)
GO

create table DETHICHITIET
(
	Ma_DeThiChiTiet nvarchar(15) not null primary key,
	Ma_DeThi nvarchar(15) not null ,
	Ma_CauHoi nvarchar(15) not null,
	GhiChu nvarchar(255) not null,
	constraint FK_DETHI_CAUHOI_DETHI foreign key(Ma_DeThi) references DETHI(Ma_DeThi),
	constraint FK_DETHI_CAUHOI_CAUHOI foreign key(Ma_CauHoi) references CAUHOI(Ma_CauHoi)
)
GO

create table HocSinh
(	
	Ma_HocSinh nvarchar(15) not null primary key,
	HoTen  nvarchar(55) not null,
	NgaySinh date not null,
	GioiTinh bit not null,
	SDT  nvarchar(13) not null,
	Email   nvarchar(55) not null,
	DiaChi  nvarchar(255) not null,
	Hinh  nvarchar(255) not null,
	MatKhau  nvarchar(25) not null,
	NgayTao  Date not null,
	Delete_At  nvarchar(50) ,
	Delete_User  nvarchar(50) ,
)
go

create table KETQUA
(
	Ma_KetQua nvarchar(15) not null primary key,
	Ma_DeThi nvarchar(15) not null,
	Ma_HocSinh nvarchar(15) not null,
	SoCauDung int not null,
	Diem float not null,
	GhiChu nvarchar(255) not null,
	constraint FK_KETQUA_DETHI foreign key(Ma_DeThi) references DETHI(Ma_DeThi),
	constraint FK_KETQUA_HS foreign key(Ma_HocSinh) references HOCSINH(Ma_HocSinh)
)
GO

create table DANHGIA
(
	Ma_DanhGia nvarchar(15) not null primary key,
	Ma_HocSinh nvarchar(15) not null,
	Cau1 nvarchar(50) not null,
	Cau2 nvarchar(50) not null,
	Cau3 nvarchar(50) not null,
	NoiDungDanhGia nvarchar(500) not null,
	NgayDG date not null,
	constraint FK_DANHGIA_HS foreign key(Ma_HocSinh) references HOCSINH(Ma_HocSinh)
)
GO


select * from DanhGia
select * from GiaoVien where Delete_At is null
--select * from GiaoVien where HoTen like N'vũ văn tuấn'
select * from Monhocchitiet where Ma_MonHoc like 'HoaHoc'

select * from cauhoi  where Ma_MonHocChiTiet = 'TOAN_TT' 

update GiaoVien set Hinh ='3.jpg' where Ma_GV = 'GV_00101'


delete from monhoc where Ma_MonHoc like 'LichSu'
select * from hocsinh

update cauhoi set Ma_MonHocChiTiet = ?, NoiDung_CauHoi = ?,DapAn_A = ?,DapAn_B = ?,DapAn_C = ?,DapAn_D = ?, DapAn_Dung = ?, DoKho =?,Hinh =? where Ma_CauHoi =?

delete from hocsinh where Ma_HocSinh = 'NVTuan'
update MonHocChiTiet set tenmonhoc = N'Đạo hàm - Nguyên Hàm' where Ma_MonHocChiTiet = 'TOAN_DH'
insert into MonHoc(Ma_MonHoc,TenMonHoc,Ma_GV)
values ('Toan',N'Toán','GV_00101'),
		('Van',N'Văn học','GV_00101'),
		('TiengAnh',N'Tiếng anh','GV_00101'),
		('VatLy',N'Vật lý','GV_00101'),
		('HoaHoc',N'Hóa học','GV_00101'),
		('DiaLy',N'Địa lý','GV_00101'),
		('GDCD',N'Giáo dục công dân','GV_00101'),
		('Sinh',N'Sinh','GV_00101'),
		('LichSu',N'Lịch Sử','GV_00101')

		update MonHoc set TenMonHoc  = N'Văn' where Ma_MonHoc = 'van'
		select * from monhoc

insert into monhocchitiet(Ma_MonHocChiTiet,tenMonHoc,Ma_MonHoc,NgayTao,Hinh,GhiChu)
values ('TOAN_TT',N'Thực tế','Toan','05-04-2020','C:\Users\ABC\Documents\Images\3.jpg',N'Môn toán thực tế'),
		('TOAN_HS',N'Hàm số','Toan','05-04-2020','C:\Users\ABC\Documents\Images\3.jpg',N'Môn toán hàm số'),
		('TOAN_SP',N'Số phức','Toan','05-04-2020','C:\Users\ABC\Documents\Images\3.jpg',N'Môn toán số phức'),
		('TOAN_DD',N'Đa diện','Toan','05-04-2020','C:\Users\ABC\Documents\Images\3.jpg',N'Môn toán đa diện'),
		('TOAN_TDKG',N'Tọa độ không gian','Toan','05-04-2020','C:\Users\ABC\Documents\Images\3.jpg',N'Môn toán tọa độ không gian'),

		('TOAN_DH',N'Đạo hàm','Toan','05-04-2020','C:\Users\ABC\Documents\Images\3.jpg',N'Môn toán đạo hàm'),
		('TOAN_TP',N'Tích phân','Toan','05-04-2020','C:\Users\ABC\Documents\Images\4.jpg',N'Môn toán tích phân'),
		('LY_DDC',N'Dao động cơ','VatLy','05-04-2020','C:\Users\ABC\Documents\Images\3.jpg',N'Môn vật lý dao động cơ'),
		('LY_SC',N'Sóng cơ','VatLy','05-04-2020','C:\Users\ABC\Documents\Images\7.jpg',N'Môn vật lý dao động cơ'),
		('SINH_DTH',N'Di truyền học','Sinh','05-04-2020','C:\Users\ABC\Documents\Images\6.jpg',N'Môn sinh học di truyền học'),
		('SINH_STH',N'Sinh thái học','Sinh','05-04-2020','C:\Users\ABC\Documents\Images\1.jpg',N'Sinh thái học')
		
delete from Monhocchitiet where Ma_MonHocChiTiet ='TOAN_TP'
		
		


--insert into THELOAI(Ma_TheLoai,TenTheLoai)
--values 
	-- Toán 
	--	('TOAN_DH',N'Đạo hàm'),
	--	('TOAN_HS',N'Hàm số'),
		--('TOAN_TP',N'Tích phân'),
	--	('TOAN_SP',N'Số phúc'),
	--	('TOAN_DD',N'Đa diện'),
	--	('TOAN_TDKD',N'Tạo độ không gian'),
	-- Lý
	--	('LY_DDC',N'Dao động cơ'),
	--	('LY_SC',N'Sóng cơ'),
	--	('LY_SA',N'Sóng âm'),
	--	('LY_DXC',N'Dòng điện xoay chiều'),
	--	('LY_SDT',N'Sóng điện từ'),
	--	('LY_SAS',N'Sóng ánh sáng'),
	--	('LY_LTAS',N'Lượng tử ánh sáng'),
	--	('LY_HNNT',N'Hạt nhân nguyên tử'),
	-- Hóa học
	--	('HH_ESTE_LIPIT',N'Este - Lipit'),
	--	('HH_CACBOHIDRAT',N'Cacbohidarat'),
	--	('HH_POLIME',N'Polime và vật liệu Polime'),
	--	('HH_AAP',N'Amin - Aminoaxit - Protein'),
	--	('HH_KL',N'Kim loại'),
	--	('HH_PBVC',N'Phân biệt các chất vô cơ'),
	-- Sinh
	--	('SI_DTH',N'Di truyền học'),
	--	('SI_TH',N'Tiến hóa'),
	--	('SI_STH',N'Sinh thái học'),
	--	('SI_DB',N'Đột biến ')


insert into GiaoVien(Ma_GV,HoTen,NgaySinh,GioiTinh,SDT,Email,DiaChi,Hinh,MatKhau,NgayTao)
values ('GV_00101',N'Vũ Văn Tuấn','05-04-1999',1,'0999999999','vutuan123@gmail.com',N'Ba Đình - Hà Nội','C:\Users\ABC\Documents\Images\3.jpg','999999','05-04-2020'),
		('GV_00112',N'Nguyễn Trung Kiên','10-09-1998',1,'0234576890','kiendaumoi123@gmail.com',N'Đông Anh - Hà Nội','C:\Users\ABC\Documents\Images\4.jpg','123456','05-04-2020'),
		('GV_00123',N'Vũ Văn Thực','06-06-1998',1,'0172906923','thucdaumoi123@gmail.com',N'Mỹ Đình - Hà Nội','C:\Users\ABC\Documents\Images\6.jpg','789123','05-04-2020'),
		('GV_00134',N'Nguyễn Thành Đạt','10-06-1998',1,'0012736893','thanhdaumoi123@gmail.com',N'Ba Vì - Hà Nội','C:\Users\ABC\Documents\Images\7.jpg','123809','05-04-2020')
		--('GV_00135',N'Nguyễn Thành Đạt 09','10-06-1998',1,'0012730993','09thanhdaumoi123@gmail.com',N'Ba Vì - Hà Nội','http://tuanmayman.freeasphost.net/Image/8.jpg','109809','05-04-2020')

delete from giaovien where Ma_GV like 'GV_00324'
update giaovien set HoTen =?, NgaySinh =?,GioiTinh=?,SDT=?,Email=?,DiaChi=?,Hinh=? where Ma_GV =?
update giaovien set Delete_At = null,Delete_User = null where Ma_GV like 'GV_00324'
select * from Monhocchitiet where Ma_MonHoc ='Toan'
select * from cauhoi
select * from danhgia
select * from CauHoi  where Ma_CauHoi = 'CH_00129'

go

insert into CauHoi(Ma_CauHoi,Ma_MonHocChiTiet,NoiDung_CauHoi,NgayTao_CauHoi,DapAn_A,DapAn_B,DapAn_C,DapAn_D,DapAn_Dung,DoKho,Hinh)
values ('CH_00101','TOAN_DD',N'Cho hình chóp tứ giác S ABCD .có đáy là hình vuông cạnh a. Tam giác SAD cân tại S và mặt bên (SAD)vuông góc với mặt phẳng đáy. Biết thể tích khối chóp S ABCD .bằng 3^a .Tính khoảng cách từ điểm B đến mặtphẳng (SCD).','2020-11-18','0,99a','6,08a','3a','0.5a','0,99a',N'Khó','NULL'),		('CH_00102','TOAN_HS',N'Giải phương trình 5^(3x - 1) = 25','2020-11-18','6','3','2','1','1',N'Dễ','NULL'),
		('CH_00103','TOAN_HS',N'Cho hàm số y = f(x) có đạo hàm f(x) = (x - 1)(x^2 - 3x +2), x thuộc R.Số điểm cực trị của hàm số đã cho là ','2020-11-18','1','2','0','3','1',N'Dễ','NULL'),

		('CH_00104','TOAN_HS',N'Trong các mệnh đề sau, mệnh đề nào đúng?','2020-11-18',N'Hàm số y = log(x) trong đoạn [1,2], nghịch biến trên khoảng (0,+oo)',N'log(a+b) = log(a) + log(b), a>0,b>0',N'Hàm số y = e^(10x +2020) đồng biến trên R',N'a^(x+y) = a^x + a^y, a>0, a & b thuộc R',N'Hàm số y = e^(10x +2020) đồng biến trên R',N'Dễ','NULL'),

		('CH_00105','TOAN_HS',N'Cho hàm số y = f(x) có bảng biến thiên như sau: Hàm số đã cho đồng biến trên khoảng nào dưới đây?','2020-11-18','(-oo;-1) (-1;+oo)','(-oo;-1)','(-oo;+oo)','(-2;1)','(-oo;-1)',N'Dễ','C:\Users\ABC\Documents\Images\cau5.jpg'),

		('CH_00106','TOAN_DD',N'Cho hình nón có chiều cao bằng 2a và bán kính đáy bằng a.Diện tích xung quanh của hình nón đã cho bằng','2020-11-18','7,02a^2','4,47a^2','10,02a^2','6,28a^2','7,02a^2',N'Dễ','NULL'),

		('CH_00107','TOAN_HS',N'Cho hàm số y = f(x) có bảng biến thiên như sau: Số nghiệm thực của phương trình 2f(x) + 1 = 0 là','2020-11-18','0','4','2','3','4',N'Dễ','C:\Users\ABC\Documents\Images\cau7.jpg'),

		('CH_00108','TOAN_HS',N'Cho cấp số cộng u với u(1) = -1; công sai d = 2, Tính tổng 100 số hạng đầu tiên của cấp số cộng u(n)','2020-11-18','9800','19600','9900','19800','9800',N'Dễ','NULL'),

		('CH_00109','TOAN_TT',N'Từ tháng 11 năm 2019, mạng Viettel sở hữu 13 đầu số dành cho thuê bao di động bao gồm: 096; 097; 098; 086; 032; 033; 034; 035; 036; 037; 038; 039; 03966. Hỏi mạng Viettel có bao nhiêu số điện thoại di động gồm 10 chữ số khác nhau?','2020-11-18','11.10^7','10!','11.7!','13.7!','11.7!',N'Khó','NULL'),

		('CH_00110','TOAN_HS',N'Một chiếc hộp có mười một thẻ đánh số từ 0 đến 10. Rút ngẫu nhiên hai thẻ rồi nhân hai số ghi trên hai thẻ với nhau. Tính xác suất để kết quả nhận được là một số chẵn.','2020-11-18','0,222','0,7777','0,8181','0,1818','0,8181',N'Khó','NULL'),

		('CH_00111','TOAN_DH',N'Cho a và b là hai số thực dương thỏa mãn a^3.b^2 = 625 . Giá trị của 3log(a) + 2log(b) bằng','2020-11-18','8','12','5','4','4',N'Dễ','NULL'),

		('CH_00112','TOAN_DD',N'Thể tích của khối trụ có chiều cao h và bán kính đáy r là ','2020-11-18','3,14.r^2.h','1,047r^2.h','13,26.r^2.h','0,45.r^2.h','',N'Khó','NULL'),

		('CH_00113','TOAN_HS',N'Cho hàm số y = f(x) có bảng biến thiên như sau: Tổng số tiệm cận đứng và tiệm cận ngang của đồ thị hàm số là ','2020-11-18','3','0','2','1','3',N'Dễ','C:\Users\ABC\Documents\Images\13.jpg'),

		('CH_00114','TOAN_DD',N'Một khối gỗ hình trụ có bán kính đáy r = 1, chiều cao bằng 3. Người ta khoét rỗng hai đầu khối gỗ thành hai nửa hình cầu mà đường tròn đáy của khối gỗ là đường tròn lớn của mỗi nửa hình cầu. Tính thể tích phần còn lại của khối gỗ.','2020-11-18','7,23','1.033','5,23','4.23','5,23',N'Dễ','NULL'),

		('CH_00115','TOAN_TDKG',N'Cho khối hộp ABCD.EFGH có thể tích V. Tính theo V thể tích của khối đa diện ABDHF','2020-11-18','V/3','V/6','2V/3','V/2','V/3',N'Dễ','NULL'),

		('CH_00116','TOAN_TDKG',N'Hình hộp chữ nhật có ba kích thước đôi một khác nhau có bao nhiêu mặt phẳng đối xứng?','2020-11-18',N'9 mặt phẳng',N'4 mặt phẳng',N'6 mặt phẳng',N'3 mặt phẳng',N'3 mặt phẳng',N'Dễ','NULL'),

		('CH_00117','TOAN_TDKG',N'Cho khối lăng trụ đứng ABC.DEF có đáy là tam giác đều cạnh 2a và AD = 1,72a. Thể tích khối lăng trụ đã cho bằng','2020-11-18','1,72.a^3','3.a^3','3a^3/4','6.a^3','3.a^3',N'Dễ','NULL'),
		('CH_00118','TOAN_DD',N'Tính thể tích V của khối chóp tứ giác đều có cạnh đáy bằng a, góc giữa mặt bên và mặt đáy bằng 45 độ ','2020-11-18','a^3/2','a^3.1,414','a^3/5','a^3/3','a^3/3',N'Khó','NULL'),

		('CH_00119','TOAN_DH',N'Giải phương trình log(3)[5-5x] = log(3)[x-1]^2','2020-11-18','x = 1 & x = -4','x = 1',N'vô nghiệm','x = -4','x = -4',N'Dễ','NULL'),

		('CH_00120','TOAN_HS',N'Tổng giá trị lớn nhất và giá trị nhỏ nhất của hàm số y = lnx/x trên đoạn [1/e;e^2]	','2020-11-18','-e + 2/e^2','e - 1/e','(-1/e)/(2/e^2)','(1/e) - e','(1/e) - e',N'Khó','NULL'),

		('CH_00121','TOAN_DD',N'Cho khối tứ diện ABCD có thể tích V và điểm E nằm trên cạnh AB sao cho AE = 3EB . Tính theo V thể tích của khối tứ diện EBCD .','2020-11-18','V/4','V/5','V/3','3V/4','V/3',N'Khó','NULL'),

		('CH_00122','TOAN_HS',N'Hàm số y = 2^(x^2 - 3.cosx) có đạo hàm là','2020-11-18','(2x - 3.sinx).2^(x^2 - 3.cosx).ln2','(2x - 3.sinx).2^(x^2 - 3.cosx)','(2x + 3.sinx).2^(x^2 - 3.cosx).ln2','(2x + 3.sinx).2^(x^2 - 3.cosx)','(2x + 3.sinx).2^(x^2 - 3.cosx).ln2',N'Khó','NULL'),

		('CH_00123','TOAN_TDKG',N'Cho hình chóp S.ABC .có SA vuông góc với mặt phẳng (ABC),SA = 3a, tam giác ABC vuông tại B,BC = a và AC =3,16a.Góc giữa đường thẳng SB và mặt phẳng (ABC) bằng','2020-11-18',N'30 độ',N'60 độ',N'90 độ',N'45 độ',N'45 độ',N'Khó','NULL'),

		('CH_00124','TOAN_HS',N'Điểm cực tiểu của hàm số y = x^3 - 3x^2 - 9x + 2 là','2020-11-18','-25','-1','7','3','3',N'Dễ','NULL'),

		('CH_00125','TOAN_DH',N'Cho dãy số U(n) xác định bởi U(1) = 2; U(n+1) = (U(n) + 1)/3 tìm số hạng U(4)','2020-11-18','2/3','5/9','1','14/27','5/9',N'Khó','NULL'),

		('CH_00126','TOAN_TDKG',N'Cho mặt cầu (S) có tâm I, bán kính R = 1,72 và điểm A thuộc (S). Gọi (P) là mặt phẳng đi qua A và tạo với IA một góc bằng a. Biết rằng a = 1/3 Tính diện tích của hình tròn có biên là đường tròn giao tuyến của mặt phẳng (P) và mặt cầu (S).','2020-11-18','3,14/3','8.3,14/3','3,14/9','4,5.3,14.3','8.3,14/3',N'Khó','NULL'),

		('CH_00127','TOAN_DD',N'Cắt mặt xung quanh của một hình nón theo một đường sinh rồi trải ra trên một mặt phẳng ta được một nửa hình tròn có bán kính 5. Góc ở đỉnh của hình nón trên là ','2020-11-18',N'120 độ',N'30 độ',N'90 độ',N'60 độ',N'60 độ',N'Dễ','NULL'),

		('CH_00128','TOAN_DD',N'Diện tích mặt cầu có đường kính R là','2020-11-18','4.3,14.R^2/3','3,14.R^2','2.3,14.R^2','4.3,14.R^2','3,14.R^2',N'Dễ','NULL'),

		('CH_00129','TOAN_HS',N'Cho phương trình Log(4)X^2 + Log(2)(4-X) = Log(2)(2+m). Có bao nhiêu giá trị nguyên của m để phương trình có nghiệm ?','2020-11-18','4','3','2',N'vô số',N'vô số',N'Dễ','NULL'),

		('CH_00130','TOAN_HS',N'Cho hàm số y = f(x) có bảng biến thiên như sau: Hàm số đạt cực đại tại điểm ','2020-11-18','x = -3','x = -1','x = 1','x = -2','x = -2',N'Khó','C:\Users\ABC\Documents\Images\cau30.jpg'),

		('CH_00131','TOAN_HS',N'Tiệm cận đứng của đồ thị hàm số y = (2-x)/x+3) là ','2020-11-18','y = -3','x = 2','y = -1 ','x = -3','x = -3',N'Dễ','NULL'),

		('CH_00132','TOAN_HS',N'Cho hàm số y = f(x) có bảng xét dấu của đạo hàm như sau: Hàm số y = f(2 - 3x) nghịch biến trên khoảng nào dưới đây?','2020-11-18','(-2;2)','(-6;-4)','(-4;-2)','(5;10)','(5;10)',N'Khó','NULL'),

		('CH_00133','TOAN_DD',N'Cho lăng trụ tam giác đều ABC.DEF có AB = AD = a. Tính khoảng cách d giữa hai đường thẳng BF vàAC','2020-11-18','1,23','0,','076,655','0,23','0,655',N'Khó','NULL'),

		('CH_00134','TOAN_DD',N'Cho hình lập phương ABCD.EFGH có cạnh bằng 5. Tính thể tích khối trụ ngoại tiếp khối lập phương đã cho','2020-11-18','125.3,14','125.3,14/3','125.3,14/2','125.3,14/6','125.3,14/2',N'Khó','NULL'),

		('CH_00135','TOAN_DD',N'Cho hai điểm A, B cố định và AB = a. Điểm M thay đổi trong không gian sao cho diện tíchS(MAB) của tam giác MAB bằng a^2 .rong các mệnh đề sau, mệnh đề nào đúng?','2020-11-18',N'M thuộc mặt cầu cố định bán kính 2a',N'M thuộc mặt mặt trụ cố định bán kính a',N'M thuộc mặt cầu cố định bán kính a.',N'M thuộc mặt trụ cố định bán kính 2a','M thuộc mặt mặt trụ cố định bán kính a',N'Khó','NULL'),

		('CH_00136','TOAN_HS',N'Có bao nhiêu giá trị nguyên thuộc tập xác định của hàm số f(x) = [1 - Log(x-1)]^(1/3)','2020-11-18','9','7','8','10','9',N'Khó','NULL'),

		('CH_00137','TOAN_TT',N'Một cái xô làm bằng inox, hình dạng và các kích thước có tỷ lệ như hình vẽ( xô không có nắp, đáy xô là hình tròn bán kính bằng 9dm). Giả định 1dm ^2 inox có giá a (đồng). Khi đó giá nguyên vật liệu làm 10 cái xô như trên gần nhất với kết quả nào dưới đây? ','2020-11-18',N'1161.3,14a(đồng)',N'11610.3,14a(đồng)',N'13230.3,14a(đồng)',N'1323.3,14a(đồng)',N'11610.3,14a(đồng)',N'Khó','C:\Users\ABC\Documents\Images\cau37.jpg'),

		('CH_00138','TOAN_HS',N'Cho hàm số y = fx) có bảng biến thiên như sau: Khẳng định nào sau đây đúng?','2020-11-18',N'Hàm số không có giá trị lớn nhất và không có giá trị nhỏ nhất.',N'Hàm số có giá trị lớn nhất bằng 1 và có giá trị nhỏ nhất bằng 0.',N'Hàm số không có giá trị lớn nhất và có giá trị nhỏ nhất bằng -2.',N'Hàm số có giá trị lớn nhất bằng 1 và có giá trị nhỏ nhất bằng -2',N'Hàm số không có giá trị lớn nhất và có giá trị nhỏ nhất bằng -2.',N'Khó','C:\Users\ABC\Documents\Images\cau38.jpg'),

		('CH_00139','TOAN_HS',N'Cho hàm số u = X^3 - 3X^2 - 2X^2 -1 có đồ thị(C) Phương trình tiếp tuyến với (C) tại giao điểm của (C) và trục tung là','2020-11-18','y = 2X +1','y = -2X - 1','y = 2X - 1','y = -2X + 1 ','y = -2X - 1',N'Khó','NULL'),

		('CH_00140','TOAN_HS',N'Tìm số hạng chứa X^6 trong khai triển (X-1/X)^2','2020-11-18',N'-C.X^6 trong đoạn(3;12)',N'C.X^6 trong đoạn(3;12)',N'-C trong đoạn(3;12)',N'C trong đoạn(3;12)',N'-C.X^6 trong đoạn(3;12)',N'Khó','NULL'),

		('CH_00141','TOAN_HS',N'Đồ thị của hàm số nào dưới đây có dạng như đường cong trong hình vẽ bên.','2020-11-18','y = X^4 - 2.X^2 + 2','y = -X^4 + 2.X^2 + 2','y = -X^4 - 2.X^2 + 2','y = X^4 + 2.X^2 + 2','y = X^4 - 2.X^2 + 2',N'Khó','C:\Users\ABC\Documents\Images\cau41.jpg'),

		('CH_00142','TOAN_HS',N'Với a khác 0.log(a^2) bằng','2020-11-18','2.log(a)','2.log|a|','1/2 + log|a|','1/2 + log(a)','1/2 + log|a|',N'Khó','NULL'),

		('CH_00143','TOAN_HS',N'Trong các mệnh đề sau, mệnh đề nào đúng?','2020-11-18',N'Đồ thị hàm số y = e^x và đồ thị hàm số y = ln.x đối xứng qua đường thẳng y = -x',N'Đồ thị hàm số y = ln.x và đồ thị hàm số y = ln/x đối xứng qua tung',N'Đồ thị hàm số y = e^x và đồ thị hàm số y = ln.x đối xứng qua đường thẳng y = x',N'Đồ thị hàm số y = e^x và đồ thị hàm số y = 1/(e^x) đối xứng qua hoành',N'Đồ thị hàm số y = e^x và đồ thị hàm số y = ln.x đối xứng qua đường thẳng y = x',N'Khó','NULL'),

		('CH_00144','TOAN_HS',N'Đồ thị được cho trong hình vẽ bên là đồ thị của hàm số nào trong các hàm số sau? ','2020-11-18','y = (3/2)^x','y = Log(1/2)X','y = (1/2)^x','y = Log(3/2)X','y = (3/2)^x',N'Khó','C:\Users\ABC\Documents\Images\cau44.jpg'),

		('CH_00145','TOAN_TT',N'Chị Dung gửi 300 triệu đồng vào ngân hàng Agribank với kỳ hạn cố định 12 tháng .lãi suất 0,68% /tháng.sau khi gửi được tròn 9 tháng chị Dung có việc phải dùng đến 300 triệu. Chị đến ngân hàng rút tiền và được nhân viên ngân hàng tư vấn: rút tiền trước kỳ hạn thì toàn bộ số tiền chị gửi chỉ được hưởng 0,2% /tháng. thế chấp sổ tiết kiệm đó tại ngân hàng để vay 300 triệu với lãi suất 0,8% /tháng.chị Dung sẽ đỡ thiệt một số tiền gần nhất với con số nào dưới đây (biết ngân hàng tính lãi suất theo hình thức lãi kép)?','2020-11-18',N'18,16 triệu đồng',N'12,72 triệu đồng',N'12,71 triệu đồng',N'18,15 triệu đồng',N'12,71 triệu đồng',N'Khó','NULL'),

		('CH_00146','TOAN_DD',N'Xét khối tứ diện ABCD có độ dài cạnh AB thay đổi, CD = 4 và các cạnh còn lại đều bằng 4,69 . Khi thể tích khối tứ diện ABCD đạt giá trị lớn nhất, hãy tính diện tích S của mặt cầu ngoại tiếp tứ diện đó','2020-11-18','118,62','29,66','355,87','18,14','118,62',N'Khó','NULL'),

		('CH_00147','TOAN_HS',N'Cho hàm số y = f(x) liên tục trên R và có đồ thị như hình vẽ. Gọi (C1 ) và (C2 ) lần lượt là đồ thị của hàm số y = f"(x).f(x) - [f(x)]^2 và y = 2020^x. Số giao điểm của (C1 ) và (C2 ) là','2020-11-18','4','0','1','2','0',N'Khó','C:\Users\ABC\Documents\Images\cau47.jpg'),

		('CH_00148','TOAN_TDKG',N'Cho hình lập phương ABCD.A′B′C′D′ cạnh a . Gọi O,O′ lần lượt là tâm của hai đáy ABCD và A′B′C′D′. Xét khối đa diện (H ) có các điểm bên trong là phần không gian chung của hai khối tứ diện ACB′D′ và A′C′BD. Gọi V1 là thể tích của phần không gian bên trong hình lập phương không bị (H) chiếm chỗ, V2 là thể tích khối nón (N) đi qua tất cả các đỉnh của đa diện (H) , đỉnh và tâm đáy của (N) lần lượt là O,O′.Tính V1/V2','2020-11-18','2/(5.3,14)','(2.3,14)/5','5/(2.3,14)','(5.3,14)/2','(5.3,14)/2',N'Khó','NULL'),

		('CH_00149','TOAN_HS',N'Cho hàm số y = f(x), hàm số y = f′(x) liên tục trên R và có đồ thị như hình vẽ. Bất phương trình fx) < m - X^3 -X ( m là tham số thực) nghiệm đúng với mọi x∈ −( 2;0) khi và chỉ khi','2020-11-18','m > f(0)','m >= f(-2) - 10','m > f(-2) - 10','m >= f(0)','m >= f(0)',N'Khó','C:\Users\ABC\Documents\Images\cau49.jpg'),

		
		('CH_00150','TOAN_DD',N'Cho tứ diện ABCD có AB⊥BC,BC⊥CD,CD⊥DA;BC = a,CD = 3,87a, góc giữa AB và CD bằng 30 độ . Thể tích khối tứ diện đó bằng','2020-11-18','2,5.a^3','4,33.a^3','0,833.a^3','1,44.a^3','1,44.a^3',N'Khó','NULL'),
		insert into CauHoi(Ma_CauHoi,Ma_MonHocChiTiet,NoiDung_CauHoi,NgayTao_CauHoi,DapAn_A,DapAn_B,DapAn_C,DapAn_D,DapAn_Dung,DoKho,Hinh)
values

		--('CH_00151','TOAN_DH',N'Họ nguyên hàm của hàm số f(x) = e^x+ x là','2020-11-18','e^x+ x^2+ C.','e^x+1/(2.x^2)+ C.','e^x/(x + 1)+x^2/2 + C','e^x+ 1 + C','e^x+1/(2.x^2)+ C.',N'Dễ','NULL'),
		--('CH_00152','TOAN_DH',N'Cho hàm số f(x) có đạo hàm f "(x) = x(x − 1)(x + 2)^3, ∀x ∈ R. Số điểm cực trị của hàm số đã cho là . ','2020-11-18','3','2','5','1','3',N'Dễ','NULL'),
		--('CH_00153','TOAN_DH',N'Hàm số f(x) = log(2)(x^2− 2x)có đạo hàm','2020-11-18','ln2/(x^2 - 2x)','1/(x^2 - 2x)/ln2','(2x - 2).ln2/(x^2 - 2x)','(2x - 2)/((x2 - 2x).ln2)','(2x - 2)/((x2 - 2x).ln2)',N'Khó','NULL'),
		--('CH_00154','TOAN_DH',N'Họ nguyên hàm của hàm số f(x) = 4x (1 + ln.x) là','2020-11-18','2.x^2.ln.x + 3x^2','2.x^2.ln.x + x^2','2.x^2.ln.x + 3x^2 + C','2.x^2.ln.x + x^2 + C','2.x^2.ln.x + x^2 + C',N'Khó','NULL'),
		--('CH_00155','TOAN_DH',N'Họ tất cả các nguyên hàm của hàm số f(x) = 2x + 5 là','2020-11-18','x^2+ 5x + C','2.x^2+ 5x + C','2.x^2 + C','x^2 + C','x^2+ 5x + C',N'Dễ','NULL'),
		--('CH_00156','TOAN_DH',N'Hàm số y = 2^(x^2−3x) có đạo hàm là','2020-11-18','(2x − 3).2^(x^2−3x)·ln 2','2^(x^2−3x)·ln 2','(2x − 3).2^(x^2−3x)','(2x − 3).2^(x^2−3x + 1)','(2x − 3).2^(x^2−3x)·ln 2',N'Khó','NULL'),

		--('CH_00157','TOAN_TP',N'Biết tích phân của f(x).dx = −2 [0;1] và g(x).dx = 3 [0;1], khi đó [f(x) − g(x)].dx trong đoạn [0;1] bằng','2020-11-18','-5','5','-1','1','-5',N'Dễ','NULL'),
		--('CH_00158','TOAN_TP',N'Cho hàm số f(x) có đạo hàm liên tục trên R. Biết f (4) = 1 và tích phân của x.f(4x).dx = 1 [0;1], khi đó x^2.f"(x).dx trong đoạn [0;1; bằng','2020-11-18','31/2','-16','8','14','-16',N'Khó','NULL'),
		--('CH_00159','TOAN_TP',N'Cho tích phân x.dx/(x + 2)^2= a + b.ln 2 + c.ln 3 trong đoạn [0;1] với a, b, c là các số hữu tỷ. Giá trị của 3a + b + c bằng','2020-11-18','-2','-1','2','1','-1',N'Dễ','NULL'),
		--('CH_00160','TOAN_TP',N'Biết tích phân f(x).dx = 3 và g(x).dx = −4. Khi đó [f(x) + g(x)].dx trong đoạn [0;1] bằng','2020-11-18','-7','7','-1','1','-1',N'Dễ','NULL'),
		--('CH_00161','TOAN_TP',N'Cho hàm số f(x) có đạo hàm liên tục trên R. Biết f(5) = 1 và tích phân xf(5x).dx = 1, khi đó x^2.f"(x).dx bằng','2020-11-18','15','23','123/5','-25','-25',N'Khó','NULL'),
		--('CH_00162','TOAN_TP',N'Cho hàm số f(x). Biết f(0) = 4 và f"(x) = 2.cos2x+3, ∀x ∈ R, khi đó tích phân của f(x)dx trong đoạn [0;π/4] bằng?','2020-11-18','(π^2+ 2)/8','(π^2+ 8.π + 8)/8','(π^2+ 8.π + 2)/8','(π^2+ 6.π + 2)/8','(π^2+ 8.π + 2)/8',N'Khó','NULL'),
		--('CH_00163','TOAN_TP',N'Biết tích phân f(x).dx = 2 và tích phân g(x)dx = 6 , khi đó [f(x) − g(x)].dx trong đoạn [1;2] bằng','2020-11-18','4','-8','8','-4','-4',N'Khó','NULL'),
		--('CH_00164','TOAN_TP',N'Một vật di chuyển với gia tốc a(t) = -20.(1 + 2t)^-2 (m/s^2) . Khi t = 0 thì vận tốc của vật là 30 / m s. Tính quảng đường vật đó di chuyển sau 2 giây (làm tròn kết quả đến chữ số hàng	đơn vị).','2020-11-18','106m','107m','108m','109m','108m',N'Khó','NULL'),
		--('CH_00165','TOAN_TP',N'Cho tích (x - 1).dx/(x^2 + 4x + 3) = a.ln5 + b.ln5,(a,b ∈ Z) trong đoạn [0;2] Giá trị của 3a + 2b là','2020-11-18','0','1','8','10','0',N'Khó','NULL'),


		--('CH_00166','TOAN_DD',N'Cho khối nón có độ dài đường sinh bằng 2a và bán kính đáy bằng a. Thể tích của khối nónđã cho bằng','2020-11-18','√3.π.a^3/3','√3.π.a^3/2','2.π.a^3/3','.π.a^3/3','√3.π.a^3/3',N'Dễ','NULL'),
		--('CH_00167','TOAN_DD',N'Thể tích của khối nón có chiều cao h và bán kính đáy r là','2020-11-18','(π.r^2.h)/3','π.r^2.h','(4.π.r^2.h)/3','2.π.r^2.h','(π.r^2.h)/3',N'Dễ','NULL'),
		--('CH_00168','TOAN_DD',N'Thể tích của khối lăng trụ có diện tích đáy B và chiều cao h là','2020-11-18',' ',' ',' ',' ',' ',N'Dễ','NULL'),
		--('CH_00169','TOAN_TDKG',N'Thể tích của khối lăng trụ có diện tích đáy B và chiều cao h là','2020-11-18','4.B.h/3','B.h/3','3.B.h','B.h','B.h',N'Dễ','NULL'),
		--('CH_00170','TOAN_TDKG',N'Cho hình chóp S.ABC có SA vuông góc với mặt phẳng (ABC),SA = 2a , tam giác ABC vuông cân tại B và AB = a√2. (minh họa nhưhình vẽ bên). Góc giữa đường thẳng SC và mặt phẳng (ABC) bằng','2020-11-18','60 độ','45 độ','30 độ','90 độ','45 độ',N'Dễ','NULL'),
		--('CH_00171','TOAN_TDKG',N'Trong không gian Oxyz, cho mặt cầu (S) : x^2+ y^2+ (z −1)^2= 5. Có tất cả bao nhiêu điểmA(a, b, c) (a, b, c là các số nguyên) thuộc mặt phẳng (Oxy) sao cho có ít nhất hai tiếp tuyến của (S)đi qua A và hai tiếp tuyến đó vuông góc với nhau?','2020-11-18','12','16','20','8','20',N'Dễ','NULL'),
		--('CH_00172','TOAN_TDKG',N'Trong không gian Oxyz, cho điểm A(0; 3; −2). Xét đường thẳng d thay đổi, song song vớitrục Oz và cách trục Oz một khoảng bằng 2. Khi khoảng cách từ A đến d lớn nhất, d đi qua điểm nào dưới đây?','2020-11-18','Q(−2; 0; −3)','M(0; 8; −5)','N(0; 2; −5)','P(0; −2; −5)','P(0; −2; −5)',N'Khó','NULL'),
		
		
		('CH_00173','TOAN_SP',N'Xét các số phức z thỏa mãn |z| =√2. Trên mặt phẳng tọa độ Oxy tập hợp các điểm biểu diễn các số phức w =(5 + iz)/(1 + z) là một đường tròn có bán kính bằng','2020-11-18','52','2√13','2√11','44','2√13',N'Khó','NULL'),
		('CH_00174','TOAN_SP',N'Cho số phức z thỏa mãn (2 − i)z + 3 + 16i = 2 (z + i). Mô-đun của z bằng','2020-11-18','√5','13','√13','5','√13',N'Dễ','NULL'),
		('CH_00175','TOAN_SP',N'Gọi z1, z2 là hai nghiệm phức của phương trình z^2−4z +7 = 0 . Giá trị của z(1)^2 z(2)^2 bằng','2020-11-18','10','8','16','2','2',N'Dễ','NULL'),
		('CH_00176','TOAN_SP',N'Cho hai số phức z1= 2 −i, z2= 1 + i. Trên mặt phẳng tọa độ Oxy, điểm biểu diễn số phức 2.z1+ z2có tọa độ là','2020-11-18','(5; −1)','(−1; 5)','(5; 0)','(0; 5)','(5; −1)',N'Khó','NULL'),
		
		('CH_00177','TOAN_TP',N'Biết cho tích phân (cos^2x + sinx.cosx + 1).dx/(cos^4x + sin x cos^3x) = a + b.ln2 + c.ln(1 +√3), với a, b, c là các số hữu tỉ. Giá trị của abc bằng','2020-11-18','0','-2','-4','-6','-2',N'Dễ','NULL'),
		('CH_00178','TOAN_TP',N'Cho (3 + ln.x).dx/(x + 1)^2 = a.ln.3 + b.ln.2 + c với a, b, c là các số hữu tỉ. Giá trị của a^2+ b^2− c^2 bằng','2020-11-18','17/18','1/8','1','0','1',N'Khó','NULL'),
		('CH_00179','TOAN_TP',N'Cho hàm số f(x) liên tục trên R và 3f(−x) − 2f(x) = tan^2.x. Tính f(x).dx trong [-π/4;π/4]','2020-11-18','1 −π/2','π/2− 1','1 +π/4','2 −π/2','2 −π/2',N'Dễ','NULL'),
		
		
		
		
		('CH_00180','TOAN_HS',N'Kim loại nào sau đây không khử được nước ở nhiệt độ thường?','2020-11-18','Be','Ba','Ca','K','Be',N'Dễ','NULL'),
		('CH_00181','TOAN_HS',N'Chất nào sau đây có màu nâu đỏ','2020-11-18','Fe(OH)2','Fe','Fe(OH)3','FeO','Fe(OH)3',N'Dễ','NULL'),
		('CH_00182','TOAN_HS',N'Chất có cùng công thức phân tử với glucozơ là','2020-11-18','fructozơ','saccarozơ','tinh bột','xenlulozơ','fructozơ',N'Dễ','NULL'),
		('CH_00183','TOAN_HS',N'Khí X là khí gây mưa axit và được sinh ra trong quá trình đốt cháy quặng pirit. Khí X là','2020-11-18','02','N2','CO2','SO2','SO2',N'Dễ','NULL'),
		('CH_00184','TOAN_HS',N'Kim loại nào sau đây là kim loại kiềm thổ','2020-11-18','Mg','Zn','K','Cu','Mg',N'Dễ','NULL'),
		('CH_00185','TOAN_HS',N'Trong phân tử chất nào sau đây chỉ có liên kết đơn?','2020-11-18','CH4','C2H4','C2H2','C6H6(benzen)','CH4',N'Dễ','NULL'),
		('CH_00186','TOAN_HS',N'Thành phần chính của quặng hematit đỏ là','2020-11-18','Fe2O3','FeCO3','Fe3O4','FeS2','Fe2O3',N'Dễ','NULL'),
		('CH_00187','TOAN_HS',N'Ion nào sau đây có tính oxi hóa mạnh nhất','2020-11-18','Al3+','Na+','Ag+','Fe2+','Ag+',N'Dễ','NULL'),
		('CH_00188','TOAN_HS',N'Trong phèn chua có chứa loại muối nhôm nào sau đây?','2020-11-18','AlCl3','AlBr3','Al(NO3)3','Al2(SO4)2','Al2(SO4)2',N'Dễ','NULL'),
		('CH_00189','TOAN_HS',N'Phân tử khối của alanin là','2020-11-18','117','75','89','93','89',N'Dễ','NULL'),
		('CH_00190','TOAN_HS',N'Cho 20,55 gam Ba vào lượng dư dung dịch MgSO4. Sau khi các phản ứng xảy ra hoàn toàn, thu được m gam kết tủa. Giá trị của m là','2020-11-18',N'34,95',N'43,65',N'3,60',N'8,70',N'43,65',N'Dễ','NULL'),

		('CH_00191','TOAN_HS',N'Kim loại Fe không tan trong dung dịch nào sau đây?','2020-11-18','HCl',N'HNO3đặc, nguội','Fe2(SO4)3',N'H2SO4loãng',N'HNO3đặc, nguội',N'Dễ','NULL'),
		('CH_00192','TOAN_HS',N'Trùng hợp chất nào dưới đây thu được polietilen','2020-11-18','CH=CH','CH2=CH2','CH2=CH-CH3','CH3-CH3','CH2=CH2',N'Dễ','NULL'),

		('CH_00193','TOAN_HS',N'Chất béo là trieste của axit béo với','2020-11-18','etylen glicol','glixerol','phenol','etanol','glixerol',N'Dễ','NULL'),
		('CH_00194','TOAN_HS',N'Oxit nào sau đây bị khử bởi khí CO ở nhiệt độ cao?','2020-11-18','Na2O','Al2O3','MgO','Fe2O3','Fe2O3',N'Dễ','NULL'),
		('CH_00195','TOAN_HS',N'Cho các polime sau: poli(vinyl clorua); poli(metyl metacrylat); poli(etylen terephtalat);poliacrilonitrin. Số polime được điều chế bằng phản ứng trùng hợp là?','2020-11-18','1','3','2','4','3',N'Dễ','NULL'),
		('CH_00196','TOAN_HS',N'Cho 18,75 gam H2NCH2COOH phản ứng hết với dung dịch KOH, thu được dung dịchchứa m gam muối. Giá trị của m là','2020-11-18','37,50','24,25','28,25','32,75','28,25',N'Dễ','NULL'),
		('CH_00197','TOAN_HS',N'Trong nước cứng tạm thời luôn có chứa anion','2020-11-18','SO4^(2-)','OH','HCO3(-)','Cl(-)','HCO3(-)',N'Dễ','NULL'),
		('CH_00198','TOAN_HS',N'Metyl axetat có công thức cấu tạo là','2020-11-18','C2H5COOCH3','HCOOC2H5','CH3COOCH3','HOC2H4CHO','CH3COOCH3',N'Dễ','NULL'),
		
		('CH_00199','TOAN_HS',N'Ngâm một lá kẽm trong 100 ml dung dịch AgNO30,1M. Giả sử toàn bộ lượng kim loạiAg sinh ra đều bám hết vào lá kẽm. Khi phản ứng kết thúc, nhấc lá kẽm ra, làm khô, khối lượnglá kẽm tăng thêm','2020-11-18',N'0,430 gam',N'0,215gam',N'1,080 gam',N'0,755 gam',N'0,755 gam',N'Dễ','NULL'),
		('CH_00200','TOAN_HS',N'Chất X được sử dụng để khử chua đất trồng, điều chế clorua vôi, làm vật liệu trong xâydựng,... X còn có tên gọi là vôi tôi. Công thức hóa học của X là','2020-11-18','CaCO3','CaSO4','CaOCl2','Ca(OH)2','Ca(OH)2',N'Dễ','NULL'),
		('CH_00201','TOAN_HS',N'Cho dung dịch NaOH đến dư vào dung dịch muối X thu được kết tinh trắng. Muối X là :','2020-11-18','Mg(NO3)2','K2CO3','FeCl3','CuSO4','Mg(NO3)2',N'Dễ','NULL'),

		('CH_00202','TOAN_HS',N'Phát biểu nào sau đây đúng?','2020-11-18',N'Phân tử H2N-CH2-CO-NH-CH2-COOH là một đipeptit',N'Dung dịch lysin không làm chuyển màu quỳ tím',N'Tất cả các protein đều tan trong nước tạo thành dung dịch keo',N'C2H5NH2là một amin bậc II',N'Phân tử H2N-CH2-CO-NH-CH2-COOH là một đipeptit',N'Dễ','NULL'),

		('CH_00203','TOAN_HS',N'Chất X có trong nhiều loài thực vật, có nhiều nhất trong cây mía, củ cải đường và hoathốt nốt. Thủy phân chất X thu được chất Y có phản ứng tráng gương, có vị ngọt hơn đường mía.Tên gọi của X và Y lần lượt là','2020-11-18',N'xenlulozơ và glucozơ',N'saccarozơ và fructozơ',N'saccarozơ và glucozơ',N'tinh bột và fructozơ',N'saccarozơ và fructozơ',N'Dễ','NULL'),
		('CH_00204','TOAN_HS',N'Đun nóng 200 ml dung dịch glucozơ a mol/lít với lượng dư dung dịch AgNO3trongNH3. Sau khi phản ứng xảy ra hoàn toàn, thu được 21,6 gam Ag. Giá trị của a là','2020-11-18','0,5','0,2','0,1','1,0','0,5',N'Dễ','NULL'),

		('CH_00205','TOAN_HS',N'Cho 1 ml dung dịch AgNO31% vào ống nghiệm sạch, lắc nhẹ, sau đó nhỏ từ từ từnggiọt dung dịch NH32M cho đến khi kết tủa sinh ra bị hòa tan hết. Nhỏ tiếp 3-5 giọt dung dịch X,sau đó ngâm ống nghiệm chứa hỗn hợp phản ứng vào cốc nước nóng (khoảng 70-800C) trongvài phút. Trên thành ống nghiệm xuất hiện lớp bạc sáng. Chất X là','2020-11-18','ancol etylic','anđehit fomic','axit axetic','glixerol','anđehit fomic',N'Dễ','NULL'),
		('CH_00206','TOAN_HS',N'Đốt cháy hoàn toàn m gam Al trong khí O2dư, thu được 10,2 gam Al2O3. Giá trị củamlà','2020-11-18','5,40','1,35','4,80','2,70','5,40',N'Dễ','NULL'),
		('CH_00207','TOAN_HS',N'Trong các dung dịch loãng sau đây, dung dịch có pH < 7 là','2020-11-18','KOH','NaCl','Ba(OH)2','HCl','HCl',N'Dễ','NULL'),

		('CH_00208','TOAN_HS',N'Phát biểu nào sau đây sai?','2020-11-18',N'Thủy ngân phản ứng với lưu huỳnh ở ngay nhiệt độ thường',N'Tôn là sắt được tráng kẽm',N'Kim loại dẫn điện tốt nhất là Cu',N'Có thể dùng thùng nhôm đựng dung dịch axit sunfuric đặc, nguội',N'Kim loại dẫn điện tốt nhất là Cu',N'Dễ','NULL'),

		('CH_00209','TOAN_HS',N'Sau khi kết thúc các phản ứng, thí nghiệm nào sau đây thu được muối sắt(III)','2020-11-18',N'Cho lượng dư bột sắt vào dung dịch HNO3đặc nóng',N'Cho bột sắt vào dung dịch AgNO3dư',N'Nung nóng hỗn hợp bột sắt và lưu huỳnh bột (không có không khí)',N'Cho FeCO3vào dung dịch H2SO4loãng dư',N'Cho bột sắt vào dung dịch AgNO3dư',N'Dễ','NULL'),
		('CH_00210','TOAN_HS',N'Đun nóng a gam hỗn hợp E chứa triglixerit X và các axit béo với 200 ml dung dịchNaOH 1M (vừa đủ), thu được glixerol và hỗn hợp muối Y. Hiđro hóa hoàn toàn Y cần vừa đủ0,1 mol H2chỉ thu được muối natri panmitat. Đốt cháy 0,07 mol E thu được 1,645 mol CO2.Biết các phản ứng xảy ra hoàn toàn. Giá trị của a là','2020-11-18',N'52,16',N'54,56',N'50,16',N'55,40',N'52,16',N'Dễ','NULL'),

		('CH_00211','TOAN_HS',N'Cho m gam hỗn hợp X gồm CuO; Fe2O3; FeO tác dụng vừa đủ với 100 ml dung dịch chứaH2SO41M và HCl 1M. Để khử hoàn toàn m gam hỗn hợp X (nung nóng) cần tối thiểu V lít khí CO(đktc). Giá trị của V là','2020-11-18',N'4,48',N'6,72',N'2,24',N'3,36',N'3,36',N'Dễ','NULL'),
		('CH_00212','TOAN_HS',N'Bước 1: Nhỏ 3 giọt dung dịch anilin vào ống nghiệm chứa 2 ml nước cất Bước 2: Nhúng giấy quỳ tím vào dung dịch trong ống nghiệm Bước 3: Nhỏ tiếp 1 ml dung dịch HCl đặc vào ống nghiệm, lắc Bước 4: Nhỏ tiếp 1 ml dung dịch NaOH đặc vào ống nghiệm Cho các phát biểu:(a) Kết thúc bước 1, anilin hầu như không tan và lắng xuống đáy ống nghiệm(b) Kết thúc bước 2, giấy quỳ tím chuyển thành màu xanh do anilin có tính bazơ(c) Kết thúc bước 3, thu được dung dịch trong suốt(d) Kết thúc bước 4, trong ống nghiệm có anilin tạo thànhSố phát biểu đúng là','2020-11-18',N'3',N'2',N'1',N'4',N'3',N'Dễ','NULL'),
		('CH_00213','TOAN_HS',N'Cho 0,06 mol hỗn hợp hai este đơn chức X và Y tác dụng vừa đủ với dung dịch KOH thuđược hỗn hợp Z gồm các chất hữu cơ. Đốt cháy hoàn toàn Z thu được H2O; 0,144 mol CO2và 0,036mol K2CO3. Làm bay hơi Z thu được m gam chất rắn. Giá trị của m là','2020-11-18',N'6,840',N'5,040',N'6,624',N'5,472',N'6,624',N'Dễ','NULL'),
		('CH_00214','TOAN_HS',N'Cho các phát biểu sau:a) Cao su buna có tính đàn hồi và độ bền tốt hơn cao su thiên nhiênb) Oxi hóa glucozơ bằng hiđro có Ni làm xúc tác thu được sobitolc) Dầu mỡ sau khi rán, có thể dùng để tái chế thành nhiên liệud) Ứng với công thức phân tử C3H7NO2có 2 α-amino axit là đồng phân cấu tạo của nhaue) Các este đơn chức đều phản ứng với dung dịch NaOH theo tỉ lệ 1:1f) Có thể dùng Cu(OH)2trong môi trường kiềm để phân biệt các dung dịch glucozơ, etanol vàlòng trắng trứngSố phát biểu sai là','2020-11-18',N'3',N'4',N'5',N'2',N'4',N'Dễ','NULL'),
		('CH_00215','TOAN_HS',N'Cho các phát biểu sau:a) Bột nhôm tự bốc cháy khi tiếp xúc với khí clob) Trong ăn mòn điện hóa học, tại anot xảy ra quá trình oxi hóa kim loạic) Nhiệt độ nóng chảy của các kim loại kiềm thổ cao hơn các kim loại kiềmd) Hỗn hợp Al và BaO (tỉ lệ mol tương ứng 1:1) tan hoàn toàn trong nước dưe) Cho dung dịch NH3tới dư vào dung dịch FeCl3, sau khi phản ứng kết thúc thu được kết tủaf) Trong xử lí nước cứng, có thể dùng các vật liệu polime có khả năng trao đổi cationSố phát biểu đúng là','2020-11-18',N'4',N'5',N'6',N'3',N'6',N'Dễ','NULL'),
		('CH_00216','TOAN_HS',N'Hấp thụ hoàn toàn 3,36 lít khí CO2(đktc) vào dung dịch chứa x mol KOH và y mol K2CO3,thu được 200 ml dung dịch X. Cho từ từ đến hết 100 ml dung dịch X vào 450 ml dung dịch HCl0,25M, thu được 2,016 lít khí (đktc). Mặt khác, cho 100 ml dung dịch X tác dụng với dung dịchCa(OH)2dư, thu được 15 gam kết tủa. Giá trị của x là','2020-11-18',N'0,075',N'0,030',N'0,225',N'0,150',N'0,075',N'Dễ','NULL'),

		('CH_00217','TOAN_HS',N'Đốt cháy hoàn toàn 0,12 mol hỗn hợp E gồm hai chất hữu cơ mạch hở X (CnH2n+1O2N) vàeste hai chức Y (CmH2m-2O4) cần vừa đủ 0,69 mol O2, thu được CO2; N2và 0,564 mol H2O. Mặtkhác, khi cho 0,12 mol E tác dụng hết với dung dịch NaOH đun nóng, kết thúc phản ứng thu đượchỗn hợp Z gồm M và N là hai ancol no, đơn chức kế tiếp nhau trong dãy đồng đẳng (biết phân tửkhối và số mol của M đều nhỏ hơn N) và a gam hỗn hợp muối khan (có chứa muối của glyxin). Giátrị của a là','2020-11-18',N'10,890',N'14,088',N'15,096',N'11,032',N'14,088',N'Dễ','NULL'),
		('CH_00218','TOAN_HS',N'Hỗn hợp E gồm: X; Y là hai axit đồng đẳng kế tiếp; Z và T là hai este (đều hai chức,mạch hở, Y và Z là đồng phân của nhau; MT– MZ= 14). Đốt cháy hoàn toàn 7,704 gam E cầnvừa đủ 0,222 mol O2, thu được CO2và H2O. Mặt khác, cho 7,704 gam E phản ứng vừa đủ đủvới 220 ml dung dịch NaOH 0,6M. Cô cạn dung dịch sau phản ứng, thu được hỗn hợp muốikhan G của các axit cacboxylic và 1,68 gam hỗn hợp ba ancol có cùng số mol. Khối lượng muốicủa axit có phân tử khối lớn nhất trong G gần nhất với giá trị nào sau đây?','2020-11-18',N'2,6 gam',N'3,8 gam',N'3,8 gam',N'2,7 gam',N'3,8 gam',N'Dễ','NULL'),
		('CH_00219','TOAN_HS',N'Cho các sơ đồ chuyển hóa theo đúng tỉ lệ mol:E(C9H12O4) + 2NaOH ---> X1 + X2 + X3, X1 + 2HCl, X2 + O2 ---> Z + H2O, Z + X3 ---> T(C5H10O2) + H2O (a) Chất E có đồng phân hình học(b) Trong thành phần nguyên tử của X1, chỉ có các nguyên tử của 3 nguyên tố(c) Trong phân tử Y, số nguyên tử oxi gấp 2 lần số nguyên tử hiđro(d) Có hai công thức cấu tạo thỏa mãn tính chất của X3(e) Chất Z có thể được tạo thành từ CH3OH chỉ bằng 1 phản ứng hóa họcSố phát biểu đúng là','2020-11-18',N'4',N'2',N'1',N'3',N'3',N'Dễ','NULL'),
		
		
		('CH_00220','TOAN_HS',N'Nhỏ dung dịch iot vào lát cắt ngang của quả chuối xanh, thấy màu của dung dịch iot chuyển thành màu nào sau đây ?','2020-11-18',N'Xanh tím',N'Vàng',N'Trắng',N'Nâu đỏ',N'Xanh tím',N'Dễ','NULL'),
		('CH_00221','TOAN_HS',N'Kim loại nào sau đây có khối lượng riêng nhỏ nhất? ','2020-11-18',N'Cs',N'Li',N'Na',N'K',N'Li',N'Dễ','NULL'),
		('CH_00222','TOAN_HS',N'Chất béo là thành phần chính trong dầu thực vật và mỡ động vật. Trong số các chất sau đây, chất nào là chất béo ?','2020-11-18',N'C17H35COOC3H5',N'(C17H33COO)2C2H4',N'CH3COOC6H5',N'(C15H31COO)3C3H5',N'(C15H31COO)3C3H5',N'Dễ','NULL'),
		('CH_00223','TOAN_HS',N'Kim loại X được dùng để chế tạo hợp kim có nhiệt độ nóng chảy thấp, dùng làm chất trao đổi nhiệt trong lò phản ứng hạt nhân, làm xúc tác cho phản ứng tạo cao su buna. X là','2020-11-18',N'Li',N'Ca',N'Al',N'Na',N'Na',N'Dễ','NULL'),
		('CH_00224','TOAN_HS',N'Cho dung dịch Ba(OH)2 vào dung dịch chất X, thu được kết tủa màu trắng Y, biết Y tan trong dung dịch axit clohiđric. Chất X là ','2020-11-18',N'Na2SO4',N'FeCl3',N'Ca(HCO3)2',N'Cu(NO3)2',N'Ca(HCO3)2',N'Dễ','NULL'),
		('CH_00225','TOAN_HS',N'Tiến hành lên men 360 gam glucozơ với hiệu suất 100%, khối lượng ancol etylic thu được là','2020-11-18',N'138 gam',N'276 gam',N'184 gam',N'92 gam',N'184 gam',N'Dễ','NULL'),
		('CH_00226','TOAN_HS',N'Phèn chua được dùng nhiều trong công nghiệp nhuộm vải, làm sạch nước ở các vùng ngập lụt. Công thức hóa học của phèn chua có dạng là K2SO4.X2(SO4)3.24H2O. Kim loại X là','2020-11-18',N'Fe',N'Al',N'Cr',N'Ni',N'Al',N'Dễ','NULL'),
		('CH_00227','TOAN_HS',N'Chất nào sau đây không phản ứng với NaOH trong dung dịch ? ','2020-11-18',N'Gly-Ala.',N'Glyxin',N'Metyl fomat',N'Metylamin.',N'Metylamin.',N'Dễ','NULL'),
		('CH_00228','TOAN_HS',N'Sắt(II) hiđroxit nguyên chất là chất rắn, màu trắng hơi xanh, không tan trong nước. Công thức của sắt(II) hiđroxit là','2020-11-18',N'Fe(OH)2',N'Fe3O4.',N'Fe(OH)3.',N'FeO',N'Fe(OH)2',N'Dễ','NULL'),
		('CH_00229','TOAN_HS',N'Cho 8,8 gam hỗn hợp X gồm Fe và Cu phản ứng với dung dịch HCl loãng (dư), đến khi phản ứng xảy ra hoàn toàn thu được 3,36 lít khí H2 (đktc) và m gam muối khan. Giá trị của m là','2020-11-18',N'19,05',N'19,45',N'8,4',N'20,25',N'19,05',N'Dễ','NULL'),
		
		('CH_00230','TOAN_HS',N'Fructozơ là chất rắn kết tinh, tan tốt trong nước và rất ngọt. Nhận xét nào sau đây không đúng?','2020-11-18',N'Dạng mạch hở, fructozơ có 5 nhóm –OH và 1 nhóm xeton.',N'Fructozơ có phản ứng tráng gương.',N'Fructozơ oxi hóa được H2 (xt Ni,t0).',N'Fructozơ làm mất màu dung dịch brom.',N'Fructozơ làm mất màu dung dịch brom.',N'Dễ','NULL'),
		('CH_00231','TOAN_HS',N'Nhận xét nào sau đây đúng ?','2020-11-18',N'Phenyl fomiat không tham gia phản ứng tráng bạc.',N'Metyl amin không làm đổi màu quỳ tím ẩm',N'Glyxylvalin có chứa hai nguyên tử O trong phân tử.',N'Triolein thủy phân cả trong môi trường axit và bazơ',N'Triolein thủy phân cả trong môi trường axit và bazơ',N'Dễ','NULL'),
		('CH_00232','TOAN_HS',N'Axit axetic có nồng độ 5,0% được dùng làm gia vị (Giấm ăn). Công thức phân tử của axit axetic là','2020-11-18',N'C2H6O2',N'C2H6O',N'C2H4O2',N'C2H4O',N'C2H4O2',N'Dễ','NULL'),
	--17	('CH_001','TOAN_HS',N'Cho vài mẩu đất đèn bằng hạt ngô vào ống nghiệm X chứa sẵn 2 ml nước. Đậy nhanh X bằng nút có ống dẫn khí gấp khúc sục vào ống nghiệm Y chứa 2 ml dung dịch Br2. Hiện tượng xảy ra trong ống nghiệm Y là','2020-11-18',N'dung dịch Br2 bị nhạt màu',N'có kết tủa màu vàng',N'có kết tủa màu xanh',N'có kết tủa màu trắng.',N'dung dịch Br2 bị nhạt màu',N'Dễ','NULL'),
		
		
		
		
		('CH_00234','TOAN_HS',N'Bước sóng là','2020-11-18',N'Khoảng cách giữa hai điểm dao động cùng pha trên cùng phương truyền sóng.',N'Khoảng cách giữa hai điểm dao động cùng pha . ',N'Quãng đường sóng truyền được trong một giây.',N'Quãng đường sóng truyền được trong một chu kì.',N'Quãng đường sóng truyền được trong một chu kì.',N'Dễ','NULL'),
		('CH_00235','TOAN_HS',N'Trong công thức điều kiện để có sóng dừng trên dây với hai đầu cố định thì. ','2020-11-18',N'Số nút sóng bằng số bụng sóng ',N'Số nút sóng nhỏ hơn số bụng sóng ',N'Số nút sóng luôn luôn bằng 2 lần số bụng sóng',N'Số nút sóng lớn hơn số bụng sóng',N'Số nút sóng lớn hơn số bụng sóng',N'Dễ','NULL'),
		('CH_00236','TOAN_HS',N'Mạch dao động gồm cuộn cảm thuần và tụ điện. Tần số góc riêng của mạch xác định bởi ','2020-11-18',N'w = 1/√LC',N'w = LC',N'w = √LC',N'w = 1 / LC',N'w = 1/√LC',N'Dễ','NULL'),
		('CH_00237','TOAN_HS',N'Trong phương trình của dao động điều hoà, rad/s là đơn vị của đại lượng:','2020-11-18',N'Biên độ',N'Pha ban đầu',N'Tần số góc',N'Chu kỳ',N'Tần số góc',N'Dễ','NULL'),
		('CH_00238','TOAN_HS',N'Khi một chất điểm dao động điều hoà thì đại lượng nào sau đây không đổi theo thời gian? ','2020-11-18',N'Biên độ',N'Gia tốc',N'Vận tốc',N'Ly độ',N'Biên độ',N'Dễ','NULL'),
		('CH_00239','TOAN_HS',N'Sóng dọc là sóng có ','2020-11-18',N'Phương dao động là phương thẳng đứng.',N'Phương dao động là phương ngang. ',N'Phương dao động các phần tử môi trường vuông góc với phương truyền sóng. ',N'Phương dao động các phần tử môi trường trùng với phương truyền sóng. ',N' Phương dao động các phần tử môi trường trùng với phương truyền sóng. ',N'Dễ','NULL'),
		
		('CH_00240','TOAN_HS',N'Xét sóng cơ có chu kì là T, tần số f, tần số góc  w, vận tốc truyền v. Hệ thức nào sau đây đúng:','2020-11-18',N'v/ f',N'v.f',N'v/T',N'T.v',N'v.f',N'Dễ','NULL'),
		('CH_00241','TOAN_HS',N'Hệ số công suất của mạch RLC nối tiếp (cuộn cảm thuần) là:','2020-11-18',N'Z1/Z',N'Zc/Z',N'R/Z',N'R.Z',N'R/Z',N'Dễ','NULL'),
		('CH_00245','TOAN_HS',N'Đặt một điện áp xoay chiều có tần số góc ω vào hai đầu đoạn mạch RLC nối tiếp.Nếu ωL > (ωC)^(-1) thì cường độ dòng điện trong mạch','2020-11-18',N'Sớm pha với điện áp góc π/2.',N'Cùng pha với điện áp',N'Trễ pha hơn điện áp',N'Sớm pha hơn điện áp.',N'Trễ pha hơn điện áp',N'Dễ','NULL'),

		('CH_00246','TOAN_HS',N'Trong thí nghiệm với mạch điện xoay chiều RLC nối tiếp, đồng hồ đa năng hiện số dùng  chế độ đo điện áp xoay chiều, giá trị đo được là giá trị','2020-11-18',N'Ở thời điểm đo',N'Tức thời',N'Cực đại',N'Hiều dụng',N'Hiều dụng',N'Dễ','NULL'),
		('CH_00267','TOAN_HS',N'Người ta xây dựng đường dây tải điện 500kV để truyền tải điện năng nhằm mục đích','2020-11-18',N'Giảm hao phí khi truyền tải',N'Tăng dòng điện trên dây tải',N'Tăng công suất nhà máy điện.',N'Tăng hệ số công suất nơi tiêu thụ.',N'Giảm hao phí khi truyền tải',N'Dễ','NULL'),
		
		('CH_00248','TOAN_HS',N'Chu kì con lắc đơn không phụ thuộc vào','2020-11-18',N'Gia tốc trọng trường g',N'Vĩ độ địa lí',N'Chiều dài l',N'Khối lượng vật nặng m',N'Khối lượng vật nặng m',N'Dễ','NULL'),
		('CH_00249','TOAN_HS',N'Ta kí hiệu (I) là chu kì, (II) là tần số, (III) là bước sóng. Sóng âm khi truyền từ không khí  vào nước thì đại lượng nào thay đổi? ','2020-11-18',N'(I) và (II)',N'(III)',N'(II)',N'(I);(II) và (III)',N'(III)',N'Dễ','NULL'),
		('CH_00250','TOAN_HS',N'Chọn phát biểu đúng khi nói về sự biến thiên điện tích của tụ điện trong mạch dao động','2020-11-18',N'Điện tích của tụ điện dao động điều hòa với tần số góc √LC',N'Điện tích chỉ biến thiên tuần hoàn theo thời gian',N'Điện tích của tụ điện dao động điều hòa với tần số f = 1/2π√LC',N'Điện tích biến thiên theo hàm số mũ theo thời gian ',N'Điện tích của tụ điện dao động điều hòa với tần số f = 1/2π√LC',N'Dễ','NULL'),
		('CH_00251','TOAN_HS',N'Một nam châm điện dùng dòng điện xoay chiều có chu kì 62,5 (μs). Nam châm tác dụng  lên một lá thép mỏng làm cho lá thép dao động điều hòa và tạo ra sóng âm. Sóng âm do nó phát ra  truyền trong không khí là:','2020-11-18',N'Hạ âm',N'Siêu âm',N'Sóng ngang',N'Âm mà tai người có thể nghe được',N'Siêu âm',N'Dễ','NULL'),
		('CH_00252','TOAN_HS',N'Cho mạch RLC nối tiếp, đặt vào hai đầu mạch điện áp xoay chiều có biểu thức  u = U0.coswt . Đại lượng nào sau đây biến đổi thì không thể làm cho mạch xảy ra hiện tượng cộng  hưởng điện?','2020-11-18',N'Điện dung C của tụ. ',N'Điện trở thuần R',N'Tần số của điện áp xoay chiều',N'Độ tự cảm L của cuộn dây',N'Điện trở thuần R',N'Dễ','NULL'),
		('CH_00253','TOAN_HS',N'Cho hai dao động điều hoà cùng phương có cùng tần số và biên độ lần lượt là A1 = 1,6cm  và A2 = 1,2 cm. Dao động tổng hợp của hai dao động trên có biên độ có thể là','2020-11-18',N'2,4 cm',N'3,8 cm',N'3 cm',N'0,3 cm',N'2,4 cm',N'Dễ','NULL'),
		('CH_00254','TOAN_HS',N'Hình vẽ nào sau đây xác định đúng chiều dòng điện cảm ứng trong khung khi cho khung  dây dịch chuyển lại gần hoặc ra xa nam châm cố định:','2020-11-18',N'Hình A',N'Hình B',N'Hình C',N'Hình D',N'Hình C',N'Dễ','NULL'),
		('CH_00255','TOAN_HS',N'Điện áp xoay chiều chạy qua một đoạn mạch RC nối tiếp biến đổi điều hoà theo thời gian  được mô tả bằng đồ thị ở hình dưới đây. Với  B. Hình (D).  C. Hình (B).  D. Hình (C).  R = 100; C = 10^(-4)/πF. Xác định biểu thức của dòng điện ','2020-11-18',N'i = √2.cos(100.π.t - π/4)(A)',N'i = 2√2.cos(50.π.t + π/4)(A)',N'i = 4.cos(50.π.t - π/2)(A)',N'i = √2.cos(50.π.t + π/4)(A)',N'i = √2.cos(50.π.t + π/4)(A)',N'Dễ','NULL'),
		('CH_00256','TOAN_HS',N'Một con lắc lò xo đang dao động điều hoà và vật đang chuyển động về vị trí cân bằng.  Chọn phát biểu đúng','2020-11-18',N'Cơ năng của vật tăng dần đến giá trị cực đại.',N'Cơ năng của vật đang chuyển hóa từ thế năng sang động năng',N'Thế năng của vật tăng dần nhưng cơ năng không đổi',N'Thế năng tăng dần và động năng giảm dần',N'Thế năng của vật tăng dần nhưng cơ năng không đổi',N'Dễ','NULL'),
		('CH_00257','TOAN_HS',N'Trên một phương truyền sóng, vị trí những điểm dao động ngược pha nhau','2020-11-18',N'Cách nhau (2k + 1).y/2',N'Cách nhau (2k + 1).y',N'Cách nhau k',N'Cách nhau k.y/2',N' ',N'Dễ','NULL'),
		
		('CH_00258','TOAN_HS',N'Khi nói về hệ số công suất cosx của đoạn mạch xoay chiều, phát biểu nào sau đây sai?','2020-11-18',N'Với đoạn mạch gồm tụ điện và điện trở thuần mắc nối tiếp thì  .0 < cosx > 1',N'Với đoạn mạch có R, L, C mắc nối tiếp đang xảy ra cộng hưởng thì  .cosx = 0',N'Với đoạn mạch chỉ có tụ điện hoặc chỉ có cuộn cảm thuần thì  .cosx = 0',N'Với đoạn mạch chỉ có điện trở thuần thì  .cosx = 1',N'Với đoạn mạch có R, L, C mắc nối tiếp đang xảy ra cộng hưởng thì  .cosx = 0',N'Dễ','NULL'),
		('CH_00259','TOAN_HS',N'Sóng điện từ và sóng cơ học không có tính chất chung nào?','2020-11-18',N'Mang năng lượng',N'Truyền được trong chân không',N'Giao thoa',N'Phản xạ.',N'Truyền được trong chân không',N'Dễ','NULL'),
		('CH_00260','TOAN_HS',N'Hai điện tích q1 = q, q2 = -3q đặt cách nhau một khoảng r. Nếu điện tích q1 tác dụng lực  điện lên điện tích q2 có độ lớn là F thì lực tác dụng của điện tích q2 lên q1 có độ lớn là','2020-11-18',N'3F',N'1,5F',N'6F',N'F',N'F',N'Dễ','NULL'),
		('CH_00270','TOAN_HS',N'Một sóng âm có tần số xác định truyền trong không khí và trong nước với tốc độ lần lượt là  320 m/s và 1440 m/s. Khi sóng âm đó truyền từ nựớc ra không khí thì bước sóng của nó sẽ ','2020-11-18',N'Giảm 4,5 lần',N'Tăng 4,4 lần',N'Giảm 4,4 lần',N'Tăng 4,5 lần',N'Giảm 4,5 lần',N'Dễ','NULL'),
		('CH_00271','TOAN_HS',N'Con lắc lò xo dao động điều hoà với chu kì T. Đồ thị biểu diễn sự biến đối động năng và  thế năng theo thời gian cho ở hình vẽ. Chu kỳ của con lắc','2020-11-18',N'0,6s',N'0,8s',N'0,2s',N'0,4s',N'0,8s',N'Dễ','NULL'),
		('CH_00278','TOAN_HS',N'Đặt một vật phẳng nhỏ vuông góc với trục chính của thấu kính hội tụ tiêu cự 20 cm cách  kính 100 cm. Ảnh của vật','2020-11-18',N'Ngược chiều và bằng 1/4 vật',N'Cùng chiều và bằng 1/3 vật',N'Cùng chiều và bằng 1/4 vật',N'Ngược chiều và bằng 1/3 vật',N'Ngược chiều và bằng 1/4 vật',N'Dễ','NULL'),
		('CH_00279','TOAN_HS',N'Con lắc lò xo treo thẳng đứng dao động điều hoà, ở vị trí cân bằng lò xo giãn 2 cm. Khi lò  xo có chiều dài cực tiểu thì lò xo bị nén 5 cm. Biên độ dao động của con lắc là','2020-11-18',N'5 cm',N'7 cm',N'2 cm',N'3 cm',N'7 cm	',N'Dễ','NULL'),
		('CH_00280','TOAN_HS',N'Cho mạch điện một chiều như hình vẽ. Với R1 = R2 = RV = 50Ω, ξ = 3V, r = 0.  Bỏ qua điện trở dây nối, tính số chỉ vôn kế:','2020-11-18',N'2V',N'0,5V',N'1V',N'1,5V',N'1V',N'Dễ','NULL'),
		
		('CH_00281','TOAN_HS',N'Một chất điểm dao động điều hoà dọc theo trục Ox với phương trình x = 3cos10t cm. Li độ  x khi chất điểm có động năng bằng thế năng là:','2020-11-18',N'+3√2 & -3√2 cm',N'+√2 & -√2 cm',N'+2√2 & -2√2 cm',N'+3/√2 & -3/√2 cm',N'+3/√2 & -3/√2 cm',N'Dễ','NULL'),	
		('CH_00282','TOAN_HS',N'Một đoạn mạch xoay chiều gồm một điện trở thuần R = 100 Ω, cuộn cảm thuần có độ tự  cảm L = 1/π H và một tụ điện có điện dung C = 10^(-4)/2π F mắc nối tiếp giữa hai điểm có hiệu điện  thế u = 200√2cos100πt V. Tính công suất của mạch khi đó','2020-11-18',N'200W',N'100√2W',N'100W',N'200√2W',N'100√2W',N'Dễ','NULL'),
		('CH_00283','TOAN_HS',N'Cho một sóng có phương trình sóng là u = 5cosπ(4t - 0,5x)mm , trong đó x tính bằng mét t tính bằng giây. Vận tốc của sóng là:  ','2020-11-18',N'8m/s',N'0,5m/s',N'2m/s',N'4m/s',N'8m/s',N'Dễ','NULL'),

		('CH_00272','TOAN_HS',N'Đặt điện áp u = U0cos100t (V) vào hai đầu đoạn mạch AB gồm hai đoạn mạch AM và  B. 0,5 m/s.  C. 2 m/s.  D. 4m/ s.  100 3  MB mắc nối tiếp. Đoạn mạch AM gồm điện trở thuần  mắc nối tiếp với cuộn cảm thuần có  104  độ tự cảm L. Đoạn mạch MB chỉ có tụ điện có điện dung  . Biết điện áp giữa hai đầu đoạn  F2  mạch AM lệch pha  so với điện áp giữa hai đầu đoạn mạch (AB). Giá trị của L là:','2020-11-18',N'2H/π',N'H/π',N'2H/π',N'3H/π',N'H/π',N'Dễ','NULL'),
		('CH_00273','TOAN_HS',N'Hai nguồn sóng kết hợp trên mặt nước S1 và S2 dao động với phương trình u1  asin t và  u2  acost . Biết O là trung điểm của S1S2 và S1S2 = 9y . Điểm M trên trung trực của S1S2 gần O  nhất dao động cùng pha với S1 cách S1 một khoảng bao nhiêu?','2020-11-18',N'45y/8',N'41y/8',N'43y/8',N'39y/8',N'41y/8',N'Dễ','NULL'),
		('CH_00274','TOAN_HS',N'Đặt điện áp  u 100 2 cos100t V    vào hai đầu đoạn mạch ꢀB gồm hai đoạn mạch AN  C  25.105 / (F)  và NB mắc nối tiếp. Đoạn mạch ꢀN có điện trở thuần R = 40 nối tiếp tụ điện  ,đoạn mạch NB là một hộp kín X có chứa hai trong ba linh kiện R0 ;C0 ; 0 ( cuộn cảm thuần) mắc  LU  80V U  60V  . X gồm có: ','2020-11-18',N'R0=30;C0=0,0001',N'L0=0,4/π;C0=0,0001',N'R0=30;L0=0,3',N'R0=40;L0=0,4',N'R0=30;L0=0,3',N'Dễ','NULL'),
		('CH_00275','TOAN_HS',N'Hai lò xo có khối lượng không đáng kể, ghép nối tiếp nhau có độ cứng tương ứng là , một đầu nối với một điểm cố định, đầu kia nối với vật m và hệ đặt trên mặt bàn nằm ngang.  k1  2k2  Bỏ qua mọi lực cản. Kéo vật để lò xo giãn tổng cộng 12 cm rồi thả để vật dao động điều hòa dọc  theo trục của các lò xo. Ngay khi động năng bằng thế năng lần đầu, ta giữ chặt điểm nối giữa hai lò  xo. Biên độ dao động của vật sau đó bằng','2020-11-18',N'6√3 cm',N'8√2 cm',N'6√2 cm',N'4√5 cm',N'4√5 cm',N'Dễ','NULL'),
		('CH_00276','TOAN_HS',N'Con lắc lò xo nằm ngang có k = 100 N/m, m = 100 g. Kéo vật cho lò xo dãn 2 cm rồi  buông nhẹ cho vật dao động. Biết hệ số ma sát là μ = 2.10^-2. Xem con lắc dao động tắt dần chậm.  Lấy g = 10 m/s^2, quãng đường vật đi được trong 4 chu kỳ đầu tiên là :','2020-11-18',N'32 cm',N'29,6 cm',N'29,44 cm',N'29,28 cm',N'29,44 cm',N'Dễ','NULL'),
		('CH_00277','TOAN_HS',N'Một vật có khối lượng 0,01 kg dao động điều hoà quanh vị trí cân bằng x = 0, có đồ thị sự  phụ thuộc hợp lực tác dụng lên vật vào li độ như hình vẽ .Chu kì dao động là ','2020-11-18',N'0,152 s',N'0,0314 s',N'1,255 s',N'0,256 s',N'0,0314 s',N'Dễ','NULL'),
		('CH_00284','TOAN_HS',N'Một sợi dây căng giữa hai điểm cố định cách nhau 75cm. Người ta tạo sóng dừng trên dây.  Hai tần số gần nhau nhất cùng tạo ra sóng dừng trên dây là 15Hz và 20Hz. Vận tốc truyền sóng trên  dây đó bằng:','2020-11-18',N'7,5 m/s',N'5 m/s',N'22,5 m/s',N'30 m/s',N'7,5 m/s',N'Dễ','NULL'),
		('CH_00285','TOAN_HS',N'Tại điểm O đặt hai nguồn âm điểm giống hệt nhau phát ra âm đẳng hướng có công suất  không đổi. Điểm ꢀ cách O một đoạn x (m). Trên tia vuông góc với Oꢀ lấy điểm B cách ꢀ một  khoảng 6m. Điểm M thuộc đoạn ꢀB sao cho ꢀM = 4,5m. Thay đổi x để góc MOB có giá trị lớn  nhất, khi đó mức cường độ âm tại ꢀ là LA = 40 dB . Để mức cường độ âm tại M là 50 dB thì cần đặt  thêm tại O bao nhiêu nguồn âm nữa?','2020-11-18',N'35',N'25',N'15',N'33',N'33',N'Dễ','NULL'),
		('CH_00286','TOAN_HS',N' ','2020-11-18',N'',N' ',N' ',N' ',N' ',N'Dễ','NULL'),
		('CH_001','TOAN_HS',N' ','2020-11-18',N'',N' ',N' ',N' ',N' ',N'Dễ','NULL'),
		('CH_001','TOAN_HS',N' ','2020-11-18',N'',N' ',N' ',N' ',N' ',N'Dễ','NULL'),
		('CH_001','TOAN_HS',N' ','2020-11-18',N'',N' ',N' ',N' ',N' ',N'Dễ','NULL'),
		('CH_001','TOAN_HS',N' ','2020-11-18',N'',N' ',N' ',N' ',N' ',N'Dễ','NULL'),
		('CH_001','TOAN_HS',N' ','2020-11-18',N'',N' ',N' ',N' ',N' ',N'Dễ','NULL'),
		('CH_001','TOAN_HS',N' ','2020-11-18',N'',N' ',N' ',N' ',N' ',N'Dễ','NULL'),
		('CH_001','TOAN_HS',N' ','2020-11-18',N'',N' ',N' ',N' ',N' ',N'Dễ','NULL'),
		('CH_001','TOAN_HS',N' ','2020-11-18',N'',N' ',N' ',N' ',N' ',N'Dễ','NULL'),
		('CH_001','TOAN_HS',N' ','2020-11-18',N'',N' ',N' ',N' ',N' ',N'Dễ','NULL'),
		('CH_001','TOAN_HS',N' ','2020-11-18',N'',N' ',N' ',N' ',N' ',N'Dễ','NULL'),
		('CH_001','TOAN_HS',N' ','2020-11-18',N'',N' ',N' ',N' ',N' ',N'Dễ','NULL'),
		('CH_001','TOAN_HS',N' ','2020-11-18',N'',N' ',N' ',N' ',N' ',N'Dễ','NULL'),
		('CH_001','TOAN_HS',N' ','2020-11-18',N'',N' ',N' ',N' ',N' ',N'Dễ','NULL'),
		('CH_001','TOAN_HS',N' ','2020-11-18',N'',N' ',N' ',N' ',N' ',N'Dễ','NULL'),







select MONHOCCHITIET.TenMonHoc,CauHoi.Ma_CauHoi,NoiDung_CauHoi,Cauhoi.Hinh,DapAn_A,DapAn_B,DapAn_C,DapAn_D,DapAn_Dung,DoKho
from MONHOC inner join MONHOCCHITIET on MONHOC.Ma_MonHoc = MONHocChiTiet.Ma_MonHoc
			inner join CauHoi on MONHOCCHITIET.Ma_MonHocChiTiet = CAUHOI.Ma_MonHocChiTiet
where MONHOC.Ma_MonHoc like 'Toan' and CauHoi.Delete_At is null



update CauHoi set Ma_MonHocChiTiet = 'TOAN_TP' where Ma_CauHoi = 'CH_00164'


select * from DETHI
select * from hocsinh
select * from DeThiChiTiet --where Ma_DeThi like 'DT_41863'
select * from KETQUA
select * from cauhoi
select * from dethi where Delete_At is null

update dethi set TenDeThi= ?,DoKho =? where ma_Dethi like 'DT_Toan_101'
select * from CauHoi where  Ma_CauHoi like 'CH_103598' --DoKho like N'Khó' and Ma_MonHocChiTiet like 'TOAN_TDKG'

select Ma_CauHoi,CauHoi.Ma_MonHocChiTiet
from MONHOCCHITIET inner join CAUHOI on CauHoi.Ma_MonHocChiTiet = MonHocChiTiet.Ma_MonHocChiTiet
where MonHocChiTiet.Ma_MonHoc like '%Toan' and DoKho like N'Dễ'


select * from MonHocChiTiet where ma_MonHoc like '%Toan'

select * from cauhoi where Delete_At is null and DoKho like N'%Khó' and Ma_MonHocChiTiet = 'TOAN_TT'

select * from MonHocChiTiet where Ma_MonHoc like '%Toan'

	insert into DeThi(Ma_DeThi,Ma_MonHoc,Ma_GV,TenDeThi,ThoiGianLamBai,TongSoCau,NgayTao,DoKho)
	values ('DT_Toan_101','Toan','GV_00101',N'Đề thi môn toán','60','50','11-20-2020',N'Khó')
	delete from DeTHi where Ma_DeThi = 'DT_1010'

	insert into DETHICHITIET(Ma_DeThiChiTiet,Ma_DeThi,Ma_CauHoi,GhiChu)
	values ('DTCT_00101','DT_Toan_101','CH_00101',N'Ghi chú'),
			('DTCT_00102','DT_Toan_101','CH_00102',N'Ghi chú'),
			('DTCT_00103','DT_Toan_101','CH_00103',N'Ghi chú'),
			('DTCT_00104','DT_Toan_101','CH_00104',N'Ghi chú'),
			('DTCT_00105','DT_Toan_101','CH_00105',N'Ghi chú'),
			('DTCT_00106','DT_Toan_101','CH_00106',N'Ghi chú'),
			('DTCT_00107','DT_Toan_101','CH_00107',N'Ghi chú'),
			('DTCT_00108','DT_Toan_101','CH_00108',N'Ghi chú'),
			('DTCT_00109','DT_Toan_101','CH_00109',N'Ghi chú'),

			('DTCT_00110','DT_Toan_101','CH_00110',N'Ghi chú'),
			('DTCT_00111','DT_Toan_101','CH_00111',N'Ghi chú'),
			('DTCT_00112','DT_Toan_101','CH_00112',N'Ghi chú'),
			('DTCT_00113','DT_Toan_101','CH_00113',N'Ghi chú'),
			('DTCT_00114','DT_Toan_101','CH_00114',N'Ghi chú'),
			('DTCT_00115','DT_Toan_101','CH_00115',N'Ghi chú'),
			('DTCT_00116','DT_Toan_101','CH_00116',N'Ghi chú'),
			('DTCT_00117','DT_Toan_101','CH_00117',N'Ghi chú'),
			('DTCT_00118','DT_Toan_101','CH_00118',N'Ghi chú'),
			('DTCT_00119','DT_Toan_101','CH_00119',N'Ghi chú'),

			('DTCT_00120','DT_Toan_101','CH_00120',N'Ghi chú'),
			('DTCT_00121','DT_Toan_101','CH_00121',N'Ghi chú'),
			('DTCT_00122','DT_Toan_101','CH_00122',N'Ghi chú'),
			('DTCT_00123','DT_Toan_101','CH_00123',N'Ghi chú'),
			('DTCT_00124','DT_Toan_101','CH_00124',N'Ghi chú'),
			('DTCT_00125','DT_Toan_101','CH_00125',N'Ghi chú'),
			('DTCT_00126','DT_Toan_101','CH_00126',N'Ghi chú'),
			('DTCT_00127','DT_Toan_101','CH_00127',N'Ghi chú'),
			('DTCT_00128','DT_Toan_101','CH_00128',N'Ghi chú'),
			('DTCT_00129','DT_Toan_101','CH_00129',N'Ghi chú'),
			('DTCT_00130','DT_Toan_101','CH_00130',N'Ghi chú'),
			('DTCT_00131','DT_Toan_101','CH_00131',N'Ghi chú'),
			('DTCT_00132','DT_Toan_101','CH_00132',N'Ghi chú'),
			('DTCT_00133','DT_Toan_101','CH_00133',N'Ghi chú'),
			('DTCT_00134','DT_Toan_101','CH_00134',N'Ghi chú'),
			('DTCT_00135','DT_Toan_101','CH_00135',N'Ghi chú'),
			('DTCT_00136','DT_Toan_101','CH_00136',N'Ghi chú'),
			('DTCT_00137','DT_Toan_101','CH_00137',N'Ghi chú'),
			('DTCT_00138','DT_Toan_101','CH_00138',N'Ghi chú'),
			('DTCT_00139','DT_Toan_101','CH_00139',N'Ghi chú'),
			('DTCT_00140','DT_Toan_101','CH_00140',N'Ghi chú'),
			('DTCT_00141','DT_Toan_101','CH_00141',N'Ghi chú'),
			('DTCT_00142','DT_Toan_101','CH_00142',N'Ghi chú'),
			('DTCT_00143','DT_Toan_101','CH_00143',N'Ghi chú'),
			('DTCT_00144','DT_Toan_101','CH_00144',N'Ghi chú'),
			('DTCT_00145','DT_Toan_101','CH_00145',N'Ghi chú'),
			('DTCT_00146','DT_Toan_101','CH_00146',N'Ghi chú'),
			('DTCT_00147','DT_Toan_101','CH_00147',N'Ghi chú'),
			('DTCT_00148','DT_Toan_101','CH_00148',N'Ghi chú'),
			('DTCT_00149','DT_Toan_101','CH_00149',N'Ghi chú'),
			('DTCT_00150','DT_Toan_101','CH_00150',N'Ghi chú')

	insert into KETQUA(Ma_KetQua,Ma_DeThi,Ma_HocSinh,SoCauDung,Diem,GhiChu)
	values ('KQ_00101','DT_Toan_101','dat',20,4,N'Qúa gà'),
			('KQ_00110','DT_Toan_101','dat',25,5,N'Đỡ hơn rồi đó ku'),
			('KQ_00120','DT_Toan_101','kien',18,3.6,N'Qúa gà'),
			('KQ_00125','DT_Toan_101','kien',26,5.2,N'Đỡ hơn rồi đó ku'),