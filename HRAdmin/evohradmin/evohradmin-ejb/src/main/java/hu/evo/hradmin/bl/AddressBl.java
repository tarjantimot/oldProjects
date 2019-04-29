/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.evo.hradmin.bl;

import hu.evo.hradmin.dto.AddressDto;
import hu.evo.hradmin.model.Address;

/**
 *
 * @author Erdahoy
 */
public interface AddressBl {
    AddressDto convertFromEntity(Address address);
    
    Address convertFromDTO(AddressDto address);
    
    AddressDto addressBuilder(Address address);
}
