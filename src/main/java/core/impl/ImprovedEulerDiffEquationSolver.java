package core.impl;

import core.DiffEquationSolver;
import util.function.DiffEquation;
import util.function.SimpleDot;
import util.function.interfaces.Dot;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Arthur Kupriyanov on 01.06.2020
 */
public class ImprovedEulerDiffEquationSolver implements DiffEquationSolver {
    @Override
    public List<Dot> solve(DiffEquation func, double x0, double y0) {
        List<Dot> dots = new ArrayList<>();
        dots.add(new SimpleDot(x0, y0));

        double x = x0;
        double y = y0;
        double h = 0.1; // todo: avoid hardcode

        for (int i = 0; i < 5; i++) {   // todo: avoid hardcode (number of iterations)
            double step = y + h * func.apply(x, y);
            y = y + h * (func.apply(x, y) + func.apply(x + h , step)) / 2;
            x += h;

            dots.add(new SimpleDot(x, y));
        }

        return dots;
    }
}
