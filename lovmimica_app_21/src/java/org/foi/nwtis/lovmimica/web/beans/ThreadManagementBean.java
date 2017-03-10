/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.web.beans;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.PreDestroy;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import org.foi.nwtis.lovmimica.app.AppStatus;
import org.foi.nwtis.lovmimica.threads.EmailHandling;

/**
 *
 * @author lovelmimica
 */
@Named(value = "threadManagementBean")
@ApplicationScoped
public class ThreadManagementBean implements Serializable{
    EmailHandling thread = null;
    /**
     * Creates a new instance of ThreadManagementBean
     */
    public ThreadManagementBean() {
    }
    
    public String startThreads(){
        if(thread == null){
            AppStatus.start();
            thread = new EmailHandling();
            thread.run();
        }
        return null;
    }
    public String stopThreads(){
        
        if(thread != null){
            AppStatus.stop();
            thread.interrupt();
            thread = null;
        }
        
        return null;
    }
    @PreDestroy
    public void preDestroy(){
        stopThreads();
    }
    
    
}
