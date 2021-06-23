package Form;

import DAO.MonHocChiTietDAO;
import DAO.MonHocDAO;
import Entity.Auth;
import Entity.MonHoc;
import Entity.MonHocChiTiet;
import TienIch_HoTro.Connections;
import TienIch_HoTro.Dialong;
import TienIch_HoTro.XConvert;
import java.awt.Image;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class QLMonHocChiTiet extends javax.swing.JInternalFrame {

    List<MonHoc> listMH = new ArrayList<>();
    List<MonHocChiTiet> listMHCT = new ArrayList<>();
    Connection con;
    String fileNameImage = "";
    String maMonHoc_Insert;
    Random rngg = new Random();
    int index = -1;

    public QLMonHocChiTiet() {
        initComponents();
        con = new Connections().ketNotDatabase();
        listMHCT = new MonHocChiTietDAO().selectAll(con);
        fillToMonHoc();
    }

    void fillToMonHoc() {
        listMH = new MonHocDAO().selectAll(con);
        for (MonHoc mh : listMH) {
            String tenMonHoc = mh.getTenMonHoc();
            comboMonHoc.addItem(tenMonHoc);
            cboMonHoc.addItem(tenMonHoc);
        }
    }

    void updateImage(String hinh) {
        ImageIcon image = new ImageIcon("src\\TienIch\\Icon\\" + hinh);
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


    Boolean checkNull() {
        if (txtTenMonHocCT.getText().equals("")) {
            Dialong.alert(this, " Tên không để trống !");
            txtTenMonHocCT.requestFocus();
            return false;
        }

        if (txtGhiChu.getText().equals("")) {
            Dialong.alert(this, " Tên không để trống !");
            txtGhiChu.requestFocus();
            return false;
        }
        if (labelAnh.getIcon() == null) {
            Dialong.alert(this, "Ảnh không để trống !");
            return false;
        }
        return true;
    }

    Boolean checkTrung() {
        List<MonHocChiTiet> list = new MonHocChiTietDAO().selectAll(con);
        String maMonHocCT = txtMaMonHocCT.getText();
        for (MonHocChiTiet mhct : list) {
            if (mhct.getMaMonHocCT().equals(maMonHocCT)) {
                Dialong.alert(this, "Mã môn học chi tiết đã tồn tại! Mời nhập mã khác !");
                txtMaMonHocCT.requestFocus();
                return false;
            }
        }
        return true;
    }

    void showDetail(List<MonHocChiTiet> lisst) {
        MonHocChiTiet mhct = lisst.get(index);
        txtMaMonHocCT.setText(mhct.getMaMonHocCT());
        txtGhiChu.setText(mhct.getGhiChu());
        txtTenMonHocCT.setText(mhct.getTenMonHc());
        updateImage(mhct.getHinh());
        String maMonHoc = mhct.getMa_MonHoc();
        String tenMonHoc = "";
        for (int i = 0; i < listMH.size(); i++) {
            MonHoc itemMonHoc = listMH.get(i);
            if (itemMonHoc.getMaMonHoc().equals(maMonHoc)) {
                tenMonHoc = itemMonHoc.getTenMonHoc();
            }
        }
        fileNameImage = mhct.getHinh();
        comboMonHoc.setSelectedItem(tenMonHoc);
        comboMonHoc.setEnabled(false);
    }

    void clear() {
        txtTenMonHocCT.setText("");
        txtGhiChu.setText("");
        labelAnh.setIcon(null);
        comboMonHoc.setSelectedIndex(0);
//        int ab = 10000 + rngg.nextInt(999999);
        txtMaMonHocCT.setText("");
        txtMaMonHocCT.setEnabled(true);
        comboMonHoc.setEnabled(true);
    }

    MonHocChiTiet setInsert() {
        MonHocChiTiet mhct = new MonHocChiTiet();
        mhct.setMaMonHocCT(txtMaMonHocCT.getText());
        mhct.setTenMonHc(txtTenMonHocCT.getText());
        mhct.setGhiChu(txtGhiChu.getText());
        mhct.setHinh(fileNameImage);
        mhct.setMa_MonHoc(maMonHoc_Insert);
        mhct.setNgayTao(XConvert.now());
        return mhct;
    }

    MonHocChiTiet setUpdate() {
        MonHocChiTiet mhct = new MonHocChiTiet();
        mhct.setMaMonHocCT(maMonHocs);
        mhct.setTenMonHc(txtTenMonHocCT.getText());
        mhct.setGhiChu(txtGhiChu.getText());
        mhct.setHinh(fileNameImage);
        return mhct;
    }

    MonHocChiTiet setDelete() {
        MonHocChiTiet mhct = new MonHocChiTiet();
        mhct.setMaMonHocCT(maMonHocs);
        String delete_At = String.valueOf(java.time.LocalDate.now() + " " + java.time.LocalTime.now());
        mhct.setDelete_At(delete_At);
        mhct.setDelete_User(Auth.user.getMaDN());
        return mhct;
    }

    void insert() {
        try {
            if (checkNull()) {
                if (checkTrung()) {
                    MonHocChiTiet mhct = setInsert();
                    new MonHocChiTietDAO().insert(mhct, con);
                    Dialong.alert(this, "Thêm thành công !");
                    return;
                }
            }

        } catch (Exception e) {
            Dialong.alert(this, "Thêm thất bại !");
            e.printStackTrace();
            return;

        }
    }

    void update() {
        try {
            if (checkNull()) {
                MonHocChiTiet mhct = setUpdate();
                new MonHocChiTietDAO().update(mhct, con);
                Dialong.alert(this, "Sửa thành công !");
                return;
            }
        } catch (Exception e) {
            Dialong.alert(this, "Sửa thất bại !");
            e.printStackTrace();
            return;

        }
    }

    void soft_Delete() {
        try {
            int chon=JOptionPane.showConfirmDialog(this,"Bạn có muốn xóa môn học chi tiết này không ?");
             if(chon!=JOptionPane.YES_OPTION){ return;}
            MonHocChiTiet mhct = setDelete();
            new MonHocChiTietDAO().delete(mhct, con);
            clear();
            Dialong.alert(this, "Xóa thành công !");
            return;

        } catch (Exception e) {
            Dialong.alert(this, "Xóa thất bại !");
            e.printStackTrace();
            return;
        }
    }

    void first() {
        index = 0;
        showDetail(listComBo);
    }

    void prev() {
        if (index == listComBo.size() - 1) {
            index = 0;

            showDetail(listComBo);
        } else if (index < listComBo.size()) {
            index++;

            showDetail(listComBo);
        }
    }

    void next() {
        if (index > 0) {
            index--;

            showDetail(listComBo);
        } else if (index == 0) {
            index = listComBo.size() - 1;

            showDetail(listComBo);
        }
    }

    void last() {
        index = listComBo.size() - 1;

        showDetail(listComBo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cboMonHoc = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableList = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaMonHocCT = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenMonHocCT = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        comboMonHoc = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        labelAnh = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 255, 204));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Quản Lý Môn Học Chi Tiết");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Quản Lý Môn Học Chi Tiết");

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(153, 255, 204));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Tên môn học");

        cboMonHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMonHocActionPerformed(evt);
            }
        });

        tableList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Môn Học CT", "Tên Môn Học CT", "Ngày Tạo", "Ghi Chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cboMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Danh sách", jPanel2);

        jPanel1.setBackground(new java.awt.Color(153, 255, 204));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Mã môn học CT ");

        txtMaMonHocCT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Tên môn học CT");

        txtTenMonHocCT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Tên môn học");

        comboMonHoc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboMonHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMonHocActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Ghi chú");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane1.setViewportView(txtGhiChu);

        labelAnh.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        labelAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelAnhMouseClicked(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/Add.png"))); // NOI18N
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaMonHocCT, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenMonHocCT, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboMonHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaMonHocCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenMonHocCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(0, 11, Short.MAX_VALUE))
                    .addComponent(labelAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)
                        .addComponent(jButton3)
                        .addComponent(jButton4)))
                .addGap(30, 30, 30))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton5, jButton6, jButton7, jButton8});

        jTabbedPane1.addTab("Cập nhật", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    String maMonHoc;
    List<MonHocChiTiet> listCBO = new ArrayList<>();
    List<MonHocChiTiet> listComBo = new ArrayList<>();
    private void cboMonHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMonHocActionPerformed
        String tenMonHoc = cboMonHoc.getSelectedItem().toString();

        for (int i = 0; i < listMH.size(); i++) {
            MonHoc itemmonhoc = listMH.get(i);
            if (itemmonhoc.getTenMonHoc().equals(tenMonHoc)) {
                maMonHoc = itemmonhoc.getMaMonHoc();
            }
        }
        listCBO = new MonHocChiTietDAO().selectAll_By_MaMonHoc(maMonHoc, con);
        DefaultTableModel model = (DefaultTableModel) tableList.getModel();
        model.setRowCount(0);
        for (MonHocChiTiet mhct : listCBO) {
            model.addRow(new Object[]{
                mhct.getMaMonHocCT(),
                mhct.getTenMonHc(),
                mhct.getNgayTao(),
                mhct.getGhiChu()});
        }
    }//GEN-LAST:event_cboMonHocActionPerformed

    private void comboMonHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMonHocActionPerformed
        String tenMonHoc = comboMonHoc.getSelectedItem().toString();
        for (int i = 0; i < listMH.size(); i++) {
            MonHoc itemmonhoc = listMH.get(i);
            if (itemmonhoc.getTenMonHoc().equals(tenMonHoc)) {
                maMonHoc_Insert = itemmonhoc.getMaMonHoc();
            }
        }

        listComBo = new MonHocChiTietDAO().selectAll_By_MaMonHoc(maMonHoc_Insert, con);
//        int ab = 10000 + rngg.nextInt(999999);
//        txtMaMonHocCT.setText(maMonHoc_Insert + "_" + ab);
//        if (index < 0) {
//            return;
//        } else {
//            if (list.size() > 0) {
//                showDetail();
////            jTabbedPane1.setSelectedIndex(0);
//            }
//        }
        System.out.println("ma môn học : " + maMonHoc_Insert);
    }//GEN-LAST:event_comboMonHocActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        insert();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        clear();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void labelAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelAnhMouseClicked
        setImage();
    }//GEN-LAST:event_labelAnhMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        update();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        soft_Delete();
    }//GEN-LAST:event_jButton3ActionPerformed
    String maMonHocs;
    private void tableListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableListMouseClicked
        index = tableList.getSelectedRow();
        System.out.println("ma mon hoc chi tieeta : " + maMonHocs);
        txtMaMonHocCT.setEnabled(false);
        if(evt.getClickCount()==2){
            maMonHocs = (String) tableList.getValueAt(index, 0);
            showDetail(listCBO);
            jTabbedPane1.setSelectedIndex(1);
        
        }
    }//GEN-LAST:event_tableListMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboMonHoc;
    private javax.swing.JComboBox<String> comboMonHoc;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelAnh;
    private javax.swing.JTable tableList;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtMaMonHocCT;
    private javax.swing.JTextField txtTenMonHocCT;
    // End of variables declaration//GEN-END:variables
}
