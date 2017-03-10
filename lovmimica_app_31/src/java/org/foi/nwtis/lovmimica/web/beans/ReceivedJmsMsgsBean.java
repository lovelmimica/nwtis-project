/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.web.beans;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.foi.nwtis.ejb.jms.JmsArchive;
import org.foi.nwtis.ejb.jms.SerializabledJmsMessage;
import org.foi.nwtis.ejb.sb.JmsMemoryBean;
/**
 *
 * @author lovelmimica
 */
@Named(value = "receivedJmsMsgsBean")
@RequestScoped
public class ReceivedJmsMsgsBean {

    @EJB
    private JmsMemoryBean jmsMemoryBean;
    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
    
    public List<SerializabledJmsMessage> findAllJms(){
        return jmsMemoryBean.getReceivedMsgs();
    }
    
    public String deleteJms(SerializabledJmsMessage jms){
        jmsMemoryBean.removeJmsMessage(jms);
        return null;
    }
    
    /**
     * Creates a new instance of JmsArchiveBean
     */
    public ReceivedJmsMsgsBean() {
    }
    
    public String readArchive(){
        try {
            JmsArchive archive = new JmsArchive();
            List<SerializabledJmsMessage> archiveList = archive.readAll();
            response = archiveList.toString();       
        } catch (Exception ex) {
            response = ex.toString();
        }  
        return null;
    }
    
    public String readAppMemory(){
        response = jmsMemoryBean.getReceivedMsgs().toString();
        
        return null;
    }
    
}
