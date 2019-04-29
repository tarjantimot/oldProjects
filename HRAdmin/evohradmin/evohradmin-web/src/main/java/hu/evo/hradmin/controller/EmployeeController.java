package hu.evo.hradmin.controller;

import hu.evo.hradmin.bl.impl.AddressBlImpl;
import hu.evo.hradmin.bl.impl.ContactBlImpl;
import hu.evo.hradmin.bl.impl.ContractBlImpl;
import hu.evo.hradmin.bl.impl.EmployeeBlImpl;
import hu.evo.hradmin.bl.impl.JobBlImpl;
import hu.evo.hradmin.bl.impl.SalaryBlImpl;
import hu.evo.hradmin.controller.util.JsfUtil;
import hu.evo.hradmin.controller.util.JsfUtil.PersistAction;
import hu.evo.hradmin.dto.AddressDto;
import hu.evo.hradmin.dto.ContactDto;
import hu.evo.hradmin.dto.ContractDto;
import hu.evo.hradmin.dto.EmployeeDto;
import hu.evo.hradmin.dto.JobDto;
import hu.evo.hradmin.dto.SalaryDto;
import hu.evo.hradmin.dto.impl.AddressDtoImpl;
import hu.evo.hradmin.dto.impl.ContactDtoImpl;
import hu.evo.hradmin.dto.impl.ContractDtoImpl;
import hu.evo.hradmin.dto.impl.EmployeeDtoImpl;
import hu.evo.hradmin.dto.impl.SalaryDtoImpl;
import hu.evo.hradmin.model.Employee;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("employeeController")
@SessionScoped
public class EmployeeController implements Serializable {

  @EJB
  private JobBlImpl jobBl;

  @EJB
  private EmployeeBlImpl employeeBl;

  @EJB
  private SalaryBlImpl salaryBl;

  @EJB
  private AddressBlImpl addressBl;

  @EJB
  private ContactBlImpl contactBl;

  @EJB
  private ContractBlImpl contractBl;

  private List<EmployeeDto> items = null;
  private EmployeeDto selected;

  private AddressDto selectedAddress = null;
  private ContactDto selectedContact = null;
  private SalaryDto selectedSalary = null;
  private ContractDto selectedContract = null;

  private Integer jobId;

  public EmployeeController() {
  }

  public List<JobDto> getAllJobs() {
    return jobBl.getAllJobs();
  }

  public Integer getJobId() {
    return jobId;
  }

  public void setJobId(Integer jobId) {
    this.jobId = jobId;
  }

  public AddressDto getSelectedAddress() {
    return selectedAddress;
  }

  public void setSelectedAddress(AddressDto selectedAddress) {
    this.selectedAddress = selectedAddress;
  }

  public ContactDto getSelectedContact() {
    return selectedContact;
  }

  public void setSelectedContact(ContactDto selectedContact) {
    this.selectedContact = selectedContact;
  }

  public SalaryDto getSelectedSalary() {
    return selectedSalary;
  }

  public void setSelectedSalary(SalaryDto selectedSalary) {
    this.selectedSalary = selectedSalary;
  }

  public ContractDto getSelectedContract() {
    return selectedContract;
  }

  public void setSelectedContract(ContractDto selectedContract) {
    this.selectedContract = selectedContract;
  }

  public EmployeeDto getSelected() {
    return selected;
  }

  public void setSelected(EmployeeDto selected) {
    this.selected = selected;
  }

  protected void setEmbeddableKeys() {
  }

  protected void initializeEmbeddableKey() {
  }

  public EmployeeDto prepareCreate() {
    EmployeeDto employeeDto = new EmployeeDtoImpl();
    initializeEmbeddableKey();
    selected = employeeDto;
    return selected;
  }

  public void create() {
    persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("EmployeeCreated"));
    if (!JsfUtil.isValidationFailed()) {
      items = null;    // Invalidate list of items to trigger re-query.
    }
  }

  public void update() {
    persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("EmployeeUpdated"));
  }

  public void destroy() {
    persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("EmployeeDeleted"));
    if (!JsfUtil.isValidationFailed()) {
      selected = null; // Remove selection
      selectedAddress = null;
      selectedContact = null;
      selectedContract = null;
      selectedSalary = null;
      items = null;    // Invalidate list of items to trigger re-query.
    }
  }

  public List<EmployeeDto> getItems() {
//        if (items == null) {
    items = employeeBl.getAllEmployees();
//        }
    return items;
  }

  public SalaryDto prepareCreateSalary() {
    SalaryDto salaryDto = new SalaryDtoImpl();
    initializeEmbeddableKey();
    selectedSalary = salaryDto;
    return selectedSalary;
  }

  public void createSalary() {
    if (selectedSalary.getStartDate().before(selectedSalary.getEndDate())) {
      if (selectedSalary.getValue() >= selected.getJob().getMinSalary()
              && selectedSalary.getValue() <= selected.getJob().getMaxSalary()) {
        selected.getSalaryList().add(selectedSalary);
        persist(PersistAction.UPDATE, "Sikeresen hozzáadta a Fizetést!");
        getItems();
        for (EmployeeDto item : items) {
          if (item.getId().equals(selected.getId())) {
            selected = item;
          }
        }
//      selectedSalary.setId(0);
        selectedSalary = null;
        //      selected = null;
        items = null;    // Invalidate list of items to trigger re-query.
      } else {
        JsfUtil.addErrorMessage("A fizetésnek a min és max között kell lennie");
      }
    } else {
      JsfUtil.addErrorMessage("A kezdő dátum nem lehet később, mint a végző dátum");

    }
  }

  public void updateSalary() {
    int replacableSalary = 0;
    for (int i = 0; i < selected.getSalaryList().size(); i++) {
      if (selected.getSalaryList().get(i).getId().equals(selectedSalary.getId())) {
        replacableSalary = i;
      }
    }
    selectedSalary.setEmployee(selected);
    if (selectedSalary.getStartDate().before(selectedSalary.getEndDate())) {
      if (selectedSalary.getValue() >= selected.getJob().getMinSalary()
              && selectedSalary.getValue() <= selected.getJob().getMaxSalary()) {
        selected.getSalaryList().set(replacableSalary, selectedSalary);

        salaryBl.update(selectedSalary);
        System.out.println(selectedSalary.getEndDate());
        JsfUtil.addSuccessMessage("Sikeresen módositotta a fizetést! ");
        selectedSalary = null;
        items = null;
      } else {
        JsfUtil.addErrorMessage("A fizetésnek a min és max között kell lennie");
      }
    } else {
      JsfUtil.addErrorMessage("A kezdő dátum nem lehet később, mint a végző dátum");
    }
  }

  public void deleteSalary() {
    int replacableSalary = 0;
    for (int i = 0; i < selected.getSalaryList().size(); i++) {
      if (selected.getSalaryList().get(i).getId().equals(selectedSalary.getId())) {
        replacableSalary = i;
      }
    }
    selected.getSalaryList().remove(replacableSalary);
    salaryBl.remove(selectedSalary);
    JsfUtil.addSuccessMessage("Sikeresen kitörölte a fizetést! ");
    selectedSalary = null;
    items = null;
  }

  public AddressDto prepareCreateAddress() {
    AddressDto addressDto = new AddressDtoImpl();
    initializeEmbeddableKey();
    selectedAddress = addressDto;
    return selectedAddress;
  }

  public void createAddress() {
    selected.getAddressList().add(selectedAddress);
    persist(PersistAction.UPDATE, "Sikeresen hozzáadta a Cimet!");
//      selectedAddress.setEmployee(selected);
//      selectedAddress.setId(0);
    getItems();
    for (EmployeeDto item : items) {
      if (item.getId().equals(selected.getId())) {
        selected = item;
      }
    }
    if (!JsfUtil.isValidationFailed()) {
      selectedAddress = null;
//      selected = null;
//      items = null;
    }
  }

  public void updateAddress() {
    int replacableAddress = 0;
    for (int i = 0; i < selected.getAddressList().size(); i++) {
      if (selected.getAddressList().get(i).getId().equals(selectedAddress.getId())) {
        replacableAddress = i;
      }
    }
    selected.getAddressList().set(replacableAddress, selectedAddress);

    selectedAddress.setEmployee(selected);
    addressBl.update(selectedAddress);
    JsfUtil.addSuccessMessage("Sikeresen módositotta a Cimet! ");
    selectedAddress = null;
    items = null;
  }

  public void deleteAddress() {
    int replacableAddress = 0;
    for (int i = 0; i < selected.getAddressList().size(); i++) {
      if (selected.getAddressList().get(i).getId().equals(selectedAddress.getId())) {
        replacableAddress = i;
      }
    }
    selected.getAddressList().remove(replacableAddress);
    addressBl.remove(selectedAddress);
    JsfUtil.addSuccessMessage("Sikeresen kitörölte a Cimet! ");
    selectedAddress = null;
    items = null;
  }

  public ContactDto prepareCreateContact() {
    ContactDto contactDto = new ContactDtoImpl();
    initializeEmbeddableKey();
    selectedContact = contactDto;
    return selectedContact;
  }

  public void createContact() {
    selected.getContactList().add(selectedContact);
    persist(PersistAction.UPDATE, "Sikeresen hozzáadta az Elérhetőséget!");
//    selectedContact.setId(0);
    getItems();
    for (EmployeeDto item : items) {
      if (item.getId().equals(selected.getId())) {
        selected = item;
      }
    }
    if (!JsfUtil.isValidationFailed()) {
      selectedContact = null;
//      selected = null;
//      items = null;
    }
  }

  public void updateContact() {
    int replacableContact = 0;
    for (int i = 0; i < selected.getContactList().size(); i++) {
      if (selected.getContactList().get(i).getId().equals(selectedContact.getId())) {
        replacableContact = i;
      }
    }
    selected.getContactList().set(replacableContact, selectedContact);

    selectedContact.setEmployee(selected);
    contactBl.update(selectedContact);
    JsfUtil.addSuccessMessage("Sikeresen módositotta az Elérhetőséget! ");
    selectedContact = null;
    items = null;
  }

  public void deleteContact() {
    int replacableContact = 0;
    for (int i = 0; i < selected.getContactList().size(); i++) {
      if (selected.getContactList().get(i).getId().equals(selectedContact.getId())) {
        replacableContact = i;
      }
    }
    selected.getContactList().remove(replacableContact);
    contactBl.remove(selectedContact);
    JsfUtil.addSuccessMessage("Sikeresen kitörölte az Elérhetőséget! ");
    selectedContact = null;
    items = null;
  }

  public String createContractDownloadText() {
    if (selectedContract.getPath() == null) {
      return "";
    } else {
      return "Dokumentum letöltése";
    }
  }

  public ContractDto prepareCreateContract() {
    ContractDto contractDto = new ContractDtoImpl();
    initializeEmbeddableKey();
    selectedContract = contractDto;
    return selectedContract;
  }

  public void createContract() {
    if (selectedContract.getCreationDate().before(selectedContract.getExpireDate())) {
      selected.getContractList().add(selectedContract);
      persist(PersistAction.UPDATE, "Sikeresen hozzáadta a Szerződést!");
//    selectedContract.setId(0); //it is a dummy it cant be 0, but in this way the dataTable will refresh..
      getItems();
      for (EmployeeDto item : items) {
        if (item.getId().equals(selected.getId())) {
          selected = item;
        }
      }
      if (!JsfUtil.isValidationFailed()) {
        selectedContract = null;
//      items = null;    // Invalidate list of items to trigger re-query.
      } else {
        JsfUtil.addErrorMessage("A Szerződés nem járhat le elöbb, minthogy létrehoztuk!");

      }
    }
  }

  public void updateContract() {
    if (selectedContract.getCreationDate().before(selectedContract.getExpireDate())) {
      int replacableContract = 0;
      for (int i = 0; i < selected.getContractList().size(); i++) {
        if (selected.getContractList().get(i).getId().equals(selectedContract.getId())) {
          replacableContract = i;
        }
      }
      selected.getContractList().set(replacableContract, selectedContract);

      selectedContract.setEmployee(selected);
      contractBl.update(selectedContract);
      JsfUtil.addSuccessMessage("Sikeresen módositotta a Szerződést! ");
      selectedContract = null;
      items = null;
    } else {
      JsfUtil.addErrorMessage("A Szerződés nem járhat le elöbb, minthogy létrehoztuk!");

    }
  }

  public void deleteContract() {
    int replacableContract = 0;
    for (int i = 0; i < selected.getContractList().size(); i++) {
      if (selected.getContractList().get(i).getId().equals(selectedContract.getId())) {
        replacableContract = i;
      }
    }
    selected.getContractList().remove(replacableContract);
    contractBl.remove(selectedContract);
    JsfUtil.addSuccessMessage("Sikeresen kitörölte a Szerződést! ");
    selectedContact = null;
    items = null;
  }

//  public JobDto getActualJob(){
//    jobBl.getJob(selected.getJob().getId());
//  }
  private void persist(PersistAction persistAction, String successMessage) {
    if (selected != null) {
      setEmbeddableKeys();
      try {
        if (persistAction == PersistAction.CREATE) {
          selected.setJob(jobBl.getJob(jobId));
          employeeBl.create(selected);
          items = null;
        } else if (persistAction == PersistAction.UPDATE) {
          if (jobId != null) {
            selected.setJob(jobBl.getJob(jobId));
            items = null;
            jobId = null;
          }
          items = null;
          employeeBl.update(selected);
        } else {
          employeeBl.delete(selected.getId());
          items = null;
        }
        JsfUtil.addSuccessMessage(successMessage);
      } catch (EJBException ex) {
        String msg = "";
        Throwable cause = ex.getCause();
        if (cause != null) {
          msg = cause.getLocalizedMessage();
        }
        if (msg.length() > 0) {
          JsfUtil.addErrorMessage(msg);
        } else {
          JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
      } catch (Exception ex) {
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
      }
    }
  }

  public EmployeeDto getEmployee(java.lang.Integer id) {
    return employeeBl.find(id);
  }

  public List<EmployeeDto> getItemsAvailableSelectMany() {
    return employeeBl.getAllEmployees();
  }

  public List<EmployeeDto> getItemsAvailableSelectOne() {
    return employeeBl.getAllEmployees();
  }

  @FacesConverter(forClass = Employee.class)
  public static class EmployeeControllerConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
      if (value == null || value.length() == 0) {
        return null;
      }
      EmployeeController controller = (EmployeeController) facesContext.getApplication().getELResolver().
              getValue(facesContext.getELContext(), null, "employeeController");
      return controller.getEmployee(getKey(value));
    }

    java.lang.Integer getKey(String value) {
      java.lang.Integer key;
      key = Integer.valueOf(value);
      return key;
    }

    String getStringKey(java.lang.Integer value) {
      StringBuilder sb = new StringBuilder();
      sb.append(value);
      return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
      if (object == null) {
        return null;
      }
      if (object instanceof Employee) {
        Employee o = (Employee) object;
        return getStringKey(o.getId());
      } else {
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Employee.class.getName()});
        return null;
      }
    }

  }

}
