/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.socketcommand.usercommand;

import com.foi.nwtis.lovmimica.bp.PresentAddresses;
import com.foi.nwtis.lovmimica.socketcommand.responses.ErrorResponse;
import com.foi.nwtis.lovmimica.socketcommand.responses.OkResponse;
import com.foi.nwtis.lovmimica.socketcommand.responses.Response;

/**
 *
 * @author lovel_mimica
 */
public class TestAddress extends UserCommand{
    @Override
    public Response concreteExecute(String[] cmdArray) {
        
        String name = cmdArray[5];
        boolean exists = PresentAddresses.exists(name);
        
        if(exists) return new OkResponse(10);
        else return new ErrorResponse(42);
    }
}
