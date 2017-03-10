/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.web.ws.rest.client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:MeteoPodaciFacadeREST
 * [com.foi.nwtis.lovmimica.logika.ws.rest.meteopodaci]<br>
 * USAGE:
 * <pre>
 *        RestWsClient client = new RestWsClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author lovelmimica
 */
public class RestWsMeteoDataClient_old {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8084/lovmimica_aplikacija_1/webresources";

    public RestWsMeteoDataClient_old() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("forecasts");
    }

    public <T> T testGetMeteoData(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("testGetMeteo");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_XML).get(responseType);
    }

    public <T> T getCurrentMeteoForecast(Class<T> responseType, String username, String password, String address) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getCurrentMeteoForecast/{0}/{0}/", username, password,address));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_XML).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
