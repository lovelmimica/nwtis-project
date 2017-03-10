/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.web.email;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author lovelmimica
 */
public abstract class AbstractEmailArchive {
    protected String folderPath;
    protected String fileName;

    public AbstractEmailArchive(String folder, String file) throws IOException {
        this.folderPath = folder;
        this.fileName = file;
        createFolder();
    }

    public void writeEmail(MimeMessage mimeMsg) throws Exception{
        SerializableEmailMessage msg = new SerializableEmailMessage(mimeMsg);
      
        List<SerializableEmailMessage> presentMsgs = readAll();
        presentMsgs.add(msg);
        
        File file = new File(folderPath + "/" + fileName);
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(presentMsgs);
        
        fos.flush();
        oos.flush();
        oos.close();
        fos.close();
    }
    
    public List<SerializableEmailMessage> readAll() throws Exception{
        File file = new File(folderPath + "/" + fileName);
        long length = file.length();
        if(length == 0){
            return new ArrayList<SerializableEmailMessage>();
        }
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<SerializableEmailMessage> dataList = (List<SerializableEmailMessage>)ois.readObject();
        ois.close();
        fis.close();
        return dataList;
    }
    
    public void clearFolder() throws IOException{
        File file = new File(folderPath + "/" + fileName);
        file.delete();
        createFolder();
    }
    
    private void createFolder() throws IOException{
        File folder = new File(folderPath );
        folder.mkdirs();
        File file = new File(folderPath + "/" + fileName);
        
        file.createNewFile();
        
    }
    
}
