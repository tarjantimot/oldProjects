/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.evo.hradmin.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author szotyi
 */
@Entity
@Table(name = "job")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Job.findAll", query = "SELECT j FROM Job j")
    , @NamedQuery(name = "Job.findById", query = "SELECT j FROM Job j WHERE j.id = :id")
    , @NamedQuery(name = "Job.findByJobTitle", query = "SELECT j FROM Job j WHERE j.jobTitle = :jobTitle")
    , @NamedQuery(name = "Job.findByJobDescription", query = "SELECT j FROM Job j WHERE j.jobDescription = :jobDescription")
    , @NamedQuery(name = "Job.findByPath", query = "SELECT j FROM Job j WHERE j.path = :path")
    , @NamedQuery(name = "Job.findByMinSalary", query = "SELECT j FROM Job j WHERE j.minSalary = :minSalary")
    , @NamedQuery(name = "Job.findByMaxSalary", query = "SELECT j FROM Job j WHERE j.maxSalary = :maxSalary")
    , @NamedQuery(name = "Job.findByWorkerNum", query = "SELECT j FROM Job j WHERE j.workerNum = :workerNum")})
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "job_title")
    private String jobTitle;
    
    @Size(max = 3000)
    @Column(name = "job_description")
    private String jobDescription;
    
    @Size(max = 255)
    @Column(name = "path")
    private String path;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "min_salary")
    private double minSalary;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "max_salary")
    private double maxSalary;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "worker_num")
    private int workerNum;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "job", fetch = FetchType.LAZY)
    private List<Employee> employeeList = new ArrayList<>();
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 85)
    @Column(name = "location")
    private String location;

    public Job() {
    }

    public Job(Integer id) {
        this.id = id;
    }

    public Job(Integer id, String jobTitle, double minSalary, double maxSalary, int workerNum, String location) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.workerNum = workerNum;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public double getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(double minSalary) {
        this.minSalary = minSalary;
    }

    public double getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(double maxSalary) {
        this.maxSalary = maxSalary;
    }

    public int getWorkerNum() {
        return workerNum;
    }

    public void setWorkerNum(int workerNum) {
        this.workerNum = workerNum;
    }

    @XmlTransient
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
    
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
        if (!(object instanceof Job)) {
            return false;
        }
        Job other = (Job) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hu.evo.hradmin.model.Job[ id=" + id + " ]";
    }
    
}
