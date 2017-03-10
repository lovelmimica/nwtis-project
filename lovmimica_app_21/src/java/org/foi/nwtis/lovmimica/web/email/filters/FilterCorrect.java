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
import javax.ejb.EJB;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.foi.nwtis.lovmimica.ejb.eb.User;
import org.foi.nwtis.lovmimica.ejb.sb.UserFacade;
import org.foi.nwtis.lovmimica.web.email.EmailArchiveFactory;

/**
 *
 * @author lovelmimica
 */
public class FilterCorrect implements Filter{


    @EJB
    UserFacade userFacade = lookupUserFacadeBean();
    
    
    
    @Override
    public void doFilter(List<MimeMessage> messageList) {
        System.out.println("FilterCorrect.doFilter()");
        List<MimeMessage> correctMsgList = new ArrayList<MimeMessage>();
        for (MimeMessage m : messageList) {
            try {
                String content = m.getContent().toString();
                String[] cmdArray = getCmdArray(content);
                if (commandCorrect(cmdArray)) {
                    //correct
                    String username = cmdArray[1];
                    User u = userFacade.find(username);
                    if (u != null && u.getApproved() == false) {
                        //successful
                        u.setApproved(true);
                        userFacade.edit(u);
                        EmailArchiveFactory.getEmailArchive("successful").writeEmail(m);
                    } else {
                        //unsuccessful
                        EmailArchiveFactory.getEmailArchive("unsuccessful").writeEmail(m);
                    }
                } else {
                    //incorrect
                    EmailArchiveFactory.getEmailArchive("incorrect").writeEmail(m);
                }

                System.out.println("Content: " + content);
            } catch (Exception ex) {
                System.out.println(new Date() + ": " + ex.toString());
            }
        }
    }
    
    private String[] getCmdArray(String command){

        String firstLine = (command.split("\n"))[0];
        String[] rawCmdArray = firstLine.split(" ");
        String[] pureCmdArray = new String[rawCmdArray.length];
        
        
        for(int i = 0; i<rawCmdArray.length; i++){
            String element = rawCmdArray[i];
            element = element.replace(";", "");
            element = element.trim();
            pureCmdArray[i] = element;
        }
        return pureCmdArray;
    }
    private boolean commandCorrect(String[] cmdArray){
        if(cmdArray.length != 6) return false;
        if(cmdArray[0].equalsIgnoreCase("ADD") == false) return false;
        if(cmdArray[2].equalsIgnoreCase("PASSWD") == false) return false;
        if(cmdArray[4].equalsIgnoreCase("ROLE") == false) return false;
        if((cmdArray[5].equalsIgnoreCase("ADMIN") == false) && (cmdArray[5].equalsIgnoreCase("USER") == false)) return false;
        String username = cmdArray[1];
        User u = userFacade.find(username);
        if(u == null || u.getApproved() == true) return false; 
        
        return true;
    }

    private UserFacade lookupUserFacadeBean() {
        try {
            Context c = new InitialContext();
            return (UserFacade) c.lookup("java:global/lovmimica_aplikacija_2/lovmimica_aplikacija_22/UserFacade!org.foi.nwtis.lovmimica.ejb.sb.UserFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

  
    
}
