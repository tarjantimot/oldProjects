/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.evo.hradmin.bl;

import hu.evo.hradmin.dto.JobDto;
import hu.evo.hradmin.model.Job;

/**
 *
 * @author szotyi
 */

public interface JobBl {

    JobDto convertFromEntity(Job job);
    
    Job convertFromDTO(JobDto jobDTO);
    
    JobDto jobBuilder(Job job);
    
    boolean hasEmployee(int id);
}
