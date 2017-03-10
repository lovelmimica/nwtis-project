/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.jms.messages;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author lovelmimica
 */
public class JmsReportMessage implements Serializable{
    private Date threadStart;
    private Date threadEnd;
    private Integer readMsgCount;
    private Integer nwtisMsgCount;
    private Integer approvedUsersCount;
    private Integer unapprovedUsersCount;

    public Date getThreadStart() {
        return threadStart;
    }

    public void setThreadStart(Date threadStart) {
        this.threadStart = threadStart;
    }

    public Date getThreadEnd() {
        return threadEnd;
    }

    public void setThreadEnd(Date threadEnd) {
        this.threadEnd = threadEnd;
    }

    public Integer getReadMsgCount() {
        return readMsgCount;
    }

    public void setReadMsgCount(Integer readMsgCount) {
        this.readMsgCount = readMsgCount;
    }

    public Integer getNwtisMsgCount() {
        return nwtisMsgCount;
    }

    public void setNwtisMsgCount(Integer nwtisMsgCount) {
        this.nwtisMsgCount = nwtisMsgCount;
    }

    public Integer getApprovedUsersCount() {
        return approvedUsersCount;
    }

    public void setApprovedUsersCount(Integer approvedUsersCount) {
        this.approvedUsersCount = approvedUsersCount;
    }

    public Integer getUnapprovedUsersCount() {
        return unapprovedUsersCount;
    }

    public void setUnapprovedUsersCount(Integer unapprovedUsersCount) {
        this.unapprovedUsersCount = unapprovedUsersCount;
    }

    
        
    public JmsReportMessage() {
    }

    @Override
    public String toString() {
        return "JmsReportMessage{" + "threadStart=" + threadStart + ", threadEnd=" + threadEnd + ", readMsgCount=" + readMsgCount + ", nwtisMsgCount=" + nwtisMsgCount + ", approvedUsersCount=" + approvedUsersCount + ", unapprovedUsersCount=" + unapprovedUsersCount + '}';
    }

  
    
    
}
