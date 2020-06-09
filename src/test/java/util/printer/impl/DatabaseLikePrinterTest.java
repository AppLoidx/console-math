package util.printer.impl;

import model.Matrix;
import model.impl.SquareMatrix;
import org.junit.jupiter.api.Test;
import testutil.RandomizerUtil;

/**
 * @author Arthur Kupriyanov on 28.02.2020
 */
class DatabaseLikePrinterTest {

    @Test
    public void prettyPrint() {
        Matrix matrix = new SquareMatrix();
        matrix.init(new RandomizerUtil().getRandomMatrix(12, 12));

        new DatabaseLikePrinter().prettyPrint(matrix, System.out);
    }

    @Test
    public void smallTest() {
        System.out.println(Float.valueOf(0));
    }

}