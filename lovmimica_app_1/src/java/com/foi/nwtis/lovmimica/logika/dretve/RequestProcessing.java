/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.logika.dretve;

import com.foi.nwtis.lovmimica.bp.PresentAddresses;
import com.foi.nwtis.lovmimica.bp.PresentUsers;
import com.foi.nwtis.lovmimica.bp.PresentSocketRequests;
import com.foi.nwtis.lovmimica.socketcommand.CommandFactory;
import com.foi.nwtis.lovmimica.socketcommand.responses.OkResponse;
import com.foi.nwtis.lovmimica.socketcommand.responses.Response;
import com.foi.nwtis.lovmimica.socketcommand.usercommand.UserCommand;
import com.foi.nwtis.lovmimica.datatypes.SocketRequest;
import com.foi.nwtis.lovmimica.socketcommand.AbstractCommand;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;


/**
 *
 * @author lovel_mimica
 */
public class RequestProcessing extends Thread {

    private String command;
    private String[] commandArray;
    private Date start;
    private Date end;
    private Socket socket;
    
    public static final String PROVJERA_KORISNIKA = "PROVJERA_KORISNIKA";
    public static final String NEISPRAVNA_KOMANDA = "GRESKA";
    public static final String PAUSE = "PAUSE";
    public static final String START = "START";
    public static final String STOP = "STOP";
    public static final String STATUS = "STATUS";
    public static final String ADD_KORISNIK = "ADD_KORISNIK";
    public static final String UPDATE_KORISNIK = "UPDATE_KORISNIK";
    public static final String TEST_ADRESA = "TEST_ADRESA";
    public static final String GET_ADRESA = "GET_ADRESA";
    public static final String ADD_ADRESA = "ADD_ADRESA";
    
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public static final String GRESKA_20 = "ERR 20";
    public static final String OK_10 = "OK 10";
    public static final String OK_02 = "OK 02";
    public static final String OK_00 = "OK 00";
    public static final String OK_01 = "OK 01";
    public static final String GRESKA_30 = "ERR 30";
    public static final String GRESKA_21 = "ERR 21";
    public static final String GRESKA_31 = "ERR 31";
    public static final String GRESKA_32 = "ERR 32";
    public static final String GRESKA_33 = "ERR 33";
    public static final String GRESKA_34 = "ERR 34";
    public static final String GRESKA_35 = "ERR 35";
    public static final String GRESKA_40 = "ERR 40";
    public static final String GRESKA_41 = "ERR 41";
    public static final String GRESKA_42 = "ERR 42";

    public RequestProcessing(Socket _socket) throws Exception{
        socket = _socket;
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
        command = receiveSocketMessage(socket);
        commandArray = createCommandArray(command);
        
    }
    
    @Override
    public void run() {
        super.run();
        start = new Date();
        
        AbstractCommand command = CommandFactory.createCommand(commandArray);
        
        Response response = command.execute(commandArray);
        end = new Date();
                
        if(command.getClass().equals(UserCommand.class) && response.getClass().equals(OkResponse.class)){
            writeLog(commandArray);
        }
        sendSocketResponse(response.toString());
        
    }

    private String receiveSocketMessage(Socket socket) throws Exception{
        String msg = (String)ois.readObject();
        return msg;
    }
    private void sendSocketResponse(String odgovor){
        try {            
            oos.writeObject(odgovor);
            
            oos.flush();
            oos.close();
            ois.close();
            socket.close();
            
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
    }   

    private void writeLog(String[] komande){
        String adresaNaziv = komande[5];
        int adresaId = PresentAddresses.getId(komande[5]);
        String korisnikNaziv = komande[1];
        int korisnikId = PresentUsers.getId(komande[1]);
        SocketRequest socketZahtijev = new SocketRequest(korisnikId, adresaId, 
                start, end, command);
        PresentSocketRequests.add(socketZahtijev);
    }
    
    
    
    private String[] createCommandArray(String komanda) {
        String temp = komanda.replace(";", "");
        String polje[] = temp.split(" ");
        return polje;
    }
    
    
    
}
