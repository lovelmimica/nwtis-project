/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ejb.jms.consumers;

import java.util.Date;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.foi.nwtis.ejb.sb.JmsMemoryBean;

/**
 *
 * @author lovelmimica
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/nwtis_lovmimica_queue_1")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class JmsReportConsumer implements MessageListener {

    @EJB
    private JmsMemoryBean jmsMemoryBean;
    
    
    
    public JmsReportConsumer() {
    }
    
    @Override
    public void onMessage(Message message) {        
        try{
            TextMessage tm = (TextMessage)message;
            jmsMemoryBean.addJmsMessage(tm);
        }catch(Exception e){
            System.out.println(new Date() + " :" + e.toString());
        }
        
        
    }
    
}
