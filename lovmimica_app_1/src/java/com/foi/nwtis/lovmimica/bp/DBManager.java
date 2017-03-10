/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.bp;

import com.foi.nwtis.lovmimica.datatypes.Address;
import com.foi.nwtis.lovmimica.datatypes.User;
import com.foi.nwtis.lovmimica.datatypes.MeteoData;
import com.foi.nwtis.lovmimica.datatypes.SocketRequest;
import com.foi.nwtis.lovmimica.datatypes.WsRequest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.foi.nwtis.lovmimica.konfiguracije.bp.BP_konfiguracija;

/**
 *
 * @author lovel_mimica
 */
public class DBManager {
    public static BP_konfiguracija dbConfiguration;
    private static ResultSet resultSet;
    private static Connection connection;
    private static Statement statement;
    
    public synchronized static List<User> selectUsers(){
        String sql = "SELECT * FROM korisnici";
        List<User> userList = new ArrayList<User>();
        resultSet = executeSelect(sql);
        try {
            userList = DataConverter.convertUsers(resultSet);
            
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
        try {
            closeConnection();
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();        
        }
        return userList;
    }
    public synchronized static List<Address> selectAddresses(){
        String sql = "SELECT * FROM adrese";
        List<Address> addressList = new ArrayList<Address>();
        resultSet = executeSelect(sql);
        try {
            addressList = DataConverter.convertAddresses(resultSet);
            
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
        try {
            closeConnection();
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
        return addressList;
    }
    public synchronized static List<MeteoData> selectMeteoData(){
        String sql = "SELECT * FROM meteo_podaci";
        List<MeteoData> meteoDataList = new ArrayList<MeteoData>();
        resultSet = executeSelect(sql);
        try{
            meteoDataList = DataConverter.convertMeteoData(resultSet);
        }catch(Exception ex){
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
        try{
            closeConnection();
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
        return meteoDataList;
    }
    public synchronized static List<WsRequest> selectWsRequests(){
        String sql = "SELECT * FROM ws_zahtijevi";
        List<WsRequest> wsRequestList = new ArrayList<WsRequest>();
        resultSet = executeSelect(sql);
        try {
            wsRequestList = DataConverter.convertWsRequests(resultSet);
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
         try{
            closeConnection();
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
         return wsRequestList;
    }
    public synchronized static List<SocketRequest> selectSocketRequests(){
        String sql = "SELECT * FROM socket_zahtijevi";
        List<SocketRequest> socketRequestList = new ArrayList<SocketRequest>();
        resultSet = executeSelect(sql);
        try {
            socketRequestList = DataConverter.convertSocketRequests(resultSet);
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
         try{
            closeConnection();
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
         return socketRequestList;
    }
    
    
    public synchronized static void insertUser(User user){
        String name = user.getName();
        String password = user.getPassword();
        int category =  user.getCategory();
        boolean admin = user.isAdmin();
        
        String sql = "INSERT INTO korisnici (naziv, lozinka, kategorija, admin) VALUES " 
                + "('" + name+ "', '" + password + "', " + category + ", " + admin + ")";  ;
        executeUpdate(sql);
        
        try { 
            closeConnection();
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
    }
    public synchronized static void updateUser(User user){
        int id = user.getId();
        int newCategory = user.getCategory();
        String sql = "UPDATE korisnici SET kategorija = " + newCategory 
               + " WHERE id = " + id;
        executeUpdate(sql);
        try { 
            closeConnection();
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
    }
    public synchronized static void insertWsRequest(WsRequest wsRequest){
        int userId = wsRequest.getUserId();
        String wsType = wsRequest.getWsType();
        String wsName = wsRequest.getWsName();
        Timestamp start = DataConverter.dateToTimestamp(wsRequest.getStart());
        Timestamp end = DataConverter.dateToTimestamp(wsRequest.getEnd());
        String command = wsRequest.getCommand();
       
        String sql = "INSERT INTO ws_zahtijevi (korisnik, ws_tip, ws_naziv, pocetak, kraj, zahtijev) "
                + "VALUES (" + userId + ", '" + wsType + "', '" + wsName + "', "
                + "'" + start + "', '" + end + "', '" + command + "')";
        executeUpdate(sql);
        
        try { 
        closeConnection();
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
    }
    public synchronized static void insertAddress(Address address){
        int userId = address.getUserId();
        String name = address.getName();
        String latitude = address.getLatitude();
        String longitude = address.getLongitude();
        
        String sql = "INSERT INTO adrese (korisnik, naziv, sirina, duzina) "
                + "VALUES (" + userId + ", '" + name + "', '" + latitude + "', '" + longitude + "')";
        executeUpdate(sql);
        try { 
            closeConnection();
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
    }
    public synchronized static void insertSocketRequest(SocketRequest socketRequest){
        int userId = socketRequest.getUserId();
        int addressId = socketRequest.getAddressId();
        Timestamp start = DataConverter.dateToTimestamp(socketRequest.getStart());
        Timestamp end = DataConverter.dateToTimestamp(socketRequest.getEnd());
        String command = socketRequest.getCommand();
        
        String sql = "INSERT INTO socket_zahtijevi (adresa, korisnik, pocetak, kraj, komanda)"
                + " VALUES (" + addressId + ", " + userId + ", '" + start + "', '" + end + "', '" + command + "')";
        executeUpdate(sql);
        try { 
            closeConnection();
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
    }
    public synchronized static void insertMeteoData(MeteoData meteoData){
        int addressId = meteoData.getAddressId();
        float temperature = meteoData.getTemperature();
        float pressure = meteoData.getPressure();
        float humidity = meteoData.getHumidity();
        float wind =  meteoData.getWind();
        float clouds = meteoData.getClouds();
        Timestamp updateTime =  DataConverter.dateToTimestamp(meteoData.getUpdateTime());
        Timestamp downloadTime = DataConverter.dateToTimestamp(meteoData.getDownloadTime());
        boolean forecast = meteoData.isForecast();
        Timestamp forecastTime;
        if(forecast) forecastTime = DataConverter.dateToTimestamp(meteoData.getForecastTime());
        else forecastTime = new Timestamp(0);
        
        String sql = "INSERT INTO meteo_podaci (adresa, temperatura, tlak, vlaga, vjetar, oborine, vrijeme_azuriranja, vrijeme_preuzimanja, prognoza, vrijeme_prognoze) "
                + " VALUES (" + addressId + ", " + temperature + ", " + pressure + ", " + humidity + ", " + wind + ", " + clouds + ","
                + " '" + updateTime + "', '" + downloadTime + "', " + forecast + ", '" + forecastTime + "')";
        
        executeUpdate(sql);
        try { 
            closeConnection();
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }       
    }

    
    private synchronized static ResultSet executeSelect(String sql){
        try {
            connection = setupConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql); 

            return resultSet;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
        return null;
    }
   
    private synchronized static void executeUpdate(String sql){
        try {
            connection = setupConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            }catch (Exception ex) {
                System.out.println(ex.toString());
                ex.printStackTrace();
            }
        
    }
    
    private synchronized static Connection setupConnection() throws Exception{
        String connUrl = dbConfiguration.getServerDatabase() + dbConfiguration.getUserDatabase();
        Class.forName(dbConfiguration.getDriverDatabase());
        Connection connection = DriverManager.getConnection(connUrl, dbConfiguration.getUserUsername(), 
                    dbConfiguration.getUserPassword());
        return connection;
    }
    private synchronized static void closeConnection()throws Exception{
        statement.close();
        connection.close();
        resultSet.close();
    }
}
