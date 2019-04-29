
package hu.evo.hradmin.repository.impl;

import hu.evo.hradmin.model.Contract;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author szotyi
 */
@Stateless
public class ContractFacade extends AbstractFacade<Contract> {

    @PersistenceContext(unitName = "hu.evo.hradmin_evohradmin-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContractFacade() {
        super(Contract.class);
    }
    
}
