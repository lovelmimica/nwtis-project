/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.logika.dretve;

import com.foi.nwtis.lovmimica.logika.ServerStatus;
import com.foi.nwtis.lovmimica.logika.GeneralConfiguration;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 *
 * @author lovel_mimica
 */
public class SocketListening extends Thread {
    private int port = GeneralConfiguration.getSocketPort();
    private ServerSocket server;

    @Override
    public void run() {
        super.run(); 
        try {
            server = new ServerSocket(port);
            
            while(ServerStatus.isStop() == false){
                Socket socket = server.accept();
                if(ServerStatus.isStop() == true) break;
                RequestProcessing requestProcessingThread = new RequestProcessing(socket);
                requestProcessingThread.start();
            }
            server.close();
            server = null;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
    }
    
    
   
}
