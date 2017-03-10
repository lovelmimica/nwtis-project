/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.web.email.filters;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author lovelmimica
 */
public class FilterNwtis implements Filter{

    @Override
    public void doFilter(List<MimeMessage> messageList) {
        List<MimeMessage> nwtisMsgList = new ArrayList<MimeMessage>(); 
        for(MimeMessage m : messageList){
            try {
                String subject = m.getSubject();
                String type = m.getContentType();
                
                if(subject.equalsIgnoreCase("izvjestaj") && type.contains("text/plain")){
                    //otkrivena nwtis poruka
                    nwtisMsgList.add(m);
                }
            } catch (MessagingException ex) {
                System.out.println(new Date()+ ": " + ex.toString());
            }
        }
        messageList.clear();
        messageList.addAll(nwtisMsgList);
    }
    
}
