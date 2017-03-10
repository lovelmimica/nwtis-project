/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.socketcommand;

import com.foi.nwtis.lovmimica.logika.Autentification;
import com.foi.nwtis.lovmimica.socketcommand.responses.ErrorResponse;
import com.foi.nwtis.lovmimica.socketcommand.responses.OkResponse;
import com.foi.nwtis.lovmimica.socketcommand.responses.Response;

/**
 *
 * @author lovel_mimica
 */
public class AutentificateUser extends AbstractCommand{

    @Override
    public Response concreteExecute(String[] commandArray) {
        boolean userAutentificated = Autentification.userAutentification(commandArray[1], commandArray[3]);
        if(userAutentificated) return new OkResponse(10);
        else return new ErrorResponse(20);
    }

    
}
