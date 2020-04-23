package util;

import org.junit.jupiter.api.Test;

/**
 * @author Arthur Kupriyanov on 08.04.2020
 */
class DoubleUtilTest {
    @Test
    public void test(){
        double val = 0.6998254341386636d;
        double acc = 0.0001d;
        System.out.println(DoubleUtil.round(val, acc));
    }
}