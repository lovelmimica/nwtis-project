/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.web.beans.user;

import com.foi.nwtis.lovmimica.logika.ws.MeteoData;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.foi.nwtis.lovmimica.app.XmlParser;
import org.foi.nwtis.lovmimica.web.ws.rest.client.RestWsMeteoDataClient;

/**
 *
 * @author lovelmimica
 */
@Named(value = "meteoGeterBean")
@SessionScoped
public class MeteoGeterBean implements Serializable {

    
    private String address;
    private String type="weather";
    private String response;
    
    private List<MeteoData> tabularData = new ArrayList<MeteoData>();

    public List<MeteoData> getTabularData() {
        return tabularData;
    }

    public void setTabularData(List<MeteoData> tabularData) {
        this.tabularData = tabularData;
    }
    

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    /**
     * Creates a new instance of MeteoGeterBean
     */
    public MeteoGeterBean() {
    }
    
    public void requestAddressMeteo(){
        if(type.equalsIgnoreCase("weather")){
            List<MeteoData> meteoData = new ArrayList<MeteoData>();
            MeteoData md = requestCurrentMeteoData();
            meteoData.add(md);
            tabularData =  meteoData;
        }else if(type.equalsIgnoreCase("forecast")){
            tabularData = requestCurrentMeteoForecast();
        }else tabularData = new ArrayList<MeteoData>();
    }
    
    public MeteoData requestCurrentMeteoData(){
        MeteoData currentMeteoData = getAddressMeteoData("pero", "123", address);
        return currentMeteoData;
    }
    
    
     public List<MeteoData> requestCurrentMeteoForecast(){
        RestWsMeteoDataClient client = new RestWsMeteoDataClient();
        String response = client.getCurrentMeteoForecast(String.class, "pero", "123", address);
        try {
            return XmlParser.parseForecast(response);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.toString());
            return new ArrayList<MeteoData>();
        }        
    }

    private MeteoData getAddressMeteoData(java.lang.String username, java.lang.String password, java.lang.String addressName) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        com.foi.nwtis.lovmimica.logika.ws.SoapWS_Service service = new com.foi.nwtis.lovmimica.logika.ws.SoapWS_Service();
        com.foi.nwtis.lovmimica.logika.ws.SoapWS port = service.getSoapWSPort();
        return port.getAddressMeteoData(username, password, addressName);
    }
}
