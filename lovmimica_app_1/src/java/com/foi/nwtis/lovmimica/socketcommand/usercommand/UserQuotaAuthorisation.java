/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.socketcommand.usercommand;

import com.foi.nwtis.lovmimica.logika.dretve.QuotaAdministration;
import com.foi.nwtis.lovmimica.logika.quotaAdministration.UserQuotaEvidence;
import com.foi.nwtis.lovmimica.socketcommand.responses.ErrorResponse;
import com.foi.nwtis.lovmimica.socketcommand.responses.OkResponse;
import com.foi.nwtis.lovmimica.socketcommand.responses.Response;

/**
 *
 * @author lovel_mimica
 */
public class UserQuotaAuthorisation extends UserCommand{

    @Override
    public Response concreteExecute(String[] cmdArray) {
         boolean requestGranted = UserQuotaEvidence.getInstance().checkQuotaAvailability(cmdArray[1]); 
            if(requestGranted) {
                return new OkResponse(10);
            }
            else return new ErrorResponse(40);
    }
    
    
}
