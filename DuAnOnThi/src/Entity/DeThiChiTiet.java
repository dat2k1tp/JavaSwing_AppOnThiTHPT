
package Entity;


public class DeThiChiTiet {
    String maDeThiCT,maDeThi,maCauHoi,ghiChu;

    public DeThiChiTiet() {
    }

    public DeThiChiTiet(String maDeThiCT, String maDeThi, String maCauHoi, String ghiChu) {
        this.maDeThiCT = maDeThiCT;
        this.maDeThi = maDeThi;
        this.maCauHoi = maCauHoi;
        this.ghiChu = ghiChu;
    }

    public String getMaDeThiCT() {
        return maDeThiCT;
    }

    public void setMaDeThiCT(String maDeThiCT) {
        this.maDeThiCT = maDeThiCT;
    }

    public String getMaDeThi() {
        return maDeThi;
    }

    public void setMaDeThi(String maDeThi) {
        this.maDeThi = maDeThi;
    }

    public String getMaCauHoi() {
        return maCauHoi;
    }

    public void setMaCauHoi(String maCauHoi) {
        this.maCauHoi = maCauHoi;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
    
}
