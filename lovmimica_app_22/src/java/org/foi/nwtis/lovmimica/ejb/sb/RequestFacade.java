/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.ejb.sb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.foi.nwtis.lovmimica.ejb.eb.AbstractFacade;
import org.foi.nwtis.lovmimica.ejb.eb.Request;

/**
 *
 * @author lovelmimica
 */
@Stateless
public class RequestFacade extends AbstractFacade<Request> {

    @PersistenceContext(unitName = "lovmimica_aplikacija_22PU_02")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RequestFacade() {
        super(Request.class);
    }
    
    public Integer createId(){
        for(Integer i = 0; ; i++){
            if(find(i) == null) return i;
        }
    }
}
