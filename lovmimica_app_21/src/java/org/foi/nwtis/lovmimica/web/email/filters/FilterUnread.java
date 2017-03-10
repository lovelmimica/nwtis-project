/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.web.email.filters;

import java.util.ArrayList;
import java.util.List;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author lovelmimica
 */
public class FilterUnread implements Filter{
    private static int readMsgsCount = 0;
    @Override
    public void doFilter(List<MimeMessage> messageList) {
        List<MimeMessage> unreadMsgList = new ArrayList<MimeMessage>();
        int listEnd = messageList.size();
        unreadMsgList.addAll(messageList.subList(readMsgsCount, listEnd));
        
        readMsgsCount += unreadMsgList.size();
        messageList.clear();
        messageList.addAll(unreadMsgList);    
    }
    
}
