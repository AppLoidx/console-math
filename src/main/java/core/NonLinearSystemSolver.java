package core;

import util.function.ExtendedFunction;

import java.util.List;

/**
 * @author Arthur Kupriyanov on 06.04.2020
 */
public interface NonLinearSystemSolver {
    double[] nonlinearSystemSolver(List<ExtendedFunction> extendedFunctions, double accuracy);
    double getLastAnswer();
}
