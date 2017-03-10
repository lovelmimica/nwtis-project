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
public class UpdateUserCategoryBuilder extends SocketRequestBuilder{

    public UpdateUserCategoryBuilder(String[] args) throws Exception {
        super(args);
    }

    @Override
    protected void createCommand(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.args[2] + " ");
        sb.append(this.args[3] + ";");
        this.request.setCommand(new String(sb));
    }

    @Override
    protected boolean argsVerification() {
        if(args.length == 4) return true;
        else return false;
    }
    
    
    
}
