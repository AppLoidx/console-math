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
    public List<Dot> solve(DiffEquation equation, double x0, double y0, double accuracy) {
        List<Dot> dots = new ArrayList<>();
        dots.add(new SimpleDot(x0, y0));

        double x = x0;
        double calculatedY = y0;

        double[] base = calculateBase(equation, x0, y0, accuracy);
        int pointsAmount = (int) base[0];
        double step = base[1];

        for (int i = 0; i < pointsAmount; i++) {
            double approximatedY = approximateY(equation, step, x, calculatedY);
            calculatedY = calculateY(equation, step, x, calculatedY, approximatedY);
            x += step;
            dots.add(new SimpleDot(x, calculatedY));
        }

        return dots;
    }

    private double[] calculateBase(DiffEquation equation, double x0, double y0, double accuracy) {
        double x = x0;
        double calculatedY = y0;
        double step = getSignedStep(x0);
        int pointsAmount = 10;
        for (int i = 0; i < pointsAmount; i++) {
            double approximatedY = approximateY(equation, step, x, calculatedY);
            double oldY = calculatedY;
            calculatedY = calculateY(equation, step, x, calculatedY, approximatedY);

            while (Math.abs(calculatedY - approximatedY) > Math.abs(accuracy * calculatedY)) {
                step = step / 2;
                pointsAmount = pointsAmount * 2 ;
                approximatedY = approximateY(equation, step, x, oldY);
                calculatedY = calculateY(equation, step, x, oldY, approximatedY);
            }
            x += step;
        }

        return new double[]{pointsAmount, step};
    }

    private double approximateY(DiffEquation equation, double h, double x, double y){
        return y +  h * equation.apply(x, y);
    }

    private double calculateY(DiffEquation equation, double h, double x, double y, double approximatedY){
        return y +  h * (equation.apply(x, y) + equation.apply(x +  h, approximatedY)) / 2;
    }

    private double getSignedStep(double x0){
        double sign = x0 >= 0 ? -1 : 1;
        return  0.1 * sign;
    }
}
