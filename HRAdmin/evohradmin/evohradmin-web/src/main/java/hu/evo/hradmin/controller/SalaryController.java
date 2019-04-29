package hu.evo.hradmin.controller;

import hu.evo.hradmin.bl.impl.EmployeeBlImpl;
import hu.evo.hradmin.bl.impl.SalaryBlImpl;
import hu.evo.hradmin.model.Salary;
import hu.evo.hradmin.controller.util.JsfUtil;
import hu.evo.hradmin.controller.util.JsfUtil.PersistAction;
import hu.evo.hradmin.dto.SalaryDto;
import hu.evo.hradmin.dto.impl.SalaryDtoImpl;
import hu.evo.hradmin.repository.impl.SalaryFacade;

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

@Named("salaryController")
@SessionScoped
public class SalaryController implements Serializable {

    @EJB
    private hu.evo.hradmin.repository.impl.SalaryFacade ejbFacade;
        
    @EJB
    private SalaryBlImpl salaryBl;
    
    @EJB
    private EmployeeBlImpl employeeBl;
    
    private List<SalaryDto> items = null;
    private SalaryDtoImpl selected;
    private Integer employeeId = 2;

    public SalaryController() {
    }

    
    public SalaryDtoImpl getSelected() {
        return selected;
    }

    public void setSelected(SalaryDtoImpl selected) {
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

    private SalaryFacade getFacade() {
        return ejbFacade;
    }

    public SalaryDtoImpl prepareCreate() {
        selected = new SalaryDtoImpl();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SalaryCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SalaryUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SalaryDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<SalaryDto> getItems() {
        if (items == null) {
            items = salaryBl.getAllSalaries();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
//          selected.setEmployee(employeeBl.find(employeeId));
          setEmbeddableKeys();
          try {
            if (persistAction == PersistAction.CREATE) {
//              salaryBl.create(selected);
              } else if (persistAction == PersistAction.UPDATE) {
//                 salaryBl.update(selected);
              } else {
//                  salaryBl.remove(selected);
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

    public Salary getSalary(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Salary> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Salary> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Salary.class)
    public static class SalaryControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SalaryController controller = (SalaryController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "salaryController");
            return controller.getSalary(getKey(value));
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
            if (object instanceof Salary) {
                Salary o = (Salary) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Salary.class.getName()});
                return null;
            }
        }

    }

}
