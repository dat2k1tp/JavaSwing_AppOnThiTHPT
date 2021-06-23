
package Entity;

import java.util.Date;


public class GiaoVien {
    String maGV,hoTen;
    Date ngaySinh,ngayTao;
    Boolean gioiTinh;
    String sdt,email,diaChi,hinh,matKhau,delete_At,delete_User;

    public GiaoVien() {
    }

    public GiaoVien(String maGV, String hoTen, Date ngaySinh, Boolean gioiTinh,
            String sdt, String email, String diaChi, String hinh, String matKhau, Date ngayTao) {
        this.maGV = maGV;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.email = email;
        this.diaChi = diaChi;
        this.hinh = hinh;
        this.matKhau = matKhau;
        this.ngayTao = ngayTao;
    }

    public GiaoVien(String maGV, String hoTen, Date ngaySinh,
    Date ngayTao, Boolean gioiTinh, String sdt, String email, String diaChi,
    String hinh, String matKhau, String delete_At, String delete_User) {
        this.maGV = maGV;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.ngayTao = ngayTao;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.email = email;
        this.diaChi = diaChi;
        this.hinh = hinh;
        this.matKhau = matKhau;
        this.delete_At = delete_At;
        this.delete_User = delete_User;
    }
    
    

    public String getMaGV() {
        return maGV;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public Boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    

    public String getDelete_At() {
        return delete_At;
    }

    public void setDelete_At(String delete_At) {
        this.delete_At = delete_At;
    }

    public String getDelete_User() {
        return delete_User;
    }

    public void setDelete_User(String delete_User) {
        this.delete_User = delete_User;
    }
    
}
