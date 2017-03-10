/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.web.slusaci;

import com.foi.nwtis.lovmimica.bp.DBManager;
import com.foi.nwtis.lovmimica.logika.ServerStatus;
import com.foi.nwtis.lovmimica.logika.GeneralConfiguration;
import com.foi.nwtis.lovmimica.logika.dretve.QuotaAdministration;
import com.foi.nwtis.lovmimica.logika.dretve.MeteoDataDownloading;
import com.foi.nwtis.lovmimica.logika.dretve.SocketListening;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.foi.nwtis.lovmimica.konfiguracije.bp.BP_konfiguracija;

/**
 * Web application lifecycle listener.
 *
 * @author lovel_mimica
 */
public class AppListener implements ServletContextListener {
    
    private List<Thread> threadPool;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        String pathBp = ctx.getRealPath("/WEB-INF" + File.separator + "database_configuration.xml");
        String pathGen = ctx.getRealPath("/WEB-INF" + File.separator + "general_configuration.xml");
        try {
            DBManager.dbConfiguration = new BP_konfiguracija(pathBp);
            GeneralConfiguration.loadConfiguration(pathGen);

            MeteoDataDownloading downloadThread = new MeteoDataDownloading();
            downloadThread.start();

            QuotaAdministration quotaAdministrationThread = new QuotaAdministration();
            quotaAdministrationThread.start();

            SocketListening socketListeningThread = new SocketListening();
            socketListeningThread.start();
            
            threadPool = new ArrayList<Thread>();
            threadPool.add(downloadThread);
            threadPool.add(quotaAdministrationThread);
            threadPool.add(socketListeningThread);

        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
            
        }

    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServerStatus.setStop(true);
        ServerStatus.setPause(true);
        
        for(Thread t : this.threadPool){
            t.interrupt();
        }
    }

}
