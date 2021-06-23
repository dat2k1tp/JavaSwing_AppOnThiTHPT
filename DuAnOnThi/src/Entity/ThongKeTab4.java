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
public class ThongKeTab4 {

    int stt;
    String tenMon;
    float DiemTN,DiemCN,DiemTB;

    public ThongKeTab4() {
    }

    public ThongKeTab4(int stt, String tenMon, float DiemTN, float DiemCN, float DiemTB) {
        this.stt = stt;
        this.tenMon = tenMon;
        this.DiemTN = DiemTN;
        this.DiemCN = DiemCN;
        this.DiemTB = DiemTB;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public float getDiemTN() {
        return DiemTN;
    }

    public void setDiemTN(float DiemTN) {
        this.DiemTN = DiemTN;
    }

    public float getDiemCN() {
        return DiemCN;
    }

    public void setDiemCN(float DiemCN) {
        this.DiemCN = DiemCN;
    }

    public float getDiemTB() {
        return DiemTB;
    }

    public void setDiemTB(float DiemTB) {
        this.DiemTB = DiemTB;
    }

    @Override
    public String toString() {
        return "ThongKeTab4{" + "stt=" + stt + ", tenMon=" + tenMon + ", DiemTN=" + DiemTN + ", DiemCN=" + DiemCN + ", DiemTB=" + DiemTB + '}';
    }

  
    
}
