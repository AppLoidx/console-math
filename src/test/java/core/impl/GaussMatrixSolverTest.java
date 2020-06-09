package core.impl;

import model.Matrix;
import model.impl.SquareMatrix;
import org.junit.jupiter.api.Test;
import testutil.RandomizerUtil;
import util.printer.MatrixPrinter;
import util.printer.impl.SimplePrettyPrinter;

import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Arthur Kupriyanov on 04.03.2020
 */
class GaussMatrixSolverTest {
    private final static MatrixPrinter PRINTER = new SimplePrettyPrinter();

    private static final PrintStream OUT = System.out;

    @Test
    public void test() {

        SquareMatrix squareMatrix = new SquareMatrix();
        squareMatrix.init(new float[][]{
                {3, 2, -5, -1},
                {2, -1, 3, 13},
                {1, 2, -1, 9}
        });

        GaussMatrixSolver solver = new GaussMatrixSolver(squareMatrix);

        float[] variables = solver.getVariables();
        float determinant = solver.getDeterminant();
        Matrix triangleMatrix = solver.getTriangleMatrix();

        printSolution(squareMatrix, triangleMatrix, determinant, variables, solver);

        squareMatrix.init(RandomizerUtil.getRandomMatrix(7, 6));

        solver = new GaussMatrixSolver(squareMatrix);
        variables = solver.getVariables();
        determinant = solver.getDeterminant();
        triangleMatrix = solver.getTriangleMatrix();

        printSolution(squareMatrix, triangleMatrix, determinant, variables, solver);

        squareMatrix.init(RandomizerUtil.getRandomMatrix(4, 3));

        solver = new GaussMatrixSolver(squareMatrix);
        variables = solver.getVariables();
        determinant = solver.getDeterminant();
        triangleMatrix = solver.getTriangleMatrix();

        printSolution(squareMatrix, triangleMatrix, determinant, variables, solver);

    }

    @Test
    public void canBeSolved() {
        SquareMatrix squareMatrix = new SquareMatrix();
        squareMatrix.init(new float[][]{
                {0, 2, -5, -1},
                {2, 0, 3, 13},
                {1, 2, 0, 9}
        });
        assertFalse(GaussMatrixSolver.isCanBeSolved(squareMatrix));

        squareMatrix.init(new float[][]{
                {3, 2, -5, -1},
                {2, -1, 3, 13},
                {1, 2, -1, 9}
        });

        assertTrue(GaussMatrixSolver.isCanBeSolved(squareMatrix));
    }

    private void printSolution(Matrix source, Matrix triangle, float determinant, float[] variables, GaussMatrixSolver solver) {
        OUT.println("Исходная матрца: ");
        PRINTER.prettyPrint(source, OUT);

        OUT.println("Треугольная матрица: ");
        PRINTER.prettyPrint(triangle, OUT);

        OUT.print("Детерминант:");
        OUT.println(determinant);

        OUT.print("Переменные: ");
        int index = 1;
        for (float val : variables) {
            OUT.print(String.format("x%d: %f, ", index, val));
            index++;
        }

        OUT.print("\nНевязка: " + Arrays.toString(solver.getResidualColumn()));
    }
}