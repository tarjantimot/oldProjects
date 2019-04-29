
package hu.evo.hradmin.rest.impl;

import hu.evo.hradmin.bl.impl.JobBlImpl;
import hu.evo.hradmin.dto.JobDto;
import hu.evo.hradmin.dto.impl.JobDtoImpl;
import hu.evo.hradmin.rest.IJobResource;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class JobResourceImpl implements IJobResource {

    @EJB
    private JobBlImpl jobBl;
    
    @Override
    public List<JobDto> getAllJobs() {
        return jobBl.getAllJobs();
    }
     
}
