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

    private static final int MAX_POINTS_COUNT = 100;

    @Override
    public List<Dot> solve(DiffEquation equation, double x0, double y0, double accuracy) {
        EulerSolverConfig config = calculateConfiguration(equation, x0, y0, accuracy);
        return solve(equation, x0, y0, config.pointsAmount, config.step);
    }

    @Override
    public List<Dot> solve(DiffEquation equation, double x0, double y0, int pointsAmount, double step) {
        List<Dot> dots = new ArrayList<>();
        dots.add(new SimpleDot(x0, y0));

        double x = x0;
        double calculatedY = y0;

        for (int i = 0; i < pointsAmount; i++) {
            double approximatedY = approximateNextY(equation, step, x, calculatedY);
            calculatedY = calculateY(equation, step, x, calculatedY, approximatedY);
            x += step;
            dots.add(new SimpleDot(x, calculatedY));
        }

        return dots;
    }

    private EulerSolverConfig calculateConfiguration(DiffEquation equation, double x0, double y0, double accuracy) {
        double x = x0;
        double calculatedY = y0;
        double step = getSignedStep(x0);
        int pointsAmount = 10;
        for (int i = 0; i < pointsAmount; i++) {
            double approximatedY = approximateNextY(equation, step, x, calculatedY);
            double oldY = calculatedY;
            calculatedY = calculateY(equation, step, x, calculatedY, approximatedY);

            while (Math.abs(calculatedY - approximatedY) > Math.abs(accuracy * calculatedY)) {
                if (pointsAmount > MAX_POINTS_COUNT) {
                    return new EulerSolverConfig(MAX_POINTS_COUNT, step);
                }

                step = step / 2;
                pointsAmount = pointsAmount * 2 ;
                approximatedY = approximateNextY(equation, step, x, oldY);
                calculatedY = calculateY(equation, step, x, oldY, approximatedY);
            }
            x += step;
        }

        return new EulerSolverConfig(pointsAmount, step);
    }

    private double approximateNextY(DiffEquation equation, double h, double x, double y){
        return y +  h * equation.apply(x, y);
    }

    private double calculateY(DiffEquation equation, double h, double x, double y, double approximatedY){
        return y +  h * (equation.apply(x, y) + equation.apply(x +  h, approximatedY)) / 2;
    }

    private double getSignedStep(double x0){
        double sign = x0 >= 0 ? -1 : 1;
        return  0.1 * sign;
    }


    private static class EulerSolverConfig {
        public final int pointsAmount;
        public final double step;

        private EulerSolverConfig(int pointsAmount, double step) {
            this.pointsAmount = pointsAmount;
            this.step = step;
        }
    }
}
