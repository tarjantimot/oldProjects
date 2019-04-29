package hu.bh.dzzt.dtos;


import java.io.Serializable;
import java.util.Objects;

public class DepartmentDTO implements Serializable {
  
  private long departmentId;
  private String departmentName;
  private ManagerDTO manager;

  public DepartmentDTO(long departmentId, String departmentName, ManagerDTO manager) {
    this.departmentId = departmentId;
    this.departmentName = departmentName;
    this.manager = manager;
  }

  public long getDepartmentId() {
    return departmentId;
  }

  public String getDepartmentName() {
    return departmentName;
  }
  
  public ManagerDTO getManager() {
    return manager;
  }

  public long getManagerId(){
    return manager == null ? -1L : manager.getEmployeeId();
  }
  
  @Override
  public int hashCode() {
    int hash = 3;
    hash = 59 * hash + Objects.hashCode(this.departmentId);
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
    final DepartmentDTO other = (DepartmentDTO) obj;
    if (!Objects.equals(this.departmentName, other.departmentName)) {
      return false;
    }
    if (!Objects.equals(this.departmentId, other.departmentId)) {
      return false;
    }
    if (!Objects.equals(this.manager, other.manager)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "Department{" + "departmentId=" + departmentId + ", departmentName=" + departmentName + ", managerId=" + manager + '}';
  }

}
