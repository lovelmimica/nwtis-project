/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.web.ws.rest.service;

import com.foi.nwtis.lovmimica.logika.ws.Address;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import org.foi.nwtis.lovmimica.web.beans.ActiveUserBean;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.foi.nwtis.lovmimica.app.ActiveUserRecordStatic;

/**
 * REST Web Service
 *
 * @author lovelmimica
 */
@Path("rest")
public class RestWsResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of HelloWorldResource
     */
    public RestWsResource() {
    }

    /**
     * Retrieves representation of an instance of org.foi.nwtis.lovmimica.app.HelloWorldResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("active_users")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ActiveUserBean> getActiveUsers() {
        List<ActiveUserBean> activeUsers = ActiveUserRecordStatic.getActiveUsers();
        
        return activeUsers;
    }
    @GET
    @Path("users_addresses/{user}")
    public List<AddressNameWrapper> getUsersAddresses(@PathParam("user") String user) {
        
        List<Address> usersAddresses = dajAdreseKorisnika("pero", "123");
        List<AddressNameWrapper> nameList = new ArrayList<AddressNameWrapper>();
        for(Address a : usersAddresses){
            nameList.add(new AddressNameWrapper(a.getName()));
        }
        
        return nameList;
    }
    
    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getTest(){
        
        return Response.ok("test_response").build();
    }

    /**
     * PUT method for updating or creating an instance of HelloWorldResource
     * @param content representation for the resource
     */
    

    private static java.util.List<com.foi.nwtis.lovmimica.logika.ws.Address> dajAdreseKorisnika(java.lang.String user, java.lang.String passwd) {
        com.foi.nwtis.lovmimica.logika.ws.SoapWS_Service service = new com.foi.nwtis.lovmimica.logika.ws.SoapWS_Service();
        com.foi.nwtis.lovmimica.logika.ws.SoapWS port = service.getSoapWSPort();
        return port.getUsersAddresses(user, passwd);
    }
}
