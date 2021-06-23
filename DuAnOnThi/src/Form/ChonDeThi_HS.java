/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import Entity.ChonDeThi;
import Entity.EntityThi;
import static Form.main_HS.jDesktopPaneHS;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hello Kiên
 */
public class ChonDeThi_HS extends javax.swing.JInternalFrame {

    /**
     * Creates new form ChonDeThi_HS
     */
    public static String theloai = null;//tên môn
    public ArrayList<ChonDeThi> ListDT;
    public static String maDe = null;//mã đề
    public static String doKho = null;//
    public static int thoiGian = 0;//
    public static int soCau = 0;

    public ChonDeThi_HS() {
        initComponents();
        lbMon.setText(theloai);
        this.connect();
        this.getMon();
        this.fillCBdokho();
        this.fillCBthoigian();
        //
        this.ListDT = new ArrayList<>();
        this.ListDT = this.fetcList();
        this.fillTable();
    }
    Connection con;

    protected void connect() {
        String uername = "tuanmayman";
        String password = "vutuan040599";
        String hosting = " sql.freeasphost.net\\MSSQL2016";
        String dbname = "databaseName=tuanmayman_Du_An_1";
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=quan_ly_sach";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://" + hosting + ";" + dbname, uername, password);
//            JOptionPane.showMessageDialog(null, "Kết nối thành công!");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Không tìm thấy Driver tương thích !");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Kết nốt thất bại !");
        }
    }
    String maMon = "";//mã môn

    protected void getMon() {
        try {
            Statement st = con.createStatement();
            String sql = "select*from MONHOC where TenMonHoc like N'" + theloai + "'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                maMon = rs.getString("Ma_MonHoc");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChonDeThi_HS.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected ArrayList<ChonDeThi> fetcList() {
        ArrayList<ChonDeThi> result = new ArrayList<>();
        try {
            Statement st = con.createStatement();
//            String sql = "select*from DETHI where Ma_MonHoc like N'" + maMon + "'";
            String sql = "select*from DETHI where Delete_At is null and Ma_MonHoc like N'" + maMon + "' and DoKho like N'" + cbDoKho.getSelectedItem() + "' and ThoiGianLamBai like " + cbTime.getSelectedItem() + "";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String Ma_DeThi = rs.getString("Ma_DeThi");
                String Ma_MonHoc = rs.getString("Ma_MonHoc");
                String Ma_GV = rs.getString("Ma_GV");
                String TenDeThi = rs.getString("TenDeThi");
                int ThoiGianLamBai = rs.getInt("ThoiGianLamBai");
                int TongSoCau = rs.getInt("TongSoCau");
                String NgayTao = rs.getString("NgayTao");
                String DoKho = rs.getString("DoKho");
                result.add(new ChonDeThi(Ma_DeThi, Ma_MonHoc, Ma_GV, TenDeThi, ThoiGianLamBai, TongSoCau, NgayTao, DoKho));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChonDeThi_HS.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    protected void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblDeThi.getModel();
        model.setRowCount(0);
        int i = 0;
        for (ChonDeThi dt : ListDT) {
            i++;
            model.addRow(new Object[]{
                i,
                dt.getMa_DeThi(),
                dt.getTenDeThi(),
                dt.getTongSoCau(),
                dt.getThoiGianLamBai(),
                dt.getDoKho()});
        }
    }

    protected void fillCBdokho() {
        try {
            Statement st = con.createStatement();
            String sql = "select distinct DoKho from DETHI where Delete_At is null and Ma_MonHoc like N'" + maMon + "'";
            ResultSet rs = st.executeQuery(sql);
            DefaultComboBoxModel cbdk = (DefaultComboBoxModel) cbDoKho.getModel();
            cbdk.removeAllElements();
            while (rs.next()) {
                String DoKho = rs.getString("DoKho");
                cbdk.addElement(DoKho);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChonDeThi_HS.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void fillCBthoigian() {
        try {
            Statement st = con.createStatement();
            String sql = "select distinct ThoiGianLamBai from DETHI where Delete_At is null and Ma_MonHoc like N'" + maMon + "' and DoKho like N'" + cbDoKho.getSelectedItem() + "'";
            ResultSet rs = st.executeQuery(sql);
            DefaultComboBoxModel cbtm = (DefaultComboBoxModel) cbTime.getModel();
            cbtm.removeAllElements();
            while (rs.next()) {
                int ThoiGianLamBai = rs.getInt("ThoiGianLamBai");
                cbtm.addElement(ThoiGianLamBai);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChonDeThi_HS.class
                    .getName()).log(Level.SEVERE, null, ex);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbMon = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbDoKho = new javax.swing.JComboBox<>();
        cbTime = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDeThi = new javax.swing.JTable();
        btnThi = new javax.swing.JButton();

        setClosable(true);
        setTitle("Chọn đề thi");

        jPanel1.setBackground(new java.awt.Color(153, 255, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Đề thi");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Môn thi:");

        lbMon.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbMon.setForeground(new java.awt.Color(255, 0, 0));
        lbMon.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbMon.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Độ khó:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Thời gian:");

        cbDoKho.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dễ", "Khó" }));
        cbDoKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDoKhoActionPerformed(evt);
            }
        });

        cbTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "15", "45" }));
        cbTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTimeActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Đề thi"));

        tblDeThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã đề thi", "Tên đề thi", "Số câu", "Thời gian", "Độ khó"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblDeThi);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnThi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TienIch/Icon/start.png"))); // NOI18N
        btnThi.setText("Bắt đầu thi");
        btnThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(255, 255, 255)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbDoKho, 0, 81, Short.MAX_VALUE)
                            .addComponent(cbTime, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbMon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 52, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnThi)
                        .addGap(61, 61, 61))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cbDoKho, cbTime, lbMon});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel2, jLabel3, jLabel4});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbMon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbDoKho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThi)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbDoKho, cbTime, lbMon});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel2, jLabel3, jLabel4});

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

    private void btnThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThiActionPerformed
        chonDe();


    }//GEN-LAST:event_btnThiActionPerformed

    private void cbDoKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDoKhoActionPerformed
//        if (cbDoKho.getSelectedItem() == null) {
        this.fillCBthoigian();
        //
        this.ListDT = new ArrayList<>();
        this.ListDT = this.fetcList();
        this.fillTable();
//        }
    }//GEN-LAST:event_cbDoKhoActionPerformed

    private void cbTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTimeActionPerformed
//        if (cbTime.getSelectedItem() == null) {
        this.ListDT = new ArrayList<>();
        this.ListDT = this.fetcList();
        this.fillTable();
//        }
    }//GEN-LAST:event_cbTimeActionPerformed
    int index = -1;

    public void chonDe() {
        index = tblDeThi.getSelectedRow();
        if (index >= 0) {
            maDe = tblDeThi.getValueAt(index, 1) + "";
            soCau = Integer.parseInt(tblDeThi.getValueAt(index, 3) + "");
            thoiGian = Integer.parseInt(tblDeThi.getValueAt(index, 4) + "");
            doKho = tblDeThi.getValueAt(index, 5) + "";
            for (JInternalFrame jif : jDesktopPaneHS.getAllFrames()) {
                jif.dispose();
            }
            QLThi ql = new QLThi();
            jDesktopPaneHS.add(ql);
            ql.setLocation((jDesktopPaneHS.getWidth() - ql.getWidth()) / 2,
                    (jDesktopPaneHS.getHeight() - ql.getHeight()) / 2);
            ql.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn đề thi !");
            return;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThi;
    public static javax.swing.JComboBox<String> cbDoKho;
    public static javax.swing.JComboBox<String> cbTime;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel lbMon;
    private javax.swing.JTable tblDeThi;
    // End of variables declaration//GEN-END:variables
}
