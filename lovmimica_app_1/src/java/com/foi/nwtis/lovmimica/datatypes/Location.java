/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.datatypes;

/**
 *
 * @author lovel_mimica
 */
public class Location {
    private String latitude;
    private String longitude;

    public Location() {
    }

    public Location(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
   
    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

}
