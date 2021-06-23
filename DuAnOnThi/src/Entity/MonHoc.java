
package Entity;


public class MonHoc {
    String maMonHoc,tenMonHoc,maGV;

    public MonHoc() {
    }

    public MonHoc(String maMonHoc, String tenMonHoc, String maGV) {
        this.maMonHoc = maMonHoc;
        this.tenMonHoc = tenMonHoc;
        this.maGV = maGV;
    }

    public String getMaMonHoc() {
        return maMonHoc;
    }

    public void setMaMonHoc(String maMonHoc) {
        this.maMonHoc = maMonHoc;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public String getMaGV() {
        return maGV;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }
    
    
}
