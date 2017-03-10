/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.socketcommand;

import com.foi.nwtis.lovmimica.socketcommand.responses.ErrorResponse;
import com.foi.nwtis.lovmimica.socketcommand.responses.Response;

/**
 *
 * @author lovelmimica
 */
public class InvalidCommand extends AbstractCommand{

    @Override
    public Response execute(String[] commandArray) {
        return new ErrorResponse(100);
    }

    @Override
    protected Response concreteExecute(String[] commandArray) {
        return new ErrorResponse(100);
    }
    
    
}
