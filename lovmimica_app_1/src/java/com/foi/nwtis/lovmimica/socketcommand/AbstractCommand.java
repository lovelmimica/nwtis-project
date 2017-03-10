/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.socketcommand;

import com.foi.nwtis.lovmimica.logika.dretve.QuotaAdministration;
import com.foi.nwtis.lovmimica.socketcommand.admincommand.AdminAuthorisation;
import com.foi.nwtis.lovmimica.socketcommand.admincommand.AdminCommand;
import com.foi.nwtis.lovmimica.socketcommand.responses.ErrorResponse;
import com.foi.nwtis.lovmimica.socketcommand.responses.Response;
import com.foi.nwtis.lovmimica.socketcommand.usercommand.UserCommand;
import com.foi.nwtis.lovmimica.socketcommand.usercommand.UserQuotaAuthorisation;

/**
 *
 * @author lovel_mimica
 */
public abstract class AbstractCommand implements Command{
    protected abstract Response concreteExecute(String[] commandArray);
    @Override
    public Response execute(String[] commandArray) {
        
        AbstractCommand autentificationCommand = new AutentificateUser();
        Response autentificationResponse = autentificationCommand.concreteExecute(commandArray);
        if(autentificationResponse.getClass().equals(ErrorResponse.class) || this.getClass().equals(AutentificateUser.class))
            return autentificationResponse;
        
        if(this.getClass().getSuperclass().equals(AdminCommand.class)){
            //admin command
            AbstractCommand authorisationCommand = new AdminAuthorisation();
            Response authorisationResponse = authorisationCommand.concreteExecute(commandArray);
            if(authorisationResponse.getClass().equals(ErrorResponse.class)) return authorisationResponse;
            
        }else if(this.getClass().getSuperclass().equals(UserCommand.class)){
            //user command
            AbstractCommand quotaCheckCommand = new UserQuotaAuthorisation();
            Response quotaResponse = quotaCheckCommand.concreteExecute(commandArray);
            if(quotaResponse.getClass().equals(ErrorResponse.class)) return quotaResponse;
        }else {
            return new ErrorResponse(101);
        }
        
        return concreteExecute(commandArray);
    }
    
    
}
