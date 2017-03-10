/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.socketcommand.admincommand;

import com.foi.nwtis.lovmimica.bp.PresentUsers;
import com.foi.nwtis.lovmimica.datatypes.User;
import com.foi.nwtis.lovmimica.logika.EmailManager;
import com.foi.nwtis.lovmimica.socketcommand.responses.ErrorResponse;
import com.foi.nwtis.lovmimica.socketcommand.responses.OkResponse;
import com.foi.nwtis.lovmimica.socketcommand.responses.Response;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lovel_mimica
 */
public class AddUser extends AdminCommand{

    @Override
    public Response concreteExecute(String[] cmdArray) {        
        boolean exists = PresentUsers.exists(cmdArray[5]);
        if(exists == true) return new ErrorResponse(33);
        
        //if user does not exists
        String userName = cmdArray[5];
        String password = cmdArray[7];
        boolean isAdmin = cmdArray[9].equalsIgnoreCase("admin");
        
        try {
            User user = new User(userName, password, 0, isAdmin);
            PresentUsers.add(user);
            EmailManager.sendMsg(this.toString(cmdArray), new Date());
            return new OkResponse(10);
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
        return new ErrorResponse(102);
    }

    public String toString(String[] cmdArray) {
        return cmdArray[4] + " " + cmdArray[5] + "; " + cmdArray[6] + " " + cmdArray[7] + "; " 
                + cmdArray[8] + " " + cmdArray[9] + ";";
     }
    
}
