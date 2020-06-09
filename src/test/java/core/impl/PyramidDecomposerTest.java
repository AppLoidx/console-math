package core.impl;

import model.impl.SquareMatrix;
import org.junit.jupiter.api.Test;
import util.printer.MatrixPrinter;
import util.printer.impl.SimplePrettyPrinter;

/**
 * @author Arthur Kupriyanov on 03.03.2020
 */
class PyramidDecomposerTest {

    private static final MatrixPrinter printer = new SimplePrettyPrinter();

    @Test
    public void simpleTest() {
        SquareMatrix squareMatrix = new SquareMatrix();
        squareMatrix.init(new float[][]{
                {3, 2, -5, -1},
                {2, -1, 3, 13},
                {1, 2, -1, 9}
        });
        printer.prettyPrint(squareMatrix, System.out);
        System.out.println();
        printer.prettyPrint(new PyramidDecomposer().decompose(squareMatrix), System.out);
    }

}