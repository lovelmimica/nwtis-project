/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.web.beans.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import org.foi.nwtis.lovmimica.ejb.eb.User;
import org.foi.nwtis.lovmimica.ejb.sb.UserFacade;
import org.foi.nwtis.lovmimica.web.socketcommunication.SocketHelper;
import org.foi.nwtis.lovmimica.web.socketcommunication.requestbuilder.AddUserBuilder;
import org.foi.nwtis.lovmimica.web.socketcommunication.requestbuilder.SocketRequestBuilder;
import org.foi.nwtis.lovmimica.web.socketcommunication.requestbuilder.UpdateUserCategoryBuilder;

/**
 *
 * @author lovelmimica
 */
@Named(value = "userManagementBean")
@RequestScoped
public class UserManagementBean {

    @EJB
    private UserFacade userFacade;

    private String username;
    private String response; 

    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String socketResponse) {
        this.response = socketResponse;
    }
    
    
    
    /**
     * Creates a new instance of RegistrationHandlingBean
     */
    public UserManagementBean() {
    }
    
    public List<User> findPendingUsers(){
        List<User> allUsers =  userFacade.findAll();
        List<User> pendingUsers = new ArrayList<User>();
        for(User u : allUsers){
            if(u.getApproved() == false)pendingUsers.add(u);
        }
        return pendingUsers;
    }
    
    public List<User> findApprovedUsers(){
        List<User> allUsers =  userFacade.findAll();
        List<User> approvedUsers = new ArrayList<User>();
        for(User u : allUsers){
            if(u.getApproved() == true)approvedUsers.add(u);
        }
        return approvedUsers;
    }
    
    public String approveUser(){
        //salji socket request
        try {
            User approvedUser = userFacade.find(this.username);
            String role = approvedUser.getAdmin() ? "ADMIN" : "USER";
            String[] args = {"pero", "123", this.username, approvedUser.getPassword(),role};
            SocketRequestBuilder builder = new AddUserBuilder(args);
            builder.createRequest();
            response = SocketHelper.sendRequest(builder.getRequest().toString(), "localhost", 8888);
        } catch (Exception ex) {
            response = ex.toString();
        }
        return null;
    }
    public String rejectUser(){
        //azuriraj bazu podataka
        User rejectedUser = userFacade.find(this.username);
        userFacade.remove(rejectedUser);
        response = "Izbrisan korisnik :" + username;
        return null;
    }
    
    public String upUser(){
        try {
            String[] args = {"pero", "123", "UP", this.username};
            SocketRequestBuilder builder = new UpdateUserCategoryBuilder(args);
            builder.createRequest();
            response = SocketHelper.sendRequest(builder.getRequest().toString(), "localhost", 8888);
        } catch (Exception ex) {
            response = ex.toString();
        }
        return null;
    }
    public String downUser(){
        try {
            String[] args = {"pero", "123", "DOWN", this.username};
            SocketRequestBuilder builder = new UpdateUserCategoryBuilder(args);
            builder.createRequest();
            response = SocketHelper.sendRequest(builder.getRequest().toString(), "localhost", 8888);
        } catch (Exception ex) {
            response = ex.toString();
        }
        return null;
    }
}
