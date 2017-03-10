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
public class SocketRequest {
    private int id;
    private int userId;
    private int addressId;
    private Date start;
    private Date end;
    private String command;

    public SocketRequest(int id, int userId, int addressId, Date start, Date end, String command) {
        this.id = id;
        this.userId = userId;
        this.addressId = addressId;
        this.start = start;
        this.end = end;
        this.command = command;
    }

    public SocketRequest(int userId, int addressId, Date start, Date end, String command) {
        this.userId = userId;
        this.addressId = addressId;
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


    public int getAddressId() {
        return addressId;
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
