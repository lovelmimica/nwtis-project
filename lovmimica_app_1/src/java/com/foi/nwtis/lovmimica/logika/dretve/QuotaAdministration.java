/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.logika.dretve;

import com.foi.nwtis.lovmimica.bp.PresentUsers;
import com.foi.nwtis.lovmimica.logika.ServerStatus;
import com.foi.nwtis.lovmimica.logika.GeneralConfiguration;
import com.foi.nwtis.lovmimica.datatypes.User;
import com.foi.nwtis.lovmimica.logika.quotaAdministration.UserQuotaEvidence;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author lovel_mimica
 */
public class QuotaAdministration extends Thread {
    private static int interval = GeneralConfiguration.getQuotaAdministrationThreadInterval() * 1000;
    

    @Override
    public void run() {
        super.run();
        long start = System.currentTimeMillis();
        while(ServerStatus.isStop() == false){       
            UserQuotaEvidence.getInstance().reset();
            long end = System.currentTimeMillis();
            try {
                wait(start, end, interval);
            } catch (Exception ex) {
            }
        }
    }
   
    private void wait(long start, long end, long interval) throws Exception{
        long sleepTimeMils = end - start;
        sleepTimeMils = interval - sleepTimeMils;
        if(sleepTimeMils > 0 ) sleep(sleepTimeMils);
    }
}
