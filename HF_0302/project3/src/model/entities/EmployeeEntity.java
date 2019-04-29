package model.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

@Entity
@Table(name = "EMPLOYEES")
public class EmployeeEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test")
  @Column(name = "EMPLOYEE_ID")
  private long employeeId;
  
  @Column(name = "FIRST_NAME")
  private String firstName;
  
  @Column(name = "LAST_NAME")
  private String lastName;
  
  @Column(name = "EMAIL")
  private String eMail;
  
  @Column(name = "PHONE_NUMBER")
  private String phoneNumber;
  
  @Column(name = "HIRE_DATE")
  private Date hireDate;
  
  @Column(name = "JOB_ID")
  private String jobId;
  
  @Column(name = "SALARY")
  private Long salary;
  
  @Column(name = "COMMISSION_PCT")
  private Float commission;
  
  @Column(name = "MANAGER_ID")
  private Long managerId;
  
  @Column(name = "DEPARTMENT_ID")
  private Long departmentId;
  
  public EmployeeEntity(){
    ;
  }

  public EmployeeEntity(String firstName, String lastName, String eMail, String phoneNumber, 
          String jobId, Long salary, Float comission, Long managerId, Long departmentId) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.eMail = eMail;
    this.phoneNumber = phoneNumber;
    this.jobId = jobId;
    this.salary = salary;
    this.commission = comission;
    this.managerId = managerId;
    this.departmentId = departmentId;
  }

  public Long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Long employeeId) {
    this.employeeId = employeeId;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String geteMail() {
    return eMail;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public Date getHireDate() {
    return hireDate;
  }

  public String getJobId() {
    return jobId;
  }

  public Long getSalary() {
    return salary;
  }

  public Float getComission() {
    return commission;
  }

  public Long getManagerId() {
    return managerId;
  }

  public Long getDepartmentId() {
    return departmentId;
  }

  public void setHireDate() {
    this.hireDate = new Date(System.currentTimeMillis());
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 53 * hash + Objects.hashCode(this.employeeId);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final EmployeeEntity other = (EmployeeEntity) obj;
    if (!Objects.equals(this.employeeId, other.employeeId)) {
      return false;
    }
    return true;
  }
  
}
