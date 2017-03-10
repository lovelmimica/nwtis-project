/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.lovmimica.app;

import com.foi.nwtis.lovmimica.logika.ws.MeteoData;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.InputSource;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author lovelmimica
 */
public class XmlParser {
    public static List<String> parseAddres(String xmlRecord) throws Exception{
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource is = new InputSource();
        
        is.setCharacterStream(new StringReader(xmlRecord));
        Document doc = db.parse(is);
        NodeList nodes = doc.getElementsByTagName("naziv");
        
        
        List<String> nodeNames = new ArrayList<String>();
        for(int i = 0; i<nodes.getLength(); i++){
            String name = nodes.item(i).getTextContent();
            nodeNames.add(name);
        }
        
        return nodeNames;
    }
    public static List<MeteoData> parseForecast(String xmlRecord) throws Exception {
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource is = new InputSource();

        is.setCharacterStream(new StringReader(xmlRecord));
        Document doc = db.parse(is);

        List<MeteoData> meteoForceast = new ArrayList<MeteoData>();

        Integer listSize = doc.getElementsByTagName("meteoPodaci").getLength();
        for (int i = 0; i < listSize; i++) {
            MeteoData md = new MeteoData();

            //temperatura
            String temperatureStr = doc.getElementsByTagName("temperatura").item(i).getTextContent();
            Float temperature = new Float(temperatureStr);
            md.setTemperature(temperature);
            //tlak
            String pressureStr = doc.getElementsByTagName("tlak").item(i).getTextContent();
            Float pressure = new Float(pressureStr);
            md.setPressure(pressure);
            //vjetar
            String windStr = doc.getElementsByTagName("vjetar").item(i).getTextContent();
            Float wind = new Float(windStr);
            md.setWind(wind);
            //vlaga
            String humidityStr = doc.getElementsByTagName("vlaga").item(i).getTextContent();
            Float humidity = new Float(humidityStr);
            md.setHumidity(humidity);

            meteoForceast.add(md);
        }

        return meteoForceast;
    }
}
