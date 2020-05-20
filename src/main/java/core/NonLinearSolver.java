package core;

import util.function.DerivativeFunction;
import util.function.ExtendedFunction;

import java.util.function.Function;

/**
 * @author Arthur Kupriyanov on 06.04.2020
 */
public interface NonLinearSolver {
    double solve(ExtendedFunction extFunction, double accuracy);
    double getLastAnswer();

    /**
     * Creates function <code>x = p(x)</code> from f(x)
     *
     * It can be used for fixed point iteration method
     *
     * @param extFunction original function
     * @param accuracy epsilon for getting max value from func {@link ExtendedFunction#getMaxValueOfFunc(Function, double, double, double)}
     * @return function with form x = p(x)
     */
    static ExtendedFunction createSupportFunction(ExtendedFunction extFunction, double accuracy){
        double lambda = - 1d /
                        ExtendedFunction.getMaxValueOfFunc(
                                extFunction.getDerivativeFunction(),
                                extFunction.getBoundaries()[0],
                                extFunction.getBoundaries()[1],
                                accuracy);
        ExtendedFunction supportFunc = new ExtendedFunction(x -> x + lambda * extFunction.apply(x));
        supportFunc.setDerivativeFunction(new DerivativeFunction(x -> 1 + lambda * extFunction.getDerivativeFunction().apply(x)));

        return supportFunc;
    }
}
