package hu.bh.dzzt.enities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class UserEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
  private Long id;

  @Column(name = "USER_NAME", nullable = false, unique = true)
  private String userName;
  
  @Column(name = "USER_PWD", nullable = false)
  private String userPwd;
  
  @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID", nullable = false)
  @ManyToOne(optional = false)
  RoleEntity userRole;

  public UserEntity() {
    ;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public RoleEntity getUserRole() {
    return userRole;
  }

  public void setUserRole(RoleEntity userRole) {
    this.userRole = userRole;
  }

  public String getUserPwd() {
    return userPwd;
  }

  public void setUserPwd(String userPwd) {
    this.userPwd = userPwd;
  }
  
  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof UserEntity)) {
      return false;
    }
    UserEntity other = (UserEntity) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "UserEntity{" + "id=" + id + ", userName=" + userName + ", userPwd=" + userPwd + ", userRole=" + userRole + '}';
  }
  
}
