/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.jms.messages;

import java.io.Serializable;
import org.foi.nwtis.lovmimica.web.socketcommunication.SocketRequest;
import org.foi.nwtis.lovmimica.web.socketcommunication.requestbuilder.AddAddressBuilder;
import org.foi.nwtis.lovmimica.web.socketcommunication.requestbuilder.SocketRequestBuilder;
import org.foi.nwtis.lovmimica.web.socketcommunication.requestbuilder.TestAddressBuilder;

/**
 *
 * @author lovelmimica
 */
public class JmsAddAddresMessage implements Serializable{
    private SocketRequest addAddress;
    private SocketRequest testAddress;

    public SocketRequest getAddAddress() {
        return addAddress;
    }

    public void setAddAddress(SocketRequest addAddress) {
        this.addAddress = addAddress;
    }

    public SocketRequest getTestAddress() {
        return testAddress;
    }

    public void setTestAddress(SocketRequest testAddress) {
        this.testAddress = testAddress;
    }

    public JmsAddAddresMessage(String[] args) throws Exception {
        SocketRequestBuilder builder = new AddAddressBuilder(args);
        builder.createRequest();
        addAddress = builder.getRequest();
        
        builder = new TestAddressBuilder(args);
        builder.createRequest();
        testAddress = builder.getRequest();
    }

    @Override
    public String toString() {
        return addAddress.toString();
    }
    
    
       
}
