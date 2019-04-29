package model.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEES")
public class ManagerEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "EMPLOYEE_ID")
  private Long employeeId;
  
  @Column(name = "FIRST_NAME")
  private String firstName;
  
  @Column(name = "LAST_NAME")
  private String lastName;
  
  @Column(name = "MANAGER_ID")
  private Long managerId;  
  
  public Long getEmployeeId() {
    return employeeId;
  }
  
  public String getName(){
    return lastName + ", " + firstName;
  }
  
  public Long getManagerId(){
    return managerId;
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
    final ManagerEntity other = (ManagerEntity) obj;
    if (!Objects.equals(this.employeeId, other.employeeId)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "Manager{" + "employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName + ", managerId=" + managerId + '}';
  }
  
}
