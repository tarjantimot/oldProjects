/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.evo.hradmin.bl.impl;

import hu.evo.hradmin.dto.AddressDto;
import hu.evo.hradmin.dto.ContactDto;
import hu.evo.hradmin.dto.ContractDto;
import hu.evo.hradmin.dto.EmployeeDto;
import hu.evo.hradmin.dto.SalaryDto;
import hu.evo.hradmin.dto.impl.EmployeeDtoImpl;
import hu.evo.hradmin.model.Address;
import hu.evo.hradmin.model.Contact;
import hu.evo.hradmin.model.Contract;
import hu.evo.hradmin.model.Employee;
import hu.evo.hradmin.model.Salary;
import hu.evo.hradmin.repository.impl.AbstractFacade;
import hu.evo.hradmin.repository.impl.EmployeeFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Erdahoy
 */

@Stateless
public class EmployeeBlImpl extends AbstractBlImpl<Employee> {

  @EJB
  private PdataBlImpl pdataBlImpl;

  @EJB
  private SalaryBlImpl salaryBlImpl;

  @EJB
  private ContactBlImpl contactBlImpl;

  @EJB
  private ContractBlImpl contractBlImpl;
  
  @EJB
  private EmployeeFacade emplFacade;
  
  @EJB
  private JobBlImpl jobBlImpl;
  
  @EJB
  private AddressBlImpl addressBlImpl;
  
  public List<EmployeeDto> getAllEmployees(){
    List<EmployeeDto> employees = new ArrayList<>();
    for (Employee employee : emplFacade.findAll()) {
      employees.add(employeeBuilder(employee));
    }
    return employees;
  }
  
  public Employee convertFromDTO(EmployeeDto employee) {

    Employee employeeEntity = new Employee();
    employeeEntity.setId(employee.getId());
    
    List<Contact> contactList = new ArrayList<>();
    for (ContactDto contactDto : employee.getContactList()) {
      contactList.add(contactBlImpl.convertFromDTO(contactDto, employeeEntity));
    }
    
    List<Contract> contractList = new ArrayList<>();
    for (ContractDto contractDto : employee.getContractList()) {
      contractList.add(contractBlImpl.convertFromDTO(contractDto, employeeEntity));
    }
    
    List<Address> addressList = new ArrayList<>();
    for (AddressDto addressDto : employee.getAddressList()) {
      addressList.add(addressBlImpl.convertFromDTO(addressDto, employeeEntity));
   }
    
    List<Salary> salaryList = new ArrayList<>();
    for (SalaryDto salaryDto : employee.getSalaryList()) {
    salaryList.add(salaryBlImpl.convertFromDTO(salaryDto, employeeEntity));
    }
    
    employeeEntity.setFirstName(employee.getFirstName());
    employeeEntity.setLastName(employee.getLastName());
    employeeEntity.setBirthPlace(employee.getBirthPlace());
    employeeEntity.setBirthDate(employee.getDateOfBirth());
    employeeEntity.setStatus(employee.getStatus());
    employeeEntity.setStatusLastModified(employee.getStatusModified());
    employeeEntity.setPdata(pdataBlImpl.convertFromDTO(employee.getPdata()));
    employeeEntity.setJob(jobBlImpl.getJobEntity(employee.getJob().getId()));
    employeeEntity.setContactList(contactList);
    employeeEntity.setContractList(contractList);
    employeeEntity.setAddressList(addressList);
    employeeEntity.setSalaryList(salaryList);
    
    return employeeEntity;
  }

 
  public EmployeeDto employeeBuilder(Employee employee) {
    
    List<SalaryDto> salarys = new ArrayList<>();
      for (Salary salary : employee.getSalaryList()) {
        salarys.add(salaryBlImpl.salaryBuilder(salary));
    }
    List<ContactDto> contacts = new ArrayList<>();
    for (Contact contact : employee.getContactList()) {
      contacts.add(contactBlImpl.contactBuilder(contact));
    }
    
    List<ContractDto> contracts = new ArrayList<>();
    for (Contract contract : employee.getContractList()) {
      contracts.add(contractBlImpl.contractBuilder(contract));
    }
    
    List<AddressDto> addressList = new ArrayList<>();
    for (Address address : employee.getAddressList()) {
      addressList.add(addressBlImpl.addressBuilder(address));
    }
    
    
    return new EmployeeDtoImpl.EmployeeDtoBuilder()
                              .id(employee.getId())
                              .firstName(employee.getFirstName())
                              .lastName(employee.getLastName())
                              .birthPlace(employee.getBirthPlace())
                              .dateOfBirth(employee.getBirthDate())
                              .contactList(contacts)
                              .contractList(contracts)
                              .salaryDtos(salarys)
                              .pData(pdataBlImpl.pdataBuilder(employee.getPdata()))
                              .status(employee.getStatus())
                              .statusModified(employee.getStatusLastModified())
                              .job(jobBlImpl.convertFromEntity(employee.getJob()))
                              .addressList(addressList)
                              .build();
                              
  }
  
  public void create(EmployeeDto emplDto){
    Employee employee = convertFromDTO(emplDto);
    emplFacade.create(employee);
  }
  
  public void remove(EmployeeDto emplDto){
    for (int i = 0; i < emplDto.getContactList().size(); i++) { 
      contactBlImpl.remove(emplDto.getContactList().get(i));
    }
    for (int i = 0; i < emplDto.getContractList().size(); i++) { 
      contractBlImpl.remove(emplDto.getContractList().get(i));
    }
    for (int i = 0; i < emplDto.getAddressList().size(); i++) { 
      addressBlImpl.remove(emplDto.getAddressList().get(i));
    }
    for (int i = 0; i < emplDto.getSalaryList().size(); i++) { 
      salaryBlImpl.remove(emplDto.getSalaryList().get(i));
    }
    Employee employee = convertFromDTO(emplDto);
    emplFacade.remove(employee);
  }
  
  public void update(EmployeeDto emplDto){
    Employee employee = convertFromDTO(emplDto);
    emplFacade.edit(employee);
  }
  
  public EmployeeDto find(Integer id){
    EmployeeDto emplDto = employeeBuilder(emplFacade.find(id));
    return emplDto;
  }

  @Override
  protected AbstractFacade<Employee> getFacade() {
    return emplFacade;
  }


  
}
