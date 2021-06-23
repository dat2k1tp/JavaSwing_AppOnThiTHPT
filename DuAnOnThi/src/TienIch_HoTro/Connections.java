package TienIch_HoTro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connections {

    public Connection ketNotDatabase() {
        Connection con = null;
        String uername = "tuanmayman";
        String password = "vutuan040599";
        String hosting = " sql.freeasphost.net\\MSSQL2016";
        String dbname = "databaseName=tuanmayman_Du_An_1";
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=quan_ly_sach";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://" + hosting + ";" + dbname, uername, password);
//            Dialong.alert(null, "Kết nối thành công !");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            Dialong.alert(null, "Không tìm thấy Driver tương thích !");

        } catch (SQLException ex) {
            ex.printStackTrace();
            Dialong.alert(null, "Kết nốt thất bại !");
        }
        return con;
    }
}
