/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.evo.hradmin.bl;

import hu.evo.hradmin.dto.ContactDto;
import hu.evo.hradmin.model.Contact;

/**
 *
 * @author Erdahoy
 */
public interface ContactBl {
  
    ContactDto convertFromEntity(Contact contact);
    
    Contact convertFromDTO(ContactDto contact);
    
    ContactDto contactBuilder(Contact contact);
}
