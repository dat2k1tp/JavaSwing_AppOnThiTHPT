package TienIch_HoTro;

import Entity.GiaoVien;
import java.util.Date;

public class XConvert {

    public static java.sql.Date convert(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    public static Date now() {
        return new Date();
    }

    public static GiaoVien USER = null;

    public static void logoff() {
        XConvert.USER = null;
    }

    public static boolean authenticated() {
        return XConvert.USER != null;
    }
}
