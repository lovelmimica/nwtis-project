/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foi.nwtis.lovmimica.logika.klijenti;

import com.foi.nwtis.lovmimica.datatypes.Location;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author lovel_mimica
 */
public class GMClient {
   
    GMRESTHelper helper;
    Client client;

    public GMClient() {
        client = ClientBuilder.newClient();
    }

    public Location getGeoLocation(String address) {
        try {
            WebTarget webResource = client.target(GMRESTHelper.getGM_BASE_URI())
                    .path("maps/api/geocode/json");
            webResource = webResource.queryParam("address",
                    URLEncoder.encode(address, "UTF-8"));
            webResource = webResource.queryParam("sensor", "false");

            String response = webResource.request(MediaType.APPLICATION_JSON).get(String.class);

            JsonReader reader = Json.createReader(new StringReader(response));

            JsonObject jo = reader.readObject();
                     
            JsonObject obj = jo.getJsonArray("results")
                    .getJsonObject(0)
                    .getJsonObject("geometry")
                    .getJsonObject("location");

            Location location = new Location(obj.getJsonNumber("lat").toString(), obj.getJsonNumber("lng").toString());

            return location;

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(OWMClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
