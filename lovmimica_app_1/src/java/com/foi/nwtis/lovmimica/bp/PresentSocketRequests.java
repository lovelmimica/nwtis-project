/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.bp;

import com.foi.nwtis.lovmimica.datatypes.SocketRequest;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lovel_mimica
 */
public class PresentSocketRequests {
    public synchronized static void add(SocketRequest socketRequest){
        DBManager.insertSocketRequest(socketRequest);
    }
    public synchronized static List<SocketRequest> getAll(){
        List<SocketRequest> presentSocketRequests = DBManager.selectSocketRequests();
        return presentSocketRequests;
    }
    public static List<SocketRequest> getUserSocketRequests(int userId){
        List<SocketRequest> presentSocketRequests = getAll();
        List<SocketRequest> userRequests = new ArrayList<SocketRequest>();
        for(SocketRequest r:  presentSocketRequests){
            if(r.getUserId() == userId) userRequests.add(r);
        }
        return userRequests;   
    } 
}
