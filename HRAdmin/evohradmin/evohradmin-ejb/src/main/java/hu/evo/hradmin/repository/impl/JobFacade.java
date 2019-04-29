
package hu.evo.hradmin.repository.impl;

import hu.evo.hradmin.model.Job;
import hu.evo.hradmin.repository.IJobFacade;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author szotyi
 */
@Stateless
public class JobFacade extends AbstractFacade<Job> {

    @PersistenceContext(unitName = "hu.evo.hradmin_evohradmin-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public JobFacade() {
        super(Job.class);
    }
    
}
