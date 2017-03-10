/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.web.beans.user;

import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import org.foi.nwtis.lovmimica.jms.messages.JmsAddAddresMessage;


/**
 *
 * @author lovelmimica
 */
@Named(value = "userCommandBean")
@RequestScoped
public class UserCommandBean{
    
    private String addressScope;

    public String getAddressScope() {
        return addressScope;
    }

    public void setAddressScope(String addressScope) {
        this.addressScope = addressScope;
    }
    
    @Resource(mappedName = "jms/nwtis_lovmimica_queue_2")
    private Queue nwtis_lovmimica_queue_2;

    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;
    
    private String addressName;
    private String response;
    /**
     * Creates a new instance of UserCommandBean
     */
    public UserCommandBean() {
    }

    public String getReponse() {
        return response;
    }

    public void setReponse(String socketReponse) {
        this.response = socketReponse;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }
 
    public String addAddress(){
        String[] args = {"pero", "123", this.addressName};
        try {
            
            /*SocketRequestBuilder builder = new AddAddressBuilder(args);
            builder.createRequest();*/
            JmsAddAddresMessage jmsMsg = new JmsAddAddresMessage(args);
            
            sendJMSMessageToNwtis_lovmimica_queue_2(jmsMsg.toString());
            
            response = "JMS sent!";
            
        } catch (Exception ex) {
            response = ex.getMessage();
        }
        return null;
    }
    
    public String testRefresh(){
        return null;
    }

    private void sendJMSMessageToNwtis_lovmimica_queue_2(String messageData) {
        context.createProducer().send(nwtis_lovmimica_queue_2, messageData);
    }
    
    
}
