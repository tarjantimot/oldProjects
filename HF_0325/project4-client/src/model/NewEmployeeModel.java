package model;

import controller.NewEmployeeController;
import java.util.ArrayList;
import java.util.List;
import model.dtos.DepartmentDTO;
import model.dtos.EmployeeDTO;
import model.dtos.JobDTO;
import model.dtos.ManagerDTO;

public class NewEmployeeModel {
  private NewEmployeeController nec;

  private String firstName, lastName, phone, email, jobId;
	long salary, minSalary, maxSalary;
	float commission;
  private long departmentId, managerId;
  private int defaultManagerIndex;
  private ArrayList<DepartmentDTO> departmentList = new ArrayList<>();
  private int selectedDepartmentIndex = 0;
  private ArrayList<JobDTO> jobList = new ArrayList<>();
  private int selectedJobIndex = 0;
  private ArrayList<ManagerDTO> managerList = new ArrayList<>();
  private int selectedManagerIndex = -1;
  private long selectedManagerId = -1;
	private Server server = null;

  public NewEmployeeModel(Server server) {
		this.server=server;
    departmentList = new ArrayList<>();
    reset();
  }
  
  public void reset(){
    selectedDepartmentIndex = -1;
    selectedJobIndex = -1;
    selectedManagerIndex = -1;
    firstName = "";
    lastName = "";
    phone = "";
    email = "";
  }
  
  public void setController(NewEmployeeController nec){
    if (nec == null)
      throw new NullPointerException();
    this.nec = nec;
  }
  
  public void addNewEmployee() {
    
  }
  
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

	public void setEmail(String email) {
		this.email = email;
	}
	
//  public String getValidEmail(String email) {
//		int max = 2;
//		int actmax = 2;
//		ArrayList<String> emailList = getSimilarEmailList(email);
//		String upEmail = email.toUpperCase();
//		if(emailList.isEmpty())
//			return email.toUpperCase();
//			for (String otherEmail : emailList) {
//				if(otherEmail.toUpperCase().equals(upEmail))
//					return email.toUpperCase()+max;
//				else if(otherEmail.toUpperCase().matches(upEmail+"(\\d{1,})")){
//						actmax=Integer.parseInt(otherEmail.substring(email.length()));
//						if(actmax>max)
//							max=actmax;
//				}
//			}
//			return email.toUpperCase()+max+1;
//		}
  
  public String validateEmail(String preferredEMail){
    if (preferredEMail == null)
      throw new NullPointerException();
    String eMail = null;
    String preferredEMailUpperCase = preferredEMail.toUpperCase();
    if(preferredEMail.length() > 2){
      List<String> eMailList = getSimilarEmailList(preferredEMail);
      boolean ok = false;
      int num = 2;
      eMail = preferredEMailUpperCase;
      while(!ok){
        ok = true;
        for (String em : eMailList) {
          if (eMail.toUpperCase().equals(em.toUpperCase())){
            ok = false;
          }
        }
        if (!ok){
          eMail = preferredEMailUpperCase + num;
          num++;
        }
      }
    }
    return eMail;
  }

  public ArrayList<String> getDepartmentList(){
    updateDepartmentList();
    setDepartmentId();
    ArrayList<String> list = new ArrayList<>();
    list.add("select one");
    for (DepartmentDTO department : departmentList) {
      list.add(department.getDepartmentName());
    }
    return list;
  }
  
  private void setDepartmentId(){
    if(selectedDepartmentIndex != -1){
      boolean found = false;
      for (int i = 0; i < departmentList.size(); i++) {
        if(departmentList.get(i).getDepartmentId() == departmentId){
          selectedDepartmentIndex = i;
          found = true;
        }
      }
      if (!found){
        selectedDepartmentIndex = -1;
        //departmentId = departmentList.get(selectedDepartmentIndex + 1).getId();        
      }
    }
  }
  
  public ArrayList<String> getJobList(){
    updateJobList();
    setJobId();
    ArrayList<String> list = new ArrayList<>();
    list.add("select one");
    for (JobDTO job : jobList) {
      list.add(job.getJobTitle());
    }
    return list;
  }
  
  private void setJobId(){
    if(selectedJobIndex != -1){
      boolean found = false;
      for (int i = 0; i < jobList.size(); i++) {
        if(jobId.equals(jobList.get(i).getJobTitle())){
          selectedJobIndex = i;
          found = true;
        }
      }
      if (!found){
        selectedJobIndex = -1;
        //jobId = jobList.get(selectedJobIndex - 1).getId();        
      }
    }
  }
  
  public ArrayList<String> getManagerList(){
    updateManagerList();
    setManagerId();
    ArrayList<String> list = new ArrayList<>();
    //list.add("select one");
    for (ManagerDTO manager : managerList) {
      list.add(manager.getLastName() + ", " + manager.getFirstName() + 
              " (id: " + manager.getEmployeeId() + ")");
    }
    return list;
  }
  
  private void setManagerId(){
    if(selectedManagerIndex < 0){ //if no manager has been set
      selectedManagerIndex = 0;
      this.managerId = managerList.get(selectedManagerIndex).getEmployeeId(); //set a fake but valid manager
      ManagerDTO manager = departmentList.get(selectedDepartmentIndex).getManager();
      if(manager == null){  //check if the manager exists, some departments has no managers assigned
        for (int i = 0; i < managerList.size(); i++) {  //search for the manager who has no manager
          if(managerList.get(i) == null){
            selectedManagerIndex = i;
            this.managerId = managerList.get(i).getEmployeeId();
          }
        } //here we have a fake manager or a manager who has no manager as manager
      }
      else{
        for (int i = 0; i < managerList.size(); i++) {  //search for the manager of the selected department
          if(managerList.get(i).getEmployeeId() == manager.getEmployeeId()){
            selectedManagerIndex = i;
            this.managerId = managerId;
          }
        }
      }
    }
    else { // if there was a manager set already
      selectedManagerIndex = 0;  //select a fake manager
      for (int i = 0; i < managerList.size(); i++) {
        if(managerList.get(i).getEmployeeId() == this.managerId){
          selectedManagerIndex = i; //find the previously selected manager by ID
        }
      }
      managerId = managerList.get(selectedManagerIndex).getEmployeeId();
    }
  }
  
  public void setSelectedDepartmentIndex(int departmentIndex) {
    if(departmentIndex < 1 || departmentIndex > departmentList.size())
      throw new IllegalArgumentException();
    this.selectedDepartmentIndex = departmentIndex - 1;
    DepartmentDTO d = departmentList.get(selectedDepartmentIndex);
    departmentId = d.getDepartmentId();
  }

  public void setSelectedJobIndex(int jobIndex) {
    if(jobIndex < 1 || jobIndex > jobList.size())
      throw new IllegalArgumentException();
    this.selectedJobIndex = jobIndex - 1;
    JobDTO j = jobList.get(selectedJobIndex);
    jobId = j.getJobId();
    minSalary = j.getMinSalary();
    maxSalary = j.getMaxSalary();
  }

  public void setSelectedManagerIndex(int managerIndex) {
    if(managerIndex < 0 || managerIndex >= managerList.size())
      throw new IllegalArgumentException();
    this.selectedManagerIndex = managerIndex;
    ManagerDTO m = managerList.get(selectedManagerIndex);
    managerId = m.getEmployeeId();
  }

  public void setSalary(int salary) {
    this.salary = salary;
  }

  public void setComission(float comission) {
    this.commission = comission;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getPhone() {
    return phone;
  }

  public String getEmail() {
    return email;
  }

  public int getSelectedDepartmentIndex() {
    if(selectedDepartmentIndex == -1){
      
    }
    
    return selectedDepartmentIndex + 1;
  }
  
  public String getSelectedDepartmentName(){
    return departmentList.get(selectedDepartmentIndex).getDepartmentName();
  }

  public int getSelectedJobIndex() {
    return selectedJobIndex + 1;
  }
  
  public String getSelectedJobTitle(){
    return jobList.get(selectedJobIndex).getJobTitle();
  }

  public int getSelectedManagerIndex() {
    return selectedManagerIndex;
  }
  
  public String getSelectedManagerName(){
    ManagerDTO m = managerList.get(selectedManagerIndex);
    return m.getLastName() +", "+ m.getFirstName() + " (" + m.getEmployeeId() + ")";
  }

  public long getSalary() {
    return salary;
  }

  public float getComission() {
    return commission;
  }

  public long getMinSalary() {
    return minSalary;
  }

  public long getMaxSalary() {
    return maxSalary;
  }
  
  private ArrayList<String> getSimilarEmailList(String preferredEmail){
    return server.getSimilarEmailList(preferredEmail);
  }
  
  private void updateDepartmentList(){
    this.departmentList.clear();
    ArrayList<DepartmentDTO> departmentList = server.getDepartmentList();
    for (DepartmentDTO department : departmentList) {
      this.departmentList.add(department);
    }
  }
  
  private void updateJobList(){
    this.jobList.clear();
    ArrayList<JobDTO> jobList = server.getJobList();
    for (JobDTO job : jobList) {
      this.jobList.add(job);
    }
  }
  
  private void updateManagerList(){
    this.managerList.clear();
    ArrayList<ManagerDTO> managerList = server.getManagerList();
    for (ManagerDTO manager : managerList) {
      this.managerList.add(manager);
    }
  }
  
  public long saveNewEmployee(){
    return saveNewEmployee(firstName, lastName, email, phone, departmentId, jobId, salary, commission, managerId);
  }
  
  public long saveNewEmployee(String firstName, String lastName, String eMail,
          String phoneNumber, long departmentId, String jobId, long salary, float commission, long managerId){
		DepartmentDTO department = null;
		for (DepartmentDTO departmentDTO : departmentList) {
			if(departmentDTO.getDepartmentId()==departmentId)
				department=departmentDTO;
		}
    JobDTO jobNewEmpl = null;
		for (JobDTO jobDTO : jobList) {
			if(jobDTO.getJobId().equals(jobId))
				jobNewEmpl=jobDTO;
		}
		ManagerDTO managerNewEmpl = null;
		for (ManagerDTO managerDTO : managerList) {
			if(managerDTO.getEmployeeId()==managerId)
				managerNewEmpl=managerDTO;
		}
		//EmployeeID-nak megadtam a 0l értéket, mert ha átmegy a szerverre, ott kap az adatbázistól saját értéket, ezt csak azért csináltam, hogy át tudjam vinni a túloldalra.
			EmployeeDTO employee = new EmployeeDTO(0l, firstName, lastName, eMail, phoneNumber, jobNewEmpl, salary, commission, managerNewEmpl, department);
    return server.insertEmployee(employee);
  }
  
//  public static void main(String[] args) {
//    NewEmployeeModel m = new NewEmployeeModel();
//    m.saveNewEmployee("jani", "nag", "eMail26", "phoneNumber", 90, "AD_PRES", 6000, 0.0f, 100);
//  }
}
