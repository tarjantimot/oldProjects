/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.evo.hradmin.bl;

import hu.evo.hradmin.dto.EmployeeDto;
import hu.evo.hradmin.model.Employee;
import javax.ejb.Stateless;

/**
 *
 * @author Erdahoy
 */
public interface EmployeeBl {
    EmployeeDto convertFromEntity(Employee employee);
    
    Employee convertFromDTO(EmployeeDto emoloyee);
    
    EmployeeDto employeeBuilder(Employee employee);
}
