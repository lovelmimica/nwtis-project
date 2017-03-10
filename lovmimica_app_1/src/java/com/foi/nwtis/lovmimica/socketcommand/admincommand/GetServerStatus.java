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
public class GetServerStatus extends AdminCommand{
    @Override
    public Response concreteExecute(String[] cmdArray) {
        System.out.println("GetServerStatus execute()");
        
        //check server status
        if(ServerStatus.isStop()) return new OkResponse(2);
        if(ServerStatus.isPause()) return new OkResponse(0);
        if(ServerStatus.isPause() == false && ServerStatus.isStop() == false) return new OkResponse(1);
        
        
        return new ErrorResponse(102);
    }
}
