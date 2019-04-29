package view;

import controller.SalaryUpdateController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.EmptyBorder;

public class SalaryUpdateView {
  private SalaryUpdateController suc = null;
  private JDialog salaryChangeDialog;
  private JComboBox cbEmployee = new JComboBox();
  private JComboBox cbDepartment = new JComboBox();
  private JComboBox cbManager = new JComboBox();
  private JComboBox cbJob = new JComboBox();
  private JTextField tfEmployeeId = new JTextField("id");
  private JTextField tfMinSalary = new JTextField("0");
  private JTextField tfMaxSalary = new JTextField("0");
  private JTextField tfActualSalary = new JTextField("0");
  private JTextField tfNewSalary = new JTextField("0");
  private JButton btChangeSalary = new JButton("Change salary");
  private JButton btReset = new JButton("Reset");
  private JLabel questionIcon;
  private boolean comboBoxListenerEnabled = false;

  public void setController(SalaryUpdateController suc){
    if(suc == null)
      throw new NullPointerException();
    this.suc = suc;
  }
  
  protected void buildSalaryChangeDialog() {
    salaryChangeDialog = new JDialog();
    salaryChangeDialog.setSize(new Dimension(780, 350));
    salaryChangeDialog.setResizable(false);
    salaryChangeDialog.setLocationRelativeTo(null);
    salaryChangeDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    salaryChangeDialog.setTitle("Updating Salary");
    salaryChangeDialog.setModal(true);
    JPanel pnMain = new JPanel(new BorderLayout());
    JPanel pnHeader = HeaderBuilder.buildHeader();
    
    JPanel pnBody = new JPanel(new BorderLayout());
    pnBody.setBorder(new EmptyBorder(0, 20, 0, 20));
    //building of the panel containing the employee name and ID
    JPanel pnEmployee = new JPanel(new FlowLayout(0, 20, 15));
    pnEmployee.setBorder(new EmptyBorder(0, 0, 20, 0));
    pnEmployee.add(new JLabel("Employee name "));
    pnEmployee.add(cbEmployee);
    cbEmployee.setPreferredSize(new Dimension(400, 22));
    pnEmployee.add(new JLabel("ID "));
    pnEmployee.add(tfEmployeeId);
    tfEmployeeId.setPreferredSize(new Dimension(50, 20));
    tfEmployeeId.setHorizontalAlignment(SwingConstants.RIGHT);
        
    //building of the panel containing the three filters
    JPanel pnCenter = new JPanel(new GridLayout(1, 2, 40, 20));
    JPanel pnFilters = new JPanel(new BorderLayout(0, 5));
    pnFilters.add(new JLabel("    Filter"), BorderLayout.NORTH);
    JPanel pnFiltersInside = new JPanel(new BorderLayout());
    pnFiltersInside.setBorder(BorderFactory.createEtchedBorder());
    JPanel pnFiltersLabels = new JPanel(new GridLayout(3, 1, 0, 15));
    pnFiltersLabels.setBorder(new EmptyBorder(10, 10, 10, 10));
    JPanel pnFiltersComboboxes = new JPanel(new GridLayout(3, 1, 0, 15));
    pnFiltersComboboxes.setBorder(new EmptyBorder(10, 10, 10, 10));
    pnFiltersLabels.add(new JLabel("Department"));
    pnFiltersLabels.add(new JLabel("Manager"));
    pnFiltersLabels.add(new JLabel("Job"));
    pnFiltersComboboxes.add(cbDepartment);
    cbDepartment.setPreferredSize(new Dimension(200, 20));
    pnFiltersComboboxes.add(cbManager);
    pnFiltersComboboxes.add(cbJob);
    pnFiltersInside.add(pnFiltersLabels, BorderLayout.WEST);
    pnFiltersInside.add(pnFiltersComboboxes, BorderLayout.EAST);
    pnFilters.add(pnFiltersInside, BorderLayout.CENTER);
    pnCenter.add(pnFilters);
        
    //building of the panel containing the min/max, actual/new salary
    JPanel pnSalaries = new JPanel(new BorderLayout(0, 10));
    JPanel pnSalariesMinMaxActual = new JPanel(new BorderLayout());
    JPanel pnSalariesMinMaxActualLabels = new JPanel(new GridLayout(3, 1, 0, 15));
    pnSalariesMinMaxActualLabels.add(new JLabel("Minimum salary"));
    pnSalariesMinMaxActualLabels.add(new JLabel("Maximum salary"));
    pnSalariesMinMaxActualLabels.add(new JLabel("Actual salary"));
    pnSalariesMinMaxActualLabels.setBorder(new EmptyBorder(0, 10, 0, 10));
    JPanel pnSalariesMinMaxActualTextfields = new JPanel(new GridLayout(3, 1, 0, 15));
    pnSalariesMinMaxActualTextfields.add(tfMinSalary);
    pnSalariesMinMaxActualTextfields.add(tfMaxSalary);
    pnSalariesMinMaxActualTextfields.add(tfActualSalary);  
    tfMinSalary.setEditable(false);
    tfMinSalary.setHorizontalAlignment(SwingConstants.RIGHT);
    tfMaxSalary.setEditable(false);
    tfMaxSalary.setHorizontalAlignment(SwingConstants.RIGHT);
    tfActualSalary.setEditable(false);
    tfActualSalary.setHorizontalAlignment(SwingConstants.RIGHT);
    JPanel pnSalariesActualCurrencyLabel = new JPanel(new GridLayout(3, 1, 0, 15));
    pnSalariesActualCurrencyLabel.add(new JLabel("$"));
    pnSalariesActualCurrencyLabel.add(new JLabel("$"));
    pnSalariesActualCurrencyLabel.add(new JLabel("$"));
    pnSalariesActualCurrencyLabel.setBorder(new EmptyBorder(0, 10, 0, 10));
    pnSalariesMinMaxActual.add(pnSalariesMinMaxActualLabels, BorderLayout.WEST);
    pnSalariesMinMaxActual.add(pnSalariesMinMaxActualTextfields, BorderLayout.CENTER);
    pnSalariesMinMaxActual.add(pnSalariesActualCurrencyLabel, BorderLayout.EAST);
    
    JPanel pnSalariesNew = new JPanel(new BorderLayout());
    JPanel pnSalariesNewLabel = new JPanel(new GridLayout(1, 1, 0, 15));
    JPanel pnSalariesNewLabelFlow = new JPanel(new FlowLayout());
    pnSalariesNewLabelFlow.add(new JLabel("<html><FONT COLOR=#009933>New salary</FONT></html>"));
    ImageIcon image = new ImageIcon("./files/questionicon.png");
    questionIcon = new JLabel(image);
    pnSalariesNewLabelFlow.add(questionIcon);
    questionIcon.setToolTipText("<html>Rules of setting a new salary:<br>"
            + "1. The new salary cannot be the same as the actual salary.<br>"
            + "2. The employee's salary be can modified by a maximum of 5%.<br>"
            + "3. Updating an employee's salary cannot affect the sum of the<br>"
            + "   salaries of the chosen employee's department by more than 3%.</html>");
    pnSalariesNewLabel.add(pnSalariesNewLabelFlow);
    pnSalariesNewLabelFlow.setBorder(new EmptyBorder(10, 8, 10, 10));
    JPanel pnSalariesNewTextfield = new JPanel(new GridLayout(1, 1, 0, 15));
    pnSalariesNewTextfield.add(tfNewSalary);
    pnSalariesNewTextfield.setBorder(new EmptyBorder(13, 0, 13, 1));
    JPanel pnSalariesNewCurrencyLabel = new JPanel(new GridLayout(1, 1, 0, 15));
    pnSalariesNewCurrencyLabel.add(new JLabel("$"));
    pnSalariesNewCurrencyLabel.setBorder(new EmptyBorder(10, 8, 10, 8));
    pnSalariesNew.setBorder(BorderFactory.createEtchedBorder());
    pnSalariesNew.add(pnSalariesNewLabelFlow, BorderLayout.WEST);
    pnSalariesNew.add(pnSalariesNewTextfield, BorderLayout.CENTER);
    pnSalariesNew.add(pnSalariesNewCurrencyLabel, BorderLayout.EAST);
    
    pnSalaries.add(pnSalariesMinMaxActual, BorderLayout.CENTER);
    pnSalaries.add(pnSalariesNew, BorderLayout.SOUTH);
    
    pnCenter.add(pnSalaries);
    
    //TextField ftNewSalary accepts only numbers (and backspace)
    tfNewSalary.setHorizontalAlignment(SwingConstants.RIGHT);
    
    //building of the panel containing the OK and reset buttons
    JPanel pnButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
    pnButtons.add(btChangeSalary);
    pnButtons.add(btReset);
    btReset.setToolTipText("Clears all the given data.");
    
    //adding all panels to the main panel
    pnBody.add(pnEmployee, BorderLayout.NORTH);
    pnBody.add(pnCenter, BorderLayout.CENTER);
    pnBody.add(pnButtons, BorderLayout.SOUTH);
    pnMain.add(pnHeader, BorderLayout.NORTH);
    pnMain.add(pnBody, BorderLayout.CENTER);
    salaryChangeDialog.add(pnMain);
    salaryChangeDialog.setVisible(true);
  }
  
  protected void setEventHandlers() {
    tfEmployeeId.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (!tfEmployeeId.getText().equals("")) {
          suc.employeeIdSelected(tfEmployeeId.getText());
          tfEmployeeId.setEditable(true);
        }
      }
    });
    tfEmployeeId.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if(!Character.isDigit(e.getKeyChar()) && e.getKeyChar()!=KeyEvent.VK_BACK_SPACE)
          ((JTextField)e.getSource()).setEditable(false);
      }

      @Override
      public void keyReleased(KeyEvent e) {
        ((JTextField)e.getSource()).setEditable(true);
      }
    });
    
    tfNewSalary.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if(!Character.isDigit(e.getKeyChar()) && e.getKeyChar()!=KeyEvent.VK_BACK_SPACE)
          ((JTextField)e.getSource()).setEditable(false);
      }

      @Override
      public void keyReleased(KeyEvent e) {
        ((JTextField)e.getSource()).setEditable(true);
      }
    });
    
    btChangeSalary.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int newSalary = Integer.parseInt(tfNewSalary.getText());
        suc.salaryUpdate(newSalary);
      }
    });
    
    btReset.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        suc.reset();
      }
    });
    
    ActionListener comboBoxListener = new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        if(comboBoxListenerEnabled){
          if (e.getSource()==cbEmployee) {
            suc.employeeSelected(cbEmployee.getSelectedIndex());
          }
          if (e.getSource()==cbManager) {
            suc.managerSelected(cbManager.getSelectedIndex());
          }
          if (e.getSource()==cbJob) {
            suc.jobSelected(cbJob.getSelectedIndex());
          }
          if (e.getSource()==cbDepartment) {
            suc.departmentSelected(cbDepartment.getSelectedIndex());
          }
        }
      }
    };
    cbEmployee.addActionListener(comboBoxListener);
    cbJob.addActionListener(comboBoxListener);
    cbManager.addActionListener(comboBoxListener);
    cbDepartment.addActionListener(comboBoxListener);
  }
  
  public void setEmployeeList(ArrayList<String> employeeList) {
    comboBoxListenerEnabled = false;
    cbEmployee.removeAllItems();
    ArrayList<String> list = employeeList;
    cbEmployee.setModel(new DefaultComboBoxModel(list.toArray()));
    comboBoxListenerEnabled = true;
  }
  
  public void setDepartmentList(ArrayList<String> departmentList) {
    comboBoxListenerEnabled = false;
    cbDepartment.removeAllItems();
    ArrayList<String> list = departmentList;
    cbDepartment.setModel(new DefaultComboBoxModel(list.toArray()));
    comboBoxListenerEnabled = true;
  }
  
  public void setJobList(ArrayList<String> jobList) {
    comboBoxListenerEnabled = false;
    cbJob.removeAllItems();
    ArrayList<String> list = jobList;
    cbJob.setModel(new DefaultComboBoxModel(list.toArray()));
    comboBoxListenerEnabled = true;
  }
  
  public void setManagerList(ArrayList<String> managerList) {
    comboBoxListenerEnabled = false;
    cbManager.removeAllItems();
    ArrayList<String> list = managerList;
    cbManager.setModel(new DefaultComboBoxModel(list.toArray()));
    comboBoxListenerEnabled = true;
  }
  
  public void setEmployeeId(String employeeId) {
    tfEmployeeId.setText(employeeId);
  }
  
  public void setActualSalary(String actualSalary) {
    tfActualSalary.setText(actualSalary);
  }
  
  public void setMinSalary(String minSalary) {
    tfMinSalary.setText(minSalary);
  }
  
  public void setMaxSalary(String maxSalary) {
    tfMaxSalary.setText(maxSalary);
  }
  
  public void setSelectedEmployee(int selectedEmployeeIndex) {
    comboBoxListenerEnabled = false;
    cbEmployee.setSelectedIndex(selectedEmployeeIndex);
    comboBoxListenerEnabled = true;
  }
  
  public void setSelectedDepartment(int selectedDepartmentIndex) {
    comboBoxListenerEnabled = false;
    cbDepartment.setSelectedIndex(selectedDepartmentIndex);
    comboBoxListenerEnabled = true;
  }
  
  public void setSelectedJobIndex(int selectedJobIndex) {
    comboBoxListenerEnabled = false;
    cbJob.setSelectedIndex(selectedJobIndex);
    comboBoxListenerEnabled = true;
  }
  
  public void setSelectedManager(int selectedManagerIndex) {
    comboBoxListenerEnabled = false;
    cbManager.setSelectedIndex(selectedManagerIndex);
    comboBoxListenerEnabled = true;
  }
  
  public void setSalaryUpdateEnabled(boolean enabled) {
    if (enabled) {
      btChangeSalary.setEnabled(true);
    } else {
      btChangeSalary.setEnabled(false);
    }
  }
  
  public void setNewSalary(String newSalary){
    tfNewSalary.setText(newSalary);
  }
}
