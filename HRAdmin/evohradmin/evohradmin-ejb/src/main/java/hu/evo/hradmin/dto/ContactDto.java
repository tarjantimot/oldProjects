/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.evo.hradmin.dto;

import java.io.Serializable;

/**
 *
 * @author Erdahoy
 */

public interface ContactDto extends Serializable {
  
  public Integer getId();
  
  public void setId(Integer id);
  
  public String getContactType();
  
  public void setContactType(String contactType);
  
  public String getContactValue();
  
  public void setContactValue(String contactValue);
  
  public EmployeeDto getEmployee();
  
  public void setEmployee(EmployeeDto employee);
}
