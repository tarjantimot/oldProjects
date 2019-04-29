/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.evo.hradmin.dto.impl;

import hu.evo.hradmin.dto.PdataDto;

/**
 *
 * @author Erdahoy
 */
public class PdataDtoImpl implements PdataDto {
  private Integer id;
  private String taxNum;
  private String identificationNum;
  private String insuranceNum;
  private String personalNum;
  private String other;
  private String otherType;

  private PdataDtoImpl(PdataDtoBuilder builder) {
    this.id = builder.id;
    this.taxNum = builder.taxNum;
    this.identificationNum = builder.identificationNum;
    this.insuranceNum = builder.insuranceNum;
    this.personalNum = builder.insuranceNum;
    this.other = builder.other;
    this.otherType = builder.otherType;
  }

    public PdataDtoImpl() {
    }
  
  

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTaxNum() {
    return taxNum;
  }

  public void setTaxNum(String taxNum) {
    this.taxNum = taxNum;
  }

  public String getIdentificationNum() {
    return identificationNum;
  }

  public void setIdentificationNum(String identificationNum) {
    this.identificationNum = identificationNum;
  }

  public String getInsuranceNum() {
    return insuranceNum;
  }

  public void setInsuranceNum(String insuranceNum) {
    this.insuranceNum = insuranceNum;
  }

  public String getPersonalNum() {
    return personalNum;
  }

  public void setPersonalNum(String personalNum) {
    this.personalNum = personalNum;
  }

  public String getOther() {
    return other;
  }

  public void setOther(String other) {
    this.other = other;
  }

  public String getOtherType() {
    return otherType;
  }

  public void setOtherType(String otherType) {
    this.otherType = otherType;
  }
  
    public static class PdataDtoBuilder {
      private Integer id;
      private String taxNum;
      private String identificationNum;
      private String insuranceNum;
      private String personalNum;
      private String other;
      private String otherType;

    public PdataDtoBuilder id(Integer id){
      this.id = id;
      return this;
    }
    
    public PdataDtoBuilder taxNum(String taxNum){
      this.taxNum = taxNum;
      return this;
    }
    
    public PdataDtoBuilder identificationNum(String identificationNum){
      this.identificationNum = identificationNum;
      return this;
    }
    
    public PdataDtoBuilder insuranceNum(String insuranceNum) {
      this.insuranceNum = insuranceNum;
      return this;
    }
    
    public PdataDtoBuilder personalNum(String personalNum){
      this.personalNum = personalNum;
      return this; 
   }
    
    public PdataDtoBuilder other(String other){
      this.other = other;
      return this;
    }
    
    public PdataDtoBuilder otherType(String otherType){
      this.otherType = otherType;
      return this;
    }
    
    public PdataDtoImpl build(){
      return new PdataDtoImpl(this);
    }
  }
}
