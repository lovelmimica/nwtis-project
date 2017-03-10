/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.web.beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.faces.context.FacesContext;

/**
 *
 * @author lovelmimica
 */
@Named(value = "localization")
@SessionScoped
public class Localization implements Serializable {
    private static Map<String, Object> lngsMap = new HashMap<String, Object>();
    private String chosenLng;
    private Locale currentLng;
    
    static{
        lngsMap.put("hr", new Locale("hr"));
        lngsMap.put("en", new Locale("en"));
    }
    /**
     * Creates a new instance of Localization
     */
    public Localization() {
    }

    public Map<String, Object> getLngsMap() {
        return lngsMap;
    }

    public void setLngsMap(Map<String, Object> lngsMap) {
        Localization.lngsMap = lngsMap;
    }

    
    public String getChosenLng() {
        return chosenLng;
    }

    public void setChosenLng(String chosenLng) {
        this.chosenLng = chosenLng;
    }

    public Locale getCurrengLng() {
        return currentLng;
    }

    public void setCurrengLng(Locale currengLng) {
        this.currentLng = currengLng;
    }
    public String chooseLng(){
        if(chosenLng != null && lngsMap.get(chosenLng) != null){
            FacesContext.getCurrentInstance().getViewRoot().
                    setLocale((Locale)lngsMap.get(this.chosenLng));
            currentLng = (Locale)lngsMap.get(this.chosenLng);
            return null;
        }else return null;
    }
}
