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
public class UnsuccessfulEmailArchive  extends AbstractEmailArchive{

    public UnsuccessfulEmailArchive() throws IOException {
        super("UnsuccessfulEmailsFolder", "unsuccessfulEmailsFile.bin");
    }

        
    
}
