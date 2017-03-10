/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.logika.ws.rest.service;

import com.foi.nwtis.lovmimica.bp.PresentAddresses;
import com.foi.nwtis.lovmimica.bp.PresentMeteoForecasts;
import com.foi.nwtis.lovmimica.datatypes.MeteoData;
import com.foi.nwtis.lovmimica.logika.ws.InitialWsActions;
import com.foi.nwtis.lovmimica.logika.ws.rest.MeteoPodaci;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author lovelmimica
 */
@Stateless
@Path("forecasts")
public class MeteoPodaciFacadeREST extends AbstractFacade<MeteoPodaci> {

    @PersistenceContext(unitName = "lovmimica_aplikacija_1PU")
    private EntityManager em;

    public MeteoPodaciFacadeREST() {
        super(MeteoPodaci.class);
    }
    
    
    @GET
    @Path("getCurrentMeteoForecast/{username}/{password}/{address}")
    @Produces(MediaType.TEXT_XML)
    public List<MeteoPodaci> getCurrentMeteoForecast(@PathParam("username") String username,
            @PathParam("password") String passwd, @PathParam("address") String addressName) {
        boolean approved = InitialWsActions.execute(username, passwd);
        if (approved) {
            Integer addressId = PresentAddresses.getId(addressName);
            List<MeteoData> addressFrecastList = PresentMeteoForecasts.getAddressCurrentForecast(addressId);
            List<MeteoPodaci> currentMeteoForecast = new ArrayList<MeteoPodaci>();

            for (MeteoData forecast : addressFrecastList) {
                currentMeteoForecast.add(new MeteoPodaci(forecast));
            }

            return currentMeteoForecast;
        }
        return null;
    }
    
    @GET
    @Path("getIntervaledMeteoForecast/{username}/{password}/{addressName}/{startDate}/{endDate}")
    public List<MeteoPodaci> getIntervaledMeteoForecast(@PathParam("username") String username,
            @PathParam("password") String passwd, @PathParam("addressName") String name,
            @PathParam("startDate") Date start, @PathParam("endDate") Date end) {
        boolean approved = InitialWsActions.execute(username, passwd);
        if (approved) {
            int addressId = PresentAddresses.getId(name);
            List<MeteoData> forecastList = PresentMeteoForecasts.getAddressIntervaledForecasts(addressId, start, end);
            List<MeteoPodaci> formatedForecastList = new ArrayList<MeteoPodaci>();
            for (MeteoData md : forecastList) {
                formatedForecastList.add(new MeteoPodaci(md));
            }
            return formatedForecastList;
        }
        return null;
    }
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
