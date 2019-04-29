/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.evo.hradmin.dto.impl;

import hu.evo.hradmin.dto.EmployeeDto;
import hu.evo.hradmin.dto.SalaryDto;
import java.util.Date;

/**
 *
 * @author Erdahoy
 */
public class SalaryDtoImpl implements SalaryDto{

  private Integer id;
  private Date startDate;
  private Date endDate;
  private double value;
  private EmployeeDto employee;
  
  
  public SalaryDtoImpl() {
  }

  public SalaryDtoImpl(SalaryDtoBuilder builder) {
    this.id = builder.id;
    this.startDate = builder.startDate;
    this.endDate = builder.endDate;
    this.value = builder.value;
  }

  
  
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }

  public EmployeeDto getEmployee() {
    return employee;
  }

  public void setEmployee(EmployeeDto employee) {
    this.employee = employee;
  }
  
  
  public static class SalaryDtoBuilder{
    private Integer id;
    private Date startDate;
    private Date endDate;
    private double value;
    
    
    public SalaryDtoBuilder id(Integer id){
      this.id = id;
      return this;
    }
    
    public SalaryDtoBuilder startDate(Date startDate){
      this.startDate = startDate;
      return this;
    }
    
    public SalaryDtoBuilder endDate(Date endDate){
      this.endDate = endDate;
      return this;
    }
    
    public SalaryDtoBuilder value(double value){
      this.value = value;
      return this;
    }
    
    public SalaryDtoImpl build(){
      return new SalaryDtoImpl(this);
    }
  }
  
}
