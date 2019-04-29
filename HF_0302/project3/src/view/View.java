package view;

import controller.NewEmployeeController;
import controller.SalaryUpdateController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class View extends JFrame {
  private SalaryUpdateController suc = null;
  private NewEmployeeController nec = null;
  private SalaryUpdateView suv = null;
  private NewEmployeeView nev = null;
  
  private JLabel lbWelcome;
  private JButton btMenuLogin = new JButton("Log in");
  private JButton btMenuUpdateSalary = new JButton("Update salary");
  private JButton btMenuNewEmployee = new JButton("Add new employee");
  private JLabel lbLoginState = new JLabel("You are not logged in.");
  
  public View(SalaryUpdateView suv, NewEmployeeView nev) {
    this.suv = suv;
    this.nev = nev;
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setTitle("HR Management Software 2.0");
    setSize(400, 400);
    setLocationRelativeTo(this);
    setLayout(new BorderLayout());
    JPanel pnMain = buildMainPanel();
    add(pnMain, BorderLayout.CENTER);
    setResizable(false);
    
    setEventHandlersOnMainPanel();
    suv.setEventHandlers();
    nev.setEventHandlers();
  }
  
  public void setController(SalaryUpdateController suc, 
          NewEmployeeController nec){
    if(suc == null || nec == null)
      throw new NullPointerException();
    this.suc = suc;
    this.nec = nec;
  }
  
  public void showMainWindow() {
    setVisible(true);
  }
  
  private JPanel buildMainPanel() {
    JPanel pnMain = new JPanel();
    pnMain.setLayout(new BorderLayout());
    JPanel pnHeader = HeaderBuilder.buildHeader();
    JPanel pnMainBody = new JPanel(new BorderLayout());
    pnMainBody.setBorder(new EmptyBorder(0, 20, 0, 20));
    lbWelcome = new JLabel(""
            + "<html><p align=center><font size=5>Welcome to<br>"
            + "HR Management Software 2.0!</font><br>"
            + "What would you like to do?</p></html>");
    lbWelcome.setHorizontalAlignment(SwingConstants.CENTER);
    pnMainBody.add(lbWelcome, BorderLayout.NORTH);
    JPanel pnMainBodyButtons = new JPanel(new GridLayout(4, 1, 15, 15));
    pnMainBodyButtons.setBorder(new EmptyBorder(20, 0, 0, 0));
    pnMainBodyButtons.add(btMenuLogin);
    btMenuLogin.setEnabled(false);
    pnMainBodyButtons.add(btMenuUpdateSalary);
    pnMainBodyButtons.add(btMenuNewEmployee);
    pnMainBody.add(pnMainBodyButtons, BorderLayout.CENTER);
    pnMain.add(pnHeader, BorderLayout.NORTH);
    pnMain.add(pnMainBody, BorderLayout.CENTER);
    pnMain.add(lbLoginState, BorderLayout.SOUTH);
    lbLoginState.setHorizontalAlignment(SwingConstants.CENTER);
    lbLoginState.setBorder(new EmptyBorder(0, 0, 10, 0));
    return pnMain;
  }
  
  private void setEventHandlersOnMainPanel() {
    btMenuUpdateSalary.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        //controller feladata lenne?
        suv.buildSalaryChangeDialog();
      }
    });
    btMenuNewEmployee.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        //controller feladata lenne?
        nev.buildNewEmployeeDialog();
      }
    });
  }
  
}
  
