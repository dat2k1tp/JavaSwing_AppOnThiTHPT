/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Hello Kiên
 */
public class Dangnhap {

    public String maDN, HoTen, NgaySinh;
    public boolean GioiTinh;
    public String SDT, Email, DiaChi, Hinh, MatKhau, NgayTao;

    public Dangnhap() {
    }

    public Dangnhap(String maDN, String HoTen, String NgaySinh, boolean GioiTinh, String SDT, String Email, String DiaChi, String Hinh, String MatKhau, String NgayTao) {
        this.maDN = maDN;
        this.HoTen = HoTen;
        this.NgaySinh = NgaySinh;
        this.GioiTinh = GioiTinh;
        this.SDT = SDT;
        this.Email = Email;
        this.DiaChi = DiaChi;
        this.Hinh = Hinh;
        this.MatKhau = MatKhau;
        this.NgayTao = NgayTao;
    }

    public String getMaDN() {
        return maDN;
    }

    public void setMaDN(String maDN) {
        this.maDN = maDN;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public boolean isGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(boolean GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String Hinh) {
        this.Hinh = Hinh;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public String getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(String NgayTao) {
        this.NgayTao = NgayTao;
    }

    @Override
    public String toString() {
        return "Dangnhap{" + "maDN=" + maDN + ", HoTen=" + HoTen + ", NgaySinh=" + NgaySinh + ", GioiTinh=" + GioiTinh + ", SDT=" + SDT + ", Email=" + Email + ", DiaChi=" + DiaChi + ", Hinh=" + Hinh + ", MatKhau=" + MatKhau + ", NgayTao=" + NgayTao + '}';
    }

}
