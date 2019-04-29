/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.evo.hradmin.dto;

import java.io.Serializable;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 *
 * @author Erdahoy
 */
@JsonDeserialize(as = JobDto.class)
public interface JobDto extends Serializable {
  
    public Integer getId();

    public void setId(Integer id);

    public String getJobTitle();

    public void setJobTitle(String jobTitle);

    public String getJobDescription();

    public void setJobDescription(String jobDescription);

    public String getPath();

    public void setPath(String path);

    public Double getMinSalary();

    public void setMinSalary(Double minSalary);

    public Double getMaxSalary();

    public void setMaxSalary(Double maxSalary);

    public Integer getWorkerNum();

    public void setWorkerNum(Integer workerNum);

    public String getLocation();

    public void setLocation(String location);

    public String getCurrency();

    public void setCurrency(String currency);

}
