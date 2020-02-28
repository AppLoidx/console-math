package util.impl;

import model.Matrix;
import model.impl.SquareMatrix;
import org.junit.jupiter.api.Test;
import testutil.RandomizerUtil;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Arthur Kupriyanov on 28.02.2020
 */
class DatabaseLikePrinterTest {

    @Test
    public void prettyPrint(){
        Matrix<Integer> matrix = new SquareMatrix<>();
        matrix.init(new RandomizerUtil().getRandomMatrix(Integer.class, 12, 12));

        new DatabaseLikePrinter().prettyPrint(matrix, System.out);
    }

}