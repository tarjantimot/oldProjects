package model;

import controller.Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Model implements SQLInterface {
	
  private ArrayList<Employee> employeeList = new ArrayList<>();
	private ArrayList<String> managerList= new ArrayList<>();
	private ArrayList<String> jobList= new ArrayList<>();
	private ArrayList<String> departmentList= new ArrayList<>();
  private int selectedEmployeeIdex;
  private int selectedDepartmentIndex;
  private int selectedJobIndex;
  private int selectedManagerIndex;
  private ArrayList<Integer> listedEmployeeIndices;
  private Controller controller;
  private Logger logger;

  
	public Model() throws SQLException, ClassNotFoundException {
		logger = new Logger();
		updateLists();
    reset();
    //selectedEmployee = listedEmployeeIndices.get(10);
		//updateSalary(500);
	}
	
	public class Employee {
		private String name;
		private int employeeId;
		private int managerIndex;
		private int departmentIndex;
		private int jobIndex;
		private int salary;
    private int initialSalary;

		public Employee(String name, int employeeID, int managerIndex, int departmentInded, int jobIndex, int salary) {
			this.name = name;
			this.employeeId = employeeID;
			this.managerIndex = managerIndex;
			this.departmentIndex = departmentInded;
			this.jobIndex = jobIndex;
			this.salary = salary;
      this.initialSalary = salary;
		}

		public String getName() {
			return name;
		}

		public int getManagerIndex() {
			return managerIndex;
		}

		public int getDepartmentIndex() {
			return departmentIndex;
		}

		public int getJobIndex() {
			return jobIndex;
		}

		public int getSalary() {
			return salary;
		}

    public int getEmployeeId() {
      return employeeId;
    }

    public void setSalary(int salary) {
      this.salary = salary;
    }
    
    private int getInitialSalary() {
      return initialSalary;
    }
        
		@Override
		public String toString() {
			return "Employee{" + "name=" + name + ", employeeId=" + employeeId + ", managerIndex=" + managerIndex + ", departmentIndex=" + departmentIndex + ", jobIndex=" + jobIndex + ", salary=" + salary +", initialSalary=" + initialSalary + '}'+"\n";
		}
		
	}
  
  public Logger getLogger(){
    return logger;
  }
	
  private void updateLists () throws SQLException, ClassNotFoundException {
    Class.forName(DRIVER);
    Connection connection=DriverManager.getConnection(URL, USER_NAME, USER_PWD);

    ResultSet employeesSet = connection.createStatement().executeQuery(SQL_GET_EMPLOYEES);
    ResultSet jobSet = connection.createStatement().executeQuery(SQL_GET_JOBS);
    ResultSet departmentSet = connection.createStatement().executeQuery(SQL_GET_DEPARTMENTS);
    ResultSet managerSet = connection.createStatement().executeQuery(SQL_GET_MANAGERS);

    ArrayList<Integer> depId = new ArrayList<>();
    ArrayList<Integer> managerId = new ArrayList<>();
    while(jobSet.next()) {
      jobList.add(jobSet.getString("JOB_TITLE"));
    }
		//System.out.println(jobList);
    while(managerSet.next()) {
      managerList.add(managerSet.getString("MANAGER_NAME"));
      managerId.add(managerSet.getInt("MANAGER_ID"));
    }
		managerList.add("-      ");
    while(departmentSet.next()){
      departmentList.add(departmentSet.getString("DEPARTMENT_NAME"));
      depId.add(departmentSet.getInt("DEPARTMENT_ID"));
    }
		
    boolean everyoneHasDep = true;
		int i = 0;
		
    while(employeesSet.next()) {
      String actualName = employeesSet.getString("EMPLOYEE_NAME");
      String actualJobTitle = employeesSet.getString("JOB_TITLE");
      int actualDepId = employeesSet.getInt("DEPARTMENT_ID");
      int actualEmplId = employeesSet.getInt("EMPLOYEE_ID");
      int actualManId = employeesSet.getInt("MANAGER_ID");
      int salary = employeesSet.getInt("SALARY");
      int actualDepIndex;
      int actualManIndex;
      int actualJobIndex;
			//System.out.println(actualDepId);
			//System.out.println(actualManId);
			//System.out.println(actualEmplId);
      /**************DEPARTMENT Index*********************/
      i = 0;
      if (-1==actualDepId){
        everyoneHasDep=false;
        actualDepIndex=departmentList.size();
      }
      else {
        while (i < depId.size() && actualDepId!=depId.get(i))
          i++;
        if (i >= depId.size())
          throw new IllegalArgumentException();
        actualDepIndex = i;
      }
			//System.out.println(actualDepIndex);
      /****************MANAGER INDEX*********************/
      i=0;
			if (-1==actualManId) {
				actualManIndex=managerId.size();
			}
			else {
        while(i < managerId.size() && actualManId != managerId.get(i))
          i++;
        if (i >= managerId.size())
          throw new IllegalArgumentException();
        actualManIndex = i;
			}
      /********************JOB INDEX***********************/
      i=0;
      while(i <  jobList.size() && !jobList.get(i).equals(actualJobTitle))
        i++;
      if (i >= jobList.size())
        throw new IllegalArgumentException();
      actualJobIndex = i;
			
      employeeList.add(new Employee(actualName, actualEmplId, actualManIndex, actualDepIndex, actualJobIndex, salary));
    }
		
    if(!everyoneHasDep)
      departmentList.add("Not assigned yet");
  }
    
  public boolean updateSalary(int newSalary) throws ClassNotFoundException, SQLException {
    //int employeeIndex  = listedEmployeeIndices.get(employeeListIndex);
    //int employeeId = employeeList.get(employeeListIndex).getEmployeeId();
    if(selectedEmployeeIdex < 0 || selectedEmployeeIdex > employeeList.size())
      throw new IndexOutOfBoundsException();
    int employeeId = employeeList.get(selectedEmployeeIdex).getEmployeeId();
    Class.forName(DRIVER);
    boolean returnValue = false;
    try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PWD)) {
      String SqlUpdateCommand =
              new StringBuilder(SQL_UPDATE_SALARY_PART1).append(newSalary).append(SQL_UPDATE_SALARY_PART2).append(employeeId).toString();
      //System.out.println(SqlUpdateCommand);
      if (connection.createStatement().executeUpdate(SqlUpdateCommand) == 1){
        returnValue = true;
        employeeList.get(selectedEmployeeIdex).setSalary(newSalary);
      }
    }
    return returnValue;
  }

  public void setSelectedDepartmentIndex(int selectedDepartmentIndex) throws IndexOutOfBoundsException {
    if (selectedDepartmentIndex < 0 || selectedDepartmentIndex > departmentList.size())
      throw new IndexOutOfBoundsException();
    this.selectedDepartmentIndex = selectedDepartmentIndex - 1;
  }

  public void setSelectedEmployeeIndex(int listedEmployeeIndex) throws IndexOutOfBoundsException{
    if (listedEmployeeIndex < 0 || listedEmployeeIndex > listedEmployeeIndices.size())
      throw new IndexOutOfBoundsException();
    if(listedEmployeeIndex == 0){
      selectedEmployeeIdex = -1;
    }
    else{
      int employeeIndex = listedEmployeeIndices.get(listedEmployeeIndex - 1);
      if (employeeIndex < 0 || employeeIndex >= employeeList.size())
        throw new IndexOutOfBoundsException();
      this.selectedEmployeeIdex = employeeIndex;
      this.selectedDepartmentIndex = employeeList.get(employeeIndex).getDepartmentIndex();
      this.selectedJobIndex = employeeList.get(employeeIndex).getJobIndex();
      this.selectedManagerIndex = employeeList.get(employeeIndex).getManagerIndex();
    }
  }
  
  public boolean setSelectedEmployeeById(int employeeId){
    boolean found = false;
    Employee employee;
    for (int i = 0; i < employeeList.size() && !found; i++) {
      if((employee = employeeList.get(i)).getEmployeeId() == employeeId){
        this.selectedEmployeeIdex = i;
        this.selectedDepartmentIndex = employee.getDepartmentIndex();
        this.selectedJobIndex = employee.getJobIndex();
        this.selectedManagerIndex = employee.getManagerIndex();        
        found = true;
      }
    }
    return found;
  }

  public void setSelectedJobIndex(int selectedJobIdex) throws IndexOutOfBoundsException{
    if (selectedJobIdex < 0 || selectedJobIdex > jobList.size())
      throw new IndexOutOfBoundsException();
    this.selectedJobIndex = selectedJobIdex - 1;
  }

  public void setSelectedManagerIndex(int selectedManagerIndex) throws IndexOutOfBoundsException{
    if (selectedManagerIndex < 0 || selectedManagerIndex > managerList.size())
      throw new IndexOutOfBoundsException();
    this.selectedManagerIndex = selectedManagerIndex - 1;
  }
    
  public int getDepartmentInitialWageCost(){
    int sumOfInitialSalary = 0;
    if(selectedEmployeeIdex >= 0){
      int departmentIndex = employeeList.get(selectedEmployeeIdex).getDepartmentIndex();
      for (Employee employee : employeeList) {
        if(employee.getDepartmentIndex() == departmentIndex)
          sumOfInitialSalary += employee.getInitialSalary();
      }
    }
    return sumOfInitialSalary;
  }
  
  public int getDepartmentWageCost(){
    int sumOfSalary = 0;
    if(selectedEmployeeIdex >= 0){
      int departmentIndex = employeeList.get(selectedEmployeeIdex).getDepartmentIndex();
      for (Employee employee : employeeList) {
        if(employee.getDepartmentIndex() == departmentIndex)
          sumOfSalary += employee.getSalary();
      }
    }
    return sumOfSalary;
  }
  
  public ArrayList<String> getShownEmployeeList(){
    ArrayList<String> list = new ArrayList<>();
    list.add("select an employee");
    listedEmployeeIndices = new ArrayList<>();
    for (int i = 0; i < employeeList.size(); i++) {
      Employee employee = employeeList.get(i);
      if((selectedDepartmentIndex == -1 || employee.departmentIndex == selectedDepartmentIndex) &&
          (selectedJobIndex == -1 || employee.jobIndex == selectedJobIndex) &&
          (selectedManagerIndex == -1 || employee.managerIndex == selectedManagerIndex)){
        list.add(employee.name + " (ID: "+ employee.employeeId + ")");
        listedEmployeeIndices.add(i);
      }
    }
    return list;
  }
  
  public ArrayList<String> getShownJobList(){
    ArrayList<String> list = new ArrayList<>();
    int[] counts = new int[jobList.size()+1];
    for (int i = 0; i < counts.length; i++) {
      counts[i] = 0;
    }
    for (int i = 0; i < employeeList.size(); i++) {
      Employee employee = employeeList.get(i);
      if((selectedManagerIndex == -1 || employee.getManagerIndex() == selectedManagerIndex) &&
         (selectedDepartmentIndex == -1 || employee.getDepartmentIndex() == selectedDepartmentIndex)){
        counts[0]++;
        counts[employeeList.get(i).jobIndex + 1]++;
      }
    }
    list.add("all (" + counts[0] + ")");
    for (int i = 0; i < jobList.size(); i++) {
      list.add(jobList.get(i) + "(" + counts[i+1] + ")");
    }
    return list;
  }
  
  public ArrayList<String> getShownDepartmentList(){
    ArrayList<String> list = new ArrayList<>();
    int[] counts = new int[departmentList.size()+1];
    for (int i = 0; i < counts.length; i++) {
      counts[i] = 0;
    }
    for (int i = 0; i < employeeList.size(); i++) {
      Employee employee = employeeList.get(i);
      if((selectedManagerIndex == -1 || employee.getManagerIndex() == selectedManagerIndex) &&
         (selectedJobIndex == -1 || employee.getJobIndex() == selectedJobIndex)){
        counts[0]++;
        counts[employeeList.get(i).departmentIndex + 1]++;
      }
    }
    list.add("all (" + counts[0] + ")");
    for (int i = 0; i < departmentList.size(); i++) {
      list.add(departmentList.get(i) + "(" + counts[i+1] + ")");
    }
    return list;
  }
  
  public ArrayList<String> getShownManagerList(){
    ArrayList<String> list = new ArrayList<>();
    int[] counts = new int[managerList.size()+1];
    for (int i = 0; i < counts.length; i++) {
      counts[i] = 0;
    }
    for (int i = 0; i < employeeList.size(); i++) {
      Employee employee = employeeList.get(i);
      if((selectedJobIndex == -1 || employee.getJobIndex() == selectedJobIndex) &&
         (selectedDepartmentIndex == -1 || employee.getDepartmentIndex() == selectedDepartmentIndex)){
        counts[0]++;
        counts[employeeList.get(i).managerIndex + 1]++;
      }
    }
    list.add("all (" + counts[0] + ")");
    for (int i = 0; i < managerList.size(); i++) {
      list.add(managerList.get(i) + "(" + counts[i+1] + ")");
    }
    return list;
  }
  
  public int getSelectedEmployeeListIndex(){
    int index = -1;
    for (int i = 0; i < listedEmployeeIndices.size() && index == -1; i++) {
      if (listedEmployeeIndices.get(i) == selectedEmployeeIdex){
        index = i;
      }
    }
    return index + 1;
  }
  
  public int getSelectedDepartmentIndex(){
    return selectedDepartmentIndex + 1;
  }
  
  public int getSelectedJobIndex(){
    return selectedJobIndex + 1;
  }
  
  public int getSelectedManagerIndex(){
    return selectedManagerIndex + 1;
  }
  
  public int getSelectedEmployeeId(){
    return employeeList.get(selectedEmployeeIdex).getEmployeeId();
  }
  
  public void reset(){
    selectedEmployeeIdex = -1;
    selectedDepartmentIndex = -1;
    selectedJobIndex = -1;
    selectedManagerIndex = -1;
		listedEmployeeIndices = new ArrayList<>();
		for (int i = 0; i < employeeList.size(); i++) {
			listedEmployeeIndices.add(i);
		}
  }
  
  public void setController(Controller controller){
    if (controller == null)
      throw new NullPointerException();
    this.controller = controller;
  }
  
  public int getSelectedEmployeeSalary(){
    return employeeList.get(selectedEmployeeIdex).getSalary();
  }
  
  public int getSelectedEmployeeInitialSalary(){
    return employeeList.get(selectedEmployeeIdex).getInitialSalary();
  }
}