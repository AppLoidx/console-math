package core;

import util.function.DiffEquation;
import util.function.interfaces.Dot;

import java.util.List;

/**
 * @author Arthur Kupriyanov on 01.06.2020
 */
public interface DiffEquationSolver {

    List<Dot> solve(DiffEquation func, double x0, double y0);
}
