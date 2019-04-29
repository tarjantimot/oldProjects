/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.evo.hradmin.bl.impl;

import hu.evo.hradmin.dto.PdataDto;
import hu.evo.hradmin.dto.impl.PdataDtoImpl;
import hu.evo.hradmin.model.Employee;
import static hu.evo.hradmin.model.Employee_.pdata;
import hu.evo.hradmin.model.Pdata;
import hu.evo.hradmin.repository.impl.AbstractFacade;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Erdahoy
 */
@Stateless
public class PdataBlImpl extends AbstractBlImpl<Pdata>  {

  @EJB
  private EmployeeBlImpl emplBl;
  
  @Override
  protected AbstractFacade<Pdata> getFacade() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  
  public PdataDto convertFromEntity(Pdata pdata) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  
  public Pdata convertFromDTO(PdataDto pdata) {
    Pdata pdataEntity =  new Pdata(pdata.getId(), pdata.getTaxNum(), pdata.getIdentificationNum(),
            pdata.getInsuranceNum(), pdata.getPersonalNum(), pdata.getOther(), pdata.getOtherType());
//    pdataEntity.getEmployeeList().add(emplBl.convertFromDTO(empl));
    return pdataEntity;
  }

  
  public PdataDto pdataBuilder(Pdata pdata) {
    return new PdataDtoImpl.PdataDtoBuilder()
            .id(pdata.getId())
            .identificationNum(pdata.getIdentificationNum())
            .insuranceNum(pdata.getInsurranceNum())
            .taxNum(pdata.getTaxNum())
            .personalNum(pdata.getPersonalNum())
            .other(pdata.getOther())
            .otherType(pdata.getOtherType())
            .build();
  }
  
}
