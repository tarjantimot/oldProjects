package controller;

import java.sql.SQLException;
import model.Model;
import view.View;
import view.Messages;

public class Controller {
  View view;
  Model model;

  public Controller(Model model, View view) {
    this.view = view;
    this.model = model;
  }
  
  public void init(){
    reset();
    view.showMainWindow();
  }
  
  public void employeeSelected(int employeeListIndex) {
    model.setSelectedEmployeeIndex(employeeListIndex);
    if(model.getSelectedEmployeeListIndex() != 0){
      view.setJobList(model.getShownJobList());
      view.setDepartmentList(model.getShownDepartmentList());
      view.setManagerList(model.getShownManagerList());
      view.setSelectedDepartment(model.getSelectedDepartmentIndex());
      view.setSelectedJobIndex(model.getSelectedJobIndex());
      view.setSelectedManager(model.getSelectedManagerIndex());
      view.setEmployeeId("" + model.getSelectedEmployeeId());
      int diffS = diffSalary(model.getSelectedEmployeeInitialSalary());
      int minS = model.getSelectedEmployeeInitialSalary()-diffS;
      int maxS = model.getSelectedEmployeeInitialSalary()+diffS;
      view.setMinSalary(""+minS);
      view.setMaxSalary(""+maxS);
      view.setActualSalary("" + model.getSelectedEmployeeSalary());
      view.setNewSalary("" + model.getSelectedEmployeeSalary());
      view.setSalaryUpdateEnabled(true);
    }
    else{
      view.setJobList(model.getShownJobList());
      model.reset();
      view.setDepartmentList(model.getShownDepartmentList());
      view.setManagerList(model.getShownManagerList());
      view.setJobList(model.getShownJobList());
      view.setSelectedDepartment(model.getSelectedDepartmentIndex());
      view.setSelectedJobIndex(model.getSelectedJobIndex());
      view.setSelectedManager(model.getSelectedManagerIndex());
      view.setEmployeeList(model.getShownEmployeeList());
      view.setSelectedEmployee(employeeListIndex);
      view.setEmployeeId("");
      view.setActualSalary("");
      view.setNewSalary("");
      view.setMinSalary("");
      view.setMaxSalary("");
    }
    view.setSalaryUpdateEnabled(true);
  }
  
  private int diffSalary(int actualSalary) {
    int depWageCost = model.getDepartmentWageCost();
    if ((double)depWageCost*0.03 < (double)actualSalary*0.05) {
      return (int)(depWageCost*0.03);
    } else {
      return (int)(actualSalary*0.05);
    }
  }
  
  public void departmentSelected(int departmentListIndex) {
    model.setSelectedDepartmentIndex(departmentListIndex);
    view.setJobList(model.getShownJobList());
    view.setSelectedJobIndex(model.getSelectedJobIndex());
    view.setManagerList(model.getShownManagerList());
    view.setSelectedManager(model.getSelectedManagerIndex());
    view.setEmployeeList(model.getShownEmployeeList());
    view.setSelectedEmployee(model.getSelectedEmployeeListIndex());
    view.setEmployeeId("");
    view.setActualSalary("");
    view.setNewSalary("");
    view.setMinSalary("");
    view.setMaxSalary("");
  }
  
  public void jobSelected(int jobListIndex) {
    model.setSelectedJobIndex(jobListIndex);
    view.setDepartmentList(model.getShownDepartmentList());
    view.setSelectedDepartment(model.getSelectedDepartmentIndex());
    view.setManagerList(model.getShownManagerList());
    view.setSelectedManager(model.getSelectedManagerIndex());
    view.setEmployeeList(model.getShownEmployeeList());
    view.setSelectedEmployee(model.getSelectedEmployeeListIndex());
    view.setEmployeeId("");
    view.setActualSalary("");
    view.setNewSalary("");
    view.setMinSalary("");
    view.setMaxSalary("");
  }
  
  public void managerSelected(int managerListIndex) {
    model.setSelectedManagerIndex(managerListIndex);
    view.setDepartmentList(model.getShownDepartmentList());
    view.setSelectedDepartment(model.getSelectedDepartmentIndex());
    view.setJobList(model.getShownJobList());
    view.setSelectedJobIndex(model.getSelectedJobIndex());
    view.setEmployeeList(model.getShownEmployeeList());
    view.setSelectedEmployee(model.getSelectedEmployeeListIndex());
    view.setEmployeeId("");
    view.setActualSalary("");
    view.setNewSalary("");
    view.setMinSalary("");
    view.setMaxSalary("");
  }
  
  public void salaryUpdate(int newSalary) {
    int oldSalary = model.getSelectedEmployeeInitialSalary();
    int diff = diffSalary(oldSalary);
    if (newSalary == oldSalary) {
      Messages.showWarningMessage("Warning!", "Salary cannot be unchanged.");
      return;
    } else if (newSalary < oldSalary-diff) {
      Messages.showWarningMessage("Warning!", "Salary cannot be lower than the Minimum Salary value!");
      return;
    } else if (newSalary > oldSalary+diff) {
      Messages.showWarningMessage("Warning!", "Salary cannot be higher than the Maximum Salary value!");
      return;
    }
    boolean res = false;
    try {
      res = model.updateSalary(newSalary);
      if (!res) {
        Messages.showWarningMessage("Salary update 1.0 - Failed to connect", "Unexpected database error.");
      }
    } catch (ClassNotFoundException | SQLException e) {
      model.getLogger().logToFile(e.getMessage());
      Messages.showWarningMessage("Salary update 1.0 - Failed to connect", "Unexpected database error.");
      return;
    }
    Messages.showInfoMessage("Message", "Salary changed successfully!");
  }
  
  public void reset() {
    model.reset();
    view.setEmployeeList(model.getShownEmployeeList());
    view.setSelectedEmployee(model.getSelectedEmployeeListIndex());
    view.setDepartmentList(model.getShownDepartmentList());
    view.setSelectedDepartment(model.getSelectedDepartmentIndex());
    view.setJobList(model.getShownJobList());
    view.setSelectedJobIndex(model.getSelectedJobIndex());
    view.setManagerList(model.getShownManagerList());
    view.setSelectedManager(model.getSelectedManagerIndex());
    view.setActualSalary("");
    view.setEmployeeId("");
    view.setMinSalary("");
    view.setMaxSalary("");
    view.setNewSalary("");
    view.setSalaryUpdateEnabled(false);
  }
  
  private boolean salaryCheck() {
   return false; 
  }
  
  public void employeeIdSelected(String employeeId) {
    int inEmployeeId = Integer.parseInt(employeeId);
    if (model.setSelectedEmployeeById(inEmployeeId)) {
      view.setJobList(model.getShownJobList());
      view.setDepartmentList(model.getShownDepartmentList());
      view.setManagerList(model.getShownManagerList());
      view.setEmployeeList(model.getShownEmployeeList());
      view.setSelectedEmployee(model.getSelectedEmployeeListIndex());
      view.setSelectedDepartment(model.getSelectedDepartmentIndex());
      view.setSelectedJobIndex(model.getSelectedJobIndex());
      view.setSelectedManager(model.getSelectedManagerIndex());
      int diffS = diffSalary(model.getSelectedEmployeeSalary());
      int minS = model.getSelectedEmployeeSalary()-diffS;
      int maxS = model.getSelectedEmployeeSalary()+diffS;
      view.setMinSalary(""+minS);
      view.setMaxSalary(""+maxS);
      view.setActualSalary("" + model.getSelectedEmployeeSalary());
      view.setNewSalary("" + model.getSelectedEmployeeSalary());
      view.setSalaryUpdateEnabled(true);
    } else {
      Messages.showWarningMessage("Warning!", "Employee ID not found.");
    }
  }
}
