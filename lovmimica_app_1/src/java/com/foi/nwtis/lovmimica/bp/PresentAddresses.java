/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.bp;

import com.foi.nwtis.lovmimica.datatypes.Address;
import com.foi.nwtis.lovmimica.datatypes.AddressFrequency;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author lovel_mimica
 */
public class PresentAddresses {
    public synchronized static List<Address> getAll(){
        List<Address> presentAddresses = DBManager.selectAddresses();
        return presentAddresses;
    }
    public synchronized static void add(Address address){
        DBManager.insertAddress(address);
    }
    public static boolean exists(String name){
        List<Address> presentAddresses = getAll();
        for(Address a : presentAddresses){
            if(a.getName().equalsIgnoreCase(name)) return true;
        }
        return false;
    }
    public static int getId(String name){
        List<Address> presentAddresses = getAll();
        for(Address a : presentAddresses){
            if(a.getName().equalsIgnoreCase(name)) return a.getId();
        }
        return -1;
    }
    public static List<Address> getUserAddresses(int userId){
        List<Address> presentAddresses = getAll();
        List<Address> adreseKorisnika = new ArrayList<Address>();
        for(Address a : presentAddresses){
            if(a.getUserId() == userId) adreseKorisnika.add(a);
        }
        return adreseKorisnika;
    }
    public static List<AddressFrequency> getAddressRangList(int n){
        List<Address> presentAddresses = getAll();
        List<AddressFrequency> addressFrequencyList = new ArrayList<AddressFrequency>();
        for(Address address : presentAddresses){
            int frequency = PresentMeteoData.gedAddresFrequency(address.getId());
            AddressFrequency adressFrequency = new AddressFrequency(address, frequency);
            addressFrequencyList.add(adressFrequency);
        }
        addressFrequencyList = sort(addressFrequencyList);
        
        int lastIndex = addressFrequencyList.size() - 1;
        
        while(lastIndex >= n){
            addressFrequencyList.remove(lastIndex);
            lastIndex--;
        }
        return addressFrequencyList;
    }
    public static Address getById(Integer id){
        List<Address> allAddresses = getAll();
        for(Address a : allAddresses){
            if(id.equals(a.getId())) return a;
        }
        return null;
    }
    
    private static List<AddressFrequency> sort(List<AddressFrequency> list){
        Collections.sort(list,new Comparator<AddressFrequency>() {
            @Override
            public int compare(AddressFrequency o1, AddressFrequency o2) {
                int freq1 = o1.getFrequency();
                int freq2 = o2.getFrequency();
                if(freq1 < freq2) return -1;
                if(freq1 > freq2) return 1;
                return 0;
            }
        } );
        return list;
    }
}
