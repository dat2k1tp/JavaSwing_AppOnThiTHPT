
package Entity;

import java.util.Date;


public class MonHocChiTiet {
    
    String maMonHocCT,tenMonHc,ma_MonHoc,hinh,ghiChu;
    Date ngayTao;
    String delete_At,delete_User;

    public MonHocChiTiet() {
    }

    public MonHocChiTiet(String maMonHocCT, String tenMonHc, String ma_MonHoc, String hinh, String ghiChu, Date ngayTao) {
        this.maMonHocCT = maMonHocCT;
        this.tenMonHc = tenMonHc;
        this.ma_MonHoc = ma_MonHoc;
        this.hinh = hinh;
        this.ghiChu = ghiChu;
        this.ngayTao = ngayTao;
    }

    public MonHocChiTiet(String maMonHocCT, String tenMonHc, String ma_MonHoc, String hinh, String ghiChu, Date ngayTao, String delete_At, String delete_User) {
        this.maMonHocCT = maMonHocCT;
        this.tenMonHc = tenMonHc;
        this.ma_MonHoc = ma_MonHoc;
        this.hinh = hinh;
        this.ghiChu = ghiChu;
        this.ngayTao = ngayTao;
        this.delete_At = delete_At;
        this.delete_User = delete_User;
    }
    

    public String getMaMonHocCT() {
        return maMonHocCT;
    }

    public void setMaMonHocCT(String maMonHocCT) {
        this.maMonHocCT = maMonHocCT;
    }

    public String getTenMonHc() {
        return tenMonHc;
    }

    public void setTenMonHc(String tenMonHc) {
        this.tenMonHc = tenMonHc;
    }

    public String getMa_MonHoc() {
        return ma_MonHoc;
    }

    public void setMa_MonHoc(String ma_MonHoc) {
        this.ma_MonHoc = ma_MonHoc;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
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
