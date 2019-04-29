/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.evo.hradmin.dto.impl;

import hu.evo.hradmin.dto.JobDto;

/**
 *
 * @author szotyi
 */
public class JobDtoImpl implements JobDto {
    
    private Integer id;
    private String jobTitle;
    private String jobDescription;
    private String path;
    private Double minSalary;
    private Double maxSalary;
    private Integer workerNum;      
    private String location;
    private String currency = "HUF";
    
    public JobDtoImpl(JobDTOBuilder builder){
        this.id = builder.id;
        this.jobTitle = builder.jobTitle;
        this.jobDescription = builder.jobDescription;
        this.path = builder.path;
        this.minSalary = builder.minSalary;
        this.maxSalary = builder.maxSalary;
        this.workerNum = builder.workerNum;
        this.location = builder.location;
    }

    public JobDtoImpl() {
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

    public Double getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Double minSalary) {
        this.minSalary = minSalary;
    }

    public Double getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Double maxSalary) {
        this.maxSalary = maxSalary;
    }

    public Integer getWorkerNum() {
        return workerNum;
    }

    public void setWorkerNum(Integer workerNum) {
        this.workerNum = workerNum;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    public static class JobDTOBuilder {
        private Integer id;
        private String jobTitle;
        private String jobDescription;
        private String path;
        private Double minSalary;
        private Double maxSalary;
        private Integer workerNum;
        private String location;
        private String currency;
    
        public JobDTOBuilder id(Integer id){
            this.id = id;
            return this;
        }
        
        public JobDTOBuilder jobTitle(String jobTitle){
            this.jobTitle = jobTitle;
            return this;
        }
        
        public JobDTOBuilder jobDescription(String jobDescription){
            this.jobDescription = jobDescription;
            return this;
        }
        
        public JobDTOBuilder path(String path){
            this.path = path;
            return this;
        }
        
        public JobDTOBuilder minSalary(Double minSalary){
            this.minSalary = minSalary;
            return this;
        }
        
        public JobDTOBuilder maxSalary(Double maxSalary){
            this.maxSalary = maxSalary;
            return this;
        }
        
        public JobDTOBuilder workerNum(Integer workerNum){
            this.workerNum = workerNum;
            return this;
        }
        
        public JobDTOBuilder location(String location){
            this.location = location;
            return this;
        }
        
        public JobDTOBuilder currency(String currency){
            this.currency = currency;
            return this;
        }           
        
        public JobDtoImpl build(){
            return new JobDtoImpl(this);
        }
 
    }
}
