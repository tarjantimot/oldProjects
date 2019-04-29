package controller;

import java.sql.SQLException;
import model.SalaryUpdateModel;
import view.Messages;
import view.SalaryUpdateView;

public class SalaryUpdateController {
  SalaryUpdateView suv;
  SalaryUpdateModel model;

  public SalaryUpdateController(SalaryUpdateModel model, SalaryUpdateView suv) {
    this.model = model;
    this.suv = suv;
  }
  
  public void init(){
    reset();
//    view.showMainWindow();
  }
  
  public void employeeSelected(int employeeListIndex) {
    model.setSelectedEmployeeIndex(employeeListIndex);
    if(model.getSelectedEmployeeListIndex() != 0){
      suv.setJobList(model.getShownJobList());
      suv.setDepartmentList(model.getShownDepartmentList());
      suv.setManagerList(model.getShownManagerList());
      suv.setEmployeeList(model.getShownEmployeeList());
      suv.setSelectedDepartment(model.getSelectedDepartmentIndex());
      suv.setSelectedJobIndex(model.getSelectedJobIndex());
      suv.setSelectedManager(model.getSelectedManagerIndex());
      suv.setSelectedEmployee(model.getSelectedEmployeeListIndex());
      suv.setEmployeeId("" + model.getSelectedEmployeeId());
      int diffS = diffSalary(model.getSelectedEmployeeInitialSalary());
      int minS = model.getSelectedEmployeeInitialSalary()-diffS;
      int maxS = model.getSelectedEmployeeInitialSalary()+diffS;
      suv.setMinSalary(""+minS);
      suv.setMaxSalary(""+maxS);
      suv.setActualSalary("" + model.getSelectedEmployeeSalary());
      suv.setNewSalary("" + model.getSelectedEmployeeSalary());
      suv.setSalaryUpdateEnabled(true);
    }
    else{
      suv.setJobList(model.getShownJobList());
//      model.reset();
      suv.setDepartmentList(model.getShownDepartmentList());
      suv.setManagerList(model.getShownManagerList());
      suv.setJobList(model.getShownJobList());
      suv.setSelectedDepartment(model.getSelectedDepartmentIndex());
      suv.setSelectedJobIndex(model.getSelectedJobIndex());
      suv.setSelectedManager(model.getSelectedManagerIndex());
      suv.setEmployeeList(model.getShownEmployeeList());
      suv.setSelectedEmployee(employeeListIndex);
      suv.setEmployeeId("");
      suv.setActualSalary("");
      suv.setNewSalary("");
      suv.setMinSalary("");
      suv.setMaxSalary("");
      suv.setSalaryUpdateEnabled(false);
    }
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
    suv.setJobList(model.getShownJobList());
    suv.setSelectedJobIndex(model.getSelectedJobIndex());
    suv.setManagerList(model.getShownManagerList());
    suv.setSelectedManager(model.getSelectedManagerIndex());
    suv.setEmployeeList(model.getShownEmployeeList());
    suv.setSelectedEmployee(model.getSelectedEmployeeListIndex());
    suv.setEmployeeId("");
    suv.setActualSalary("");
    suv.setNewSalary("");
    suv.setMinSalary("");
    suv.setMaxSalary("");
  }
  
  public void jobSelected(int jobListIndex) {
    model.setSelectedJobIndex(jobListIndex);
    suv.setDepartmentList(model.getShownDepartmentList());
    suv.setSelectedDepartment(model.getSelectedDepartmentIndex());
    suv.setManagerList(model.getShownManagerList());
    suv.setSelectedManager(model.getSelectedManagerIndex());
    suv.setEmployeeList(model.getShownEmployeeList());
    suv.setSelectedEmployee(model.getSelectedEmployeeListIndex());
    suv.setEmployeeId("");
    suv.setActualSalary("");
    suv.setNewSalary("");
    suv.setMinSalary("");
    suv.setMaxSalary("");
  }
  
  public void managerSelected(int managerListIndex) {
    model.setSelectedManagerIndex(managerListIndex);
    suv.setDepartmentList(model.getShownDepartmentList());
    suv.setSelectedDepartment(model.getSelectedDepartmentIndex());
    suv.setJobList(model.getShownJobList());
    suv.setSelectedJobIndex(model.getSelectedJobIndex());
    suv.setEmployeeList(model.getShownEmployeeList());
    suv.setSelectedEmployee(model.getSelectedEmployeeListIndex());
    suv.setEmployeeId("");
    suv.setActualSalary("");
    suv.setNewSalary("");
    suv.setMinSalary("");
    suv.setMaxSalary("");
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
      Messages.showWarningMessage("Salary update 1.0 - Failed to connect", "Unexpected database error.");
      return;
    }
    Messages.showInfoMessage("Message", "Salary changed successfully!");
  }
  
  public void reset() {
    model.reset();
    suv.setEmployeeList(model.getShownEmployeeList());
    suv.setSelectedEmployee(model.getSelectedEmployeeListIndex());
    suv.setDepartmentList(model.getShownDepartmentList());
    suv.setSelectedDepartment(model.getSelectedDepartmentIndex());
    suv.setJobList(model.getShownJobList());
    suv.setSelectedJobIndex(model.getSelectedJobIndex());
    suv.setManagerList(model.getShownManagerList());
    suv.setSelectedManager(model.getSelectedManagerIndex());
    suv.setActualSalary("");
    suv.setEmployeeId("");
    suv.setMinSalary("");
    suv.setMaxSalary("");
    suv.setNewSalary("");
    suv.setSalaryUpdateEnabled(false);
  }
  
  private boolean salaryCheck() {
   return false; 
  }
  
  public void employeeIdSelected(String employeeId) {
    int inEmployeeId = Integer.parseInt(employeeId);
    if (model.setSelectedEmployeeById(inEmployeeId)) {
      suv.setJobList(model.getShownJobList());
      suv.setDepartmentList(model.getShownDepartmentList());
      suv.setManagerList(model.getShownManagerList());
      suv.setEmployeeList(model.getShownEmployeeList());
      suv.setSelectedEmployee(model.getSelectedEmployeeListIndex());
      suv.setSelectedDepartment(model.getSelectedDepartmentIndex());
      suv.setSelectedJobIndex(model.getSelectedJobIndex());
      suv.setSelectedManager(model.getSelectedManagerIndex());
      int diffS = diffSalary(model.getSelectedEmployeeSalary());
      int minS = model.getSelectedEmployeeSalary()-diffS;
      int maxS = model.getSelectedEmployeeSalary()+diffS;
      suv.setMinSalary(""+minS);
      suv.setMaxSalary(""+maxS);
      suv.setActualSalary("" + model.getSelectedEmployeeSalary());
      suv.setNewSalary("" + model.getSelectedEmployeeSalary());
      suv.setSalaryUpdateEnabled(true);
    } else {
      Messages.showWarningMessage("Warning!", "Employee ID not found.");
    }
  }
}
