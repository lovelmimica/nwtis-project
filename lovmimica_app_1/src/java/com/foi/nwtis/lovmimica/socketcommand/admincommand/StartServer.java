/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.socketcommand.admincommand;

import com.foi.nwtis.lovmimica.logika.ServerStatus;
import com.foi.nwtis.lovmimica.socketcommand.responses.ErrorResponse;
import com.foi.nwtis.lovmimica.socketcommand.responses.OkResponse;
import com.foi.nwtis.lovmimica.socketcommand.responses.Response;

/**
 *
 * @author lovel_mimica
 */
public class StartServer extends AdminCommand {
    @Override
    public Response concreteExecute(String[] cmdArray) {        
        if(ServerStatus.isPause() == false) return new ErrorResponse(31);
        ServerStatus.setPause(false);
        return new OkResponse(10);
    }
}
