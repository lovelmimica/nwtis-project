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
 * Jersey REST client generated for REST resource:AdreseFacadeREST
 * [com.foi.nwtis.lovmimica.logika.ws.rest.adrese]<br>
 * USAGE:
 * <pre>
 *        RestWsAddressClient client = new RestWsAddressClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author lovelmimica
 */
public class RestWsAddressClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8084/lovmimica_aplikacija_1/webresources";

    public RestWsAddressClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("addresses");
    }

    public <T> T getAllAddresses(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllAddresses/pero/123");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_XML).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
