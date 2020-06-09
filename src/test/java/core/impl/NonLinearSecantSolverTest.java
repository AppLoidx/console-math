package core.impl;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import util.function.ExtendedFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Arthur Kupriyanov on 07.04.2020
 */
class NonLinearSecantSolverTest {
    @Test
    public void simpleTest() {
        ExtendedFunction f = new ExtendedFunction(x -> Math.pow(x, 3) - x + 4);

        f.setBoundaries(-1, -2);

        double accuracy = 0.0001d;
        double expected = -1.7963d;

        assertEquals(expected, new NonLinearSecantSolver().solve(f, accuracy), accuracy);

    }

    @Test
    public void sin_x() {
        ExtendedFunction f = new ExtendedFunction(x -> Math.sin(x));

        f.setBoundaries(-6, 5);

        double accuracy = 0.00001d;
        double expected = 3.57764d;

//        assertEquals(expected, new NonLinearSecantSolver().solve(f, accuracy), accuracy);
        System.out.println(new NonLinearSecantSolver().solve(f, accuracy));
    }

    @Test
    @Disabled
    public void sin_x_plus_1() {
        ExtendedFunction f = new ExtendedFunction(x -> Math.sin(x) + 1);

        f.setBoundaries(3, 4);

        double accuracy = 0.1d;
        double expected = (3 * Math.PI) / 2;

        assertEquals(expected, new NonLinearSecantSolver().solve(f, accuracy), accuracy);
    }

    @Test
    public void simple_test() {
        ExtendedFunction f = new ExtendedFunction(x -> Math.pow(x, 3) - 4 * x + 4);

        f.setBoundaries(-3, -2);

        System.out.println(new NonLinearSecantSolver().solve(f, 0.0001d));
    }
}