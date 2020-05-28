package pl.atos.exercise.controller;

import org.junit.Test;
import pl.atos.exercise.service.CalculatorService;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorControllerTest{

    @Test
    public void calculatorWorksWithCorrectDataCurrencyWithDot(){
        CalculatorService calculatorService = new CalculatorService(
                new File("src/main/resources/eurofxref-daily.xml"));
        CalculatorController calculatorController = new CalculatorController(calculatorService);
        calculatorService.getDataFromXml();

        assertThat(calculatorController.calculatorCurrency("BRL", "10.5"))
                .isEqualTo("10.5 EUR = 61.61295 BRL");
    }

    @Test
    public void calculatorWorksWithCorrectDataCurrencyWithComma(){
        CalculatorService calculatorService = new CalculatorService(
                new File("src/main/resources/eurofxref-daily.xml"));
        CalculatorController calculatorController = new CalculatorController(calculatorService);
        calculatorService.getDataFromXml();

        assertThat(calculatorController.calculatorCurrency("BRL", "10,5"))
                .isEqualTo("10.5 EUR = 61.61295 BRL");
    }

    @Test
    public void calculatorWorksWithEmptyCurrency(){
        CalculatorService calculatorService = new CalculatorService(
                new File("src/main/resources/eurofxref-daily.xml"));
        CalculatorController calculatorController = new CalculatorController(calculatorService);
        calculatorService.getDataFromXml();

        assertThat(calculatorController.calculatorCurrency("", "10.5"))
                .isEqualTo("Currency cannot be empty");
    }

    @Test
    public void calculatorWorksWithEmptyAmount(){
        CalculatorService calculatorService = new CalculatorService(
                new File("src/main/resources/eurofxref-daily.xml"));
        CalculatorController calculatorController = new CalculatorController(calculatorService);
        calculatorService.getDataFromXml();

        assertThat(calculatorController.calculatorCurrency("BRL", ""))
                .isEqualTo("Euros amount cannot be empty");
    }

    @Test
    public void calculatorWorksWithIncorrectCurrency(){
        CalculatorService calculatorService = new CalculatorService(
                new File("src/main/resources/eurofxref-daily.xml"));
        CalculatorController calculatorController = new CalculatorController(calculatorService);
        calculatorService.getDataFromXml();

        assertThat(calculatorController.calculatorCurrency("BRhtrL", "10.5"))
                .isEqualTo("Currency could not have been found");
    }

    @Test
    public void calculatorWorksWithIncorrectAmountFormat(){
        CalculatorService calculatorService = new CalculatorService(
                new File("src/main/resources/eurofxref-daily.xml"));
        CalculatorController calculatorController = new CalculatorController(calculatorService);
        calculatorService.getDataFromXml();

        assertThat(calculatorController.calculatorCurrency("BRL", "10ht.5"))
                .isEqualTo("Incorrect format of euros amount");
    }

}
