/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.datatypes;

import com.foi.nwtis.lovmimica.logika.klijenti.GMClient;

/**
 *
 * @author lovel_mimica
 */
public class Address {
    private int id;
    private int userId;
    private String name;
    private String latitude;
    private String longitude;

    public Address() {
    }

    public Address(int userId, String name, String latitude, String longitude) {
        this.userId = userId;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Address(int id, int userId, String name, String latitude, String longitude) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Address(int userId, String name) {
        this.userId = userId;
        this.name = name;
        
        GMClient gmc = new GMClient();
        Location loc = gmc.getGeoLocation(name);
        
        this.latitude = loc.getLatitude();
        this.longitude = loc.getLongitude();
        
    }
    

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    
   
    
}
