package util.printer;

import org.junit.jupiter.api.Test;

/**
 * @author Arthur Kupriyanov on 03.03.2020
 */
class NumberConverterTest {
    @Test
    public void converterTest(){
        NumberConverter numberConverter = new NumberConverter();
        Float a = 15f;

        Float b = (Float) numberConverter.convertTo(Float.class, a.toString());

    }
}