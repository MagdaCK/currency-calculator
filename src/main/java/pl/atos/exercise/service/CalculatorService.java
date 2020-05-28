package pl.atos.exercise.service;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Float.parseFloat;

public class CalculatorService{

    public static final Logger log = Logger.getLogger(CalculatorService.class.getName());

    private final File xmlToLoad;

    private final HashMap<String, Float> dataFromXml = new HashMap<>();

    public CalculatorService(File xmlToLoad){
        this.xmlToLoad = xmlToLoad;
    }

    /**
     * Method uploads data from xml file (from resource folder) to the memory as HashMap where currency symbol
     * is a key and rate is a value
     * @return HashMap with currency symbol-rate pairs
     */
    public HashMap<String, Float> getDataFromXml() {

        //Build Document
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder=null;
        try{
            builder=factory.newDocumentBuilder();
        } catch(ParserConfigurationException e){
            log.log(Level.WARNING, "Parser Configuration error", e);
        }

        Document document=null;
        try{
            document=builder.parse(xmlToLoad);
        } catch(SAXException | IOException e){
            log.log(Level.WARNING, "SAXException/IOException", e);
        }

        //Normalize the XML Structure
        document.getDocumentElement().normalize();

        //Get the root node
        Element root=document.getDocumentElement();

        //Get childNodes till the last Cube
        NodeList nodesWithData = root.getChildNodes().item(5).getChildNodes().item(1).getChildNodes();

        for(int i=0; i<nodesWithData.getLength(); i++){
            if(nodesWithData.item(i).hasAttributes()){
                NamedNodeMap nodesAttr = nodesWithData.item(i).getAttributes();
                String rateString=nodesAttr.getNamedItem("rate").getNodeValue();
                String currency=nodesAttr.getNamedItem("currency").getNodeValue();
                Float rate=parseFloat(rateString);

                dataFromXml.put(currency, rate);
            }
        }
        return dataFromXml;
    }

    /**
     * Method calculates the amount of introduced euro to other currency
     * @param currency to which euro amount is about to be converted
     * @param amountToConvert quantity of money to convert
     * @return converted euros (to other currency)
     */
    public Float calculator(String currency, Float amountToConvert) {

        Float rate = dataFromXml.get(currency);

        return amountToConvert * rate;
    }

}
