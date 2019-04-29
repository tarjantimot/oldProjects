
package hu.evo.hradmin.controller;

import hu.evo.hradmin.bl.impl.JobBlImpl;
import hu.evo.hradmin.dto.JobDto;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author szotyi
 */
@ManagedBean(name = "jobDescriptionUploadController", eager = true)
@SessionScoped
public class JobDescriptionUploadController implements Serializable {

    private JobDto job;
    private UploadedFile uploadedFile;
    private String folder = "C:\\middleware\\wildfly-15.0.1.Final\\standalone\\deployments\\hradmin-1.0.war\\resources";
    
    @EJB
    private JobBlImpl jobBl;

    public JobDto getJob() {
        return job;
    }

    public void setJob(JobDto job) {
        this.job = job;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
    
    public void attrListener(ActionEvent event){
	String action = (String)event.getComponent().getAttributes().get("job_id");
        Integer id = Integer.parseInt(action);
        job =  jobBl.getJob(id);
    }
    
    public String descriptionUploadPage(){
        return "/view/job/JobDescription.xhtml";
    }
    
    public void upload() {
        if(uploadedFile != null) {
            FacesMessage message = new FacesMessage("Succesful", uploadedFile.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void handleFileUpload(FileUploadEvent event) {
        InputStream input = null;
        try {
            Path folderPath = Paths.get(folder);
            System.out.println(event.getFile().getFileName());
            
            String filename = FilenameUtils.getBaseName(event.getFile().getFileName()); 
            String extension = FilenameUtils.getExtension(event.getFile().getFileName());

            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd_hh-mm-ss");
            String date = sdf.format(new Date());
            Path fileToCreate = folderPath.resolve("JOBDESCRIPTION-" + filename + "-ID_" 
                    + job.getId() 
                    + "-" + date + "." + extension);
            
            System.out.println("File to Create: " + fileToCreate);
            Path file = Files.createFile(fileToCreate);
            System.out.println("New File exits: " + Files.exists(file));
            
            input = event.getFile().getInputstream();
            Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);
            
            System.out.println("Uploaded file successfully saved in " + file); 
            input.close();
            
//            
            FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            job.setPath("../../../resources/" + "JOBDESCRIPTION-" + filename + "-ID_" 
                    + job.getId() 
                    + "-" + date + "." + extension);
            
            System.out.println("Job Description: " + job.getJobDescription());
            jobBl.update(job);          
//            with the following code there is something error :S (never dispatching to the List view)
            setUploadedFile(null);
//            FacesContext.getCurrentInstance().getExternalContext().dispatch("/view/job/List.xhtml");
        } catch(IOException ex){
            System.out.println(ex.getMessage());      
        } 

    }
    
}
