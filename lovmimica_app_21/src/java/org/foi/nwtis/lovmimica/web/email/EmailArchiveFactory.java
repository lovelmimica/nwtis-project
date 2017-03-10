/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.web.email;

import java.io.IOException;

/**
 *
 * @author lovelmimica
 */
public class EmailArchiveFactory {
    public static AbstractEmailArchive getEmailArchive(String type) throws IOException{
        if(type.equalsIgnoreCase("successful")) return new SuccessfulEmailArchive();
        if(type.equalsIgnoreCase("unsuccessful")) return new UnsuccessfulEmailArchive();
        if(type.equalsIgnoreCase("incorrect")) return new IncorrectEmailArchive();
        else return new DefaultEmailArchive();
    }
}
