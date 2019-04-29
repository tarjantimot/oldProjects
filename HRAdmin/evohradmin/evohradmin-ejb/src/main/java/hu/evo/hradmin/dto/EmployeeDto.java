/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.evo.hradmin.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Erdahoy
 */
public interface EmployeeDto extends Serializable {
  
  public Integer getId();
  
  public void setId(Integer id);
  
  public String getFirstName();
  
  public void setFirstName(String firstName);
  
  public String getLastName();
  
  public void setLastName(String lastName);
  
  public String getBirthPlace();
  
  public void setBirthPlace(String birthPlace);
  
  public Date getDateOfBirth();
  
  public void setDateOfBirth(Date birthDate);
  
  public List<ContactDto> getContactList();
  
  public void setContactList(List<ContactDto> contactList);
  
  public List<ContractDto> getContractList();
  
  public void setContractList(List<ContractDto> contractList);
  
  public List<SalaryDto> getSalaryList();
  
  public void setSalaryList(List<SalaryDto> salary);
  
  public PdataDto getPdata();
  
  public void setPdata(PdataDto pData);
  
  public String getStatus();
  
  public void setStatus(String status);
  
  public Date getStatusModified();
  
  public void setStatusModified(Date statusModified);
  
  public JobDto getJob();
  
  public void setJob(JobDto job);
  
  public List<AddressDto> getAddressList();
  
  public void setAddressList(List<AddressDto> addressList);
  
}
