package Form;

import DAO.CauHoiDAO;
import DAO.MonHocChiTietDAO;
import DAO.MonHocDAO;
import Entity.Auth;
import Entity.CauHoi;
import Entity.MonHoc;
import Entity.MonHocChiTiet;
import TienIch_HoTro.Connections;
import TienIch_HoTro.Dialong;
import TienIch_HoTro.XConvert;
import java.awt.Image;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class QLCauHoi extends javax.swing.JInternalFrame {

    Connection con;
    List<MonHoc> listMH = new ArrayList<>();
    List<MonHocChiTiet> listMHCT = new ArrayList<>();
    List<CauHoi> listCH = new ArrayList<>();
    String fileNameImage;
    int index;
    List<MonHocChiTiet> list = new ArrayList<>();
    //DefaultTableModel model;

    public QLCauHoi() {
        initComponents();
        con = new Connections().ketNotDatabase();
        fillToMonHoc();
        Random rd = new Random();
        int a = 10000 + rd.nextInt(99999);
        txtMaCauHoi.setText("CH_" + a);
        cboDoKho = comboDoKho.getSelectedItem().toString();
        System.out.println("hihi : " + cboDoKho);
        listCH = new CauHoiDAO().selectAll_IS_NULL(con);
        list = new MonHocChiTietDAO().selectAll(con);
        listMH = new MonHocDAO().selectAll(con);
        txtMaCauHoi.setEnabled(false);
    }

    void fillToMonHoc() {
        listMH = new MonHocDAO().selectAll(con);
        for (MonHoc mh : listMH) {
            String tenMonHoc = mh.getTenMonHoc();
            comboMonHoc.addItem(tenMonHoc);
        }
    }

    void findToMonHocCT(String key) {
        comboMonHocCT.removeAllItems();
        cboDanhDachMonHoc.removeAllItems();
        listMHCT = new MonHocChiTietDAO().selectAll_By_MaMonHoc(key, con);
        for (MonHocChiTiet mhct : listMHCT) {
            String tenMonHoc = mhct.getTenMonHc();
            comboMonHocCT.addItem(tenMonHoc);
            cboDanhDachMonHoc.addItem(tenMonHoc);
        }
        listMHCT.clear();
    }

    void findToDSMonHocCT(String key) {
        cboDanhDachMonHoc.removeAllItems();
        listMHCT = new MonHocChiTietDAO().selectAll_By_MaMonHoc(key, con);
        for (MonHocChiTiet mhct : listMHCT) {
            String tenMonHoc = mhct.getTenMonHc();
            cboDanhDachMonHoc.addItem(tenMonHoc);
        }
        listMHCT.clear();
    }

    void showDetail(CauHoi ch, List<MonHocChiTiet> list) {

        txtNoiDung.setText(ch.getNoiDung());
        txtDA_A.setText(ch.getDapAn_A());
        txtDA_B.setText(ch.getDapAn_B());
        txtDA_C.setText(ch.getDapAn_C());
        txtDA_D.setText(ch.getDapAn_D());
        txtDA_Dung.setText(ch.getDapAn_Dung());
        txtMaCauHoi.setText(ch.getMaCauHoi());
        updateImage(ch.getHinh());
        String maCH = ch.getDoKho();
        String name = null;
        for (int i = 0; i < listCH.size(); i++) {
            CauHoi itemCH = listCH.get(i);
            if (itemCH.getDoKho().equals(maCH)) {
                name = itemCH.getDoKho();
                 comboDoKho.setSelectedItem(name);
                 break;
            }
        }

       
        try {

            String tenMonHocCT = null;
//            String maMonHocs = null;
            String tenmonhocChiTiet = ch.getMa_MonHocCT();
            for (int i = 0; i < list.size(); i++) {
                MonHocChiTiet itemMHCT = list.get(i);
                if (itemMHCT.getMaMonHocCT().equals(tenmonhocChiTiet)) {
                    tenMonHocCT = itemMHCT.getTenMonHc();
//                    maMonHocs = itemMHCT.getMa_MonHoc();
                    comboMonHocCT.setSelectedItem(tenMonHocCT);
                    break;
                }
            }

            
            System.out.println("tên môn học chi tiết TRUE : " + tenMonHocCT);
            System.out.println("tên môn học chi tiết : " + tenmonhocChiTiet);
//            System.out.println("mã môn học  : " + maMonHocs);
//            String tenMh = null;
//            for (int i = 0; i < listMH.size(); i++) {
//                MonHoc itemMonHoc = listMH.get(i);
//                if (itemMonHoc.getMaMonHoc().equals(maMonHocs)) {
//                    tenMh = itemMonHoc.getTenMonHoc();
//                }
//            }
//            System.out.println("tem mon hoc : " + tenMh);
//            comboMonHoc.setSelectedItem(tenMh);
//            System.out.println("-----------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void showDetailss(CauHoi ch) {

        txtNoiDung.setText(ch.getNoiDung());
        txtDA_A.setText(ch.getDapAn_A());
        txtDA_B.setText(ch.getDapAn_B());
        txtDA_C.setText(ch.getDapAn_C());
        txtDA_D.setText(ch.getDapAn_D());
        txtDA_Dung.setText(ch.getDapAn_Dung());
        txtMaCauHoi.setText(ch.getMaCauHoi());
        updateImage(ch.getHinh());
        String maCH = ch.getDoKho();
        String tenMHCT = ch.getTenMonCT();
        comboDoKho.setSelectedItem(maCH);
        comboMonHocCT.setSelectedItem(tenMHCT);

    }

    boolean checkNull() {
        if (txtNoiDung.getText().equals("")) {
            Dialong.alert(this, "Nội dung câu hỏi không để trống !");
            txtNoiDung.requestFocus();
            return false;
        }
        if (txtDA_A.getText().equals("")) {
            Dialong.alert(this, "Đáp án A không để trống !");
            txtDA_A.requestFocus();
            return false;
        }
        if (txtDA_B.getText().equals("")) {
            Dialong.alert(this, "Đáp án B không để trống !");
            txtDA_B.requestFocus();
            return false;
        }

        if (txtDA_C.getText().equals("")) {
            Dialong.alert(this, "Đáp án C không để trống !");
            txtDA_C.requestFocus();
            return false;
        }
        if (txtDA_D.getText().equals("")) {
            Dialong.alert(this, "Đáp án D không để trống !");
            txtDA_D.requestFocus();
            return false;
        }

        if (txtDA_Dung.getText().equals("")) {
            Dialong.alert(this, "Đáp án Đúng không để trống !");
            txtDA_Dung.requestFocus();
            return false;
        }

        return true;
    }

    void clear() {
        txtDA_A.setText("");
        txtDA_B.setText("");
        txtDA_C.setText("");
        txtDA_D.setText("");
        txtDA_Dung.setText("");
        txtNoiDung.setText("");
        Random rd = new Random();
        int a = 10000 + rd.nextInt(99999);
        txtMaCauHoi.setText("CH_" + a);
        comboMonHoc.setSelectedIndex(0);
        labelAnh.setIcon(null);
    }

    CauHoi setInsert() {
        CauHoi ch = new CauHoi();
        ch.setMaCauHoi(txtMaCauHoi.getText());
        ch.setNgayTao(XConvert.now());
        ch.setMa_MonHocCT(maMonHocChiTiet);
        ch.setNoiDung(txtNoiDung.getText());
        ch.setDapAn_A(txtDA_A.getText());
        ch.setDapAn_B(txtDA_B.getText());
        ch.setDapAn_C(txtDA_C.getText());
        ch.setDapAn_D(txtDA_D.getText());
        ch.setDapAn_Dung(txtDA_Dung.getText());
        ch.setDoKho(cboDoKho);
        return ch;
    }

    CauHoi setUpdate() {
        CauHoi ch = new CauHoi();
        ch.setMaCauHoi(txtMaCauHoi.getText());
        ch.setMa_MonHocCT(maMonHocChiTiet);
        ch.setNoiDung(txtNoiDung.getText());
        ch.setDapAn_A(txtDA_A.getText());
        ch.setDapAn_B(txtDA_B.getText());
        ch.setDapAn_C(txtDA_C.getText());
        ch.setDapAn_D(txtDA_D.getText());
        ch.setDapAn_Dung(txtDA_Dung.getText());
        ch.setDoKho(cboDoKho);
        return ch;
    }

    CauHoi setDelete() {
        CauHoi ch = new CauHoi();
        String delete_At = String.valueOf(java.time.LocalDate.now() + " " + java.time.LocalTime.now());
        ch.setMaCauHoi(txtMaCauHoi.getText());
        ch.setDelete_User(Auth.user.getMaDN());
        ch.setDelete_At(delete_At);
        return ch;
    }

    void updateImage(String hinh) {
        ImageIcon image = new ImageIcon("src\\TienIch\\Icon\\" +hinh);
        Image im = image.getImage();
        ImageIcon icon = new ImageIcon(im.getScaledInstance(labelAnh.getWidth() - 4,
                labelAnh.getHeight() - 4, im.SCALE_SMOOTH));
        labelAnh.setIcon(icon);
    }

    void setImage() {
        JFileChooser fc = new JFileChooser("src\\TienIch\\Icon\\");
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            fileNameImage = fc.getSelectedFile().getAbsolutePath();
            updateImage(fileNameImage);
        }
    }

    void insert() {
        try {
            if (checkNull()) {
                if (checkTrung()) {
                    if (labelAnh != null) {
                        CauHoi ch = setInsert();
                        ch.setHinh(fileNameImage);
                        new CauHoiDAO().insert(ch, con);
                        Dialong.alert(this, "Thêm câu hỏi thành công !");
                        return;
                    } else {
                        CauHoi chh = setInsert();
                        chh.setHinh("NULL");
                        new CauHoiDAO().insert(chh, con);
                        Dialong.alert(this, "Thêm câu hỏi thành công !");
                        return;
                    }
                }
            }
        } catch (Exception e) {
            Dialong.alert(this, "Thêm câu hỏi thất bại!");
            e.printStackTrace();
            return;
        }

    }

    void update() {
        try {
            if (checkNull()) {
                if (labelAnh != null) {
                    CauHoi ch = setUpdate();
                    ch.setHinh(fileNameImage);
                    new CauHoiDAO().update(ch, con);
                    Dialong.alert(this, "Sửa câu hỏi thành công !");
                    return;
                } else {
                    CauHoi chh = setUpdate();
                    chh.setHinh("NULL");
                    new CauHoiDAO().update(chh, con);
                    Dialong.alert(this, "Sửa câu hỏi thành công !");
                    return;
                }

            }
        } catch (Exception e) {
            Dialong.alert(this, "Sửa câu hỏi thất bại!");
            e.printStackTrace();
            return;
        }

    }

    void soft_Delete() {
        try {
            int chon=JOptionPane.showConfirmDialog(this,"Bạn có muốn xóa câu hỏi này không ?");
            if(chon!=JOptionPane.YES_OPTION){ return;}
            CauHoi ch = setDelete();
            new CauHoiDAO().delete(ch, con);
            clear();
            Dialong.alert(this, "Xóa câu hỏi thành công!");

        } catch (Exception e) {
            Dialong.alert(this, "Xóa câu hỏi thất bại!");
            e.printStackTrace();
            return;
        }
    }

    boolean checkTrung() {
        try {
            List<CauHoi> list = new CauHoiDAO().selectAll(con);
            String maCauHoi = txtMaCauHoi.getText();
            for (CauHoi ch : list) {
                if (ch.getMaCauHoi().equals(maCauHoi)) {
                    Dialong.alert(this, "Mã câu hỏi đã tồn tại !! Nhập mã khác .");

                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        comboMonHoc = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        comboDoKho = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtMaCauHoi = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNoiDung = new javax.swing.JTextArea();
        labelAnh = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtDA_A = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtDA_B = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtDA_D = new javax.swing.JTextField();
        txtDA_C = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtDA_Dung = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        comboMonHocCT = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        cboDanhDachMonHoc = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableList = new javax.swing.JTable();

        setBackground(new java.awt.Color(204, 255, 204));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Quản Lý Câu Hỏi");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Quản Lý Câu Hỏi");

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(153, 255, 204));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Môn học : ");

        comboMonHoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboMonHocMouseClicked(evt);
            }
        });
        comboMonHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMonHocActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Độ khó : ");

        comboDoKho.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dễ", "Khó" }));
        comboDoKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboDoKhoActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Mã câu hỏi :");

        txtMaCauHoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaCauHoiActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Câu hỏi "));

        jLabel7.setText("Nội dung :");

        txtNoiDung.setColumns(20);
        txtNoiDung.setRows(5);
        jScrollPane1.setViewportView(txtNoiDung);

        labelAnh.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        labelAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelAnhMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1))
            .addComponent(labelAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Đáp án"));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Đáp án A");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Đáp án B");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Đáp án C");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Đáp án D");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Đáp án đúng ");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDA_C, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
                    .addComponent(txtDA_D)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtDA_Dung))
                    .addComponent(txtDA_A)
                    .addComponent(txtDA_B))
                .addGap(21, 21, 21))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtDA_A, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(txtDA_B, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDA_C, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDA_D, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDA_Dung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Môn học chi tiết : ");

        comboMonHocCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMonHocCTActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Create.png"))); // NOI18N
        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Edit.png"))); // NOI18N
        jButton2.setText("Sửa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Delete.png"))); // NOI18N
        jButton3.setText("Xóa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/new.png"))); // NOI18N
        jButton4.setText("Mới");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setText("|<");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton6.setText("<<");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton7.setText(">>");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton8.setText(">|");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6)
                        .addGap(20, 20, 20)
                        .addComponent(jButton7)
                        .addGap(18, 18, 18)
                        .addComponent(jButton8)
                        .addGap(2, 2, 2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboMonHocCT, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comboDoKho, 0, 248, Short.MAX_VALUE)
                            .addComponent(txtMaCauHoi)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(24, 24, 24))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(txtMaCauHoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(comboMonHocCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboDoKho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)
                        .addComponent(jButton3)
                        .addComponent(jButton4)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton5, jButton6, jButton7, jButton8});

        jTabbedPane1.addTab("Cập nhật", jPanel2);

        jPanel1.setBackground(new java.awt.Color(153, 255, 204));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Môn học");

        cboDanhDachMonHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDanhDachMonHocActionPerformed(evt);
            }
        });

        tableList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã câu hỏi", "Độ khó", "Đáp án A", "Đáp án B", "Đáp án C", "Đáp án D", "Đáp án Đúng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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
        jScrollPane2.setViewportView(tableList);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cboDanhDachMonHoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(29, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 743, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cboDanhDachMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(101, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Danh sách", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(305, 305, 305)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaCauHoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaCauHoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaCauHoiActionPerformed
    String cboDoKho;
    private void comboDoKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboDoKhoActionPerformed
        cboDoKho = comboDoKho.getSelectedItem().toString();
        System.out.println("do kho : " + cboDoKho);
    }//GEN-LAST:event_comboDoKhoActionPerformed

    private void comboMonHocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboMonHocMouseClicked

    }//GEN-LAST:event_comboMonHocMouseClicked
    String maMonHoc;
    List<CauHoi> list_CH = new ArrayList<>();
    private void comboMonHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMonHocActionPerformed
        String tenMonHoc = comboMonHoc.getSelectedItem().toString();
        if (tenMonHoc == null) {
            return;
        } else {
            for (int i = 0; i < listMH.size(); i++) {
                MonHoc itemMonHoc = listMH.get(i);
                if (itemMonHoc.getTenMonHoc().equals(tenMonHoc)) {
                    maMonHoc = itemMonHoc.getMaMonHoc();
                }
            }
            findToMonHocCT(maMonHoc);
//            findToDSMonHocCT(maMonHoc);
            System.out.println("mã môn học :" + maMonHoc);
            list_CH = new CauHoiDAO().selectAll_IS_NULL_MaMobHoc(maMonHoc, con);
            System.out.println("size: " + list_CH.size());
        }
    }//GEN-LAST:event_comboMonHocActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        clear();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        insert();
    }//GEN-LAST:event_jButton1ActionPerformed
    String maMonHocChiTiet;
    private void comboMonHocCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMonHocCTActionPerformed

        if (comboMonHocCT.getSelectedItem() == null) {
            return;
        } else {
            String tenMonHoc = comboMonHocCT.getSelectedItem().toString();
            listMHCT = new MonHocChiTietDAO().selectAll(con);
            for (int i = 0; i < listMHCT.size(); i++) {
                MonHocChiTiet itemMHCT = listMHCT.get(i);
                if (itemMHCT.getTenMonHc().equals(tenMonHoc)) {
                    maMonHocChiTiet = itemMHCT.getMaMonHocCT();
                }
            }
            System.out.println("ma mon ho ct :" + maMonHocChiTiet);
        }

    }//GEN-LAST:event_comboMonHocCTActionPerformed

    private void labelAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelAnhMouseClicked
        setImage();
    }//GEN-LAST:event_labelAnhMouseClicked

    String dsMaMonHocCT;
    private void cboDanhDachMonHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDanhDachMonHocActionPerformed
        DefaultTableModel model = (DefaultTableModel) tableList.getModel();
        if (cboDanhDachMonHoc.getSelectedItem() == null) {
            model.setRowCount(0);
            return;
        } else {

            String tenMonHoc = cboDanhDachMonHoc.getSelectedItem().toString();
            listMHCT = new MonHocChiTietDAO().selectAll(con);
            for (int i = 0; i < listMHCT.size(); i++) {
                MonHocChiTiet itemMHCT = listMHCT.get(i);
                if (itemMHCT.getTenMonHc().equals(tenMonHoc)) {
                    dsMaMonHocCT = itemMHCT.getMaMonHocCT();

                }
            }
            List<CauHoi> list = new CauHoiDAO().selectAll_BY_MaMonHoc(dsMaMonHocCT, con);
            model.setRowCount(0);
            for (CauHoi ch : list) {
                model.addRow(new Object[]{
                    ch.getMaCauHoi(),
                    ch.getDoKho(),
                    ch.getDapAn_A(),
                    ch.getDapAn_B(),
                    ch.getDapAn_C(),
                    ch.getDapAn_D(),
                    ch.getDapAn_Dung()});
            }
            System.out.println("ma danh sach mon ho ct :" + dsMaMonHocCT);
        }
    }//GEN-LAST:event_cboDanhDachMonHocActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        update();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tableListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableListMouseClicked
        index = tableList.getSelectedRow();
        if(evt.getClickCount()==2){
            List<CauHoi> list = new CauHoiDAO().selectAll_BY_MaMonHoc(dsMaMonHocCT, con);
            CauHoi ch = list.get(index);
            showDetail(ch, listMHCT);
            jTabbedPane1.setSelectedIndex(0);
        }

    }//GEN-LAST:event_tableListMouseClicked

    void first() {
        if (list_CH.size() <= 0) {
            clear();
            return;
        } else {
            index = 0;
            CauHoi gv = list_CH.get(index);

            System.out.println("giá trị : " + gv.getMa_MonHocCT());
            showDetailss(gv);
        }
    }

    void prev() {
        if (list_CH.size() <= 0) {
            clear();
            return;
        } else {
            if (index > 0) {
                index--;
                CauHoi gv = list_CH.get(index);
                showDetailss(gv);
            } else if (index == 0) {
                index = list_CH.size() - 1;
                CauHoi gv = list_CH.get(index);
                showDetailss(gv);
            }
        }
    }

    void next() {
        if (list_CH.size() <= 0) {
            clear();
            return;
        } else {
            if (index == list_CH.size() - 1) {
                index = 0;
                CauHoi gv = list_CH.get(index);
                System.out.println("giá trị : " + gv.getMa_MonHocCT());
                showDetailss(gv);
            } else if (index < list_CH.size()) {
                index++;
                CauHoi gv = list_CH.get(index);
                System.out.println("giá trị : " + gv.getMa_MonHocCT());
                showDetailss(gv);
            }
        }
    }

    void last() {
        if (list_CH.size() <= 0) {
            clear();
            return;
        } else {
            index = list_CH.size() - 1;
            CauHoi gv = list_CH.get(index);
            showDetailss(gv);
        }
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        soft_Delete();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        first();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        prev();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        next();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        last();
    }//GEN-LAST:event_jButton8ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboDanhDachMonHoc;
    private javax.swing.JComboBox<String> comboDoKho;
    private javax.swing.JComboBox<String> comboMonHoc;
    private javax.swing.JComboBox<String> comboMonHocCT;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelAnh;
    private javax.swing.JTable tableList;
    private javax.swing.JTextField txtDA_A;
    private javax.swing.JTextField txtDA_B;
    private javax.swing.JTextField txtDA_C;
    private javax.swing.JTextField txtDA_D;
    private javax.swing.JTextField txtDA_Dung;
    private javax.swing.JTextField txtMaCauHoi;
    private javax.swing.JTextArea txtNoiDung;
    // End of variables declaration//GEN-END:variables
}
