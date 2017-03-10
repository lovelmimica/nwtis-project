/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.logika.ws;

import com.foi.nwtis.lovmimica.logika.Autentification;
import com.foi.nwtis.lovmimica.logika.quotaAdministration.UserQuotaEvidence;

/**
 *
 * @author lovel_mimica
 */
public class InitialWsActions {
    public static boolean execute(String korisnik, String lozinka){
        boolean autentificated = Autentification.userAutentification(korisnik, lozinka);
        if(autentificated == false) return false;
        boolean authorized = UserQuotaEvidence.getInstance().checkQuotaAvailability(korisnik);
        if(authorized == false) return false;
        UserQuotaEvidence.getInstance().decrementQuota(korisnik);
        return true;
    }
}
