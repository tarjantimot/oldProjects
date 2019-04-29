package hu.bh.utils;

public class EmployeeData {
  long id;
  String name;
  String department;
  String manager;
  String job;
  long minSalary;
  long maxSalary;
  long salary;
  boolean salaryOk;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getManager() {
    return manager;
  }

  public void setManager(String manager) {
    this.manager = manager;
  }

  public String getJob() {
    return job;
  }

  public void setJob(String job) {
    this.job = job;
  }

  public long getMinSalary() {
    return minSalary;
  }

  public void setMinSalary(long minSalary) {
    this.minSalary = minSalary;
  }

  public long getMaxSalary() {
    return maxSalary;
  }

  public void setMaxSalary(long maxSalary) {
    this.maxSalary = maxSalary;
  }

  public long getSalary() {
    return salary;
  }

  public void setSalary(long salary) {
    this.salary = salary;
  }

  public boolean isSalaryOk() {
    return salaryOk;
  }

  public void setSalaryOk(boolean salaryOk) {
    this.salaryOk = salaryOk;
  }
  
  
}
