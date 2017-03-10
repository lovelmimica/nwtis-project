/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.web.beans;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import org.foi.nwtis.lovmimica.app.ActiveUserRecordStatic;

/**
 *
 * @author lovelmimica
 */
@Named(value = "activeUsersRecord")
@ApplicationScoped
public class ActiveUsersRecord {
    private List<ActiveUserBean> activeUsers;
    /**
     * Creates a new instance of ActiveUsersRecord
     */
    public ActiveUsersRecord() {
        activeUsers = new ArrayList<ActiveUserBean>();
    }
    
    public synchronized void addActiveUser(ActiveUserBean user){
        if(this.activeUsers.contains(user) == false)this.activeUsers.add(user);
    }
    public synchronized void removeActiveUser(ActiveUserBean user){
        if(this.activeUsers.contains(user)) this.activeUsers.remove(user);
    }
    
    public synchronized String printUsers(){
        return ActiveUserRecordStatic.printUsers();
    }
    
    
}
