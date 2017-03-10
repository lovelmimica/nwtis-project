/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.bp;

import com.foi.nwtis.lovmimica.datatypes.MeteoData;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lovel_mimica
 */
public class PresentMeteoData {
    public synchronized static void add(MeteoData meteoData){
        DBManager.insertMeteoData(meteoData);
    }
    public synchronized static void add(List<MeteoData> meteoDataList){
        for(MeteoData md : meteoDataList){
            DBManager.insertMeteoData(md);
        }
    }
    public synchronized static List<MeteoData> getAll(){
        List<MeteoData> presentAllMeteoData =  DBManager.selectMeteoData();
        List<MeteoData> presentMeteoDataList = new ArrayList<MeteoData>();
        for(MeteoData mp : presentAllMeteoData ){
            if(mp.isForecast() == false) presentMeteoDataList.add(mp);
        }
        return presentMeteoDataList;
    }
    public static List<MeteoData> getAddressAllMeteoData(int addressId){
        List<MeteoData> presentMeteoData = getAll();
        List<MeteoData> adressMeteoData = new ArrayList<MeteoData>();
        for(MeteoData md : presentMeteoData){
            if(md.getAddressId() == addressId) adressMeteoData.add(md);
        }
        return adressMeteoData;
    }
    public static MeteoData getAddressCurrentMeteoData(int addressId){
        List<MeteoData> addressMeteoDataList = getAddressAllMeteoData(addressId);
        if(addressMeteoDataList.size() == 0) return null;
        
        return addressMeteoDataList.get(addressMeteoDataList.size() - 1);
    }
    public static int gedAddresFrequency(int addressId){
        List<MeteoData> presentMeteoData = getAll();
        int count = 0; 
        for(MeteoData md : presentMeteoData){
            if(md.getAddressId() == addressId) count++;
        }
        return count;
    }
    public static List<MeteoData> getAddressCountedMeteoData(int adresaId, int n){
        List<MeteoData> addressMeteoData = getAddressAllMeteoData(adresaId);
        addressMeteoData = MeteoTimeComparator.sort(addressMeteoData);
        int lastIndex = addressMeteoData.size() - 1;
        if(n<1) return new ArrayList<MeteoData>();
        while(lastIndex >= n){
            addressMeteoData.remove(lastIndex);
            lastIndex--;
        }
        return addressMeteoData;
    }
    public static List<MeteoData> getAddressIntervaledMeteoData(int addresId, Date start, Date end){
        List<MeteoData> addressMeteoData = getAddressAllMeteoData(addresId);
        List<MeteoData> filteredAddressMeteoData = new ArrayList<MeteoData>();
        for(MeteoData md : addressMeteoData){
            if(MeteoTimeComparator.isInInterval(md.getDownloadTime(), start, end) == true) 
                filteredAddressMeteoData.add(md);
        }
        return filteredAddressMeteoData;
    }
    
}
