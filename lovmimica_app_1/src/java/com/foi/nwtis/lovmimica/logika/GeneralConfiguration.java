/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.logika;

import org.foi.nwtis.lovmimica.konfiguracije.Konfiguracija;
import org.foi.nwtis.lovmimica.konfiguracije.KonfiguracijaApstraktna;

/**
 *
 * @author lovel_mimica
 */
public class GeneralConfiguration {
    private static int downloadThreadInterval = 30000;
    private static String apiKey = "2240264f7e3eff3b7748191d7771a257";
    private static String emailReceiver = "pero@nwtis.nastava.foi.hr";
    private static String emailSender = "mali_pero@nwtis.nastava.foi.hr";
    private static String emailSubject = "Izvjestaj";
    private static int quotaAdministrationThreadInterval = 30000;
    private static int userQuotaMultiplier = 1;
    private static String emailServer;
    private static int socketPort;
    
    private static final String DOWNLOAD_THREAD_INTERVAL = "downloadThreadInterval";
    private static final String API_KEY = "apiKey";
    private static final String EMAIL_RECEIVER = "emailReceiver";
    private static final String EMAIL_SENDER = "emailSender";
    private static final String EMAIL_SUBJECT = "emailSubject";
    private static final String ADMINISTRATION_THREAD_INTERVAL = "quotaAdministrationThreadInterval";
    private static final String USER_QUOTA_MULTIPLIER = "userQuotaMultiplier";
    private static final String EMAIL_SERVER = "emailServer";
    private static final String SOCKET_PORT = "socketPort";
        
    public static void loadConfiguration(String path)throws Exception {
        Konfiguracija konf = KonfiguracijaApstraktna.preuzmiKonfiguraciju(path);
        downloadThreadInterval = new Integer(konf.dajPostavku(DOWNLOAD_THREAD_INTERVAL)) * 1000;
        apiKey = konf.dajPostavku(API_KEY);
        emailReceiver = konf.dajPostavku(EMAIL_RECEIVER);
        emailSender = konf.dajPostavku(EMAIL_SENDER);
        emailSubject = konf.dajPostavku(EMAIL_SUBJECT);
        quotaAdministrationThreadInterval = new Integer(konf.dajPostavku(ADMINISTRATION_THREAD_INTERVAL)) * 1000;
        userQuotaMultiplier = new Integer(konf.dajPostavku(USER_QUOTA_MULTIPLIER));
        emailServer = konf.dajPostavku(EMAIL_SERVER);
        socketPort = new Integer(konf.dajPostavku(SOCKET_PORT));
    }

    public static int getDownloadThreadInterval() {
        return downloadThreadInterval;
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static String getEmailReceiver() {
        return emailReceiver;
    }

    public static String getEmailSender() {
        return emailSender;
    }

    public static String getEmailSubject() {
        return emailSubject;
    }

    public static int getQuotaAdministrationThreadInterval() {
        return quotaAdministrationThreadInterval;
    }

    public static int getUserQuotaMultiplier() {
        return userQuotaMultiplier;
    }

    public static String getEmailServer() {
        return emailServer;
    }

    public static int getSocketPort() {
        return socketPort;
    }

    public static String getINTERVAL_DRETVE_PREUZIMANJA() {
        return DOWNLOAD_THREAD_INTERVAL;
    }

    public static String getAPI_KEY() {
        return API_KEY;
    }

    public static String getMAIL_PRIMATELJ() {
        return EMAIL_RECEIVER;
    }

    public static String getMAIL_POSILJATELJ() {
        return EMAIL_SENDER;
    }

    public static String getMAIL_PREDMET() {
        return EMAIL_SUBJECT;
    }

    public static String getINTERVAL_DRETVE_ADMINISTRACIJE() {
        return ADMINISTRATION_THREAD_INTERVAL;
    }

    public static String getMULTIPLIKATOR() {
        return USER_QUOTA_MULTIPLIER;
    }

    public static String getMAIL_SERVER() {
        return EMAIL_SERVER;
    }

    public static String getSOCKET_PORT() {
        return SOCKET_PORT;
    }
    
    
}
