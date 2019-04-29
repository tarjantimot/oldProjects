package hu.bh.dzzt.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DEPARTMENTS")
public class DepartmentEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "DEPARTMENT_ID")
  private Long departmentId;
  
  @Column(name = "DEPARTMENT_NAME")
  private String departmentName;

  @JoinColumn(name = "MANAGER_ID", referencedColumnName = "EMPLOYEE_ID", nullable = false)
  @OneToOne(optional = false)
  private ManagerEntity manager;

  public DepartmentEntity() {
  }
  public Long getDepartmentId() {
    return departmentId;
  }

  public String getDepartmentName() {
    return departmentName;
  }

  public ManagerEntity getManager() {
    return manager;
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
    final DepartmentEntity other = (DepartmentEntity) obj;
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
