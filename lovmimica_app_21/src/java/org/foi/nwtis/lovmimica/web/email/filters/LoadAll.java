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
import javax.mail.internet.MimeMessage;
import org.foi.nwtis.lovmimica.web.email.EmailHelper;

/**
 *
 * @author lovelmimica
 */
public class LoadAll implements Filter{

    @Override
    public void doFilter(List<MimeMessage> messageList) {
        String server = "localhost";
        String receiver = "pele@nwtis.nastava.foi.hr";
        String password = "123";
        
        try {
            messageList.addAll(EmailHelper.loadAllMessages(server, receiver, password));
        } catch (Exception ex) {
            System.out.println(new Date() + " " + ex.toString());
        }
    }
    
}
