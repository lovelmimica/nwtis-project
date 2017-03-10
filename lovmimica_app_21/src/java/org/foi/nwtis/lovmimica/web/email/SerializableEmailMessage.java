/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.web.email;

import java.io.IOException;
import javax.mail.internet.MimeMessage;
import java.io.InputStream;
import java.io.Serializable;
import javax.mail.MessagingException;

/**
 *
 * @author lovelmimica
 */
public class SerializableEmailMessage implements Serializable{
    private String subject; 
    private String sender;
    private String content;
    
    public String getSubject() {
        return subject;
    }

    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }
    
    public SerializableEmailMessage(MimeMessage source) throws Exception {
        this.subject = source.getSubject();
        this.sender = source.getSender().toString();
        this.content = source.getContent().toString();
    }

    public SerializableEmailMessage() {
    }

    
    
    
    

    
    

    
    
}
