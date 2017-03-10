/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.web.beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import org.foi.nwtis.lovmimica.app.ActiveUserRecordStatic;
import org.foi.nwtis.lovmimica.ejb.sb.RequestFacade;
import org.foi.nwtis.lovmimica.ejb.eb.User;
import org.foi.nwtis.lovmimica.ejb.sb.UserFacade;

/**
 *
 * @author lovelmimica
 */
@Named(value = "activeUserBean")
@SessionScoped
public class ActiveUserBean implements Serializable {

    @EJB
    private org.foi.nwtis.lovmimica.ejb.sb.UserFacade userFacade;
 
    private String username ="nonLogedUser"; 
    private String password = "empty";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ActiveUserBean() {
    }
    
    public String login(){
         try {
            User u = userFacade.find(username);
            if (u != null && u.getPassword().equalsIgnoreCase(password) && u.getApproved().equals(true)) {
                reportActivation();
                if(u.getAdmin().equals(true)){
                    return "admin-gui.xhtml";
                }
                else return "user-gui.xhtml";
            }
            System.out.println(new Date() + " Incorrect log in credentials: " + username + ";" + password + ";" );
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }    
        return null;
    }
    @PreDestroy
    public void preDestroy(){
        reportDeactivation();
    }
    
    private void reportActivation(){
        ActiveUserRecordStatic.addActiveUser(this);
    }
    private void reportDeactivation(){
        ActiveUserRecordStatic.removeActiveUser(this);
    }
  
}
