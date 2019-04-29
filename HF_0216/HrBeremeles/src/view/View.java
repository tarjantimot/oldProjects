package view;

import controller.Controller;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
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
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class View extends JFrame {
  private Controller controller = null;
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
  private boolean comboBoxListenerEnabled;
  private JLabel questionIcon;

  public View() {
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setTitle("Salary update 1.0");
    setSize(780, 350);
    setResizable(false);
    setLocationRelativeTo(this);
    setLayout(new BorderLayout());
    JPanel pnMain = buildMainPanel();
    add(pnMain, BorderLayout.CENTER);
    setEventHandlers();
    comboBoxListenerEnabled = false;
  }
  
  public void setController(Controller controller){
    if(controller == null)
      throw new NullPointerException();
    this.controller = controller;
  }
  
  public void showMainWindow() {
    setVisible(true);
  }
  
  private JPanel buildMainPanel() {
    JPanel pnMain = new JPanel();
    pnMain.setLayout(new BorderLayout());
    //building of the colorful header
    JPanel pnHeader = new JPanel(new GridLayout(5, 1));
    JPanel pnColored = new JPanel();
    JPanel pnColored1 = new JPanel();
    JPanel pnColored2 = new JPanel();
    JPanel pnColored3 = new JPanel();
    pnColored.setBackground(SystemColor.window);
    pnColored1.setBackground(new Color(0, 153, 51));
    pnColored2.setBackground(new Color(51, 255, 119));
    pnColored3.setBackground(new Color(179, 255, 179));
    pnHeader.add(pnColored);
    pnHeader.add(pnColored1);
    pnHeader.add(pnColored2);
    pnHeader.add(pnColored3);
    
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
    return pnMain;
  }

  private void setEventHandlers() {
    tfEmployeeId.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (!tfEmployeeId.getText().equals("")) {
          controller.employeeIdSelected(tfEmployeeId.getText());
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
        controller.salaryUpdate(newSalary);
      }
    });
    
    btReset.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        controller.reset();
      }
    });
    
    ActionListener comboBoxListener = new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        if(comboBoxListenerEnabled){
          if (e.getSource()==cbEmployee) {
            controller.employeeSelected(cbEmployee.getSelectedIndex());
          }
          if (e.getSource()==cbManager) {
            controller.managerSelected(cbManager.getSelectedIndex());
          }
          if (e.getSource()==cbJob) {
            controller.jobSelected(cbJob.getSelectedIndex());
          }
          if (e.getSource()==cbDepartment) {
            controller.departmentSelected(cbDepartment.getSelectedIndex());
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
  
