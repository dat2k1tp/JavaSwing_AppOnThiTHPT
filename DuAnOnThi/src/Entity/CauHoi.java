
package Entity;

import java.util.Date;


public class CauHoi {
    String maCauHoi,ma_MonHocCT,noiDung;
    Date ngayTao;
    String hinh,dapAn_A,dapAn_B,dapAn_C,dapAn_D,dapAn_Dung,doKho,delete_At,delete_User;

    String tenMonCT;
    public CauHoi() {
    }

    public CauHoi(String maCauHoi, String ma_MonHocCT, String noiDung, Date ngayTao, String hinh, String dapAn_A,
            String dapAn_B, String dapAn_C, String dapAn_D, String dapAn_Dung, String doKho) {
        this.maCauHoi = maCauHoi;
        this.ma_MonHocCT = ma_MonHocCT;
        this.noiDung = noiDung;
        this.ngayTao = ngayTao;
        this.hinh = hinh;
        this.dapAn_A = dapAn_A;
        this.dapAn_B = dapAn_B;
        this.dapAn_C = dapAn_C;
        this.dapAn_D = dapAn_D;
        this.dapAn_Dung = dapAn_Dung;
        this.doKho = doKho;
        
    }

    public CauHoi(String maCauHoi, String noiDung, String hinh, String dapAn_A, String dapAn_B, String dapAn_C, String dapAn_D, String dapAn_Dung, String doKho, String tenMonCT) {
        this.maCauHoi = maCauHoi;
        this.noiDung = noiDung;
        this.hinh = hinh;
        this.dapAn_A = dapAn_A;
        this.dapAn_B = dapAn_B;
        this.dapAn_C = dapAn_C;
        this.dapAn_D = dapAn_D;
        this.dapAn_Dung = dapAn_Dung;
        this.doKho = doKho;
        this.tenMonCT = tenMonCT;
    }

    public CauHoi(String maCauHoi, String noiDung, Date ngayTao, String doKho, String delete_At, String delete_User) {
        this.maCauHoi = maCauHoi;
        this.noiDung = noiDung;
        this.ngayTao = ngayTao;
        this.doKho = doKho;
        this.delete_At = delete_At;
        this.delete_User = delete_User;
    }

    public CauHoi(String maCauHoi, String noiDung, String doKho) {
        this.maCauHoi = maCauHoi;
        this.noiDung = noiDung;
        this.doKho = doKho;
    }
    

    
    
    

    public String getTenMonCT() {
        return tenMonCT;
    }

    public void setTenMonCT(String tenMonCT) {
        this.tenMonCT = tenMonCT;
    }
    
    

    public String getMaCauHoi() {
        return maCauHoi;
    }

    public void setMaCauHoi(String maCauHoi) {
        this.maCauHoi = maCauHoi;
    }

    public String getMa_MonHocCT() {
        return ma_MonHocCT;
    }

    public void setMa_MonHocCT(String ma_MonHocCT) {
        this.ma_MonHocCT = ma_MonHocCT;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getDapAn_A() {
        return dapAn_A;
    }

    public void setDapAn_A(String dapAn_A) {
        this.dapAn_A = dapAn_A;
    }

    public String getDapAn_B() {
        return dapAn_B;
    }

    public void setDapAn_B(String dapAn_B) {
        this.dapAn_B = dapAn_B;
    }

    public String getDapAn_C() {
        return dapAn_C;
    }

    public void setDapAn_C(String dapAn_C) {
        this.dapAn_C = dapAn_C;
    }

    public String getDapAn_D() {
        return dapAn_D;
    }

    public void setDapAn_D(String dapAn_D) {
        this.dapAn_D = dapAn_D;
    }

    public String getDapAn_Dung() {
        return dapAn_Dung;
    }

    public void setDapAn_Dung(String dapAn_Dung) {
        this.dapAn_Dung = dapAn_Dung;
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
