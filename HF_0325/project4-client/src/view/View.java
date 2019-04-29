package view;

import controller.NewEmployeeController;
import controller.SalaryUpdateController;
import controller.LoginController;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class View extends JFrame {
  private SalaryUpdateController suc = null;
  private NewEmployeeController nec = null;
  private SalaryUpdateView suv = null;
  private NewEmployeeView nev = null;
	private LoginController loginController = null;
	private String userName;
	private int accessLevel;
  
  private JLabel lbWelcome;
	private JLabel lbLoggedInUserName = new JLabel();
	private JLabel lbUserName = new JLabel("Username:");
	private JLabel lbPassword = new JLabel("Password:");
  private JTextField tfUsername = new JTextField();
  private JPasswordField tfPassword = new JPasswordField();
  private JButton btMenuLogin = new JButton("Login");
  private JButton btMenuUpdateSalary = new JButton("Update salary");
  private JButton btMenuNewEmployee = new JButton("Add new employee");
  private JLabel lbLoginState = new JLabel("You are not logged in.");
  private JButton btLogout = new JButton("Logout");
  
  public View(SalaryUpdateView suv, NewEmployeeView nev) {
    this.suv = suv;
    this.nev = nev;
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setTitle("HR Management Software 2.0");
    setSize(400, 450);
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
          NewEmployeeController nec, LoginController loginController){
    if(suc == null || nec == null || loginController == null)
      throw new NullPointerException();
    this.suc = suc;
    this.nec = nec;
		this.loginController = loginController;
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
            + "HR Management Software 2.0!</font><br></html>");
    lbWelcome.setHorizontalAlignment(SwingConstants.CENTER);
    pnMainBody.add(lbWelcome, BorderLayout.NORTH);
    JPanel pnMainBodyButtons = new JPanel(new BorderLayout());
    
    pnMainBodyButtons.setBorder(new EmptyBorder(20, 0, 0, 0));
    JPanel pnLogin = new JPanel(new BorderLayout());

    JPanel pnFields = new JPanel(new BorderLayout());
    JPanel pnFieldsLabel = new JPanel(new GridLayout(2, 1, 10, 10));
    JPanel pnFieldsText = new JPanel(new GridLayout(2, 1, 10, 10));
    pnFieldsLabel.add(lbUserName);
    pnFieldsLabel.add(lbPassword);
    pnFieldsLabel.setBorder(new EmptyBorder(0, 0, 0, 30));
    pnFieldsText.add(tfUsername);
    pnFieldsText.add(tfPassword);
    pnFields.add(pnFieldsLabel, BorderLayout.WEST);
    pnFields.add(pnFieldsText, BorderLayout.CENTER);
    
    JPanel pnButton = new JPanel();
    pnButton.setBorder(new EmptyBorder(10, 0, 0, 0));
    pnButton.add(btMenuLogin);
    
    pnLogin.add(pnButton, BorderLayout.SOUTH);
    pnLogin.add(pnFields, BorderLayout.CENTER);
    pnMainBodyButtons.add(pnLogin, BorderLayout.CENTER);
    
    JPanel pnButtons = new JPanel(new GridLayout(2, 1, 15, 15));
		btMenuUpdateSalary.setVisible(false);
    pnButtons.add(btMenuUpdateSalary);
		btMenuNewEmployee.setVisible(false);
    pnButtons.add(btMenuNewEmployee);
    pnButtons.setBorder(new EmptyBorder(30, 0, 30, 0));
    pnMainBodyButtons.add(pnButtons, BorderLayout.SOUTH);
    pnMainBody.add(pnMainBodyButtons, BorderLayout.CENTER);
    pnMain.add(pnHeader, BorderLayout.NORTH);
    pnMain.add(pnMainBody, BorderLayout.CENTER);
    JPanel pnLoginState = new JPanel(new BorderLayout());
    pnMain.add(pnLoginState, BorderLayout.SOUTH);
    pnLoginState.add(lbLoginState, BorderLayout.CENTER);
    lbLoginState.setHorizontalAlignment(SwingConstants.CENTER);
    lbLoginState.setBorder(new EmptyBorder(0, 0, 10, 0));
		btLogout.setVisible(false);
    pnLoginState.add(btLogout, BorderLayout.SOUTH);
    pnLoginState.setBorder(new EmptyBorder(0, 20, 10, 20));
    return pnMain;
  }
  
  private void setEventHandlersOnMainPanel() {
    btMenuUpdateSalary.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        suv.buildSalaryChangeDialog();
      }
    });
    btMenuNewEmployee.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        nev.buildNewEmployeeDialog();
      }
    });
		btMenuLogin.addActionListener((ActionEvent e) -> {
			String userName = tfUsername.getText();
			String password = String.valueOf(tfPassword.getPassword());
			if(tfUsername.getText().length()>0 || tfPassword.getPassword().length>0){
//				System.out.println(userName);
//				System.out.println(password);
				loginController.logIn(userName, password);
//				if(accessLevel==-1)
//					Messages.showWarningMessage("ERROR!", "ERROR: wrong login name and/or password!");
//				if(accessLevel>-1){
//					btMenuLogin.setVisible(false);
//					lbUserName.setVisible(false);
//					lbPassword.setVisible(false);
//					tfPassword.setVisible(false);
//					tfUsername.setVisible(false);
//					btLogout.setVisible(true);
//					lbLoginState.setText("You are logged in as: "+userName.toUpperCase());
//					if(accessLevel==0){
//						btMenuUpdateSalary.setVisible(true);
//					} else if (accessLevel==1){
//						btMenuNewEmployee.setVisible(true);
//						btMenuUpdateSalary.setVisible(true);
//					}
//				}
			}
		});
		btLogout.addActionListener((ActionEvent e) -> {
			loginController.logOut();
			btMenuLogin.setVisible(true);
			tfPassword.setText("");
			tfPassword.setVisible(true);
			lbPassword.setVisible(true);
			tfUsername.setText("");
			tfUsername.setVisible(true);
			lbUserName.setVisible(true);
			btMenuNewEmployee.setVisible(false);
			btMenuUpdateSalary.setVisible(false);
			lbLoginState.setText("You are not logged in.");
			btLogout.setVisible(false);
		});
  }

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}

	public void loggedInAccess0(String userName) {
		btMenuLogin.setVisible(false);
		lbUserName.setVisible(false);
		lbPassword.setVisible(false);
		tfPassword.setVisible(false);
		tfUsername.setVisible(false);
		btLogout.setVisible(true);
		btMenuUpdateSalary.setVisible(true);
		lbLoginState.setText("You are logged in as: "+userName.toUpperCase());
	}

	public void loggedInAccess1(String userName) {
		btMenuNewEmployee.setVisible(true);
		btMenuUpdateSalary.setVisible(true);
		btMenuLogin.setVisible(false);
		lbUserName.setVisible(false);
		lbPassword.setVisible(false);
		tfPassword.setVisible(false);
		tfUsername.setVisible(false);
		btLogout.setVisible(true);
		lbLoginState.setText("You are logged in as: "+userName.toUpperCase());
	}

	public void invalidUserOrPw() {
		Messages.showWarningMessage("ERROR!", "ERROR: wrong login name and/or password!");
	}
	
}
  
