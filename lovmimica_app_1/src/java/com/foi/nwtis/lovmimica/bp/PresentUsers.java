/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.bp;

import com.foi.nwtis.lovmimica.datatypes.User;
import java.util.List;

/**
 *
 * @author lovel_mimica
 */
public class PresentUsers {
    public synchronized static void add(User user){
        DBManager.insertUser(user);
    }
    public synchronized static List<User> getAll(){
        List<User> presentUsers = DBManager.selectUsers();
        return presentUsers;
    }
    public static int getAllUserCount(){
        List<User> presentUsers = getAll();
        int count = presentUsers.size();
        return count;
    }
    public static int adminCount(){
        List<User> presentUsers = getAll();
        int count = 0;
        for(User k : presentUsers){
            if(k.isAdmin() == true) count++;
        }
        return count;
    }
    public static int normalUserCount(){
        List<User> presentUsers = getAll();
        int count = 0;
        for(User u : presentUsers){
            if(u.isAdmin() == false) count++;
        }
        return count;
    }
    public static boolean exists(String userName){
        List<User> presentUsers = getAll();
        for(User u : presentUsers){
            if(u.getName().equals(userName)) return true;
        }
        return false;
    }
    public static User getUser(String userName){
        List<User> presentUsers = getAll();
        for(User u : presentUsers){
            if(u.getName().equals(userName)) return u;
        }
        return null;
    }
    public synchronized static void update(User user){
        DBManager.updateUser(user);
    }
    public static int getId(String userName){
        User user = getUser(userName);
        if(user == null) return -1;
        int id = user.getId();
        return id;
    }
    
}
