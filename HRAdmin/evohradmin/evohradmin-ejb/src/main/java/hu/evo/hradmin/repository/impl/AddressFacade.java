
package hu.evo.hradmin.repository.impl;

import hu.evo.hradmin.model.Address;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AddressFacade extends AbstractFacade<Address> {

    @PersistenceContext(unitName = "hu.evo.hradmin_evohradmin-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AddressFacade() {
        super(Address.class);
    }
    
}
