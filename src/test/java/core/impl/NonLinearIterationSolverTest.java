package core.impl;

import org.junit.jupiter.api.Test;
import util.function.DerivativeFunction;
import util.function.ExtendedFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Arthur Kupriyanov on 08.04.2020
 */
class NonLinearIterationSolverTest {

    @Test
    public void simpleTest() {
        NonLinearIterationSolver solver = new NonLinearIterationSolver();
        ExtendedFunction f = new ExtendedFunction(x -> Math.pow(x, 3) - x + 4);
        f.setBoundaries(-2, 1);
        DerivativeFunction dFunc = new DerivativeFunction(x -> Math.pow(x, 2) * 3 - 1);
        dFunc.setIsInRange(x -> true);
        f.setDerivativeFunction(dFunc);

        double ans = solver.solve(f, 0.0001d);
        assertEquals(-1.7963d, ans, 0.0001d);
    }

    @Test
    public void simpleTest_linear() {
        NonLinearIterationSolver solver = new NonLinearIterationSolver();
        ExtendedFunction f = new ExtendedFunction(x -> x * 2);
        f.setBoundaries(0, 1);
        DerivativeFunction dFunc = new DerivativeFunction(x -> 2d);
        dFunc.setIsInRange(x -> true);
        f.setDerivativeFunction(dFunc);

        double ans = solver.solve(f, 0.0001d);
        assertEquals(0, ans, 0.0001d);
    }

    @Test
    public void simpleTest_linear_sinX() {
        NonLinearIterationSolver solver = new NonLinearIterationSolver();
        ExtendedFunction f = new ExtendedFunction(Math::sin);
        f.setBoundaries(0, 1);
        DerivativeFunction dFunc = new DerivativeFunction(x -> -Math.cos(x));
        dFunc.setIsInRange(x -> true);
        f.setDerivativeFunction(dFunc);

        double ans = solver.solve(f, 0.0001d);
        assertEquals(0, ans, 0.0001d);
    }

    @Test
    public void simpleTest_linear_sinX_2() {
        NonLinearIterationSolver solver = new NonLinearIterationSolver();
        ExtendedFunction f = new ExtendedFunction(Math::sin);
        f.setBoundaries(-1, 1);
        DerivativeFunction dFunc = new DerivativeFunction(x -> -Math.cos(x));
        dFunc.setIsInRange(x -> true);
        f.setDerivativeFunction(dFunc);

        double ans = solver.solve(f, 0.0001d);
        assertEquals(0, ans, 0.0001d);
    }

}