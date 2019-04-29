package hu.bh.dzzt.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "JOBS")
public class JobEntity implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Column(name = "JOB_ID")
  private String jobId;
  
  @Column(name = "JOB_TITLE")
  private String jobTitle;
  
  @Column(name = "MIN_SALARY")
  private Long minSalary;

  @Column(name = "MAX_SALARY")
  private Long maxSalary;  

  public JobEntity() {
    ;
  }

  public String getJobId() {
    return jobId;
  }

  public String getJobTitle() {
    return jobTitle;
  }

  public Long getMaxSalary() {
    return maxSalary;
  }

  public Long getMinSalary() {
    return minSalary;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (jobId != null ? jobId.hashCode() : 0);
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
    final JobEntity other = (JobEntity) obj;
    if (!Objects.equals(this.jobId, other.jobId)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "JobEntity{" + "jobId=" + jobId + ", jobTitle=" + jobTitle + ", minSalary=" + minSalary + ", maxSalary=" + maxSalary + '}';
  }
  
}