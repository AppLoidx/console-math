package core.impl;

import core.NonLinearSolver;
import util.function.DerivativeFunction;
import util.function.ExtendedFunction;

/**
 * @author Arthur Kupriyanov on 07.04.2020
 */
public class NonLinearIterationSolver implements NonLinearSolver {

    private final static int MAX_ITERATION = 50_000_000;
    private Double lastXVal;

    @Override
    public double solve(ExtendedFunction extFunction, double accuracy) {

        double lambda = - 1d /
                        ExtendedFunction.getMaxValueOfFunc(
                                extFunction.getDerivativeFunction(),
                                extFunction.getBoundaries()[0],
                                extFunction.getBoundaries()[1],
                                accuracy);
        System.out.println("lambda" + lambda);
        ExtendedFunction supportFunc = new ExtendedFunction(x -> x + lambda * extFunction.apply(x));
        supportFunc.setDerivativeFunction(new DerivativeFunction(x -> 1 + lambda * extFunction.getDerivativeFunction().apply(x)));
        double x0;
        double x = extFunction.getBoundaries()[0];
        int counter = 0;
        do {
            x0 = x;
            x = supportFunc.apply(x);
            counter++;
        } while(Math.abs(x - x0) >= accuracy && counter < MAX_ITERATION);
        System.out.println(supportFunc.getDerivativeFunction().apply(x));
        lastXVal = x;
        if (counter == MAX_ITERATION) {
            throw new IllegalArgumentException("Превышено макмимальное количество итераций (50_000_000)");
        }

        return x;
    }

    @Override
    public double getLastAnswer() {
        return lastXVal;
    }

    public Double getLastXVal() {
        return lastXVal;
    }
}
