package core;

import util.function.ExtendedFunction;

/**
 * @author Arthur Kupriyanov on 18.03.2020
 */
public interface IntegralSolver {
    double solve(ExtendedFunction function, int top, int bottom, double partition);
}
