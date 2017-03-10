/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.datatypes;

import java.util.Date;

/**
 *
 * @author lovel_mimica
 */
public class WsRequest {

    private int id;
    private int userId;
    private String wsType;
    private String wsName;
    private Date start;
    private Date end;
    private String command;

    public WsRequest(int id, int userId, String wsType, String wsName, Date start, Date end, String command) {
        this.id = id;
        this.userId = userId;
        this.wsType = wsType;
        this.wsName = wsName;
        this.start = start;
        this.end = end;
        this.command = command;
    }

    public WsRequest(int userId, String wsType, String wsName, Date start, Date end, String command) {
        this.userId = userId;
        this.wsType = wsType;
        this.wsName = wsName;
        this.start = start;
        this.end = end;
        this.command = command;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getWsType() {
        return wsType;
    }

    public String getWsName() {
        return wsName;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public String getCommand() {
        return command;
    }

}
