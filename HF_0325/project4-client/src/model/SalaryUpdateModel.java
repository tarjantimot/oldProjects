package model;

import controller.SalaryUpdateController;
import java.sql.SQLException;
import java.util.ArrayList;
import model.dtos.DepartmentDTO;
import model.dtos.EmployeeDTO;
import model.dtos.JobDTO;
import model.dtos.ManagerDTO;

public class SalaryUpdateModel {
	private static final long SELECT_ONE_INDEX = -1;
  private static final long HAS_NONE_INDEX = -2;
  private static final String SELECT_ONE_ID = "select one";
  private ArrayList<EmployeeDTO> employeeList = new ArrayList<>();
  private ArrayList<EmployeeDTO> employeeListFull = new ArrayList<>();
  private ArrayList<EmployeeDTO> initialEmployeeList = new ArrayList<>();
	private ArrayList<ManagerDTO> managerList= new ArrayList<>();
	private ArrayList<JobDTO> jobList= new ArrayList<>();
	private ArrayList<DepartmentDTO> departmentList= new ArrayList<>();
  private EmployeeDTO selectedEmployee;
  private DepartmentDTO selectedDepartment;
  private JobDTO selectedJob;
  private ManagerDTO selectedManager;
  private SalaryUpdateController suc;
  private Logger logger;
  private Server server;

  
	public SalaryUpdateModel(Server server) {
		logger = new Logger();
    this.server = server;
    initialEmployeeList = server.getEmployeeList();
    reset();
	}
	
  public Logger getLogger(){
    return logger;
  }
	
  public boolean updateSalary(int newSalary) throws ClassNotFoundException, SQLException {
    EmployeeDTO employee = new EmployeeDTO(selectedEmployee.getEmployeeId(), "", "", "", "", null, new Long(newSalary), 0.0F, null, null);
    long result = server.updateSalary(employee);
    return result == 2;
  }
  
  /**
   * Sets the department the job and the manager according to the selected employee
   */
  private void setDepartmentJobManager(){
    DepartmentDTO dep = selectedEmployee.getDepartment();
    if(dep != null){
      selectedDepartment = dep;
    }
    else if(selectedEmployee.getEmployeeId() == SELECT_ONE_INDEX){
      selectedDepartment = departmentList.get(0);
    }
    else {
      selectedDepartment = departmentList.get(departmentList.size()-1); 
    }
    JobDTO job = selectedEmployee.getJob();
    if(job!=null){ 
      selectedJob = job;
    }
    else{
      selectedJob = jobList.get(0);
    }
    ManagerDTO man = selectedEmployee.getManager();
    if(man != null){
      selectedManager = man;
    }
    else if(selectedEmployee.getEmployeeId() == SELECT_ONE_INDEX){
      selectedManager = managerList.get(0);
    }
    else{
      selectedManager = managerList.get(managerList.size()-1);
    }
  }
    
  private void updateLists(){
    departmentList = server.getDepartmentList();
    departmentList.add(0, new DepartmentDTO(SELECT_ONE_INDEX, "", null));
    departmentList.add(new DepartmentDTO(HAS_NONE_INDEX, "", null));
    managerList = server.getManagerList();
    managerList.add(0, new ManagerDTO(SELECT_ONE_INDEX, "", "", HAS_NONE_INDEX));
    managerList.add(new ManagerDTO(HAS_NONE_INDEX, "", "", HAS_NONE_INDEX));
    jobList = server.getJobList();
    jobList.add(0,new JobDTO(SELECT_ONE_ID, "", 0L, 0L));
    employeeListFull = server.getEmployeeList();
    employeeListFull.add(0, new EmployeeDTO(SELECT_ONE_INDEX, "", "", "", "", null, 0L, 0.0F, null, null));
  }
  
  public void setSelectedEmployeeIndex(int listedEmployeeIndex) throws IndexOutOfBoundsException{
    if (listedEmployeeIndex < 0 || listedEmployeeIndex >= employeeList.size())
      throw new IndexOutOfBoundsException();
    selectedEmployee = employeeList.get(listedEmployeeIndex);
    updateLists();
    if(listedEmployeeIndex != 0)
      setDepartmentJobManager();
    setEmployeeList();
  }
  
  public boolean setSelectedEmployeeById(long employeeId){
    boolean found = false;
    EmployeeDTO employee;
    for (int i = 0; i < employeeListFull.size() && !found; i++) {
      if((employee = employeeListFull.get(i)).getEmployeeId() == employeeId){
        selectedEmployee = employee;
        updateLists();
        setDepartmentJobManager();
        setEmployeeList();
        found = true;
      }
    }
    return found;
  }
  
  private void setEmployeeList(){
    employeeList.clear();
    for (EmployeeDTO employee : employeeListFull) {
      if((employee.getEmployeeId() == SELECT_ONE_INDEX ||
         (selectedDepartment.getDepartmentId() == SELECT_ONE_INDEX || 
              (employee.getDepartment() == null && selectedDepartment.getDepartmentId() == HAS_NONE_INDEX) || 
              (employee.getDepartment() != null && employee.getDepartment().getDepartmentId() == selectedDepartment.getDepartmentId())) &&
         (selectedJob.getJobId().equals(SELECT_ONE_ID) || 
              employee.getJob() != null && employee.getJob().getJobId().equals(selectedJob.getJobId())) &&
         (selectedManager.getEmployeeId() == SELECT_ONE_INDEX || 
              (selectedManager.getEmployeeId()== HAS_NONE_INDEX && employee.getManager() == null) || 
              (employee.getManager() != null && employee.getManager().getEmployeeId().equals(selectedManager.getEmployeeId()))))){
        employeeList.add(employee);
      }
    }
  }

  public void setSelectedDepartmentIndex(int selectedDepartmentIndex) throws IndexOutOfBoundsException {
    if (selectedDepartmentIndex < 0 || selectedDepartmentIndex > departmentList.size())
      throw new IndexOutOfBoundsException();
    selectedDepartment = departmentList.get(selectedDepartmentIndex);
    updateLists();
    int index = 0;
    for (int i = 1; i< departmentList.size() && index == 0; i++) {
      if(selectedDepartment.getDepartmentId() == departmentList.get(i).getDepartmentId()){
        index = i;
      }
    }
    selectedDepartment = departmentList.get(index);
    setEmployeeList();
  }
  
  public void setSelectedJobIndex(int selectedJobIdex) throws IndexOutOfBoundsException{
    if (selectedJobIdex < 0 || selectedJobIdex > jobList.size())
      throw new IndexOutOfBoundsException();
    selectedJob = jobList.get(selectedJobIdex);
    updateLists();
    int index = 0;
    for (int i = 1; i< jobList.size() && index == 0; i++) {
      if(selectedJob.getJobId().equals(jobList.get(i).getJobId())){
        index = i;
      }
    }
    selectedJob = jobList.get(index);
    setEmployeeList();
  }

  public void setSelectedManagerIndex(int selectedManagerIndex) throws IndexOutOfBoundsException{
    if (selectedManagerIndex < 0 || selectedManagerIndex > managerList.size())
      throw new IndexOutOfBoundsException();
    selectedManager = managerList.get(selectedManagerIndex);
    long selectedManagerId = selectedManager.getEmployeeId();
    updateLists();
    int index = 0;
    for (int i = 1; i< managerList.size() && index == 0; i++) {
      long managerId = managerList.get(i).getEmployeeId();
      if((long)selectedManager.getEmployeeId() == (long)managerList.get(i).getEmployeeId()){
        index = i;
      }
    }
    selectedManager = managerList.get(index);
    setEmployeeList();
  }
    
  public int getDepartmentInitialWageCost(){
    int sumOfSalary = 0;
    if(selectedDepartment.getDepartmentId() == -2){
      for (EmployeeDTO employee : initialEmployeeList) {
        if (employee.getDepartment() == null){
          sumOfSalary += employee.getSalary();
        }
      }
    }
    else{
      for (EmployeeDTO employee : initialEmployeeList) {
        DepartmentDTO department = employee.getDepartment();
        if(department != null && 
                selectedDepartment.getDepartmentId() == department.getDepartmentId()){
          sumOfSalary += employee.getSalary();
        }
      }
    }
    return sumOfSalary;
  }
  
  public int getDepartmentWageCost(){
    int sumOfSalary = 0;
    if(selectedDepartment.getDepartmentId() == -2){
      for (EmployeeDTO employee : employeeListFull) {
        if (employee.getDepartment() == null){
          sumOfSalary += employee.getSalary();
        }
      }
    }
    else{
      for (EmployeeDTO employee : employeeListFull) {
        DepartmentDTO department = employee.getDepartment();
        if(department != null && 
                selectedDepartment.getDepartmentId() == department.getDepartmentId()){
          sumOfSalary += employee.getSalary();
        }
      }
    }
    return sumOfSalary;
  }
  
  public ArrayList<String> getShownEmployeeList(){
    ArrayList<String> list = new ArrayList<>();
    list.add("select an empoyee");
    for (int i = 1; i < employeeList.size(); i++) {
      EmployeeDTO employee = employeeList.get(i);
      list.add(employee.getLastName() + ", " + employee.getFirstName() + " (id: " + employee.getEmployeeId() + ")");
    }
    return list;
  }
  
  public ArrayList<String> getShownJobList(){
    ArrayList<String> list = new ArrayList<>();
    int[] counts = new int[jobList.size()];
    for (int i = 0; i < counts.length; i++) {
      counts[i] = 0;
    }
    for (int i = 1; i < employeeListFull.size(); i++) {
      EmployeeDTO employee = employeeListFull.get(i);
      if((selectedManager.getEmployeeId() == SELECT_ONE_INDEX || employee.getManager() == null && selectedManager.getEmployeeId() == HAS_NONE_INDEX ||
              employee.getManager() != null && employee.getManager().getEmployeeId().equals(selectedManager.getEmployeeId())) &&
       (selectedDepartment.getDepartmentId() == SELECT_ONE_INDEX || 
              employee.getDepartment() == null && selectedDepartment.getDepartmentId() == HAS_NONE_INDEX ||
              employee.getDepartment() != null && employee.getDepartment().getDepartmentId() == selectedDepartment.getDepartmentId())){
        counts[0]++;
        String employeeJobId = employee == null ? null : employee.getJob() == null ? null : employee.getJob().getJobId();
        for (int j = 1; j < jobList.size(); j++) {
          if(jobList.get(j).getJobId().equals(employeeJobId)){
            counts[j]++;
          }
        }
      }
    }
    list.add("all (" + counts[0] + ")");
    for (int i = 1; i < jobList.size(); i++) {
      list.add(jobList.get(i).getJobTitle() + "(" + counts[i] + ")");
    }
    return list;
  }
  
  public ArrayList<String> getShownDepartmentList(){
    ArrayList<String> list = new ArrayList<>();
    int[] counts = new int[departmentList.size()];
    for (int i = 0; i < counts.length; i++) {
      counts[i] = 0;
    }
    for (int i = 1; i < employeeListFull.size(); i++) {
      EmployeeDTO employee = employeeListFull.get(i);
      if((selectedManager.getEmployeeId() == SELECT_ONE_INDEX || employee.getManager() == null && selectedManager.getEmployeeId() == HAS_NONE_INDEX ||
              employee.getManager()!= null && employee.getManager().getEmployeeId().equals(selectedManager.getEmployeeId())) &&
         (selectedJob.getJobId().equals(SELECT_ONE_ID) || employee.getJob().getJobId().equals(selectedJob.getJobId()))){
        counts[0]++;
        for (int j = 1; j < departmentList.size()-1; j++) {
          if(employee.getDepartment()== null && j == departmentList.size() - 1){
            counts[departmentList.size()-1]++;
          }
          else if( employee.getDepartment()!= null && employee.getDepartment().getDepartmentId() ==  departmentList.get(j).getDepartmentId()){
            counts[j]++;
          }
        }
      }
    }
    list.add("all (" + counts[0] + ")");
    for (int i = 1; i < departmentList.size()-1; i++) {
      list.add(departmentList.get(i).getDepartmentName() + "(" + counts[i] + ")");
    }
    list.add("- has no department - (" + counts[counts.length - 1] + ")");
    return list;
  }
  
  public ArrayList<String> getShownManagerList(){
    ArrayList<String> list = new ArrayList<>();
    int[] counts = new int[managerList.size()];
    for (int i = 0; i < counts.length; i++) {
      counts[i] = 0;
    }
    for (int i = 1; i < employeeListFull.size(); i++) {
      EmployeeDTO employee = employeeListFull.get(i);
      if((selectedJob.getJobId().equals(SELECT_ONE_ID) || selectedJob.getJobId().equals(employee.getJob().getJobId())) &&
         (selectedDepartment.getDepartmentId() == SELECT_ONE_INDEX || 
              employee.getDepartment() == null && selectedDepartment.getDepartmentId() == HAS_NONE_INDEX ||
              employee.getDepartment() != null && (employee.getDepartment().getDepartmentId() == selectedDepartment.getDepartmentId()))){
        counts[0]++;
        for (int j = 1; j < managerList.size(); j++) {
          if(employee.getManager() == null && j == managerList.size()-1){
            counts[managerList.size()-1]++;
          }
          else if(employee.getManager() != null && managerList.get(j).getEmployeeId().equals(employee.getManager().getEmployeeId())){
            counts[j]++;
          }
        }
      }
    }
    list.add("all (" + counts[0] + ")");
    for (int i = 1; i < managerList.size()-1; i++) {
      list.add(managerList.get(i).getLastName() + ", " + managerList.get(i).getFirstName() + "(" + counts[i] + ")");
    }
    list.add("- has no manager - (" + counts[managerList.size()-1] + ")");
    return list;
  }
  
  public int getSelectedEmployeeListIndex(){
    int index = 0;
    for (int i = 0; i < employeeList.size() && index == 0; i++) {
      if(employeeList.get(i).getEmployeeId().equals(selectedEmployee.getEmployeeId())){
        index = i;
      }
    }
    return index;
  }
  
  public int getSelectedDepartmentIndex(){
    int index = 0;
    long departmentId = selectedDepartment.getDepartmentId();
    for (int i = 0; i < departmentList.size() && index == 0; i++) {
      if(departmentList.get(i).getDepartmentId() == departmentId){
        index = i;
      }
    }
    return index;
  }
  
  public int getSelectedJobIndex(){
    int index = 0;
    for (int i = 0; i < jobList.size() && index == 0; i++) {
      if(jobList.get(i).getJobId().equals(selectedJob.getJobId())){
        index = i;
      }
    }
    return index;
  }
  
  public int getSelectedManagerIndex(){
    int index = 0;
    for (int i = 0; i < managerList.size() && index == 0; i++) {
      if(managerList.get(i).getEmployeeId().equals(selectedManager.getEmployeeId())){
        index = i;
      }
    }
    return index;
  }
  
  public int getSelectedEmployeeId(){
    int index = 0;
    for (int i = 0; i < employeeList.size() && index == 0; i++) {
      if(employeeList.get(i).getEmployeeId().equals(selectedEmployee.getEmployeeId())){
        index = i;
      }
    }
    return index == SELECT_ONE_INDEX ? 0 : (int)(long)employeeList.get(index).getEmployeeId();
  }
  
  public final void reset(){
    updateLists();
    selectedDepartment = departmentList.get(0);
    selectedJob = jobList.get(0);
    selectedManager = managerList.get(0);
    setEmployeeList();
    selectedEmployee = employeeList.get(0);
  }
  
  public void setController(SalaryUpdateController suc){
    if (suc == null)
      throw new NullPointerException();
    this.suc = suc;
  }
  
  public int getSelectedEmployeeSalary(){
    return (int)(long)selectedEmployee.getSalary();
  }
  
  public int getSelectedEmployeeInitialSalary(){
    int initialSalary = 0;
    for (int i = 0; i < initialEmployeeList.size(); i++) {
      if(selectedEmployee.getEmployeeId().equals(initialEmployeeList.get(i).getEmployeeId())){
        initialSalary = (int)(long)initialEmployeeList.get(i).getSalary();
      }
    }
    return initialSalary;
  }
}