package model.dtos;

import java.io.Serializable;
import java.util.Objects;
import model.entities.JobEntity;

public class JobDTO implements Serializable {

  private String jobId;
  private String jobTitle;
  private long minSalary;
  private long maxSalary;

  public JobDTO(String jobId, String jobTitle, Long minSalary, Long maxSalary) {
    this.jobId = jobId;
    this.jobTitle = jobTitle;
    this.minSalary = minSalary == 0 ? 0 : minSalary;
    this.maxSalary = maxSalary == 0 ? 0 : maxSalary;
  }

  public String getJobId() {
    return jobId;
  }

  public String getJobTitle() {
    return jobTitle;
  }

  public long getMaxSalary() {
    return maxSalary;
  }

  public long getMinSalary() {
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
    final JobDTO other = (JobDTO) obj;
    if (!Objects.equals(this.jobId, other.jobId)) {
      return false;
    }
    if (!Objects.equals(this.jobTitle, other.jobTitle)) {
      return false;
    }
    if (!Objects.equals(this.minSalary, other.minSalary)) {
      return false;
    }
    if (!Objects.equals(this.maxSalary, other.maxSalary)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "JobDTO{" + "jobId=" + jobId + ", jobTitle=" + jobTitle + ", minSalary=" + minSalary + ", maxSalary=" + maxSalary + '}';
  }


  
}