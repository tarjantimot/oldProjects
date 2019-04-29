/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.evo.hradmin.dto.impl;

import hu.evo.hradmin.dto.ContactDto;
import hu.evo.hradmin.dto.EmployeeDto;

/**
 *
 * @author Erdahoy
 */
public class ContactDtoImpl implements ContactDto{
  private Integer id;
  private String contactType;
  private String contactValue;
  private EmployeeDto employee;

  public ContactDtoImpl() {
  }

  public ContactDtoImpl(ContactDtoBuilder builder) {
    this.id = builder.id;
    this.contactType = builder.contactType;
    this.contactValue = builder.contactValue;
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

  public String getContactType() {
    return contactType;
  }

  public void setContactType(String contactType) {
    this.contactType = contactType;
  }

  public String getContactValue() {
    return contactValue;
  }

  public void setContactValue(String contactValue) {
    this.contactValue = contactValue;
  }
  
  public static class ContactDtoBuilder {
    private Integer id;
    private String contactType;
    private String contactValue;

    public ContactDtoBuilder id(int id){
      this.id = id;
      return this;
    }
    
    public ContactDtoBuilder contactType(String contactType){
      this.contactType = contactType;
      return this;
    }
    
    public ContactDtoBuilder contactValue (String contactValue){
      this.contactValue = contactValue;
      return this;
    }
    public ContactDto build(){
      return new ContactDtoImpl(this);
    }
  }
}