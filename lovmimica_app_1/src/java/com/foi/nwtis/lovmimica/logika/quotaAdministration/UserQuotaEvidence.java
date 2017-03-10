/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.logika.quotaAdministration;

import com.foi.nwtis.lovmimica.bp.PresentUsers;
import com.foi.nwtis.lovmimica.datatypes.User;
import com.foi.nwtis.lovmimica.logika.GeneralConfiguration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author lovelmimica
 */
public class UserQuotaEvidence {
    
    private Map<String,Integer> quotaMap;
    
    private static UserQuotaEvidence instance = null;
    private static int multiplier = GeneralConfiguration.getUserQuotaMultiplier();

    public UserQuotaEvidence() {
        quotaMap = new ConcurrentHashMap<String,Integer>();
    }

    public static UserQuotaEvidence getInstance() {
        if(instance == null) instance = new UserQuotaEvidence();
        return instance;
    }
    
    public boolean checkQuotaAvailability(String userName){
        if(quotaMap.containsKey(userName) == false){
            return true;
        }else{
            int kvota = quotaMap.get(userName);
            if(kvota > 0) return true;
        }
        return false;
    }
    
    public synchronized void decrementQuota(String userName) {
        synchronized(this.quotaMap){
            if (quotaMap.containsKey(userName) == false) {
            User user = PresentUsers.getUser(userName);
        if(user != null){
            Integer quota = user.getCategory() * multiplier + 2;
            quotaMap.put(userName, quota);
        }
        else quotaMap.put(userName, -10);
        }

        Integer quota = quotaMap.get(userName);
        quota = quota - 1;
        quotaMap.put(userName, quota);
        }
    }

    
   
    public synchronized void reset(){
        quotaMap.clear();
    }
    
    
}
