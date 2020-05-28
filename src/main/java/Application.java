import pl.atos.exercise.controller.CalculatorController;
import pl.atos.exercise.service.CalculatorService;

import java.io.*;
import java.util.*;

import static java.lang.System.exit;

public class Application{

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        CalculatorService calculatorService = new CalculatorService(
                new File("src/main/resources/eurofxref-daily.xml"));

        CalculatorController calculatorController = new CalculatorController(calculatorService);
        calculatorService.getDataFromXml();

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Welcome to simple currency calculator! You can convert euros to other currencies.");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        while(true) {
            System.out.println("Please, provide symbol of the currency that you wish to convert:");
            String currency = scanner.next().toUpperCase();
            System.out.println("Please, provide an amount of euro you wish to convert:");
            String amountString = scanner.next();
            System.out.println(calculatorController.calculatorCurrency(currency, amountString));
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
