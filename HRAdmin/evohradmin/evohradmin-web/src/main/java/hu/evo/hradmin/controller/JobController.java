package hu.evo.hradmin.controller;

import hu.evo.hradmin.bl.impl.JobBlImpl;
import hu.evo.hradmin.model.Job;
import hu.evo.hradmin.controller.util.JsfUtil;
import hu.evo.hradmin.controller.util.JsfUtil.PersistAction;
import hu.evo.hradmin.dto.JobDto;
import hu.evo.hradmin.dto.impl.JobDtoImpl;
import hu.evo.hradmin.exception.InvalidOperationException;
import hu.evo.hradmin.exception.ValidationException;
import hu.evo.hradmin.repository.impl.JobFacade;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.io.Serializable;
import java.nio.file.Files;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.servlet.http.Part;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "jobController", eager = true)
@ViewScoped
public class JobController implements Serializable {

    @EJB
    private JobFacade ejbFacade;
    private List<JobDto> items = null;
//    private List<JobDto> filteredItems;
    private JobDto selected = null;
    
//    @Inject
    private Logger logger;
    
    /*fields for input validations:*/
//    private Double minSalary;
//    private Double maxSalary;
//    private String workerNum;
    
    //may we should job jobBl instead of ejbFacade (bl layer should go first, and void to use repository elements from view)
    @EJB
    private JobBlImpl jobBl;
    
    public JobController() {
    }
    
    
    public JobDto getSelected() {
        return selected;
    }

    public void setSelected(JobDto selected) {
        this.selected = selected;
    }
    
    public String getJobDescriptionSubstr(String jobDescription){
        if(jobDescription.length() > 45){
            return jobDescription.substring(0, 40).toString() + ".....";
        } 
        return jobDescription;
    }
    
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private JobFacade getFacade() {
        return jobBl.getJobFacade();
    }
    
    public String goToJobDescriptionPage(){
        return "/view/job/JobDescription.xhtml?faces-redirect=true";
    }
    
    /*GENERATED METHODS:*/
       
    public JobDto prepareCreate() {
        JobDto jobDto = new JobDtoImpl();
        initializeEmbeddableKey();
        selected = jobDto;
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("JobCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("JobUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("JobDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<JobDto> getItems() {
        if (items == null) {
            items = jobBl.getAllJobs();
        }
        return items;
    }
    
    private boolean validateInputs(JobDto dto){
        
        Double minSalary = dto.getMinSalary();
        System.out.println("Min salary : " + minSalary);
        Double maxSalary = dto.getMaxSalary();
        System.out.println("Max salary : " + minSalary);
        Integer workerNum = dto.getWorkerNum();
        System.out.println("Worker num : " + workerNum);
        if(minSalary < maxSalary && workerNum >= 0 && minSalary > 0 && maxSalary > 0){
            System.out.println("Validation success");
            return true;
        }
        System.out.println("Validation failed");
        return false;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if(validateInputs(selected)){
                    if (persistAction == PersistAction.CREATE) {                    
                            jobBl.create(selected);
    //                        selected = null;
                        
                    } else if(persistAction == PersistAction.UPDATE) {
                        jobBl.update(selected);
    //                    selected = null;
                    } else {
                        if(!jobBl.hasEmployee(selected.getId())){
                            jobBl.delete(selected.getId());
                        } else {
                            throw new InvalidOperationException("Nem törölhető állás!\n Az álláshoz regisztrált dolgozó(k) tartozik.");
                        }  
                    }
                    JsfUtil.addSuccessMessage(successMessage);
                } else {
                    throw new ValidationException("Hibás adatbevitel!");
                }
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
            } catch (ValidationException | InvalidOperationException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ex.getMessage());
            }catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
            
        }
        
    }

    public JobDto getJob(Integer id) {
        return jobBl.getJob(id);
    }

    public List<JobDto> getItemsAvailableSelectMany() {
        return jobBl.getAllJobs();
    }

    public List<JobDto> getItemsAvailableSelectOne() {
        return jobBl.getAllJobs();
    }

    @FacesConverter(forClass = JobDto.class)
    public static class JobControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            JobController controller = (JobController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "jobController");
            return controller.getJob(getKey(value));
        }

        Integer getKey(String value) {
            Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Job) {
                Job o = (Job) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Job.class.getName()});
                return null;
            }
        }

    }

}
