/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.logika;

import com.foi.nwtis.lovmimica.bp.PresentUsers;
import java.util.Date;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author lovel_mimica
 */
public class EmailManager {
    private static String server = GeneralConfiguration.getEmailServer();
    private static String hostProperty = "mail.smtp.host";
    private static String tekstStandard = "ISO-8859-1";
    
    public static void sendMsg(String command, Date start){
        String receiver = GeneralConfiguration.getEmailReceiver();
        String sender = GeneralConfiguration.getEmailSender();
        String subject = GeneralConfiguration.getEmailSubject();
        System.out.println(receiver + ", " + sender + ", " + subject);
                
        
        int allUserCount = PresentUsers.getAllUserCount();
        int adminCount = PresentUsers.adminCount();
        int normalUserCount = PresentUsers.normalUserCount();
        
        String message = command + "\n" 
                + "Received request at: " + start.toString() + "\n"
                + "Total admins + users: " + allUserCount + "\n" 
                + "Total admins: " + adminCount + "\n" 
                + "Total users: " + normalUserCount;
         
        try {
        Properties properties = System.getProperties();
        properties.put(hostProperty, server);
        Session sesion = Session.getInstance(properties, null);
        MimeMessage msg = new MimeMessage(sesion);
        Address[] receivers = InternetAddress.parse(receiver);
        msg.setRecipients(Message.RecipientType.TO, receivers);
        msg.setSender(new InternetAddress(sender));
        msg.setSubject(subject);
        msg.setText(message,tekstStandard);
        msg.setSentDate(new Date());
        Transport.send(msg);
       
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
        
    }
}
