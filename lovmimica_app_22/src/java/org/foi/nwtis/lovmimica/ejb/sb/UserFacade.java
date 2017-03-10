/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.ejb.sb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.foi.nwtis.lovmimica.ejb.eb.User;

/**
 *
 * @author lovelmimica
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {
    
    @PersistenceContext(unitName = "lovmimica_aplikacija_22PU_02")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
}
