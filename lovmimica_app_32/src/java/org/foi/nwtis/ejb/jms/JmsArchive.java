/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ejb.jms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lovelmimica
 */
public class JmsArchive {
    protected String folderPath;
    protected String fileName;

    public JmsArchive() throws IOException {
        this.folderPath = "JmsArchive";
        this.fileName = "storedJmsMessages.bin";
        createFolder(folderPath);
    }
    
    
    public void saveJmsList(List<SerializabledJmsMessage> messageList) throws Exception{
        for(SerializabledJmsMessage msg : messageList){
            saveJms(msg);
        }   
    }
    public void saveJms(SerializabledJmsMessage msg) throws Exception{      
        List<SerializabledJmsMessage> presentMsgs = readAll();
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
    
    public List<SerializabledJmsMessage> readAll() throws Exception{
        File file = new File(folderPath + "/" + fileName);
        long length = file.length();
        if(length == 0){
            return new ArrayList<SerializabledJmsMessage>();
        }
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<SerializabledJmsMessage> dataList = (List<SerializabledJmsMessage>)ois.readObject();
        ois.close();
        fis.close();
        return dataList;
    }
    
    
    
    private void createFolder(String path) throws IOException{
        File folder = new File(folderPath );
        folder.mkdirs();
        File file = new File(folderPath + "/" + fileName);
        file.createNewFile();
        
    }
}
