/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.logika;

import com.foi.nwtis.lovmimica.bp.PresentUsers;
import com.foi.nwtis.lovmimica.datatypes.User;
import java.util.List;

/**
 *
 * @author lovel_mimica
 */
public class Autentification {
    public static boolean userAutentification(String userName, String userPswd){
        List<User> presentUsers = PresentUsers.getAll();
        for(User user : presentUsers){
            if(user.getName().equals(userName) && user.getPassword().equals(userPswd)) return true;
        }
        return false;
    }
    public static boolean adminAutentification(String userName, String userPswd){
        List<User> presentUsers = PresentUsers.getAll();
        for(User user : presentUsers){
            if(user.getName().equals(userName) 
                    && user.getPassword().equals(userPswd) && user.isAdmin() == true) return true;
        }
        return false;
    }
}
