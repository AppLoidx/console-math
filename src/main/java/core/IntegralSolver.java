package core;

import util.function.ExtendedFunction;

/**
 * @author Arthur Kupriyanov on 18.03.2020
 */
public interface IntegralSolver {
    double solve(ExtendedFunction function, double top, double bottom, double partition);
}
