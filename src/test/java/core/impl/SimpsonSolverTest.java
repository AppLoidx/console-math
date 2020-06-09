package core.impl;

import core.IntegralSolver;
import org.junit.jupiter.api.Test;
import util.function.ExtendedFunction;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Arthur Kupriyanov on 18.03.2020
 */
class SimpsonSolverTest {
    @Test
    public void solve() {
        Function<Double, Double> function = aDouble -> 2 * aDouble * aDouble;
        final int topBoundary = 2;
        final int bottomBoundary = 1;
        IntegralSolver solver = new SimpsonSolver();

        final double expectedValue = 14 / 3d;

        assertEquals(expectedValue, solver.solve(new ExtendedFunction(function), topBoundary, bottomBoundary, 1000));
    }

    @Test
    public void solveWithAccuracy_2xx() {
        Function<Double, Double> function = x -> 2 * x * x;   // 2 * x ^ 2
        final int topBoundary = 2;
        final int bottomBoundary = 1;
        SimpsonSolverExtended solver = new SimpsonSolverExtended();

        final double expectedValue = 14 / 3d;
        final double accuracy = 0.001d;

        assertEquals(expectedValue, solver.solveWithAccuracy(new ExtendedFunction(function), topBoundary, bottomBoundary, accuracy), accuracy);
        assertEquals(-expectedValue, solver.solveWithAccuracy(new ExtendedFunction(function), bottomBoundary, topBoundary, accuracy), accuracy);


        System.out.println(solver.getLastAccuracy());
        System.out.println(solver.getLastPartition());
    }

    @Test
    public void solveWithAccuracy_xx() {
        Function<Double, Double> function = x -> x * x;   // 2 * x ^ 2
        final int topBoundary = 2;
        final int bottomBoundary = 1;
        SimpsonSolverExtended solver = new SimpsonSolverExtended();

        final double expectedValue = 2.333d;
        final double accuracy = 0.001d;

        assertEquals(expectedValue, solver.solveWithAccuracy(new ExtendedFunction(function), topBoundary, bottomBoundary, accuracy), accuracy);
        assertEquals(-expectedValue, solver.solveWithAccuracy(new ExtendedFunction(function), bottomBoundary, topBoundary, accuracy), accuracy);


        System.out.println(solver.getLastAccuracy());
        System.out.println(solver.getLastPartition());
    }

    @Test
    public void solveWithAccuracy_1partitionX() {
        Function<Double, Double> function = x -> 1 / x;   // 2 * x ^ 2
        final int topBoundary = 1;
        final int bottomBoundary = -1;
        SimpsonSolverExtended solver = new SimpsonSolverExtended();

        final double expectedValue = 0;
        final double accuracy = 0.001d;

        assertEquals(expectedValue, solver.solveWithAccuracy(new ExtendedFunction(function), topBoundary, bottomBoundary, accuracy), accuracy);
    }
}