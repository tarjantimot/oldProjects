/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.evo.hradmin.dto.impl;

import hu.evo.hradmin.dto.AddressDto;
import hu.evo.hradmin.dto.ContactDto;
import hu.evo.hradmin.dto.ContractDto;
import hu.evo.hradmin.dto.EmployeeDto;
import hu.evo.hradmin.dto.JobDto;
import hu.evo.hradmin.dto.PdataDto;
import hu.evo.hradmin.dto.SalaryDto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Erdahoy
 */
public class EmployeeDtoImpl implements EmployeeDto{
  private Integer id;
  private String firstName;
  private String lastName;
  private String birthPlace;
  private Date dateOfBirth;
  private List<ContractDto> contractList;
  private List<ContactDto> contactList;
  private List<AddressDto> addressList;
  private List<SalaryDto> salaryList;
  private PdataDto pData;
  private String status;
  private Date statusModified;
  private JobDto job;

  private EmployeeDtoImpl(EmployeeDtoBuilder builder) {
    this.id = builder.id;
    this.firstName = builder.firstName;
    this.lastName = builder.lastName;
    this.birthPlace = builder.birthPlace;
    this.dateOfBirth = builder.dateOfBirth;
    this.contactList = builder.contactList;
    this.contractList = builder.contractList;
    this.addressList = builder.addressList;
    this.salaryList = builder.salaryList;
    this.pData = builder.pData;
    this.status = builder.status;
    this.statusModified = builder.statusModified;
    this.job = builder.job;
  }

  public EmployeeDtoImpl() {
      this.addressList = new ArrayList<>();
      this.contactList = new ArrayList<>();
      this.contractList = new ArrayList<>();
      this.salaryList = new ArrayList<>();
      this.job = new JobDtoImpl();
      this.pData = new PdataDtoImpl();
      
  }
  
  

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public List<ContractDto> getContractList() {
    return contractList;
  }

  public void setContractList(List<ContractDto> contractList) {
    this.contractList = contractList;
  }

  public List<ContactDto> getContactList() {
    return contactList;
  }

  public void setContactList(List<ContactDto> contactList) {
    this.contactList = contactList;
  }

  public String getBirthPlace() {
    return birthPlace;
  }

  public void setBirthPlace(String birthPlace) {
    this.birthPlace = birthPlace;
  }

  public PdataDto getpData() {
    return pData;
  }

  public void setpData(PdataDto pData) {
    this.pData = pData;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Date getStatusModified() {
    return statusModified;
  }

  public void setStatusModified(Date statusModified) {
    this.statusModified = statusModified;
  }

  public List<AddressDto> getAddressList() {
    return addressList;
  }

  public void setAddressList(List<AddressDto> addressList) {
    this.addressList = addressList;
  }

  public JobDto getJob() {
    return job;
  }

  public void setJob(JobDto job) {
    this.job = job;
  }

  
  
  public List<SalaryDto> getSalaryList() {
    return salaryList;
  }

  public void setSalaryList(List<SalaryDto> salaryList) {
    this.salaryList = salaryList;
  }

  public PdataDto getPdata() {
    return pData;
  }

  public void setPdata(PdataDto pData) {
    this.pData = pData;
  }

  public static class EmployeeDtoBuilder{
    private Integer id;
    private String firstName;
    private String lastName;
    private String birthPlace;
    private Date dateOfBirth;
    private List<ContactDto> contactList;
    private List<ContractDto> contractList;
    private List<AddressDto> addressList;
    private List<SalaryDto> salaryList;
    private PdataDto pData;
    private String status;
    private Date statusModified;
    private JobDto job;
    
    public EmployeeDtoBuilder id(int id){
      this.id = id;
      return this;
    }
    
    public EmployeeDtoBuilder firstName(String firstName){
      this.firstName = firstName;
      return this;
    }
    
    public EmployeeDtoBuilder lastName(String lastName){
      this.lastName = lastName;
      return this;
    }
    
    public EmployeeDtoBuilder dateOfBirth(Date dateOfBirth){
      this.dateOfBirth = dateOfBirth;
      return this;
    }
    
    public EmployeeDtoBuilder contactList(List<ContactDto> contactList){
      this.contactList = contactList;
      return this;
    }
    
    public EmployeeDtoBuilder contractList(List<ContractDto> contractList){
      this.contractList = contractList;
      return this;
    }
    
    public EmployeeDtoBuilder salaryDtos(List<SalaryDto> salaryList){
      this.salaryList = salaryList;
      return this;
    }
    
    public EmployeeDtoBuilder pData(PdataDto pData){
      this.pData = pData;
      return this;
    }
    
    public EmployeeDtoBuilder birthPlace(String birthPlace){
      this.birthPlace = birthPlace;
      return this;
    }
    
    public EmployeeDtoBuilder status(String status){
      this.status = status;
      return this;
    }
    
    public EmployeeDtoBuilder statusModified(Date statusModified){
      this.statusModified = statusModified;
      return this;
    }
    
    public EmployeeDtoBuilder addressList(List<AddressDto> addressList){
      this.addressList = addressList;
      return this;
    }
    
    public EmployeeDtoBuilder job(JobDto job){
      this.job = job;
      return this;
    }
    
    public EmployeeDto build(){
      return new EmployeeDtoImpl(this);
    }
  }

  
}
