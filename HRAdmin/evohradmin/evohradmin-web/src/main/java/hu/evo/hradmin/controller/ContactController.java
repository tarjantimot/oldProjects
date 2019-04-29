package hu.evo.hradmin.controller;

import hu.evo.hradmin.bl.impl.ContactBlImpl;
import hu.evo.hradmin.bl.impl.EmployeeBlImpl;
import hu.evo.hradmin.model.Contact;
import hu.evo.hradmin.controller.util.JsfUtil;
import hu.evo.hradmin.controller.util.JsfUtil.PersistAction;
import hu.evo.hradmin.dto.ContactDto;
import hu.evo.hradmin.dto.impl.ContactDtoImpl;
import hu.evo.hradmin.repository.impl.ContactFacade;

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

@Named("contactController")
@SessionScoped
public class ContactController implements Serializable {

    @EJB
    private hu.evo.hradmin.repository.impl.ContactFacade ejbFacade;
    
    @EJB
    private ContactBlImpl contactBl;
    
    @EJB
    private EmployeeBlImpl employeeBl;
    
    private List<ContactDto> items = null;
    private ContactDtoImpl selected;
    private Integer employeeId = 2;
    
    public ContactController() {
    }

    public ContactDto getSelected() {
        return selected;
    }

  public Integer getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Integer employeeId) {
    this.employeeId = employeeId;
  }

    
    
    public void setSelected(ContactDtoImpl selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ContactFacade getFacade() {
        return ejbFacade;
    }

    public ContactDto prepareCreate() {
        selected = new ContactDtoImpl();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ContactCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ContactUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ContactDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ContactDto> getItems() {
        if (items == null) {
            items = contactBl.getAllContacts();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            selected.setEmployee(employeeBl.find(employeeId));
            setEmbeddableKeys();
            try {
              if (persistAction == PersistAction.CREATE) {
                contactBl.create(selected);
              } else if (persistAction == PersistAction.UPDATE) {
                    contactBl.update(selected);
                } else {
                    contactBl.remove(selected);
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

    public Contact getContact(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Contact> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Contact> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Contact.class)
    public static class ContactControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ContactController controller = (ContactController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "contactController");
            return controller.getContact(getKey(value));
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
            if (object instanceof Contact) {
                Contact o = (Contact) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Contact.class.getName()});
                return null;
            }
        }

    }

}
