/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.evo.hradmin.bl.impl;

import hu.evo.hradmin.dto.ContractDto;
import hu.evo.hradmin.dto.impl.ContractDtoImpl;
import hu.evo.hradmin.model.Contract;
import hu.evo.hradmin.model.Employee;
import hu.evo.hradmin.repository.impl.AbstractFacade;
import hu.evo.hradmin.repository.impl.ContractFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Erdahoy
 */

@Stateless
public class ContractBlImpl extends AbstractBlImpl<Contract> {

  @EJB
  private EmployeeBlImpl emplBl;
  
  @EJB
  private ContractFacade contractFacade;
  
  @Override
  protected AbstractFacade<Contract> getFacade() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  
  public ContractDto convertFromEntity(Contract contract) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  
  public Contract convertFromDTO(ContractDto contract) {
    Contract contractEntity = new Contract(contract.getId(), contract.getCreationDate(), contract.getExpireDate(), contract.getPath());
    return contractEntity;
  }

    public Contract convertFromDTO(ContractDto contract, Employee employee) {
    Contract contractEntity = new Contract();
    contractEntity.setCreationDate(contract.getCreationDate());
    contractEntity.setExpiryDate(contract.getExpireDate());
    contractEntity.setPath(contract.getPath());
    contractEntity.setEmployee(employee);
    if(contract.getId() == null) {
      contractFacade.create(contractEntity);
    } else {
      contractEntity.setId(contract.getId());
    }
    return contractEntity;
  }
  
  public ContractDto contractBuilder(Contract contract) {
    return new ContractDtoImpl.ContractDtoBuilder()
            .id(contract.getId())
            .creationDate(contract.getCreationDate())
            .expireDate(contract.getExpiryDate())
            .path(contract.getPath())
            .build();
  }
  
  
  public void create(ContractDto contactDto){
      Contract contact = convertFromDTO(contactDto);
      contractFacade.create(contact);
  }
  
  public void remove(ContractDto contractDto){
      Contract contract = convertFromDTO(contractDto);
      contractFacade.remove(contract);
  }
  
  public void update(ContractDto contractDto){
      System.out.println(contractDto.getId() + " update ID" );
      System.out.println(contractDto.getPath());
      Contract contract = contractFacade.find(contractDto.getId());
      contract.setCreationDate(contractDto.getCreationDate());
      contract.setExpiryDate(contractDto.getExpireDate());
      contract.setPath(contractDto.getPath());
      
//      Contract contract = convertFromDTO(contractDto);
      
//      contract.setEmployee(new Employee(contractDto.getEmployee().getId()));
      
      contractFacade.edit(contract);
  }
    
  public List<ContractDto> getAllContracts(){
    List<ContractDto> contracts = new ArrayList<>();
    for (Contract contract : contractFacade.findAll()) {
     contracts.add(contractBuilder(contract));
    }
    return contracts;
  }
  
  public ContractDto getContract(Integer id){
    return contractBuilder(contractFacade.find(id));
  }
}
