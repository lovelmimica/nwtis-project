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
public class AddressFrequency {

    private Address address;
    private int frequency;

    public AddressFrequency(Address address, int frequency) {
        this.address = address;
        this.frequency = frequency;
    }

    public int getFrequency() {
        return frequency;
    }

    public Address getAddress() {
        return address;
    }
    
}
