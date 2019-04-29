/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.evo.hradmin.rest;

import hu.evo.hradmin.dto.JobDto;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author szotyi
 */
@Path("jobs")
@Consumes({ MediaType.APPLICATION_JSON})
@Produces(MediaType.APPLICATION_JSON  + ";charset=utf-8")
public interface IJobResource {
   
    @GET
    public List<JobDto> getAllJobs();
    
    
    
}
