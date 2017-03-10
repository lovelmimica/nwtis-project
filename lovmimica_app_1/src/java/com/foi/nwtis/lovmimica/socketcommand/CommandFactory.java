/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.socketcommand;

import com.foi.nwtis.lovmimica.socketcommand.admincommand.AddUser;
import com.foi.nwtis.lovmimica.socketcommand.admincommand.GetServerStatus;
import com.foi.nwtis.lovmimica.socketcommand.admincommand.PauseServer;
import com.foi.nwtis.lovmimica.socketcommand.admincommand.StartServer;
import com.foi.nwtis.lovmimica.socketcommand.admincommand.StopServer;
import com.foi.nwtis.lovmimica.socketcommand.admincommand.UpdateUserQuota;
import com.foi.nwtis.lovmimica.socketcommand.usercommand.AddAddress;
import com.foi.nwtis.lovmimica.socketcommand.usercommand.GetAddress;
import com.foi.nwtis.lovmimica.socketcommand.usercommand.TestAddress;

/**
 *
 * @author lovel_mimica
 */
public class CommandFactory {
    public static AbstractCommand createCommand(String[] commandString){
        if(syntaxCorrect(commandString) == false) return new InvalidCommand();
        
        if(commandString.length == 4) return new AutentificateUser();
         String order = commandString[4];
        
         //pause
        if (order.equalsIgnoreCase("PAUSE") && commandString.length == 5) return new PauseServer();
        //start
        if (order.equalsIgnoreCase("START") && commandString.length == 5) return new StartServer();
        //stop
        if (order.equalsIgnoreCase("STOP") && commandString.length == 5) return new StopServer();
        //status
        if (order.equalsIgnoreCase("STATUS") && commandString.length == 5) return new GetServerStatus();
        //add user...
        if (order.equalsIgnoreCase("ADD") && commandString.length == 10) {
            if (syntaxCorrectAddUser(commandString) == false) return new InvalidCommand();
            else return new AddUser();
        }
        //up/down user
        if ((order.equalsIgnoreCase("UP") || order.equalsIgnoreCase("DOWN"))
                && commandString.length  == 6) return new UpdateUserQuota();
        
        //test address
        if (order.equalsIgnoreCase("TEST") && commandString.length == 6) return new TestAddress();
        //get address
        if (order.equalsIgnoreCase("GET") && commandString.length == 6) return new GetAddress();
        //add address
        if (order.equalsIgnoreCase("ADD") && commandString.length == 6) return new AddAddress();
        
        //if nothing, return InvalidCommand
        return new InvalidCommand();
    }
    
    private static boolean syntaxCorrect(String[] command){
        int cmdLength = command.length; 
        if(cmdLength < 4) return false;
        if((command[0].equalsIgnoreCase("USER") == false)) return false;
        if (command[2].equalsIgnoreCase("PASSWD") == false) return false;
        
        return true;
    }
    
    private static boolean syntaxCorrectAddUser(String[] command){
        if (command[6].equalsIgnoreCase("PASSWD") == false) {
            return false;
        }
        if (command[8].equalsIgnoreCase("ROLE") == false) {
            return false;
        }
        if (command[9].equalsIgnoreCase("ADMIN") == false
                && command[9].equalsIgnoreCase("USER") == false) {
            return false;
        }
        return true;
    }
}
