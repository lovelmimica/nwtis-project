/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.app;

import java.util.ArrayList;
import java.util.List;
import org.foi.nwtis.lovmimica.web.beans.ActiveUserBean;

/**
 *
 * @author lovelmimica
 */
public class ActiveUserRecordStatic {
    private static List<ActiveUserBean> activeUsers = new ArrayList<ActiveUserBean>();
    /**
     * Creates a new instance of ActiveUsersRecord
     */
    public static synchronized void addActiveUser(ActiveUserBean user){
        if(activeUsers.contains(user) == false)activeUsers.add(user);
    }
    public static synchronized void removeActiveUser(ActiveUserBean user){
        if(activeUsers.contains(user)) activeUsers.remove(user);
    }
    
    public static synchronized String printUsers(){
        StringBuilder sb = new StringBuilder();
        for(ActiveUserBean user : activeUsers){
            sb.append("Username: " + user.getUsername() + "; Password: " + user.getPassword() + "\n");
        }
        return new String(sb);
    }
    public static synchronized List<ActiveUserBean> getActiveUsers(){
        List<ActiveUserBean> newList = new ArrayList<ActiveUserBean>();
        newList.addAll(activeUsers);
        return newList;     
    }
}
