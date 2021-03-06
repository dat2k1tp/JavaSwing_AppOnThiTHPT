/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import DAO.CauHoiDAO;
import DAO.DeThiDAO;
import DAO.GiaoVienDAO;
import DAO.MonHocChiTietDAO;
import DAO.MonHocDAO;
import Entity.CauHoi;
import Entity.DeThi;
import Entity.GiaoVien;
import Entity.MonHocChiTiet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class ThungRac extends javax.swing.JInternalFrame {

    /**
     * Creates new form ThungRac
     */
    public ThungRac() {
        initComponents();
        connet();
        fillComboxDeThi();
        fillComboxGiaoVien();
        fillComboxMonHocTab3();
        fillComboxMonHocTab4();
        
        filltoTableDeThi();
        filltoTableGiaoVien();
        filltoTableMonHocCT();
        filltoTableCauHoi();
        
    }
    Connection con;
    DeThiDAO dtdao = new DeThiDAO();
    GiaoVienDAO gvdao = new GiaoVienDAO();
    MonHocChiTietDAO mhctdao = new MonHocChiTietDAO();
    CauHoiDAO chdao = new CauHoiDAO();

    protected void connet() {
        
        String uername = "tuanmayman";
        String password = "vutuan040599";
        String hosting = " sql.freeasphost.net\\MSSQL2016";
        String dbname = "databaseName=tuanmayman_Du_An_1";
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=quan_ly_sach";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://" + hosting + ";" + dbname, uername, password);
//            JOptionPane.showMessageDialog(this, "Kết nối database thành công !");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ko có driver");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDeThi = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        cboNamTab1 = new javax.swing.JComboBox<>();
        btnKPDeThi = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableGiaoVien = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        cboGiaoVien = new javax.swing.JComboBox<>();
        btnKPGiaoVien = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        cboMonHocTab3 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblMonHocCT = new javax.swing.JTable();
        btnKPMonHocCT = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        cboMonHocTab4 = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblCauHoi = new javax.swing.JTable();
        btnKPCauHoi = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Thùng Rác");

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jTabbedPane1.setBackground(new java.awt.Color(153, 255, 204));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(153, 255, 204));

        tblDeThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ ĐỀ THI", "MÃ MÔN HỌC", "THỜI GIAN", "SỐ CÂU", "NGÀY TẠO", "ĐỘ KHÓ", "TÊN ĐỀ THI", "MÃ GIÁO VIÊN", "NGÀY XÓA", "XÓA BỞI"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDeThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDeThiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDeThi);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Năm");

        cboNamTab1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cboNamTab1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNamTab1ActionPerformed(evt);
            }
        });

        btnKPDeThi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnKPDeThi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/restore.png"))); // NOI18N
        btnKPDeThi.setText("Khôi Phục Dữ Liệu");
        btnKPDeThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKPDeThiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 853, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(343, 343, 343)
                        .addComponent(btnKPDeThi)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(322, 322, 322)
                .addComponent(jLabel12)
                .addGap(53, 53, 53)
                .addComponent(cboNamTab1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cboNamTab1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnKPDeThi)
                .addGap(10, 10, 10))
        );

        jTabbedPane1.addTab("ĐỀ THI", jPanel2);

        jPanel3.setBackground(new java.awt.Color(153, 255, 204));

        tableGiaoVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã giáo viên", "Họ tên", "Ngày sinh", "Giới tính", "SĐT", "Email", "Địa chỉ", "Ngày tạo", "Ngày Xóa", "Xóa Bởi"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableGiaoVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableGiaoVienMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableGiaoVien);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Năm");

        cboGiaoVien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cboGiaoVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboGiaoVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboGiaoVienActionPerformed(evt);
            }
        });

        btnKPGiaoVien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnKPGiaoVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/restore.png"))); // NOI18N
        btnKPGiaoVien.setText("Khôi Phục Dữ Liệu");
        btnKPGiaoVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKPGiaoVienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(351, 351, 351)
                        .addComponent(btnKPGiaoVien)
                        .addGap(0, 341, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(330, 330, 330)
                .addComponent(jLabel2)
                .addGap(49, 49, 49)
                .addComponent(cboGiaoVien, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboGiaoVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(btnKPGiaoVien)
                .addContainerGap())
        );

        jTabbedPane1.addTab("GIÁO VIÊN", jPanel3);

        jPanel4.setBackground(new java.awt.Color(153, 255, 204));

        cboMonHocTab3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMonHocTab3ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Tên môn học");

        tblMonHocCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Môn Học CT", "Tên Môn Học CT", "Ngày Tạo", "Ghi Chú", "Ngày Xóa", "Xóa Bởi"
            }
        ));
        tblMonHocCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMonHocCTMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblMonHocCT);

        btnKPMonHocCT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnKPMonHocCT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/restore.png"))); // NOI18N
        btnKPMonHocCT.setText("Khôi Phục Dữ Liệu");
        btnKPMonHocCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKPMonHocCTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 274, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(cboMonHocTab3, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(262, 262, 262))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(332, 332, 332)
                .addComponent(btnKPMonHocCT)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cboMonHocTab3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(btnKPMonHocCT)
                .addContainerGap())
        );

        jTabbedPane1.addTab("MÔN HỌC CHI TIẾT", jPanel4);

        jPanel5.setBackground(new java.awt.Color(153, 255, 204));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Môn học chi tiết");

        cboMonHocTab4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMonHocTab4ActionPerformed(evt);
            }
        });

        tblCauHoi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Câu Hỏi", "Độ Khó", "Ngày Tạo", "Nội Dung", "Ngày Xóa", "Xóa Bởi"
            }
        ));
        tblCauHoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCauHoiMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblCauHoi);

        btnKPCauHoi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnKPCauHoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/restore.png"))); // NOI18N
        btnKPCauHoi.setText("Khôi Phục Dữ Liệu");
        btnKPCauHoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKPCauHoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(40, 40, 40)
                        .addComponent(cboMonHocTab4, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(295, 295, 295))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 824, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(340, 340, 340)
                .addComponent(btnKPCauHoi)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboMonHocTab4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnKPCauHoi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("CÂU HỎI", jPanel5);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Thùng Rác");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(391, 391, 391))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblDeThiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDeThiMouseClicked
        

    }//GEN-LAST:event_tblDeThiMouseClicked

    private void cboNamTab1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNamTab1ActionPerformed
        filltoTableDeThi();
    }//GEN-LAST:event_cboNamTab1ActionPerformed

    private void tableGiaoVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableGiaoVienMouseClicked
        

    }//GEN-LAST:event_tableGiaoVienMouseClicked

    private void cboGiaoVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboGiaoVienActionPerformed
        filltoTableGiaoVien();
    }//GEN-LAST:event_cboGiaoVienActionPerformed

    private void cboMonHocTab3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMonHocTab3ActionPerformed
        filltoTableMonHocCT();
    }//GEN-LAST:event_cboMonHocTab3ActionPerformed

    private void tblMonHocCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMonHocCTMouseClicked

    }//GEN-LAST:event_tblMonHocCTMouseClicked

    private void cboMonHocTab4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMonHocTab4ActionPerformed
        filltoTableCauHoi();
    }//GEN-LAST:event_cboMonHocTab4ActionPerformed

    private void tblCauHoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCauHoiMouseClicked
       
    }//GEN-LAST:event_tblCauHoiMouseClicked

    private void btnKPDeThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKPDeThiActionPerformed
         int index=tblDeThi.getSelectedRow();  
         
         if(index>=0){
             int chon=JOptionPane.showConfirmDialog(this,"Bạn có muốn khôi phục đề thi này không ?");
             if(chon!=JOptionPane.YES_OPTION){ return;}
             String maDe=tblDeThi.getValueAt(index,0)+"";
             dtdao.reStore(maDe, con);
             filltoTableDeThi();
             JOptionPane.showMessageDialog(this,"Khôi phục thành công !");
        }else{
            JOptionPane.showMessageDialog(this,"Bạn chưa chọn đề thi !");
            return;
        }
        
    }//GEN-LAST:event_btnKPDeThiActionPerformed

    private void btnKPGiaoVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKPGiaoVienActionPerformed
       int index=tableGiaoVien.getSelectedRow();  
         
         if(index>=0){
             int chon=JOptionPane.showConfirmDialog(this,"Bạn có muốn khôi phục giáo viên này không ?");
             if(chon!=JOptionPane.YES_OPTION){ return;}
             String ma=tableGiaoVien.getValueAt(index,0)+"";
             gvdao.reStore(ma, con);
             filltoTableGiaoVien();
             JOptionPane.showMessageDialog(this,"Khôi phục thành công !");
        }else{
            JOptionPane.showMessageDialog(this,"Bạn chưa chọn giáo viên !");
            return;
        }
    }//GEN-LAST:event_btnKPGiaoVienActionPerformed

    private void btnKPMonHocCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKPMonHocCTActionPerformed
       int index=tblMonHocCT.getSelectedRow();  
         
         if(index>=0){
             int chon=JOptionPane.showConfirmDialog(this,"Bạn có muốn khôi phục môn học CT này không ?");
             if(chon!=JOptionPane.YES_OPTION){ return;}
             String ma=tblMonHocCT.getValueAt(index,0)+"";
             mhctdao.reStore(ma, con);
             filltoTableMonHocCT();
             JOptionPane.showMessageDialog(this,"Khôi phục thành công !");
        }else{
            JOptionPane.showMessageDialog(this,"Bạn chưa chọn môn học CT !");
            return;
        }
    }//GEN-LAST:event_btnKPMonHocCTActionPerformed

    private void btnKPCauHoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKPCauHoiActionPerformed
       int index=tblCauHoi.getSelectedRow();  
         
         if(index>=0){
             int chon=JOptionPane.showConfirmDialog(this,"Bạn có muốn khôi phục câu hỏi này không ?");
             if(chon!=JOptionPane.YES_OPTION){ return;}
             String ma=tblCauHoi.getValueAt(index,0)+"";
             chdao.reStore(ma, con);
             filltoTableCauHoi();
             JOptionPane.showMessageDialog(this,"Khôi phục thành công !");
        }else{
            JOptionPane.showMessageDialog(this,"Bạn chưa chọn câu hỏi !");
            return;
        }
    }//GEN-LAST:event_btnKPCauHoiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKPCauHoi;
    private javax.swing.JButton btnKPDeThi;
    private javax.swing.JButton btnKPGiaoVien;
    private javax.swing.JButton btnKPMonHocCT;
    private javax.swing.JComboBox<String> cboGiaoVien;
    private javax.swing.JComboBox<String> cboMonHocTab3;
    private javax.swing.JComboBox<String> cboMonHocTab4;
    private javax.swing.JComboBox<String> cboNamTab1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tableGiaoVien;
    private javax.swing.JTable tblCauHoi;
    private javax.swing.JTable tblDeThi;
    private javax.swing.JTable tblMonHocCT;
    // End of variables declaration//GEN-END:variables

    protected void fillComboxDeThi() {
        try {
            
            String query = "Select distinct Year(Delete_At) as Nam from DeThi \n"
                    + "where Delete_At is not null order by Nam asc";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            DefaultComboBoxModel model = (DefaultComboBoxModel) cboNamTab1.getModel();
            model.removeAllElements();
            while (rs.next()) {
                String nam = rs.getString("Nam");
                model.addElement(nam);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    protected void fillComboxGiaoVien() {
        try {
            String query = "Select distinct Year(Delete_At) as Nam from GiaoVien \n"
                    + "where Delete_At is not null order by Nam asc";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            DefaultComboBoxModel model = (DefaultComboBoxModel) cboGiaoVien.getModel();
            model.removeAllElements();
            while (rs.next()) {
                String nam = rs.getString("Nam");
                model.addElement(nam);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    protected void fillComboxMonHocTab3() {
        try {
            String query = "Select TenMonHoc from MonHoc order by TenMonHoc desc";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            DefaultComboBoxModel cboMhTab3 = (DefaultComboBoxModel) cboMonHocTab3.getModel();
            cboMhTab3.removeAllElements();
            while (rs.next()) {
                String nam = rs.getString("TenMonHoc");
                cboMhTab3.addElement(nam);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    protected void fillComboxMonHocTab4() {
        try {
            String query = "Select TenMonHoc from MonHocChiTiet order by TenMonHoc desc";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            DefaultComboBoxModel cboMhTab4 = (DefaultComboBoxModel) cboMonHocTab4.getModel();
            cboMhTab4.removeAllElements();
            while (rs.next()) {
                String nam = rs.getString("TenMonHoc");
                cboMhTab4.addElement(nam);
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    private void filltoTableDeThi() {
        DefaultTableModel model = (DefaultTableModel) tblDeThi.getModel();
        model.setRowCount(0);
        try {
            String keyword = cboNamTab1.getSelectedItem() + "";
            List<DeThi> list = dtdao.selectBy_TimKiemThungRac(keyword, con);
            for (DeThi dt : list) {
                model.addRow(new Object[]{dt.getMaDeThi(), dt.getMaMonHoc(),
                    dt.getThoiGianLamBai(), dt.getTongSoCau(), dt.getNgayTao(), dt.getDoKho(),
                    dt.getTenDeThi(), dt.getMaGV(), dt.getDelete_At(), dt.getDelete_User()});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void filltoTableGiaoVien() {
        DefaultTableModel model = (DefaultTableModel) tableGiaoVien.getModel();
        model.setRowCount(0);
        try {
            String keyword = cboGiaoVien.getSelectedItem() + "";
            List<GiaoVien> list = gvdao.selectThungRac(keyword, con);
            for (GiaoVien gv : list) {
                String gioiTinh;
                if (gv.getGioiTinh() == true) {
                    gioiTinh = "Nam";
                } else {
                    gioiTinh = "Nữ";
                }
                model.addRow(new Object[]{
                    gv.getMaGV(),
                    gv.getHoTen(),
                    gv.getNgaySinh(),
                    gioiTinh,
                    gv.getSdt(),
                    gv.getEmail(),
                    gv.getDiaChi(),
                    gv.getNgayTao(),
                    gv.getDelete_At(),
                    gv.getDelete_User()});
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void filltoTableMonHocCT() {
        DefaultTableModel model = (DefaultTableModel) tblMonHocCT.getModel();
        model.setRowCount(0);
        try {
            String keyword = cboMonHocTab3.getSelectedItem() + "";
            List<MonHocChiTiet> list = mhctdao.selectThungRac(keyword, con);
            for (MonHocChiTiet mhct : list) {
                model.addRow(new Object[]{
                    mhct.getMaMonHocCT(),
                    mhct.getTenMonHc(),
                    mhct.getNgayTao(),
                    mhct.getGhiChu(),
                    mhct.getDelete_At(),
                    mhct.getDelete_User()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void filltoTableCauHoi() {
        DefaultTableModel model = (DefaultTableModel) tblCauHoi.getModel();
        model.setRowCount(0);
        try {
            String keyword = cboMonHocTab4.getSelectedItem() + "";
              List<CauHoi> list = chdao.selectThungRac(keyword, con);
            for (CauHoi ch : list) {
                System.out.println(ch.getDelete_At()+"   "+ch.getDelete_User());
                 model.addRow(new Object[]{
                    ch.getMaCauHoi(),
                    ch.getDoKho(),
                    ch.getNgayTao(),
                    ch.getNoiDung(),
                    ch.getDelete_At(),
                    ch.getDelete_User()
  
                 });      
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
