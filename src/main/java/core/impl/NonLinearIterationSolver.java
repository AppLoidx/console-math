package core.impl;

import core.NonLinearSolver;
import util.function.ExtendedFunction;

/**
 * @author Arthur Kupriyanov on 07.04.2020
 */
public class NonLinearIterationSolver implements NonLinearSolver {

    private final static int MAX_ITERATION = 50_000_000;
    private Double lastXVal;

    @Override
    public double solve(ExtendedFunction extFunction, double accuracy) {

        ExtendedFunction supportFunc = NonLinearSolver.createSupportFunction(extFunction, accuracy);
        double x0;
        double x = extFunction.getBoundaries()[0];
        int counter = 0;
        do {
            x0 = x;
            x = supportFunc.apply(x);
            if (Math.abs(supportFunc.getDerivativeFunction().apply(x)) > 1) {
                throw new IllegalArgumentException("[WARN]: φ'(x) > 1");
            }
            counter++;
        } while (Math.abs(x - x0) >= accuracy && counter < MAX_ITERATION);

        lastXVal = x;
        if (counter == MAX_ITERATION) {
            throw new IllegalArgumentException("Превышено максимальное количество итераций (50_000_000)");
        }

        return x;
    }

    @Override
    public double getLastAnswer() {
        return lastXVal == null ? Double.NaN : lastXVal;
    }
}
