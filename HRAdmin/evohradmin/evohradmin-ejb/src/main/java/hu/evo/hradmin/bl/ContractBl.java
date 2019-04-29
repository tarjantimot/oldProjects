/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.evo.hradmin.bl;

import hu.evo.hradmin.dto.ContractDto;
import hu.evo.hradmin.model.Contract;

/**
 *
 * @author Erdahoy
 */
public interface ContractBl {
    
    ContractDto convertFromEntity(Contract contract);
    
    Contract convertFromDTO(ContractDto contract);
    
    ContractDto contractBuilder(Contract contact);
}
