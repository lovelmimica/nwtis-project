/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.web.email;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author lovelmimica
 */
public class EmailHelper {
    private static final String EMAIL_HOST_KEY = "mail.smtp.host";
    private static final String PROTOCOL = "pop3";
    private static final String INBOX = "INBOX";
    
    public static List<MimeMessage> loadAllMessages(String server, String receiver, String password) throws Exception{
        Properties properties = System.getProperties();
        properties.put(EMAIL_HOST_KEY, server);
        Session session = Session.getInstance(properties);
        Store store = session.getStore(PROTOCOL);
        store.connect(server, receiver, password);
        Folder folder = store.getFolder(INBOX);
        folder.open(Folder.READ_ONLY);
        Message[] msgs = folder.getMessages();
        List<MimeMessage> mimeMsgList = new ArrayList<MimeMessage>();
        for(Message m : msgs){
            mimeMsgList.add((MimeMessage)m);
        }
        return mimeMsgList;
    }
}
