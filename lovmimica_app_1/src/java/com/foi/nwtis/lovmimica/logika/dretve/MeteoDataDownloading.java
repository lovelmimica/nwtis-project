/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.logika.dretve;

import com.foi.nwtis.lovmimica.bp.PresentAddresses;
import com.foi.nwtis.lovmimica.bp.PresentMeteoForecasts;
import com.foi.nwtis.lovmimica.bp.PresentMeteoData;
import com.foi.nwtis.lovmimica.logika.ServerStatus;
import com.foi.nwtis.lovmimica.logika.GeneralConfiguration;
import com.foi.nwtis.lovmimica.logika.klijenti.OWMClient;
import com.foi.nwtis.lovmimica.datatypes.Address;
import com.foi.nwtis.lovmimica.datatypes.MeteoData;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lovel_mimica
 */
public class MeteoDataDownloading extends Thread {

    @Override
    public void run() {
        super.run(); 
        long interval = GeneralConfiguration.getDownloadThreadInterval();
        while(ServerStatus.isStop() == false){
            while(ServerStatus.isPause() == false){
                long start = new Date().getTime();
                downloadMeteoData();
                long end = new Date().getTime();
                try {
                    wait(start, end, interval);
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                    ex.printStackTrace();
                }
            }
        }
    }
    private void downloadMeteoData(){
        List<Address> addresses = PresentAddresses.getAll();
        OWMClient owmClient = new OWMClient(GeneralConfiguration.getApiKey());
        
        List<MeteoData> meteoData = owmClient.downloadMeteoDatas(addresses);
        PresentMeteoData.add(meteoData);
        
        List<MeteoData> meteoForecast = owmClient.downloadMeteoForecasts(addresses); 
        PresentMeteoForecasts.add(meteoForecast);
 
    }
    private void wait(long start, long end, long interval) throws Exception{
        long waitMils = end - start;
        waitMils = interval - waitMils;
        if(waitMils > 0 ) sleep(waitMils);
    }
    
}
