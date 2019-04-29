/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.evo.hradmin.bl;

import hu.evo.hradmin.repository.impl.AbstractFacade;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author szotyi
 */
public abstract class AbstractBl<T extends Serializable> {

    
    protected AbstractBl() {
    }

    protected abstract AbstractFacade<T> getFacade();

    public T find(int id) {
        return getFacade().find(id);
    }

    public void save(T entity) {
        getFacade().create(entity);
    }

    public void update(T entity) {
        getFacade().edit(entity);
    }

    public void delete(int id) {
        getFacade().remove(getFacade().find(id));
    }

//    public void refresh(T entity) {
//        getFacade().refresh(entity);
//    }

    public List<T> findAll() {
        return getFacade().findAll();
    }
    
}
