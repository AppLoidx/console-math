package core;

import util.function.ExtendedFunction;

/**
 * @author Arthur Kupriyanov on 06.04.2020
 */
public interface NonLinearSolver {
    double solve(ExtendedFunction extFunction, double accuracy);
    double getLastAnswer();
}
