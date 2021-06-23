package DAO;

import Entity.DeThiChiTiet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class DeThiChiTietDAO implements EntityDAOimp<DeThiChiTiet, String> {

    private Statement stst;
    private PreparedStatement ps;
    private ResultSet rs;
    
    String insert = "insert into DETHICHITIET(Ma_DeThiChiTiet,Ma_DeThi,Ma_CauHoi,GhiChu) values(?,?,?,?)";
    
    @Override
    public void insert(DeThiChiTiet entity, Connection con) {
        try {
            ps = con.prepareStatement(insert);
            ps.setString(1, entity.getMaDeThiCT());
            ps.setString(2, entity.getMaDeThi());
            ps.setString(3, entity.getMaCauHoi());
            ps.setString(4, entity.getGhiChu());
            ps.execute();
        } catch (Exception e) {
        }
    }

    @Override
    public void update(DeThiChiTiet entity, Connection con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(DeThiChiTiet entity, Connection con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DeThiChiTiet> selectAll(Connection con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DeThiChiTiet selectByID(String key, Connection con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
