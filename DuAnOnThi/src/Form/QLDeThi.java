package Form;

import DAO.CauHoiDAO;
import DAO.DeThiChiTietDAO;
import DAO.DeThiDAO;
import DAO.MonHocChiTietDAO;
import DAO.MonHocDAO;
import Entity.Auth;
import Entity.CauHoi;
import Entity.DeThi;
import Entity.DeThiChiTiet;
import Entity.GiaoVien;
import Entity.MonHoc;
import Entity.MonHocChiTiet;
import TienIch_HoTro.Connections;
import TienIch_HoTro.Dialong;
import TienIch_HoTro.XConvert;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class QLDeThi extends javax.swing.JInternalFrame {

    List<MonHoc> listMH = new ArrayList<>();
    List<DeThi> listDT = new ArrayList<>();
    Connection con;
    String doKho = "Dễ";
    int index = -1;

    public QLDeThi() {
        initComponents();
        con = new Connections().ketNotDatabase();
        Random rd = new Random();
        int a = 10000 + rd.nextInt(99999);
        txtMaDeThi.setText("DT_" + a);
        listDT = new DeThiDAO().selectAll(con);
        fillToMonHoc();
        fillToTable();
        EnableButton();
        txtMaDeThi.setEnabled(false);
    }

    void fillToMonHoc() {
        listMH = new MonHocDAO().selectAll(con);
        for (MonHoc mh : listMH) {
            String tenMonHoc = mh.getTenMonHoc();
            comboMonHoc.addItem(tenMonHoc);
        }
    }

    void fillToTable() {
        DefaultTableModel model = (DefaultTableModel) tableList.getModel();
        model.setRowCount(0);
        List<DeThi> list = new DeThiDAO().selectAll(con);
        for (int i = 0; i < list.size(); i++) {
            model.addRow(new Object[]{
                list.get(i).getMaDeThi(),
                list.get(i).getMaMonHoc(),
                list.get(i).getTenDeThi(),
                list.get(i).getThoiGianLamBai(),
                list.get(i).getTongSoCau(),
                list.get(i).getNgayTao(),
                list.get(i).getDoKho(),
                list.get(i).getMaGV()});
        }
    }

    void showDetail() {
        DeThi dt = listDT.get(index);
        txtMaDeThi.setText(dt.getMaDeThi());
        txtTenDeThi.setText(dt.getTenDeThi());
        txtThoiGian.setText(String.valueOf(dt.getThoiGianLamBai()));
        txtTongSoCau.setText(String.valueOf(dt.getTongSoCau()));
        comboDoKho.setSelectedItem(dt.getDoKho());
        String mamonhoc = dt.getMaMonHoc();
        String tenMonHoc = null;
        for (int i = 0; i < listMH.size(); i++) {
            MonHoc itemMonHoc = listMH.get(i);
            if (itemMonHoc.getMaMonHoc().equals(mamonhoc)) {
                tenMonHoc = itemMonHoc.getTenMonHoc();
            }
        }

        comboMonHoc.setSelectedItem(tenMonHoc);
        txtMaDeThi.setEnabled(false);
        txtTongSoCau.setEnabled(false);
        txtTongCaude.setEnabled(false);
        txtTongCauKho.setEnabled(false);
        txtThoiGian.setEnabled(false);
        //show len table
        DefaultTableModel modelTable = (DefaultTableModel) tableListTam.getModel();
        modelTable.setRowCount(0);

        CauHoiDAO chDao = new CauHoiDAO();
        List<CauHoi> listCH = chDao.selectByMaDeThi(tableList.getValueAt(index, 0) + "", con);
        for (CauHoi ch : listCH) {
            modelTable.addRow(new Object[]{
                modelTable.getRowCount() + 1,
                ch.getMaCauHoi(),
                ch.getNoiDung(),
                ch.getDoKho()
            });
//            System.out.println("fillTable Ok");
        }
        int row = tableListTam.getRowCount();
        int k = 0, d = 0;
        for (int i = 0; i < row; i++) {
            if (tableListTam.getValueAt(i, 3).equals("Khó")) {
                k++;
            } else if (tableListTam.getValueAt(i, 3).equals("Dễ")) {
                d++;
            }

        }
//        System.out.println("tsCauDe"+d+"    TongSoCauKho"+k);
        txtTongCauKho.setText(k + "");
        txtTongCaude.setText(d + "");

    }

    boolean checkNull() {
        if (txtTongSoCau.getText().equals("")) {
            Dialong.alert(this, "Tổng số câu không để trống !");
            txtTongSoCau.requestFocus();
            return false;
        } else {
            try {
                int so = Integer.parseInt(txtTongSoCau.getText());
                if (so < 0) {
                    Dialong.alert(this, "Là số nguyên dương !");
                    txtTongSoCau.requestFocus();
                    return false;
                }
            } catch (Exception e) {
                Dialong.alert(this, "Phải là kiểu số !");
                txtTongSoCau.requestFocus();
                return false;
            }
        }
        if (txtTongCaude.getText().equals("")) {
            Dialong.alert(this, "Tổng số câu dễ không để trống !");
            txtTongCaude.requestFocus();
            return false;
        } else {
            try {
                int so = Integer.parseInt(txtTongCaude.getText());
                if (so < 0) {
                    Dialong.alert(this, "Là số nguyên dương !");
                    txtTongCaude.requestFocus();
                    return false;
                }
            } catch (Exception e) {
                Dialong.alert(this, "Phải là kiểu số !");
                txtTongCaude.requestFocus();
                return false;
            }
        }

        if (txtTenDeThi.getText().equals("")) {
            Dialong.alert(this, "Tên đề thi không được để trống !");
            txtTenDeThi.requestFocus();
            return false;
        }

        if (txtThoiGian.getText().equals("")) {
            Dialong.alert(this, "Thời gian không để trống !");
            txtThoiGian.requestFocus();
            return false;
        } else {
            try {
                int so = Integer.parseInt(txtThoiGian.getText());
                if (so < 0) {
                    Dialong.alert(this, "Là số nguyên dương !");
                    txtThoiGian.requestFocus();
                    return false;
                }
            } catch (Exception e) {
                Dialong.alert(this, "Phải là kiểu số !");
                txtThoiGian.requestFocus();
                return false;
            }
        }

        if (txtTongCauKho.getText().equals("")) {
            Dialong.alert(this, "Tổng số câu khó không để trống !");
            txtTongCauKho.requestFocus();
            return false;
        } else {
            try {
                int so = Integer.parseInt(txtTongCauKho.getText());
                if (so < 0) {
                    Dialong.alert(this, "Là số nguyên dương !");
                    txtTongCauKho.requestFocus();
                    return false;
                }
            } catch (Exception e) {
                Dialong.alert(this, "Phải là kiểu số !");
                txtTongCauKho.requestFocus();
                return false;
            }
        }

        return true;
    }

    boolean checkTaoDe() {
        
        //tongSoCau
        if (txtTongSoCau.getText().equals("")) {
            Dialong.alert(this, "Tổng số câu không để trống !");
            txtTongSoCau.requestFocus();
            return false;
        } else {
            try {
               int tongSoCau = Integer.parseInt(txtTongSoCau.getText());
                if (tongSoCau < 0) {
                    Dialong.alert(this, "Là số nguyên dương !");
                    txtTongSoCau.requestFocus();
                    return false;
                }
            } catch (Exception e) {
                Dialong.alert(this, "Phải là kiểu số !");
                txtTongSoCau.requestFocus();
                return false;
            }
        }
        int tongSoCau = Integer.parseInt(txtTongSoCau.getText());
        
        //cauDe
        if (txtTongCaude.getText().equals("")) {
            Dialong.alert(this, "Tổng số câu dễ không để trống !");
            txtTongCaude.requestFocus();
            return false;
        } else {
            try {
                int cauDe = Integer.parseInt(txtTongCaude.getText());
                if (cauDe < 0) {
                    Dialong.alert(this, "Là số nguyên dương !");
                    txtTongCaude.requestFocus();
                    return false;
                }
                if (cauDe >= tongSoCau) {
                    Dialong.alert(this, "Tổng số câu dễ nhỏ hơn tổng số câu !");
                    txtTongCaude.requestFocus();
                    return false;
                }

            } catch (Exception e) {
                Dialong.alert(this, "Phải là kiểu số !");
                txtTongCaude.requestFocus();
                return false;
            }
        }
        int cauDe = Integer.parseInt(txtTongCaude.getText());
        
        //tenDeThi
        if (txtTenDeThi.getText().equals("")) {
            Dialong.alert(this, "Tên đề thi không được để trống !");
            txtTenDeThi.requestFocus();
            return false;
        }
        //thoiGian
        if (txtThoiGian.getText().equals("")) {
            Dialong.alert(this, "Thời gian không để trống !");
            txtThoiGian.requestFocus();
            return false;
        } else {
            try {
                int so = Integer.parseInt(txtThoiGian.getText());
                if (so < 0) {
                    Dialong.alert(this, "Là số nguyên dương !");
                    txtThoiGian.requestFocus();
                    return false;
                }
            } catch (Exception e) {
                Dialong.alert(this, "Phải là kiểu số !");
                txtThoiGian.requestFocus();
                return false;
            }
        }
        //cauKho
        if (txtTongCauKho.getText().equals("")) {
            Dialong.alert(this, "Tổng số câu khó không để trống !");
            txtTongCauKho.requestFocus();
            return false;
        } else {
            try {
                int cauKho = Integer.parseInt(txtTongCauKho.getText());
                if (cauKho < 0) {
                    Dialong.alert(this, "Là số nguyên dương !");
                    txtTongCauKho.requestFocus();
                    return false;
                }

                if (cauKho >= tongSoCau) {
                    Dialong.alert(this, "Tổng số câu khó nhỏ hơn tổng số câu !");
                    txtTongCauKho.requestFocus();
                    return false;
                }

            } catch (Exception e) {
                Dialong.alert(this, "Phải là kiểu số !");
                txtTongCauKho.requestFocus();
                return false;
            }
        }
        int cauKho = Integer.parseInt(txtTongCauKho.getText());
        
        int tongNew = cauKho + cauDe;
        if (tongSoCau != tongNew) {
            Dialong.alert(this, "Tổng số câu dễ và khó chưa hợp lệ !");
            return false;
        }

        return true;
    }

    void clear() {
        Random rd = new Random();
        int a = 10000 + rd.nextInt(99999);
        txtMaDeThi.setText("DT_" + a);
        txtTongSoCau.setText("");
        txtTongCaude.setText("");
        txtTongCauKho.setText("");
        txtTenDeThi.setText("");
        txtThoiGian.setText("");


        txtTongSoCau.setEnabled(true);
        txtTongCaude.setEnabled(true);
        txtTongCauKho.setEnabled(true);
        txtThoiGian.setEnabled(true);
        comboMonHoc.setEnabled(true);
        comboDoKho.setEnabled(true);
         btnTaoDe.setEnabled(true);
         EnableButton();
        DefaultTableModel modelTable = (DefaultTableModel) tableListTam.getModel();
        modelTable.setRowCount(0);
        list_Gop.clear();
        listMaDeThiChiTiet.clear();
    }

    void random() {
        //List<MonHoc> list = new MonHocDAO().selectAll(con);
        List<CauHoi> list = new CauHoiDAO().selectAll_IS_NULL_BY_DO_KHO(con);
        Collections.shuffle(list);
        System.out.println("hello list : " + list.toString());

        List<String> targetListMonHoc = new ArrayList<>();
        List<String> targetListMa_MonHoc = new ArrayList<>();
        for (int j = 0; j < list.size(); j++) {
            if (!targetListMonHoc.contains(list.get(j).getMa_MonHocCT())) {
                targetListMonHoc.add(list.get(j).getMa_MonHocCT());
                targetListMa_MonHoc.add(list.get(j).getMaCauHoi());
//                System.out.println("ma cau hoi : " + targetListMa_MonHoc.get(j).toString()
//                        + "\t Ma mon hoc :" + targetListMonHoc.get(j).toString());
            }
        }

        System.out.println("mon hoc : " + targetListMonHoc.toString() + "\t Ma mon hoc :" + targetListMa_MonHoc.toString());
    }

    void danhSach_By_Ma_MonHoc_De(String maMonHocs, int soCauDe) {
        List<MonHocChiTiet> list_MonHocCT = new MonHocChiTietDAO().selectAll_By_MaMonHoc(maMonHocs, con);
        List<CauHoi> list_CauHoi = new ArrayList<>();
        List<String> list_CauHoi_RanDom = new ArrayList<>();
        List<String> list_RanDom = new ArrayList<>();
        List<String> list_Sau_Random = new ArrayList<>();

//        Random rd = new Random();
//        int a = 10000 + rd.nextInt(99999);
        int index;
        for (int i = 0; i < list_MonHocCT.size(); i++) {
            if (list_MonHocCT.get(i).getMaMonHocCT().equals("TOAN_TT")) {
                index = i;
                list_MonHocCT.remove(index);
            }
        }

        int phanNg = soCauDe / list_MonHocCT.size();
        int phanDu = soCauDe % list_MonHocCT.size();
        System.out.println("phần nguyên : " + phanNg);
        System.out.println("phần dư : " + phanDu);
        String ma;
        for (MonHocChiTiet mhct : list_MonHocCT) {
            ma = mhct.getMaMonHocCT();
//          
            list_CauHoi = new CauHoiDAO().selectAll_IS_NULL_BY_MaMH_De(ma, con);
//        

            if (list_CauHoi.size() < 3) {
                Dialong.alert(this, "Chuyên đề :" + mhct.getTenMonHc() + " Không đủ số câu hỏi để trộn đề ! Thêm câu hỏi !!!");
                return;
            } else {
                Collections.shuffle(list_CauHoi);
                for (int i = 0; i < list_CauHoi.size(); i++) {
                    System.out.println("radom : " + list_CauHoi.get(i).getMaCauHoi());
                    list_CauHoi_RanDom.add(list_CauHoi.get(i).getMaCauHoi());

                }

                Collections.shuffle(list_CauHoi_RanDom);
                for (int j = 0; j < phanNg; j++) {
                    // list_DECT.add("DTCT_" + a);
                    list_RanDom.add(list_CauHoi_RanDom.get(j).toString());
                    list_Gop.add(list_CauHoi_RanDom.get(j).toString());
                    list_CauHoi_RanDom.remove(list_CauHoi_RanDom.get(j));

                }
                for (String trues : list_CauHoi_RanDom) {
                    list_Sau_Random.add(trues);
                }

                System.out.println("list DECT : " + list_CauHoi_RanDom.size() + "\t de thi chi tiet : " + list_CauHoi_RanDom.toString());
                list_CauHoi_RanDom.clear();

            }
        }
        List<String> listDu = new ArrayList<>();
        Collections.shuffle(list_Sau_Random);
        for (int i = 0; i < phanDu; i++) {
            listDu.add(list_Sau_Random.get(i).toString());
            list_Gop.add(list_Sau_Random.get(i).toString());
        }

        System.out.println("list lấy random dư : " + listDu.size() + "\t gia trị : " + listDu.toString());

        System.out.println("list random : " + list_RanDom.size() + "\t gia tri : " + list_RanDom.toString());

        System.out.println("list theo mon hoc : " + list_MonHocCT.size());
        System.out.println("list theo mon hoc : " + list_Sau_Random.size());
        System.out.println("list DECT : " + list_Sau_Random.size() + "\t de thi chi tiet : " + list_Sau_Random.toString());

        list_MonHocCT.clear();

    }
    List<String> list_Gop = new ArrayList<>();

    void danhSach_By_Ma_MonHoc_Kho(String maMonHocs, int soCauKho) {
        List<MonHocChiTiet> list_MonHocCT = new MonHocChiTietDAO().selectAll_By_MaMonHoc(maMonHocs, con);
        List<CauHoi> list_CauHoi = new ArrayList<>();
        List<String> list_CauHoi_RanDom = new ArrayList<>();
        List<String> list_RanDom = new ArrayList<>();
        List<String> list_Sau_Random = new ArrayList<>();

//        Random rd = new Random();
//        int a = 10000 + rd.nextInt(99999);
        int index;
        for (int i = 0; i < list_MonHocCT.size(); i++) {
            if (list_MonHocCT.get(i).getMaMonHocCT().equals("TOAN_TT")) {
                index = i;
                list_MonHocCT.remove(index);
            }
        }

        int phanNg = (soCauKho/* - 1*/) / list_MonHocCT.size();
        int phanDu = (soCauKho/* - 1*/) % list_MonHocCT.size();
        System.out.println("phần nguyên : " + phanNg);
        System.out.println("phần dư : " + phanDu);
        String ma;
        for (MonHocChiTiet mhct : list_MonHocCT) {
            ma = mhct.getMaMonHocCT();
//          
            list_CauHoi = new CauHoiDAO().selectAll_IS_NULL_BY_MaMH_Kho(ma, con);
            if (list_CauHoi.size() < 3) {
                Dialong.alert(this, "Chuyên đề :" + mhct.getTenMonHc() + " Không đủ số câu hỏi để trộn đề ! Thêm câu hỏi !!!");
                return;
            } else {
                Collections.shuffle(list_CauHoi);
                for (int i = 0; i < list_CauHoi.size(); i++) {
                    System.out.println("radom : " + list_CauHoi.get(i).getMaCauHoi());
                    list_CauHoi_RanDom.add(list_CauHoi.get(i).getMaCauHoi());

                }

                Collections.shuffle(list_CauHoi_RanDom);
                for (int j = 0; j < phanNg; j++) {
                    list_RanDom.add(list_CauHoi_RanDom.get(j).toString());
                    list_Gop.add(list_CauHoi_RanDom.get(j).toString());
                    list_CauHoi_RanDom.remove(list_CauHoi_RanDom.get(j));

                }
                for (String trues : list_CauHoi_RanDom) {
                    list_Sau_Random.add(trues);
                }

                System.out.println("list DECT : " + list_CauHoi_RanDom.size() + "\t de thi chi tiet : " + list_CauHoi_RanDom.toString());
                list_CauHoi_RanDom.clear();

            }
        }
        List<String> listDu = new ArrayList<>();
        Collections.shuffle(list_Sau_Random);
        if (phanDu == 0) {
            return;
        } else {
            for (int i = 0; i < phanDu; i++) {
                listDu.add(list_Sau_Random.get(i).toString());
                list_Gop.add(list_Sau_Random.get(i).toString());
            }
        }

        System.out.println("list lấy random dư : " + listDu.size() + "\t gia trị : " + listDu.toString());

        System.out.println("list random : " + list_RanDom.size() + "\t gia tri : " + list_RanDom.toString());

        System.out.println("list theo mon hoc : " + list_MonHocCT.size());
        System.out.println("list theo mon hoc : " + list_Sau_Random.size());
        System.out.println("list DECT : " + list_Sau_Random.size() + "\t de thi chi tiet : " + list_Sau_Random.toString());

        list_MonHocCT.clear();

    }

    void danh_Sach_By_TT() {
        List<CauHoi> list = new CauHoiDAO().selectAll_IS_NULL_BY_MaMH_TT(con);
        System.out.println("list thực tế : " + list.size());

        Collections.shuffle(list);
        for (int i = 0; i < 1; i++) {
            list_Gop.add(list.get(i).getMaCauHoi());
        }

    }

    DeThi setInsert_DT() {
        DeThi dt = new DeThi();
        dt.setMaDeThi(txtMaDeThi.getText());
        dt.setMaMonHoc(maMonHoc);
        dt.setTenDeThi(txtTenDeThi.getText());
        dt.setNgayTao(XConvert.now());
        dt.setTongSoCau(Integer.parseInt(txtTongSoCau.getText()));
        dt.setThoiGianLamBai(Integer.parseInt(txtThoiGian.getText()));
        dt.setDoKho(doKho);
        dt.setMaGV(Auth.user.getMaDN());
        return dt;
    }

    DeThi setUpdate_DT() {
        DeThi dt = new DeThi();
        dt.setMaDeThi(txtMaDeThi.getText());
        dt.setTenDeThi(txtTenDeThi.getText());
        dt.setDoKho(doKho);
        return dt;
    }

    DeThi setDeLete_DT() {
        DeThi dt = new DeThi();
        dt.setMaDeThi(txtMaDeThi.getText());
        String delete_At = String.valueOf(java.time.LocalDate.now() + " " + java.time.LocalTime.now());
        dt.setDelete_At(delete_At);
        dt.setDelete_User(Auth.user.getMaDN());
        return dt;
    }
    
    void setMaMon(String maMonHocs, int caude, int caukho) {
        String maMonHOcSet = "Toan";

        if (maMonHocs.equals(maMonHOcSet)) {
            System.out.println(maMonHocs);
            caude = Integer.parseInt(txtTongCaude.getText());
            caukho = Integer.parseInt(txtTongCauKho.getText()) - 1;
            danhSach_By_Ma_MonHoc_De(maMonHocs, caude);
            danhSach_By_Ma_MonHoc_Kho(maMonHocs, caukho);
            danh_Sach_By_TT();
            System.out.println("caude: " + caude + "\t  caukho :" + caukho);

            return;
        } else {
            caude = Integer.parseInt(txtTongCaude.getText());
            caukho = Integer.parseInt(txtTongCauKho.getText());
            danhSach_By_Ma_MonHoc_De(maMonHocs, caude);
            danhSach_By_Ma_MonHoc_Kho(maMonHocs, caukho);
            System.out.println("caude: " + caude + "\t  caukho :" + caukho);

            System.out.println(maMonHocs);
            return;

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableList = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaDeThi = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        comboMonHoc = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtTongSoCau = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTongCaude = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTenDeThi = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        comboDoKho = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtThoiGian = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        txtTongCauKho = new javax.swing.JTextField();
        btnTaoDe = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableListTam = new javax.swing.JTable();

        setBackground(new java.awt.Color(204, 255, 204));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Quản Lý Đề Thi");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Quản Lý Đề Thi");

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(153, 255, 204));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Tìm kiếm theo tên");

        txtSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        tableList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã đề thi", "Môn ", "Tên Đề thi", "Thời gian", "Số câu", "Ngày Tạo", "Độ khó", "Mã giáo viên"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableList);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(138, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Danh sách", jPanel2);

        jPanel1.setBackground(new java.awt.Color(153, 255, 204));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Mã đề thi ");

        txtMaDeThi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Môn học");

        comboMonHoc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboMonHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMonHocActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Tổng số câu");

        txtTongSoCau.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Tổng câu dễ");

        txtTongCaude.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTongCaude.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTongCaudeKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Tên đề thi ");

        txtTenDeThi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Độ khó");

        comboDoKho.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboDoKho.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dễ", "Khó" }));
        comboDoKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboDoKhoActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Thời gian( phút )");

        txtThoiGian.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Tổng câu khó");

        btnThem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Add.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Edit.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Delete.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnMoi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/new.png"))); // NOI18N
        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnFirst.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnFirst.setText("|<");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPrev.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPrev.setText("<<");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnNext.setText(">>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLast.setText(">|");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        txtTongCauKho.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTongCauKho.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTongCauKhoKeyReleased(evt);
            }
        });

        btnTaoDe.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnTaoDe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Create.png"))); // NOI18N
        btnTaoDe.setText("Tạo Đề");
        btnTaoDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoDeActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Danh sách câu hỏi ");

        tableListTam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Câu Hỏi", "Nội Dung Câu Hỏi", "Độ Khó"
            }
        ));
        jScrollPane2.setViewportView(tableListTam);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaDeThi)
                            .addComponent(comboMonHoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTongSoCau)
                            .addComponent(txtTongCaude)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThem)
                        .addGap(18, 18, 18)
                        .addComponent(btnSua)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa)
                        .addGap(18, 18, 18)
                        .addComponent(btnMoi)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(31, 31, 31))
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btnTaoDe)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnFirst)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrev)
                        .addGap(12, 12, 12)
                        .addComponent(btnNext)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLast))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTongCauKho, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(comboDoKho, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTenDeThi, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(txtThoiGian, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(156, 156, 156))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel11))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 846, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 902, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnMoi, btnSua, btnThem, btnXoa});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(comboMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtTenDeThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtMaDeThi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(comboDoKho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTongSoCau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTongCaude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtTongCauKho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnMoi)
                    .addComponent(btnTaoDe)
                    .addComponent(btnFirst)
                    .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNext)
                    .addComponent(btnLast))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnFirst, btnLast, btnNext, btnPrev});

        jTabbedPane1.addTab("Cập nhật", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 903, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(366, 366, 366)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed

        try {
            if (checkNull()) {
                DeThi deThi = setInsert_DT();
                new DeThiDAO().insert(deThi, con);
                Random rngg = new Random();
                for (int i = 0; i < list_Gop.size(); i++) {
                    int ab = 10000 + rngg.nextInt(999999);
                    System.out.println("aaa : " + ab);
                    if (listMaDeThiChiTiet.contains(ab)) {
                        int abc = 10000 + rngg.nextInt(999999);
                        listMaDeThiChiTiet.add("DTCT_" + abc);
                        System.out.println("đã trùng !!!" + ab);
                        DeThiChiTiet dect = new DeThiChiTiet("DTCT_" + abc, txtMaDeThi.getText(), list_Gop.get(i).toString(), "True");
                        new DeThiChiTietDAO().insert(dect, con);

                    } else {
                        listMaDeThiChiTiet.add("DTCT_" + ab);
                        DeThiChiTiet dect = new DeThiChiTiet("DTCT_" + ab, txtMaDeThi.getText(), list_Gop.get(i).toString(), "True");
                        new DeThiChiTietDAO().insert(dect, con);
                    }

                }
                fillToTable();
                Dialong.alert(this, "Thêm thành công !!!");
                ActiveButton();
                list_Gop.clear();
            }
        } catch (Exception e) {
            Dialong.alert(this, "Thêm thất bại !!!");
            e.printStackTrace();
            return;
        }

    }//GEN-LAST:event_btnThemActionPerformed
    List<String> listMaDeThiChiTiet = new ArrayList<>();

//    void testSinhSo(int tongSoCau) {
//
//        Random rngg = new Random();
//        for (int i = 0; i < tongSoCau; i++) {
//            int ab = 10000 + rngg.nextInt(999999);
//            System.out.println("aaa : " + ab);
//            if (listMaDeThiChiTiet.contains(ab)) {
//                int abc = 10000 + rngg.nextInt(999999);
//                listMaDeThiChiTiet.add("DTCT_" + abc);
//                System.out.println("đã trùng !!!" + ab);
//            } else {
//                listMaDeThiChiTiet.add("DTCT_" + ab);
//            }
//        }
//        System.out.println("size : " + listMaDeThiChiTiet.size() + "\t gia tri : " + listMaDeThiChiTiet.toString());
//        System.out.println("-----------------");
//
//    }

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        clear();
        


    }//GEN-LAST:event_btnMoiActionPerformed
    String maMonHoc;
    private void comboMonHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMonHocActionPerformed
        String tenMonHoc = comboMonHoc.getSelectedItem().toString();

        for (int i = 0; i < listMH.size(); i++) {
            MonHoc itemMonHoc = listMH.get(i);
            if (itemMonHoc.getTenMonHoc().equals(tenMonHoc)) {
                maMonHoc = itemMonHoc.getMaMonHoc();

            }
        }

    }//GEN-LAST:event_comboMonHocActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        try {
            if (txtTenDeThi.getText().equals("")) {
                Dialong.alert(this, "Tên đề thi không để trống !");
                txtTenDeThi.requestFocus();
                return;
            } else {
                DeThi dt = setUpdate_DT();
                new DeThiDAO().update(dt, con);
                Dialong.alert(this, "Update thành công !");
                fillToTable();
            }
        } catch (Exception e) {
            Dialong.alert(this, "Update thành công !");
            e.printStackTrace();
            return;

        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
//        danh_Sach_By_TT();
        try {
            int chon=JOptionPane.showConfirmDialog(this,"Bạn có muốn xóa đề thi này không ?");
            if(chon!=JOptionPane.YES_OPTION){ return;}
            DeThi dt = setDeLete_DT();
            new DeThiDAO().delete(dt, con);
            Dialong.alert(this, "Xóa thành công !");
            fillToTable();
            clear();
        } catch (Exception e) {
            Dialong.alert(this, "Xóa thất bại !");
            e.printStackTrace();
            return;

        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtTongCaudeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTongCaudeKeyReleased
//        try {
//            if (txtTongSoCau.getText().equals("")) {
//                Dialong.alert(this, "Tổng số câu chưa nhâp ! mời nhập ");
//                txtTongSoCau.requestFocus();
//                return;
//
//            } else {
//                if (txtTongCaude.getText().equals("")) {
//                    return;
//                } else {
//                    int tongSoCau = Integer.parseInt(txtTongSoCau.getText());
//                    int tongcauDe = Integer.parseInt(txtTongCaude.getText());
//                    if (tongcauDe < 21 || tongcauDe > 29) {
//                        Dialong.alert(this, "Tổng số câu dễ từ 21 --> 29 !");
//                        txtTongCaude.requestFocus();
//                        return;
//                    } else {
//                        txtTongCauKho.setText(tongSoCau - tongcauDe + "");
//                    }
//                }
//            }
//        } catch (Exception e) {
//            Dialong.alert(this, "Tổng số câu dễ Phải là số nguyên dương !");
//            txtTongCaude.requestFocus();
//            return;
//        }
    }//GEN-LAST:event_txtTongCaudeKeyReleased

    private void txtTongCauKhoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTongCauKhoKeyReleased
//        try {
//            if (txtTongSoCau.getText().equals("")) {
//                Dialong.alert(this, "Tổng số câu chưa nhâp ! mời nhập ");
//                txtTongSoCau.requestFocus();
//                return;
//
//            } else {
//                if (txtTongCauKho.getText().equals("")) {
//                    return;
//                } else {
//
//                    int tongSoCau = Integer.parseInt(txtTongSoCau.getText());
//                    int tongcauKho = Integer.parseInt(txtTongCauKho.getText());
//                    if (tongcauKho < 21 || tongcauKho > 29) {
//                        Dialong.alert(this, "Tổng số câu khó từ 21 --> 29 !");
//                        txtTongCauKho.requestFocus();
//                        return;
//                    }
//
//                    txtTongCaude.setText(tongSoCau - tongcauKho + "");
//                }
//            }
//        } catch (Exception e) {
//            Dialong.alert(this, "Tổng số câu khó Phải là số nguyên dương !");
//            txtTongCauKho.requestFocus();
//            return;
//
//        }
    }//GEN-LAST:event_txtTongCauKhoKeyReleased

    private void btnTaoDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoDeActionPerformed
        
        DefaultTableModel modelTable = (DefaultTableModel) tableListTam.getModel();
        modelTable.setRowCount(0);
        list_Gop.clear();
        listMaDeThiChiTiet.clear();
        if (checkTaoDe()) {
            btnThem.setEnabled(true);
            try {
                int soCauDe = Integer.parseInt(txtTongCaude.getText());
                //int tongSoCau = Integer.parseInt(txtTongSoCau.getText());
                int soCauKho = Integer.parseInt(txtTongCauKho.getText());
//                danhSach_By_Ma_MonHoc_De(maMonHoc, soCauDe);
//                danhSach_By_Ma_MonHoc_Kho(maMonHoc, soCauKho);
//                //testSinhSo(tongSoCau);
//                danh_Sach_By_TT();
                setMaMon(maMonHoc, soCauDe, soCauKho);
                System.out.println("mã môn học : " + maMonHoc);
                System.out.println("List gộp : " + list_Gop.size());
                System.out.println("Gía trị : " + list_Gop.toString());
                Random rngg = new Random();
                for (int j = 0; j < list_Gop.size(); j++) {

                    int ab = 10000 + rngg.nextInt(999999);
                    System.out.println("aaa : " + ab);
                    if (listMaDeThiChiTiet.contains(ab)) {
                        int abc = 10000 + rngg.nextInt(999999);
                        listMaDeThiChiTiet.add("DTCT_" + abc);
                        System.out.println("đã trùng !!!" + ab);
                    } else {
                        listMaDeThiChiTiet.add("DTCT_" + ab);
                    }
                    CauHoiDAO chDao = new CauHoiDAO();
                    List<CauHoi> listCH = chDao.selectByMaCH(list_Gop.get(j).toString(), con);
                    for (CauHoi ch : listCH) {
                        modelTable.addRow(new Object[]{
                            modelTable.getRowCount() + 1,
                            ch.getMaCauHoi(),
                            ch.getNoiDung(),
                            ch.getDoKho()
                        });
//                        System.out.println("TestCH:" + ch.getMaCauHoi());
                    }
                }

                System.out.println("list ma đề thi chi tiết" + listMaDeThiChiTiet.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }//GEN-LAST:event_btnTaoDeActionPerformed

    private void comboDoKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboDoKhoActionPerformed
        String doKhos = comboDoKho.getSelectedItem().toString();
        doKho = doKhos;
        System.out.println("độ khó : " + doKho);
    }//GEN-LAST:event_comboDoKhoActionPerformed

    private void tableListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableListMouseClicked

        if (evt.getClickCount() == 2) {
            index = tableList.getSelectedRow();
            listDT = new DeThiDAO().selectAll(con);
            jTabbedPane1.setSelectedIndex(1);
            showDetail();
            ActiveButton();
        }
    }//GEN-LAST:event_tableListMouseClicked

    void first() {
        index = 0;
        showDetail();
    }

    void prev() {
        if (index == listDT.size() - 1) {
            index = 0;

            showDetail();
        } else if (index < listDT.size()) {
            index++;

            showDetail();
        }
    }

    void next() {
        if (index > 0) {
            index--;

            showDetail();
        } else if (index == 0) {
            index = listDT.size() - 1;

            showDetail();
        }
    }

    void last() {
        index = listDT.size() - 1;
        showDetail();
    }
    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        first();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        next();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        prev();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        last();
    }//GEN-LAST:event_btnLastActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        try {
            String ten = txtSearch.getText();
            List<DeThi> list = new DeThiDAO().select_ByName(ten, con);
            DefaultTableModel model = (DefaultTableModel) tableList.getModel();
            model.setRowCount(0);
            System.out.println("list search : " + list.size());
            for (int i = 0; i < list.size(); i++) {
                model.addRow(new Object[]{
                    list.get(i).getMaDeThi(),
                    list.get(i).getMaMonHoc(),
                    list.get(i).getTenDeThi(),
                    list.get(i).getThoiGianLamBai(),
                    list.get(i).getTongSoCau(),
                    list.get(i).getNgayTao(),
                    list.get(i).getDoKho(),
                    list.get(i).getMaGV()});
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

    }//GEN-LAST:event_txtSearchKeyReleased
    void EnableButton() {
        btnXoa.setEnabled(false);
        btnSua.setEnabled(false);
        btnThem.setEnabled(false);
        btnFirst.setEnabled(false);
        btnPrev.setEnabled(false);
        btnLast.setEnabled(false);
        btnNext.setEnabled(false);
       
    }

    void ActiveButton() {
        btnXoa.setEnabled(true);
        btnSua.setEnabled(true);
        btnThem.setEnabled(false);
        btnTaoDe.setEnabled(false);
        btnFirst.setEnabled(true);
        btnPrev.setEnabled(true);
        btnLast.setEnabled(true);
        btnNext.setEnabled(true);
        txtMaDeThi.setEnabled(false);
        txtTongSoCau.setEnabled(false);
        txtTongCaude.setEnabled(false);
        txtTongCauKho.setEnabled(false);
        txtThoiGian.setEnabled(false);
        comboMonHoc.setEnabled(false);
        comboDoKho.setEnabled(false);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTaoDe;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> comboDoKho;
    private javax.swing.JComboBox<String> comboMonHoc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tableList;
    private javax.swing.JTable tableListTam;
    private javax.swing.JTextField txtMaDeThi;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTenDeThi;
    private javax.swing.JTextField txtThoiGian;
    private javax.swing.JTextField txtTongCauKho;
    private javax.swing.JTextField txtTongCaude;
    private javax.swing.JTextField txtTongSoCau;
    // End of variables declaration//GEN-END:variables
}
