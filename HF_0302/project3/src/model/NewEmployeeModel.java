package model;

import controller.NewEmployeeController;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.entities.DepartmentEntity;
import model.entities.EmployeeEntity;
import model.entities.JobEntity;
import model.entities.ManagerEntity;

class Job{
  String id;
  String title;
  long minSalary;
  long maxSalary;

  public Job(String id, String title, long minSalary, long maxSalary) {
    this.id = id;
    this.title = title;
    this.minSalary = minSalary;
    this.maxSalary = maxSalary;
  }

  public String getId() {
    return id;
  }

  public long getMinSalary() {
    return minSalary;
  }

  public long getMaxSalary() {
    return maxSalary;
  }

  public String getTitle() {
    return title;
  }

  @Override
  public String toString() {
    return "Job{" + "id=" + id + ", title=" + title + ", minSalary=" + minSalary + ", maxSalary=" + maxSalary + '}';
  }
  
}

class Department{
  long id;
  String name;
  long managerId;

  public Department(long id, String name, long managerId) {
    this.id = id;
    this.name = name;
    this.managerId = managerId;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public long getManagerId() {
    return managerId;
  }

  @Override
  public String toString() {
    return "Department{" + "id=" + id + ", name=" + name + ", managerId=" + managerId + '}';
  }
  
}

class Manager{
  long employeeId;
  String name;
  long managerId;

  public Manager(long employeeId, String name, long managerId) {
    this.employeeId = employeeId;
    this.name = name;
    this.managerId = managerId;
  }

  public long getEmployeeId() {
    return employeeId;
  }

  public String getName() {
    return name;
  }

  public long getManagerId() {
    return managerId;
  }

  @Override
  public String toString() {
    return "Manager{" + "employeeId=" + employeeId + ", name=" + name + ", managerId=" + managerId + '}';
  }
  
}

public class NewEmployeeModel {
  private NewEmployeeController nec;
  private int MIN_SALARY = 0;
  private int MAX_SALARY = 1;
  private String firstName, lastName, phone, email, jobId;
	long salary, minSalary, maxSalary;
	float commission;
  private long departmentId, managerId;
  private EntityManagerFactory entityManagerFactory;
  private int defaultManagerIndex;
  private ArrayList<Department> departmentList = new ArrayList<>();
  private int selectedDepartmentIndex = 0;
  private ArrayList<Job> jobList = new ArrayList<>();
  private int selectedJobIndex = 0;
  private ArrayList<Manager> managerList = new ArrayList<>();
  private int selectedManagerIndex = -1;
  private long selectedManagerId = -1;

  public NewEmployeeModel() {
    entityManagerFactory = Persistence.createEntityManagerFactory("project3PU");
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
    for (Department department : departmentList) {
      list.add(department.getName());
    }
    return list;
  }
  
  private void setDepartmentId(){
    if(selectedDepartmentIndex != -1){
      boolean found = false;
      for (int i = 0; i < departmentList.size(); i++) {
        if(departmentList.get(i).getId() == departmentId){
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
    for (Job job : jobList) {
      list.add(job.getTitle());
    }
    return list;
  }
  
  private void setJobId(){
    if(selectedJobIndex != -1){
      boolean found = false;
      for (int i = 0; i < jobList.size(); i++) {
        if(jobId.equals(jobList.get(i).getId())){
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
    for (Manager manager : managerList) {
      list.add(manager.getName() + " (id: " + manager.getEmployeeId() + ")");
    }
    return list;
  }
  
  private void setManagerId(){
    if(selectedManagerIndex < 0){ //if no manager has been set
      selectedManagerIndex = 0;
      this.managerId = managerList.get(selectedManagerIndex).getEmployeeId(); //set a fake but valid manager
      long managerId = departmentList.get(selectedDepartmentIndex).managerId; //get the selected department's manager ID
      if(managerId <= 0){  //check if the manager exists, some departments has no managers assigned
        for (int i = 0; i < managerList.size(); i++) {  //search for the manager who has no manager
          if(managerList.get(i).managerId < 0){
            selectedManagerIndex = i;
            this.managerId = managerList.get(i).employeeId;
          }
        } //here we have a fake manager or a manager who has no manager as manager
      }
      else{
        for (int i = 0; i < managerList.size(); i++) {  //search for the manager of the selected department
          if(managerList.get(i).getEmployeeId() == managerId){
            selectedManagerIndex = i;
            this.managerId = managerId;
          }
        }
      }
    }
    else { // if there was a manager set already
      selectedManagerIndex = 0;  //select a fake manager
      for (int i = 0; i < managerList.size(); i++) {
        if(managerList.get(i).employeeId == this.managerId){
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
    Department d = departmentList.get(selectedDepartmentIndex);
    departmentId = d.getId();
  }

  public void setSelectedJobIndex(int jobIndex) {
    if(jobIndex < 1 || jobIndex > jobList.size())
      throw new IllegalArgumentException();
    this.selectedJobIndex = jobIndex - 1;
    Job j = jobList.get(selectedJobIndex);
    jobId = j.getId();
    minSalary = j.getMinSalary();
    maxSalary = j.getMaxSalary();
  }

  public void setSelectedManagerIndex(int managerIndex) {
    if(managerIndex < 0 || managerIndex >= managerList.size())
      throw new IllegalArgumentException();
    this.selectedManagerIndex = managerIndex;
    Manager m = managerList.get(selectedManagerIndex);
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
    return departmentList.get(selectedDepartmentIndex).getName();
  }

  public int getSelectedJobIndex() {
    return selectedJobIndex + 1;
  }
  
  public String getSelectedJobTitle(){
    return jobList.get(selectedJobIndex).title;
  }

  public int getSelectedManagerIndex() {
    return selectedManagerIndex;
  }
  
  public String getSelectedManagerName(){
    Manager m = managerList.get(selectedManagerIndex);
    return m.getName() + " (" + m.getEmployeeId() + ")";
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
    EntityManager em = entityManagerFactory.createEntityManager();
    String eMail = preferredEmail.toUpperCase();
    Query q = em.createQuery("SELECT e.eMail FROM EmployeeEntity e "
            + "WHERE UPPER(e.eMail) = '" + eMail + "' OR UPPER(e.eMail) LIKE '" + eMail + "%'");
    List<String> similarEMails = (List<String>) q.getResultList();
    ArrayList<String> returnList = new ArrayList<>();
    returnList.addAll(similarEMails);
    em.close();
    return returnList;
  }
  
  private void updateDepartmentList(){
    EntityManager em = entityManagerFactory.createEntityManager();
    Query q = em.createQuery("SELECT D FROM DepartmentEntity D ORDER BY D.departmentName");
    List<DepartmentEntity> departmentList = (List<DepartmentEntity>) q.getResultList();
    this.departmentList.clear();
    for (DepartmentEntity department : departmentList) {
      this.departmentList.add(
              new Department(department.getDepartmentId(), department.getDepartmentName(), 
                      department.getManagerId() != null ? department.getManagerId() : -1L));
    }
    em.close();
  }
  
  private void updateJobList(){
    EntityManager em = entityManagerFactory.createEntityManager();
    Query q = em.createQuery("SELECT J FROM JobEntity J ORDER BY J.jobTitle");
    List<JobEntity> jobList = (List<JobEntity>) q.getResultList();
    this.jobList.clear();
    for (JobEntity job : jobList) {
      this.jobList.add(new Job(job.getJobId(), job.getJobTitle(), job.getMinSalary(), job.getMaxSalary()));
    }
    em.close();
  }
  
  private void updateManagerList(){
    EntityManager em = entityManagerFactory.createEntityManager();
    Query q = em.createQuery("SELECT M FROM ManagerEntity M ORDER BY M.lastName, M.firstName");
    List<ManagerEntity> managerList = (List<ManagerEntity>) q.getResultList();
    this.managerList.clear();
    for (ManagerEntity manager : managerList) {
      this.managerList.add(new Manager(manager.getEmployeeId(), manager.getName(), 
              manager.getManagerId() != null ? manager.getManagerId() : -1L));
    }
    em.close();
  }
  
  public long saveNewEmployee(){
    return saveNewEmployee(firstName, lastName, email, phone, departmentId, jobId, salary, commission, managerId);
  }
  
  public long saveNewEmployee(String firstName, String lastName, String eMail,
          String phoneNumber, long departmentId, String jobId, long salary, float commission, long managerId){
    EntityManager em = entityManagerFactory.createEntityManager();
    EmployeeEntity employee = new EmployeeEntity(firstName, lastName, eMail.toUpperCase(), phoneNumber, jobId, salary, commission, managerId, departmentId);
    employee.setHireDate();
    //Query q = em.createNativeQuery("SELECT EMPLOYEES_SEQ.NEXTVAL FROM DUAL");
    //long employeeId = ((BigDecimal)q.getSingleResult()).longValue();
    //employee.setEmployeeId(employeeId);
    em.getTransaction().begin();
    em.persist(employee);
    em.getTransaction().commit();
    long employeeId = employee.getEmployeeId();
    long returnValue = -1;
    if(em.find(EmployeeEntity.class, employeeId) != null){
      returnValue = employeeId;
    }
    em.close();
    return returnValue;
  }
  
  public static void main(String[] args) {
    NewEmployeeModel m = new NewEmployeeModel();
    m.saveNewEmployee("jani", "nag", "eMail2", "phoneNumber", 90, "AD_PRES", 6000, 0.0f, 100);
  }
}
