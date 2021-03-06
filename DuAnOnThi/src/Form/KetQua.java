/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import Entity.Auth;
import static Form.QLThi.con;
import static Form.main_HS.jDesktopPaneHS;
import TienIch_HoTro.XConvert;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;

/**
 *
 * @author ABC
 */
public class KetQua extends javax.swing.JInternalFrame {

    /**
     * Creates new form KetQua
     */
    public static String mon, dokho;
    public static int socau, socaudung;

    public KetQua() {
        initComponents();
        lbKQ.setText(socaudung + "/" + socau);
        lbMon.setText("Môn: " + mon);
        lbDokho.setText("Độ khó: " + dokho);
        lbDanhgia.setText(KhichLe());
    }

    protected String KhichLe() {
        float diem = socaudung / socau * 100;
        if (diem >= 9) {
            return "Bạn làm tốt lắm!";
        } else if (diem >= 6.5) {
            return "Bài làm xếp loại khá!";
        } else if (diem >= 5) {
            return "Bài làm xếp loại trung bình!";
        } else {
            return "Không ổn bạn cần luyện tập thêm!";
        }
    }

    protected void insertKQ() {
        String sql = "insert into KETQUA(Ma_KetQua,Ma_DeThi,Ma_HocSinh,SoCauDung,Diem,GhiChu,NgayThi) values(?,?,?,?,?,?,?)";
        Random rd = new Random();
        int so = rd.nextInt(1000000);
        String ma_kq = "KQ" + so;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ma_kq);
            ps.setString(2, ChonDeThi_HS.maDe);
            ps.setString(3, Auth.user.getMaDN());
            ps.setInt(4, socaudung);
            float diem =Float.valueOf(socaudung)/Float.valueOf(socau)*10;
            ps.setFloat(5, diem);
            //ghi chú là khích lệ điểm cao thấp
            ps.setString(6, KhichLe());
            ps.setDate(7,Date.valueOf(LocalDate.now()));
            ps.executeUpdate();
            System.out.println(diem+"");
            System.out.println(ma_kq+ChonDeThi_HS.maDe+Auth.user.getMaDN()+socaudung+diem+KhichLe()+LocalDate.now());
        } catch (SQLException ex) {
            Logger.getLogger(KetQua.class.getName()).log(Level.SEVERE, null, ex);
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
        cc = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lbMon = new javax.swing.JLabel();
        lbDokho = new javax.swing.JLabel();
        lbKQ = new javax.swing.JLabel();
        lbDanhgia = new javax.swing.JLabel();

        setTitle("Hệ thống ôn thi");

        jPanel1.setBackground(new java.awt.Color(153, 255, 204));

        cc.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cc.setText("Kết quả:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Kết Thúc Bài Thi !");

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lbMon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbMon.setText("Môn: Toán");

        lbDokho.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbDokho.setText("Độ khó: Dễ");

        lbKQ.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbKQ.setText("10/20");

        lbDanhgia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbDanhgia.setText("amazing good job em!!!");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbDokho)
                                        .addComponent(lbMon)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(cc)
                                    .addGap(32, 32, 32)
                                    .addComponent(lbKQ, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(lbDanhgia)))
                        .addGap(125, 125, 125))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(193, 193, 193))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel1)
                .addGap(42, 42, 42)
                .addComponent(lbMon)
                .addGap(30, 30, 30)
                .addComponent(lbDokho)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cc)
                    .addComponent(lbKQ, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(lbDanhgia)
                .addGap(35, 35, 35)
                .addComponent(jButton1)
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        insertKQ();
        for (JInternalFrame jif : jDesktopPaneHS.getAllFrames()) {
            jif.dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cc;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbDanhgia;
    private javax.swing.JLabel lbDokho;
    private javax.swing.JLabel lbKQ;
    private javax.swing.JLabel lbMon;
    // End of variables declaration//GEN-END:variables
}
