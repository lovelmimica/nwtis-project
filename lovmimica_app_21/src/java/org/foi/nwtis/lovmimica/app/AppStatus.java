/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.app;

/**
 *
 * @author lovelmimica
 */
public class AppStatus {
    private static boolean running = true;
    
    public static void stop(){
        running = false;
    }
    public static void start(){
        running = true;
    }

    public static boolean isRunning() {
        return running;
    }
    
    
}
