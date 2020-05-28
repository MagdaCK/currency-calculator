package pl.atos.exercise.controller;

import pl.atos.exercise.service.CalculatorService;

import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Float.parseFloat;

public class CalculatorController{

    public static final Logger log = Logger.getLogger(CalculatorController.class.getName());

    private CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService){
        this.calculatorService=calculatorService;
    }

    /**
     * Method returns String of converted euro; validates user's inputs
     * @param currency symbol of currency to convert euro to
     * @param amountString euro amount to convert
     * @return String of the euro converted to other currency
     */
    public String calculatorCurrency(String currency, String amountString) {
        if(ifCurrencyEmpty(currency)) {
            return "Currency cannot be empty";
        }
        if(ifAmountEmpty(amountString)) {
            return "Euros amount cannot be empty";
        }
        if(!ifCurrencySymbolExistsInFile(currency)) {
            return "Currency could not have been found";
        }
        amountString = ifCommaChangeToDot(amountString);
        Float amount = parseAmountToFloat(amountString);
        if(amount == null) {
            return "Incorrect format of euros amount";
        }
        return amountString+" EUR = "+calculatorService.calculator(currency, amount)+" "+currency;
    }

    //Validating methods
    private boolean ifCurrencyEmpty(String currency) {
        if(currency.isEmpty()) {
            return true;
        }
        return false;
    }
    private boolean ifAmountEmpty(String amountString) {
        if(amountString.isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean ifCurrencySymbolExistsInFile(String currency) {
        if(calculatorService.getDataFromXml().keySet().contains(currency)){
            return true;
        }
        return false;

    }

    private String ifCommaChangeToDot(String amountString) {
        if (amountString.contains(",")) {
            return amountString.replace(",", ".");
        }
        return amountString;
    }

    private Float parseAmountToFloat(String amountString) {
        try {
            return parseFloat(amountString);
        } catch (NumberFormatException ex) {
            log.log(Level.WARNING, "Incorrect format", ex);
            return null;
        }
    }

}
