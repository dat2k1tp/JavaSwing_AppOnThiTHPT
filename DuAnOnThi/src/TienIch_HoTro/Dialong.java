package TienIch_HoTro;

import java.awt.Component;
import javax.swing.JOptionPane;

public class Dialong {

    public static void alert(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message);
    }

    public static boolean confirm(Component parent, String message) {
        int result = JOptionPane.showConfirmDialog(parent, message, "Hệ thống quản lý đào tạo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }

    public static boolean confirmXN(Component parent, String message) {
        int result = JOptionPane.showConfirmDialog(parent, message);
        return result == JOptionPane.YES_OPTION;
    }

}
