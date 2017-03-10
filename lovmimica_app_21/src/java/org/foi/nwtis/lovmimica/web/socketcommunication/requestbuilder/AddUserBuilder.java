/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.web.socketcommunication.requestbuilder;

/**
 *
 * @author lovelmimica
 */
public class AddUserBuilder extends SocketRequestBuilder{

    public AddUserBuilder(String[] args) throws Exception {
        super(args);
    }

    @Override
    protected void createCommand(){
        StringBuilder sb = new StringBuilder();
        sb.append("ADD " + this.args[2] + "; ");
        sb.append("PASSWD " + this.args[3] + "; ");
        sb.append("ROLE " + this.args[4] + "; ");
        this.request.setCommand(new String(sb));
    }

    @Override
    protected boolean argsVerification() {
        if(this.args.length == 5) return true;
        else return false;
    }
    
    
}
