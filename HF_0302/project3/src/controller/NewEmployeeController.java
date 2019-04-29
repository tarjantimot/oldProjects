package controller;

import javax.swing.JOptionPane;
import model.NewEmployeeModel;
import view.Messages;
import view.NewEmployeeView;


public class NewEmployeeController {
  NewEmployeeView nev;
  NewEmployeeModel nem;

  public NewEmployeeController(NewEmployeeModel model, NewEmployeeView nev) {
    this.nem = model;
    this.nev = nev;
  }
  
  public void init() {
//    reset();
	}
  
  public void reset() {
    nev.reset();
  }
  
  public void addNewEmployee() {
    int dialogResult = Messages.showConfirmDialog(
            "Adding a new employee", "Are you sure you want to add this employee?");
    if (dialogResult == JOptionPane.YES_OPTION) {
      try {
        long l = nem.saveNewEmployee();
        Messages.showInfoMessage("Information", "The new employee has been added succesfully. Id: "+l);
      } catch (Exception e) {
        Messages.showWarningMessage("Error", "An error occured during the saving process. "
                + "Please try again.");
      }
    }
      reset();
  }
  
  public void cancelOrExitPressed() {
    int dialogResult = Messages.showConfirmDialog(
            "Interruping the process", "Are you sure you want to cancel the process?");
    if (dialogResult == JOptionPane.YES_OPTION) {
      nev.cancelProcess();
    } 
  }
  
  public boolean getDataFromFirstInputPanel(String firstName, String lastName, String phone) {
      //TODO ide kellene egy normális ellenőrzés...
			boolean validInput = false;
			System.out.println(firstName);
			System.out.println(lastName);
			System.out.println(phone);
      if (firstName.matches("[a-zA-ZáéőúűöüóíÍÁÉŰÚŐÓÜ]{1,}") && lastName.matches("[a-zA-ZáéőúűöüóíÍÁÉŰÚŐÓÜ]{1,}") && phone.matches("\\d{3}.\\d{3}.\\d{4}")) {
				nem.setFirstName(firstName);
				nem.setLastName(lastName);
				nem.setPhone(phone);
				nem.setEmail(nem.validateEmail(firstName.charAt(0)+lastName));
				setNewEmployeeFirstNameLabel();
				setNewEmployeeLastNameLabel();
				setNewEmployeePhoneLabel();
				setNewEmployeeEmailLabel();
				nev.setDepartmentList(nem.getDepartmentList());
        nev.setDepartmentIdnex(nem.getSelectedDepartmentIndex());
				
				validInput = true;
      } else {
				Messages.showWarningMessage("Wrong input!", "All fields must be filled!");
      }
    return validInput;
  }
  
  public boolean getDataFromSecondInputPanel(int deparment) {
		boolean isIt = false;
		if(deparment!=0){
			isIt=true;
			nev.setJobList(nem.getJobList());
      nev.setJobIndex(nem.getSelectedJobIndex());
      nem.setSelectedDepartmentIndex(deparment);
			setNewEmployeeDepartmentLabel();
		} else {
			Messages.showWarningMessage("Wrong input!", "Please choose a department!");
		}
		return isIt;
  }
  
  public boolean getDataFromThirdInputPanel(int job) {
		boolean isIt = false;
		if(job!=0){
			isIt=true;
			nev.setManagerList(nem.getManagerList());
      nev.setManagerIndex(nem.getSelectedManagerIndex());
      nem.setSelectedJobIndex(job);
			setNewEmployeeJobLabel();
			setMinSalaryLabel();
			setMaxSalaryLabel();
		} else {
			Messages.showWarningMessage("Wrong input!", "Please choose a job!");
		}
		return isIt;
  }
  
  public void getDataFromFourthInputPanel(int manager) {
		nem.setSelectedManagerIndex(manager);
		setNewEmployeeManagerLabel();
  }
  
  public boolean getDataFromFifthInputPanel(String salary, String commission) {
		if(commission.equals(" .  ")){
			Messages.showWarningMessage("Wrong input!", "The commission format must be like: X.XX");
			return false;
		} else {
		int numSalary = Integer.parseInt(salary);
		float numComission = Float.parseFloat(commission);
		boolean isItValid = numSalary >= nem.getMinSalary() && numSalary <= nem.getMaxSalary() && numComission>=0 && numComission<=1;
		if (isItValid) {
			nem.setSalary(numSalary);
			nem.setComission(numComission);
			setNewEmployeeSalaryLabel();
			setNewEmployeeCommissionLabel();
		} else {
			Messages.showWarningMessage("Wrong input!", "Please fill every box as it is indicated!");
		}
    return isItValid;
		}
  }
  
  public void setNewEmployeeFirstNameLabel() {
    nev.setNewEmployeeFirstName(nem.getFirstName());
  }
  
  public void setNewEmployeeLastNameLabel() {
    nev.setNewEmployeeLastName(nem.getLastName());
  }
  
  public void setNewEmployeePhoneLabel() {
    nev.setNewEmployeePhone(nem.getPhone());
  }
  
  public void setNewEmployeeEmailLabel() {
    nev.setNewEmployeeEmail(nem.getEmail());
  }
  
  public void setNewEmployeeDepartmentLabel() {
    nev.setNewEmployeeDepartment(nem.getSelectedDepartmentName());
  }
  
  public void setNewEmployeeJobLabel() {
    nev.setNewEmployeeJob(nem.getSelectedJobTitle());
  }
  
   public void setNewEmployeeManagerLabel() {
    nev.setNewEmployeeManager(nem.getSelectedManagerName());
  }
  
  public void setNewEmployeeSalaryLabel() {
    nev.setNewEmployeeSalary(nem.getSalary()+"");
  }
  
  public void setNewEmployeeCommissionLabel() {
    nev.setNewEmployeeCommission(nem.getComission()+"");
  }
  
  public void setMinSalaryLabel() {
    nev.setMinSalary(nem.getMinSalary()+"");
  }
  
  public void setMaxSalaryLabel() {
    nev.setMaxSalary(nem.getMaxSalary()+"");
  }
	
	public void departmentListInit(){
		nev.setDepartmentList(nem.getDepartmentList());     
	}

	public void jobListInit(){
			nev.setJobList(nem.getJobList());    
	}

	public void managetListInit(){
			nev.setManagerList(nem.getManagerList());

	}
}
