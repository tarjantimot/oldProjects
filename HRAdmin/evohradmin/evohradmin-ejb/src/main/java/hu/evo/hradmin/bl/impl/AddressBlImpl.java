/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.evo.hradmin.bl.impl;

import hu.evo.hradmin.dto.AddressDto;
import hu.evo.hradmin.dto.impl.AddressDtoImpl;
import hu.evo.hradmin.model.Address;
import hu.evo.hradmin.model.Employee;
import hu.evo.hradmin.repository.impl.AbstractFacade;
import hu.evo.hradmin.repository.impl.AddressFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Erdahoy
 */
@Stateless
public class AddressBlImpl extends AbstractBlImpl<Address> {

  @EJB
  private EmployeeBlImpl emplBl;
  
  @EJB
  private AddressFacade addressFacade;
  
  @Override
  protected AbstractFacade<Address> getFacade() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  public AddressDto convertFromEntity(Address address) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  public Address convertFromDTO(AddressDto address, Employee employee) {
    Address addressEntity =  new Address();
    addressEntity.setCountry(address.getCountry());
    addressEntity.setPostalCode(address.getPostalCode());
    addressEntity.setCity(address.getCity());
    addressEntity.setStreet(address.getStreet());
    addressEntity.setStreetNumber(address.getStreetNumber());
    addressEntity.setDoor(address.getDoor());
    addressEntity.setFloor(address.getFloor());
    addressEntity.setEmployee(employee);
    if(address.getId() == null){
      addressFacade.create(addressEntity);
    } else {
      addressEntity.setId(address.getId());
    }
    
//    addressEntity.setEmployee(emplBl.convertFromDTO(employee));
    return addressEntity;
  }
  
    public Address convertFromDTO(AddressDto address) {
    Address addressEntity =  new Address();
    addressEntity.setCountry(address.getCountry());
    addressEntity.setPostalCode(address.getPostalCode());
    addressEntity.setCity(address.getCity());
    addressEntity.setStreet(address.getStreet());
    addressEntity.setStreetNumber(address.getStreetNumber());
    addressEntity.setDoor(address.getDoor());
    addressEntity.setFloor(address.getFloor());
//    addressEntity.setEmployee(employee);
    if(address.getId() == null){
      addressFacade.create(addressEntity);
    } else {
      addressEntity.setId(address.getId());
    }
    
//    addressEntity.setEmployee(emplBl.convertFromDTO(employee));
    return addressEntity;
  }

  public AddressDto addressBuilder(Address address) {
    return new AddressDtoImpl.AddressDtoImplBuilder()
                            .id(address.getId())
                            .coutry(address.getCountry())
                            .postalCode(address.getPostalCode())
                            .city(address.getCity())
                            .street(address.getStreet())
                            .streetNumber(address.getStreetNumber())
                            .floor(address.getFloor())
                            .door(address.getDoor())
                            .build();
  }
  
    public void create(AddressDto addressDto){
      Address address = convertFromDTO(addressDto);
      address.setEmployee(new Employee(addressDto.getEmployee().getId()));
      addressFacade.create(address);
  }
  
    public void remove(AddressDto addressDto){
      Address address = convertFromDTO(addressDto);
      addressFacade.remove(address);
  }
  
    public void update(AddressDto addressDto){
      Address address = convertFromDTO(addressDto);
      address.setEmployee(new Employee(addressDto.getEmployee().getId()));
      addressFacade.edit(address);
  }
    
  public List<AddressDto> getAllAddresses(){
    List<AddressDto> addresses = new ArrayList<>();
    for (Address address : addressFacade.findAll()) {
     addresses.add(addressBuilder(address));
    }
    return addresses;
  }
  
}
