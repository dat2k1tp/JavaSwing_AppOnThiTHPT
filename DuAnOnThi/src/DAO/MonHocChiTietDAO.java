package DAO;

import Entity.MonHocChiTiet;
import TienIch_HoTro.XConvert;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MonHocChiTietDAO implements EntityDAOimp<MonHocChiTiet, String> {

    private Statement stst;
    private PreparedStatement ps;
    private ResultSet rs;

    String select_IS_NULL = "select * from Monhocchitiet where Delete_At is null";
    //test delete_at trong select_BY_MAMH
    String select_BY_MAMH = "select * from Monhocchitiet Where Ma_MonHoc = ? and Delete_At is null order by NgayTao desc";
    String insert = "insert into monhocchitiet(Ma_MonHocChiTiet,tenMonHoc,Ma_MonHoc,NgayTao,Hinh,GhiChu) values(?,?,?,?,?,?)";
    String update = "update monhocchitiet set tenMonHoc =?, Hinh =?,GhiChu =? where  Ma_MonHocChiTiet =? ";
    String soft_Delete = "update monhocchitiet set Delete_At =?, Delete_User =? where  Ma_MonHocChiTiet =? ";
    String thungRac = "select * from Monhocchitiet mhct inner join MONHOC mh\n"
                      + "on mhct.Ma_MonHoc=mh.Ma_MonHoc\n"
                     + "where Delete_At is not null and mh.TenMonHoc  like ? \n"
                      + " order by Delete_At desc";

    @Override
    public void insert(MonHocChiTiet entity, Connection con) {
        try {
            ps = con.prepareStatement(insert);
            ps.setString(1, entity.getMaMonHocCT());
            ps.setString(2, entity.getTenMonHc());
            ps.setString(3, entity.getMa_MonHoc());
            ps.setDate(4, XConvert.convert(entity.getNgayTao()));
            ps.setString(5, entity.getHinh());
            ps.setString(6, entity.getGhiChu());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(MonHocChiTiet entity, Connection con) {
        try {
            ps = con.prepareStatement(update);
            ps.setString(1, entity.getTenMonHc());
            ps.setString(2, entity.getHinh());
            ps.setString(3, entity.getGhiChu());
            ps.setString(4, entity.getMaMonHocCT());
            System.out.println("true !");

            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //khoi phuc du lieu
    public void reStore(String ma, Connection con) {
        try {
             String khoiPhuc = "update monhocchitiet set Delete_At=null,Delete_User=null where  Ma_MonHocChiTiet =? ";
            ps = con.prepareStatement(khoiPhuc);
            ps.setString(1,ma);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void delete(MonHocChiTiet entity, Connection con) {
        try {
            ps = con.prepareStatement(soft_Delete);
            ps.setString(1, entity.getDelete_At());
            ps.setString(2, entity.getDelete_User());
            ps.setString(3, entity.getMaMonHocCT());

            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<MonHocChiTiet> selectAll(Connection con) {
        List<MonHocChiTiet> list = new ArrayList<>();
        try {
            stst = con.createStatement();
            rs = stst.executeQuery(select_IS_NULL);
            while (rs.next()) {
                String maMonHocCT = rs.getString("Ma_MonHocChiTiet");
                String tenMonHoc = rs.getString("TenMonHoc");
                String maMonHoc = rs.getString("Ma_MonHoc");
                String hinh = rs.getString("Hinh");
                String ghiChu = rs.getString("GhiChu");
                Date ngayTao = rs.getDate("NgayTao");

                MonHocChiTiet monHocCT = new MonHocChiTiet(maMonHocCT, tenMonHoc, maMonHoc, hinh, ghiChu, ngayTao);
                list.add(monHocCT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<MonHocChiTiet> selectAll_By_MaMonHoc(String key, Connection con) {
        List<MonHocChiTiet> list = new ArrayList<>();
        try {
            ps = con.prepareStatement(select_BY_MAMH);
            ps.setString(1, key);
            rs = ps.executeQuery();
            while (rs.next()) {
                String maMonHocCT = rs.getString("Ma_MonHocChiTiet");
                String tenMonHoc = rs.getString("TenMonHoc");
                String maMonHoc = rs.getString("Ma_MonHoc");
                String hinh = rs.getString("Hinh");
                String ghiChu = rs.getString("GhiChu");
                Date ngayTao = rs.getDate("NgayTao");

                MonHocChiTiet monHocCT = new MonHocChiTiet(maMonHocCT, tenMonHoc, maMonHoc, hinh, ghiChu, ngayTao);
                list.add(monHocCT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public MonHocChiTiet selectByID(String key, Connection con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<MonHocChiTiet> selectThungRac(String key, Connection con) {
        List<MonHocChiTiet> list = new ArrayList<>();
        try {
            ps = con.prepareStatement(thungRac);
            ps.setString(1, "%" + key + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                String maMonHocCT = rs.getString("Ma_MonHocChiTiet");
                String tenMonHoc = rs.getString("TenMonHoc");
                String maMonHoc = rs.getString("Ma_MonHoc");
                String hinh = rs.getString("Hinh");
                String ghiChu = rs.getString("GhiChu");
                Date ngayTao = rs.getDate("NgayTao");
                String deAt = rs.getString("delete_At");
                String deUs = rs.getString("delete_User");
                MonHocChiTiet monHocCT = new MonHocChiTiet(maMonHocCT, tenMonHoc, maMonHoc, hinh, ghiChu, ngayTao, deAt, deUs);
                list.add(monHocCT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
