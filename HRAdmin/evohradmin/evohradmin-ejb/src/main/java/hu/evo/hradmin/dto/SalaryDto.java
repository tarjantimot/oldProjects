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
public interface SalaryDto extends Serializable {
  public Integer getId();
  
  public void setId (Integer id);
  
  public Date getStartDate();
  
  public void setStartDate(Date startDate);
  
  public Date getEndDate();
  
  public void setEndDate(Date endDate);
  
  public double getValue();
  
  public void setValue(double amount);
  
  public EmployeeDto getEmployee();
  
  public void setEmployee(EmployeeDto employee);
}
