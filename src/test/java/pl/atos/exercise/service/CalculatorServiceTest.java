package pl.atos.exercise.service;

import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.*;

public class CalculatorServiceTest{

    @Test
    public void calculatorWorksWithCorrectData(){
        CalculatorService calculatorService = new CalculatorService(
                new File("src/main/resources/eurofxref-daily.xml"));
        calculatorService.getDataFromXml();

        assertThat(calculatorService.calculator("BRL", 10.5f))
                .isEqualTo(61.61295f);
    }

    @Test
    public void calculatorThrowsNullPointerExIfCurrencyNotFound(){
        CalculatorService calculatorService = new CalculatorService(
                new File("src/main/resources/eurofxref-daily.xml"));
        calculatorService.getDataFromXml();

        assertThatThrownBy(() -> {
            calculatorService.calculator("BRLLL", 10.5f);
        }).isInstanceOf(NullPointerException.class);
    }

}
