package util.impl;

import model.Matrix;
import model.impl.SquareMatrix;
import org.junit.jupiter.api.Test;
import testutil.RandomizerUtil;
import util.impl.SimplePrettyPrinter;

/**
 * @author Arthur Kupriyanov on 28.02.2020
 */
class SimplePrettyPrinterTest {

    @Test
    public void prettyPrint(){
        Matrix<Integer> matrix = new SquareMatrix<>();
        matrix.init(new RandomizerUtil().getRandomMatrix(Integer.class, 12, 12));

        new SimplePrettyPrinter().prettyPrint(matrix, System.out);
    }

}