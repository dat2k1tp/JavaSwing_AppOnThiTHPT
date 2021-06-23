
package Entity;

import java.util.Date;


public class DeThi {
    
    String maDeThi,maMonHoc,maGV,tenDeThi;
    Date ngayTao;
    int tongSoCau,thoiGianLamBai;
    String doKho,delete_At,delete_User;

    public DeThi() {
    }

    public DeThi(String maDeThi, String maMonHoc, String maGV, String tenDeThi, int thoiGianLamBai, Date ngayTao, int tongSoCau, String doKho) {
        this.maDeThi = maDeThi;
        this.maMonHoc = maMonHoc;
        this.maGV = maGV;
        this.tenDeThi = tenDeThi;
        this.thoiGianLamBai = thoiGianLamBai;
        this.ngayTao = ngayTao;
        this.tongSoCau = tongSoCau;
        this.doKho = doKho;
    }

    public DeThi(String maDeThi, String maMonHoc, String maGV, String tenDeThi, Date ngayTao, int tongSoCau, int thoiGianLamBai, String doKho, String delete_At, String delete_User) {
        this.maDeThi = maDeThi;
        this.maMonHoc = maMonHoc;
        this.maGV = maGV;
        this.tenDeThi = tenDeThi;
        this.ngayTao = ngayTao;
        this.tongSoCau = tongSoCau;
        this.thoiGianLamBai = thoiGianLamBai;
        this.doKho = doKho;
        this.delete_At = delete_At;
        this.delete_User = delete_User;
    }
    

    public String getMaDeThi() {
        return maDeThi;
    }

    public void setMaDeThi(String maDeThi) {
        this.maDeThi = maDeThi;
    }

    public String getMaMonHoc() {
        return maMonHoc;
    }

    public void setMaMonHoc(String maMonHoc) {
        this.maMonHoc = maMonHoc;
    }

    public String getMaGV() {
        return maGV;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    public String getTenDeThi() {
        return tenDeThi;
    }

    public int getThoiGianLamBai() {
        return thoiGianLamBai;
    }

    public void setThoiGianLamBai(int thoiGianLamBai) {
        this.thoiGianLamBai = thoiGianLamBai;
    }
    
    

    public void setTenDeThi(String tenDeThi) {
        this.tenDeThi = tenDeThi;
    }

    

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public int getTongSoCau() {
        return tongSoCau;
    }

    public void setTongSoCau(int tongSoCau) {
        this.tongSoCau = tongSoCau;
    }

    public String getDoKho() {
        return doKho;
    }

    public void setDoKho(String doKho) {
        this.doKho = doKho;
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
