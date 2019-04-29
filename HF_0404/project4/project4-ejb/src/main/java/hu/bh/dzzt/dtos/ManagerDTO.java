package hu.bh.dzzt.dtos;

import java.io.Serializable;
import java.util.Objects;

public class ManagerDTO implements Serializable {

  private long employeeId;
  private String firstName;
  private String lastName;
  private long managerId;  

  public ManagerDTO(Long employeeId, String firstName, String lastName, Long managerId) {
    this.employeeId = employeeId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.managerId = managerId == null ? -1L : managerId;
  }
  
  public long getEmployeeId() {
    return employeeId;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }
  
  public long getManagerId(){
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
    final ManagerDTO other = (ManagerDTO) obj;
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
