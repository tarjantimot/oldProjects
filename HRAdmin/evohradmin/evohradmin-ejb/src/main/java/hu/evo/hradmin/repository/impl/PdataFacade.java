
package hu.evo.hradmin.repository.impl;

import hu.evo.hradmin.model.Pdata;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author szotyi
 */
@Stateless
public class PdataFacade extends AbstractFacade<Pdata> {

    @PersistenceContext(unitName = "hu.evo.hradmin_evohradmin-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PdataFacade() {
        super(Pdata.class);
    }
    
}
