/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.web.socket;

/**
 *
 * @author lovelmimica
 */
public class TestAddressBuilder extends SocketRequestBuilder{
    public TestAddressBuilder(String[] args) throws Exception {
        super(args);
    }

    @Override
    protected void createCommand() {
        String command = "TEST " + this.args[2] + ";";
        this.request.setCommand(command);
    }

    @Override
    protected boolean argsVerification() {
        if(this.args.length == 3) return true;
        else return false;
    }
    
}
