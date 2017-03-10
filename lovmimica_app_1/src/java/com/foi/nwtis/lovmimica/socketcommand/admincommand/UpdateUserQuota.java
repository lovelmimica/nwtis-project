/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.socketcommand.admincommand;

import com.foi.nwtis.lovmimica.bp.PresentUsers;
import com.foi.nwtis.lovmimica.socketcommand.responses.OkResponse;
import com.foi.nwtis.lovmimica.socketcommand.responses.Response;
import com.foi.nwtis.lovmimica.datatypes.User;
import com.foi.nwtis.lovmimica.socketcommand.responses.ErrorResponse;
/**
 *
 * @author lovel_mimica
 */
public class UpdateUserQuota extends AdminCommand {
    @Override
    public Response concreteExecute(String[] cmdArray) {        
        String updateType = cmdArray[4];
        String username = cmdArray[5];
        
        User updatedUser = PresentUsers.getUser(username);
        if(updatedUser == null) return new ErrorResponse(35);
        
        Integer category = updatedUser.getCategory();
        
        if(updateType.equalsIgnoreCase("UP")){
            if(category >= 4) return new ErrorResponse(34);
            category++;
        }else if(updateType.equalsIgnoreCase("DOWN")){
            if(category == 0) return new ErrorResponse(34);
            category--;
        }else return new ErrorResponse(104);
        
        updatedUser.setCategory(category);
        PresentUsers.update(updatedUser);

        return new OkResponse(10);
    }
}
