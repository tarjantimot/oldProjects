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
public interface PdataDto extends Serializable {
  
  public Integer getId();
  
  public void setId(Integer id);
  
  public String getTaxNum();
  
  public void setTaxNum(String taxNum);
  
  public String getIdentificationNum();
  
  public void setIdentificationNum(String identificationNum);
  
  public String getInsuranceNum();
  
  public void setInsuranceNum(String insuranceNum);
  
  public String getPersonalNum();
  
  public void setPersonalNum(String personalNum);
  
  public String getOther();
  
  public void setOther(String other);
  
  public String getOtherType();
  
  public void setOtherType(String otherType);
}
