/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.web.beans.admin;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.foi.nwtis.lovmimica.web.email.AbstractEmailArchive;
import org.foi.nwtis.lovmimica.web.email.EmailArchiveFactory;
import org.foi.nwtis.lovmimica.web.email.SerializableEmailMessage;

/**
 *
 * @author lovelmimica
 */
@Named(value = "emailArchiveBean")
@SessionScoped
public class EmailArchiveBean implements Serializable {
private String response;
    private String currentFolder;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    

    public String getCurrentFolder() {
        return currentFolder;
    }

    public void setCurrentFolder(String currentFolder) {
        this.currentFolder = currentFolder;
    }

    
    
    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
    
    /**
     * Creates a new instance of EmailArchiveBean
     */
    public EmailArchiveBean() {
    }
    
    
    
    public List<SerializableEmailMessage> findEmailsAbstract(){
        try {
            AbstractEmailArchive archive = EmailArchiveFactory.getEmailArchive(currentFolder);
            List<SerializableEmailMessage> emailList = archive.readAll();
            return emailList;
        } catch (Exception ex) {
            return new ArrayList<SerializableEmailMessage>();
        }
    }
    public void printContent(SerializableEmailMessage msg){
        this.content = msg.getContent();
    }
    public void clearFolder(){
    try {
        AbstractEmailArchive archive = EmailArchiveFactory.getEmailArchive(currentFolder);
        archive.clearFolder();
    } catch (IOException ex) {
        System.out.println(new Date() + ": " + ex.toString());
    }
    }
    
}
