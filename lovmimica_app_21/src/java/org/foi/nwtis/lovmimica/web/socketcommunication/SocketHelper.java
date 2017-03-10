/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.web.socketcommunication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author lovelmimica
 */
public class SocketHelper {
    public static String sendRequest(String msg, String host, Integer port) throws Exception{
        Socket socket = new Socket(host, port);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        
        oos.writeObject(msg);
        oos.flush();
        String response = (String)ois.readObject();
        oos.close();
        ois.close();
        socket.close();
        
        return response;
    }
}
