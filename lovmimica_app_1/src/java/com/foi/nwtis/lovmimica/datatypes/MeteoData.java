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
public class MeteoData {

    private int id;
    private int addressId;
    private float temperature;
    private float pressure;
    private float humidity;
    private float wind;
    private float clouds;
    private Date updateTime;
    private Date downloadTime;
    private boolean forecast;
    private Date forecastTime;

    public MeteoData(int id, int addressId, float temperature, float pressure, float humidity, float wind, float clouds, Date updateTime, Date downloadTime, boolean forecast, Date forecastTime) {
        this.id = id;
        this.addressId = addressId;
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.wind = wind;
        this.clouds = clouds;
        this.updateTime = updateTime;
        this.downloadTime = downloadTime;
        this.forecast = forecast;
        this.forecastTime = forecastTime;
    }

    public MeteoData(int addressId, float temperature, float pressure, float humidity, float wind, float clouds, Date updateTime, Date downloadTime, boolean forecast, Date forecastTime) {
        this.addressId = addressId;
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.wind = wind;
        this.clouds = clouds;
        this.updateTime = updateTime;
        this.downloadTime = downloadTime;
        this.forecast = forecast;
        this.forecastTime = forecastTime;
    }
   
    

    public int getAddressId() {
        return addressId;
    }


    public float getTemperature() {
        return temperature;
    }

    public float getPressure() {
        return pressure;
    }

    public float getHumidity() {
        return humidity;
    }
    
    public float getWind() {
        return wind;
    }

    public float getClouds() {
        return clouds;
    }

 
    public Date getUpdateTime() {
        return updateTime;
    }


    public Date getDownloadTime() {
        return downloadTime;
    }


    public boolean isForecast() {
        return forecast;
    }

    public Date getForecastTime() {
        return forecastTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public void setWind(float wind) {
        this.wind = wind;
    }

    public void setClouds(float clouds) {
        this.clouds = clouds;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setDownloadTime(Date downloadTime) {
        this.downloadTime = downloadTime;
    }

    public void setForecast(boolean forecast) {
        this.forecast = forecast;
    }

    public void setForecastTime(Date forecastTime) {
        this.forecastTime = forecastTime;
    }
    
}
