package core.impl;

import model.Matrix;
import model.impl.SquareMatrix;
import org.junit.jupiter.api.Test;
import util.printer.MatrixPrinter;
import util.printer.impl.SimplePrettyPrinter;

import java.io.PrintStream;
import java.util.Arrays;

/**
 * @author Arthur Kupriyanov on 04.03.2020
 */
class GaussMatrixSolverTest {
    private final static MatrixPrinter PRINTER = new SimplePrettyPrinter();

    private static final PrintStream OUT = System.out;

    @Test
    public void test(){

        SquareMatrix squareMatrix = new SquareMatrix();
        squareMatrix.init(new float[][]{
                {3, 2, -5, -1},
                {2, -1, 3 , 13},
                {1, 2 , -1 , 9}
        });

        GaussMatrixSolver solver = new GaussMatrixSolver(squareMatrix);

        float[] variables = solver.getVariables();
        float determinant = solver.getDeterminant();
        Matrix triangleMatrix = solver.getTriangleMatrix();

        OUT.println("Исходная матрца: ");
        PRINTER.prettyPrint(squareMatrix, OUT);

        OUT.println("Треугольная матрица: ");
        PRINTER.prettyPrint(triangleMatrix, OUT);

        OUT.print("Детерминант:");
        OUT.println(determinant);

        OUT.print("Переменные: ");
        int index = 1;
        for (float val : variables){
            OUT.print(String.format("x%d: %f, ", index, val));
            index++;
        }

        OUT.print("\nНевязка: " + Arrays.toString(solver.getResidualColumn()));
    }
}