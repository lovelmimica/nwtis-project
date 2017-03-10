/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.socketcommand;

import com.foi.nwtis.lovmimica.socketcommand.responses.Response;

/**
 *
 * @author lovel_mimica
 */
public interface Command {
    Response execute(String[] commandArray);

}
