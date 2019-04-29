/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.evo.hradmin.repository;

import hu.evo.hradmin.model.Job;
import java.util.List;

/**
 *
 * @author szotyi
 */
public interface IJobFacade {
    
    List<Job> findAll();
    
    
}
