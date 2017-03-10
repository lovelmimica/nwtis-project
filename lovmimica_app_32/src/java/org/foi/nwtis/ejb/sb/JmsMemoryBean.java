/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ejb.sb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import org.foi.nwtis.ejb.jms.JmsArchive;
import org.foi.nwtis.ejb.jms.SerializabledJmsMessage;
/**
 *
 * @author lovelmimica
 */
@Singleton
@LocalBean
public class JmsMemoryBean {
    private List<SerializabledJmsMessage> receivedMsgs;

    public List<SerializabledJmsMessage> getReceivedMsgs() {
        return receivedMsgs;
    }

    public void setReceivedMsgs(List<SerializabledJmsMessage> receivedMsgs) {
        this.receivedMsgs = receivedMsgs;
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PostConstruct
    public void postConstruct(){
        receivedMsgs = new ArrayList<SerializabledJmsMessage>();
        try {
            //deserialize jms list
            receivedMsgs = new JmsArchive().readAll();
        } catch (Exception ex) {
            System.out.println("Iznimka: " + ex.toString());
        }
    }
    @PreDestroy
    public void preDestroy(){
        //serialize jms list
        try {
            JmsArchive archive = new JmsArchive();
            archive.saveJmsList(receivedMsgs);
        } catch (Exception ex) {
            System.out.println("Iznimka: " + ex.toString());
        }
    }
    
    public void addJmsMessage(TextMessage msg) throws JMSException{
        this.receivedMsgs.add(new SerializabledJmsMessage(msg));
    }
    public void removeJmsMessage(SerializabledJmsMessage msg){
        this.receivedMsgs.remove(msg);
    }
}
