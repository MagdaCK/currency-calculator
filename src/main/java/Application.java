import org.w3c.dom.*;
import org.xml.sax.SAXException;
import pl.atos.exercise.service.CalculatorService;

import javax.xml.parsers.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.*;

import static java.lang.Float.parseFloat;
import static java.lang.System.exit;

public class Application{

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException{

        CalculatorService calculatorService = new CalculatorService(new File("src/main/resources/eurofxref-daily.xml"));

//        HashMap<String, Float> dataFromXml= calculatorService.getDataFromXml();

        Scanner scanner = new Scanner(System.in);

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Welcome to simple currency calculator! You can convert euros to other currencies.");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");


        while(true) {
            System.out.println("Please, provide symbol of the currency that you wish to convert:");
            String currency = scanner.next().toUpperCase();
            System.out.println("Please, provide an amount of euro you wish to convert:");
            String amountString = scanner.next();
            Float amount = parseFloat(amountString);
            System.out.println(currency);
            System.out.println(amount);
            System.out.println(calculatorService.calculator(currency, amount));
            System.out.println("Would you like to make anther calculation? y/n");
            String answer = scanner.next();
            switch(answer) {
                case "y":
                    break;
                case "n":
                default:
                    exit(0);
            }
        }




    }
}
