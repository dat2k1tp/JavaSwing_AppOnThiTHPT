package DAO;

import Entity.CauHoi;
import TienIch_HoTro.XConvert;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CauHoiDAO implements EntityDAOimp<CauHoi, String> {

    private Statement stst;
    private PreparedStatement ps;
    private ResultSet rs;

    String insert = "insert into CauHoi(Ma_CauHoi,Ma_MonHocChiTiet,NoiDung_CauHoi,NgayTao_CauHoi,DapAn_A,DapAn_B,DapAn_C,DapAn_D,DapAn_Dung,DoKho,Hinh)\n"
            + "values(?,?,?,?,?,?,?,?,?,?,?)";
    String update = "update cauhoi set Ma_MonHocChiTiet = ?, NoiDung_CauHoi = ?,DapAn_A = ?,DapAn_B = ?,DapAn_C = ?,DapAn_D = ?, DapAn_Dung = ?, DoKho =?,Hinh =? where Ma_CauHoi =?";
    String select_IS_NULL = "select * from cauhoi  where Delete_At is null";
    String select_ALL = "select * from cauhoi ";
    String select_BY_MonHocCT = "select * from cauhoi  where Delete_At is null and  Ma_MonHocChiTiet = ? order by NgayTao_CauHoi desc";
    String select_BY_DE = "select * from cauhoi where Delete_At is null and DoKho like N'%Dễ'";
    String select_BY_MaMH_De = "select * from CauHoi where Ma_MonHocChiTiet like ? and DoKho like N'Dễ' and Delete_At is null ";
    String select_BY_MaMH_Kho = "select * from CauHoi where Ma_MonHocChiTiet like ? and DoKho like N'Khó' and Delete_At is null";
    String select_BY_MaMH_TT = "select * from cauhoi  where Ma_MonHocChiTiet = 'TOAN_TT' and Delete_At is null ";

    String delet_Soft = "update CauHoi set Delete_At=?,Delete_User=? where Ma_CauHoi = ?";

    String select_By_MonHoc = "select CauHoi.Ma_CauHoi, MONHOCCHITIET.TenMonHoc,NoiDung_CauHoi,Cauhoi.Hinh,DapAn_A,DapAn_B,DapAn_C,DapAn_D,DapAn_Dung,DoKho\n"
            + "from MONHOC inner join MONHOCCHITIET on MONHOC.Ma_MonHoc = MONHocChiTiet.Ma_MonHoc\n"
            + "			inner join CauHoi on MONHOCCHITIET.Ma_MonHocChiTiet = CAUHOI.Ma_MonHocChiTiet\n"
            + "where MONHOC.Ma_MonHoc = ? and CauHoi.Delete_At is null";
    String thungRac = "select Ma_CauHoi,NoiDung_CauHoi,NgayTao_CauHoi,DoKho\n"
            + ",ch.Delete_At as NgayXoa,ch.Delete_User as XoaBoi\n"
            + "from Monhocchitiet mhct inner join CAUHOI ch\n"
            + "on mhct.Ma_MonHocChiTiet=ch.Ma_MonHocChiTiet \n"
            + "where ch.Delete_At is not null and mhct.TenMonHoc like ? order by ch.Delete_At desc";
    String select_MaCH = "select * from cauhoi where Ma_CauHoi like ?";
    String select_MaDe = "select dtct.Ma_CauHoi as maCH,ch.NoiDung_CauHoi as noiDung,ch.DoKho as doKho\n"
            + "from DETHICHITIET dtct inner join CAUHOI ch\n"
            + "on dtct.Ma_CauHoi=ch.Ma_CauHoi\n"
            + "where dtct.Ma_DeThi like ?";

    @Override
    public void insert(CauHoi entity, Connection con) {
        try {
            ps = con.prepareStatement(insert);
            ps.setString(1, entity.getMaCauHoi());
            ps.setString(2, entity.getMa_MonHocCT());
            ps.setString(3, entity.getNoiDung());
            ps.setDate(4, XConvert.convert(entity.getNgayTao()));
            ps.setString(5, entity.getDapAn_A());
            ps.setString(6, entity.getDapAn_B());
            ps.setString(7, entity.getDapAn_C());
            ps.setString(8, entity.getDapAn_D());
            ps.setString(9, entity.getDapAn_Dung());
            ps.setString(10, entity.getDoKho());
            ps.setString(11, entity.getHinh());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(CauHoi entity, Connection con) {
        try {
            ps = con.prepareStatement(update);

            ps.setString(1, entity.getMa_MonHocCT());
            ps.setString(2, entity.getNoiDung());
            ps.setString(3, entity.getDapAn_A());
            ps.setString(4, entity.getDapAn_B());
            ps.setString(5, entity.getDapAn_C());
            ps.setString(6, entity.getDapAn_D());
            ps.setString(7, entity.getDapAn_Dung());
            ps.setString(8, entity.getDoKho());
            ps.setString(9, entity.getHinh());
            ps.setString(10, entity.getMaCauHoi());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reStore(String ma, Connection con) {
        try {
            String khoiPhuc = "update cauhoi set Delete_At=null,Delete_User=null where Ma_CauHoi =?";
            ps = con.prepareStatement(khoiPhuc);
            ps.setString(1,ma);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(CauHoi entity, Connection con) {
        try {
            ps = con.prepareStatement(delet_Soft);
            ps.setString(1, entity.getDelete_At());
            ps.setString(2, entity.getDelete_User());
            ps.setString(3, entity.getMaCauHoi());
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CauHoi> selectAll(Connection con) {
        List<CauHoi> list = new ArrayList<>();
        try {
            stst = con.createStatement();
            rs = stst.executeQuery(select_ALL);
            while (rs.next()) {
                String maCH = rs.getString("Ma_CauHoi");
                String maMHCT = rs.getString("Ma_MonHocChiTiet");
                String noiDung = rs.getString("NoiDung_CauHoi");
                Date ngayTao = rs.getDate("NgayTao_CauHoi");
                String hinh = rs.getString("Hinh");
                String dapAn_A = rs.getString("DapAn_A");
                String dapAn_B = rs.getString("DapAn_B");
                String dapAn_C = rs.getString("DapAn_C");
                String dapAn_D = rs.getString("DapAn_D");
                String dapAn_Dung = rs.getString("DapAn_Dung");
                String doKho = rs.getString("DoKho");

                CauHoi ch = new CauHoi(maCH, maMHCT, noiDung, ngayTao, hinh, dapAn_A, dapAn_B, dapAn_C, dapAn_D, dapAn_Dung, doKho);
                list.add(ch);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<CauHoi> selectAll_IS_NULL_BY_DO_KHO(Connection con) {
        List<CauHoi> list = new ArrayList<>();
        try {
            stst = con.createStatement();
            rs = stst.executeQuery(select_BY_DE);
            while (rs.next()) {
                String maCH = rs.getString("Ma_CauHoi");
                String maMHCT = rs.getString("Ma_MonHocChiTiet");
                String noiDung = rs.getString("NoiDung_CauHoi");
                Date ngayTao = rs.getDate("NgayTao_CauHoi");
                String hinh = rs.getString("Hinh");
                String dapAn_A = rs.getString("DapAn_A");
                String dapAn_B = rs.getString("DapAn_B");
                String dapAn_C = rs.getString("DapAn_C");
                String dapAn_D = rs.getString("DapAn_D");
                String dapAn_Dung = rs.getString("DapAn_Dung");
                String doKho = rs.getString("DoKho");

                CauHoi ch = new CauHoi(maCH, maMHCT, noiDung, ngayTao, hinh, dapAn_A, dapAn_B, dapAn_C, dapAn_D, dapAn_Dung, doKho);
                list.add(ch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<CauHoi> selectAll_IS_NULL_BY_MaMH_De(String key, Connection con) {
        List<CauHoi> list = new ArrayList<>();
        try {
            ps = con.prepareStatement(select_BY_MaMH_De);
            ps.setString(1, "%" + key + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                String maCH = rs.getString("Ma_CauHoi");
                String maMHCT = rs.getString("Ma_MonHocChiTiet");
                String noiDung = rs.getString("NoiDung_CauHoi");
                Date ngayTao = rs.getDate("NgayTao_CauHoi");
                String hinh = rs.getString("Hinh");
                String dapAn_A = rs.getString("DapAn_A");
                String dapAn_B = rs.getString("DapAn_B");
                String dapAn_C = rs.getString("DapAn_C");
                String dapAn_D = rs.getString("DapAn_D");
                String dapAn_Dung = rs.getString("DapAn_Dung");
                String doKho = rs.getString("DoKho");

                CauHoi ch = new CauHoi(maCH, maMHCT, noiDung, ngayTao, hinh, dapAn_A, dapAn_B, dapAn_C, dapAn_D, dapAn_Dung, doKho);
                list.add(ch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<CauHoi> selectAll_IS_NULL_BY_MaMH_Kho(String key, Connection con) {
        List<CauHoi> list = new ArrayList<>();
        try {
            ps = con.prepareStatement(select_BY_MaMH_Kho);
            ps.setString(1, "%" + key + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                String maCH = rs.getString("Ma_CauHoi");
                String maMHCT = rs.getString("Ma_MonHocChiTiet");
                String noiDung = rs.getString("NoiDung_CauHoi");
                Date ngayTao = rs.getDate("NgayTao_CauHoi");
                String hinh = rs.getString("Hinh");
                String dapAn_A = rs.getString("DapAn_A");
                String dapAn_B = rs.getString("DapAn_B");
                String dapAn_C = rs.getString("DapAn_C");
                String dapAn_D = rs.getString("DapAn_D");
                String dapAn_Dung = rs.getString("DapAn_Dung");
                String doKho = rs.getString("DoKho");

                CauHoi ch = new CauHoi(maCH, maMHCT, noiDung, ngayTao, hinh, dapAn_A, dapAn_B, dapAn_C, dapAn_D, dapAn_Dung, doKho);
                list.add(ch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<CauHoi> selectAll_IS_NULL_BY_MaMH_TT(Connection con) {
        List<CauHoi> list = new ArrayList<>();
        try {
            stst = con.createStatement();
            rs = stst.executeQuery(select_BY_MaMH_TT);
            while (rs.next()) {
                String maCH = rs.getString("Ma_CauHoi");
                String maMHCT = rs.getString("Ma_MonHocChiTiet");
                String noiDung = rs.getString("NoiDung_CauHoi");
                Date ngayTao = rs.getDate("NgayTao_CauHoi");
                String hinh = rs.getString("Hinh");
                String dapAn_A = rs.getString("DapAn_A");
                String dapAn_B = rs.getString("DapAn_B");
                String dapAn_C = rs.getString("DapAn_C");
                String dapAn_D = rs.getString("DapAn_D");
                String dapAn_Dung = rs.getString("DapAn_Dung");
                String doKho = rs.getString("DoKho");

                CauHoi ch = new CauHoi(maCH, maMHCT, noiDung, ngayTao, hinh, dapAn_A, dapAn_B, dapAn_C, dapAn_D, dapAn_Dung, doKho);
                list.add(ch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<CauHoi> selectAll_IS_NULL(Connection con) {
        List<CauHoi> list = new ArrayList<>();
        try {
            stst = con.createStatement();
            rs = stst.executeQuery(select_IS_NULL);
            while (rs.next()) {
                String maCH = rs.getString("Ma_CauHoi");
                String maMHCT = rs.getString("Ma_MonHocChiTiet");
                String noiDung = rs.getString("NoiDung_CauHoi");
                Date ngayTao = rs.getDate("NgayTao_CauHoi");
                String hinh = rs.getString("Hinh");
                String dapAn_A = rs.getString("DapAn_A");
                String dapAn_B = rs.getString("DapAn_B");
                String dapAn_C = rs.getString("DapAn_C");
                String dapAn_D = rs.getString("DapAn_D");
                String dapAn_Dung = rs.getString("DapAn_Dung");
                String doKho = rs.getString("DoKho");

                CauHoi ch = new CauHoi(maCH, maMHCT, noiDung, ngayTao, hinh, dapAn_A, dapAn_B, dapAn_C, dapAn_D, dapAn_Dung, doKho);
                list.add(ch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<CauHoi> selectAll_BY_MaMonHoc(String key, Connection con) {
        List<CauHoi> list = new ArrayList<>();
        try {
            ps = con.prepareStatement(select_BY_MonHocCT);
            ps.setString(1, key);
            rs = ps.executeQuery();
            while (rs.next()) {
                String maCH = rs.getString("Ma_CauHoi");
                String maMHCT = rs.getString("Ma_MonHocChiTiet");
                String noiDung = rs.getString("NoiDung_CauHoi");
                Date ngayTao = rs.getDate("NgayTao_CauHoi");
                String hinh = rs.getString("Hinh");
                String dapAn_A = rs.getString("DapAn_A");
                String dapAn_B = rs.getString("DapAn_B");
                String dapAn_C = rs.getString("DapAn_C");
                String dapAn_D = rs.getString("DapAn_D");
                String dapAn_Dung = rs.getString("DapAn_Dung");
                String doKho = rs.getString("DoKho");

                CauHoi ch = new CauHoi(maCH, maMHCT, noiDung, ngayTao, hinh, dapAn_A, dapAn_B, dapAn_C, dapAn_D, dapAn_Dung, doKho);
                list.add(ch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<CauHoi> selectAll_IS_NULL_MaMobHoc(String key, Connection con) {
        List<CauHoi> list = new ArrayList<>();
        try {
            ps = con.prepareStatement(select_By_MonHoc);
            ps.setString(1, key);
            rs = ps.executeQuery();
            while (rs.next()) {
                String maCH = rs.getString("Ma_CauHoi");
                String tenMHCT = rs.getString("TenMonHoc");
                String noiDung = rs.getString("NoiDung_CauHoi");

                String hinh = rs.getString("Hinh");
                String dapAn_A = rs.getString("DapAn_A");
                String dapAn_B = rs.getString("DapAn_B");
                String dapAn_C = rs.getString("DapAn_C");
                String dapAn_D = rs.getString("DapAn_D");
                String dapAn_Dung = rs.getString("DapAn_Dung");
                String doKho = rs.getString("DoKho");

                CauHoi ch = new CauHoi(maCH, noiDung, hinh, dapAn_A, dapAn_B, dapAn_C, dapAn_D, dapAn_Dung, doKho, tenMHCT);
                list.add(ch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public CauHoi selectByID(String key, Connection con) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<CauHoi> selectThungRac(String key, Connection con) {
        List<CauHoi> list = new ArrayList<>();
        try {
            ps = con.prepareStatement(thungRac);
            ps.setString(1, "%" + key + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                String maCH = rs.getString("Ma_CauHoi");
                String noiDung = rs.getString("NoiDung_CauHoi");
                Date ngayTao = rs.getDate("NgayTao_CauHoi");
                String doKho = rs.getString("DoKho");
                String deAt = rs.getString("NgayXoa");
                String deUs = rs.getString("XoaBoi");
                CauHoi ch = new CauHoi(maCH, noiDung, ngayTao, doKho, deAt, deUs);
                list.add(ch);
//                Ma_CauHoi,NoiDung_CauHoi,NgayTao_CauHoi,DoKho,CauHoi.Delete_At,CauHoi.Delete_User
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<CauHoi> selectByMaCH(String key, Connection con) {
        List<CauHoi> list = new ArrayList<>();
        try {
            ps = con.prepareStatement(select_MaCH);
            ps.setString(1, "%" + key + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                String maCH = rs.getString("Ma_CauHoi");
                String noiDung = rs.getString("NoiDung_CauHoi");
                String doKho = rs.getString("DoKho");
                CauHoi ch = new CauHoi(maCH, noiDung, doKho);
                list.add(ch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<CauHoi> selectByMaDeThi(String key, Connection con) {
        List<CauHoi> list = new ArrayList<>();
        try {
            ps = con.prepareStatement(select_MaDe);
            ps.setString(1, "%" + key + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                String maCH = rs.getString("maCH");
                String noiDung = rs.getString("noiDung");
                String doKho = rs.getString("doKho");
                CauHoi ch = new CauHoi(maCH, noiDung, doKho);
                list.add(ch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
