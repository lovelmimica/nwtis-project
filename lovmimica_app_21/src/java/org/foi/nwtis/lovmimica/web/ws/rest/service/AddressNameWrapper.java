/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.web.ws.rest.service;

/**
 *
 * @author lovelmimica
 */
public class AddressNameWrapper {
    private String address; 
    

    public AddressNameWrapper() {
    }

    public AddressNameWrapper(String addressName) {
        this.address = addressName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    

}
