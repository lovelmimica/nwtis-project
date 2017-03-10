/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.threads;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.foi.nwtis.lovmimica.ejb.eb.User;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.foi.nwtis.lovmimica.app.AppStatus;
import org.foi.nwtis.lovmimica.ejb.sb.UserFacade;
import org.foi.nwtis.lovmimica.jms.messages.JmsReportMessage;
import org.foi.nwtis.lovmimica.web.email.filters.Filter;
import org.foi.nwtis.lovmimica.web.email.filters.FilterCorrect;
import org.foi.nwtis.lovmimica.web.email.filters.FilterNwtis;
import org.foi.nwtis.lovmimica.web.email.filters.FilterUnread;
import org.foi.nwtis.lovmimica.web.email.filters.LoadAll;

/**
 *
 * @author lovelmimica
 */
public class EmailHandling extends Thread{
    private JmsReportMessage jmsMessage;
    
    @EJB
    UserFacade userFacade = lookupUserFacadeBean();
    
    private UserFacade lookupUserFacadeBean() {
        try {
            Context c = new InitialContext();
            return (UserFacade) c.lookup("java:global/lovmimica_aplikacija_2/lovmimica_aplikacija_22/UserFacade!org.foi.nwtis.lovmimica.ejb.sb.UserFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    @Override
    public void run() {
        super.run();
        while (AppStatus.isRunning()) {
            jmsMessage = new JmsReportMessage();

            Date threadStart = new Date();
            long start = threadStart.getTime();
            jmsMessage.setThreadStart(threadStart);

            List<MimeMessage> messageList = new ArrayList<MimeMessage>();
            Filter loadAll = new LoadAll();
            loadAll.doFilter(messageList);

            Filter filterUnread = new FilterUnread();
            filterUnread.doFilter(messageList);

            jmsMessage.setReadMsgCount(messageList.size());

            Filter filterNwtis = new FilterNwtis();
            filterNwtis.doFilter(messageList);

            jmsMessage.setNwtisMsgCount(messageList.size());

            Filter filterCorrect = new FilterCorrect();
            filterCorrect.doFilter(messageList);

            jmsMessage.setUnapprovedUsersCount(getUnapprovedUsersCount());
            jmsMessage.setApprovedUsersCount(getApprovedUsersCount());

            Date threadEnd = new Date();
            jmsMessage.setThreadEnd(threadEnd);

            long end = threadEnd.getTime();

            try {
                sendJMSMessageToNwtis_lovmimica_queue_1(jmsMessage.toString());
                        
            } catch (Exception ex) {
                System.out.println(new Date() + ": Exception: " + ex.toString());
            }
            try {
                wait(start, end, 10000);
            } catch (Exception ex) {

            }
        }

    }

    
    
    
    private void wait(long start, long end, long interval) throws Exception{
        long waitMils = end - start;
        waitMils = interval - waitMils;
        if(waitMils > 0 ) sleep(waitMils);
    }
    
    private Integer getApprovedUsersCount(){
        List<User> allUsers = userFacade.findAll();
        Integer counter = 0;
        for(User u : allUsers){
            if(u.getApproved()) counter++;
        }
        return counter;
    }
    private Integer getUnapprovedUsersCount(){
        List<User> allUsers = userFacade.findAll();
        Integer counter = 0;
        for(User u : allUsers){
            if(u.getApproved() == false) counter++;
        }
        return counter;
    }

    private Message createJMSMessageForjmsNwtis_lovmimica_queue_1(Session session, String messageData) throws JMSException {
        TextMessage tm = session.createTextMessage(messageData);
        return tm;
    }

    private void sendJMSMessageToNwtis_lovmimica_queue_1(String messageData) throws JMSException, NamingException {
        Context c = new InitialContext();
        ConnectionFactory cf = (ConnectionFactory) c.lookup("java:comp/DefaultJMSConnectionFactory");
        Connection conn = null;
        Session s = null;
        try {
            conn = cf.createConnection();
            s = conn.createSession(false, s.AUTO_ACKNOWLEDGE);
            Destination destination = (Destination) c.lookup("java:comp/env/jms/nwtis_lovmimica_queue_1");
            MessageProducer mp = s.createProducer(destination);
            mp.send(createJMSMessageForjmsNwtis_lovmimica_queue_1(s, messageData));
        } finally {
            if (s != null) {
                try {
                    s.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    
}
