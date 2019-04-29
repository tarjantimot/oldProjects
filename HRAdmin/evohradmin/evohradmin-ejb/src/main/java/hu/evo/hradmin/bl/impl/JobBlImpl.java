
package hu.evo.hradmin.bl.impl;

import hu.evo.hradmin.bl.JobBl;
import hu.evo.hradmin.dto.JobDto;
import hu.evo.hradmin.dto.impl.JobDtoImpl;
import hu.evo.hradmin.model.Job;
import hu.evo.hradmin.repository.impl.AbstractFacade;
import hu.evo.hradmin.repository.impl.JobFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author szotyi
 */
@Stateless
public class JobBlImpl extends AbstractBlImpl<Job> {
    
    @EJB
    private JobFacade jobFacade;
    
    public JobBlImpl(){
        super();
    }

    public JobFacade getJobFacade() {
        return jobFacade;
    }
    
    public List<JobDto> getAllJobs() {
        List<JobDto> jobs = new ArrayList<>();
        for (Job job : jobFacade.findAll()) {
            jobs.add(jobBuilder(job));
        }    
        return jobs;
    }
    
    public JobDto jobBuilder(Job job){
        return new JobDtoImpl.JobDTOBuilder()
                         .id(job.getId())
                         .jobTitle(job.getJobTitle())
                         .jobDescription(job.getJobDescription())
                         .path(job.getPath())
                         .minSalary(job.getMinSalary())
                         .maxSalary(job.getMaxSalary())
                         .workerNum(job.getWorkerNum())
                         .location(job.getLocation())
                         .build();
    }
    
    public JobDto getJob(int id){
        Job job = jobFacade.find(id);
        if(job != null){
            return jobBuilder(job);
        }
        return null;
    }
    
    public Job getJobEntity(int id){
        return jobFacade.find(id);
    }
    
    @Override
    protected AbstractFacade<Job> getFacade() {
        return jobFacade;
    }
    
    public void create(JobDto jobDTO){
        //TODO IMPL
        Job job = convertFromDTO(jobDTO);
        jobFacade.create(job);
    }
    
    public void update(JobDto jobDTO){
         //TODO IMPL
        Job job = convertFromDTO(jobDTO);
        jobFacade.edit(job);
    }
    
    public boolean hasEmployee(int id){
        Job job = jobFacade.find(id);
        if(!job.getEmployeeList().isEmpty()){
            return true;
        }
        return false;
    }
    
    public void delete(int id) {
         //TODO IMPL
        jobFacade.remove(jobFacade.find(id));
    }
    
    /* BL FOR SEARCH + FILE UPLOAD GOES HERE... */
    
    public JobDto convertFromEntity(Job job){
        if(job != null){
            return new JobDtoImpl.JobDTOBuilder()
                                .id(job.getId())
                                .jobTitle(job.getJobTitle())
                                .jobDescription(job.getJobDescription())
                                .path(job.getPath())
                                .minSalary(job.getMinSalary())
                                .maxSalary(job.getMaxSalary())
                                .workerNum(job.getWorkerNum())
                                .location(job.getLocation())
                                .currency("HUF")
                                .build();
        }
        return null;
    }
    
    public Job convertFromDTO(JobDto jobDTO){
        Job job = new Job();
        job.setId(jobDTO.getId());
        job.setJobTitle(jobDTO.getJobTitle());
        job.setJobDescription(jobDTO.getJobDescription());
        job.setPath(jobDTO.getPath());
        job.setMinSalary(jobDTO.getMinSalary());
        job.setMaxSalary(jobDTO.getMaxSalary());
        job.setWorkerNum(jobDTO.getWorkerNum());
        job.setLocation(jobDTO.getLocation());
        return job;
    } 
    
}
