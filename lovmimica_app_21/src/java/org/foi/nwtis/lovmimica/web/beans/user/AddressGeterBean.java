/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.web.beans.user;

import com.foi.nwtis.lovmimica.logika.ws.Address;
import com.foi.nwtis.lovmimica.logika.ws.SoapWS_Service;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.foi.nwtis.lovmimica.app.XmlParser;
import org.foi.nwtis.lovmimica.web.ws.rest.client.RestWsAddressClient;

/**
 *
 * @author lovelmimica
 */
@Named(value = "addressGeterBean")
@SessionScoped
public class AddressGeterBean implements Serializable {
    private String scope="all";
    

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
    
    /**
     * Creates a new instance of AddressGeterBean
     */
    public AddressGeterBean() {
    }
    
    public List<String> requestAddresses(){
        if(scope.equalsIgnoreCase("all")){
            return requestAllAddressesList();
        }else if(scope.equalsIgnoreCase("users")){
            return requestUsersAddresses();
        }
        return new ArrayList<String>();
    }
    
    private List<String> requestAllAddressesList(){
        RestWsAddressClient client = new RestWsAddressClient();
        String response = client.getAllAddresses(String.class);
        try {
            List<String> addressNames = XmlParser.parseAddres(response);
            return addressNames;
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.toString());
            return new ArrayList<String>();
        }
    }
    private List<String> requestUsersAddresses(){
        List<String> addressNames = new ArrayList<String>();
        List<Address> addressList = getUserAddresses("pero", "123");
        for(Address a : addressList){
            addressNames.add(a.getName());
        }
        return addressNames;
    }
     private java.util.List<com.foi.nwtis.lovmimica.logika.ws.Address> getUserAddresses(java.lang.String user, java.lang.String passwd) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        SoapWS_Service service = new SoapWS_Service();
        com.foi.nwtis.lovmimica.logika.ws.SoapWS port = service.getSoapWSPort();
        return port.getUsersAddresses(user, passwd);
    }
}
