
package hu.evo.hradmin.repository.impl;

import hu.evo.hradmin.model.Salary;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author szotyi
 */
@Stateless
public class SalaryFacade extends AbstractFacade<Salary> {

    @PersistenceContext(unitName = "hu.evo.hradmin_evohradmin-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SalaryFacade() {
        super(Salary.class);
    }
    
    
}
