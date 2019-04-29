/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.evo.hradmin.bl.impl;

import hu.evo.hradmin.dto.ContactDto;
import hu.evo.hradmin.dto.impl.ContactDtoImpl;
import hu.evo.hradmin.model.Contact;
import hu.evo.hradmin.model.Employee;
import hu.evo.hradmin.repository.impl.AbstractFacade;
import hu.evo.hradmin.repository.impl.ContactFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Erdahoy
 */

@Stateless
public class ContactBlImpl extends AbstractBlImpl<Contact> {

  @EJB
  EmployeeBlImpl emplBl;
  
  @EJB
  ContactFacade contactFacade;
  
  @Override
  protected AbstractFacade<Contact> getFacade() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  
  public ContactDto convertFromEntity(Contact contact) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  
  public Contact convertFromDTO(ContactDto contact) {
    Contact contactEntity = new Contact(contact.getId(), contact.getContactType(), contact.getContactValue());
//    contactEntity.setEmployee(emplBl.convertFromDTO(empl));
    return contactEntity;
  }

    public Contact convertFromDTO(ContactDto contact, Employee employee) {
    Contact contactEntity = new Contact();
    contactEntity.setContactType(contact.getContactType());
    contactEntity.setContactValue(contact.getContactValue());
    contactEntity.setEmployee(employee);
    if(contact.getId() == null){
      contactFacade.create(contactEntity);
    } else {
      contactEntity.setId(contact.getId());
    }
//    contactEntity.setEmployee(emplBl.convertFromDTO(empl));
    return contactEntity;
  }
  
  public ContactDto contactBuilder(Contact contact) {
    return new ContactDtoImpl.ContactDtoBuilder()
            .id(contact.getId())
            .contactType(contact.getContactType())
            .contactValue(contact.getContactValue())
            .build();
  }
  
  public void create(ContactDto contactDto){
      Contact contact = convertFromDTO(contactDto);
      contactFacade.create(contact);
  }
  
  public void remove(ContactDto contactDto){
      Contact contact = convertFromDTO(contactDto);
      contactFacade.remove(contact);
  }
  
  public void update(ContactDto contactDto){
      Contact contact = convertFromDTO(contactDto);
      contact.setEmployee(new Employee(contactDto.getEmployee().getId()));
      contactFacade.edit(contact);
  }
    
  public List<ContactDto> getAllContacts(){
    List<ContactDto> contacts = new ArrayList<>();
    for (Contact contact : contactFacade.findAll()) {
     contacts.add(contactBuilder(contact));
    }
    return contacts;
  }
  
}
