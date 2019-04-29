/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.evo.hradmin.bl;

import hu.evo.hradmin.dto.SalaryDto;
import hu.evo.hradmin.model.Salary;

/**
 *
 * @author Erdahoy
 */
public interface SalaryBl {
  SalaryDto convertFromEntity(Salary salary);
    
  Salary convertFromDTO(SalaryDto salary);
    
  SalaryDto salaryBuilder(Salary salary);
}
