/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.web.socketcommunication.requestbuilder;

import org.foi.nwtis.lovmimica.web.socketcommunication.SocketRequest;

/**
 *
 * @author lovelmimica
 */
public abstract class SocketRequestBuilder {
    protected abstract void createCommand();
    protected abstract boolean argsVerification();
    
    protected String[] args;
    protected SocketRequest request;

    public SocketRequest getRequest() {
        return request;
    }
    
    public SocketRequestBuilder(String[] args) throws Exception {
        this.args = args;
        if(argsVerification() == false) throw new Exception("Params not correct");
        request = new SocketRequest();
    }
    
    public void createRequest(){
        createRequester();
        createCommand();
    }
    
    private void createRequester(){
        request.setUsername(args[0]);
        request.setPassword(args[1]);
    }
    
    
}
