package hu.bh.dzzt.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEES")
public class EmployeeEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLOYEES_SEQ")
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

  @JoinColumn(name = "JOB_ID", referencedColumnName = "JOB_ID", nullable = false)
  @ManyToOne(optional = false)   
  private JobEntity job;
  
  @Column(name = "SALARY")
  private Long salary;
  
  @Column(name = "COMMISSION_PCT")
  private Float commission;

  @JoinColumn(name = "MANAGER_ID", referencedColumnName = "EMPLOYEE_ID", nullable = true)
  @ManyToOne(optional = false)  
  private ManagerEntity manager;
  
  @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "DEPARTMENT_ID", nullable = false)
  @ManyToOne(optional = false)
  private DepartmentEntity department;
  
  public EmployeeEntity(){
    ;
  }

  public EmployeeEntity(String firstName, String lastName, String eMail, String phoneNumber, 
          JobEntity job, Long salary, Float comission, ManagerEntity manager, DepartmentEntity department) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.eMail = eMail;
    this.phoneNumber = phoneNumber;
    this.job = job;
    this.salary = salary;
    this.commission = comission;
    this.manager = manager;
    this.department = department;
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

  public JobEntity getJob() {
    return job;
  }

  public Long getSalary() {
    return salary;
  }

  public Float getCommission() {
    return commission;
  }

  public ManagerEntity getManager() {
    return manager;
  }

  public DepartmentEntity getDepartment() {
    return department;
  }

  public void setHireDate() {
    this.hireDate = new Date(System.currentTimeMillis());
  }

	public void setSalary(Long salary) {
		this.salary = salary;
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
