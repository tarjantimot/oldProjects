package hu.bh.dzzt.dtos;

import java.io.Serializable;
import java.sql.Date;


public class EmployeeDTO implements Serializable {

  private long employeeId;
  private String firstName;
  private String lastName;
  private String eMail;
  private String phoneNumber;
  private Date hireDate;
  private JobDTO job;
  private long salary;
  private float commission;  
  private ManagerDTO manager;
  private DepartmentDTO department;
  
  public EmployeeDTO(){
    ;
  }

  public EmployeeDTO(Long employeeId, String firstName, String lastName,
          String eMail, String phoneNumber, JobDTO job, Long salary,
          Float commission, ManagerDTO manager, DepartmentDTO department) {
    this.employeeId = employeeId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.eMail = eMail;
    this.phoneNumber = phoneNumber;
    this.job = job;
    this.salary = salary == null ? 0L : salary;
    this.commission = commission == null ? 0.0F : commission;
    this.manager = manager;
    this.department = department;
  }

  public long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Long employeeId) {
    this.employeeId = employeeId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String geteMail() {
    return eMail;
  }

  public void seteMail(String eMail) {
    this.eMail = eMail;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Date getHireDate() {
    return hireDate;
  }

  public void setHireDate(Date hireDate) {
    this.hireDate = hireDate;
  }

  public JobDTO getJob() {
    return job;
  }

  public Long getSalary() {
    return salary;
  }

  public Float getComission() {
    return commission;
  }

  public ManagerDTO getManager() {
    return manager;
  }

  public DepartmentDTO getDepartment() {
    return department;
  }

  public void setDepartment(DepartmentDTO department) {
    this.department = department;
  }

  public void setHireDate() {
    this.hireDate = new Date(System.currentTimeMillis());
  }

  @Override
  public String toString() {
    return "EmployeeDTO{" + "employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName + ", eMail=" + eMail + ", phoneNumber=" + phoneNumber + ", hireDate=" + hireDate + ", job=" + job + ", salary=" + salary + ", commission=" + commission + ", manager=" + manager + ", department=" + department + '}';
  }
  
}
