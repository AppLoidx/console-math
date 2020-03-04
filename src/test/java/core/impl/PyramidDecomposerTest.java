package core.impl;

import model.Matrix;
import model.impl.SquareMatrix;
import org.junit.jupiter.api.Test;
import testutil.RandomizerUtil;
import util.printer.MatrixPrinter;
import util.printer.impl.SimplePrettyPrinter;

import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Arthur Kupriyanov on 03.03.2020
 */
class PyramidDecomposerTest {

    private static final MatrixPrinter printer = new SimplePrettyPrinter();

    @Test
    public void simpleTest(){
        SquareMatrix squareMatrix = new SquareMatrix();
        squareMatrix.init(new float[][]{
                {3, 2, -5, -1},
                {2, -1, 3 , 13},
                {1, 2 , -1 , 9}
        });
        printer.prettyPrint(squareMatrix, System.out);
        System.out.println();
        printer.prettyPrint(new PyramidDecomposer().decompose(squareMatrix), System.out);
    }

}