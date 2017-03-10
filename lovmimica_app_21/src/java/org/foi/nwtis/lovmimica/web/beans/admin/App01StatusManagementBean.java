/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.web.beans.admin;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.foi.nwtis.lovmimica.web.socketcommunication.SocketHelper;
import org.foi.nwtis.lovmimica.web.socketcommunication.requestbuilder.GetServerStatusBuilder;
import org.foi.nwtis.lovmimica.web.socketcommunication.requestbuilder.PauseServerBuilder;
import org.foi.nwtis.lovmimica.web.socketcommunication.requestbuilder.SocketRequestBuilder;
import org.foi.nwtis.lovmimica.web.socketcommunication.requestbuilder.StartServerBuilder;
import org.foi.nwtis.lovmimica.web.socketcommunication.requestbuilder.StopServerBuilder;

/**
 *
 * @author lovelmimica
 */
@Named(value = "app01StatusManagementBean")
@RequestScoped
public class App01StatusManagementBean {
    private String socketResponse;

    public String getSocketResponse() {
        return socketResponse;
    }

    public void setSocketResponse(String socketResponse) {
        this.socketResponse = socketResponse;
    }

    /**
     * Creates a new instance of AdminCommandBean
     */
    public App01StatusManagementBean() {
    }
    
    public String requestPause(){
        String[] args = {"pero", "123"};
        try {
            SocketRequestBuilder builder = new PauseServerBuilder(args);
            builder.createRequest();
            socketResponse = SocketHelper.sendRequest(builder.getRequest().toString(), "localhost", 8888);
        } catch (Exception ex) {
            socketResponse = ex.toString();
        }
        return null;
    }
    public String requestStop(){
        String[] args = {"pero", "123"};
        try {
            SocketRequestBuilder builder = new StopServerBuilder(args);
            builder.createRequest();
            socketResponse = SocketHelper.sendRequest(builder.getRequest().toString(), "localhost", 8888);
        } catch (Exception ex) {
            socketResponse = ex.getMessage();
        }
        return null;
    }
    public String requestGetStatus(){
        String[] args = {"pero", "123"};
        try {
            SocketRequestBuilder builder = new GetServerStatusBuilder(args);
            builder.createRequest();
            socketResponse = SocketHelper.sendRequest(builder.getRequest().toString(), "localhost", 8888);
        } catch (Exception ex) {
            socketResponse = ex.getMessage();
        }
        return null;
    }
    public String requestStart(){
        String[] args = {"pero", "123"};
        try {
            SocketRequestBuilder builder = new StartServerBuilder(args);
            builder.createRequest();
            socketResponse = SocketHelper.sendRequest(builder.getRequest().toString(), "localhost", 8888);
        } catch (Exception ex) {
            socketResponse = ex.getMessage();
        }
        return null;
    }
    
    
}
