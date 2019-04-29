/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.evo.hradmin.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Erdahoy
 */
public interface ContractDto extends Serializable {
  public Integer getId();
  
  public void setId(Integer id);
  
  public Date getCreationDate();
  
  public void setCreationDate(Date creationDate);
  
  public Date getExpireDate();
  
  public void setExpireDate(Date expireDate);
  
  public String getPath();
  
  public void setPath(String path);
  
  public EmployeeDto getEmployee();
  
  public void setEmployee(EmployeeDto employee);
}
