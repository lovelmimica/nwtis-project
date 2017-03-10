/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ejb.jms;

import java.io.Serializable;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 *
 * @author lovelmimica
 */
public class SerializabledJmsMessage implements Serializable{
    private String message;

    public SerializabledJmsMessage(TextMessage message) throws JMSException {
        this.message = message.getText();
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
    
    
   
    
}
