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
public class GetServerStatusBuilder extends SocketRequestBuilder{

    public GetServerStatusBuilder(String[] args) throws Exception  {
        super(args);
    }

    @Override
    protected void createCommand(){
        String command = "STATUS;";
        this.request.setCommand(command);
    }

    @Override
    protected boolean argsVerification() {
        if(this.args.length == 2) return true;
        else return false;
    }
    
}
