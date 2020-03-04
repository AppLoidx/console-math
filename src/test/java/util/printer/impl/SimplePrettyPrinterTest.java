package util.printer.impl;

import model.Matrix;
import model.impl.SquareMatrix;
import org.junit.jupiter.api.Test;
import testutil.RandomizerUtil;

import java.util.Arrays;

/**
 * @author Arthur Kupriyanov on 28.02.2020
 */
class SimplePrettyPrinterTest {

    @Test
    public void prettyPrint(){
        Matrix matrix = new SquareMatrix();
        matrix.init(new RandomizerUtil().getRandomMatrix(12, 12));
        for (int i = 0; i < matrix.getYSize(); i++) {
            for (int j = 0; j < matrix.getXSize(); j++) {
                System.out.print(" " + matrix.getElement(i, j) + " ");
            }
            System.out.println();
        }
        System.out.println();
        new SimplePrettyPrinter().prettyPrint(matrix, System.out);
    }

}