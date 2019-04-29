package view;

import javax.swing.JOptionPane;

public class Messages {
  
  public static void showWarningMessage(String title, String message) {
    JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
  }
  
  public static void showInfoMessage(String title, String message) {
    JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
  }
  
  public static int showConfirmDialog(String title, String message) {
    return JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
  }
  
}
