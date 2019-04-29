package hu.evo.hradmin.controller;

import hu.evo.hradmin.bl.impl.AddressBlImpl;
import hu.evo.hradmin.bl.impl.EmployeeBlImpl;
import hu.evo.hradmin.model.Address;
import hu.evo.hradmin.controller.util.JsfUtil;
import hu.evo.hradmin.controller.util.JsfUtil.PersistAction;
import hu.evo.hradmin.dto.AddressDto;
import hu.evo.hradmin.dto.impl.AddressDtoImpl;
import hu.evo.hradmin.repository.impl.AddressFacade;

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

@Named("addressController")
@SessionScoped
public class AddressController implements Serializable {

    @EJB
    private hu.evo.hradmin.repository.impl.AddressFacade ejbFacade;
    
    @EJB
    private AddressBlImpl addressBl;
    
    @EJB
    private EmployeeBlImpl employeeBl;
    
    
    private List<AddressDto> items = null;
    private AddressDtoImpl selected;
    private Integer employeeId = 2;

    public AddressController() {
    }

    public AddressDtoImpl getSelected() {
        return selected;
    }

    public void setSelected(AddressDtoImpl selected) {
        this.selected = selected;
    }

  public Integer getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Integer employeeId) {
    this.employeeId = employeeId;
  }

    
    
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AddressFacade getFacade() {
        return ejbFacade;
    }

    public AddressDto prepareCreate() {
        selected = new AddressDtoImpl();
        initializeEmbeddableKey();
        return selected;
    }

    public void create(Integer id) {
        employeeId = id;
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AddressCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AddressUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AddressDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<AddressDto> getItems() {
        if (items == null) {
            items = addressBl.getAllAddresses();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            selected.setEmployee(employeeBl.find(employeeId));
            setEmbeddableKeys();
            try {
              if (persistAction == PersistAction.CREATE) {
                addressBl.create(selected);
              } else if (persistAction == PersistAction.UPDATE) {
                  addressBl.update(selected);
                } else {
                  addressBl.remove(selected);
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

    public Address getAddress(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Address> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Address> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Address.class)
    public static class AddressControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AddressController controller = (AddressController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "addressController");
            return controller.getAddress(getKey(value));
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
            if (object instanceof Address) {
                Address o = (Address) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Address.class.getName()});
                return null;
            }
        }

    }

}
