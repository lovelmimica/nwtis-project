/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.web.beans;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityExistsException;
import org.foi.nwtis.lovmimica.ejb.eb.User;
import org.foi.nwtis.lovmimica.ejb.sb.UserFacade;

/**
 *
 * @author lovelmimica
 */
@Named(value = "registrationBean")
@RequestScoped
public class RegistrationBean {
    
    private String username; 
    private String password;
    private String passwordRepeat;
    private boolean admin;
    
    @EJB
    private UserFacade userFacade;
    
    
    
    /**
     * Creates a new instance of RegistrationBean
     */
    public RegistrationBean() {
    }
    
    public String register() {
        User u = new User(username, password, 0, admin, false);
        try {
            userFacade.create(u);
        } catch (EntityExistsException e) {
            System.out.println(e.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
        return "index.xhtml";
    }

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

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    
}
