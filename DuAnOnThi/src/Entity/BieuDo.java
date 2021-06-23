/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class BieuDo {
    private String maDeThi;
    private Date ngayTao;
    private float diem;

    public BieuDo() {
    }

    public BieuDo(String maDeThi, Date ngayTao, float diem) {
        this.maDeThi = maDeThi;
        this.ngayTao = ngayTao;
        this.diem = diem;
    }

  
   

    public String getMaDeThi() {
        return maDeThi;
    }

    public void setMaDeThi(String maDeThi) {
        this.maDeThi = maDeThi;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }


    public float getDiem() {
        return diem;
    }

    public void setDiem(float diem) {
        this.diem = diem;
    }
    
    
}
