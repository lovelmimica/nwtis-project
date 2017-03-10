/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.logika.ws.rest.service;

import com.foi.nwtis.lovmimica.bp.PresentAddresses;
import com.foi.nwtis.lovmimica.datatypes.Address;
import com.foi.nwtis.lovmimica.logika.ws.InitialWsActions;
import com.foi.nwtis.lovmimica.logika.ws.rest.Adrese;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author lovelmimica
 */
@Stateless
@Path("addresses")
public class AdreseFacadeREST extends AbstractFacade<Adrese> {

    @PersistenceContext(unitName = "lovmimica_aplikacija_1PU")
    private EntityManager em;

    public AdreseFacadeREST() {
        super(Adrese.class);
    }

    @GET
    @Path("getAllAddresses/{username}/{password}")
    @Produces(MediaType.TEXT_XML)
    public List<Adrese> getAllAddresses(@PathParam("username") String username,
            @PathParam("password") String passwd) {
        boolean approved = InitialWsActions.execute(username, passwd);
        if (approved) {
            List<Address> allAddresses = PresentAddresses.getAll();
            List<Adrese> allAdrese = new ArrayList<Adrese>();
            for (Address a : allAddresses) {
                allAdrese.add(new Adrese(a));
            }
            return allAdrese;
        }
        return null;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
