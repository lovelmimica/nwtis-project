/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.web.beans;

import com.foi.nwtis.lovmimica.logika.ws.MeteoData;
import com.foi.nwtis.lovmimica.logika.ws.SoapWS_Service;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.Response;
import javax.xml.ws.WebServiceRef;
import org.foi.nwtis.web.ws.rest.client.App02RestWsClient;

/**
 *
 * @author lovelmimica
 */
@Named(value = "wSRequesingBean")
@RequestScoped
public class WSRequesingBean {

    private String address;
    private String response;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    
    
    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
    
    /**
     * Creates a new instance of WSRequesingBean
     */
    public WSRequesingBean() {
    }
    public String requestActiveUsers(){
        App02RestWsClient client = new App02RestWsClient();
        Response r = client.getActiveUsers();
        response = r.toString();
                
        return null;
    }
    public String requestUsersAddresses(){
        App02RestWsClient client = new App02RestWsClient();
        response = client.getUsersAddresses("pero").readEntity(String.class);
        return null;
    }
    
    public String requestCurrentMeteoData(){
        
        MeteoData md = getCurrentMeteoData("pero", "123", address);
        
        StringBuilder sb = new StringBuilder();
        sb.append("\nTemperature: " + md.getTemperature());
        sb.append("\nHumidity: " + md.getHumidity());
        sb.append("\nWind: " + md.getWind());
        sb.append("\nPressure: " + md.getPressure());
        response = new String(sb);
        return null;
    }

    private MeteoData getCurrentMeteoData(java.lang.String username, java.lang.String password, String address) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        com.foi.nwtis.lovmimica.logika.ws.SoapWS_Service service = new SoapWS_Service();
        com.foi.nwtis.lovmimica.logika.ws.SoapWS port = service.getSoapWSPort();
        return port.getAddressMeteoData(username, password, address);
    }
    
    

   
}
