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
public class ChonDeThi {

    public String Ma_DeThi, Ma_MonHoc, Ma_GV, TenDeThi;
    public int ThoiGianLamBai, TongSoCau;
    public String NgayTao, DoKho;

    public ChonDeThi() {
    }

    public ChonDeThi(String Ma_DeThi, String Ma_MonHoc, String Ma_GV, String TenDeThi, int ThoiGianLamBai, int TongSoCau, String NgayTao, String DoKho) {
        this.Ma_DeThi = Ma_DeThi;
        this.Ma_MonHoc = Ma_MonHoc;
        this.Ma_GV = Ma_GV;
        this.TenDeThi = TenDeThi;
        this.ThoiGianLamBai = ThoiGianLamBai;
        this.TongSoCau = TongSoCau;
        this.NgayTao = NgayTao;
        this.DoKho = DoKho;
    }

    public String getMa_DeThi() {
        return Ma_DeThi;
    }

    public void setMa_DeThi(String Ma_DeThi) {
        this.Ma_DeThi = Ma_DeThi;
    }

    public String getMa_MonHoc() {
        return Ma_MonHoc;
    }

    public void setMa_MonHoc(String Ma_MonHoc) {
        this.Ma_MonHoc = Ma_MonHoc;
    }

    public String getMa_GV() {
        return Ma_GV;
    }

    public void setMa_GV(String Ma_GV) {
        this.Ma_GV = Ma_GV;
    }

    public String getTenDeThi() {
        return TenDeThi;
    }

    public void setTenDeThi(String TenDeThi) {
        this.TenDeThi = TenDeThi;
    }

    public int getThoiGianLamBai() {
        return ThoiGianLamBai;
    }

    public void setThoiGianLamBai(int ThoiGianLamBai) {
        this.ThoiGianLamBai = ThoiGianLamBai;
    }

    public int getTongSoCau() {
        return TongSoCau;
    }

    public void setTongSoCau(int TongSoCau) {
        this.TongSoCau = TongSoCau;
    }

    public String getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(String NgayTao) {
        this.NgayTao = NgayTao;
    }

    public String getDoKho() {
        return DoKho;
    }

    public void setDoKho(String DoKho) {
        this.DoKho = DoKho;
    }

    @Override
    public String toString() {
        return "ChonDeThi{" + "Ma_DeThi=" + Ma_DeThi + ", Ma_MonHoc=" + Ma_MonHoc + ", Ma_GV=" + Ma_GV + ", TenDeThi=" + TenDeThi + ", ThoiGianLamBai=" + ThoiGianLamBai + ", TongSoCau=" + TongSoCau + ", NgayTao=" + NgayTao + ", DoKho=" + DoKho + '}';
    }

}
