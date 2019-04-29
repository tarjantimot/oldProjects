package hu.evo.hradmin.controller;

import hu.evo.hradmin.bl.impl.ContractBlImpl;
import hu.evo.hradmin.bl.impl.EmployeeBlImpl;
import hu.evo.hradmin.model.Contract;
import hu.evo.hradmin.controller.util.JsfUtil;
import hu.evo.hradmin.controller.util.JsfUtil.PersistAction;
import hu.evo.hradmin.dto.ContractDto;
import hu.evo.hradmin.dto.impl.ContractDtoImpl;
import hu.evo.hradmin.repository.impl.ContractFacade;

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

@Named("contractController")
@SessionScoped
public class ContractController implements Serializable {

    @EJB
    private hu.evo.hradmin.repository.impl.ContractFacade ejbFacade;
    
    @EJB
    private ContractBlImpl contractBl;
    
    @EJB
    private EmployeeBlImpl employeeBl;
    
    private List<ContractDto> items = null;
    private ContractDto selected;
    private Integer employeeId = 2;

    public ContractController() {
    }

    public ContractDto getSelected() {
        return selected;
    }

  public Integer getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Integer employeeId) {
    this.employeeId = employeeId;
  }

    
    
    public void setSelected(ContractDto selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ContractFacade getFacade() {
        return ejbFacade;
    }

    public ContractDto prepareCreate() {
        selected = new ContractDtoImpl();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ContractCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ContractUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ContractDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ContractDto> getItems() {
        if (items == null) {
            items = contractBl.getAllContracts();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
//            selected.setEmployee(employeeBl.find(employeeId));
            setEmbeddableKeys();
            try {
              if (persistAction == PersistAction.CREATE) {
                contractBl.create(selected);
              } else if (persistAction == PersistAction.UPDATE) {
                    contractBl.update(selected);
                } else {
                    contractBl.remove(selected);
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

    public Contract getContract(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Contract> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Contract> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Contract.class)
    public static class ContractControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ContractController controller = (ContractController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "contractController");
            return controller.getContract(getKey(value));
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
            if (object instanceof Contract) {
                Contract o = (Contract) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Contract.class.getName()});
                return null;
            }
        }

    }

}
