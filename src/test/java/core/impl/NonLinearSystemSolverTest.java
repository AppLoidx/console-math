package core.impl;

import org.junit.jupiter.api.Test;
import util.function.DerivativeFunction;
import util.function.ExtendedFunction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Arthur Kupriyanov on 23.04.2020
 */
class NonLinearSystemSolverTest {

    @Test
    public void simpleTest(){
        NonLinearSystemSolver systemSolver = new NonLinearSystemSolver();
        ExtendedFunction func1 = new ExtendedFunction(x -> Math.sqrt(x + 1));
        func1.setBoundaries(1, 6d);
        func1.setDerivativeFunction(new DerivativeFunction(x -> 1/2d * 1/Math.sqrt(x + 1)));

        ExtendedFunction func2 = new ExtendedFunction(x -> Math.sqrt(x + 1));
        func2.setBoundaries(1d, 6d);
        func2.setDerivativeFunction(new DerivativeFunction(x -> 1/2d * 1/Math.sqrt(x + 1)));

        systemSolver.nonlinearSystemSolver(List.of(func1, func2), 0.00001d);
    }

}