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
public abstract class Response {
    protected Integer responseCode;
    public Response(Integer code) {
        this.responseCode = code;
    }  

    
}
