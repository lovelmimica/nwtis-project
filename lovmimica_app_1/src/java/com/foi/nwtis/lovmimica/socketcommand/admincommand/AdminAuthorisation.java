/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.socketcommand.admincommand;

import com.foi.nwtis.lovmimica.logika.Autentification;
import com.foi.nwtis.lovmimica.socketcommand.responses.ErrorResponse;
import com.foi.nwtis.lovmimica.socketcommand.responses.OkResponse;
import com.foi.nwtis.lovmimica.socketcommand.responses.Response;

/**
 *
 * @author lovel_mimica
 */
public class AdminAuthorisation extends AdminCommand{

    @Override
    public Response concreteExecute(String[] cmdArray) {
        boolean adminAuthorised = Autentification.adminAutentification(cmdArray[1], cmdArray[3]);
        if(adminAuthorised == true) return new OkResponse(10);
        else return new ErrorResponse(21);
    }
    
}
