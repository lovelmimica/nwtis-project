/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.bp;

import com.foi.nwtis.lovmimica.datatypes.WsRequest;
import java.util.List;

/**
 *
 * @author lovel_mimica
 */
public class PresentWsRequests {
    public synchronized static void add(WsRequest wsZahtijev){
        DBManager.insertWsRequest(wsZahtijev);
    }
    public synchronized static List<WsRequest> getAll(){
        List<WsRequest> presentWsRequests = DBManager.selectWsRequests();
        return presentWsRequests;
    }
}
