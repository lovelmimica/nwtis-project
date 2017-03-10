/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.logika;

/**
 *
 * @author lovel_mimica
 */
public class ServerStatus {
    private static boolean pause = false;
    private static boolean stop = false;

    public static boolean isPause() {
        return pause;
    }

    public static boolean isStop() {
        return stop;
    }

    public static void setPause(boolean pause) {
        ServerStatus.pause = pause;
    }

    public static void setStop(boolean stop) {
        ServerStatus.stop = stop;
    }
    
    
}
