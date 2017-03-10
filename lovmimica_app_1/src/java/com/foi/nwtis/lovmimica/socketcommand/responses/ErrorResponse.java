/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.socketcommand.responses;

/**
 *
 * @author lovel_mimica
 */
public class ErrorResponse extends Response{

    public ErrorResponse(Integer code) {
        super(code);
    }

    @Override
    public String toString() {
        return "ERR_" + this.responseCode;
    }
    
    

}
