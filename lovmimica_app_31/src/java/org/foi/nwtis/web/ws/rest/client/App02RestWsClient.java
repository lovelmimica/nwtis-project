/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.web.ws.rest.client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * Jersey REST client generated for REST resource:HelloWorldResource
 * [helloWorld]<br>
 * USAGE:
 * <pre>
 *        App02RestWsClient client = new App02RestWsClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author lovelmimica
 */
public class App02RestWsClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/lovmimica_aplikacija_21/webresources";

    public App02RestWsClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("helloWorld");
    }

    public Response getActiveUsers() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("active_users");
        return resource.request().get();
    }

    public void putText(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.TEXT_PLAIN));
    }

    public Response getUsersAddresses(String user) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("users_addresses/{0}", new Object[]{user}));
        return resource.request().get();
    }

    public void close() {
        client.close();
    }
    
}
