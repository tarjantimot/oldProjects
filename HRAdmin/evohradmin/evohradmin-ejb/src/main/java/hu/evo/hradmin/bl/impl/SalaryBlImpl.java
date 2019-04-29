/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.evo.hradmin.bl.impl;

import hu.evo.hradmin.bl.SalaryBl;
import hu.evo.hradmin.dto.EmployeeDto;
import hu.evo.hradmin.dto.SalaryDto;
import hu.evo.hradmin.dto.impl.SalaryDtoImpl;
import hu.evo.hradmin.model.Employee;
import hu.evo.hradmin.model.Salary;
import hu.evo.hradmin.repository.impl.AbstractFacade;
import hu.evo.hradmin.repository.impl.SalaryFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Erdahoy
 */
@Stateless
public class SalaryBlImpl extends AbstractBlImpl<Salary> {
  
  @EJB
  private SalaryFacade salaryFacade;

  @EJB
  private EmployeeBlImpl emplBl;
  
  @Override
  protected AbstractFacade<Salary> getFacade() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  
  public SalaryDto convertFromEntity(Salary salary) {
//    return New SalaryDto
return null;
  }

  
  public Salary convertFromDTO(SalaryDto salary, Employee employee) {
    Salary salaryEntity = new Salary();
    salaryEntity.setStartDate(salary.getStartDate());
    salaryEntity.setEndDate(salary.getEndDate());
    salaryEntity.setAmount(salary.getValue());
    salaryEntity.setEmployee(employee);
    if(salary.getId() == null) {
        salaryFacade.create(salaryEntity);
    } else {
        salaryEntity.setId(salary.getId());
    }
    return salaryEntity;
  }
  
    public Salary convertFromDTO(SalaryDto salary) {
    Salary salaryEntity = new Salary();
    salaryEntity.setId(salary.getId());
    salaryEntity.setStartDate(salary.getStartDate());
    salaryEntity.setEndDate(salary.getEndDate());
    salaryEntity.setAmount(salary.getValue());
//    salaryEntity.setEmployee(emplBl.convertFromDTO(emplBl.find(salary.getEmployee().getId());
    return salaryEntity;
  }

  
  public SalaryDto salaryBuilder(Salary salary) {
    SalaryDto salaryDto = new SalaryDtoImpl.SalaryDtoBuilder()
            .id(salary.getId())
            .startDate(salary.getStartDate())
            .endDate(salary.getEndDate())
            .value(salary.getAmount())
//            .employee(emplBl.employeeBuilder(salary.getEmployee()))
            .build();
    return salaryDto;
  }
  
  
    public void create(SalaryDto salDto){
    Salary salary = convertFromDTO(salDto);
//    salary.setEmployee(new Employee(salDto.getEmployee().getId()));
    salaryFacade.create(salary);
  }
  
    public void remove(SalaryDto salDto){
    Salary salary = convertFromDTO(salDto);
    salaryFacade.remove(salary);
  }
  
    public void update(SalaryDto salDto){
    Salary salary = convertFromDTO(salDto);
    salary.setEmployee(new Employee(salDto.getEmployee().getId()));
    salaryFacade.edit(salary);
  }
    
  public List<SalaryDto> getAllSalaries(){
    List<SalaryDto> salaries = new ArrayList<>();
    for (Salary salary : salaryFacade.findAll()) {
     salaries.add(salaryBuilder(salary));
    }
    return salaries;
  }
  
}
