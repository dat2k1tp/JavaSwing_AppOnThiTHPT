package DAO;

import Entity.MonHoc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MonHocDAO implements EntityDAOimp<MonHoc, String> {

    private Statement stst;
    private PreparedStatement ps;
    private ResultSet rs;

    String select_IS_NULL = "select * from monhoc ";
    String insert = "insert into MonHoc(Ma_MonHoc,TenMonHoc,Ma_GV) values(?,?,?)";
    String update = "update MonHoc set TenMonHoc  = ? where Ma_MonHoc = ?";
    String delete = "delete from monhoc where ma_MonHoc =?";
    String select_By_Ten = "select * from monhoc where TenMonHoc like ?";

    @Override
    public void insert(MonHoc entity, Connection con) {
        try {
            ps = con.prepareStatement(insert);
            ps.setString(1, entity.getMaMonHoc());
            ps.setString(2, entity.getTenMonHoc());
            ps.setString(3, entity.getMaGV());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(MonHoc entity, Connection con) {
        try {
            ps = con.prepareStatement(update);
            ps.setString(1, entity.getTenMonHoc());
            ps.setString(2, entity.getMaMonHoc());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(MonHoc entity, Connection con) {
        try {
            ps = con.prepareStatement(delete);
            ps.setString(1, entity.getMaMonHoc());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<MonHoc> selectAll(Connection con) {
        List<MonHoc> list = new ArrayList<>();
        try {
            stst = con.createStatement();
            rs = stst.executeQuery(select_IS_NULL);
            while (rs.next()) {
                String maMonHoc = rs.getString("Ma_MonHoc");
                String tenMonHoc = rs.getString("TenMonHoc");
                String maGV = rs.getString("Ma_GV");

                MonHoc monhoc = new MonHoc(maMonHoc, tenMonHoc, maGV);
                list.add(monhoc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<MonHoc> select_By_Ten(String key, Connection con) {
        List<MonHoc> list = new ArrayList<>();
        try {
            ps = con.prepareStatement(select_By_Ten);
            ps.setString(1, "%" + key + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                String maMonHoc = rs.getString("Ma_MonHoc");
                String tenMonHoc = rs.getString("TenMonHoc");
                String maGV = rs.getString("Ma_GV");

                MonHoc monhoc = new MonHoc(maMonHoc, tenMonHoc, maGV);
                list.add(monhoc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public MonHoc selectByID(String key, Connection con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
