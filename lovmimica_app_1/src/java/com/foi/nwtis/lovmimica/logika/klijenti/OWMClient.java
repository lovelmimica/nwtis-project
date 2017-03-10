/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.logika.klijenti;
import com.foi.nwtis.lovmimica.datatypes.Address;
import com.foi.nwtis.lovmimica.datatypes.MeteoData;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
/**
 *
 * @author lovel_mimica
 */
public class OWMClient {
    
    String apiKey;
    OWMRESTHelper helper;
    Client client;

    public OWMClient(String apiKey) {
        this.apiKey = apiKey;
        helper = new OWMRESTHelper(apiKey);
        client = ClientBuilder.newClient();
    }
    public List<MeteoData>downloadMeteoDatas(List<Address> addresses){
        List<MeteoData> meteoDataList = new ArrayList<MeteoData>();
        for(Address address :  addresses){
            meteoDataList.add(downloadMeteoData(address));
        }
        return meteoDataList;
    }
    public List<MeteoData> downloadMeteoForecasts(List<Address> addresses){
        List<MeteoData> meteForecastList = new ArrayList<MeteoData>();
        for(Address a : addresses){
            List<MeteoData> prognoza = downloadMeteoForecast(a);
            meteForecastList.addAll(prognoza);
        }
        return meteForecastList;
    }

    private MeteoData downloadMeteoData(Address address) {
        WebTarget webResource = client.target(OWMRESTHelper.getOWM_BASE_URI())
                .path(OWMRESTHelper.getOWM_Current_Path());
        webResource = webResource.queryParam("lat", address.getLatitude());
        webResource = webResource.queryParam("lon", address.getLongitude());
        webResource = webResource.queryParam("lang", "hr");
        webResource = webResource.queryParam("units", "metric");
        webResource = webResource.queryParam("APIKEY", apiKey);
        
        String odgovor = webResource.request(MediaType.APPLICATION_JSON).get(String.class);
        try {
            JsonReader reader = Json.createReader(new StringReader(odgovor));

            JsonObject jo = reader.readObject();

           
            int addressId = address.getId();
            float temperature;
            float pressure;
            float humidity;
            float wind;
            float clouds;
            Date updateTime;
            Date downloadTime = new Date();
            boolean forecast = false;
            Date forecastTime = new Date(0);

            temperature = new Double(jo.getJsonObject("main").getJsonNumber("temp").doubleValue()).floatValue(); 
            humidity = new Double(jo.getJsonObject("main").getJsonNumber("humidity").doubleValue()).floatValue();  
            pressure = new Double(jo.getJsonObject("main").getJsonNumber("pressure").doubleValue()).floatValue();
            wind = new Double(jo.getJsonObject("wind").getJsonNumber("speed").doubleValue()).floatValue();
            updateTime = new Date(jo.getJsonNumber("dt").bigDecimalValue().longValue()*1000);
            clouds = new Double(jo.getJsonObject("clouds").getJsonNumber("all").doubleValue()).floatValue(); 
            
            MeteoData meteoData = new MeteoData(addressId, temperature, pressure, humidity, wind, clouds, updateTime, downloadTime, forecast, forecastTime);
            
            return meteoData;
            
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
        return null;
    }
    private List<MeteoData> downloadMeteoForecast(Address address){
        WebTarget webResource = client.target(OWMRESTHelper.getOWM_BASE_URI())
                .path(OWMRESTHelper.getOWM_Forecast_Path());
        webResource = webResource.queryParam("lat", address.getLatitude());
        webResource = webResource.queryParam("lon", address.getLongitude());
        webResource = webResource.queryParam("lang", "hr");
        webResource = webResource.queryParam("units", "metric");
        webResource = webResource.queryParam("APIKEY", apiKey);
        
        String response = webResource.request(MediaType.APPLICATION_JSON).get(String.class);
        
        JsonReader reader = Json.createReader(new StringReader(response));
        JsonObject jo = reader.readObject();
        JsonArray jsonMeteoData = jo.getJsonArray("list");
        
        List<MeteoData> meteoForecast = new ArrayList<MeteoData>();
        Date updateTime = new Date();
        Date downloadTime = new Date();
        for(int i = 0; i<jsonMeteoData.size(); i++){
            JsonObject mainObj = jsonMeteoData.getJsonObject(i).getJsonObject("main");
            JsonObject cloudObj = jsonMeteoData.getJsonObject(i).getJsonObject("clouds");
            JsonObject windObj = jsonMeteoData.getJsonObject(i).getJsonObject("wind");
            
            int addressId = address.getId();
            float temperature;
            float pressure;
            float humidity;
            float wind;
            float clouds;
            
            boolean forecast = true;
            Date forecastTime;
            
            long forecastTimeLong = new Double(jsonMeteoData.getJsonObject(i).getJsonNumber("dt").doubleValue()).longValue()*1000;
            
            forecastTime = new Date(forecastTimeLong);
            
            temperature = new Double(mainObj.getJsonNumber("temp").doubleValue()).floatValue();
            pressure = new Double(mainObj.getJsonNumber("pressure").doubleValue()).floatValue();
            humidity = new Double(mainObj.getJsonNumber("humidity").doubleValue()).floatValue();
            wind = new Double(windObj.getJsonNumber("speed").doubleValue()).floatValue();
            clouds = new Double(cloudObj.getJsonNumber("all").doubleValue()).floatValue();
            
            
            MeteoData meteoData = new MeteoData(addressId, temperature, pressure, humidity, wind, clouds, updateTime, downloadTime,forecast, forecastTime);
            
            meteoForecast.add(meteoData);
        }
        return meteoForecast;
    }
}
