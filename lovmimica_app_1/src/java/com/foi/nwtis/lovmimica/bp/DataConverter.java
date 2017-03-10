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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author lovel_mimica
 */
public class DataConverter {
    public static List<User> convertUsers(ResultSet resultSet) throws SQLException{
        ArrayList<User> userList = new ArrayList<User>();
        while(resultSet.next()){
            try {  
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String password = resultSet.getString(3); 
                int category = resultSet.getInt(4);
                boolean admin = resultSet.getBoolean(5);

                userList.add(new User(id, name, password, category, admin));
            } catch (Exception ex) {
                System.out.println(ex.toString());
                ex.printStackTrace();
            }
        }
        return userList;
    }
    public static List<Address> convertAddresses(ResultSet resultSet) throws SQLException{
        ArrayList<Address> addressList = new ArrayList<Address>();
        while(resultSet.next()){
            try{
                int id = resultSet.getInt(1);
                int userId = resultSet.getInt(2);
                String name = resultSet.getString(3);
                String latitude = resultSet.getString(4);
                String longitude = resultSet.getString(5);
                
                addressList.add(new Address(id, userId, name, latitude, longitude));
            } catch (SQLException ex) {
                System.out.println(ex.toString());
                ex.printStackTrace();
            }
        }
        return addressList;
    }
    public static List<MeteoData>convertMeteoData(ResultSet resultSet)throws Exception{
        ArrayList<MeteoData> meteoDataList = new ArrayList<MeteoData>();
        while(resultSet.next()){
            try {
                int id = resultSet.getInt(1);
                int addressId = resultSet.getInt(2);
                float temperature = resultSet.getFloat(3);
                float pressure = resultSet.getFloat(4);
                float humidity = resultSet.getFloat(5);
                float wind = resultSet.getFloat(6);
                float clouds = resultSet.getFloat(7);
                
                Date updateTime = resultSet.getTimestamp(8);
                Date downloadTime = resultSet.getTimestamp(9);
                
                boolean forecast = resultSet.getBoolean(10);
                Date forecastTime = resultSet.getTimestamp(11);
                
                meteoDataList.add(new MeteoData(id, addressId, temperature, pressure, humidity,
                        wind, clouds, updateTime, downloadTime, 
                        forecast, forecastTime));
                
            } catch (SQLException ex) {
                System.out.println(ex.toString());
                ex.printStackTrace();
            }
        }
        return meteoDataList;
    }
    public static List<WsRequest> convertWsRequests(ResultSet resultSet) throws Exception{
        ArrayList<WsRequest> wsRequestList = new ArrayList<WsRequest>();
        while(resultSet.next()){
            try{
                int id = resultSet.getInt(1);
                int userId = resultSet.getInt(2);
                String wsType = resultSet.getString(3);
                String wsName= resultSet.getString(4);
                Date start =  timestampToDate(resultSet.getTimestamp(5));
                Date end = timestampToDate(resultSet.getTimestamp(6));;
                String command = resultSet.getString(7);

                wsRequestList.add(new WsRequest(id,userId, wsType, wsName, start, end, command));

            }catch(SQLException ex){
                System.out.println(ex.toString());
                ex.printStackTrace();
            }
        }
        return wsRequestList;
    }
    public static List<SocketRequest> convertSocketRequests(ResultSet resultSet) throws Exception{
        ArrayList<SocketRequest> socketRequestList = new ArrayList<SocketRequest>();
        while(resultSet.next()){
            try{
                int id = resultSet.getInt(1);
                int addressId = resultSet.getInt(2);
                int userId = resultSet.getInt(3);
                Date start = timestampToDate(resultSet.getTimestamp(4));
                Date end = timestampToDate(resultSet.getTimestamp(5));
                String command = resultSet.getString(6);

                socketRequestList.add(new SocketRequest(id,userId, addressId,start, end, command));

            }catch(SQLException ex){
                System.out.println(ex.toString());
                ex.printStackTrace();   
            }
        }
        return socketRequestList;
    }
    
    public static Date timestampToDate(Timestamp datum){
        if(datum != null){
            return new Date(datum.getTime());
        }
        return null;
    }
    public static Timestamp dateToTimestamp(Date datum){
        if(datum != null){
            return new Timestamp(datum.getTime());
        }
        return null;
    }
}

