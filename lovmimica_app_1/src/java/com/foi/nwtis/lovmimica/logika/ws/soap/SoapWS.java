/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.logika.ws.soap;

import com.foi.nwtis.lovmimica.bp.PresentAddresses;
import com.foi.nwtis.lovmimica.bp.PresentUsers;
import com.foi.nwtis.lovmimica.bp.PresentMeteoData;
import com.foi.nwtis.lovmimica.bp.PresentWsRequests;
import com.foi.nwtis.lovmimica.datatypes.Address;
import com.foi.nwtis.lovmimica.datatypes.MeteoData;
import com.foi.nwtis.lovmimica.datatypes.AddressFrequency;
import com.foi.nwtis.lovmimica.datatypes.WsRequest;
import com.foi.nwtis.lovmimica.logika.ws.InitialWsActions;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author lovel_mimica
 */
@WebService(serviceName = "SoapWS")
public class SoapWS {
    
    private static final String WS_TIP = "SOAP";
    private static final String WS_NAZIV = "SOAP Web Servis";
    

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAddressMeteoData")
    public MeteoData getAddressMeteoData(@WebParam(name = "username") String username, @WebParam(name = "password") String password, @WebParam(name = "addressName") String addressName) {
        Integer addressId = PresentAddresses.getId(addressName);        
        return getCurrentMeteoData(username, password, addressId);
    }
    
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "getCurrentMeteoData")
    public MeteoData getCurrentMeteoData(@WebParam(name = "user") String user, @WebParam(name = "password") String password, @WebParam(name = "adresaId") int addressId) {
        boolean odobreno = InitialWsActions.execute(user, password);
        if(odobreno){
            Date start = new Date();
            MeteoData currentMd = PresentMeteoData.getAddressCurrentMeteoData(addressId);
            Date end = new Date();
            
            int userId = PresentUsers.getId(user);
            String wsType=  WS_TIP;
            String wsName = WS_NAZIV;
            String request = "getCurrentMeteoData";
            WsRequest wsRequest = new WsRequest(userId, wsType, wsName, start, end, request);
            PresentWsRequests.add(wsRequest);
            
            return currentMd;
        }
        return null;
    }
    /**
     * Web service operation
     */
    @WebMethod(operationName = "getUsersAddresses")
    public List<Address> getUsersAddresses(@WebParam(name = "user") String user, @WebParam(name = "password") String password) {
        boolean granted = InitialWsActions.execute(user, password);
        if(granted){
            Date start = new Date();
            int userId = PresentUsers.getId(user);
            List<Address> usersAddressesList = PresentAddresses.getUserAddresses(userId);
            Date end = new Date();
            
            String wsType = WS_TIP;
            String wsName = WS_NAZIV;
            String request = "getUsersAddresses";
            WsRequest wsRequest = new WsRequest(userId, wsType, wsName, start, end, request);
            PresentWsRequests.add(wsRequest);
            
            return usersAddressesList;
        }
        return null;
    }


    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAddressRankList")
    public List<AddressFrequency> getAddressRankList(@WebParam(name = "user") String user, @WebParam(name = "password") String password, @WebParam(name = "n") int n) {
        boolean odobreno = InitialWsActions.execute(user, password);
        if(odobreno){
            Date start = new Date();
            List<AddressFrequency> rankList = PresentAddresses.getAddressRangList(n);
            Date end = new Date();
            
            int userId = PresentUsers.getId(user); 
            String wsType = WS_TIP;
            String wsName=  WS_NAZIV;
            String request = "getAddressRankList";
            WsRequest wsRequest = new WsRequest(userId, wsType, wsName, start, end, request);
            PresentWsRequests.add(wsRequest);
            
            return rankList;
        }
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAddressesMeteoData")
    public List<MeteoData> getAddressesMeteoData(@WebParam(name = "user") String user, @WebParam(name = "password") String password, @WebParam(name = "addressId") int addressId, @WebParam(name = "n") int n) {
        boolean grant = InitialWsActions.execute(user, password);
        if(grant){
            Date pocetak = new Date();
            List<MeteoData> addressMeteoDataList = PresentMeteoData.getAddressCountedMeteoData(addressId, n);
            Date kraj = new Date();
            
            int userId = PresentUsers.getId(user);
            String wsType = WS_TIP;
            String wsName = WS_NAZIV;
            String request = "getAddressesMeteoData";
            WsRequest wsRequest = new WsRequest(userId, wsType, wsName, pocetak, kraj, request);
            PresentWsRequests.add(wsRequest); 
            
            return addressMeteoDataList;
        }
        return null;
    }
    /**
     * Web service operation
     */
    

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAddressIntervaledMeteoData")
    public List<MeteoData> getAddressIntervaledMeteoData(@WebParam(name = "user") String user, @WebParam(name = "password") String password, @WebParam(name = "addressId") int addressId, @WebParam(name = "start") Date start, @WebParam(name = "end") Date end) {
        boolean odobreno = InitialWsActions.execute(user, password);
        if(odobreno){
            Date processingStart = new Date();
            List<MeteoData> addressMeteoData = PresentMeteoData.getAddressIntervaledMeteoData(addressId, processingStart, end);
            Date processingEnd = new Date();
            
            int userId = PresentUsers.getId(user);
            String wsType = WS_TIP;
            String wsName = WS_NAZIV;
            String request = "getAddressIntervaledMeteoData";
            WsRequest wsRequest = new WsRequest(userId, wsType, wsName, processingStart, processingEnd, request);
            PresentWsRequests.add(wsRequest); 
            
            return addressMeteoData;
        }
        return null;
    }

}
