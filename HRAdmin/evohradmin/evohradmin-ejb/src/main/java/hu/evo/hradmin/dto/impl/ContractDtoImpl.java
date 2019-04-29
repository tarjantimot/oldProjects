/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.evo.hradmin.dto.impl;

import hu.evo.hradmin.dto.ContractDto;
import hu.evo.hradmin.dto.EmployeeDto;
import java.util.Date;

/**
 *
 * @author Erdahoy
 */
public class ContractDtoImpl implements ContractDto{
  private Integer id;
  private Date creationDate;
  private Date expireDate;
  private String path;
  private EmployeeDto employee;

  private ContractDtoImpl(ContractDtoBuilder builder) {
    this.id = builder.id;
    this.creationDate = builder.creationDate;
    this.expireDate = builder.expireDate;
    this.path = builder.path;
  }

  public ContractDtoImpl() {
  }

  public EmployeeDto getEmployee() {
    return employee;
  }

  public void setEmployee(EmployeeDto employee) {
    this.employee = employee;
  }
  
  

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public Date getExpireDate() {
    return expireDate;
  }

  public void setExpireDate(Date expireDate) {
    this.expireDate = expireDate;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }
  
  public static class ContractDtoBuilder{
    private Integer id;
    private Date creationDate;
    private Date expireDate;
    private String path;
    
    public ContractDtoBuilder id(Integer id){
      this.id = id;
      return this;
   }
    
    public ContractDtoBuilder creationDate(Date creationDate){
      this.creationDate = creationDate;
      return this;
    }
    
    public ContractDtoBuilder expireDate(Date expireDate){
      this.expireDate = expireDate;
      return this;
    }
    
    public ContractDtoBuilder path(String path){
      this.path = path;
      return this;
    }
    
    public ContractDtoImpl build(){
      return new ContractDtoImpl(this);
    }
  }
}
