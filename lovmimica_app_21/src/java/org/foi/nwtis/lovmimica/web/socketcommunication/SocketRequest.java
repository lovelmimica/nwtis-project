/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.web.socketcommunication;

import java.io.Serializable;

/**
 *
 * @author lovelmimica
 */
public class SocketRequest implements Serializable{
    private String username;
    private String password;
    private String command;

    public SocketRequest() {
    }

    public void setUsername(String username) {
        this.username = "USER " + username + "; ";
    }

    public void setPassword(String password) {
        this.password = "PASSWD " + password + "; ";
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return username + password + command;
    }
    
    
    
   
}
