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
public interface AddressDto extends Serializable{
  
  public Integer getId();
  
  public void setId(Integer id);
  
  public String getCountry();
  
  public void setCountry(String country);
  
  public String getPostalCode();
  
  public void setPostalCode(String postalCode);
  
  public String getCity();
  
  public void setCity(String city);
  
  public String getStreet();
  
  public void setStreet(String street);
  
  public String getStreetNumber();
  
  public void setStreetNumber(String street);
  
  public Integer getFloor();
  
  public void setFloor(Integer floor);
  
  public Integer getDoor();
  
  public void setDoor(Integer door);
  
  public EmployeeDto getEmployee();
  
  public void setEmployee (EmployeeDto employee);
}
