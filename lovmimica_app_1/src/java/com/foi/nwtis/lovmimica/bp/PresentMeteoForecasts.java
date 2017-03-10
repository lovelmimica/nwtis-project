/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.bp;

import com.foi.nwtis.lovmimica.datatypes.MeteoData;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lovel_mimica
 */
public class PresentMeteoForecasts {
    public synchronized static List<MeteoData> getAll(){
        List<MeteoData> presentAllMeteoData = DBManager.selectMeteoData();
        List<MeteoData> presentMeteoForecasts = new ArrayList<MeteoData>();
        for(MeteoData mp : presentAllMeteoData){
            if(mp.isForecast() == true) presentMeteoForecasts.add(mp);
        }
        return presentMeteoForecasts;
    }
    public synchronized static void add(List<MeteoData> meteoForecastList){
        for(MeteoData md : meteoForecastList){
            DBManager.insertMeteoData(md);
        }
    }
    public static List<MeteoData> getAddressAllForecasts(int addressId){
        List<MeteoData> presentForecasts = getAll();
        List<MeteoData> addressForecastList = new ArrayList<MeteoData>();
        for(MeteoData md :presentForecasts){
            if(md.getAddressId() == addressId) addressForecastList.add(md);
        }
        return addressForecastList;
    }
    public static List<MeteoData> getAddressIntervaledForecasts(int adresaId, Date pocetak, Date kraj){
        List<MeteoData> addressForecasts = getAddressAllForecasts(adresaId);
        List<MeteoData> filteredForecasts = new ArrayList<MeteoData>();
        for(MeteoData mp : addressForecasts){
            if(MeteoTimeComparator.isInInterval(mp.getForecastTime(), pocetak, kraj)) 
                filteredForecasts.add(mp);
        }
        return filteredForecasts;
    }
    public static List<MeteoData> getAddressCurrentForecast(int adresaId){
        Date start = new Date();
        Date end = new Date();
        long longTime = end.getTime() + (5*24*60*60*1000);
        end = new Date(longTime);
        
        List<MeteoData> addressCurrentForecast = getAddressIntervaledForecasts(adresaId, start, end);
        List<MeteoData> newestForecast = getNewestForecast(addressCurrentForecast);
        
        return newestForecast;
    }
    
    private static List<MeteoData> getNewestForecast(List<MeteoData> forecastList) {

        forecastList.sort(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                MeteoData md1 = (MeteoData) o1;
                MeteoData md2 = (MeteoData) o2;
                int result;

                if (md1.getDownloadTime().after(md2.getDownloadTime())) {
                    result = -1;
                } else if (md1.getDownloadTime().before(md2.getDownloadTime())) {
                    result = 1;
                } else {
                    result = 0;
                }

                return result;
            }
        });

        Date max = forecastList.get(0).getDownloadTime();
        List<MeteoData> newestForecastList = new ArrayList<MeteoData>();
        for (MeteoData md : forecastList) {
            if (md.getDownloadTime().equals(max)) {
                newestForecastList.add(md);
            }
        }

        return newestForecastList;
    }
                
                
}
