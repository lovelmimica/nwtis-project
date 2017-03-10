/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.web.beans.admin;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import org.foi.nwtis.lovmimica.ejb.eb.Request;
import org.foi.nwtis.lovmimica.ejb.sb.RequestFacade;

/**
 *
 * @author lovelmimica
 */
@Named(value = "appLogBean")
@Dependent
public class AppLogBean {

    @EJB
    private RequestFacade requestFacade;

    /**
     * Creates a new instance of AppLogBean
     */
    public AppLogBean() {
    }
    
    public List<Request> findRequestList(){
        List<Request> requestList = requestFacade.findAll();
        return requestList;
    }
}
