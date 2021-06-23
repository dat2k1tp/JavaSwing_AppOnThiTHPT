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
public class EntityThi {

    public String Ma_CauHoi,NoiDung_CauHoi, DapAn_A, DapAn_B, DapAn_C, DapAn_D, DapAn_Dung, Hinh, DapAn_Chon;

    public EntityThi() {
    }

    public EntityThi(String Ma_CauHoi, String NoiDung_CauHoi, String DapAn_A, String DapAn_B, String DapAn_C, String DapAn_D, String DapAn_Dung, String Hinh, String DapAn_Chon) {
        this.Ma_CauHoi = Ma_CauHoi;
        this.NoiDung_CauHoi = NoiDung_CauHoi;
        this.DapAn_A = DapAn_A;
        this.DapAn_B = DapAn_B;
        this.DapAn_C = DapAn_C;
        this.DapAn_D = DapAn_D;
        this.DapAn_Dung = DapAn_Dung;
        this.Hinh = Hinh;
        this.DapAn_Chon = DapAn_Chon;
    }

    public String getMa_CauHoi() {
        return Ma_CauHoi;
    }

    public void setMa_CauHoi(String Ma_CauHoi) {
        this.Ma_CauHoi = Ma_CauHoi;
    }

    public String getNoiDung_CauHoi() {
        return NoiDung_CauHoi;
    }

    public void setNoiDung_CauHoi(String NoiDung_CauHoi) {
        this.NoiDung_CauHoi = NoiDung_CauHoi;
    }

    public String getDapAn_A() {
        return DapAn_A;
    }

    public void setDapAn_A(String DapAn_A) {
        this.DapAn_A = DapAn_A;
    }

    public String getDapAn_B() {
        return DapAn_B;
    }

    public void setDapAn_B(String DapAn_B) {
        this.DapAn_B = DapAn_B;
    }

    public String getDapAn_C() {
        return DapAn_C;
    }

    public void setDapAn_C(String DapAn_C) {
        this.DapAn_C = DapAn_C;
    }

    public String getDapAn_D() {
        return DapAn_D;
    }

    public void setDapAn_D(String DapAn_D) {
        this.DapAn_D = DapAn_D;
    }

    public String getDapAn_Dung() {
        return DapAn_Dung;
    }

    public void setDapAn_Dung(String DapAn_Dung) {
        this.DapAn_Dung = DapAn_Dung;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String Hinh) {
        this.Hinh = Hinh;
    }

    public String getDapAn_Chon() {
        return DapAn_Chon;
    }

    public void setDapAn_Chon(String DapAn_Chon) {
        this.DapAn_Chon = DapAn_Chon;
    }

    @Override
    public String toString() {
        return "entityButton{" + "Ma_CauHoi=" + Ma_CauHoi + ", NoiDung_CauHoi=" + NoiDung_CauHoi + ", DapAn_A=" + DapAn_A + ", DapAn_B=" + DapAn_B + ", DapAn_C=" + DapAn_C + ", DapAn_D=" + DapAn_D + ", DapAn_Dung=" + DapAn_Dung + ", Hinh=" + Hinh + ", DapAn_Chon=" + DapAn_Chon + '}';
    }

  
}
