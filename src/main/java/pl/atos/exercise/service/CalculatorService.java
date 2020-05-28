package pl.atos.exercise.service;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import pl.atos.exercise.model.Calculator;

import javax.xml.parsers.*;
import java.io.*;
import java.util.HashMap;

import static java.lang.Float.parseFloat;

public class CalculatorService{

    private File xmlToLoad;
    private Calculator calculator;

    public CalculatorService(File xmlToLoad){
        this.xmlToLoad = xmlToLoad;
    }

    private HashMap<String, Float> dataFromXml=new HashMap<>();

    public HashMap<String, Float> getDataFromXml() throws ParserConfigurationException, IOException, SAXException{

        //Build Document
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder=factory.newDocumentBuilder();

        Document document=builder.parse(xmlToLoad);

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

}
