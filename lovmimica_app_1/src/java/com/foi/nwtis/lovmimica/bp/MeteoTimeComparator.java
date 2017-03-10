/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.bp;

import com.foi.nwtis.lovmimica.datatypes.MeteoData;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lovel_mimica
 */
public class MeteoTimeComparator {

    public static List<MeteoData> sort(List<MeteoData> mp) {
        Collections.sort(mp, new Comparator<MeteoData>() {
            @Override
            public int compare(MeteoData o1, MeteoData o2) {
                return o1.getDownloadTime().compareTo(o2.getDownloadTime());
            }
        });
        return mp;
    }

    public static boolean isInInterval(Date datum, Date pocetak, Date kraj) {
        int rezultat = datum.compareTo(pocetak);
        if (rezultat < 0) {
            return false;
        }
        rezultat = datum.compareTo(kraj);
        if (rezultat > 0) {
            return false;
        }
        return true;
    }
    
}
