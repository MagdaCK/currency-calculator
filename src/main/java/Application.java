import org.w3c.dom.*;
import org.xml.sax.SAXException;
import pl.atos.exercise.service.CalculatorService;

import javax.xml.parsers.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static java.lang.Float.parseFloat;

public class Application{

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException{

        CalculatorService calculatorService = new CalculatorService(new File("src/main/resources/eurofxref-daily.xml"));

        HashMap<String, Float> dataFromXml= calculatorService.getDataFromXml();

        // Get a set of the entries
        Set set=dataFromXml.entrySet();
        // Get an iterator
        Iterator i=set.iterator();

        // Display elements
        while(i.hasNext())

        {
            Map.Entry me=(Map.Entry) i.next();
            System.out.print(me.getKey() + ": ");
            System.out.println(me.getValue());
        }
        System.out.println();


    }
}
