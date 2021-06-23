package DAO;

import Entity.GiaoVien;
import TienIch_HoTro.XConvert;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GiaoVienDAO implements EntityDAOimp<GiaoVien, String> {

    private Statement stst;
    private PreparedStatement ps;
    private ResultSet rs;

    String seletct_All_NULL = "select * from GiaoVien where Delete_At is null order by NgayTao desc";
    String seletct_All = "select * from GiaoVien ";
    String insert = "insert into GiaoVien(Ma_GV,HoTen,NgaySinh,GioiTinh,SDT,Email,DiaChi,Hinh,MatKhau,NgayTao) values(?,?,?,?,?,?,?,?,?,?)";
    String selectBy_HoTen = "select * from GiaoVien where Delete_At is null and HoTen like ? order by NgayTao desc";
    String update = "update giaovien set HoTen =?, NgaySinh =?,GioiTinh=?,SDT=?,Email=?,DiaChi=?,Hinh=? where Ma_GV =?";
    String delet = "update giaovien set Delete_At = ?,Delete_User = ? where Ma_GV = ?";
    String thungRac = "select * from GiaoVien where Delete_At is not null and Year(Delete_At) like ?  order by Delete_At desc";
    @Override
    public void insert(GiaoVien entity, Connection con) {
        try {
            ps = con.prepareStatement(insert);
            ps.setString(1, entity.getMaGV());
            ps.setString(2, entity.getHoTen());
            ps.setDate(3, XConvert.convert(entity.getNgaySinh()));
            ps.setBoolean(4, entity.getGioiTinh());
            ps.setString(5, entity.getSdt());
            ps.setString(6, entity.getEmail());
            ps.setString(7, entity.getDiaChi());
            ps.setString(8, entity.getHinh());
            ps.setString(9, entity.getMatKhau());
            ps.setDate(10, XConvert.convert(entity.getNgayTao()));
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(GiaoVien entity, Connection con) {
        try {
            ps = con.prepareStatement(update);
            ps.setString(1, entity.getHoTen());
            ps.setDate(2, XConvert.convert(entity.getNgaySinh()));
            ps.setBoolean(3, entity.getGioiTinh());
            ps.setString(4, entity.getSdt());
            ps.setString(5, entity.getEmail());
            ps.setString(6, entity.getDiaChi());
            ps.setString(7, entity.getHinh());
            ps.setString(8, entity.getMaGV());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void reStore(String ma, Connection con) {
        try {
            String khoiPhuc = "update giaovien set Delete_At=null,Delete_User=null where Ma_GV =?";
            ps = con.prepareStatement(khoiPhuc);
            ps.setString(1,ma);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delete(GiaoVien entity, Connection con) {
        try {
            ps = con.prepareStatement(delet);
            ps.setString(1, entity.getDelete_At());
            ps.setString(2, entity.getDelete_User());
            ps.setString(3, entity.getMaGV());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<GiaoVien> selectAll(Connection con) {
        List<GiaoVien> list = new ArrayList<>();
        try {
            stst = con.createStatement();
            rs = stst.executeQuery(seletct_All_NULL);
            while (rs.next()) {
                String maGV = rs.getString("Ma_GV");
                String hoTen = rs.getString("HoTen");
                Date ngaySinh = rs.getDate("NgaySinh");
                Boolean gioiTinh = rs.getBoolean("GioiTinh");
                String sdt = rs.getString("SDT");
                String email = rs.getString("Email");
                String diaChi = rs.getString("DiaChi");
                String hinh = rs.getString("Hinh");
                String matKhau = rs.getString("MatKhau");
                Date ngayTao = rs.getDate("NgayTao");

                GiaoVien giaoVien = new GiaoVien(maGV, hoTen, ngaySinh, gioiTinh, sdt,
                        email, diaChi, hinh, matKhau, ngayTao);
                list.add(giaoVien);
                System.out.println("Mã giáo viên : " + maGV + "\t họ tên :" + hoTen);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<GiaoVien> selectAll_ALL(Connection con) {
        List<GiaoVien> list = new ArrayList<>();
        try {
            stst = con.createStatement();
            rs = stst.executeQuery(seletct_All);
            while (rs.next()) {
                String maGV = rs.getString("Ma_GV");
                String hoTen = rs.getString("HoTen");
                Date ngaySinh = rs.getDate("NgaySinh");
                Boolean gioiTinh = rs.getBoolean("GioiTinh");
                String sdt = rs.getString("SDT");
                String email = rs.getString("Email");
                String diaChi = rs.getString("DiaChi");
                String hinh = rs.getString("Hinh");
                String matKhau = rs.getString("MatKhau");
                Date ngayTao = rs.getDate("NgayTao");

                GiaoVien giaoVien = new GiaoVien(maGV, hoTen, ngaySinh, gioiTinh, sdt,
                        email, diaChi, hinh, matKhau, ngayTao);
                list.add(giaoVien);
                //System.out.println("Mã giáo viên : " + maGV + "\t họ tên :" + hoTen);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<GiaoVien> selectAll_By_Ten(String key, Connection con) {
        List<GiaoVien> list = new ArrayList<>();
        try {
            ps = con.prepareStatement(selectBy_HoTen);
            ps.setString(1, "%" + key + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                String maGV = rs.getString("Ma_GV");
                String hoTen = rs.getString("HoTen");
                Date ngaySinh = rs.getDate("NgaySinh");
                Boolean gioiTinh = rs.getBoolean("GioiTinh");
                String sdt = rs.getString("SDT");
                String email = rs.getString("Email");
                String diaChi = rs.getString("DiaChi");
                String hinh = rs.getString("Hinh");
                String matKhau = rs.getString("MatKhau");
                Date ngayTao = rs.getDate("NgayTao");

                GiaoVien giaoVien = new GiaoVien(maGV, hoTen, ngaySinh, gioiTinh, sdt,
                        email, diaChi, hinh, matKhau, ngayTao);
                list.add(giaoVien);
                System.out.println("tao sợ quá ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public GiaoVien selectByID(String key, Connection con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     public List<GiaoVien> selectThungRac(String key, Connection con) {
        List<GiaoVien> list = new ArrayList<>();
        try {
            ps = con.prepareStatement(thungRac);
            ps.setString(1, "%" + key + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                String maGV = rs.getString("Ma_GV");
                String hoTen = rs.getString("HoTen");
                Date ngaySinh = rs.getDate("NgaySinh");
                Boolean gioiTinh = rs.getBoolean("GioiTinh");
                String sdt = rs.getString("SDT");
                String email = rs.getString("Email");
                String diaChi = rs.getString("DiaChi");
                String hinh = rs.getString("Hinh");
                String matKhau = rs.getString("MatKhau");
                Date ngayTao = rs.getDate("NgayTao");
                String deleteAt = rs.getString("Delete_At");
                String deleteUser= rs.getString("Delete_User");
                GiaoVien giaoVien = new GiaoVien(maGV, hoTen, ngaySinh, ngayTao,
                gioiTinh, sdt, email, diaChi, hinh, matKhau, deleteAt, deleteUser);
                list.add(giaoVien);
                System.out.println("tao sợ quá ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


}
