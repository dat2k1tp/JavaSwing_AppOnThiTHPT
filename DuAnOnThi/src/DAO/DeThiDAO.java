package DAO;

import Entity.DeThi;
import TienIch_HoTro.XConvert;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeThiDAO implements EntityDAOimp<DeThi, String> {

    private Statement stst;
    private PreparedStatement ps;
    private ResultSet rs;
    String insert = "insert into DeThi(Ma_DeThi,Ma_MonHoc,Ma_GV,TenDeThi,ThoiGianLamBai,TongSoCau,NgayTao,DoKho) values(?,?,?,?,?,?,?,?)";
    String update = "update dethi set TenDeThi= ?,DoKho =? where ma_Dethi = ?";
    String select = "select * from dethi where Delete_At is null order by NgayTao desc";
    String delete = "update dethi set Delete_At= ?,Delete_User =? where ma_Dethi = ?";
    String select_ByName = "select * from dethi where Delete_At is null and TenDeThi like ? order by NgayTao desc";
     String thungRac = "select * from dethi where Delete_At is not null and Year(Delete_At) like ?  order by Delete_At desc";
    
    @Override
    public void insert(DeThi entity, Connection con) {
        try {
            ps = con.prepareStatement(insert);
            ps.setString(1, entity.getMaDeThi());
            ps.setString(2, entity.getMaMonHoc());
            ps.setString(3, entity.getMaGV());
            ps.setString(4, entity.getTenDeThi());
            ps.setInt(5, entity.getThoiGianLamBai());
            ps.setInt(6, entity.getTongSoCau());
            ps.setDate(7, XConvert.convert(entity.getNgayTao()));
            ps.setString(8, entity.getDoKho());
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(DeThi entity, Connection con) {
        try {
            ps = con.prepareStatement(update);
            ps.setString(1, entity.getTenDeThi());
            ps.setString(2, entity.getDoKho());
            ps.setString(3, entity.getMaDeThi());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void reStore(String ma, Connection con) {
        try {
            String khoiPhuc = "update dethi set Delete_At = null,Delete_User=null where ma_Dethi like ?";
            ps = con.prepareStatement(khoiPhuc);
            ps.setString(1,"%"+ma+"%");
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(DeThi entity, Connection con) {
        try {
            ps = con.prepareStatement(delete);
            ps.setString(1, entity.getDelete_At());
            ps.setString(2, entity.getDelete_User());
            ps.setString(3, entity.getMaDeThi());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DeThi> selectAll(Connection con) {
        List<DeThi> list = new ArrayList<>();

        try {
            stst = con.createStatement();
            rs = stst.executeQuery(select);
            while (rs.next()) {
                String maDeThi = rs.getString("Ma_DeThi");
                String tenDeThi = rs.getString("TenDeThi");
                String maMonHoc = rs.getString("Ma_MonHoc");
                int thoiGian = rs.getInt("ThoiGianLamBai");
                int tongSoCau = rs.getInt("TongSoCau");
                Date ngayTao = rs.getDate("NgayTao");
                String doKho = rs.getString("DoKho");
                String maGV = rs.getString("Ma_GV");

                DeThi deThi = new DeThi(maDeThi, maMonHoc, maGV, tenDeThi, thoiGian, ngayTao, tongSoCau, doKho);
                list.add(deThi);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    public List<DeThi> select_ByName(String key, Connection con) {
        List<DeThi> list = new ArrayList<>();

        try {
            ps = con.prepareStatement(select_ByName);
            ps.setString(1, "%" + key + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                String maDeThi = rs.getString("Ma_DeThi");
                String tenDeThi = rs.getString("TenDeThi");
                String maMonHoc = rs.getString("Ma_MonHoc");
                int thoiGian = rs.getInt("ThoiGianLamBai");
                int tongSoCau = rs.getInt("TongSoCau");
                Date ngayTao = rs.getDate("NgayTao");
                String doKho = rs.getString("DoKho");
                String maGV = rs.getString("Ma_GV");

                DeThi deThi = new DeThi(maDeThi, maMonHoc, maGV, tenDeThi, thoiGian, ngayTao, tongSoCau, doKho);
                list.add(deThi);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    @Override
    public DeThi selectByID(String key, Connection con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<DeThi> selectBy_TimKiemThungRac(String key, Connection con) {
        List<DeThi> list = new ArrayList<>();

        try {
            ps = con.prepareStatement(thungRac);
            ps.setString(1, "%" + key + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                String maDeThi = rs.getString("Ma_DeThi");
                String tenDeThi = rs.getString("TenDeThi");
                String maMonHoc = rs.getString("Ma_MonHoc");
                int thoiGian = rs.getInt("ThoiGianLamBai");
                int tongSoCau = rs.getInt("TongSoCau");
                Date ngayTao = rs.getDate("NgayTao");
                String doKho = rs.getString("DoKho");
                String maGV = rs.getString("Ma_GV");
                 String delete_At = rs.getString("Delete_At");
                String delete_User = rs.getString("Delete_User");
                DeThi deThi = new DeThi(maDeThi, maMonHoc, maGV, tenDeThi,
                ngayTao, tongSoCau, thoiGian, doKho, delete_At, delete_User);
                list.add(deThi);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

}
