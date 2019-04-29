/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.evo.hradmin.dto.impl;

import hu.evo.hradmin.dto.AddressDto;
import hu.evo.hradmin.dto.EmployeeDto;

/**
 *
 * @author Erdahoy
 */
public class AddressDtoImpl implements AddressDto{
  private Integer id;
  private String country;
  private String postalCode;
  private String city;
  private String street;
  private String streetNumber;
  private Integer floor;
  private Integer door;
  private EmployeeDto employee;

  public AddressDtoImpl() {
  }

  
  
  private AddressDtoImpl(AddressDtoImplBuilder builder) {
    this.id = builder.id;
    this.country = builder.country;
    this.postalCode = builder.postalCode;
    this.city = builder.city;
    this.street = builder.street;
    this.streetNumber = builder.streetNumber;
    this.floor = builder.floor;
    this.door = builder.door;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getStreetNumber() {
    return streetNumber;
  }

  public void setStreetNumber(String streetNumber) {
    this.streetNumber = streetNumber;
  }

  public Integer getFloor() {
    return floor;
  }

  public void setFloor(Integer floor) {
    this.floor = floor;
  }

  public Integer getDoor() {
    return door;
  }

  public void setDoor(Integer door) {
    this.door = door;
  }

  public EmployeeDto getEmployee() {
    return employee;
  }

  public void setEmployee(EmployeeDto employee) {
    this.employee = employee;
  }
  
  
  
  public static class AddressDtoImplBuilder {
    private Integer id;
    private String country;
    private String postalCode;
    private String city;
    private String street;
    private String streetNumber;
    private Integer floor;
    private Integer door;
    
    public AddressDtoImplBuilder id(Integer id){
      this.id = id;
      return this;
    }
    
    public AddressDtoImplBuilder coutry(String country){
      this.country = country;
      return this;
    }
    
    public AddressDtoImplBuilder postalCode(String postalCode){
      this.postalCode = postalCode;
      return this;
    }
    
    public AddressDtoImplBuilder city(String city){
      this.city = city;
      return this;
    }
    
    public AddressDtoImplBuilder street(String street){
      this.street = street;
      return this;
    }
    
    public AddressDtoImplBuilder streetNumber(String streetNumber){
      this.streetNumber = streetNumber;
      return this;
    }
    
    public AddressDtoImplBuilder floor(Integer floor){
      this.floor = floor;
      return this;
    }
    
    public AddressDtoImplBuilder door(Integer door){
      this.door = door;
      return this;
    }
    
    public AddressDtoImpl build(){
      return new AddressDtoImpl(this);
    } 
  }
  
}
