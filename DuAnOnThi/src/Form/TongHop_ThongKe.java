/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import Entity.ThongKeTab4;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hello Kiên
 */
public class TongHop_ThongKe extends javax.swing.JInternalFrame {

    /**
     * Creates new form TongHop_ThongKe
     */
    public TongHop_ThongKe() {
        initComponents();
        this.connect();
        //tab1
        this.fillcomboboxTab1();
        this.filltbltab1();
        //tab2
        this.fillcomboboxTab2();
        this.filltbltab2();
        //tab3
        this.fillcomboboxtab3();
        this.filltbltab3_1();
        this.filltbltab3_2();
        this.txtTongMon();
        //tab4
        this.filltbltab4();
        this.gtTN_CNtab4();
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
//code tab1

    protected void fillcomboboxTab1() {
        try {
            Statement st = con.createStatement();
            String query = "select TenMonHoc from MONHOC";
            ResultSet rs = st.executeQuery(query);
            DefaultComboBoxModel cb1 = (DefaultComboBoxModel) cbMontab1.getModel();
            cb1.removeAllElements();
            while (rs.next()) {
                String mon = rs.getString("TenMonHoc");
                cb1.addElement(mon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TongHop_ThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void filltbltab1() {
        try {
            Statement st = con.createStatement();
            String query = "select hs.Ma_HocSinh as Ma,hs.HoTen as Ten,min(kq.Diem) as DTN,max(kq.Diem) as DCN,avg(kq.Diem) as DTB\n"
                    + "from HocSinh hs inner join KETQUA kq on hs.Ma_HocSinh=kq.Ma_HocSinh\n"
                    + "	inner join DETHI dt on kq.Ma_DeThi=dt.Ma_DeThi\n"
                    + "	inner join MONHOC mh on dt.Ma_MonHoc=mh.Ma_MonHoc\n"
                    + "where mh.TenMonHoc like N'" + cbMontab1.getSelectedItem() + "'\n"
                    + "group by  hs.Ma_HocSinh,hs.HoTen";
            ResultSet rs = st.executeQuery(query);
            DefaultTableModel tbl1 = (DefaultTableModel) tbltab1.getModel();
            tbl1.setRowCount(0);
            int i = 0;
            while (rs.next()) {
                i++;
                String MAHS = rs.getString("Ma");
                String TenHS = rs.getString("Ten");
                float DTN = rs.getFloat("DTN");
                float DCN = rs.getFloat("DCN");
                float DTB = rs.getFloat("DTB");
                String xeploai = "";
                if (DTB >= 8) {
                    xeploai = "Giỏi";
                } else if (DTB >= 6.5) {
                    xeploai = "Khá";
                } else if (DTB >= 4.5) {
                    xeploai = "Trung bình";
                } else {
                    xeploai = "Yếu";
                }
                tbl1.addRow(new Object[]{i, MAHS, TenHS, DTN, DCN, DTB, xeploai});
            }

        } catch (SQLException ex) {
            Logger.getLogger(TongHop_ThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//code tab2
    protected void fillcomboboxTab2() {
        try {
            Statement st = con.createStatement();
            String query = "select distinct YEAR(NgayTao) as Nam from HocSinh order by Nam desc";
            ResultSet rs = st.executeQuery(query);
            DefaultComboBoxModel cb2 = (DefaultComboBoxModel) cbNamtab2.getModel();
            cb2.removeAllElements();
            while (rs.next()) {
                String ngay = rs.getString("Nam");
                cb2.addElement(ngay);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TongHop_ThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void filltbltab2() {
        try {
            Statement st = con.createStatement();
            String query = "select MONTH(NgayTao) as Thang, count(Ma_HocSinh) as SoHS, min(NgayTao) as DKsom,max(NgayTao) as DKmuon \n"
                    + "from HocSinh where YEAR(NgayTao) like N'" + cbNamtab2.getSelectedItem() + "' group by MONTH(NgayTao)";
            ResultSet rs = st.executeQuery(query);
            DefaultTableModel tbl2 = (DefaultTableModel) tbltab2.getModel();
            tbl2.setRowCount(0);
            while (rs.next()) {
                String thang = rs.getString("Thang");
                int soHS = rs.getInt("SoHS");
                String DKsom = rs.getString("DKsom");
                String DKmuon = rs.getString("DKmuon");
                tbl2.addRow(new Object[]{thang, soHS, DKsom, DKmuon});
            }
        } catch (SQLException ex) {
            Logger.getLogger(TongHop_ThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//code tab3
    protected void fillcomboboxtab3() {
        try {
            Statement st = con.createStatement();
            String query = "select distinct YEAR(NgayTao) as Nam from DETHI order by Nam desc";
            ResultSet rs = st.executeQuery(query);
            DefaultComboBoxModel cb3 = (DefaultComboBoxModel) cbNamtab3.getModel();
            cb3.removeAllElements();
            while (rs.next()) {
                String ngay = rs.getString("Nam");
                cb3.addElement(ngay);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TongHop_ThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void filltbltab3_1() {
        try {
            Statement st = con.createStatement();
            String query = "select MONTH(NgayTao) as Thang,count(Ma_DeThi) as SoDT from DETHI\n"
                    + "where YEAR(NgayTao) like N'" + cbNamtab3.getSelectedItem() + "' group by MONTH(NgayTao)";
            ResultSet rs = st.executeQuery(query);
            DefaultTableModel tbl3_1 = (DefaultTableModel) tbltab3_1.getModel();
            tbl3_1.setRowCount(0);
            while (rs.next()) {
                String thang = rs.getString("Thang");
                int soDT = rs.getInt("SoDT");
                tbl3_1.addRow(new Object[]{thang, soDT});
            }
        } catch (SQLException ex) {
            Logger.getLogger(TongHop_ThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void filltbltab3_2() {
        try {
            Statement st = con.createStatement();
            String query = "select   mh.TenMonHoc as Mon, count(dt.Ma_DeThi) as SoDT \n"
                    + "from DETHI dt inner join MONHOC mh on dt.Ma_MonHoc=mh.Ma_MonHoc\n"
                    + "where YEAR(dt.NgayTao) like N'" + cbNamtab3.getSelectedItem() + "'\n"
                    + "group by mh.TenMonHoc";
            ResultSet rs = st.executeQuery(query);
            DefaultTableModel tbl3_2 = (DefaultTableModel) tbltab3_2.getModel();
            tbl3_2.setRowCount(0);
            while (rs.next()) {
                String mon = rs.getString("Mon");
                int soDT = rs.getInt("SoDT");
                tbl3_2.addRow(new Object[]{mon, soDT});
            }
        } catch (SQLException ex) {
            Logger.getLogger(TongHop_ThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void txtTongMon() {
        try {
            Statement st = con.createStatement();
            String query = "select count(Ma_DeThi) as SoDT from DETHI where YEAR(NgayTao) like N'" + cbNamtab3.getSelectedItem() + "'";
            ResultSet rs = st.executeQuery(query);
            int soDT = 0;
            while (rs.next()) {
                soDT = rs.getInt("SoDT");

            }
            System.out.println(soDT);
            txtSodethi.setText(soDT + "");
        } catch (SQLException ex) {
            Logger.getLogger(TongHop_ThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//code tab4
    int i4 = 0;
    ArrayList<ThongKeTab4> list = new ArrayList<>();

    protected void filltbltab4() {
        try {
            Statement st = con.createStatement();
            String query = "select mh.TenMonHoc as Ten,min(kq.Diem) as DTN,max(kq.Diem) as DCN,avg(kq.Diem) as DTB\n"
                    + "from KETQUA kq inner join DETHI dt on kq.Ma_DeThi=dt.Ma_DeThi\n"
                    + "	inner join MONHOC mh on dt.Ma_MonHoc=mh.Ma_MonHoc\n"
                    + "group by mh.TenMonHoc\n"
                    + "order by avg(kq.Diem) desc";
            ResultSet rs = st.executeQuery(query);
            DefaultTableModel tbl4 = (DefaultTableModel) tbltab4.getModel();
            tbl4.setRowCount(0);

            while (rs.next()) {
                i4++;
                String ten = rs.getString("Ten");
                float DTN = rs.getFloat("DTN");
                float DCN = rs.getFloat("DCN");
                float DTB = rs.getFloat("DTB");
                tbl4.addRow(new Object[]{i4, ten, DTN, DCN, DTB});
                list.add(new ThongKeTab4(i4, ten, DTN, DCN, DTB));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TongHop_ThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void gtTN_CNtab4() {
        txtDiemcao.disable();
        txtDiemthap.disable();
        txtMoncao.disable();
        txtMonthap.disable();
        i4 = 0;
        txtMoncao.setText(tbltab4.getValueAt(i4, 1) + "");
        txtDiemcao.setText(tbltab4.getValueAt(i4, 4) + "");
        //thap
        if (list.size() > 0) {
            i4 = list.size() - 1;
        }
        txtMonthap.setText(tbltab4.getValueAt(i4, 1) + "");
        txtDiemthap.setText(tbltab4.getValueAt(i4, 4) + "");

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
        tabs = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbMontab1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbltab1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cbNamtab2 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbltab2 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cbNamtab3 = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbltab3_2 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbltab3_1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtSodethi = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbltab4 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtMoncao = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtMonthap = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtDiemcao = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtDiemthap = new javax.swing.JTextField();

        setClosable(true);
        setTitle("Tổng hợp & Thống kê");

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Tổng hợp & Thống kê");

        jPanel2.setBackground(new java.awt.Color(153, 255, 204));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Chọn môn:");

        cbMontab1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Toán", "Lý", "Hóa" }));
        cbMontab1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMontab1ActionPerformed(evt);
            }
        });

        tbltab1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Stt", "Mã HS", "Tên HS", "Điểm TN", "Điểm CN", "Điểm TB", "Xếp loại"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbltab1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(cbMontab1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbMontab1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.addTab("BẢNG ĐIỂM THEO MÔN", jPanel2);

        jPanel3.setBackground(new java.awt.Color(153, 255, 204));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Chọn năm:");

        cbNamtab2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020", "2019", "2018" }));
        cbNamtab2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNamtab2ActionPerformed(evt);
            }
        });

        tbltab2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tháng", "Tổng số HS", "Đăng ký SN", "Đăng ký MN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbltab2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(cbNamtab2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbNamtab2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.addTab("SỐ LƯỢNG HỌC SINH", jPanel3);

        jPanel4.setBackground(new java.awt.Color(153, 255, 204));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Chọn năm:");

        cbNamtab3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2020", "2019", "2018" }));
        cbNamtab3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNamtab3ActionPerformed(evt);
            }
        });

        tbltab3_2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Môn", "Số đề thi được tạo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tbltab3_2);

        tbltab3_1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Tháng", "Số đề thi được tạo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tbltab3_1);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Tổng số đề thi:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(cbNamtab3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSodethi, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbNamtab3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtSodethi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );

        tabs.addTab("SỐ ĐỀ THI", jPanel4);

        jPanel5.setBackground(new java.awt.Color(153, 255, 204));

        tbltab4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Stt", "Tên môn", "Điểm TN", "Điểm CN", "Điểm TB"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tbltab4);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Môn có điểm TB cao nhất:");

        txtMoncao.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Môn có điểm TB thấp nhất:");

        txtMonthap.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Điểm:");

        txtDiemcao.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Điểm:");

        txtDiemthap.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(31, 31, 31)
                                .addComponent(txtMoncao, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(25, 25, 25)
                                .addComponent(txtMonthap)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(71, 71, 71)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDiemcao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiemthap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtDiemcao, txtDiemthap, txtMoncao, txtMonthap});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtMoncao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtMonthap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDiemcao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDiemthap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))))
                .addContainerGap(131, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtDiemcao, txtDiemthap, txtMoncao, txtMonthap});

        tabs.addTab("ĐIỂM TB MÔN", jPanel5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabs)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabs)
                .addContainerGap())
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

    private void cbNamtab3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNamtab3ActionPerformed
        if (cbNamtab3.getSelectedItem() != null) {
            this.filltbltab3_1();
            this.filltbltab3_2();
            this.txtTongMon();
        }
    }//GEN-LAST:event_cbNamtab3ActionPerformed

    private void cbNamtab2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNamtab2ActionPerformed
        if (cbNamtab2.getSelectedItem() != null) {
            this.filltbltab2();
        }
    }//GEN-LAST:event_cbNamtab2ActionPerformed

    private void cbMontab1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMontab1ActionPerformed
        if (cbMontab1.getSelectedItem() != null) {
            this.filltbltab1();
        }
    }//GEN-LAST:event_cbMontab1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbMontab1;
    private javax.swing.JComboBox<String> cbNamtab2;
    private javax.swing.JComboBox<String> cbNamtab3;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    public static javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tbltab1;
    private javax.swing.JTable tbltab2;
    private javax.swing.JTable tbltab3_1;
    private javax.swing.JTable tbltab3_2;
    private javax.swing.JTable tbltab4;
    private javax.swing.JTextField txtDiemcao;
    private javax.swing.JTextField txtDiemthap;
    private javax.swing.JTextField txtMoncao;
    private javax.swing.JTextField txtMonthap;
    private javax.swing.JTextField txtSodethi;
    // End of variables declaration//GEN-END:variables
}
