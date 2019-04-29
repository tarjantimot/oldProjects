package view;

import controller.NewEmployeeController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

public class NewEmployeeView {
  private NewEmployeeController nec = null;
  private ImageIcon image = new ImageIcon("./files/infoicon.png");
  private JDialog newEmployeeDialog;
  private JLabel infoIcon = new JLabel(image);;
  private JLabel infoIcon2 = new JLabel(image);;
  private JLabel lbnewEmployeeWelcome;
  private JButton btFirstNext = new JButton("Next");
  
  private JTextField tfFirstName = new JTextField();
  private JTextField tfLastName = new JTextField();
  private JTextField tfEmail = new JTextField();
  private JFormattedTextField tfPhone;
  private JButton btSecondNext = new JButton("Next");
  private JButton btFirstPrevious = new JButton("Previous");
  
  private JComboBox cbDepartment = new JComboBox();
  private JButton btThirdNext = new JButton("Next");
  private JButton btSecondPrevious = new JButton("Previous");
  
  private JComboBox cbJob = new JComboBox();
  private JButton btFourthNext = new JButton("Next");
  private JButton btThirdPrevious = new JButton("Previous");

  private JComboBox cbManager = new JComboBox();
  private JButton btFifthNext = new JButton("Next");
  private JButton btFourthPrevious = new JButton("Previous");
  
  private JLabel lbMinSalary = new JLabel("0");
  private JLabel lbMaxSalary = new JLabel("0");
  private JTextField tfSalary = new JTextField();
  private JFormattedTextField tfCommission;
  private JButton btSixthNext = new JButton("Next");
  private JButton btFifthPrevious = new JButton("Previous");
  
  private JButton btSixthPrevious = new JButton("Previous");
  private JButton btFinish = new JButton("Finish");
  private JLabel lbNewEmployeeFirstName = new JLabel("null");
  private JLabel lbNewEmployeeLastName = new JLabel("null");
  private JLabel lbNewEmployeePhone = new JLabel("null");
  private JLabel lbNewEmployeeEmail = new JLabel("null");
  private JLabel lbNewEmployeeDepartment = new JLabel("null");
  private JLabel lbNewEmployeeJob = new JLabel("null");
  private JLabel lbNewEmployeeManager = new JLabel("null");
  private JLabel lbNewEmployeeSalary = new JLabel("null");
  private JLabel lbNewEmployeeCommission = new JLabel("null");
  private JButton btCancel = new JButton("Cancel");
  
  public void setController(NewEmployeeController nec){
    if(nec == null)
      throw new NullPointerException();
    this.nec = nec;
  }
  
  protected void buildNewEmployeeDialog() {
    newEmployeeDialog = new JDialog();
    newEmployeeDialog.setSize(new Dimension(500, 350));
    newEmployeeDialog.setResizable(false);
    newEmployeeDialog.setLocationRelativeTo(null);
    newEmployeeDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    newEmployeeDialog.setTitle("Adding a new employee");
    newEmployeeDialog.setModal(true);
    JPanel pnMain = buildNewEmployeePanelWelcome();
    newEmployeeDialog.add(pnMain);
    newEmployeeDialog.addWindowListener(new WindowAdapter() {
      @Override
        public void windowClosing(WindowEvent e) {
          nec.cancelOrExitPressed();
        }
    });
    newEmployeeDialog.setVisible(true);
  }
  
  protected void setEventHandlers() {
    ActionListener previousNextListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btFirstPrevious) {
          newEmployeeDialog.getContentPane().removeAll();
          JPanel currentPanel = buildNewEmployeePanelWelcome();
          newEmployeeDialog.add(currentPanel);
          newEmployeeDialog.repaint();
          newEmployeeDialog.revalidate();
        }
        if (e.getSource() == btFirstNext || e.getSource() == btSecondPrevious) {
          newEmployeeDialog.getContentPane().removeAll();
          JPanel currentPanel = buildNewEmployeePanelBasicInfo();
          newEmployeeDialog.add(currentPanel);
          newEmployeeDialog.repaint();
          newEmployeeDialog.revalidate();
        }
        if (e.getSource() == btSecondNext || e.getSource() == btThirdPrevious ) {
          if(nec.getDataFromFirstInputPanel(
							tfFirstName.getText(),
							tfLastName.getText(),
							tfPhone.getText())){
						newEmployeeDialog.getContentPane().removeAll();
						JPanel currentPanel = buildNewEmployeePanelDepartment();
						newEmployeeDialog.add(currentPanel);
						newEmployeeDialog.repaint();
						newEmployeeDialog.revalidate();
					}else {
						;
					 }
				}
        if (e.getSource() == btThirdNext || e.getSource() == btFourthPrevious) {
          if(nec.getDataFromSecondInputPanel(cbDepartment.getSelectedIndex())){
          newEmployeeDialog.getContentPane().removeAll();
          JPanel currentPanel = buildNewEmployeePanelJob();
          newEmployeeDialog.add(currentPanel);
          newEmployeeDialog.repaint();
          newEmployeeDialog.revalidate();
					} else {
						;
					}
        }
        if (e.getSource() == btFourthNext || e.getSource() == btFifthPrevious) {
          if(nec.getDataFromThirdInputPanel(cbJob.getSelectedIndex())){
          newEmployeeDialog.getContentPane().removeAll();
          JPanel currentPanel = buildNewEmployeePanelManager();
          newEmployeeDialog.add(currentPanel);
          newEmployeeDialog.repaint();
          newEmployeeDialog.revalidate();
					} else {
						;
					}
        }
        if (e.getSource() == btFifthNext || e.getSource() == btSixthPrevious) {
          nec.getDataFromFourthInputPanel(cbManager.getSelectedIndex());          
          newEmployeeDialog.getContentPane().removeAll();
          JPanel currentPanel = buildNewEmployeePanelSalary();
          newEmployeeDialog.add(currentPanel);
          newEmployeeDialog.repaint();
          newEmployeeDialog.revalidate();
        }
        if (e.getSource() == btSixthNext) {
          if(nec.getDataFromFifthInputPanel(tfSalary.getText(), tfCommission.getText())) {
          newEmployeeDialog.getContentPane().removeAll();
          JPanel currentPanel = buildNewEmployeePanelOverview();
          newEmployeeDialog.add(currentPanel);
          newEmployeeDialog.repaint();
          newEmployeeDialog.revalidate();
					} else {
						;
					}
        }
      }
		
    };
    btFirstNext.addActionListener(previousNextListener);
    btFirstPrevious.addActionListener(previousNextListener);
    btSecondNext.addActionListener(previousNextListener);
    btSecondPrevious.addActionListener(previousNextListener);
    btThirdNext.addActionListener(previousNextListener);
    btThirdPrevious.addActionListener(previousNextListener);
    btFourthNext.addActionListener(previousNextListener);
    btFourthPrevious.addActionListener(previousNextListener);
    btFifthNext.addActionListener(previousNextListener);
    btFifthPrevious.addActionListener(previousNextListener);
    btSixthNext.addActionListener(previousNextListener);
    btSixthPrevious.addActionListener(previousNextListener);
    
    btFinish.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        nec.addNewEmployee();
				newEmployeeDialog.setVisible(false);
				
      }
    });
    
    btCancel.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        nec.cancelOrExitPressed();
      }
    });
    
    tfSalary.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if(!Character.isDigit(e.getKeyChar()) && e.getKeyChar()!=KeyEvent.VK_BACK_SPACE
                && e.getKeyChar()!=KeyEvent.VK_DELETE)
          ((JTextField)e.getSource()).setEditable(false);
      }

      @Override
      public void keyReleased(KeyEvent e) {
        ((JTextField)e.getSource()).setEditable(true);
      }
    });
  }
  
  protected JPanel buildNewEmployeePanelWelcome(){
    JPanel pnMain = new JPanel(new BorderLayout());
    JPanel pnHeader = HeaderBuilder.buildHeader();
    pnMain.add(pnHeader, BorderLayout.NORTH);
    lbnewEmployeeWelcome = new JLabel(""
            + "<html><p align=center><font size=3>"
            + "Steps of adding a new employee:</p><br><br>"
            + "<ol>"
            + "<li>adding basic information (name and phone)</li>"
            + "<li>choosing a department</li>"
            + "<li>choosing a job title</li>"
            + "<li>choosing a manager</li>"
            + "<li>setting a salary</li>"
            + "<li>overview</li>"
            + "</ol>"
            + "<br><p align=center>Press next to continue.</p><html>");
    lbnewEmployeeWelcome.setHorizontalAlignment(SwingConstants.CENTER);
    pnMain.add(lbnewEmployeeWelcome, BorderLayout.CENTER);
    JPanel pnNewEmployeeFooter = new JPanel(new GridLayout(1, 2));
    JPanel pnNewEmployeeFooterLeft = new JPanel(new FlowLayout());
    JPanel pnNewEmployeeFooterRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    pnNewEmployeeFooterLeft.add(btCancel);
    pnNewEmployeeFooterRight.add(btFirstNext);
    pnNewEmployeeFooter.setBorder((new EmptyBorder(10, 10, 10, 10)));
    pnNewEmployeeFooter.add(pnNewEmployeeFooterLeft);
    pnNewEmployeeFooter.add(pnNewEmployeeFooterRight);
    btFirstNext.setPreferredSize(new Dimension(100, 20));
    btCancel.setPreferredSize(new Dimension(100, 20));
    pnMain.add(pnNewEmployeeFooter, BorderLayout.SOUTH);
    return pnMain;
  }
  
  protected JPanel buildNewEmployeePanelBasicInfo() {
    JPanel pnMain = new JPanel(new BorderLayout());
    JPanel pnHeader = HeaderBuilder.buildHeader();
    pnMain.add(pnHeader, BorderLayout.NORTH);
    
    JPanel pnCenter = new JPanel(new BorderLayout());
    JPanel pnCenterLabels = new JPanel(new GridLayout(6, 1, 5, 10));
    pnCenterLabels.add(new JLabel("First name: "));
    pnCenterLabels.add(new JLabel("Last name: "));
    pnCenterLabels.add(new JLabel());
    pnCenterLabels.add(new JLabel("Phone: "));
    pnCenterLabels.add(new JLabel());
//    pnCenterLabels.add(new JLabel("Email: "));
    JPanel pnCenterTextfields = new JPanel(new GridLayout(6, 1, 5, 10));
    pnCenterTextfields.add(tfFirstName);
    pnCenterTextfields.add(tfLastName);
    try {
      tfPhone = new JFormattedTextField(new MaskFormatter("###.###.####"));
    } catch (ParseException ex) {
      Logger.getLogger(NewEmployeeView.class.getName()).log(Level.SEVERE, null, ex);
    }
    JPanel pnPhoneInfo = new JPanel(new FlowLayout(SwingConstants.LEFT));
    pnPhoneInfo.add(infoIcon);
    pnPhoneInfo.add(new JLabel("Correct format of phone no. is 999.999.9999"));
    pnCenterTextfields.add(pnPhoneInfo);
    pnCenterTextfields.add(tfPhone);
//    JPanel pnEmailInfo = new JPanel(new FlowLayout(SwingConstants.LEFT));
//    pnEmailInfo.add(infoIcon2);
//    pnEmailInfo.add(new JLabel("Email is generated from first and last name."));
//    pnCenterTextfields.add(pnEmailInfo);
//    pnCenterTextfields.add(tfEmail);
//    tfEmail.setEditable(false);
    pnCenter.add(pnCenterLabels, BorderLayout.WEST);
    pnCenter.add(pnCenterTextfields, BorderLayout.CENTER);
    pnCenter.setBorder(new EmptyBorder(10, 40, 10, 40));
    pnCenterTextfields.setBorder(new EmptyBorder(0, 50, 0, 0));
    pnMain.add(pnCenter, BorderLayout.CENTER);
    
    JPanel pnNewEmployeeFooter = new JPanel(new GridLayout(1, 2));
    JPanel pnNewEmployeeFooterLeft = new JPanel(new FlowLayout());
    JPanel pnNewEmployeeFooterRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    pnNewEmployeeFooterLeft.add(btCancel);
    pnNewEmployeeFooterRight.add(btFirstPrevious);
    pnNewEmployeeFooterRight.add(btSecondNext);
    pnNewEmployeeFooter.setBorder((new EmptyBorder(10, 10, 10, 10)));
    pnNewEmployeeFooter.add(pnNewEmployeeFooterLeft);
    pnNewEmployeeFooter.add(pnNewEmployeeFooterRight);
    btFirstPrevious.setPreferredSize(new Dimension(100, 20));
    btSecondNext.setPreferredSize(new Dimension(100, 20));
    btCancel.setPreferredSize(new Dimension(100, 20));
    pnMain.add(pnNewEmployeeFooter, BorderLayout.SOUTH);
    return pnMain;
  }
  
  protected JPanel buildNewEmployeePanelDepartment() {
    JPanel pnMain = new JPanel(new BorderLayout());
    JPanel pnHeader = HeaderBuilder.buildHeader();
    pnMain.add(pnHeader, BorderLayout.NORTH);
    
    JPanel pnCenter = new JPanel(new BorderLayout());
    JPanel pnCenterLabel = new JPanel(new GridLayout(1, 1, 5, 20));
    pnCenterLabel.add(new JLabel("Department: "));
    pnCenterLabel.setPreferredSize(new Dimension(115, 20));
    JPanel pnCenterCombobox = new JPanel(new GridLayout(1, 1, 5, 20));
    pnCenterCombobox.add(cbDepartment);
    pnCenter.add(pnCenterLabel, BorderLayout.WEST);
    pnCenter.add(pnCenterCombobox, BorderLayout.CENTER);
    pnCenter.setBorder(new EmptyBorder(100, 40, 100, 40));
    pnMain.add(pnCenter, BorderLayout.CENTER);
    
    JPanel pnNewEmployeeFooter = new JPanel(new GridLayout(1, 2));
    JPanel pnNewEmployeeFooterLeft = new JPanel(new FlowLayout());
    JPanel pnNewEmployeeFooterRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    pnNewEmployeeFooterLeft.add(btCancel);
    pnNewEmployeeFooterRight.add(btSecondPrevious);
    pnNewEmployeeFooterRight.add(btThirdNext);
    pnNewEmployeeFooter.setBorder((new EmptyBorder(10, 10, 10, 10)));
    pnNewEmployeeFooter.add(pnNewEmployeeFooterLeft);
    pnNewEmployeeFooter.add(pnNewEmployeeFooterRight);
    btSecondPrevious.setPreferredSize(new Dimension(100, 20));
    btThirdNext.setPreferredSize(new Dimension(100, 20));
    btCancel.setPreferredSize(new Dimension(100, 20));
    pnMain.add(pnNewEmployeeFooter, BorderLayout.SOUTH);
    return pnMain;
  }
  
  protected JPanel buildNewEmployeePanelJob() {
    JPanel pnMain = new JPanel(new BorderLayout());
    JPanel pnHeader = HeaderBuilder.buildHeader();
    pnMain.add(pnHeader, BorderLayout.NORTH);
    
    JPanel pnCenter = new JPanel(new BorderLayout());
    JPanel pnCenterLabel = new JPanel(new GridLayout(1, 1, 5, 20));
    pnCenterLabel.add(new JLabel("Job title: "));
    pnCenterLabel.setPreferredSize(new Dimension(115, 20));
    JPanel pnCenterCombobox = new JPanel(new GridLayout(1, 1, 5, 20));
    pnCenterCombobox.add(cbJob);
    pnCenter.add(pnCenterLabel, BorderLayout.WEST);
    pnCenter.add(pnCenterCombobox, BorderLayout.CENTER);
    pnCenter.setBorder(new EmptyBorder(100, 40, 100, 40));
    pnMain.add(pnCenter, BorderLayout.CENTER);
    
    JPanel pnNewEmployeeFooter = new JPanel(new GridLayout(1, 2));
    JPanel pnNewEmployeeFooterLeft = new JPanel(new FlowLayout());
    JPanel pnNewEmployeeFooterRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    pnNewEmployeeFooterLeft.add(btCancel);
    pnNewEmployeeFooterRight.add(btThirdPrevious);
    pnNewEmployeeFooterRight.add(btFourthNext);
    pnNewEmployeeFooter.setBorder((new EmptyBorder(10, 10, 10, 10)));
    pnNewEmployeeFooter.add(pnNewEmployeeFooterLeft);
    pnNewEmployeeFooter.add(pnNewEmployeeFooterRight);
    btThirdPrevious.setPreferredSize(new Dimension(100, 20));
    btFourthNext.setPreferredSize(new Dimension(100, 20));
    btCancel.setPreferredSize(new Dimension(100, 20));
    pnMain.add(pnNewEmployeeFooter, BorderLayout.SOUTH);
    return pnMain;
  }
  
  protected JPanel buildNewEmployeePanelManager() {
    JPanel pnMain = new JPanel(new BorderLayout());
    JPanel pnHeader = HeaderBuilder.buildHeader();
    pnMain.add(pnHeader, BorderLayout.NORTH);
    
    JPanel pnCenter = new JPanel(new BorderLayout());
    JPanel pnCenterLabel = new JPanel(new GridLayout(1, 1, 5, 20));
    pnCenterLabel.add(new JLabel("Manager: "));
    pnCenterLabel.setPreferredSize(new Dimension(115, 20));
    JPanel pnCenterCombobox = new JPanel(new GridLayout(1, 1, 5, 20));
    pnCenterCombobox.add(cbManager);
    pnCenter.add(pnCenterLabel, BorderLayout.WEST);
    pnCenter.add(pnCenterCombobox, BorderLayout.CENTER);
    pnCenter.setBorder(new EmptyBorder(100, 40, 100, 40));
    pnMain.add(pnCenter, BorderLayout.CENTER);
    
    JPanel pnNewEmployeeFooter = new JPanel(new GridLayout(1, 2));
    JPanel pnNewEmployeeFooterLeft = new JPanel(new FlowLayout());
    JPanel pnNewEmployeeFooterRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    pnNewEmployeeFooterLeft.add(btCancel);
    pnNewEmployeeFooterRight.add(btFourthPrevious);
    pnNewEmployeeFooterRight.add(btFifthNext);
    pnNewEmployeeFooter.setBorder((new EmptyBorder(10, 10, 10, 10)));
    pnNewEmployeeFooter.add(pnNewEmployeeFooterLeft);
    pnNewEmployeeFooter.add(pnNewEmployeeFooterRight);
    btFourthPrevious.setPreferredSize(new Dimension(100, 20));
    btFifthNext.setPreferredSize(new Dimension(100, 20));
    btCancel.setPreferredSize(new Dimension(100, 20));
    pnMain.add(pnNewEmployeeFooter, BorderLayout.SOUTH);
    return pnMain;
  }
  
  protected JPanel buildNewEmployeePanelSalary() {
    JPanel pnMain = new JPanel(new BorderLayout());
    JPanel pnHeader = HeaderBuilder.buildHeader();
    pnMain.add(pnHeader, BorderLayout.NORTH);
    
    JPanel pnCenter = new JPanel(new BorderLayout());
    JPanel pnInfo = new JPanel(new GridLayout(2, 1, 0, 0));
    JPanel firstRow = new JPanel(new FlowLayout());
    JPanel secondRow = new JPanel(new FlowLayout());
    firstRow.add(infoIcon2);
    firstRow.add(new JLabel("The salary must be between "));
    firstRow.add(lbMinSalary);
    lbMinSalary.setForeground(new Color(0, 153, 51));
    firstRow.add(new JLabel(" and "));
    firstRow.add(lbMaxSalary);
    lbMaxSalary.setForeground(new Color(0, 153, 51));
    firstRow.add(new JLabel("."));
    secondRow.add(infoIcon);
    secondRow.add(new JLabel("<html>The commission must be less than 1 and is<br>"
            + "limited to two decimal places. (eg. 0.25)</html>"));
    pnInfo.add(firstRow);
    pnInfo.add(secondRow);
    JPanel pnCenterLabel = new JPanel(new GridLayout(2, 1, 5, 20));
    pnCenterLabel.setBorder(new EmptyBorder(18, 0, 18, 0));
    pnCenterLabel.add(new JLabel("Salary: "));
    pnCenterLabel.setPreferredSize(new Dimension(115, 20));
    pnCenterLabel.add(new JLabel("Commission: "));
    JPanel pnCenterTextfield = new JPanel(new GridLayout(2, 1, 5, 20));
    pnCenterTextfield.setBorder(new EmptyBorder(18, 0, 18, 0));
    pnCenterTextfield.add(tfSalary);
    try {
      tfCommission = new JFormattedTextField(new MaskFormatter("#.##"));
    } catch (ParseException ex) {
      Logger.getLogger(NewEmployeeView.class.getName()).log(Level.SEVERE, null, ex);
    }
    pnCenterTextfield.add(tfCommission);
    pnCenter.add(pnInfo, BorderLayout.NORTH);
    pnCenter.add(pnCenterLabel, BorderLayout.WEST);
    pnCenter.add(pnCenterTextfield, BorderLayout.CENTER);
    pnCenter.setBorder(new EmptyBorder(20, 40, 20, 40));
    pnMain.add(pnCenter, BorderLayout.CENTER);
    
    JPanel pnNewEmployeeFooter = new JPanel(new GridLayout(1, 2));
    JPanel pnNewEmployeeFooterLeft = new JPanel(new FlowLayout());
    JPanel pnNewEmployeeFooterRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    pnNewEmployeeFooterLeft.add(btCancel);
    pnNewEmployeeFooterRight.add(btFifthPrevious);
    pnNewEmployeeFooterRight.add(btSixthNext);
    pnNewEmployeeFooter.setBorder((new EmptyBorder(10, 10, 10, 10)));
    pnNewEmployeeFooter.add(pnNewEmployeeFooterLeft);
    pnNewEmployeeFooter.add(pnNewEmployeeFooterRight);
    btFifthPrevious.setPreferredSize(new Dimension(100, 20));
    btSixthNext.setPreferredSize(new Dimension(100, 20));
    btCancel.setPreferredSize(new Dimension(100, 20));
    pnMain.add(pnNewEmployeeFooter, BorderLayout.SOUTH);
    return pnMain;
  }
  
  protected JPanel buildNewEmployeePanelOverview() {
    JPanel pnMain = new JPanel(new BorderLayout());
    JPanel pnHeader = HeaderBuilder.buildHeader();
    pnMain.add(pnHeader, BorderLayout.NORTH);
    
    JPanel pnCenter = new JPanel(new BorderLayout());
    JLabel lbOverview = new JLabel("<html><p align=center><font size=4>Overview</font></p></html>");
    pnCenter.add(lbOverview, BorderLayout.NORTH);
    lbOverview.setHorizontalAlignment(SwingConstants.CENTER);
    lbOverview.setBorder(new EmptyBorder(0, 0, 10, 0));
    JPanel pnCenterStaticLabels = new JPanel(new GridLayout(9, 1, 5, 5));
    pnCenterStaticLabels.add(new JLabel("First name: "));
    pnCenterStaticLabels.add(new JLabel("Last name: "));
    pnCenterStaticLabels.add(new JLabel("Phone: "));
    pnCenterStaticLabels.add(new JLabel("Email: "));
    pnCenterStaticLabels.add(new JLabel("Department: "));
    pnCenterStaticLabels.add(new JLabel("Job: "));
    pnCenterStaticLabels.add(new JLabel("Manager: "));
    pnCenterStaticLabels.add(new JLabel("Salary: "));
    pnCenterStaticLabels.add(new JLabel("Commission: "));
    JPanel pnCenterDinamicLabels = new JPanel(new GridLayout(9, 1, 5, 5));
    pnCenterDinamicLabels.add(lbNewEmployeeFirstName);
    lbNewEmployeeFirstName.setForeground(new Color(0, 153, 51));
    lbNewEmployeeFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
    pnCenterDinamicLabels.add(lbNewEmployeeLastName);
    lbNewEmployeeLastName.setForeground(new Color(0, 153, 51));
    lbNewEmployeeLastName.setHorizontalAlignment(SwingConstants.RIGHT);
    pnCenterDinamicLabels.add(lbNewEmployeePhone);
    lbNewEmployeePhone.setForeground(new Color(0, 153, 51));
    lbNewEmployeePhone.setHorizontalAlignment(SwingConstants.RIGHT);
    pnCenterDinamicLabels.add(lbNewEmployeeEmail);
    lbNewEmployeeEmail.setForeground(new Color(0, 153, 51));
    lbNewEmployeeEmail.setHorizontalAlignment(SwingConstants.RIGHT);
    pnCenterDinamicLabels.add(lbNewEmployeeDepartment);
    lbNewEmployeeDepartment.setForeground(new Color(0, 153, 51));
    lbNewEmployeeDepartment.setHorizontalAlignment(SwingConstants.RIGHT);
    pnCenterDinamicLabels.add(lbNewEmployeeJob);
    lbNewEmployeeJob.setForeground(new Color(0, 153, 51));
    lbNewEmployeeJob.setHorizontalAlignment(SwingConstants.RIGHT);
    pnCenterDinamicLabels.add(lbNewEmployeeManager);
    lbNewEmployeeManager.setForeground(new Color(0, 153, 51));
    lbNewEmployeeManager.setHorizontalAlignment(SwingConstants.RIGHT);
    pnCenterDinamicLabels.add(lbNewEmployeeSalary);
    lbNewEmployeeSalary.setForeground(new Color(0, 153, 51));
    lbNewEmployeeSalary.setHorizontalAlignment(SwingConstants.RIGHT);
    pnCenterDinamicLabels.add(lbNewEmployeeCommission);
    lbNewEmployeeCommission.setForeground(new Color(0, 153, 51));
    lbNewEmployeeCommission.setHorizontalAlignment(SwingConstants.RIGHT);
    pnCenter.add(pnCenterStaticLabels, BorderLayout.WEST);
    pnCenter.add(pnCenterDinamicLabels, BorderLayout.CENTER);
    pnCenter.setBorder(new EmptyBorder(20, 40, 20, 40));
    pnCenterDinamicLabels.setBorder(new EmptyBorder(0, 150, 0, 0));
    pnMain.add(pnCenter, BorderLayout.CENTER);
    
    JPanel pnNewEmployeeFooter = new JPanel(new GridLayout(1, 2));
    JPanel pnNewEmployeeFooterLeft = new JPanel(new FlowLayout());
    JPanel pnNewEmployeeFooterRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    pnNewEmployeeFooterLeft.add(btCancel);
    pnNewEmployeeFooterRight.add(btSixthPrevious);
    pnNewEmployeeFooterRight.add(btFinish);
    pnNewEmployeeFooter.setBorder((new EmptyBorder(10, 10, 10, 10)));
    pnNewEmployeeFooter.add(pnNewEmployeeFooterLeft);
    pnNewEmployeeFooter.add(pnNewEmployeeFooterRight);
    btSixthPrevious.setPreferredSize(new Dimension(100, 20));
    btFinish.setPreferredSize(new Dimension(100, 20));
    btCancel.setPreferredSize(new Dimension(100, 20));
    pnMain.add(pnNewEmployeeFooter, BorderLayout.SOUTH);
    return pnMain;
  }

  public void cancelProcess() {
    newEmployeeDialog.dispose();
  }
  
  public void setDepartmentList(ArrayList<String> departmentList) {
    cbDepartment.removeAllItems();
    ArrayList<String> list = departmentList;
    cbDepartment.setModel(new DefaultComboBoxModel(list.toArray()));
  }
  
  public void setDepartmentIdnex(int index){
    cbDepartment.setSelectedIndex(index);
  }
  
  public void setJobList(ArrayList<String> jobList) {
    cbJob.removeAllItems();
    ArrayList<String> list = jobList;
    cbJob.setModel(new DefaultComboBoxModel(list.toArray()));
  }
  
  public void setJobIndex(int index){
    cbJob.setSelectedIndex(index);
  }
  
  public void setManagerList(ArrayList<String> managerList) {
    cbManager.removeAllItems();
    ArrayList<String> list = managerList;
    cbManager.setModel(new DefaultComboBoxModel(list.toArray()));
  }
  
  public void setNewEmployeeFirstName (String firstName) {
    lbNewEmployeeFirstName.setText(firstName);
  }
  
  public void setNewEmployeeLastName (String lastName) {
    lbNewEmployeeLastName.setText(lastName);
  }
  
  public void setNewEmployeePhone (String phone) {
    lbNewEmployeePhone.setText(phone);
  }
  
  public void setNewEmployeeEmail (String email) {
    lbNewEmployeeEmail.setText(email);
  }
  
  public void setNewEmployeeDepartment (String department) {
    lbNewEmployeeDepartment.setText(department);
  }
  
  public void setNewEmployeeJob (String job) {
    lbNewEmployeeJob.setText(job);
  }
  
  public void setNewEmployeeManager (String manager) {
    lbNewEmployeeManager.setText(manager);
  }
  
  public void setNewEmployeeSalary (String salary) {
    lbNewEmployeeSalary.setText(salary);
  }
  
  public void setNewEmployeeCommission (String commission) {
    lbNewEmployeeCommission.setText(commission);
  }
  
  public void setMinSalary(String minSalary) {
    lbMinSalary.setText(minSalary);
  }
  
  public void setMaxSalary(String maxSalary) {
    lbMaxSalary.setText(maxSalary);
  }
	
	public void setManagerIndex(int index){
		cbManager.setSelectedIndex(index);
	}

	public void reset() {
		tfCommission.setText("");
		tfEmail.setText("");
		tfFirstName.setText("");
		tfLastName.setText("");
		tfPhone.setText("");
		tfSalary.setText("");
		}
}
