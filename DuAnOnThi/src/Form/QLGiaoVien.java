package Form;

import DAO.GiaoVienDAO;
import Entity.Auth;
import Entity.GiaoVien;
import java.sql.Connection;
import TienIch_HoTro.Connections;
import TienIch_HoTro.Dialong;
import TienIch_HoTro.XConvert;

import java.awt.Image;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class QLGiaoVien extends javax.swing.JInternalFrame {

    private Connection con;
    DefaultTableModel model;
    String fileNameImage = "";
    List<GiaoVien> listGV = new ArrayList<>();
    int index=-1;
    
    public QLGiaoVien() {
        initComponents();
        con = new Connections().ketNotDatabase();
        //listGV = new GiaoVienDAO().selectAll(con);
        fillToTable();
    }

    void fillToTable() {
        model = (DefaultTableModel) tableList.getModel();
        model.setRowCount(0);
        listGV = new GiaoVienDAO().selectAll(con);
        System.out.println("list ban đầu : " + listGV.size());
        for (GiaoVien gv : listGV) {
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
                gv.getNgayTao()});
        }
    }

    void showDetail(GiaoVien gv) {

        txtMaGV.setText(gv.getMaGV());
        txtHoTen.setText(gv.getHoTen());
        txtSDT.setText(gv.getSdt());
        txtDiaChi.setText(gv.getDiaChi());
        txtEmail.setText(gv.getEmail());
        Boolean gioiTinh = gv.getGioiTinh();

        if (gioiTinh == true) {
            radioNam.setSelected(true);
        } else {
            radioNu.setSelected(true);
        }

        updateImage(gv.getHinh());
        fileNameImage = gv.getHinh();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date d = gv.getNgaySinh();
        txtNgaySinh.setText(sdf.format(d));
    }

    void clear() {

        txtMaGV.setText("");
        txtHoTen.setText("");
        txtSDT.setText("");
        txtDiaChi.setText("");
        txtEmail.setText("");
        radioNam.setSelected(true);
        txtNgaySinh.setText("");
        labelAnh.setIcon(null);
        txtMaGV.setEnabled(true);
        txtMatKhau.setEnabled(true);
         txtXacNhanMatKhau.setEnabled(true);
    }

    void updateImage(String hinh) {
        ImageIcon image = new ImageIcon("src\\TienIch\\Icon\\"+hinh);
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

    boolean checkNull() {
        if (txtMaGV.getText().equals("")) {
            Dialong.alert(this, "Mã  không được để trống !");
            txtMaGV.requestFocus();
            return false;
        }

        if (txtHoTen.getText().equals("")) {
            Dialong.alert(this, "Họ tên không được để trống !");
            txtHoTen.requestFocus();
            return false;
        }

        if (txtNgaySinh.getText().equals("")) {
            Dialong.alert(this, "Ngày sinh  không được để trống !");
            txtNgaySinh.requestFocus();
            return false;
        } else {
            String ngaySinh = txtNgaySinh.getText();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date d;
            try {
                d = sdf.parse(ngaySinh);
            } catch (Exception e) {
                Dialong.alert(this, "Ngày sinh không đúng định dạng !");
                txtNgaySinh.requestFocus();
                return false;
            }
        }

        if (txtSDT.getText().equals("")) {
            Dialong.alert(this, "Số điện thoại không được để trống !");
            txtSDT.requestFocus();
            return false;
        }

        if (txtEmail.getText().equals("")) {
            Dialong.alert(this, "Email  không được để trống !");
            txtEmail.requestFocus();
            return false;
        } else {
            try {
                String checkEmail = "\\w+@\\w+(\\.\\w+){1,2}";

                if (!txtEmail.getText().matches(checkEmail)) {
                    Dialong.alert(this, "Email ko dung dinh dang : VD : abc123@gmail.com !");
                    txtEmail.requestFocus();
                    return false;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (txtDiaChi.getText().equals("")) {
            Dialong.alert(this, "Địa chỉ không được để trống !");
            txtDiaChi.requestFocus();
            return false;
        }

        if (labelAnh.getIcon() == null) {
            Dialong.alert(this, "Ảnh không để trống !");
            return false;
        }

        return true;
    }

    boolean checkTrung() {
        List<GiaoVien> list = new GiaoVienDAO().selectAll(con);
        if (txtMatKhau.getText().equals("")) {
            Dialong.alert(this, "Mật khẩu không được để trống !");
            txtMatKhau.requestFocus();
            return false;
        }

        if (txtXacNhanMatKhau.getText().equals("")) {
            Dialong.alert(this, "Xác nhận mật khẩu không được để trống !");
            txtXacNhanMatKhau.requestFocus();
            return false;
        } else {
            String matKhau = txtMatKhau.getText();
            if (!txtXacNhanMatKhau.getText().equals(matKhau)) {
                Dialong.alert(this, "Xác nhận mật khẩu không khớp với mật khẩu đã nhập !");
                txtXacNhanMatKhau.requestFocus();
                return false;
            }
        }

        try {
            String maGV = txtMaGV.getText();
            String sdt = txtSDT.getText();
            String email = txtEmail.getText();

            for (GiaoVien gv : list) {
                if (gv.getMaGV().equals(maGV)) {
                    Dialong.alert(this, "Mã đã tồn tại mời nhập mã khác !");
                    txtMaGV.requestFocus();
                    return false;
                }
            }
            for (GiaoVien gv : list) {
                if (gv.getSdt().equals(sdt)) {
                    Dialong.alert(this, "Số điện thoại đã tồn tại mời nhập số điện thoại khác !");
                    txtSDT.requestFocus();
                    return false;
                }
            }
            for (GiaoVien gv : list) {
                if (gv.getEmail().equals(email)) {
                    Dialong.alert(this, "Eail đã tồn tại mời nhập email khác !");
                    txtEmail.requestFocus();
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    GiaoVien setInsert() {
        GiaoVien gv = new GiaoVien();
        try {
            gv.setMaGV(txtMaGV.getText());
            gv.setHoTen(txtHoTen.getText());
            Date dateNgaySinh = new SimpleDateFormat("dd-MM-yyyy").parse(txtNgaySinh.getText());
            gv.setNgaySinh(dateNgaySinh);
            gv.setEmail(txtEmail.getText());
            gv.setDiaChi(txtDiaChi.getText());
            gv.setSdt(txtSDT.getText());
            gv.setMatKhau(txtMatKhau.getText());
            gv.setNgayTao(XConvert.now());
            Boolean gioiTinh;
            if (radioNam.isSelected()) {
                gioiTinh = true;
            } else {
                gioiTinh = false;
            }
            gv.setGioiTinh(gioiTinh);

            gv.setHinh(fileNameImage);

        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        return gv;
    }

    GiaoVien setUpdate() {
        GiaoVien gv = new GiaoVien();
        try {
            gv.setMaGV(txtMaGV.getText());
            gv.setHoTen(txtHoTen.getText());
            Date dateNgaySinh = new SimpleDateFormat("dd-MM-yyyy").parse(txtNgaySinh.getText());
            gv.setNgaySinh(dateNgaySinh);
            gv.setEmail(txtEmail.getText());
            gv.setDiaChi(txtDiaChi.getText());
            gv.setSdt(txtSDT.getText());
            Boolean gioiTinh;
            if (radioNam.isSelected()) {
                gioiTinh = true;
            } else {
                gioiTinh = false;
            }
            gv.setGioiTinh(gioiTinh);

            gv.setHinh(fileNameImage);

        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        return gv;
    }
    
    GiaoVien setSoft_Delete(){
        GiaoVien gv = new GiaoVien();
        try {
            String delete_At = String.valueOf(java.time.LocalDate.now() + " " + java.time.LocalTime.now());
            gv.setDelete_At(delete_At);
            gv.setDelete_User(Auth.user.getMaDN());
            gv.setMaGV(txtMaGV.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gv;
    }

    void insert() {
        try {
            if (checkNull()) {
                if (checkTrung()) {
                    GiaoVien gv = setInsert();
                    new GiaoVienDAO().insert(gv, con);

                    fillToTable();
                    System.out.println("ok ok ok !!!");
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
                GiaoVien gv = setUpdate();
                new GiaoVienDAO().update(gv, con);
                fillToTable();
                System.out.println("Sửa ok ok ok !!!");
                Dialong.alert(this, "Sửa thành công !");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Dialong.alert(this, "Sửa thất bại !");
        }
    }
    
    void soft_Delete(){
        try {
            int chon=JOptionPane.showConfirmDialog(this,"Bạn có muốn xóa giáo viên này không ?");
             if(chon!=JOptionPane.YES_OPTION){ return;}
            GiaoVien gv = setSoft_Delete();
            new GiaoVienDAO().delete(gv, con);
            fillToTable();
            Dialong.alert(this, "Xóa thành công !");
            clear();
            return;
        } catch (Exception e) {
            Dialong.alert(this, "Xóa thất bại !");
            e.printStackTrace();
            return;
        }
    }

    void timKiem() {
        try {
            String hoTen = txtSearch.getText();
            List<GiaoVien> list = new GiaoVienDAO().selectAll_By_Ten(hoTen, con);
            model = (DefaultTableModel) tableList.getModel();
            System.out.println("list size : " + list.size());
            System.out.println("---------------- ko hiểu ------------");
            model.setRowCount(0);
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
                    gv.getNgayTao()});
            }
            System.out.println("true true true !!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableList = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaGV = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        radioNam = new javax.swing.JRadioButton();
        radioNu = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDiaChi = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        labelAnh = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtXacNhanMatKhau = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 255, 204));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Quản Lý Giáo Viên");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Quản Lý Giáo Viên");

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(153, 255, 204));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Tìm kiếm theo tên");

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
                "Mã giáo viên", "Họ tên", "Ngày sinh", "Giới tính", "SĐT", "Email", "Địa chỉ", "Ngày tạo"
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
        jScrollPane2.setViewportView(tableList);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 736, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, 47))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        jTabbedPane1.addTab("Danh Sách", jPanel2);

        jPanel1.setBackground(new java.awt.Color(153, 255, 204));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Mã giáo viên");

        txtMaGV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Họ tên");

        txtHoTen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Ngày sinh");

        txtNgaySinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Số điện thoại");

        txtSDT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Giới tính");

        buttonGroup1.add(radioNam);
        radioNam.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        radioNam.setText("Nam");

        buttonGroup1.add(radioNu);
        radioNu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        radioNu.setText("Nữ");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Email");

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Hình");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Địa chỉ");

        txtDiaChi.setColumns(20);
        txtDiaChi.setRows(5);
        jScrollPane1.setViewportView(txtDiaChi);

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

        btnFirst.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnFirst.setText("|<");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPrev.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnPrev.setText("<<");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnNext.setText(">>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnLast.setText(">|");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        labelAnh.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        labelAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelAnhMouseClicked(evt);
            }
        });

        txtMatKhau.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Mật khẩu");

        txtXacNhanMatKhau.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Xác nhận mật khẩu");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addGap(106, 106, 106)
                        .addComponent(btnFirst)
                        .addGap(18, 18, 18)
                        .addComponent(btnPrev)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNext)
                        .addGap(18, 18, 18)
                        .addComponent(btnLast))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaGV, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(radioNam)
                                        .addGap(18, 18, 18)
                                        .addComponent(radioNu))
                                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtXacNhanMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(67, 67, 67)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(53, 53, 53))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnFirst, btnLast, btnNext, btnPrev});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(labelAnh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(txtMaGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(radioNam)
                                    .addComponent(radioNu))
                                .addComponent(jLabel6))
                            .addGap(4, 4, 4)))
                    .addComponent(jLabel8))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtXacNhanMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrev)
                    .addComponent(btnLast)
                    .addComponent(btnNext))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnFirst, btnLast, btnNext, btnPrev});

        jTabbedPane1.addTab("Cập nhật", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(258, 258, 258))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableListMouseClicked
        index = tableList.getSelectedRow();
        if(evt.getClickCount()==2){
            if (index >= 0) {
            txtMaGV.setEnabled(false);
            txtMatKhau.setEnabled(false);
            txtXacNhanMatKhau.setEnabled(false);
            GiaoVien gv = listGV.get(index);
            showDetail(gv);
            jTabbedPane1.setSelectedIndex(1);
        }
        }
        
    }//GEN-LAST:event_tableListMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        clear();
    }//GEN-LAST:event_jButton4ActionPerformed
    void first() {
        index = 0;
        GiaoVien gv = listGV.get(index);
        showDetail(gv);
    }
    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        first();
    }//GEN-LAST:event_btnFirstActionPerformed

    void prev() {
        if (index == listGV.size() - 1) {
            index = 0;
            GiaoVien gv = listGV.get(index);
            showDetail(gv);
        } else if (index < listGV.size()) {
            index++;
            GiaoVien gv = listGV.get(index);
            showDetail(gv);
        }
    }
    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        next();
    }//GEN-LAST:event_btnPrevActionPerformed
    void next() {
        if (index > 0) {
            index--;
            GiaoVien gv = listGV.get(index);
            showDetail(gv);
        } else if (index == 0) {
            index = listGV.size() - 1;
            GiaoVien gv = listGV.get(index);
            showDetail(gv);
        }
    }
    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
       prev();
    }//GEN-LAST:event_btnNextActionPerformed

    void last() {
        index = listGV.size() - 1;
        GiaoVien gv = listGV.get(index);
        showDetail(gv);
    }
    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        last();
    }//GEN-LAST:event_btnLastActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        insert();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void labelAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelAnhMouseClicked
        setImage();
    }//GEN-LAST:event_labelAnhMouseClicked

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        timKiem();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        update();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        soft_Delete();
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelAnh;
    private javax.swing.JRadioButton radioNam;
    private javax.swing.JRadioButton radioNu;
    private javax.swing.JTable tableList;
    private javax.swing.JTextArea txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaGV;
    private javax.swing.JTextField txtMatKhau;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtXacNhanMatKhau;
    // End of variables declaration//GEN-END:variables
}
